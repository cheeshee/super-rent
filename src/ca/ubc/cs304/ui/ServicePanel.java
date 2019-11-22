package ca.ubc.cs304.ui;

import javax.swing.*;

public class ServicePanel {
    // main panel
    private JPanel servicePanel;
    private JPanel customerPanel;
    private JPanel clerkPanel;
    private JTabbedPane tabbedPane1 ;
    private JPanel rentTab;
    private JPanel returnTab;
    private JPanel reportTab;

    // clerk - rent
    private JButton APPLYButton;
    private JButton CLEARButton;
    private JButton BACKButton;
    private JLabel rentHint ;
    private JLabel confHint ;
    private JPanel btnPanel ;
    private JPanel rentInputPanel;
    private JPanel receiptPanel ;
    private JTextField confNoTextField ;
    private JLabel confNoHint ;
    private JLabel RENTALHINT ;
    private JPanel backUpPanel ;
    private JPanel rentLeftPanel ;
    private JPanel rentRightPanel;
    private JLabel receiptConfNo;
    private JLabel receiptConfNoContent;
    private JLabel receiptFromDate;
    private JLabel receiptFromDateCont;
    private JLabel receiptToDate;
    private JLabel receiptToDateCont;
    private JLabel receiptvtname;
    private JLabel receiptvtnameCont;
    private JLabel receiptLocation;
    private JLabel receiptLocationCont;
    private JLabel returnPeriod;
    private JLabel returnPeriodCont;
    private JLabel receiptHint;

    // clerk - return

    private int choiceForClerkReport;

    public JPanel getServicePanel() {
        return this.servicePanel;
    }

    public ServicePanel() {
        initializeCustomerPane();
        servicePanel.add(customerPanel);

        initializeClerkPane();
        servicePanel.add(clerkPanel);
    }

    /**customer panel*/
    private void initializeCustomerPane() {

    }


    /**clerk panel*/
    private void initializeClerkPane() {
        initializeClerkRent();
        initializeClerkReturn();
        initializeReport(choiceForClerkReport);
    }

    private void initializeClerkRent(){ }
    private void initializeClerkReturn(){ }

    private void initializeReport(int choice) {
        switch(choice) {
            case 1: initializeClerkReportForBranchRental(); break;
            case 2: initializeClerkReportForBranchReturn(); break;
            case 3: initializeClerkReportForRental(); break;
            case 4: initializeClerkReportForReturn(); break;
            default:  break;
        }
    }

    private void initializeClerkReportForBranchRental() { }
    private void initializeClerkReportForBranchReturn() { }
    private void initializeClerkReportForRental() {}
    private void initializeClerkReportForReturn() {}


}
