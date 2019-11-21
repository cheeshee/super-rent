package ca.ubc.cs304.ui;

import javax.management.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.ubc.cs304.handler.Manipulation;
import ca.ubc.cs304.model.InsertCustomerQueryModel;
import ca.ubc.cs304.model.QueryModel;

public class ManipulationPanel {

    /**step 2: assume the user choose the manipulation
     * then the project will jump to this part*/
    private Manipulation manipulation = new Manipulation();

    private QueryModel query;

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
    private JPanel cardInsert = new JPanel();
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


    //TerminalTransactions terminalTransactions = new TerminalTransactions();

    // constructor
    public ManipulationPanel() {
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


    public String getInsertInput() {return this.insertInput;}
    public String getDeleteInput() {return this.deleteInput;}
    public String getUpdateInput() {return this.updateInput;}


    // initialize home card
    private void initializeCardMHome() {
        GridLayout gridLayout = new GridLayout(2,1);
        cardMHome.setLayout(gridLayout);
        gridLayout.setHgap(50);
        gridLayout.setVgap(20);
        cardMHome.setOpaque(false);

        JLabel selectionLabel = new JLabel("What are you looking for?");
        selectionLabel.setBorder(BorderFactory.createEmptyBorder(75,120,45,0));
        selectionLabel.setFont(new Font("Serif", Font.PLAIN,23));
        selectionLabel.setOpaque(false);

        cardMHome.add(selectionLabel);
        cardMHome.add(initializeMHomeBtnsPanel());
    }
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

    private JPanel btnPanel() {
        JPanel btns = new JPanel();
        JButton showBefore = new JButton("SHOW BEFORE");
        showBefore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "error: INSERT BUTTON!");
                }
            }
        });

        JButton showAfter = new JButton("SHOW AFTER");



        return btns;
    }

    // initialize insert card
    private void initializeCardInsert() {
        query = new InsertCustomerQueryModel();
        JPanel insertPanel = new JPanel(new GridLayout(1,3)); insertPanel.setOpaque(false);


        JPanel left = new JPanel(new GridLayout(2,1));
        insertPanel.add(left);

        JPanel part1 = new JPanel(new GridLayout(1,2)); part1.setOpaque(false);
        JLabel hint1 = new JLabel("INSERT INTO");
        part1.add(hint1);
        JTextField tName = new JTextField();
        part1.add(tName);

        JPanel part2 = new JPanel(new GridLayout(1,2)); part2.setOpaque(false);
        JLabel hint2 = new JLabel("VALUES");
        part2.add(hint2);
        JTextField fields = new JTextField();
        part2.add(fields);

        left.add(part1); left.add(part2);
        left.setOpaque(false);


        //insertInput = handleInsertInput(text1.getText());
        //manipulation.setInsertCustomerQuery();

        JPanel btnPanel = new JPanel();

        cardInsert.add(insertPanel);
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
