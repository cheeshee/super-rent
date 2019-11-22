package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.*;
import ca.ubc.cs304.delegates.*;

import ca.ubc.cs304.model.ModelForManipulation.ManipulateCustomersModel;
import ca.ubc.cs304.model.ModelForService.*;
import ca.ubc.cs304.ui.*;


/**
 * This is the main controller class that will orchestrate everything.
 */
public class SuperRent implements LoginWindowDelegate, Delegate {
    private DatabaseConnectionHandler dbHandler = null;
    private LoginWindow loginWindow = null;
    private MainUI mainUI = null;

    public SuperRent() {
        dbHandler = new DatabaseConnectionHandler();
    }
    private void start() {
        loginWindow = new LoginWindow();
        loginWindow.showFrame(this);
    }
    public void login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            loginWindow.dispose();

            /**Step1: show the UI window*/
            mainUI = new MainUI();
            mainUI.showMainUI(this);
        } else {
            loginWindow.handleLoginFailed();
            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
    }

    // TODO: backend step 2: declare function here which you add in Delegate interface
    /**services functions*/

    // query 1
    public CustomerGetAvailableVehicleModel[] customerGetAvailableVehicles(String vtname, String location, String fromDate, String toDate) {
        return dbHandler.customerGetAvailableVehicles(vtname, location,fromDate,toDate);
    }

    // query 2
    public boolean checkCustomerExist (int dlicense) {
        return dbHandler.checkCustomerExist(dlicense);
    }
        // when customer exists
    public int customerMakeReservation(String vtname, int dlicense, String fromDate, String toDate){
        return dbHandler.customerMakeReservation(vtname, dlicense, fromDate, toDate);
    }
        // when customer doesn't exist, call insertCustomer

    // query 3
    public String[] clerkRentVehicle(int confNo, String vtname, String vlicense, int dlicense, String fromDate,
                                     String toDate, String cardName, String cardNo, String expDate) {
        return dbHandler.clerkRentVehicle(confNo,vtname,vlicense,dlicense, fromDate,toDate,cardName,cardNo, expDate);
    }

    // query 4
    public String[] clerkReturnVehicle(int rid, int odometer, int fulltank) {
        return dbHandler.clerkReturnVehicle(rid, odometer, fulltank);
    }

    // query 5
    public ClerkGenerateReportForRentalModel[] clerkGenerateReportForRental(){
        return dbHandler.clerkGenerateReportForRental();
    }

    // query 6
    public ClerkGenerateReportForBranchRentalModel[] clerkGenerateReportForBranchRental(){
        return dbHandler.clerkGenerateReportForBranchRental();
    }

    // query 7
    public ClerkGenerateReportForReturnModel[] clerkGenerateReportForReturn() {
        return dbHandler.clerkGenerateReportForReturn();
    }

    // query 8
    public ClerkGenerateReportForBranchReturnModel[] clerkGenerateReportForBranchReturn(){
        return dbHandler.clerkGenerateReportForBranchReturn();
    };


    /**manipulation functions*/
    // for example:
    public void insertCustomer(int dlicense, String name, String cellphone, String address) {
        dbHandler.insertCustomer(dlicense, name, cellphone, address);
    }
    public ManipulateCustomersModel[] viewCustomer() {
        System.out.println("in super-rent viewCustomer");
        return dbHandler.viewCustomer();
    }


	public static void main(String args[]) {
		SuperRent SuperRent = new SuperRent();
		SuperRent.start();
	}
}
