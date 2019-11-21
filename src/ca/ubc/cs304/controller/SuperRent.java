package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.*;
import ca.ubc.cs304.delegates.*;
import ca.ubc.cs304.handler.*;
import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.*;


/**
 * This is the main controller class that will orchestrate everything.
 */
public class SuperRent implements LoginWindowDelegate{
	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	// middle part in our diagram
	private Manipulation manipulation = new Manipulation();
	private Service service = new Service();


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
            new MainUI();
		} else {
			loginWindow.handleLoginFailed();
			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}

	public static void main(String args[]) {
		SuperRent SuperRent = new SuperRent();
		SuperRent.start();
	}
}
