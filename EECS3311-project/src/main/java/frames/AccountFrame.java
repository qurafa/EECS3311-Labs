package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFrame extends JFrame{

    JTextField search;

    JPanel optionPanel;
    JPanel currentPanel;

    JButton homeButton;
    JButton customersButton;
    JButton managersButton;
    JButton storesButton;
    JButton itemsButton;
    JButton settingsButton;
    JButton logoutButton;

    public AccountFrame() {
        search = new JTextField();
        search.setPreferredSize(new Dimension(250,40));
        search.setFont(new Font("Consolas", Font.PLAIN, 35));
        search.setForeground(Color.white);
        search.setBackground(Color.black);
        search.setCaretColor(new Color(0x00FF00));
        search.setBounds(500, 0, 2500, 100);

        optionPanel = new JPanel();
        optionPanel.setBackground(new Color(0x57335B));
        optionPanel.setBounds(0,0,500,2000);
        optionPanel.setLayout(null);

        homeButton = new JButton("Home");
        homeButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        homeButton.setFocusable(false);//look at this later...
        homeButton.addActionListener((ActionListener) this);
        homeButton.setBounds(0, 500, 500, 100);
        homeButton.setBorder(null);
        homeButton.setBackground(Color.white);

        settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        settingsButton.setFocusable(false);//look at this later...
        settingsButton.addActionListener((ActionListener) this);
        settingsButton.setBounds(0, 700, 500, 100);
        settingsButton.setBorder(null);
        settingsButton.setBackground(Color.white);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        logoutButton.setFocusable(false);//look at this later...
        logoutButton.addActionListener((ActionListener) this);
        logoutButton.setBounds(0, 900, 500, 100);
        logoutButton.setBorder(null);
        logoutButton.setBackground(Color.white);

        optionPanel.add(homeButton);
        optionPanel.add(settingsButton);
        optionPanel.add(logoutButton);
    }

    public JPanel getCurrentPanel(){
        return currentPanel;
    }

    public void setCurrentPanel(JPanel current){
        this.currentPanel = current;
    }
}
