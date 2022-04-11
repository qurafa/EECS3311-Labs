package frames;

import panels.*;
import project.AdminAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminAccountFrame extends AccountFrame implements ActionListener {

    AdminAccount account;

    public AdminAccountFrame(AdminAccount account){
        this.account = account;

        homeButton.addActionListener(this);

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

        settingsButton.setBounds(0, 1500, 500, 100);
        settingsButton.addActionListener(this);
        settingsButton.updateUI();

        logoutButton.setBounds(0, 1700, 500, 100);
        logoutButton.addActionListener(this);
        logoutButton.updateUI();

        optionPanel.add(customersButton);
        optionPanel.add(managersButton);
        optionPanel.add(storesButton);
        optionPanel.add(itemsButton);

        currentPanel = new AdminHomePanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(3000, 2000));
        this.setResizable(false);
        this.setVisible(true);
        this.add(search);
        this.add(optionPanel);
        this.add(currentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == homeButton){
            System.out.println("homeButton");
            this.remove(currentPanel);
            currentPanel = new AdminHomePanel();
            this.add(currentPanel);
            this.repaint();
        }
        else if(e.getSource() == customersButton){
            System.out.println("customerButton");
            this.remove(currentPanel);
            currentPanel = new AdminCustomersPanel();
            this.add(currentPanel);
            this.repaint();
        }
        else if(e.getSource() == managersButton){
            System.out.println("managerButton");
            this.remove(currentPanel);
            currentPanel = new AdminManagersPanel();
            this.add(currentPanel);
            this.repaint();
        }
        else if(e.getSource() == storesButton){
            System.out.println("storesButton");
            this.remove(currentPanel);
            currentPanel = new AdminStoresPanel(this);
            this.add(currentPanel);
            this.repaint();
        }
        else if(e.getSource() == itemsButton){
            System.out.println("itemsButton");
            this.remove(currentPanel);
            currentPanel = new AdminItemsPanel(this);
            this.add(currentPanel);
            this.repaint();
        }
        else if(e.getSource() == settingsButton){
            System.out.println("settingsButton");
            this.remove(currentPanel);
            currentPanel = new SettingsPanel(account, this);
            this.add(currentPanel);
            this.repaint();
        }
        else if(e.getSource() == logoutButton){
            System.out.println("logoutButton");
            this.dispose();
        }
    }
}
