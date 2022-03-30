package panels;

import project.SmartShoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel implements ActionListener {
    //todo add the observer for when the values change...

    JLabel customerCountValueLabel;
    JLabel customerCountLabel;
    JLabel managerCountValueLabel;
    JLabel managerCountLabel;
    JLabel adminCountValueLabel;
    JLabel adminCountLabel;
    JLabel storeCountValueLabel;
    JLabel storeCountLabel;
    JLabel itemCountValueLabel;
    JLabel itemCountLabel;

    //Admin Home panel
    JPanel customerPanel;
    JPanel managerPanel;
    JPanel adminPanel;
    JPanel storePanel;
    JPanel itemPanel;

    public HomePanel(int type) {

        if(type == 0){
            setUpCustomer();
        }
        else if(type == 1){
            setUpManager();
        }
        else if(type == 2){
            setUpAdmin();
        }
        else{
            System.out.println("Wrong type, HomePanel.java");
        }
    }

    public HomePanel(int type, int x, int y, int width, int height) {
        this(type);
        this.setBounds(x,y,width,height);
        this.updateUI();
    }

    private void setUpAdmin(){
        this.setBackground(Color.white);
        this.setBounds(500,100,2400,1900);
        this.setLayout(null);

        ///////////////////////////////////////////
        customerPanel = new JPanel();
        customerPanel.setBackground(new Color(0x695E93));
        customerPanel.setBounds(40,150,400,400);
        customerPanel.setLayout(null);

        customerCountValueLabel = new JLabel();
        customerCountValueLabel.setText(SmartShoppers.getInstance().getNumberOfCustomerAccounts()+"");
        customerCountValueLabel.setFont(new Font("Consolas", Font.PLAIN, 100));
        customerCountValueLabel.setBackground(Color.white);
        customerCountValueLabel.setBounds(0, 0, 400, 200);

        customerCountLabel = new JLabel();
        customerCountLabel.setText("Customers");
        customerCountLabel.setFont(new Font("Consolas", Font.PLAIN, 75));
        customerCountLabel.setBackground(Color.white);
        customerCountLabel.setBounds(0, 200, 400, 200);

        customerPanel.add(customerCountValueLabel);
        customerPanel.add(customerCountLabel);

        ///////////////////////////////////////////
        managerPanel = new JPanel();
        managerPanel.setBackground(new Color(0x695E93));
        managerPanel.setBounds(480,150,400,400);
        managerPanel.setLayout(null);

        managerCountValueLabel = new JLabel();
        managerCountValueLabel.setText(SmartShoppers.getInstance().getNumberOfManagerAccounts()+"");
        managerCountValueLabel.setFont(new Font("Consolas", Font.PLAIN, 100));
        managerCountValueLabel.setBackground(Color.white);
        managerCountValueLabel.setBounds(0, 0, 400, 200);

        managerCountLabel = new JLabel();
        managerCountLabel.setText("Managers");
        managerCountLabel.setFont(new Font("Consolas", Font.PLAIN, 75));
        managerCountLabel.setBackground(Color.white);
        managerCountLabel.setBounds(0, 200, 400, 200);

        managerPanel.add(managerCountValueLabel);
        managerPanel.add(managerCountLabel);

        ///////////////////////////////////////////
        adminPanel = new JPanel();
        adminPanel.setBackground(new Color(0x695E93));
        adminPanel.setBounds(920,150,400,400);
        adminPanel.setLayout(null);

        adminCountValueLabel = new JLabel();
        adminCountValueLabel.setText(SmartShoppers.getInstance().getNumberOfAdminAccounts()+"");
        adminCountValueLabel.setFont(new Font("Consolas", Font.PLAIN, 100));
        adminCountValueLabel.setBackground(Color.white);
        adminCountValueLabel.setBounds(0, 0, 400, 200);

        adminCountLabel = new JLabel();
        adminCountLabel.setText("Admins");
        adminCountLabel.setFont(new Font("Consolas", Font.PLAIN, 75));
        adminCountLabel.setBackground(Color.white);
        adminCountLabel.setBounds(0, 200, 400, 200);

        adminPanel.add(adminCountValueLabel);
        adminPanel.add(adminCountLabel);

        ///////////////////////////////////////////
        storePanel = new JPanel();
        storePanel.setBackground(new Color(0x695E93));
        storePanel.setBounds(1360,150,400,400);
        storePanel.setLayout(null);

        storeCountValueLabel = new JLabel();
        storeCountValueLabel.setText(SmartShoppers.getInstance().getNumberOfStores()+"");
        storeCountValueLabel.setFont(new Font("Consolas", Font.PLAIN, 100));
        storeCountValueLabel.setBackground(Color.white);
        storeCountValueLabel.setBounds(0, 0, 400, 200);

        storeCountLabel = new JLabel();
        storeCountLabel.setText("Stores");
        storeCountLabel.setFont(new Font("Consolas", Font.PLAIN, 75));
        storeCountLabel.setBackground(Color.white);
        storeCountLabel.setBounds(0, 200, 400, 200);

        storePanel.add(storeCountValueLabel);
        storePanel.add(storeCountLabel);

        ///////////////////////////////////////////
        itemPanel = new JPanel();
        itemPanel.setBackground(new Color(0x695E93));
        itemPanel.setBounds(1800,150,400,400);
        itemPanel.setLayout(null);

        itemCountValueLabel = new JLabel();
        itemCountValueLabel.setText(SmartShoppers.getInstance().getNumberOfItems()+"");
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
        this.add(customerPanel);
        this.add(managerPanel);
        this.add(adminPanel);
        this.add(storePanel);
        this.add(itemPanel);
    }

    private void setUpManager(){

    }

    private void setUpCustomer(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
