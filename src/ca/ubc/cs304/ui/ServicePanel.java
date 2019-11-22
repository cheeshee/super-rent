package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.Delegate;

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
    private Delegate delegate;

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
    private int choiceForClerkReport;


    // clerk - return



    public JPanel getServicePanel() {
        return this.servicePanel;
    }

    public ServicePanel(Delegate delegate) {
        this.delegate = delegate;

        initializeCustomerPane();
        initializeClerkPane();

        servicePanel.add("customerPane",customerPanel);
        servicePanel.add("clerkPane",clerkPanel);
    }

    /**customer panel*/
    private void initializeCustomerPane() {

    }


    /**clerk panel*/
    private void initializeClerkPane() {
        initializeClerkRent();
        initializeClerkReturn();
        initializeReport(choiceForClerkReport);
        tabbedPane1.add(rentTab);
        tabbedPane1.add(returnTab);
        tabbedPane1.add(reportTab);
        clerkPanel.add(tabbedPane1);
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
