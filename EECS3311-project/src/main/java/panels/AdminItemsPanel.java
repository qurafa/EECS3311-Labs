package panels;

import frames.AccountFrame;
import project.Category;
import project.Item;
import project.SmartShoppers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminItemsPanel extends SidePanel implements ActionListener {

    JButton addItemButton;
    JButton addCategoryButton;

    Map <JButton, Item> updateItemButtons;
    Map <JButton, Item> deleteItemButtons;

    JTable itemTable;

    AccountFrame frame;

    public AdminItemsPanel(AccountFrame frame){
        super("Items");
        this.frame = frame;

        addItemButton = new JButton("Add Item");
        addItemButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        addItemButton.setFocusable(false);//look at this later...
        addItemButton.addActionListener(this);
        addItemButton.setBounds(600, 100, 300, 50);
        addItemButton.setBorder(null);
        addItemButton.setBackground(new Color(0x695E93));

        addCategoryButton = new JButton("Add Category");
        addCategoryButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        addCategoryButton.setFocusable(false);//look at this later...
        addCategoryButton.addActionListener(this);
        addCategoryButton.setBounds(1000, 100, 400, 50);
        addCategoryButton.setBorder(null);
        addCategoryButton.setBackground(new Color(0x695E93));

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Item Description", "Unit Price", "Total Amount", "Sale Percentage", "Update"}, 0);

        //adding the entries into the table
        for(Item a : SmartShoppers.getInstance().getItems()) {
            List<Object> item = new ArrayList<>();
            item.add(a.getDescription());
            item.add(a.getPrice());
            item.add(a.getTotalAmount());
            item.add(a.getSale());
            item.add("Update");
            tableModel.addRow(item.toArray());
        }

        itemTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        itemTable.getTableHeader().setFont(new Font("Consolas", Font.PLAIN, 50));
        itemTable.setRowHeight(50);
        itemTable.setFont(new Font("Consolas", Font.PLAIN, 40));
        itemTable.setCellSelectionEnabled(false);
        itemTable.enableInputMethods(false);
        itemTable.setBounds(100, 400, 2200, 1000);
        itemTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = itemTable.rowAtPoint(e.getPoint());
                int col = itemTable.columnAtPoint(e.getPoint());


                if(col == 4){
                    Item it = SmartShoppers.getInstance().getItem(itemTable.getValueAt(row, 0).toString());

                    if(it != null){
                        System.out.println("Admin Item Row: " + row + "Admin Item Col: " + col);
                        frame.remove(frame.getCurrentPanel());
                        frame.setCurrentPanel(new ItemEditPanel(it, frame));
                        frame.add(frame.getCurrentPanel());
                        frame.repaint();
                    }
                }
            }

        });

        JScrollPane customerTableScrollPane = new JScrollPane(itemTable);
        customerTableScrollPane.setBounds(100, 400, 2200, 1000);

        this.add(addItemButton);
        this.add(addCategoryButton);
        this.add(customerTableScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addItemButton){
            frame.remove(frame.getCurrentPanel());
            frame.setCurrentPanel(new AddItemsPanel(frame));
            frame.add(frame.getCurrentPanel());
            frame.repaint();
        }
        else if(e.getSource() == addCategoryButton){
            String desc = JOptionPane.showInputDialog(this ,"Specify Category");
            for(Category cat : SmartShoppers.getInstance().getCategories()){
                if(cat.getDesc().equals(desc)){
                    JOptionPane.showMessageDialog(this ,"Category Already exists");
                    return;
                }
            }
            if(desc != null && desc.length() > 0)
                SmartShoppers.getInstance().createCategory(desc);
        }
    }
}
