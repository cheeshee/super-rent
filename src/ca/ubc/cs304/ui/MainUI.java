package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.ubc.cs304.delegates.Delegate;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainUI {
    private ImageIcon background = new ImageIcon("src\\bkgImg.jpg");
    private JFrame frame = new JFrame("super rent");
    private Dimension dim = frame.getToolkit().getScreenSize();
    private JPanel contentPane = (JPanel) frame.getContentPane();
    private CardLayout cardLayout;
    private JPanel cards;
    private Delegate delegate;
    public static Font myFont = new Font("SansSerif", Font.BOLD, 50);

    // buttons on the main page
    private JButton servicebtn = new JButton("Services");
    private JButton manipulationbtn = new JButton("Manipulations");
    private JButton quitbtn = new JButton("Quit");

    //initialize 'service' card
    private ServicePanel servicePanel;

    //initialize 'manipulation' card
    private ManipulationPanel manipulationPane;

    /**dominant functions*/
    public MainUI(){

    }
    public void showMainUI(Delegate delegate) {
        this.delegate = delegate;
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(true);
        contentPane.setOpaque(false);


        // set frame location
        setFrameLocation();

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setOpaque(false);

        cards.add("cardHome",cardHome());
        cards.add("cardManipulation",cardManipulation());
        cards.add("cardService",cardService());

        cardLayout.show(cards,"cardHome");
        contentPane.add(cards, BorderLayout.CENTER);

        // set buttons
        setButtonAction();
        // set background image
        JLayeredPane layeredPane = frame.getLayeredPane();
        layeredPane.add(setBackgroundImage(),new Integer(Integer.MIN_VALUE));
        layeredPane.setOpaque(true);
    }

    /**preparation for cards*/
    // EFFECTS: initialize hintPanel and btnPanel
    private JPanel cardHome() {
        JPanel cardHome = new JPanel(new GridLayout(2,1));

        // initialize homePanel
        JPanel labelPanel_Home = new JPanel();
        labelPanel_Home.setOpaque(false);
        JLabel label_Home = new JLabel("what do you want to do with Super Rent?");
        label_Home.setBorder(BorderFactory.createEmptyBorder(30,20,10,20));
        labelPanel_Home.add(label_Home);
        JPanel buttonPanel_Home = new JPanel();
        buttonPanel_Home.setBorder(BorderFactory.createEmptyBorder(80,20,50,20));
        label_Home.setFont(myFont);
        buttonPanel_Home.setOpaque(false);

        // initialize btnPanel
        servicebtn.setPreferredSize(new Dimension(120,50));
        buttonPanel_Home.add(servicebtn);
        manipulationbtn.setPreferredSize(new Dimension(120,50));
        buttonPanel_Home.add(manipulationbtn);
        quitbtn.setPreferredSize(new Dimension(120,50));
        buttonPanel_Home.add(quitbtn);

        // add both panels to home panel
        cardHome.add(labelPanel_Home, BorderLayout.SOUTH);
        cardHome.add(buttonPanel_Home, BorderLayout.CENTER);
        cardHome.setOpaque(false);
        return cardHome;
    }

    // EFFECTS: initialize and return manipulation card
    private JPanel cardManipulation() {
        manipulationPane = new ManipulationPanel(this.delegate);
        return manipulationPane.getManipulationPanel();
    }

    // EFFECTS: initialize and return service card
    private JPanel cardService() {
        servicePanel = new ServicePanel(delegate);
        return servicePanel.getServicePanel();
    }

    /**logistic functions*/
    private void setFrameLocation(){
        frame.setSize(background.getIconWidth(),background.getIconHeight());
        frame.setLocation(dim.width/2,dim.height/2);
        frame.setLocationRelativeTo(null);
    }
    private JLabel setBackgroundImage(){
        JLabel imgLabel = new JLabel(background);
        imgLabel.setBounds(0,0,background.getIconWidth(), background.getIconHeight());
        imgLabel.setOpaque(true);
        return imgLabel;
    }
    private void setButtonAction() {
        JButton mBackBtn = manipulationPane.getHomeMBackBtn();
        mBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards,"cardHome");
            }
        });

        servicebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards,"cardService");
            }
        });

        manipulationbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards,"cardManipulation");
            }
        });

        quitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
