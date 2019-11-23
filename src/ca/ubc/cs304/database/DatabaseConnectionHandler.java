package ca.ubc.cs304.database;
import java.sql.*;
import java.util.*;
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

    /**service functions*/
    // query 1
    /**
     * getAvailable Vehicles returns an array of VehiclesModel
     * takes car type, location, time interval fromDate, fromTime, toDate, toTime
     */
    public CustomerGetAvailableVehicleModel[] customerGetAvailableVehicles(String vtname, String location, String fromDate, String toDate) {
        ArrayList<CustomerGetAvailableVehicleModel> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            String executeString = "SELECT * FROM vehicles WHERE status = \'available\'";
            if (vtname != null) {
                executeString += " AND vtname = \'" + vtname + "\'";
            }
            if (location != null) {
                executeString += " AND location = \'" + location + "\'";
            }
            if (fromDate != null && toDate != null) {
                executeString += " AND vlicense NOT IN (SELECT vlicense FROM reservations WHERE (fromDate > TO_DATE(\'" + fromDate +
                        "\',\'DD/MM/YYYY\') AND fromDate < TO_DATE(\'" + toDate +
                        "\',\'DD/MM/YYYY\')) OR (toDate > TO_DATE(\'" + fromDate +
                        "AND toDate < TO_DATE(\'" + toDate +
                        "\',\'DD/MM/YYYY\'))";
            }

            // order by location
            executeString += " GROUP BY location";
            ResultSet rs = stmt.executeQuery(executeString);

            while (rs.next()) {
                CustomerGetAvailableVehicleModel model = new CustomerGetAvailableVehicleModel();
                model.setVlicense(rs.getString("vehicles_vlicense"));
                model.setMake(rs.getString("vehicles_make"));
                model.setModel(rs.getString("vehicles_model"));
                model.setYear(rs.getInt("vehicles_year"));
                model.setColor(rs.getString("vehicles_color"));
                model.setVtname(rs.getString("vehicles_vtname"));
                model.setLocation(rs.getString("vehicles_location"));
                result.add(model);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        // result.size() would just be the number of available cars, use that in handler
        return result.toArray(new CustomerGetAvailableVehicleModel[result.size()]);
    }

    // query 2
    public boolean checkCustomerExist(int dlicense) {
        boolean result = false;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE dlicense = " + dlicense);
            result = rs.next();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return result;
    }

    /**
     * 
     * @param vtname
     * @param dlicense
     * @param fromDate
     * @param toDate
     * @return confNo or throws an exception if there's an error
     */
    public int customerMakeReservation(String vtname, int dlicense, String fromDate, String toDate){
        int confNo = 0;
        try {

            // see if there's an available vehicle
            CustomerGetAvailableVehicleModel[] available;
            String vlicense;
            available = customerGetAvailableVehicles(vtname, null, fromDate, toDate);
            if (available.length == 0) {
                throw new SQLException();
            } else {
                vlicense = available[0].getVlicense();
            }
            // figure out confNo?
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM reservations");
            confNo = rs.getInt(1) + 1;    // should return just an int
            stmt.close();
            rs.close();

            PreparedStatement ps = connection.prepareStatement("INSERT INTO reservations VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, confNo);
            ps.setString(2, vtname);
            ps.setString(3, vlicense);
            ps.setInt(4, dlicense);
            ps.setString(5, "TO_DATE (\'" + fromDate + "\',\'DD/MM/YYYY\')");
            ps.setString(7, "TO_DATE (\'" + toDate + "\',\'DD/MM/YYYY\')");

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return confNo;
    }


    // query 3
    // Fred implemented below FIX IT
    /**clerkReturnVehicle returns a receipt with information for the customer
     first checks to see if the rid exists in Returns
     throws a SQL exception if the car has not been taken out*/

    // query 4
    // Fred implemented below FIX IT
    // you can call other function
    // you need to set vehicle status to "rented"
    // check for null value inputs
    /**
     * clerkRentVehicle returns a receipt with a lot of info for the
     * rental that was made
     * returns a message if there are no cars currently available
     */


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
    public ClerkGenerateReportForBranchRentalModel[] clerkGenerateReportForBranchRental(String location, String city) {
        return null;
    }

    // query 7
    public ClerkGenerateReportForReturnModel[] clerkGenerateReportForReturn() {
        return null;
    }

    // query 8
    public ClerkGenerateReportForBranchReturnModel[] clerkGenerateReportForBranchReturn(String location, String city) {
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
            PreparedStatement ps = connection.prepareStatement("INSERT INTO customers VALUES (?,?,?,?)");
            ps.setInt(1, dlicense);
            ps.setString(2, name);
            if (cellphone == null) {
                ps.setNull(3, java.sql.Types.VARCHAR);
            } else {
                ps.setObject(3, cellphone);
            }
            if (address == null) {
                ps.setNull(4, java.sql.Types.VARCHAR);
            } else {
                ps.setString(4, address);
            }

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }
    public void deleteCustomer(int dlicense) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM customers WHERE DLICENSE = ?");
            ps.setInt(1, dlicense);

            System.out.println("inside delete customer");
            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }
    public ManipulateCustomersModel[] viewCustomer() {
        ArrayList<ManipulateCustomersModel> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while (rs.next()) {
                System.out.println("in dbhandler viewCustomer");
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
    }


    /******************************PARTNERS*******************************/

    // TRY TO MAKE A QUERY
    // View the number of available vehicles for a specific car type, location, and time interval.
    // The user should be able to provide any subset of {car type, location, time interval} to
    // view the available vehicles. If the user provides no information, your application should
    // automatically return a list of all vehicles (at that branch) sorted in some reasonable way
    // for the user to peruse.


//    public VehiclesModel[] getAvailableVehicles(String vtname, String location, Date fromDate, Time fromTime, Date toDate, Time toTime) {
//        ArrayList<VehiclesModel> result = new ArrayList<>();
//
//        try {
//            Statement stmt = connection.createStatement();
//            String executeString = "SELECT * FROM vehicles WHERE status = \"available\"";
//            if (vtname != null) {
//                executeString += " AND vtname = " + vtname;
//            }
//            if (location != null) {
//                executeString += " AND location = " + location;
//            }
//            if (fromDate != null && fromTime != null && toDate != null && toTime != null) {
//                executeString += " AND vlicense NOT IN (SELECT vlicense FROM reservations WHERE (fromDate > " + fromDate +
//                        "AND fromDate < " + toDate +
//                        ") OR (toDate > " + fromDate +
//                        "AND toDate < " + toDate +
//                        " ) OR (fromDate = " + fromDate +
//                        "AND fromTime >= " + fromTime +
//                        " ) OR (fromDate = " + toDate +
//                        "AND fromTime <= " + toTime +
//                        " ) OR (toDate = " + fromDate +
//                        "AND toTime >= " + fromTime +
//                        " ) OR (toDate = " + toDate +
//                        "AND toTime <= " + toTime +
//                        "))";
//            }
//
//            // order by location
//            executeString += " GROUP BY location";
//            ResultSet rs = stmt.executeQuery(executeString);
//
//            while (rs.next()) {
//                VehiclesModel model = new VehiclesModel();
//                model.setVlicense(rs.getString("vehicles_vlicense"));
//                model.setMake(rs.getString("vehicles_make"));
//                model.setModel(rs.getString("vehicles_model"));
//                model.setYear(rs.getInt("vehicles_year"));
//                model.setColor(rs.getString("vehicles_color"));
//                model.setOdometer(rs.getInt("vehicles_odometer"));
//                model.setStatus(rs.getString("vehicles_status"));
//                model.setVtname(rs.getString("vehicles_vtname"));
//                model.setLocation(rs.getString("vehicles_location"));
//                model.setCity(rs.getString("vehicles_city"));
//                result.add(model);
//            }
//            rs.close();
//            stmt.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//            rollbackConnection();
//        }
//
//        // result.size() would just be the number of available cars, use that in handler
//        return result.toArray(new VehiclesModel[result.size()]);
//    }

    // If a customer is new, add the customer’s details to the database.


//    public void insertCustomer(ManipulateCustomersModel model) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO customers VALUES (?,?,?,?)");
//            ps.setInt(1, Integer.parseInt(model.getDlicense()));
//            ps.setString(2, model.getName());
//            if (Integer.parseInt(model.getCellphone()) <= 0) {
//                ps.setNull(3, java.sql.Types.NUMERIC);
//            } else {
//                ps.setObject(3, model.getCellphone());
//            }
//            if (model.getAddress() == null) {
//                ps.setNull(4, java.sql.Types.VARCHAR);
//            } else {
//                ps.setString(4, model.getAddress());
//            }
//
//            ps.executeUpdate();
//            connection.commit();
//
//            ps.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//            rollbackConnection();
//        }
//    }

    /**
     * clerkRentVehicle returns a receipt with a lot of info for the
     * rental that was made
     * returns a message if there are no cars currently available
     */
    // TODO: for Fred: handle the result and convert all the results to be string[], order: confNo, fromDate, toDate, vtname, location, rentalPeriod
    // you can call other function
    // you need to set vehicle status to "rented"
    // check for null value inputs
    public String[] clerkRentVehicle(int confNo, String vtname, int dlicense, String fromDate, String toDate, String cardName, String cardNo, String expDate){
        String[] receipt = new String[10];

        try{//check if confNo exists
            if (confNo >= 1) {

                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM reservartions WHERE confNo = " + confNo); //QUERY FOR THE RESERVATION
                if (rs.next() == false){
                    throw new SQLException();
                }
                String lp = rs.getString("vlicense");
                ResultSet rs0 = stmt.executeQuery("SELECT * FROM vehicles WHERE vlicense = \' lp \' ");
                if (rs.next() == false){
                    throw new SQLException();
                }

                receipt = clerkRentVehicleInsertQuery(rs.getString("license"), rs.getInt("dlicense"), rs.getString("fromDate"), rs.getString("toDate"), rs0.getInt("odometer"), cardName, cardNo,expDate,confNo, rs0.getString("location"), rs0.getString("city"), rs.getString("vtname"));
                stmt.close();
            } else {

                //Make a new reservation if there was no reservation
                int confirmation = customerMakeReservation(vtname, dlicense, fromDate, toDate);    //Do a reservation first to check all the things such as carmodel etc...

                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM reservartions WHERE confNo = " + confNo); //QUERY FOR THE RESERVATION
                if (!rs.next()){
                    throw new SQLException();
                }
                String lp = rs.getString("vlicense");
                ResultSet rs0 = stmt.executeQuery("SELECT * FROM vehicles WHERE vlicense = \' lp \' ");
                if (!rs.next()){
                    throw new SQLException();
                }

                receipt = clerkRentVehicleInsertQuery(rs.getString("license"), dlicense, fromDate, toDate, rs0.getInt("odometer"), cardName, cardNo,expDate,confirmation, rs0.getString("location"), rs0.getString("city"), vtname);
                stmt.close();

            }

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return receipt;

    }

    private String[] clerkRentVehicleInsertQuery(String vlicense, int dlicense, String fromDate, String toDate, int odometer,
                                             String cardName, String cardNo, String expDate, int confNo, String location, String city, String vtname ){

        String[] receipt = new String[10];

        try {
            ///////////MAKE QUERY FOR CONF NO///////////////////////////////
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) from rentals");
            int rid = rs.getInt(1) + 1;
            stmt.close();
            ////////////////////////////////////////////////////////////////

            /////////////INSERT QUERY////////////////////////////////////////////////////
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rentals VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            ps.setInt(1, rid);
            ps.setString(2, vlicense);
            ps.setInt(3, dlicense);
            ps.setString(4, fromDate);
            ps.setString(5, toDate);
            ps.setInt(6, odometer);
            ps.setString(7, cardName);
            ps.setString(8, cardNo);
            ps.setString(9, expDate);
            ps.setInt(10, confNo);

            ps.executeUpdate();
            connection.commit();

            ps.close();
            ///////////////////////////////////////////////////////////////////////////////

            //build receipt
            receipt[0] = Integer.toString(confNo);
            receipt[1] = fromDate;
            receipt[2] = vtname;
            receipt[3] = location;
            receipt[4] = city;

        } catch (SQLException e){
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return receipt;
    }

    /**
     clerkReturnVehicle returns a receipt with information for the customer
     first checks to see if the rid exists in Returns
     throws a SQL exception if the car has not been taken out
     */
    // TODO: for Fred: handle the result and convert all the results to be string[], order: rid, confNo, Date
    public String[] clerkReturnVehicle(int rid, int odometer, int fulltank) {
        String[] receipt = new String[10];
//        try{
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM rentals WHERE rid = " + rid);
//            if (rs == null) { //throw new SQLException(); }
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO rentals VALUES (?,?,?,?,?)");
//
//            //VERY NOT SURE WHICH ONES ARE AVAIBLE ATM ESPECIALLY ODOMETER
//            ps.setInt(1,rid);
//            ps.setDate(2, model.getDate());
//            ps.setInt(4, model.getOdometer());
//            ps.setInt(5, model.getFulltank());
//            ps.setFloat(6, model.getValue());
//
//            ps.executeUpdate();
//
//            /*TODO Maybe do a delete somewhere
//             *
//             *
//             *
//             */
//            connection.commit();
//
//            ps.close();
//
//            //builds receipt string
//            receipt = receipt + "Confirmation Number: " + ""//TODO
//                    + "\nDate returned: " + java.time.LocalDateTime.now()
//                    + "\nAt: " + "/TODO some kind of location????" //TODO
//                    + "\nTotal Cost: " + model.getValue();
//        } catch (Exception e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//            rollbackConnection();
//        }
        return receipt;
    }


    // public String[] clerkRentVehicle(int confNo, String vtname, String vlicense, int dlicense,
    //                                 String fromDate, String toDate, int rid, int odometer,
    //                                 String cardName, String cardNo, String expDate) {
    //    return null;
    // }


    /******************belows are demo examples, including "delete", "insert","getBranchInfo","update" query***************/
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
