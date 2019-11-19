package ca.ubc.cs304.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.VehiclesModel;

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
	 * the specifics which is a string array where indices 0, 1, 2, 4 
	 * correspond to car type, location, time interval fromDate, toDate respectively
	 */
	public VehiclesModel[] getAvailableVehicles(String[] specifics) {
		ArrayList<VehiclesModel> result = new ArrayList<>();

		try{
			Statement stmt = connection.createStatement();
			String executeString = "SELECT * FROM vehicles WHERE status = \"available\"";
			if (specifics[0] != null) {
				executeString += " AND vtname = " + specifics[0];
			}
			if (specifics[1] != null) {
				executeString += " AND location = " + specifics[1];
			}
			if (specifics[2] != null && specifics[3] != null) {
				executeString += " AND vlicense NOT IN (SELECT vlicense FROM reservations WHERE fromDate = " + specifics[2] + " AND toDate = " + specifics[3] + ")"; 
			}
			executeString = executeString + " GROUP BY ";
			if (specifics[0] != null) {
				executeString += "vtname";
			} else if (specifics[1] != null) {
				executeString += "location";
			} else {
				// return in order of availability otherwise
				executeString += "fromDate";
			}
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

		return result.toArray(new VehiclesModel[result.size()]);
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
