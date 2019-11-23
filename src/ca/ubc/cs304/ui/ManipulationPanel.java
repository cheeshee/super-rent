package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.ubc.cs304.delegates.Delegate;
import ca.ubc.cs304.model.ModelForManipulation.*;
/**steps:*/
/**paint: initialize all panels*/
/**handle: get inputs, request for query(pass the param and call function), set the result panel(setText)*/
/**show: add actionlistener to the buttons(setVisible(true))*/

public class ManipulationPanel {
    // fiels for initialization
    private Delegate delegate;

    // btns in menu
    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
    private JPanel cardMHome = new JPanel();
    private JButton homeMInsertBtn = new JButton("INSERT");
    private JButton homeMDeleteBtn = new JButton("DELETE");
    private JButton homeMUpdateBtn = new JButton("UPDATE");
    private JButton homeMViewAllBtn = new JButton("VIEW ALL");
    private JButton homeMBackBtn = new JButton("BACK");

    // fields for card insert
    private JPanel cardInsert = new JPanel(new GridLayout(1,3));
    private JPanel insertPanel1, insertPanel2, insertPanel3;
    private JButton applyBtnInsert;
    private JButton showBtnInsert;
    private JButton backBtnInsert;
    private JTextField dlicenseInsert;
    private JTextField nameInsert;
    private JTextField cellphoneInsert;
    private JTextField addressInsert;
    private JPanel topMiddlePart = new JPanel(new GridLayout(1,2));
    private JLabel hint1 = new JLabel("INSERT INTO CUSTOMERS");
    private JPanel midMiddlePart = new JPanel(new GridLayout(1,6));
    private JLabel hint2 = new JLabel("VALUES ( ");
    private JLabel hint3 = new JLabel(" )");


    // fields for card delete
    private JPanel cardDelete = new JPanel(new GridLayout(1,3));
    private JPanel deletePanel1, deletePanel2, deletePanel3;
    private JButton applyBtnDelete;
    private JButton showBtnDelete;
    private JButton backBtnDelete;
    private JTextField dlicenseDelete;
    private JPanel topMiddlePart1 = new JPanel(new GridLayout(1,2));
    private JLabel hint11 = new JLabel("DELETE FROM CUSTOMERS");
    private JPanel midMiddlePart1 = new JPanel(new GridLayout(1,2));
    private JLabel hint22 = new JLabel("WHERE VALUES = ");


    // fields for card update
    private JPanel cardUpdate = new JPanel(new GridLayout(1,3));
    private JPanel updatePanel1, updatePanel2, updatePanel3;
    private JButton applyBtnUpdate = new JButton("APPLY");
    private JButton showBtnUpdate = new JButton("SHOW");
    private JButton backBtnUpdate = new JButton("BACK");
    private JTextField addressUpdate;
    private JTextField nameUpdate;

    // fields for card showing all the tables
    private JPanel cardViewAll = new JPanel();
    private JButton viewAllBackBtn = new JButton("BACK");


    /**dominant functions*/
    // delegate: SuperRent
    public ManipulationPanel(Delegate delegate) {
        this.delegate = delegate;

        initializeCardMHome();
        initializeCardInsert();
        initializeCardDelete();
        initializeCardUpdate();
        initializeCardViewAll();

        cards.setOpaque(false);
        cards.add("cardMHome",cardMHome);
        cards.add("cardInsert",cardInsert);
        cards.add("cardDelete",cardDelete);
        cards.add("cardUpdate",cardUpdate);
        cards.add("cardViewAll", cardViewAll);
        cardLayout.show(cards,"cardMHome");
    }
    // return all the cards
    public JPanel getManipulationPanel() {
        return this.cards;
    }
    // return to the home page
    public JButton getHomeMBackBtn() {return this.homeMBackBtn;}


