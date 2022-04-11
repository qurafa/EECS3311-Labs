package panels;

import frames.AccountFrame;
import frames.ManagerAccountFrame;
import project.Database;
import project.Item;
import project.ManagerAccount;
import project.SmartShoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class ManagerAddItemsPanel extends SidePanel implements ActionListener {

    ManagerAccount account;
    ManagerAccountFrame frame;

    JComboBox itemsComboBox;
    JTextField availableAmount;
    JTextField amountWanted;
    JButton addItemButton;
    JButton closeButton;

    ManagerAddItemsPanel(ManagerAccount account, ManagerAccountFrame frame){
        super("Add Item");

        this.account = account;
        this.frame = frame;

        List<String> items = new ArrayList<String>();

        for(Item i : SmartShoppers.getInstance().getItems()){
            items.add(i.getDescription());
        }

        itemsComboBox = new JComboBox(items.toArray());
        itemsComboBox.setFont(new Font("Consolas", Font.PLAIN, 50));
        itemsComboBox.setSelectedIndex(0);
        itemsComboBox.setSize(2000, 100);
        itemsComboBox.setBounds(100, 400, 2000, 100);

        JLabel unitPriceLabel = new JLabel("Unit Price");
        unitPriceLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        unitPriceLabel.setBackground(Color.white);
        unitPriceLabel.setBounds(100, 600, 500, 100);

        JTextField unitPrice = new JTextField();
        unitPrice.setEditable(false);
        unitPrice.setText(items.size() > 0 ? SmartShoppers.getInstance().getItem(items.get(0)).getPrice()+"" : "");
        unitPrice.setPreferredSize(new Dimension(250,100));
        unitPrice.setFont(new Font("Consolas", Font.PLAIN, 35));
        unitPrice.setForeground(new Color(0x00FF00));
        unitPrice.setBackground(Color.black);
        unitPrice.setCaretColor(Color.white);
        unitPrice.setBounds(100, 700, 500, 100);

        JLabel availableAmountLabel = new JLabel("Available Amount");
        availableAmountLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        availableAmountLabel.setBackground(Color.white);
        availableAmountLabel.setBounds(800, 600, 500, 100);

        availableAmount = new JTextField();
        availableAmount.setEditable(false);
        availableAmount.setText(items.size() > 0 ? SmartShoppers.getInstance().getItem(items.get(0)).getTotalAmount()+"" : "");
        availableAmount.setPreferredSize(new Dimension(250,100));
        availableAmount.setFont(new Font("Consolas", Font.PLAIN, 35));
        availableAmount.setForeground(new Color(0x00FF00));
        availableAmount.setBackground(Color.black);
        availableAmount.setCaretColor(Color.white);
        availableAmount.setBounds(800, 700, 500, 100);

        JLabel salePercentLabel = new JLabel("Sale Percent");
        salePercentLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        salePercentLabel.setBackground(Color.white);
        salePercentLabel.setBounds(100, 900, 500, 100);

        JTextField salePercent = new JTextField();
        salePercent.setEditable(false);
        salePercent.setText(items.size() > 0 ? SmartShoppers.getInstance().getItem(items.get(0)).getSale()+"" : "");
        salePercent.setPreferredSize(new Dimension(250,100));
        salePercent.setFont(new Font("Consolas", Font.PLAIN, 35));
        salePercent.setForeground(new Color(0x00FF00));
        salePercent.setBackground(Color.black);
        salePercent.setCaretColor(Color.white);
        salePercent.setBounds(100, 1000, 500, 100);

        JLabel amountWantedLabel = new JLabel("Amount Wanted");
        amountWantedLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        amountWantedLabel.setBackground(Color.white);
        amountWantedLabel.setBounds(100, 1200, 500, 100);

        amountWanted = new JTextField();
        amountWanted.setPreferredSize(new Dimension(250,100));
        amountWanted.setFont(new Font("Consolas", Font.PLAIN, 35));
        amountWanted.setForeground(new Color(0x00FF00));
        amountWanted.setBackground(Color.black);
        amountWanted.setCaretColor(Color.white);
        amountWanted.setBounds(100, 1300, 500, 100);

        addItemButton = new JButton("Add Item");
        addItemButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        addItemButton.setFocusable(false);//look at this later...
        addItemButton.addActionListener(this);
        addItemButton.setBounds(100, 1500, 500, 100);
        addItemButton.setBorder(null);
        addItemButton.setBackground(new Color(0x695E93));

        closeButton =  new JButton("Close");
        closeButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        closeButton.setFocusable(false);//look at this later...
        closeButton.addActionListener(this);
        closeButton.setBounds(700, 1500, 500, 100);
        closeButton.setBorder(null);
        closeButton.setBackground(new Color(0x695E93));

         itemsComboBox.addItemListener(new ItemListener() {
             @Override
             public void itemStateChanged(ItemEvent e) {
                 unitPrice.setText(items.size() > 0 ? SmartShoppers.getInstance().getItem(itemsComboBox.getSelectedItem().toString()).getPrice()+"" : "");
                 availableAmount.setText(items.size() > 0 ? SmartShoppers.getInstance().getItem(itemsComboBox.getSelectedItem().toString()).getTotalAmount()+"" : "");
                 salePercent.setText(items.size() > 0 ? SmartShoppers.getInstance().getItem(itemsComboBox.getSelectedItem().toString()).getSale()+"" : "");
             }
         });

        this.add(itemsComboBox);
        this.add(unitPriceLabel);
        this.add(unitPrice);
        this.add(availableAmountLabel);
        this.add(availableAmount);
        this.add(salePercentLabel);
        this.add(salePercent);
        this.add(amountWantedLabel);
        this.add(amountWanted);
        this.add(addItemButton);
        this.add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addItemButton && itemsComboBox.getSelectedItem() != null){

            if(!verifyAmount()) return;

            int amount = Integer.parseInt(amountWanted.getText());

            Item item = SmartShoppers.getInstance().getItem(itemsComboBox.getSelectedItem().toString());

            if(account.getStore().getItemAmount(item) < 1)
                Database.addItemStore(item, account.getStore(), amount);
            else
                Database.updateItemStore(item, account.getStore(), account.getStore().getItemAmount(item) + amount);

            Database.updateItemInformation(item, item.getDescription(), item.getPrice(), item.getTotalAmount() - amount, item.getSale(), item.getSuggestPoint());
            SmartShoppers.getInstance().addItem(account.getStore(), item, amount);
            item.setTotalAmount(item.getTotalAmount() - amount);

            closePanel();
        }
        else if(e.getSource() == closeButton){
            closePanel();
        }
    }

    private boolean verifyAmount(){
        boolean output = true;
        try{
            Integer.parseInt(amountWanted.getText());
        }
        catch(NumberFormatException n){
            JOptionPane.showMessageDialog(this , "Amount Wrongly Specified");
            return false;
        }

        if(Integer.parseInt(amountWanted.getText()) < 0){
            JOptionPane.showMessageDialog(this , "Amount Cannot be Negative");
            output = false;
        }

        if(Integer.parseInt(amountWanted.getText()) > Integer.parseInt(availableAmount.getText())){
            JOptionPane.showMessageDialog(this , "Please select and Amount less than inventory");
            output = false;
        }

        return output;
    }

    private void closePanel(){
        frame.remove(frame.getCurrentPanel());
        frame.setCurrentPanel(new ManagerItemsPanel(account, frame));
        frame.add(frame.getCurrentPanel());
        frame.repaint();
    }
}
