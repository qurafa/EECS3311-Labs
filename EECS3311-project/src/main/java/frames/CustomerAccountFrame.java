package frames;

import panels.*;
import project.CustomerAccount;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAccountFrame extends AccountFrame implements ActionListener {

    CustomerAccount account;

    public CustomerAccountFrame(CustomerAccount account){
        this.account = account;

        search.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchControl();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchControl();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchControl();
            }
        });

        homeButton.addActionListener(this);

        storesButton = new JButton("Store");
        storesButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        storesButton.setFocusable(false);//look at this later...
        storesButton.addActionListener(this);
        storesButton.setBounds(0, 700, 500, 100);
        storesButton.setBorder(null);
        storesButton.setBackground(Color.white);

        itemsButton = new JButton("Shopping Cart");
        itemsButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemsButton.setFocusable(false);//look at this later...
        itemsButton.addActionListener(this);
        itemsButton.setBounds(0, 900, 500, 100);
        itemsButton.setBorder(null);
        itemsButton.setBackground(Color.white);

        settingsButton.setBounds(0, 1100, 500, 100);
        settingsButton.addActionListener(this);
        settingsButton.updateUI();

        logoutButton.setBounds(0, 1300, 500, 100);
        logoutButton.addActionListener(this);
        logoutButton.updateUI();

        optionPanel.add(storesButton);
        optionPanel.add(itemsButton);

        currentPanel = new CustomerHomePanel(account);

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
            currentPanel = new CustomerHomePanel(account);
            this.add(currentPanel);
            this.repaint();
        }
        else if(e.getSource() == storesButton){
            System.out.println("storesButton");
            this.remove(currentPanel);
            currentPanel = new CustomerStorePanel(account);
            this.add(currentPanel);
            this.repaint();
        }
        else if(e.getSource() == itemsButton){
            System.out.println("itemsButton");
            this.remove(currentPanel);
            currentPanel = new CustomerShoppingCartPanel(account, this);
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
            //new LoginFrame();
        }
    }

    private void searchControl(){
        if(search.getText().equals("")){
            if(!currentPanel.getClass().getName().toLowerCase().contains("customerhomepanel")){
                this.remove(currentPanel);
                currentPanel = new CustomerHomePanel(account);
                this.add(currentPanel);
                this.repaint();
            }
        }
        else{
            this.remove(currentPanel);
            currentPanel = new SearchPanel(search.getText(), account, this);
            this.add(currentPanel);
            this.repaint();
        }
    }

}
