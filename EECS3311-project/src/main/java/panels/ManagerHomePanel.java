package panels;

import project.ManagerAccount;
import project.SmartShoppers;
import project.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerHomePanel extends HomePanel implements ActionListener {

    ManagerAccount account;

    JButton customerButton;

    JPanel currentStorePanel;
    JLabel currentStoreLabel;

    public ManagerHomePanel(ManagerAccount account){
        this.account = account;

        ///////////////////////////////////////////
        customerButton = new JButton();
        customerButton.setFocusable(false);//look at this later...
        customerButton.addActionListener(this);
        customerButton.setBounds(0,0,400,400);
        customerButton.setOpaque(false);
        customerButton.setContentAreaFilled(false);
        customerButton.setBorderPainted(false);

        customerPanel = new JPanel();
        customerPanel.setBackground(new Color(0x695E93));
        customerPanel.setBounds(40,150,400,400);
        customerPanel.setLayout(null);

        customerCountValueLabel = new JLabel();
        customerCountValueLabel.setText(SmartShoppers.getInstance().getNumberOfCustomerAccounts(account.getStore())+"");
        customerCountValueLabel.setFont(new Font("Consolas", Font.PLAIN, 100));
        customerCountValueLabel.setBackground(Color.white);
        customerCountValueLabel.setBounds(0, 0, 400, 200);

        customerCountLabel = new JLabel();
        customerCountLabel.setText("Customers");
        customerCountLabel.setFont(new Font("Consolas", Font.PLAIN, 75));
        customerCountLabel.setBackground(Color.white);
        customerCountLabel.setBounds(0, 200, 400, 200);

        customerPanel.add(customerButton);
        customerPanel.add(customerCountValueLabel);
        customerPanel.add(customerCountLabel);

        ///////////////////////////////////////////
        storePanel = new JPanel();
        storePanel.setBackground(new Color(0x695E93));
        storePanel.setBounds(480,150,400,400);
        storePanel.setLayout(null);

        storeCountLabel = new JLabel();
        storeCountLabel.setText("Store");
        storeCountLabel.setFont(new Font("Consolas", Font.PLAIN, 75));
        storeCountLabel.setBackground(Color.white);
        storeCountLabel.setBounds(0, 200, 400, 200);

        storePanel.add(storeCountLabel);

        ///////////////////////////////////////////
        itemPanel = new JPanel();
        itemPanel.setBackground(new Color(0x695E93));
        itemPanel.setBounds(920,150,400,400);
        itemPanel.setLayout(null);

        itemCountValueLabel = new JLabel();
        itemCountValueLabel.setText(SmartShoppers.getInstance().getNumberOfItems(account.getStore())+"");
        itemCountValueLabel.setFont(new Font("Consolas", Font.PLAIN, 100));
        itemCountValueLabel.setBackground(Color.white);
        itemCountValueLabel.setBounds(0, 0, 400, 200);

        itemCountLabel = new JLabel();
        itemCountLabel.setText("Items");
        itemCountLabel.setFont(new Font("Consolas", Font.PLAIN, 75));
        itemCountLabel.setBackground(Color.white);
        itemCountLabel.setBounds(0, 200, 400, 200);

        itemPanel.add(itemCountValueLabel);
        itemPanel.add(itemCountLabel);

        ///////////////////////////////////////////
        currentStorePanel = new JPanel();
        currentStorePanel.setBackground(new Color(0x695E93));
        currentStorePanel.setBounds(0,1700,2400,100);
        currentStorePanel.setLayout(null);

        currentStoreLabel = new JLabel(account.getStore().getLocation().toString());
        currentStoreLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        currentStoreLabel.setBackground(Color.white);
        currentStoreLabel.setBounds(0, 0, 2400, 100);

        currentStorePanel.add(currentStoreLabel);

        ///////////////////////////////////////////
        this.add(customerPanel);
        this.add(storePanel);
        this.add(itemPanel);
        this.add(currentStorePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
