package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.ubc.cs304.delegates.Delegate;
import ca.ubc.cs304.model.ModelForManipulation.*;

public class ManipulationPanel {

    /**step 2: assume the user choose the manipulation
     * then the project will jump to this part*/

    //fields for cards
    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);

    // fields for card manipulation Home
    private JPanel cardMHome = new JPanel();

    /**step 3: assume the user click the INSERT button*/
    private JButton homeMInsertBtn = new JButton("INSERT");
    private JButton homeMDeleteBtn = new JButton("DELETE");
    private JButton homeMUpdateBtn = new JButton("UPDATE");
    private JButton homeMViewSomeBtn = new JButton("VIEW SOME");
    private JButton homeMViewAllBtn = new JButton("VIEW ALL");
    private JButton homeMBackBtn = new JButton("BACK");

    // fields for card inserts
    private JPanel cardInsert = new JPanel(new GridLayout(1,3));
    private JButton insertApplyBtn = new JButton("APPLY");
    private JButton insertBackBtn = new JButton("BACK");
    private String insertInput = null;


    // fields for card delete
    private JPanel cardDelete = new JPanel();
    private JButton deleteApplyBtn = new JButton("APPLY");
    private JButton deleteBackBtn = new JButton("BACK");
    private String deleteInput = null;


    // fields for card update
    private JPanel cardUpdate = new JPanel();
    private JButton updateApplyBtn = new JButton("APPLY");
    private JButton updateBackBtn = new JButton("BACK");
    private String updateInput = null;


    // fields for card showing specific table
    private JPanel cardViewSome = new JPanel();
    private JButton viewSomeApplyBtn = new JButton("APPLY");
    private JButton viewSomeBackBtn = new JButton("BACK");


    // fields for card showing all the tables
    private JPanel cardViewAll = new JPanel();
    private JButton viewAllBackBtn = new JButton("BACK");

    // delegate: SuperRent
    public Delegate delegate;

    // constructor
    public ManipulationPanel(Delegate delegate) {
        this.delegate = delegate;

        initializeCardMHome();
        initializeCardInsert();
        initializeCardDelete();
        initializeCardUpdate();
        initializeCardViewSome();
        initializeCardViewAll();

        cards.setOpaque(false);
        cards.add("cardMHome",cardMHome);
        cards.add("cardInsert",cardInsert);
        cards.add("cardDelete",cardDelete);
        cards.add("cardUpdate",cardUpdate);
        cards.add("cardViewSome", cardViewSome);
        cards.add("cardViewAll", cardViewAll);
        cardLayout.show(cards,"cardMHome");
    }

    // return all the cards
    public JPanel getManipulationPanel() {
        return this.cards;
    }
    // return to the home page
    public JButton getHomeMBackBtn() {return this.homeMBackBtn;}

    // initialize home card
    private void initializeCardMHome() {
        GridLayout gridLayout = new GridLayout(2,1);
        cardMHome.setLayout(gridLayout);
        gridLayout.setHgap(50);
        gridLayout.setVgap(20);
        cardMHome.setOpaque(false);

        JLabel selectionLabel = new JLabel("What are you looking for?");
        selectionLabel.setFont(MainUI.myFont);
        selectionLabel.setBorder(BorderFactory.createEmptyBorder(75,120,45,0));
        selectionLabel.setOpaque(false);

        cardMHome.add(selectionLabel);
        cardMHome.add(initializeMHomeBtnsPanel());
    }

    /**step 4: program now jumps to here*/
    private JPanel initializeMHomeBtnsPanel(){
        GridLayout gd = new GridLayout(2,1);
        gd.setHgap(50); gd.setVgap(20);

        GridLayout gd1 = new GridLayout(1,5);
        gd1.setHgap(50); gd1.setVgap(10);

        GridLayout gd2 = new GridLayout(1,1);
        gd2.setHgap(50); gd2.setVgap(10);

        JPanel btnPanel = new JPanel(gd);
        JPanel btnPanel1 = new JPanel(gd1);
        JPanel btnPanel2 = new JPanel(gd2);


        homeMInsertBtn.setPreferredSize(new Dimension(72,35));
        homeMInsertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    cardLayout.show(cards,"cardInsert");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "error: INSERT BUTTON!");
                }
            }
        });

        homeMDeleteBtn.setPreferredSize(new Dimension(72,35));
        homeMDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    cardLayout.show(cards,"cardDelete");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "error: DELETE BUTTON!");
                }
            }
        });

        homeMUpdateBtn.setPreferredSize(new Dimension(72,35));
        homeMUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    cardLayout.show(cards,"cardUpdate");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "error: UPDATE BUTTON!");
                }
            }
        });

        homeMViewSomeBtn.setPreferredSize(new Dimension(72,35));
        homeMViewSomeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    cardLayout.show(cards,"cardViewSome");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "error: VIEW SOME BUTTON!");
                }
            }
        });

        homeMViewAllBtn.setPreferredSize(new Dimension(72,35));
        homeMViewAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    cardLayout.show(cards,"cardViewAll");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "error: VIEW ALL BUTTON!");
                }
            }
        });

        homeMBackBtn.setPreferredSize(new Dimension(72,35));
        homeMBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    cardMHome.setVisible(false);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "error: VIEW ALL BUTTON!");
                }
            }
        });

        btnPanel1.add(homeMInsertBtn);
        btnPanel1.add(homeMDeleteBtn);
        btnPanel1.add(homeMUpdateBtn);
        btnPanel1.add(homeMViewSomeBtn);
        btnPanel1.add(homeMViewAllBtn);
        btnPanel2.add(homeMBackBtn);

        btnPanel.add(btnPanel1);
        btnPanel.add(btnPanel2);

        btnPanel.setOpaque(false);
        btnPanel1.setOpaque(false);
        btnPanel2.setOpaque(false);

        return btnPanel;
    }

    /**step 5: now the 'card' Insert shows*/
    private JPanel panel1, panel2, panel3;
    private JPanel paintInsertTable(String state) {
        // first and third: before insert
        ManipulateCustomersModel[] ManipulateCustomersModels = delegate.viewCustomer();
        System.out.println("customer number of rows:" + ManipulateCustomersModels.length);
        System.out.println("in paint insert table");
        JPanel tablePane = new JPanel(new GridLayout(ManipulateCustomersModels.length,1));
        tablePane.setBorder(BorderFactory.createEmptyBorder(10,20,10,200));
        tablePane.setOpaque(false);

        if (state.equals("before"))
            tablePane.setVisible(true);
        else
            tablePane.setVisible(false);

        JLabel hint = new JLabel(state+" insert");
        hint.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        hint.setFont(new Font("SansSerif", Font.BOLD, 30));
        tablePane.add(hint);

        JLabel attr = new JLabel("dlicense      name        cellphone        address");
        attr.setFont(new Font("SansSerif",Font.BOLD,15));
        tablePane.add(attr);

        for (int i = 0; i< ManipulateCustomersModels.length; i++) {
            JLabel aftCustomer = new JLabel();
            aftCustomer.setText(
                    ManipulateCustomersModels[i].getDlicense()+"   "+ ManipulateCustomersModels[i].getName()+"   "+
                            ManipulateCustomersModels[i].getCellphone()+ "   "+ ManipulateCustomersModels[i].getName());
            tablePane.add(aftCustomer);
        }

        return tablePane;
    }
    private void initializeCardInsert() {
        // first: paint "before" table
        panel1 = paintInsertTable("before");
        cardInsert.add(panel1);

        // second: insert syntax
        /**step 6: once call delegate.insertCustomer, and pass in params*/
        panel2 = new JPanel(new GridLayout(3,1));
        panel2.setBorder(BorderFactory.createEmptyBorder(80,80,80,80));
        panel2.setOpaque(false);
        panel2.setVisible(true);
        JPanel part1 = new JPanel(new GridLayout(1,2)); part1.setOpaque(false);
        JLabel hint1 = new JLabel("INSERT INTO CUSTOMERS");
        part1.add(hint1);

        JPanel part2 = new JPanel(new GridLayout(1,6)); part2.setOpaque(false);
        JLabel hint2 = new JLabel("VALUES( "); part2.add(hint2);
        JTextField dlicense = new JTextField(); part2.add(dlicense);
        JTextField name = new JTextField(); part2.add(name);
        JTextField cellphone = new JTextField(); part2.add(cellphone);
        JTextField address = new JTextField(); part2.add(address);
        JLabel hint3 = new JLabel(" )"); part2.add(hint3);
        part2.setBorder(BorderFactory.createEmptyBorder(20,10,30,10));

        // third
        JPanel btnPanel = new JPanel(new GridLayout(1,2)); btnPanel.setOpaque(false);
        JButton apply = new JButton("APPLY");
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dlicense.getText().trim().equals("")|name.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "dlicense or name shouldn't be null!");
                } else {
                    /**step 7: once call delegate.insertCustomer, and pass in params*/
                    delegate.insertCustomer(
                            Integer.parseInt(dlicense.getText().trim()),
                            name.getText().trim(),
                            cellphone.getText().trim(),
                            address.getText().trim());
                    // third: paint "after" table
                    panel3 = paintInsertTable("after");

                }
            }
        });

        JButton show = new JButton("SHOW");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                panel3.setVisible(true);
            }
        });

        JButton clear = new JButton("CLEAR");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlicense.setText("");
                name.setText("");
                cellphone.setText("");
                address.setText("");
                panel1 = new JPanel();
                panel2 = new JPanel();
                panel3 = new JPanel();
            }
        });

        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlicense.setText("");
                name.setText("");
                cellphone.setText("");
                address.setText("");
                panel1.removeAll();
                panel2.removeAll();
                panel3.removeAll();

                panel1 = new JPanel();
                panel2 = new JPanel();
                panel3 = new JPanel();
                cardLayout.show(cards, "cardMHome");
            }
        });

        btnPanel.add(apply); btnPanel.add(show); btnPanel.add(clear); btnPanel.add(back);
        panel2.add(part1); panel2.add(part2); panel2.add(btnPanel);
        panel2.setPreferredSize(new Dimension(70,80));

        cardInsert.add(panel2);cardInsert.add(panel3);
        cardInsert.setOpaque(false);
    }


    // initialize delete card
    private void initializeCardDelete() {

    }

    // initialize update card
    private void initializeCardUpdate() {

    }

    // initialize show some card
    private void initializeCardViewSome() {

    }

    // initialize show all card
    private void initializeCardViewAll() {

    }
}
