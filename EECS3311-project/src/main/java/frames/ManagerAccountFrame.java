package frames;

import panels.*;
import project.ManagerAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerAccountFrame extends AccountFrame implements ActionListener {

    ManagerAccount account;

    public ManagerAccountFrame(ManagerAccount account){
        super();
        this.account = account;

        customersButton = new JButton("Customers");
        customersButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        customersButton.setFocusable(false);//look at this later...
        customersButton.addActionListener(this);
        customersButton.setBounds(0, 700, 500, 100);
        customersButton.setBorder(null);
        customersButton.setBackground(Color.white);

        storesButton = new JButton("Store");
        storesButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        storesButton.setFocusable(false);//look at this later...
        storesButton.addActionListener(this);
        storesButton.setBounds(0, 900, 500, 100);
        storesButton.setBorder(null);
        storesButton.setBackground(Color.white);

        itemsButton = new JButton("Items");
        itemsButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemsButton.setFocusable(false);//look at this later...
        itemsButton.addActionListener(this);
        itemsButton.setBounds(0, 1100, 500, 100);
        itemsButton.setBorder(null);
        itemsButton.setBackground(Color.white);

        settingsButton.setBounds(0, 1300, 500, 100);
        settingsButton.updateUI();

        logoutButton.setBounds(0, 1500, 500, 100);
        logoutButton.updateUI();

        optionPanel.add(customersButton);
        optionPanel.add(storesButton);
        optionPanel.add(itemsButton);

        currentPanel = new ManagerHomePanel(account);

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
            switchPanel(new ManagerHomePanel(account));
        }
        else if(e.getSource() == customersButton){
            System.out.println("customerButton");
            switchPanel(new ManagerCustomersPanel(account));
        }
        else if(e.getSource() == storesButton){
            System.out.println("storesButton");
            switchPanel(new ManagerStoresPanel(account));
        }
        else if(e.getSource() == itemsButton){
            System.out.println("itemsButton");
            switchPanel(new ManagerItemsPanel(account, this));
        }
        else if(e.getSource() == settingsButton){
            System.out.println("settingsButton");
            switchPanel(new SettingsPanel(account, this));
        }
        else if(e.getSource() == logoutButton){
            System.out.println("logoutButton");
            this.dispose();
            //new LoginFrame();
        }
    }

    private void switchPanel(JPanel to){
        this.remove(currentPanel);
        currentPanel = to;
        this.add(currentPanel);
        this.repaint();
    }
}
