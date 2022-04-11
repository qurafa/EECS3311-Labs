package frames;

import project.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectLocationFrame extends JFrame implements ActionListener {

    Account account;
    int accountType;

    JFrame from;

    JComboBox storeComboBox;

    JButton backButton;
    JButton doneButton;

    //TODO add observer for when the store is updated or another store is added or removed

    public SelectLocationFrame(Account account){
        this.account = account;
        System.out.println("Name!!!!! " + account.getClass().getName());
        accountType = (account.getClass().getName().contains("Customer")) ? 0 :
                (account.getClass().getName().contains("Manager")) ? 1 : -1;

        if(accountType == 0)account = (CustomerAccount) account;
        if(accountType == 1)account = (ManagerAccount) account;

        if(accountType == 0 || accountType == 1){
            System.out.println("Start Select Location Frame");

            JLabel selectStoreLabel = new JLabel();
            selectStoreLabel.setText(accountType == 0 ?
                    "Select Preferred Shopping Location" :
                    "Select Assigned Store Location");
            selectStoreLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
            selectStoreLabel.setBounds(175, 125, 650, 100);

            List<String> storesString = new ArrayList<String>();
            for (Store s : SmartShoppers.getInstance().getStores()){
                storesString.add(s.getLocation().toString());
            }

            storeComboBox = new JComboBox(storesString.toArray());
            storeComboBox.setBounds(175,250,650,100);

            doneButton = new JButton("Done >");
            doneButton.setFont(new Font("Consolas", Font.PLAIN, 35));
            doneButton.setFocusable(true);//look at this later...
            doneButton.addActionListener(this);
            doneButton.setBounds(425, 450, 150, 50);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(new Dimension(1000, 1000));
            this.setResizable(false);
            this.setVisible(true);
            this.add(selectStoreLabel);
            this.add(storeComboBox);
            this.add(doneButton);

            System.out.println("Done Select Location Frame");
        }
        else{
            System.out.println("Incorrect accountType SelectLocationFrame");
        }
    }

    public SelectLocationFrame(Account account, JFrame from){
        this(account);
        this.from = from;

        backButton = new JButton("< Back");
        backButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        backButton.setFocusable(true);//look at this later...
        backButton.addActionListener(this);
        backButton.setBounds(25, 825, 150, 50);
        backButton.setBorder(null);
        backButton.setBackground(Color.white);

        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == doneButton && storeComboBox.getSelectedItem() != null){
            account.setStore(SmartShoppers.getInstance().getStore(storeComboBox.getSelectedItem().toString()));
            Database.setAccountStore(account.getID(), account.getStore());

            if(accountType == 1)
                new ManagerAccountFrame((ManagerAccount) account);
            else if(accountType == 0)
                new CustomerAccountFrame((CustomerAccount) account);

            this.dispose();
        }

        if(e.getSource() == backButton){
            this.setVisible(false);
            from.setVisible(true);
        }
    }
}
