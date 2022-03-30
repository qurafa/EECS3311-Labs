package frames;

import javafx.scene.control.ScrollPane;
import panels.HomePanel;
import project.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class AdminHomePageFrame extends JFrame implements ActionListener {

    JTextField search;

    JPanel searchPanel;
    JPanel homePanel;
    JPanel customersPanel;
    JPanel managersPanel;
    JPanel storesPanel;
    JPanel itemsPanel;
    JPanel settingsPanel;

    JButton homeButton;
    JButton customersButton;
    JButton managersButton;
    JButton storesButton;
    JButton itemsButton;
    JButton settingsButton;
    JButton logoutButton;

    public AdminHomePageFrame(){
        search = new JTextField();
        search.setPreferredSize(new Dimension(250,40));
        search.setFont(new Font("Consolas", Font.PLAIN, 35));
        search.setForeground(Color.white);
        search.setBackground(Color.black);
        search.setCaretColor(new Color(0x00FF00));
        search.setBounds(500, 0, 2500, 100);

        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(new Color(0x57335B));
        optionPanel.setBounds(0,0,500,2000);
        optionPanel.setLayout(null);

        homeButton = new JButton("Home");
        homeButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        homeButton.setFocusable(false);//look at this later...
        homeButton.addActionListener(this);
        homeButton.setBounds(0, 500, 500, 100);
        homeButton.setBorder(null);
        homeButton.setBackground(Color.white);

        customersButton = new JButton("Customers");
        customersButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        customersButton.setFocusable(false);//look at this later...
        customersButton.addActionListener(this);
        customersButton.setBounds(0, 700, 500, 100);
        customersButton.setBorder(null);
        customersButton.setBackground(Color.white);

        managersButton = new JButton("Managers");
        managersButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        managersButton.setFocusable(false);//look at this later...
        managersButton.addActionListener(this);
        managersButton.setBounds(0, 900, 500, 100);
        managersButton.setBorder(null);
        managersButton.setBackground(Color.white);

        storesButton = new JButton("Stores");
        storesButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        storesButton.setFocusable(false);//look at this later...
        storesButton.addActionListener(this);
        storesButton.setBounds(0, 1100, 500, 100);
        storesButton.setBorder(null);
        storesButton.setBackground(Color.white);

        itemsButton = new JButton("Items");
        itemsButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemsButton.setFocusable(false);//look at this later...
        itemsButton.addActionListener(this);
        itemsButton.setBounds(0, 1300, 500, 100);
        itemsButton.setBorder(null);
        itemsButton.setBackground(Color.white);

        settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        settingsButton.setFocusable(false);//look at this later...
        settingsButton.addActionListener(this);
        settingsButton.setBounds(0, 1500, 500, 100);
        settingsButton.setBorder(null);
        settingsButton.setBackground(Color.white);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        logoutButton.setFocusable(false);//look at this later...
        logoutButton.addActionListener(this);
        logoutButton.setBounds(0, 1700, 500, 100);
        logoutButton.setBorder(null);
        logoutButton.setBackground(Color.white);

        optionPanel.add(homeButton);
        optionPanel.add(customersButton);
        optionPanel.add(managersButton);
        optionPanel.add(storesButton);
        optionPanel.add(itemsButton);
        optionPanel.add(settingsButton);
        optionPanel.add(logoutButton);

//        homePanel = new JPanel();
//        customersPanel = new JPanel();
//        managersPanel = new JPanel();
//        storesPanel = new JPanel();
//        itemsPanel = new JPanel();
//        settingsPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(3000, 2000));
        this.setResizable(false);
        this.setVisible(true);
        this.add(search);
        this.add(optionPanel);
        HomePanel h = new HomePanel(2);
        this.add(new HomePanel(2));
    }

    public AdminHomePageFrame(Account a){
        this();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void loadHomePanel(){

    }

    private void unloadHomePanel(){}

    private class MyAdjustmentListener implements AdjustmentListener{
        JFrame frame;

        MyAdjustmentListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            frame.repaint();
        }
    }
}
