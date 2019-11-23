package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.Delegate;
import ca.ubc.cs304.model.ModelForService.CustomerGetAvailableVehicleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicePanel {
    private JPanel servicePanel = new JPanel(new GridLayout(1,2));

    public Delegate delegate;


    // customer button panel
    private JPanel customerBtnPanel;
    private JButton previewBtn;
    private JButton reserveBtn;
    private JButton backBtn;

    // preview service
    private String previewVtName;
    private String previewLocation;
    private String previewFromDate;
    private String previewToDate;

    // reserve service
    private String reserveVtName;
    private String reserveDlicense;
    private String reserveFromDate;
    private String reserveToDate;

    // clerk button panel
    private JPanel clerkBtnPanel;
    private JButton rentBtn;
    private JButton returnBtn;
    private JButton reportBtn1,reportBtn2,reportBtn3,reportBtn4;

    /**clerk panel*/
    // EFFECTS: helper functions of RENT

    // EFFECTS: helper functions of RETURN

    // EFFECTS: helper functions of REPORT FOR BRANCH RETURN

    // EFFECTS: helper functions of REPORT FOR BRANCH RENTAL

    // EFFECTS: helper functions of REPORT FOR GENERAL RETURN

    // EFFECTS: helper functions of REPORT FOR GENERAL RENTAL

    // TODO
    private void setUpClerkBtnPanel() {
        rentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"yes", "no"};
                Object l = JOptionPane.showInputDialog(null, "did you reserve a vehicle?",
                        "reserve before?", JOptionPane.PLAIN_MESSAGE, null, options, options[3]);
                if (l.toString().equals("yes")) {

                }
            }
        });

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // report for branch return
        reportBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // report for branch rental
        reportBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // report for general return
        reportBtn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // report for general rental
        reportBtn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private void initializeClerkBtnPanel() {
        GridLayout gd = new GridLayout(6,1);
        gd.setVgap(30);
        clerkBtnPanel = new JPanel(gd);
        clerkBtnPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        clerkBtnPanel.setOpaque(false);
        clerkBtnPanel.setVisible(true);

        rentBtn = new JButton("RENT");
        returnBtn = new JButton("RETURN");
        reportBtn1 = new JButton("REPORT FOR BRANCH RETURN");
        reportBtn2 = new JButton("REPORT FOR BRANCH RENTAL");
        reportBtn3 = new JButton("REPORT FOR GENERAL RETURN");
        reportBtn4 = new JButton("REPORT FOR GENERAL RENTAL");

        clerkBtnPanel.add(rentBtn);
        clerkBtnPanel.add(returnBtn);
        clerkBtnPanel.add(reportBtn1);
        clerkBtnPanel.add(reportBtn2);
        clerkBtnPanel.add(reportBtn3);
        clerkBtnPanel.add(reportBtn4);
    }


    /**customer panel*/
    // EFFECTS: helper function for both PREVIEW and RESERVE
    private boolean checkValidityOfYear (String year) {
        if (year.equals("") || Integer.parseInt(year) > 2022 || Integer.parseInt(year) <1990) {
            JOptionPane.showMessageDialog(null, "please input a valid year", "Error",0);
            return false;
        }
        return true;
    }
    private boolean checkValidityOfMon (String month) {
        if (month.equals("") || Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1){
            JOptionPane.showMessageDialog(null, "please input a valid month", "Error",0);
            return false;
        }

        return true;
    }
    private boolean checkValidityOfDay (String year, String month, String day) {
        // if the day < 1, false
        if (Integer.parseInt(day)<1) {
            JOptionPane.showMessageDialog(null, "please input a valid Day > 1", "Error",0);
            return false;
        }

        // if the month = 1,3,5,7,8,10,12,31, and the day > 31, false
        if (Integer.parseInt(month)==1 ||
                Integer.parseInt(month)==3 ||
                Integer.parseInt(month)==5 ||
                Integer.parseInt(month)==7 ||
                Integer.parseInt(month)== 8 ||
                Integer.parseInt(month)== 10 ||
                Integer.parseInt(month)==12) {
            if (Integer.parseInt(day)>31) {
                JOptionPane.showMessageDialog(null, "please input a valid Day <= 31", "Error",0);
                return false;
            }
        }

        // if the month = 4,6,9,11, and the day > 30, false
        if (Integer.parseInt(month) == 4 ||
                Integer.parseInt(month) == 6 ||
                Integer.parseInt(month) == 9 ||
                Integer.parseInt(month) == 11) {
            if (Integer.parseInt(day) > 30) {
                JOptionPane.showMessageDialog(null, "please input a valid Day <= 30", "Error",0);
                return false;
            }
        }

        // if it is 闰年, and the day > 29, false
        if ((Integer.parseInt(year) % 4) == 0 ) {
            if (Integer.parseInt(day) > 29) {
                JOptionPane.showMessageDialog(null, "please input a valid Day <= 29", "Error",0);
                return false;
            }
        }

        // if it is not 闰年, and the day > 28, false
        if ((Integer.parseInt(year) % 4) != 0) {
            if (Integer.parseInt(day) > 28) {
                JOptionPane.showMessageDialog(null, "please input a valid Day <= 28", "Error",0);
                return false;
            }
        }
        return true;
    }
    private boolean checkValidityOfHour (String hour) {
        if (Integer.parseInt(hour) >24 || Integer.parseInt(hour)<0 ) {
            JOptionPane.showMessageDialog(null, "please input a valid Hour > 0 and <24", "Error",0);
            return false;
        }
        return true;
    }
    private boolean checkValidityOfMinute (String minute) {
        if (Integer.parseInt(minute) > 59 || Integer.parseInt(minute) < 0) {
            JOptionPane.showMessageDialog(null, "please input a valid minute > 0 and < 60", "Error",0);
            return false;
        }
        return true;
    }
    private boolean checkValidityOfSec (String sec) {
        if(Integer.parseInt(sec) > 59 || Integer.parseInt(sec) < 0 ) {
            JOptionPane.showMessageDialog(null, "please input a valid second > 0 and < 60", "Error",0);
            return false;
        }
        return true;
    }
    private String makeUpZero(String number) {
        if (Integer.parseInt(number) < 10 && Integer.parseInt(number) > 0){
            number = "0"+number;
        }
        return number;
    }

    // EFFECTS: helper functions of PREVIEW
    // output: vlicense, make, model, year, color, vtname, location
    private void getPreviewOutput(){
        System.out.println("customer get available vehicles input: "+previewVtName + "," + previewLocation + "," + previewFromDate + "," + previewToDate);

        String[] output = null;
        CustomerGetAvailableVehicleModel[] cgavh = delegate.customerGetAvailableVehicles(previewVtName,previewLocation,previewFromDate,previewToDate);
        for(int i = 0; i<cgavh.length; i++){
            output[i] = cgavh[i].getVlicense() + "   " + cgavh[i].getMake() + "   " + cgavh[i].getModel() + "   "
                    + cgavh[i].getYear() + "   " + cgavh[i].getColor()  + "   "
                    + cgavh[i].getVtname() +"   "+ cgavh[i].getLocation() +"\n";
        }

        JOptionPane.showMessageDialog(new JFrame("preview result"), output);
    }

    // EFFECTS: helper functions of RESERVE
    // output: confNo
    private void getReserveOutput() {
        System.out.println("customer reserves vehicles input: " + reserveVtName + "," + reserveDlicense + "," + reserveFromDate + "," + reserveToDate);
        int confNo = delegate.customerMakeReservation(reserveVtName,Integer.parseInt(reserveDlicense),reserveFromDate,reserveToDate);
        JOptionPane.showMessageDialog(new JFrame("confirmation number"), confNo);
    }

    // EFFECTS: main functions of customer panel
    private void initializeCustomerBtnPanel() {
        GridLayout gd = new GridLayout(3,1);
        gd.setVgap(40);
        customerBtnPanel = new JPanel(gd);
        customerBtnPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        customerBtnPanel.setOpaque(false);
        customerBtnPanel.setVisible(true);

        previewBtn = new JButton("PREVIEW");
        reserveBtn = new JButton("RESERVE");
        backBtn = new JButton("BACK TO HOME");
        customerBtnPanel.add(previewBtn);
        customerBtnPanel.add(reserveBtn);
        customerBtnPanel.add(backBtn);
    }
    private void setUpCustomerBtnPanel() {
        previewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] questions = new String[14];
                String fromYear = null;
                String fromMon = null;
                String fromDay = null;
                String fromHour = null;
                String fromMin = null;
                String fromSec = null;
                String toYear = null;
                String toMon = null;
                String toDay = null;
                String toHour =  null;
                String toMin = null;
                String toSec = null;

                questions[0] = "what is the vehicle type?";
                questions[1] = "what is the location?";
                questions[2] = "start from which year? format: yyyy";
                questions[3] = "start from which month? format: mm";
                questions[4] = "start from which day? format: dd";
                questions[5] = "start from which hour? format: hh";
                questions[6] = "start from which minute? format: mm";
                questions[7] = "start from which second? format: ss";
                questions[8] = "end at which year? format: yyyy";
                questions[9] = "end at which month? format: mm";
                questions[10] = "end at which day? format: dd";
                questions[11] = "end at which hour? format: hh";
                questions[12] = "end at which minute? formate: mm";
                questions[13] = "end at which second? formate: ss";

                int i = 0;
                while (i<14) {
                    switch (i) {
                        case 0: {
                            String res = JOptionPane.showInputDialog(new JFrame("name of vehicle type"), questions[i]);
                            if(res.equals("")) {
                                JOptionPane.showMessageDialog(null, "Please input the vehicle type","Error",0);
                                break;
                            } else {
                                previewVtName = res;
                                i++; break;
                            }
                        }
                        case 1: {
                            String res = JOptionPane.showInputDialog(new JFrame("location"),questions[i]);
                            if(res.equals("")) {
                                JOptionPane.showMessageDialog(null, "please input the location", "Error",0);
                                break;
                            } else {
                                previewLocation = res;
                                i++; break;
                            }
                        }
                        case 2: {
                            fromYear = JOptionPane.showInputDialog(new JFrame("from Year"), questions[i]);
                            if(!checkValidityOfYear(fromYear)){
                                break;
                            }
                            i++;break;

                        }
                        case 3: {
                            fromMon = JOptionPane.showInputDialog(new JFrame("from Month"), questions[i]);
                            if(!checkValidityOfMon(fromMon)) {
                                break;
                            } else {
                                i++; break;
                            }
                        }
                        case 4: {
                            fromDay = JOptionPane.showInputDialog(new JFrame("from Day"), questions[i]);
                            if (!checkValidityOfDay(fromYear, fromMon, fromDay))
                                break;
                            i++; break;
                        }
                        case 5: {
                            fromHour= JOptionPane.showInputDialog(new JFrame("from Hour"), questions[i]);
                            if(!checkValidityOfHour(fromHour))
                                break;
                            i++; break;
                        }
                        case 6: {
                            fromMin = JOptionPane.showInputDialog(new JFrame("from Minute"), questions[i]);
                            if(!checkValidityOfMinute(fromMin))
                                break;
                            i++;break;
                        }
                        case 7: {
                            fromSec = JOptionPane.showInputDialog(new Frame("from Second"), questions[i]);
                            if(!checkValidityOfSec(fromSec))
                                break;
                            previewFromDate = fromYear + "-" + makeUpZero(fromMon) + "-" + makeUpZero(fromDay) + "T"
                                    + makeUpZero(fromHour) + ":" + makeUpZero(fromMin) + ":" + makeUpZero(fromSec) + ".00Z";
                            System.out.println("previewFromDate: " + previewFromDate);
                            i++;break;
                        }
                        case 8 : {
                            toYear = JOptionPane.showInputDialog(new Frame("to Year"), questions[i]);
                            if(!checkValidityOfYear(toYear))
                                break;
                            i++;break;
                        }
                        case 9: {
                            toMon = JOptionPane.showInputDialog(new Frame("to Month"), questions[i]);
                            if (!checkValidityOfMon(toMon))
                                break;
                            i++;break;
                        }
                        case 10: {
                            toDay = JOptionPane.showInputDialog(new Frame("to Day"), questions[i]);
                            if(!checkValidityOfDay(toYear,toMon,toDay))
                                break;
                            i++;break;
                        }
                        case 11: {
                            toHour = JOptionPane.showInputDialog(new Frame("to Hour"), questions[i]);
                            if(!checkValidityOfHour(toHour))
                                break;
                            i++;break;
                        }
                        case 12: {
                            toMin = JOptionPane.showInputDialog(new Frame("to minute"), questions[i]);
                            if(!checkValidityOfMinute(toMin))
                                break;
                            i++;break;
                        }
                        case 13: {
                            toSec = JOptionPane.showInputDialog(new Frame("to Second"), questions[i]);
                            if (!checkValidityOfSec(toSec))
                                break;
                            previewToDate = toYear + "-" + makeUpZero(toMon) + "-" + makeUpZero(toDay) + "T" +
                                    makeUpZero(toHour) + ":" + makeUpZero(toMin) + ":" + makeUpZero(toSec) + ".00Z";
                            System.out.println("previewFromDate: " + previewToDate);
                            i++;
                            break;
                        }
                        default: {
                            i = 14;
                            JOptionPane.showMessageDialog(null, "error in preview", "Error",0);
                            break;
                        }
                    }
                }
                getPreviewOutput();
            }
        });

        reserveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] questions = new String[14];
                questions[0] = "what is the vehicle type?";
                questions[1] = "what is your driver license?";
                questions[2] = "start from which year? format: yyyy";
                questions[3] = "start from which month? format: mm";
                questions[4] = "start from which day? format: dd";
                questions[5] = "start from which hour? format: hh";
                questions[6] = "start from which minute? format: mm";
                questions[7] = "start from which second? format: ss";
                questions[8] = "end at which year? format: yyyy";
                questions[9] = "end at which month? format: mm";
                questions[10] = "end at which day? format: dd";
                questions[11] = "end at which hour? format: hh";
                questions[12] = "end at which minute? formate: mm";
                questions[13] = "end at which second? formate: ss";

                String fromYear = null;
                String fromMon = null;
                String fromDay = null;
                String fromHour = null;
                String fromMin = null;
                String fromSec = null;
                String toYear = null;
                String toMon = null;
                String toDay = null;
                String toHour =  null;
                String toMin = null;
                String toSec = null;

                int i = 0;
                while (i<14) {
                    switch (i) {
                        case 0: {
                            String res = JOptionPane.showInputDialog(new JFrame("name of vehicle type"), questions[i]);
                            if(res.equals("")) {
                                JOptionPane.showMessageDialog(null, "Please input the vehicle type","Error",0);
                                break;
                            } else {
                                reserveVtName = res;
                                i++; break;
                            }
                        }
                        case 1: {
                            String res = JOptionPane.showInputDialog(new JFrame("driver license"),questions[i]);
                            if(res.equals("")) {
                                JOptionPane.showMessageDialog(null, "please input the dlicense", "Error",0);
                                break;
                            } else {
                                reserveDlicense = res;
                                i++; break;
                            }
                        }
                        case 2: {
                            fromYear = JOptionPane.showInputDialog(new JFrame("from Year"), questions[i]);
                            if(!checkValidityOfYear(fromYear)){
                                break;
                            }
                            i++;break;

                        }
                        case 3: {
                            fromMon = JOptionPane.showInputDialog(new JFrame("from Month"), questions[i]);
                            if(!checkValidityOfMon(fromMon)) {
                                break;
                            } else {
                                i++; break;
                            }
                        }
                        case 4: {
                            fromDay = JOptionPane.showInputDialog(new JFrame("from Day"), questions[i]);
                            if (!checkValidityOfDay(fromYear, fromMon, fromDay))
                                break;
                            i++; break;
                        }
                        case 5: {
                            fromHour= JOptionPane.showInputDialog(new JFrame("from Hour"), questions[i]);
                            if(!checkValidityOfHour(fromHour))
                                break;
                            i++; break;
                        }
                        case 6: {
                            fromMin = JOptionPane.showInputDialog(new JFrame("from Minute"), questions[i]);
                            if(!checkValidityOfMinute(fromMin))
                                break;
                            i++;break;
                        }
                        case 7: {
                            fromSec = JOptionPane.showInputDialog(new Frame("from Second"), questions[i]);
                            if(!checkValidityOfSec(fromSec))
                                break;
                            reserveFromDate = fromYear + "-" + fromMon + "-" + fromDay + "T" + fromHour + ":" + fromMin + ":" + fromSec + ".00Z";
                            System.out.println("reserveFromDate: " + reserveFromDate);
                            i++;break;
                        }
                        case 8 : {
                            toYear = JOptionPane.showInputDialog(new Frame("to Year"), questions[i]);
                            if(!checkValidityOfYear(toYear))
                                break;
                            i++;break;
                        }
                        case 9: {
                            toMon = JOptionPane.showInputDialog(new Frame("to Month"), questions[i]);
                            if (!checkValidityOfMon(toMon))
                                break;
                            i++;break;
                        }
                        case 10: {
                            toDay = JOptionPane.showInputDialog(new Frame("to Day"), questions[i]);
                            if(!checkValidityOfDay(toYear,toMon,toDay))
                                break;
                            i++;break;
                        }
                        case 11: {
                            toHour = JOptionPane.showInputDialog(new Frame("to Hour"), questions[i]);
                            if(!checkValidityOfHour(toHour))
                                break;
                            i++;break;
                        }
                        case 12: {
                            toMin = JOptionPane.showInputDialog(new Frame("to minute"), questions[i]);
                            if(!checkValidityOfMinute(toMin))
                                break;
                            i++;break;
                        }
                        case 13: {
                            toSec = JOptionPane.showInputDialog(new Frame("to Second"), questions[i]);
                            if (!checkValidityOfSec(toSec))
                                break;
                            reserveToDate = toYear + "-" + toMon + "-" + toDay + "T" + toHour + ":" + toMin + ":" + toSec + ".00Z";
                            System.out.println("reserveToDate: " + reserveToDate);
                            i++;
                            break;
                        }
                        default: {
                            i = 14;
                            JOptionPane.showMessageDialog(null, "error in reservation", "Error",0);
                            break;
                        }
                    }
                }
                getReserveOutput();
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                servicePanel.setVisible(false);
            }
        });
    }



    /**logistics functions*/
    public ServicePanel(Delegate delegate) {
        this.delegate = delegate;

        initializeClerkBtnPanel();
        setUpClerkBtnPanel();
        initializeCustomerBtnPanel();
        setUpCustomerBtnPanel();

        customerBtnPanel.setOpaque(false);
        clerkBtnPanel.setOpaque(false);
        servicePanel.add(customerBtnPanel);
        servicePanel.add(clerkBtnPanel);
        servicePanel.setOpaque(false);
    }
    public JPanel getServicePanel() {
        return this.servicePanel;
    }
    public JButton getBackBtn() {return this.backBtn;}

}