    /**helper functions for insert, update, delete*/
    // EFFECTS: helper function to paint card (also view some tables!!)
    private JPanel paintTable(String state) {
        // first do query, get the number of rows of table customer
        ManipulateCustomersModel[] ManipulateCustomersModels = delegate.viewCustomer();

        JPanel tablePane = new JPanel(new GridLayout(ManipulateCustomersModels.length + 2,1));
        tablePane.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        tablePane.setOpaque(false);

        if (state.equals("before"))
            tablePane.setVisible(true);
        else if (state.equals("after"))
            tablePane.setVisible(false);
        else
            JOptionPane.showMessageDialog(null,"error when viewing customer table");

        // second paint the result to the panel
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
                            ManipulateCustomersModels[i].getCellphone()+ "   "+ ManipulateCustomersModels[i].getAddress());
            tablePane.add(aftCustomer);
        }

        return tablePane;
    }



    /**for delete card*/
    // EFFECTS: paint manipulation delete card
    private void initializeCardDelete() {
        applyBtnDelete = new JButton("APPLY");
        showBtnDelete = new JButton("SHOW");
        backBtnDelete = new JButton("BACK");
        dlicenseDelete = new JTextField();

        //
        deletePanel1 = paintTable("before");

        //
        deletePanel2 = new JPanel(new GridLayout(3,1));
        deletePanel2.setBorder(BorderFactory.createEmptyBorder(80,80,80,80));
        deletePanel2.setOpaque(false);
        deletePanel2.setVisible(true);

        topMiddlePart1.setOpaque(false);
        topMiddlePart1.add(hint11);

        midMiddlePart1.setBorder(BorderFactory.createEmptyBorder(20,10,30,10));
        midMiddlePart1.setOpaque(false);
        midMiddlePart1.add(hint22);
        midMiddlePart1.add(dlicenseDelete);

        JPanel bottomMiddlePanel = new JPanel(new GridLayout(1,3));
        bottomMiddlePanel.add(applyBtnDelete);
        bottomMiddlePanel.add(showBtnDelete);
        bottomMiddlePanel.add(backBtnDelete);
        bottomMiddlePanel.setOpaque(false);
        bottomMiddlePanel.setVisible(true);


        deletePanel2.add(topMiddlePart1);
        deletePanel2.add(midMiddlePart1);
        deletePanel2.add(bottomMiddlePanel);

        // paint right part
        deletePanel3 = new JPanel();
        deletePanel3.setOpaque(false);
        deletePanel3.setVisible(false);

        // add all the three panels to the insertPanel
        cardDelete.add(deletePanel1);
        cardDelete.add(deletePanel2);
        cardDelete.add(deletePanel3);
        cardDelete.setOpaque(false);

        setDeletetBtns();
    }

    // EFFECTS: show the result
    private void setDeletetBtns() {
        applyBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ddlicense = dlicenseDelete.getText().trim();
                System.out.println("in setDeleteBtns");
                if (ddlicense.equals("") ) {
                    JOptionPane.showMessageDialog(null, "dlicense  cannot be null!");
                } else {
                    delegate.deleteCustomer(Integer.parseInt(ddlicense));
                    deletePanel3.removeAll();
                    deletePanel3 = paintTable("after");
                    deletePanel3.setOpaque(false);
                    deletePanel3.setVisible(false);
                    cardDelete.add(deletePanel3);
                }
            }
        });
        showBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePanel3.setVisible(true);
            }
        });
        backBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards,"cardMHome");
                cardDelete.removeAll();
            }
        });
    }


    /**for update card*/
    // initialize update card
    private void initializeCardUpdate() {

    }


    private void setUpdateBtns(){}

    /**for viewAll card*/
    // initialize show all card
    private void initializeCardViewAll() {

    }




    /**for mHome card*/
    // EFFECTS: paint manipulation home card
    private void initializeCardMHome() {
        GridLayout gridLayout = new GridLayout(2,1);
        cardMHome.setLayout(gridLayout);
        gridLayout.setHgap(50);
        gridLayout.setVgap(20);
        cardMHome.setOpaque(false);

        JLabel selectionLabel = new JLabel("What are you looking for?");
        selectionLabel.setFont(MainUI.myFont);
        selectionLabel.setBorder(BorderFactory.createEmptyBorder(200,120,45,0));
        selectionLabel.setOpaque(false);

        cardMHome.add(selectionLabel);
        cardMHome.add(initializeMHomeBtnsPanel());
    }
    // EFFECTS: helper function to home card
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
        btnPanel1.add(homeMViewAllBtn);
        btnPanel2.add(homeMBackBtn);

        btnPanel.add(btnPanel1);
        btnPanel.add(btnPanel2);

        btnPanel.setOpaque(false);
        btnPanel1.setOpaque(false);
        btnPanel2.setOpaque(false);

        return btnPanel;
    }


    /**for insert card*/
    // EFFECTS: paint manipulation insert card
    private void initializeCardInsert() {
        applyBtnInsert = new JButton("APPLY");
        showBtnInsert = new JButton("SHOW");
        backBtnInsert = new JButton("BACK");
        dlicenseInsert = new JTextField();
        nameInsert = new JTextField();
        cellphoneInsert = new JTextField();
        addressInsert = new JTextField();

        // paint left part
        insertPanel1 = paintTable("before");

        // paint middle part
        insertPanel2 = new JPanel(new GridLayout(3,1));
        insertPanel2.setBorder(BorderFactory.createEmptyBorder(80,80,80,80));
        insertPanel2.setOpaque(false);
        insertPanel2.setVisible(true);

        topMiddlePart.setOpaque(false);
        topMiddlePart.add(hint1);

        midMiddlePart.setBorder(BorderFactory.createEmptyBorder(20,10,30,10));
        midMiddlePart.setOpaque(false);
        midMiddlePart.add(hint2);

        midMiddlePart.add(dlicenseInsert);
        midMiddlePart.add(nameInsert);
        midMiddlePart.add(cellphoneInsert);
        midMiddlePart.add(addressInsert);
        midMiddlePart.add(hint3);

        JPanel bottomMiddlePanel = new JPanel(new GridLayout(1,3));
        bottomMiddlePanel.add(applyBtnInsert);
        bottomMiddlePanel.add(showBtnInsert);
        bottomMiddlePanel.add(backBtnInsert);
        bottomMiddlePanel.setOpaque(false);
        bottomMiddlePanel.setVisible(true);


        insertPanel2.add(topMiddlePart);
        insertPanel2.add(midMiddlePart);
        insertPanel2.add(bottomMiddlePanel);

        // paint right part
        insertPanel3 = new JPanel();
        insertPanel3.setOpaque(false);
        insertPanel3.setVisible(false);


        // add all the three panels to the insertPanel
        cardInsert.add(insertPanel1);
        cardInsert.add(insertPanel2);
        cardInsert.add(insertPanel3);
        cardInsert.setOpaque(false);
        setInsertBtns();
    }

    // EFFECTS: show the result
    private void setInsertBtns() {
        applyBtnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ddlicense = dlicenseInsert.getText().trim();
                String nname = nameInsert.getText().trim();
                String ccellphone = cellphoneInsert.getText().trim();
                String aaddress = addressInsert.getText().trim();
                if (ddlicense.equals("") || nname.equals("")) {
                    JOptionPane.showMessageDialog(null, "dlicense and name cannot be null!");
                } else {
                    delegate.insertCustomer(Integer.parseInt(ddlicense), nname , ccellphone, aaddress);
                    insertPanel3.removeAll();
                    insertPanel3 = paintTable("after");
                    insertPanel3.setOpaque(false);
                    insertPanel3.setVisible(false);
                    cardInsert.add(insertPanel3);
                }
            }
        });
        showBtnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("show button is here: is insertPanel3 visible "+insertPanel3.isVisible());
                System.out.println("show button is here: is insertPanel3 available " + insertPanel3.isValid());
                insertPanel3.setVisible(true);
            }
        });
        backBtnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards,"cardMHome");
                cardInsert.removeAll();
            }
        });
    }

}
