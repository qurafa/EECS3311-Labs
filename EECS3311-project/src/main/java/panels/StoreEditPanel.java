package panels;

import frames.AccountFrame;
import project.Database;
import project.SmartShoppers;
import project.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreEditPanel extends SidePanel implements ActionListener {

    Store store;
    AccountFrame frame;

    JLabel storeAddressLabel;
    JTextField storeAddress;
    JLabel storeCityLabel;
    JTextField storeCity;
    JLabel storeStateLabel;
    JTextField storeState;
    JLabel storePostalCodeLabel;
    JTextField storePostalCode;
    JLabel storeCountryLabel;
    JTextField storeCountry;
    JLabel storeOpenHourLabel;
    JTextField storeOpenHour;
    JLabel storeCloseHourLabel;
    JTextField storeCloseHour;

    JButton updateStoreButton;
    JButton removeStoreButton;
    JButton closeButton;

    public StoreEditPanel(Store store, AccountFrame frame){
        super("Edit Store Information",100, 100, 2000, 200);

        this.store = store;
        this.frame = frame;

        this.setBackground(Color.white);
        this.setBounds(500,100,2400,1900);
        this.setLayout(null);

        storeAddressLabel = new JLabel("Address");
        storeAddressLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        storeAddressLabel.setBackground(Color.white);
        storeAddressLabel.setBounds(100, 400, 500, 100);

        storeAddress = new JTextField();
        storeAddress.setText(store.getLocation().getAddress());
        storeAddress.setPreferredSize(new Dimension(250,100));
        storeAddress.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeAddress.setForeground(new Color(0x00FF00));
        storeAddress.setBackground(Color.black);
        storeAddress.setCaretColor(Color.white);
        storeAddress.setBounds(100, 500, 500, 100);

        storeCityLabel = new JLabel("City");
        storeCityLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        storeCityLabel.setBackground(Color.white);
        storeCityLabel.setBounds(700, 400, 500, 100);

        storeCity = new JTextField();
        storeCity.setText(store.getLocation().getCity());
        storeCity.setPreferredSize(new Dimension(250,100));
        storeCity.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeCity.setForeground(new Color(0x00FF00));
        storeCity.setBackground(Color.black);
        storeCity.setCaretColor(Color.white);
        storeCity.setBounds(700, 500, 500, 100);

        storeStateLabel = new JLabel("State");
        storeStateLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        storeStateLabel.setBackground(Color.white);
        storeStateLabel.setBounds(100, 700, 500, 100);

        storeState = new JTextField();
        storeState.setText(store.getLocation().getState());
        storeState.setPreferredSize(new Dimension(250,100));
        storeState.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeState.setForeground(new Color(0x00FF00));
        storeState.setBackground(Color.black);
        storeState.setCaretColor(Color.white);
        storeState.setBounds(100, 800, 500, 100);

        storePostalCodeLabel = new JLabel("Postal Code");
        storePostalCodeLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        storePostalCodeLabel.setBackground(Color.white);
        storePostalCodeLabel.setBounds(700, 700, 500, 100);

        storePostalCode = new JTextField();
        storePostalCode.setText(store.getLocation().getPostalCode());
        storePostalCode.setPreferredSize(new Dimension(250,100));
        storePostalCode.setFont(new Font("Consolas", Font.PLAIN, 35));
        storePostalCode.setForeground(new Color(0x00FF00));
        storePostalCode.setBackground(Color.black);
        storePostalCode.setCaretColor(Color.white);
        storePostalCode.setBounds(700, 800, 500, 100);

        storeCountryLabel = new JLabel("Country");
        storeCountryLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        storeCountryLabel.setBackground(Color.white);
        storeCountryLabel.setBounds(100, 1000, 500, 100);

        storeCountry = new JTextField();
        storeCountry.setText(store.getLocation().getCountry());
        storeCountry.setPreferredSize(new Dimension(250,100));
        storeCountry.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeCountry.setForeground(new Color(0x00FF00));
        storeCountry.setBackground(Color.black);
        storeCountry.setCaretColor(Color.white);
        storeCountry.setBounds(100, 1100, 500, 100);

        storeOpenHourLabel = new JLabel("Open Hour");
        storeOpenHourLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        storeOpenHourLabel.setBackground(Color.white);
        storeOpenHourLabel.setBounds(100, 1300, 500, 100);

        storeOpenHour = new JTextField();
        storeOpenHour.setText(store.getOpenHour());
        storeOpenHour.setPreferredSize(new Dimension(250,100));
        storeOpenHour.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeOpenHour.setForeground(new Color(0x00FF00));
        storeOpenHour.setBackground(Color.black);
        storeOpenHour.setCaretColor(Color.white);
        storeOpenHour.setBounds(100, 1400, 500, 100);

        storeCloseHourLabel = new JLabel("Close Hour");
        storeCloseHourLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        storeCloseHourLabel.setBackground(Color.white);
        storeCloseHourLabel.setBounds(700, 1300, 500, 100);

        storeCloseHour = new JTextField();
        storeCloseHour.setText(store.getCloseHour());
        storeCloseHour.setPreferredSize(new Dimension(250,100));
        storeCloseHour.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeCloseHour.setForeground(new Color(0x00FF00));
        storeCloseHour.setBackground(Color.black);
        storeCloseHour.setCaretColor(Color.white);
        storeCloseHour.setBounds(700, 1400, 500, 100);

        updateStoreButton = new JButton("Update");
        updateStoreButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        updateStoreButton.setFocusable(false);//look at this later...
        updateStoreButton.setBounds(100, 1600, 500, 100);
        updateStoreButton.setBorder(null);
        updateStoreButton.setBackground(new Color(0x695E93));
        updateStoreButton.addActionListener(this);

        removeStoreButton =  new JButton("Remove");
        removeStoreButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        removeStoreButton.setFocusable(false);//look at this later...
        removeStoreButton.setBounds(700, 1600, 500, 100);
        removeStoreButton.setBorder(null);
        removeStoreButton.setBackground(new Color(0x695E93));
        removeStoreButton.addActionListener(this);

        closeButton =  new JButton("Close");
        closeButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        closeButton.setFocusable(false);//look at this later...
        closeButton.setBounds(1300, 1600, 500, 100);
        closeButton.setBorder(null);
        closeButton.setBackground(new Color(0x695E93));
        closeButton.addActionListener(this);

        this.add(storeAddressLabel);
        this.add(storeAddress);
        this.add(storeCityLabel);
        this.add(storeCity);
        this.add(storeStateLabel);
        this.add(storeState);
        this.add(storePostalCodeLabel);
        this.add(storePostalCode);
        this.add(storeCountryLabel);
        this.add(storeCountry);
        this.add(storeOpenHourLabel);
        this.add(storeOpenHour);
        this.add(storeCloseHourLabel);
        this.add(storeCloseHour);
        this.add(storeCloseHour);
        this.add(updateStoreButton);
        this.add(removeStoreButton);
        this.add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateStoreButton){
            System.out.println("Update");

            if(infoChange() &&
                    JOptionPane.showConfirmDialog(this, "Update Information?","", JOptionPane.YES_NO_OPTION) == 0){
                Database.updateStoreInformation(store, storeAddress.getText(),
                        storeCity.getText(), storeState.getText(), storePostalCode.getText(),
                        storeCountry.getText(), storeOpenHour.getText(), storeCloseHour.getText());
                store.setAddress(storeAddress.getText());
                store.setCity(storeCity.getText());
                store.setState(storeState.getText());
                store.setPostalCode(storePostalCode.getText());
                store.setCountry(storeCountry.getText());
                store.setOpenHour(storeOpenHour.getText());
                store.setCloseHour(storeCloseHour.getText());
            }

            closePanel();
        }
        else if(e.getSource() == removeStoreButton &&
                JOptionPane.showConfirmDialog(this, "Are You Sure??","", JOptionPane.YES_NO_OPTION) == 0){
            System.out.println("Remove");
            SmartShoppers.getInstance().deleteStore(store);
            Database.deleteStore(store.getID());
            closePanel();
        }
        else if(e.getSource() == closeButton){
            System.out.println("Close");
            closePanel();
        }
    }

    private boolean infoChange(){
        return addressChange() || cityChange() || stateChange() || postalCodeChange() || countryChange() || openHourChange() || closeHourChange();
    }

    private boolean addressChange(){
        return !store.getLocation().getAddress().equals(storeAddress.getText().toString());
    }

    private boolean cityChange(){
        return !store.getLocation().getCity().equals(storeCity.getText().toString());
    }

    private boolean stateChange(){
        return !store.getLocation().getState().equals(storeState.getText().toString());
    }

    private boolean postalCodeChange(){
        return !store.getLocation().getPostalCode().equals(storePostalCode.getText().toString());
    }

    private boolean countryChange(){
        return !store.getLocation().getCountry().equals(storeCountry.getText().toString());
    }

    private boolean openHourChange(){
        return !store.getOpenHour().equals(storeOpenHour.getText().toString());
    }

    private boolean closeHourChange(){
        return !store.getCloseHour().equals(storeCloseHour.getText().toString());
    }

    private void closePanel(){
        frame.remove(frame.getCurrentPanel());
        frame.setCurrentPanel(new AdminStoresPanel(frame));
        frame.add(frame.getCurrentPanel());
        frame.repaint();
    }
}
