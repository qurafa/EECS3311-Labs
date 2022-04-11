package panels;

import project.Database;
import project.ManagerAccount;
import project.SmartShoppers;
import project.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class ManagerStoresPanel extends SidePanel implements ActionListener {

    ManagerAccount account;

    JLabel storeAddressLabel;
    JComboBox storeAddressComboBox;
    JLabel openHourLabel;
    JTextField openHour;
    JLabel closeHourLabel;
    JTextField closeHour;

    JButton updateButton;

    public ManagerStoresPanel(ManagerAccount account){
        super("Store");
        this.account = account;

        storeAddressLabel = new JLabel("Store Address");
        storeAddressLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        storeAddressLabel.setBackground(Color.white);
        storeAddressLabel.setBounds(100, 400, 500, 100);

        List<String> storesString = new ArrayList<String>();
        for(Store s : SmartShoppers.getInstance().getStores()){
            storesString.add(s.getLocation().toString());
        }

        storeAddressComboBox = new JComboBox(storesString.toArray());
        storeAddressComboBox.setFont(new Font("Consolas", Font.PLAIN, 50));
        storeAddressComboBox.setSize(2000, 100);
        storeAddressComboBox.setSelectedItem(account.getStore().getLocation().toString());
        storeAddressComboBox.setBounds(100, 500, 2000, 100);

        openHourLabel = new JLabel("Open Hour");
        openHourLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        openHourLabel.setBackground(Color.white);
        openHourLabel.setBounds(100, 700, 500, 100);

        openHour = new JTextField();
        openHour.setEditable(true);
        openHour.setText(account.getStore().getOpenHour());
        openHour.setPreferredSize(new Dimension(250,100));
        openHour.setFont(new Font("Consolas", Font.PLAIN, 35));
        openHour.setForeground(new Color(0x00FF00));
        openHour.setBackground(Color.black);
        openHour.setBounds(100, 800, 500, 100);

        closeHourLabel = new JLabel("Close Hour");
        closeHourLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        closeHourLabel.setBackground(Color.white);
        closeHourLabel.setBounds(100, 1000, 500, 100);

        closeHour = new JTextField();
        closeHour.setEditable(true);
        closeHour.setText(account.getStore().getCloseHour());
        closeHour.setPreferredSize(new Dimension(250,100));
        closeHour.setFont(new Font("Consolas", Font.PLAIN, 35));
        closeHour.setForeground(new Color(0x00FF00));
        closeHour.setBackground(Color.black);
        closeHour.setBounds(100, 1100, 500, 100);

        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        updateButton.setFocusable(false);//look at this later...
        updateButton.addActionListener((ActionListener) this);
        updateButton.setBounds(100, 1300, 500, 100);
        updateButton.setBorder(null);
        updateButton.setBackground(new Color(0x695E93));

        storeAddressComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                openHour.setText(SmartShoppers.getInstance().getStore(storeAddressComboBox.getSelectedItem().toString()).getOpenHour());
                closeHour.setText(SmartShoppers.getInstance().getStore(storeAddressComboBox.getSelectedItem().toString()).getCloseHour());
            }
        });

        this.add(storeAddressLabel);
        this.add(storeAddressComboBox);
        this.add(openHourLabel);
        this.add(openHour);
        this.add(closeHourLabel);
        this.add(closeHour);
        this.add(updateButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateButton &&
                storeAddressComboBox.getSelectedItem().toString().equals(account.getStore().getLocation().toString()) &&
                JOptionPane.showConfirmDialog(this, "Update Information?","", JOptionPane.YES_NO_OPTION) == 0){
            if(openHourChange() || closeHourChange()){
                System.out.println("Open Hour Change");
                Database.updateStoreInformation(account.getStore(), account.getStore().getLocation().getAddress(), account.getStore().getLocation().getCity(),
                        account.getStore().getLocation().getState(), account.getStore().getLocation().getPostalCode(), account.getStore().getLocation().getCountry(),
                        openHour.getText(), closeHour.getText());
                account.getStore().setOpenHour(openHour.getText());
                account.getStore().setCloseHour(closeHour.getText());
            }
        }
        else if(e.getSource() == updateButton &&
                !storeAddressComboBox.getSelectedItem().toString().equals(account.getStore().getLocation().toString())){
            JOptionPane.showMessageDialog(this, "Cannot Modify Hours of other Stores!!");
        }

    }

    private boolean openHourChange(){
        return !(account.getStore().getOpenHour()+"").equals(openHour.getText());
    }

    private boolean closeHourChange(){
        return !(account.getStore().getCloseHour()+"").equals(closeHour.getText());
    }

}
