package ca.ubc.cs304.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import ca.ubc.cs304.model.ModelForManipulation.*;
import ca.ubc.cs304.model.ModelForService.*;

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

    /**
     * logistics functions
     */
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
        try {
            connection.rollback();
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


    // TODO: backend step 3: do the actual query here for

    /**
     * service functions
     */
    // query 1
    public CustomerGetAvailableVehicleModel[] customerGetAvailableVehicles(String vtname, String location, String fromDate, String toDate) {
        ArrayList<CustomerGetAvailableVehicleModel> result = new ArrayList<>();

        return result.toArray(new CustomerGetAvailableVehicleModel[result.size()]);
    }

    // query 2
    public boolean checkCustomerExist(int dlicense) {
        boolean result = false;


        return result;
    }
    public int customerMakeReservation(String vtname, int dlicense, String fromDate, String toDate) {
        return 0;
    }


    // query 3
    // Fred implemented below FIX IT

    // query 4
    // Fred implemented below FIX IT


    /**
     clerkGenerateReport generates a set of tuples as laid out in the deliverable outline
     Put 1: Daily Rentals
     2: Daily Rentals for Branch
     3: Daily Returns
     4: Daily Returns for Branch
     */

    // query 5
    public ClerkGenerateReportForRentalModel[] clerkGenerateReportForRental() {
        return null;
    }

    // query 6
    public ClerkGenerateReportForBranchRentalModel[] clerkGenerateReportForBranchRental() {
        return null;
    }

    // query 7
    public ClerkGenerateReportForReturnModel[] clerkGenerateReportForReturn() {
        return null;
    }

    // query 8
    public ClerkGenerateReportForBranchReturnModel[] clerkGenerateReportForBranchReturn() {
        return null;
    }
    /**
     public ClerkGenerateReportForRentalModel[] clerkGenerateReportModel(int report, String branchLocation, String branchCity) {
     ArrayList<ClerkGenerateReportForRentalModel> result = new ArrayList<>();
     try {
     Statement stmt = connection.createStatement();
     ResultSet rs = stmt.executeQuery(
     "SELECT V1.VTNAME, V1.LOCATION, V1.VLICENSE FROM VEHICLES V1\n" +
     "    WHERE (V1.STATUS = 'available'\n" +
     "                    AND V1.VTNAME = 'Mazida'\n" +
     "                    AND V1.LOCATION = 'vancouver'\n" +
     "                    AND V1.VLICENSE NOT IN (\n" +
     "                    SELECT R1.VLICENSE\n" +
     "                    FROM RESERVATION R1\n" +
     "                    WHERE (20190321 <= R1.FROMDATE AND 20191021 > R1.TODATE)\n" +
     "    )\n" +
     "            );");
     while (rs.next()) {
     ClerkGenerateReportForRentalModel model = new ClerkGenerateReportForRentalModel();
     //                 model.setDlicense(rs.getString("dlicense"));
     //                 model.setName(rs.getString("name"));
     //                 model.setCellphone(rs.getString("cellphone"));
     //                 model.setAddress(rs.getString("address"));
     result.add(model);
     }

     rs.close();
     stmt.close();
     } catch (SQLException e) {
     System.out.println(EXCEPTION_TAG + " " + e.getMessage());
     }

     return result.toArray(new ClerkGenerateReportForRentalModel[result.size()]);

     }
     */



    /**
     * manipulation functions
     */
    public void insertCustomer(int dlicense, String name, String cellphone, String address) {
        try {
            System.out.println("before insert");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ORA_BOLIN1.CUSTOMER VALUES (?,?,?,?)");
            System.out.println("after insert");
            ps.setInt(1, dlicense);
            ps.setString(2, name);

            if (cellphone == null) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setLong(3, Long.parseLong(cellphone));
            }

            if (address == null) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setString(4, address);
            }

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (Exception e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public ManipulateCustomersModel[] viewCustomer() {
        System.out.println("in dbhandler viewCustomer");

        ArrayList<ManipulateCustomersModel> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORA_BOLIN1.CUSTOMERS");
            while (rs.next()) {
                ManipulateCustomersModel model = new ManipulateCustomersModel();
                model.setDlicense(rs.getInt("dlicense"));
                model.setName(rs.getString("name"));
                model.setCellphone(rs.getString("cellphone"));
                model.setAddress(rs.getString("address"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new ManipulateCustomersModel[result.size()]);
//          get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}
    }


    /******************************PARTNERS*******************************/

    // TRY TO MAKE A QUERY
    // View the number of available vehicles for a specific car type, location, and time interval.
    // The user should be able to provide any subset of {car type, location, time interval} to
    // view the available vehicles. If the user provides no information, your application should
    // automatically return a list of all vehicles (at that branch) sorted in some reasonable way
    // for the user to peruse.

    /**
     * getAvailable Vehicles returns an array of VehiclesModel
     * takes car type, location, time interval fromDate, fromTime, toDate, toTime
     */
    public VehiclesModel[] getAvailableVehicles(String vtname, String location, Date fromDate, Time fromTime, Date toDate, Time toTime) {
        ArrayList<VehiclesModel> result = new ArrayList<>();

        try {
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

            while (rs.next()) {
                VehiclesModel model = new VehiclesModel();
                model.setVlicense(rs.getString("vehicles_vlicense"));
                model.setMake(rs.getString("vehicles_make"));
                model.setModel(rs.getString("vehicles_model"));
                model.setYear(rs.getInt("vehicles_year"));
                model.setColor(rs.getString("vehicles_color"));
                model.setOdometer(rs.getInt("vehicles_odometer"));
                model.setStatus(rs.getString("vehicles_status"));
                model.setVtname(rs.getString("vehicles_vtname"));
                model.setLocation(rs.getString("vehicles_location"));
                model.setCity(rs.getString("vehicles_city"));
                result.add(model);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        // result.size() would just be the number of available cars, use that in handler
        return result.toArray(new VehiclesModel[result.size()]);
    }

    // If a customer is new, add the customer’s details to the database.

    /**
     * insertCustomer adds a new customer to the customers table in the database
     * takes a customersModel
     */
    public void insertCustomer(ManipulateCustomersModel model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO customers VALUES (?,?,?,?)");
            ps.setInt(1, Integer.parseInt(model.getDlicense()));
            ps.setString(2, model.getName());
            if (Integer.parseInt(model.getCellphone()) <= 0) {
                ps.setNull(3, java.sql.Types.NUMERIC);
            } else {
                ps.setObject(3, model.getCellphone());
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

    /**
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
            confNo = rs.getInt(1) + 1;    // should return just an int

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

    /**
     * clerkRentVehicle returns a receipt with a lot of info for the
     * rental that was made
     * returns a message if there are no cars currently available
     */
    // TODO: for Fred: handle the result and convert all the results to be string[], order: confNo, fromDate, toDate, vtname, location, rentalPeriod
    // you can call other function
    // you need to set vehicle status to "rented"
    // check for null value inputs
    public String[] clerkRentVehicle(int confNo, String vtname, String vlicense, int dlicense,
                                     String fromDate, String toDate, int rid, int odometer,
                                     String cardName, String cardNo, String expDate) {
        return null;
    }

    /**
     clerkReturnVehicle returns a receipt with information for the customer
     first checks to see if the rid exists in Returns
     throws a SQL exception if the car has not been taken out
     */
    // TODO: for Fred: handle the result and convert all the results to be string[], order: rid, confNo, Date
    public String[] clerkReturnVehicle(int rid, int odometer, int fulltank) {
	    return null;
    }



/**belows are demo examples, including "delete", "insert","getBranchInfo","update" query*/
//	public void deleteBranch(int branchId) {
//		try {
//			PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
//			ps.setInt(1, branchId);
//
//			int rowCount = ps.executeUpdate();
//			if (rowCount == 0) {
//				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
//			}
//
//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}

    //	public void insertBranch() {
//		try {
//			PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?,?,?,?)");
//			ps.setInt(1, model.getId());
//			ps.setString(2, model.getName());
//			ps.setString(3, model.getAddress());
//			ps.setString(4, model.getCity());
//			if (model.getPhoneNumber() == 0) {
//				ps.setNull(5, java.sql.Types.INTEGER);
//			} else {
//				ps.setInt(5, model.getPhoneNumber());
//			}
//
//			ps.executeUpdate();
//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}
//	public DemoBranchModel[] getBranchInfo() {
//		ArrayList<DemoBranchModel> result = new ArrayList<DemoBranchModel>();
//
//		try {
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM branch");
//
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
//
//			while(rs.next()) {
//				DemoBranchModel model = new DemoBranchModel();
//				model.setAddress(rs.getString("branch_addr"));
//				model.setCity(rs.getString("branch_city"));
//				model.setId(rs.getInt("branch_id"));
//				model.setName(rs.getString("branch_name"));
//				model.setPhoneNumber(rs.getInt("branch_phone"));
//				result.add(model);
//			}
//
//			rs.close();
//			stmt.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new DemoBranchModel[result.size()]);
//	}
//
//	public void updateBranch(int id, String name) {
//		try {
//		  PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
//		  ps.setString(1, name);
//		  ps.setInt(2, id);
//
//		  int rowCount = ps.executeUpdate();
//		  if (rowCount == 0) {
//		      System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
//		  }
//
//		  connection.commit();
//
//		  ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}
}
