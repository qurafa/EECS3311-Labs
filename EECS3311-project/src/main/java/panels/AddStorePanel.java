package panels;

import frames.AccountFrame;
import project.SmartShoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStorePanel extends JPanel implements ActionListener {

    JTextField storeAddress;
    JTextField storeCity;
    JTextField storeState;
    JTextField storePostalCode;
    JTextField storeCountry;
    JTextField storeOpenHour;
    JTextField storeCloseHour;

    JButton addStoreButton;
    JButton closeButton;

    AccountFrame frame;

    public AddStorePanel(AccountFrame frame){
        this.frame = frame;

        this.setBackground(Color.white);
        this.setBounds(500,100,2400,1900);
        this.setLayout(null);

        JLabel addStore = new JLabel("Add Store");
        addStore.setFont(new Font("Consolas", Font.PLAIN, 50));
        addStore.setBackground(Color.white);
        addStore.setBounds(20, 20, 500, 100);

        JLabel address = new JLabel("Address");
        address.setFont(new Font("Consolas", Font.PLAIN, 50));
        address.setBackground(Color.white);
        address.setBounds(20, 130, 500, 100);

        storeAddress = new JTextField();
        storeAddress.setPreferredSize(new Dimension(250,100));
        storeAddress.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeAddress.setForeground(new Color(0x00FF00));
        storeAddress.setBackground(Color.black);
        storeAddress.setCaretColor(Color.white);
        storeAddress.setBounds(20, 240, 500, 100);

        JLabel city = new JLabel("City");
        city.setFont(new Font("Consolas", Font.PLAIN, 50));
        city.setBackground(Color.white);
        city.setBounds(20, 350, 500, 100);

        storeCity = new JTextField();
        storeCity.setPreferredSize(new Dimension(250,100));
        storeCity.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeCity.setForeground(new Color(0x00FF00));
        storeCity.setBackground(Color.black);
        storeCity.setCaretColor(Color.white);
        storeCity.setBounds(20, 460, 500, 100);

        JLabel state = new JLabel("State");
        state.setFont(new Font("Consolas", Font.PLAIN, 50));
        state.setBackground(Color.white);
        state.setBounds(20, 570, 500, 100);

        storeState = new JTextField();
        storeState.setPreferredSize(new Dimension(250,100));
        storeState.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeState.setForeground(new Color(0x00FF00));
        storeState.setBackground(Color.black);
        storeState.setCaretColor(Color.white);
        storeState.setBounds(20, 680, 500, 100);

        JLabel postalCode = new JLabel("Postal Code");
        postalCode.setFont(new Font("Consolas", Font.PLAIN, 50));
        postalCode.setBackground(Color.white);
        postalCode.setBounds(20, 790, 500, 100);

        storePostalCode = new JTextField();
        storePostalCode.setPreferredSize(new Dimension(250,100));
        storePostalCode.setFont(new Font("Consolas", Font.PLAIN, 35));
        storePostalCode.setForeground(new Color(0x00FF00));
        storePostalCode.setBackground(Color.black);
        storePostalCode.setCaretColor(Color.white);
        storePostalCode.setBounds(20, 900, 500, 100);

        JLabel country = new JLabel("Country");
        country.setFont(new Font("Consolas", Font.PLAIN, 50));
        country.setBackground(Color.white);
        country.setBounds(20, 1010, 500, 100);

        storeCountry = new JTextField();
        storeCountry.setPreferredSize(new Dimension(250,100));
        storeCountry.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeCountry.setForeground(new Color(0x00FF00));
        storeCountry.setBackground(Color.black);
        storeCountry.setCaretColor(Color.white);
        storeCountry.setBounds(20, 1120, 500, 100);

        JLabel openHour = new JLabel("Open Hour");
        openHour.setFont(new Font("Consolas", Font.PLAIN, 50));
        openHour.setBackground(Color.white);
        openHour.setBounds(20, 1230, 500, 100);

        storeOpenHour = new JTextField();
        storeOpenHour.setPreferredSize(new Dimension(250,100));
        storeOpenHour.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeOpenHour.setForeground(new Color(0x00FF00));
        storeOpenHour.setBackground(Color.black);
        storeOpenHour.setCaretColor(Color.white);
        storeOpenHour.setBounds(20, 1340, 500, 100);

        JLabel closeHour = new JLabel("Close Hour");
        closeHour.setFont(new Font("Consolas", Font.PLAIN, 50));
        closeHour.setBackground(Color.white);
        closeHour.setBounds(20, 1450, 500, 100);

        storeCloseHour = new JTextField();
        storeCloseHour.setPreferredSize(new Dimension(250,100));
        storeCloseHour.setFont(new Font("Consolas", Font.PLAIN, 35));
        storeCloseHour.setForeground(new Color(0x00FF00));
        storeCloseHour.setBackground(Color.black);
        storeCloseHour.setCaretColor(Color.white);
        storeCloseHour.setBounds(20, 1560, 500, 100);

        addStoreButton = new JButton("Add Store");
        addStoreButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        addStoreButton.setFocusable(false);//look at this later...
        addStoreButton.addActionListener(this);
        addStoreButton.setBounds(0, 1670, 500, 100);
        addStoreButton.setBorder(null);
        addStoreButton.setBackground(new Color(0x695E93));

        closeButton =  new JButton("Close");
        closeButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        closeButton.setFocusable(false);//look at this later...
        closeButton.addActionListener(this);
        closeButton.setBounds(600, 1670, 500, 100);
        closeButton.setBorder(null);
        closeButton.setBackground(new Color(0x695E93));

        this.add(addStore);
        this.add(address);
        this.add(storeAddress);
        this.add(city);
        this.add(storeCity);
        this.add(state);
        this.add(storeState);
        this.add(postalCode);
        this.add(storePostalCode);
        this.add(country);
        this.add(storeCountry);
        this.add(openHour);
        this.add(storeOpenHour);
        this.add(closeHour);
        this.add(storeCloseHour);
        this.add(addStoreButton);
        this.add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addStoreButton){
            SmartShoppers.getInstance().createStore(storeAddress.getText(), storeCity.getText(), storeState.getText(),
                    storePostalCode.getText(), storeCountry.getText(), storeOpenHour.getText(), storeCloseHour.getText());
            frame.remove(frame.getCurrentPanel());
            frame.setCurrentPanel(new AdminStoresPanel(frame));
            frame.add(frame.getCurrentPanel());
            frame.repaint();
        }
        else if(e.getSource() == closeButton){
            frame.remove(frame.getCurrentPanel());
            frame.setCurrentPanel(new AdminStoresPanel(frame));
            frame.add(frame.getCurrentPanel());
            frame.repaint();
        }
    }
}
