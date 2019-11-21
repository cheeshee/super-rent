package ca.ubc.cs304.handler;

import java.util.ArrayList;
import java.util.List;

import ca.ubc.cs304.model.*;

public class Manipulation {
    /**put all the querymodel here*/
    private InsertCustomerQueryModel insertCustomerQueryModel;


    /**for each query, frontend implement setter, backend implement getter*/
    public void setInsertCustomerQuery (String dlicense, String name, long cellphone, String address) {
        insertCustomerQueryModel = new InsertCustomerQueryModel();
        insertCustomerQueryModel.setDlicense(dlicense);
        insertCustomerQueryModel.setName(name);
        insertCustomerQueryModel.setCellphone(cellphone);
        insertCustomerQueryModel.setAddress(address);
    }
    public QueryModel getCustomer() {
        return this.insertCustomerQueryModel;
    }










    private String dlicense = null;
    private List result = null;


    public void setCustomer(String dlicense, String name) {
        this.dlicense = dlicense;
    }



    public void setViewAllCustomers(List customers){

    }

    public ArrayList viewAllCustomers(){
        return null;
    }
    // getAvailableVehicles(String vtname, String location, Date fromDate, Time fromTime, Date toDate, Time toTime) {
    // insertCustomer(CustomersModel model)
    // insertReservation(ReservationsModel model)
    // clerkRentVehicle(RentalsModel rentmodel, ReservationsModel reservemodel)
    // clerkReturnVehicle(ReturnsModel model)
    // clerkGenerateReport(int report, String branchLocation, String branchCity)





    public void setResult(List result) {
        this.result = result;
    }

    public List getResult() {
        return this.result;
    }























//
//
//
//    private static final String EXCEPTION_TAG = "[EXCEPTION]";
//    private static final String WARNING_TAG = "[WARNING]";
//    private static final int INVALID_INPUT = Integer.MIN_VALUE;
//    private static final int EMPTY_INPUT = 0;
//
//    private BufferedReader bufferedReader = null;
//    private ManipulationDelegate delegate = null;
//
//    public Manipulation() {
//    }
//
//    /**
//     * Displays simple text interface
//     */
//    public void showMainMenu(ManipulationDelegate delegate) {
//        this.delegate = delegate;
//
//        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        int choice = INVALID_INPUT;
//
//        while (choice != 5) {
//            System.out.println();
//            System.out.println("1. Insert branch");
//            System.out.println("2. Delete branch");
//            System.out.println("3. Update branch name");
//            System.out.println("4. Show branch");
//            System.out.println("5. Quit");
//            System.out.println("6. TEST insertCustomer");
//            System.out.print("Please choose one of the above 5 options: ");
//
//            choice = readInteger(false);
//
//            System.out.println(" ");
//
//            if (choice != INVALID_INPUT) {
//                switch (choice) {
//                    case 1:
//                        handleInsertOption();
//                        break;
//                    case 2:
//                        handleDeleteOption();
//                        break;
//                    case 3:
//                        handleUpdateOption();
//                        break;
//                    case 4:
//                        delegate.showBranch();
//                        break;
//                    case 5:
//                        handleQuitOption();
//                        break;
//                    case 6:
//                        handleInsertCustomer();
//                        break;
//                    default:
//                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
//                        break;
//                }
//            }
//        }
//    }
//
//    private void handleDeleteOption() {
//        int branchId = INVALID_INPUT;
//        while (branchId == INVALID_INPUT) {
//            System.out.print("Please enter the branch ID you wish to delete: ");
//            branchId = readInteger(false);
//            if (branchId != INVALID_INPUT) {
//                delegate.deleteBranch(branchId);
//            }
//        }
//    }
//
//    // HANDLER FOR insertCustomer
//
//    private void handleInsertCustomer() {
//        String name = null;
//        while (name == null || name.length() <= 0) {
//            System.out.print("Please enter your name: ");
//            name = readLine().trim();
//        }
//
//        int dlicense = INVALID_INPUT;
//        while (dlicense == INVALID_INPUT) {
//            System.out.print("Please enter your driver's license: ");
//            dlicense = readInteger(false);
//        }
//
//        long cellphone = INVALID_INPUT;
//        System.out.print("Please enter your cellphone number: ");
//        cellphone = Long.parseLong(readLine());
//        if (cellphone == INVALID_INPUT) {
//            cellphone = -1;
//        }
//
//        System.out.print("Please enter your address: ");
//        String address = readLine().trim();
//        if (address.length() == 0) {
//            address = null;
//        }
//
//        CustomersModel model = new CustomersModel(dlicense, name, cellphone, address);
//        delegate.insertCustomer(model);
//    }
//
//
//    private void handleInsertOption() {
//        int id = INVALID_INPUT;
//        while (id == INVALID_INPUT) {
//            System.out.print("Please enter the branch ID you wish to insert: ");
//            id = readInteger(false);
//        }
//
//        String name = null;
//        while (name == null || name.length() <= 0) {
//            System.out.print("Please enter the branch name you wish to insert: ");
//            name = readLine().trim();
//        }
//
//        // branch address is allowed to be null so we don't need to repeatedly ask for the address
//        System.out.print("Please enter the branch address you wish to insert: ");
//        String address = readLine().trim();
//        if (address.length() == 0) {
//            address = null;
//        }
//
//        String city = null;
//        while (city == null || city.length() <= 0) {
//            System.out.print("Please enter the branch city you wish to insert: ");
//            city = readLine().trim();
//        }
//
//        int phoneNumber = INVALID_INPUT;
//        while (phoneNumber == INVALID_INPUT) {
//            System.out.print("Please enter the branch phone number you wish to insert: ");
//            phoneNumber = readInteger(true);
//        }
//
//        BranchModel model = new BranchModel(address,
//                city,
//                id,
//                name,
//                phoneNumber);
//        delegate.insertBranch(model);
//    }
//
//    private void handleQuitOption() {
//        System.out.println("Good Bye!");
//
//        if (bufferedReader != null) {
//            try {
//                bufferedReader.close();
//            } catch (IOException e) {
//                System.out.println("IOException!");
//            }
//        }
//
//        delegate.terminalTransactionsFinished();
//    }
//
//    private void handleUpdateOption() {
//        int id = INVALID_INPUT;
//        while (id == INVALID_INPUT) {
//            System.out.print("Please enter the branch ID you wish to update: ");
//            id = readInteger(false);
//        }
//
//        String name = null;
//        while (name == null || name.length() <= 0) {
//            System.out.print("Please enter the branch name you wish to update: ");
//            name = readLine().trim();
//        }
//
//        delegate.updateBranch(id, name);
//    }
//
//    private int readInteger(boolean allowEmpty) {
//        String line = null;
//        int input = INVALID_INPUT;
//        try {
//            line = bufferedReader.readLine();
//            input = Integer.parseInt(line);
//        } catch (IOException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        } catch (NumberFormatException e) {
//            if (allowEmpty && line.length() == 0) {
//                input = EMPTY_INPUT;
//            } else {
//                System.out.println(WARNING_TAG + " Your input was not an integer");
//            }
//        }
//        return input;
//    }
//
//    private String readLine() {
//        String result = null;
//        try {
//            result = bufferedReader.readLine();
//        } catch (IOException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//        return result;
//    }
}
