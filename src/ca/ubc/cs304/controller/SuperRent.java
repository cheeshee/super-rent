package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.*;
import ca.ubc.cs304.delegates.*;

import ca.ubc.cs304.model.ModelForManipulation.ManipulateCustomersModel;
import ca.ubc.cs304.model.ModelForService.CustomerQueryModel;
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
    // function1
    // function2



    /**manipulation functions*/
    // for example:
    public void insertCustomer(String dlicense, String name, String cellphone, String address) {
        dbHandler.insertCustomer(dlicense, name, cellphone, address);
    }
    public ManipulateCustomersModel[] viewCustomer() {
        System.out.println("in super-rent viewCustomer");
        return dbHandler.viewCustomer();
    }

    // function1
    // function2

	public static void main(String args[]) {
		SuperRent SuperRent = new SuperRent();
		SuperRent.start();
	}
}
