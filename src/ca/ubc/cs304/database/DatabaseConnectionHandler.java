package ca.ubc.cs304.database;

import java.io.ObjectInputFilter.Config;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.security.auth.callback.ConfirmationCallback;

import ca.ubc.cs304.model.*;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	
	private Connection connection = null;
	
	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void deleteBranch(int branchId) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
			ps.setInt(1, branchId);
			
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
			}
			
			connection.commit();
	
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	
	public void insertBranch(BranchModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?,?,?,?)");
			ps.setInt(1, model.getId());
			ps.setString(2, model.getName());
			ps.setString(3, model.getAddress());
			ps.setString(4, model.getCity());
			if (model.getPhoneNumber() == 0) {
				ps.setNull(5, java.sql.Types.INTEGER);
			} else {
				ps.setInt(5, model.getPhoneNumber());
			}

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	
	// TRY TO MAKE A QUERY
	// View the number of available vehicles for a specific car type, location, and time interval.
	// The user should be able to provide any subset of {car type, location, time interval} to
	// view the available vehicles. If the user provides no information, your application should
	// automatically return a list of all vehicles (at that branch) sorted in some reasonable way
	// for the user to peruse.


	/*
	 * getAvailable Vehicles returns an array of VehiclesModel
	 * takes car type, location, time interval fromDate, fromTime, toDate, toTime
	 */
	public VehiclesModel[] getAvailableVehicles(String vtname, String location, Date fromDate, Time fromTime, Date toDate, Time toTime) {
		ArrayList<VehiclesModel> result = new ArrayList<>();

		try{
			Statement stmt = connection.createStatement();
			String executeString = "SELECT * FROM vehicles WHERE status = \"available\"";
			if (vtname != null) {
				executeString += " AND vtname = " + vtname;
			}
			if (location != null) {
				executeString += " AND location = " + location;
			}
			if (fromDate != null && fromTime != null && toDate != null && toTime != null) {
				executeString += " AND vlicense NOT IN (SELECT vlicense FROM reservations WHERE (fromDate > " + fromDate + 
																								"AND fromDate < " + toDate + 
																								") OR (toDate > " + fromDate + 
																								"AND toDate < " + toDate + 
																								" ) OR (fromDate = " + fromDate + 
																								"AND fromTime >= " + fromTime + 
																								" ) OR (fromDate = " + toDate + 
																								"AND fromTime <= " + toTime + 																								
																								" ) OR (toDate = " + fromDate + 
																								"AND toTime >= " + fromTime + 
																								" ) OR (toDate = " + toDate + 
																								"AND toTime <= " + toTime + 
																								"))"; 
			}
			
			// order by location
			executeString += " GROUP BY location";
			ResultSet rs = stmt.executeQuery(executeString);
			
			while(rs.next()) {
				VehiclesModel model = new VehiclesModel(rs.getString("vehicles_vlicense"),
													rs.getString("vehicles_make"),
													rs.getString("vehicles_model"),
													rs.getInt("vehicles_year"),
													rs.getString("vehicles_color"),
													rs.getInt("vehicles_odometer"),
													rs.getString("vehicles_status"),
													rs.getString("vehicles_vtname"),
													rs.getString("vehicles_location"),
													rs.getString("vehicles_city"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		}catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}	

		// result.size() would just be the number of available cars, use that in handler
		return result.toArray(new VehiclesModel[result.size()]);
	}

	// If a customer is new, add the customer’s details to the database.

	/*
	 *	insertCustomer adds a new customer to the customers table in the database
	 *  takes a customersModel
	 */
	public void insertCustomer(CustomersModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO customers VALUES (?,?,?,?)");
			ps.setInt(1, model.getDlicense());
			ps.setString(2, model.getName());
			if (model.getCellphone() <= 0) {
				ps.setNull(3, java.sql.Types.BIGINT);
			} else {
				ps.setInt(3, model.getCellphone());
			}
			if (model.getAddress() == null) {
				ps.setNull(4, java.sql.Types.VARCHAR);
			} else {
				ps.setString(4, model.getAddress());
			}

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	// Upon successful completion, a confirmation number for the reservation should be
	// shown along with the details entered during the reservation. Refer to the project
	// description for details on what kind of information the user needs to provide when
	// making a reservation.
	// If the customer’s desired vehicle is not available, an appropriate error message should
	// be shown.

	/*
	 * insertReservation returns the confNo which in our case is simply the
	 * order that the reservation was made
	 * returns 0 if there are no cars available
	 */
	public int insertReservation(ReservationsModel model) {
		int confNo = 0;
		try {

			// see if there's an available vehicle
			VehiclesModel[] available;
			String vlicense;
			available = getAvailableVehicles(model.getVtname(), null, model.getFromDate(), model.getFromTime(), model.getToDate(), model.getToTime());
			if (available.length == 0) {
				return confNo;
			} else {
				vlicense = available[0].getVlicense();
			}
			// figure out confNo?
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM reservations");
			confNo = rs.getInt(1) + 1;	// should return just an int

			PreparedStatement ps = connection.prepareStatement("INSERT INTO reservations VALUES (?,?,?,?,?,?,?,?)");
			ps.setInt(1, confNo);
			ps.setString(2, model.getVtname());
			ps.setString(3, vlicense);
			ps.setInt(4, model.getDlicense());
			ps.setDate(5, model.getFromDate());
			ps.setTime(6, model.getFromTime());
			ps.setDate(7, model.getToDate());
			ps.setTime(8, model.getToTime());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
		return confNo;
	}

	/*
	 * clerkRentVehicle returns a receipt with a lot of info for the
	 * rental that was made
	 * returns a message if there are no cars currently available
	 */
	public String clerkRentVehicle(RentalsModel rentmodel, ReservationsModel reservemodel){
		String receipt = "";

		try{

			int check = insertReservation(reservemodel);	//Do a reservation first to check all the things such as carmodel etc...
			if (check == 0){
				return "sorry no cars available to rent";
			}


			PreparedStatement ps = connection.prepareStatement("INSERT INTO rentals VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

			//VERY NOT SURE WHICH ONES ARE AVAIBLE ATM ESPECIALLY ODOMETER
			ps.setInt(1,rentmodel.getRid());
			ps.setString(2, rentmodel.getVlicense());
			ps.setInt(3, rentmodel.getDlicense());
			ps.setDate(4, rentmodel.getFromDate());
			ps.setTime(5, rentmodel.getFromTime());
			ps.setDate(6, rentmodel.getToDate());
			ps.setTime(7, rentmodel.getToTime());
			ps.setInt(8, rentmodel.getOdometer());
			ps.setString(9, rentmodel.getCardName());
			ps.setInt(10, rentmodel.getCardNo());
			ps.setDate(11, rentmodel.getExpDate());
			ps.setInt(12, rentmodel.getConfNo());

			ps.executeUpdate();
			connection.commit();

			ps.close();

			//builds receipt string
			receipt = receipt + "Confirmation Number: " + rentmodel.getConfNo()
					          + "\nReserve date: " + java.time.LocalDateTime.now()
							  + "\nType of car: " + reservemodel.getVtname()
					          + "\nAt: " + ""//TODO location
					          + "\nFrom " + rentmodel.getFromDate() + " To " + rentmodel.getToDate()
							  + "\n" + rentmodel.getFromTime() + " To " + rentmodel.getToTime();



		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		return receipt;


	}
	/*
	clerkReturnVehicle returns a receipt with information for the customer
	first checks to see if the rid exists in Returns
	throws a SQL exception if the car has not been taken out
	 */
	public String clerkReturnVehicle(ReturnsModel model) throws SQLException{
		String receipt = "";
		int rentalID = model.getRid();
		try{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM rentals WHERE rid = " + rentalID);
			if (rs == null) {
				throw new SQLException();
			}

			PreparedStatement ps = connection.prepareStatement("INSERT INTO rentals VALUES (?,?,?,?,?,?)");

			//VERY NOT SURE WHICH ONES ARE AVAIBLE ATM ESPECIALLY ODOMETER
			ps.setInt(1,model.getRid());
			ps.setDate(2, model.getDate());
			ps.setTime(3, model.getTime());
			ps.setInt(4, model.getOdometer());
			ps.setInt(5, model.getFulltank());
			ps.setFloat(6, model.getValue());

			ps.executeUpdate();

			/*TODO Maybe do a delete somewhere
			 *
			 *
			 *
			 */
			connection.commit();

			ps.close();

			//builds receipt string
			receipt = receipt + "Confirmation Number: " + ""//TODO
					+ "\nDate returned: " + java.time.LocalDateTime.now()
					+ "\nAt: " + "/TODO some kind of location????" //TODO
					+ "\nTotal Cost: " + model.getValue();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		return receipt;
	}
	/*
	clerkGenerateReport generates a set of tuples as laid out in the deliverable outline
	Put 1: Daily Rentals
		2: Daily Rentals for Branch
		3: Daily Returns
		4: Daily Returns for Branch
	 */
	public void clerkGenerateReport(int report, String branchLocation, String branchCity) throws SQLException {
		try {

			Statement stmt = connection.createStatement();
			String executeStr = "SELECT * FROM rentals, reservations, vehicles, returns WHERE ";
			switch (report) {
				case 1:
					executeStr = executeStr + "fromDate = " + java.time.LocalDateTime.now();
					break;
				case 2:
					executeStr = executeStr + "fromDate = " + java.time.LocalDateTime.now() + " AND location = " + branchLocation + " AND city = " + branchCity;
					break;
				case 3:
					executeStr = executeStr + "date = " + java.time.LocalDateTime.now();
					break;
				case 4:
					executeStr = executeStr + "date = " + java.time.LocalDateTime.now() + " AND location = " + branchLocation + " AND city = " + branchCity;
					break;

			}

			executeStr += "GROUP BY location,vtname";
			ResultSet rs = stmt.executeQuery(executeStr);
			//TODO do something with this rs?
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public BranchModel[] getBranchInfo() {
		ArrayList<BranchModel> result = new ArrayList<BranchModel>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM branch");
		
//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}
			
			while(rs.next()) {
				BranchModel model = new BranchModel(rs.getString("branch_addr"),
													rs.getString("branch_city"),
													rs.getInt("branch_id"),
													rs.getString("branch_name"),
													rs.getInt("branch_phone"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}	
		
		return result.toArray(new BranchModel[result.size()]);
	}
	
	public void updateBranch(int id, String name) {
		try {
		  PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
		  ps.setString(1, name);
		  ps.setInt(2, id);
		
		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
		  }
	
		  connection.commit();
		  
		  ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}	
	}
	
	
	// keep the following two functions

	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}
	
			connection = DriverManager.getConnection(ORACLE_URL, username, password);
			connection.setAutoCommit(false);
	
			System.out.println("\nConnected to Oracle!");


			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

	private void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
}
