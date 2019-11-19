package ca.ubc.cs304.ui;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainUI {
    private ImageIcon background = new ImageIcon("src\\bkgImg.jpg");
    private JFrame frame = new JFrame("super rent");
    private Dimension dim = frame.getToolkit().getScreenSize();
    private JPanel contentPane = (JPanel) frame.getContentPane();
    private CardLayout cardLayout;
    private JPanel cards;

    // buttons on the main page
    private JButton service = new JButton("Services");
    private JButton manipulation = new JButton("Manipulations");
    private JButton quit = new JButton("Quit");
    private JButton play = new JButton ();
    private boolean isPlay;
    private InputStream music;
    private AudioStream audios;
    private Font myFont = new Font("Serif", Font.BOLD, 40);

    //initialize 'customer' card
    private JPanel customer = new JPanel();

    //initialize 'clerk' card
    private JPanel clerk = new JPanel();

    public MainUI(){
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
        cards.add("cardService",cardService());
        cards.add("cardCustomer",cardManipulation());

        cardLayout.show(cards,"cardHome");
        contentPane.add(cards,BorderLayout.CENTER);

        // set buttons
        setButtonAction();
        // set background image
        JLayeredPane layeredPane = frame.getLayeredPane();
        layeredPane.add(setBackgroundImage(),new Integer(Integer.MIN_VALUE));
        layeredPane.setOpaque(true);
    }

    private JPanel cardHome() {
        JPanel cardHome = new JPanel();
        JPanel labelPanel_Home = new JPanel();
        labelPanel_Home.setOpaque(false);
        JLabel label_Home = new JLabel("what do you want to do with Super Rent?");
        label_Home.setBorder(BorderFactory.createEmptyBorder(30,20,10,20));
        labelPanel_Home.add(label_Home);
        JPanel buttonPanel_Home = new JPanel();
        buttonPanel_Home.setBorder(BorderFactory.createEmptyBorder(80,20,50,20));
        label_Home.setFont(myFont);
        buttonPanel_Home.setOpaque(false);

        service.setPreferredSize(new Dimension(120,50));
        buttonPanel_Home.add(service);

        manipulation.setPreferredSize(new Dimension(120,50));
        buttonPanel_Home.add(manipulation);

        play.setPreferredSize(new Dimension(120,50));
        play.setText("Music");
        buttonPanel_Home.add(play);
        isPlay = false;

        quit.setPreferredSize(new Dimension(120,50));
        buttonPanel_Home.add(quit);

        cardHome.add(labelPanel_Home,BorderLayout.NORTH);
        cardHome.add(buttonPanel_Home,BorderLayout.CENTER);
        cardHome.setOpaque(false);

        return  cardHome;
    }


    private JPanel cardService() {
        JPanel cardService = new JPanel();
        return cardService;
    }


    private JPanel cardManipulation() {
        JPanel cardManipulation = new JPanel();
        return cardManipulation;
    }


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
        service.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards,"cardService");
            }
        });

        manipulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards,"cardManipulation");
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        try {
            music = new FileInputStream(new File("src\\bkgMusic.wav"));
            audios = new AudioStream(music);
        }catch(IOException e1){
            JOptionPane.showMessageDialog(null,"error");
        }

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!isPlay) {
                        isPlay = true;
                        play.setText("sounds on");
                        System.out.println("isPlay state = " + isPlay);
                        AudioPlayer.player.start(audios);
                    }else if (isPlay) {
                        isPlay = false;
                        play.setText("sounds off");
                        System.out.println("isPlay state = " + isPlay);
                        AudioPlayer.player.stop(audios);
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "error");
                }
            }
        });

    }

}
