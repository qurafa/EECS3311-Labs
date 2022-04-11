package panels;

import frames.AccountFrame;
import project.Category;
import project.Database;
import project.Item;
import project.SmartShoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class AddItemsPanel extends SidePanel implements ActionListener {

    JTextField itemDescription;
    JTextField itemUnitPrice;
    JTextField itemTotalAmount;
    JTextField itemSalePercentage;
    JTextField category;
    JButton categoryButton;
    JComboBox categoryComboBox;

    JButton addItemButton;
    JButton closeButton;

    AccountFrame frame;

    private Category selectedCategory;

    AddItemsPanel(AccountFrame frame){
        super("Add Item");
        this.frame = frame;

        JLabel itemDescriptionLabel = new JLabel("Item Description");
        itemDescriptionLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        itemDescriptionLabel.setBackground(Color.white);
        itemDescriptionLabel.setBounds(100, 400, 500, 100);

        itemDescription = new JTextField();
        itemDescription.setPreferredSize(new Dimension(250,100));
        itemDescription.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemDescription.setForeground(new Color(0x00FF00));
        itemDescription.setBackground(Color.black);
        itemDescription.setCaretColor(Color.white);
        itemDescription.setBounds(100, 510, 500, 100);

        JLabel unitPriceLabel = new JLabel("Unit Price ");
        unitPriceLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        unitPriceLabel.setBackground(Color.white);
        unitPriceLabel.setBounds(100, 620, 500, 100);

        itemUnitPrice = new JTextField();
        itemUnitPrice.setPreferredSize(new Dimension(250,100));
        itemUnitPrice.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemUnitPrice.setForeground(new Color(0x00FF00));
        itemUnitPrice.setBackground(Color.black);
        itemUnitPrice.setCaretColor(Color.white);
        itemUnitPrice.setBounds(100, 730, 500, 100);

        JLabel totalAmountLabel = new JLabel("Total Amount");
        totalAmountLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        totalAmountLabel.setBackground(Color.white);
        totalAmountLabel.setBounds(100, 840, 500, 100);

        itemTotalAmount = new JTextField();
        itemTotalAmount.setPreferredSize(new Dimension(250,100));
        itemTotalAmount.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemTotalAmount.setForeground(new Color(0x00FF00));
        itemTotalAmount.setBackground(Color.black);
        itemTotalAmount.setCaretColor(Color.white);
        itemTotalAmount.setBounds(100, 950, 500, 100);

        JLabel salePercentLabel = new JLabel("Sale Percent");
        salePercentLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        salePercentLabel.setBackground(Color.white);
        salePercentLabel.setBounds(100, 1060, 500, 100);

        itemSalePercentage = new JTextField();
        itemSalePercentage.setPreferredSize(new Dimension(250,100));
        itemSalePercentage.setFont(new Font("Consolas", Font.PLAIN, 35));
        itemSalePercentage.setForeground(new Color(0x00FF00));
        itemSalePercentage.setBackground(Color.black);
        itemSalePercentage.setCaretColor(Color.white);
        itemSalePercentage.setBounds(100, 1170, 500, 100);

        JLabel selectCategory = new JLabel("Select Category");
        selectCategory.setFont(new Font("Consolas", Font.PLAIN, 50));
        selectCategory.setBackground(Color.white);
        selectCategory.setBounds(100, 1280, 500, 100);

        category = new JTextField();
        category.setPreferredSize(new Dimension(250,100));
        category.setFont(new Font("Consolas", Font.PLAIN, 35));
        category.setForeground(new Color(0x00FF00));
        category.setBackground(Color.black);
        category.setCaretColor(Color.white);
        category.setBounds(100, 1390, 500, 100);

        categoryButton = new JButton("Search");
        categoryButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        categoryButton.setFocusable(false);//look at this later...
        categoryButton.addActionListener(this);
        categoryButton.setBounds(600, 1390, 300, 100);
        categoryButton.setBorder(null);
        categoryButton.setBackground(Color.white);

        List<String> categoryString = new ArrayList<String>();

        for(Category category : SmartShoppers.getInstance().getCategories())
            categoryString.add(category.getDesc());

        categoryComboBox= new JComboBox(categoryString.toArray());
        categoryComboBox.setFont(new Font("Consolas", Font.PLAIN, 50));
        categoryComboBox.setSize(2000, 100);
        categoryComboBox.setBounds(100, 1500, 2000, 100);

        addItemButton = new JButton("Add Item");
        addItemButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        addItemButton.setFocusable(false);//look at this later...
        addItemButton.addActionListener(this);
        addItemButton.setBounds(100, 1610, 500, 100);
        addItemButton.setBorder(null);
        addItemButton.setBackground(new Color(0x695E93));

        closeButton =  new JButton("Close");
        closeButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        closeButton.setFocusable(false);//look at this later...
        closeButton.addActionListener(this);
        closeButton.setBounds(700, 1610, 500, 100);
        closeButton.setBorder(null);
        closeButton.setBackground(new Color(0x695E93));

        this.add(itemDescriptionLabel);
        this.add(itemDescription);
        this.add(unitPriceLabel);
        this.add(itemUnitPrice);
        this.add(totalAmountLabel);
        this.add(itemTotalAmount);
        this.add(salePercentLabel);
        this.add(itemSalePercentage);
        this.add(selectCategory);
        this.add(category);
        this.add(categoryButton);
        this.add(categoryComboBox);
        this.add(addItemButton);
        this.add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addItemButton){
            Item item = SmartShoppers.getInstance().createItem(itemDescription.getText(), itemUnitPrice.getText(),
                    itemTotalAmount.getText(), itemSalePercentage.getText());
            selectedCategory = SmartShoppers.getInstance().getCategory(categoryComboBox.getSelectedItem().toString());
            selectedCategory.addItem(item);
            Database.addItemCategory(item, selectedCategory);
            closePanel();
        }
        else if(e.getSource() == closeButton){
            closePanel();
        }
        else if(e.getSource() == categoryButton){
            for(Category cat : SmartShoppers.getInstance().getCategories()){
                if(cat.getDesc().equals(category.getText())) {
                    categoryComboBox.setSelectedItem(category.getText());
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Category Does not Exist");
            int option = JOptionPane.showConfirmDialog(this, "Add New Category?","", JOptionPane.YES_NO_OPTION);
            if(option == 0 && category.getText().length() > 0){
                SmartShoppers.getInstance().createCategory(category.getText());
                categoryComboBox.addItem(category.getText());
                categoryComboBox.setSelectedItem(category.getText());
            }
        }
    }

    private void setCategoryComboBox(){

    }

    private void closePanel(){
        frame.remove(frame.getCurrentPanel());
        frame.setCurrentPanel(new AdminItemsPanel(frame));
        frame.add(frame.getCurrentPanel());
        frame.repaint();
    }
}
