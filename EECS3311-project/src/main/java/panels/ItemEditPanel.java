package panels;

import frames.AccountFrame;
import project.Account;
import project.Database;
import project.Item;
import project.SmartShoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemEditPanel extends SidePanel implements ActionListener {

    Item item;
    AccountFrame frame;

    JLabel itemDescriptionLabel;
    JLabel unitPriceLabel;
    JLabel totalAmountLabel;
    JLabel salePercentLabel;

    JTextField itemDescription;
    JTextField itemUnitPrice;
    JTextField itemTotalAmount;
    JTextField itemSalePercentage;

    JButton updateItemButton;
    JButton removeItemButton;
    JButton closeButton;

    public ItemEditPanel(Item item, AccountFrame frame){
        super("Update Item", 100, 100, 1000, 200);

        this.item = item;
        this.frame = frame;

        this.setBackground(Color.white);
        this.setBounds(500,100,2400,1900);
        this.setLayout(null);

        itemDescriptionLabel = new JLabel("Item Description");
        itemDescriptionLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        itemDescriptionLabel.setBackground(Color.white);
        itemDescriptionLabel.setBounds(100, 400, 500, 100);

        itemDescription = new JTextField();
        itemDescription.setText(item.getDescription());
        itemDescription.setPreferredSize(new Dimension(250,100));
        itemDescription.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemDescription.setForeground(new Color(0x00FF00));
        itemDescription.setBackground(Color.black);
        itemDescription.setCaretColor(Color.white);
        itemDescription.setBounds(100, 500, 500, 100);

        unitPriceLabel = new JLabel("Unit Price");
        unitPriceLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        unitPriceLabel.setBackground(Color.white);
        unitPriceLabel.setBounds(100, 700, 500, 100);

        itemUnitPrice = new JTextField();
        itemUnitPrice.setText(item.getPrice()+"");
        itemUnitPrice.setPreferredSize(new Dimension(250,100));
        itemUnitPrice.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemUnitPrice.setForeground(new Color(0x00FF00));
        itemUnitPrice.setBackground(Color.black);
        itemUnitPrice.setCaretColor(Color.white);
        itemUnitPrice.setBounds(100, 800, 500, 100);

        totalAmountLabel = new JLabel("Total Amount");
        totalAmountLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        totalAmountLabel.setBackground(Color.white);
        totalAmountLabel.setBounds(100, 1000, 500, 100);

        itemTotalAmount = new JTextField();
        itemTotalAmount.setText(item.getTotalAmount()+"");
        itemTotalAmount.setPreferredSize(new Dimension(250,100));
        itemTotalAmount.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemTotalAmount.setForeground(new Color(0x00FF00));
        itemTotalAmount.setBackground(Color.black);
        itemTotalAmount.setCaretColor(Color.white);
        itemTotalAmount.setBounds(100, 1100, 500, 100);

        salePercentLabel = new JLabel("Sale Percent");
        salePercentLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        salePercentLabel.setBackground(Color.white);
        salePercentLabel.setBounds(100, 1300, 500, 100);

        itemSalePercentage = new JTextField();
        itemSalePercentage.setText(item.getSale()+"");
        itemSalePercentage.setPreferredSize(new Dimension(250,100));
        itemSalePercentage.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemSalePercentage.setForeground(new Color(0x00FF00));
        itemSalePercentage.setBackground(Color.black);
        itemSalePercentage.setCaretColor(Color.white);
        itemSalePercentage.setBounds(100, 1400, 500, 100);

        updateItemButton = new JButton("Update");
        updateItemButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        updateItemButton.setFocusable(false);//look at this later...
        updateItemButton.setBounds(100, 1600, 500, 100);
        updateItemButton.setBorder(null);
        updateItemButton.setBackground(new Color(0x695E93));
        updateItemButton.addActionListener(this);

        removeItemButton =  new JButton("Remove");
        removeItemButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        removeItemButton.setFocusable(false);//look at this later...
        removeItemButton.setBounds(700, 1600, 500, 100);
        removeItemButton.setBorder(null);
        removeItemButton.setBackground(new Color(0x695E93));
        removeItemButton.addActionListener(this);

        closeButton =  new JButton("Close");
        closeButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        closeButton.setFocusable(false);//look at this later...
        closeButton.setBounds(1300, 1600, 500, 100);
        closeButton.setBorder(null);
        closeButton.setBackground(new Color(0x695E93));
        closeButton.addActionListener(this);

        this.add(itemDescriptionLabel);
        this.add(itemDescription);
        this.add(unitPriceLabel);
        this.add(itemUnitPrice);
        this.add(totalAmountLabel);
        this.add(itemTotalAmount);
        this.add(salePercentLabel);
        this.add(itemSalePercentage);
        this.add(updateItemButton);
        this.add(removeItemButton);
        this.add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateItemButton &&
                itemChange() &&
                JOptionPane.showConfirmDialog(this, "Update Item Information?","", JOptionPane.YES_NO_OPTION) == 0){
            System.out.println("Update Item");
            Database.updateItemInformation(item, itemDescription.getText(), Double.parseDouble(itemUnitPrice.getText()), Integer.parseInt(itemTotalAmount.getText()), Integer.parseInt(itemSalePercentage.getText()), item.getSuggestPoint());
            item.setDescription(itemDescription.getText());
            item.setPrice(Double.parseDouble(itemUnitPrice.getText()));
            item.setTotalAmount(Integer.parseInt(itemTotalAmount.getText()));
            item.setSale(Integer.parseInt(itemSalePercentage.getText()));
        }
        else if(e.getSource() == removeItemButton &&
                JOptionPane.showConfirmDialog(this, "Are You Sure?","", JOptionPane.YES_NO_OPTION) == 0){
            System.out.println("Remove Item");
            Database.deleteItem(item.getID());
            SmartShoppers.getInstance().deleteItem(item);
        }
        else if(e.getSource() == closeButton){
            System.out.println("Close");
            frame.remove(frame.getCurrentPanel());
            frame.setCurrentPanel(new AdminItemsPanel(frame));
            frame.add(frame.getCurrentPanel());
            frame.repaint();
        }
    }

    private boolean itemChange(){
        if(!itemDescription.getText().equals(item.getDescription()))
            return true;
        if(!itemUnitPrice.getText().equals(item.getPrice()+""))
            return true;
        if(!itemTotalAmount.getText().equals(item.getTotalAmount()+""))
            return true;
        if(!itemSalePercentage.getText().equals(item.getSale()+""))
            return true;

        return false;
    }

}
