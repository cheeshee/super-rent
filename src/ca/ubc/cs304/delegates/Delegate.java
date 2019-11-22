package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.ModelForManipulation.ManipulateCustomersModel;
import ca.ubc.cs304.model.ModelForService.*;

// TODO: backend step 1: add function signature here
public interface Delegate {

    /**
     * manipulation function
     */
    void insertCustomer(int dlicense, String name, String cellphone, String address);

    ManipulateCustomersModel[] viewCustomer();


    /**
     * service function
     */
    // query 1
    CustomerGetAvailableVehicleModel[] customerGetAvailableVehicles(String vtname, String location, String fromDate, String toDate);

    // query 2
    boolean checkCustomerExist(int dlicense);

    int customerMakeReservation(String vtname, int dlicense, String fromDate, String toDate);

    // query 3
    String[] clerkRentVehicle(int confNo, String vtname, String vlicense, int dlicense, String fromDate,
                              String toDate, String cardName, String cardNo, String expDate);


    // query 4
    String[] clerkReturnVehicle(int rid, int odometer, int fulltank);

    // query 5
    ClerkGenerateReportForRentalModel[] clerkGenerateReportForRental();

    // query 6
    ClerkGenerateReportForBranchRentalModel[] clerkGenerateReportForBranchRental();

    // query 7
    ClerkGenerateReportForReturnModel[] clerkGenerateReportForReturn();

    // query 8
    ClerkGenerateReportForBranchReturnModel[] clerkGenerateReportForBranchReturn();

}
