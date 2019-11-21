package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.ModelForManipulation.ManipulateCustomersModel;

public interface Delegate {
    void insertCustomer (String dlicense, String name, String cellphone, String address);
    ManipulateCustomersModel[] viewCustomer();

    // TODO: backend step 1: add function signature here

    // for example:
    // ClerkGenerateReportModel[] clerkGenerateReportModel(int report, String branchLocation, String branchCity);
}
