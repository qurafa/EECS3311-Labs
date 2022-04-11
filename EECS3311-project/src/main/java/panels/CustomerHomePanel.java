package panels;

import project.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

public class CustomerHomePanel extends HomePanel implements ActionListener {
    CustomerAccount account;

    JPanel currentStorePanel;
    JLabel currentStoreLabel;

    JTable saleItemsTable;
    JPanel saleItemsPanel;
    JLabel saleItemsLabel;

    JTable suggestedItemsTable;
    JPanel suggestedItemsPanel;
    JLabel suggestedItemsLabel;

    JTable itemsTable;
    JPanel itemsPanel;
    JLabel itemsLabel;

    public CustomerHomePanel(CustomerAccount account){
        this.account = account;

        ///////////////////////////////////////////
        currentStorePanel = new JPanel();
        currentStorePanel.setBackground(new Color(0x695E93));
        currentStorePanel.setBounds(0,1700,2400,100);
        currentStorePanel.setLayout(null);

        currentStoreLabel = new JLabel(account.getStore().getLocation().toString());
        currentStoreLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        currentStoreLabel.setBackground(Color.white);
        currentStoreLabel.setBounds(50, 0, 2400, 100);

        currentStorePanel.add(currentStoreLabel);

        ///////////////////////////////////////////
        saleItemsPanel = new JPanel();
        saleItemsPanel.setBackground(new Color(0x695E93));
        saleItemsPanel.setBounds(50,0,2300,400);
        saleItemsPanel.setLayout(null);

        saleItemsLabel = new JLabel("Sale Items");
        saleItemsLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        saleItemsLabel.setBackground(Color.white);
        saleItemsLabel.setBounds(50, 0, 2300, 100);

        saleItemsPanel.add(saleItemsLabel);

        ///////////////////////////////////////////

        List<String> row = new ArrayList<>();
        List<Item> saleItems = account.getStore().getSaleItems();
        for(Item item : saleItems){
            row.add(item.getDescription());
        }
        DefaultTableModel tableModel = new DefaultTableModel(row.toArray(),0);
        row.clear();
        for(Item item : saleItems){
            row.add("$" + item.getPrice());
        }
        tableModel.addRow(row.toArray());
        row.clear();
        for(int i = 0; i < saleItems.size(); i++){
            row.add("Add Item To Cart");
        }
        tableModel.addRow(row.toArray());
        row.clear();

        saleItemsTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        saleItemsTable.getTableHeader().setFont(new Font("Consolas", Font.PLAIN, 100));
        saleItemsTable.setRowHeight(100);
        saleItemsTable.setFont(new Font("Consolas", Font.PLAIN, 40));
        saleItemsTable.setCellSelectionEnabled(false);
        saleItemsTable.setBounds(0, 100, 2300, 400);
        saleItemsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = saleItemsTable.rowAtPoint(e.getPoint());
                int col = saleItemsTable.columnAtPoint(e.getPoint());

                if(row == 1){
                    System.out.println("Adding to cart");
                    Item i = SmartShoppers.getInstance().getItem(tableModel.getColumnName(col));
                    i.increaseSuggestPoint();
                    Database.updateItemInformation(i, i.getDescription(), i.getPrice(), i.getTotalAmount(), i.getSale(), i.getSuggestPoint());
                    Store s = account.getStore();
                    account.getCart().addItem(i);
                    s.removeItem(i);
                    if(s.getItemAmount(i) < 1){
                        TableColumn tCol = saleItemsTable.getColumnModel().getColumn(col);
                        saleItemsTable.removeColumn(tCol);
                    }
                    System.out.println("Added to cart");
                }
                saleItemsTable.revalidate();
                saleItemsTable.repaint();
            }
        });

        TableColumnModel tm = saleItemsTable.getColumnModel();
        for(int i = 0; i < saleItemsTable.getColumnCount(); i++){
            tm.getColumn(i).setMinWidth(100);
            tm.getColumn(i).setMaxWidth(500);
        }

        JScrollPane saleItemsListScrollPane =  new JScrollPane(saleItemsTable, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        saleItemsListScrollPane.setBounds(0,100,2300,400);

        saleItemsPanel.add(saleItemsListScrollPane);

        ///////////////////////////////////////////
        suggestedItemsPanel = new JPanel();
        suggestedItemsPanel.setBackground(new Color(0x695E93));
        suggestedItemsPanel.setBounds(50,500,2300,400);
        suggestedItemsPanel.setLayout(null);

        suggestedItemsLabel = new JLabel("Suggested Items");
        suggestedItemsLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        suggestedItemsLabel.setBackground(Color.white);
        suggestedItemsLabel.setBounds(50, 0, 2300, 100);

        suggestedItemsPanel.add(suggestedItemsLabel);

        ///////////////////////////////////////////

        List <String> rowSuggested = new ArrayList<>();
        List<Item> suggestedItems = account.getStore().getSuggestedItems();
        for(Item item : suggestedItems){
            System.out.println("SP!!!!" + item.getSuggestPoint());
            rowSuggested.add(item.getDescription());
        }
        DefaultTableModel tableModelSuggested = new DefaultTableModel(rowSuggested.toArray(),0);
        rowSuggested.clear();
        for(Item item : suggestedItems){
            rowSuggested.add("$" + item.getPrice());
        }
        tableModelSuggested.addRow(rowSuggested.toArray());
        rowSuggested.clear();
        for(int i = 0; i < suggestedItems.size(); i++){
            rowSuggested.add("Add Item To Cart");
        }
        tableModelSuggested.addRow(rowSuggested.toArray());
        rowSuggested.clear();

        suggestedItemsTable = new JTable(tableModelSuggested){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        suggestedItemsTable.getTableHeader().setFont(new Font("Consolas", Font.PLAIN, 100));
        suggestedItemsTable.setRowHeight(100);
        suggestedItemsTable.setFont(new Font("Consolas", Font.PLAIN, 40));
        suggestedItemsTable.setCellSelectionEnabled(false);
        suggestedItemsTable.setBounds(0, 100, 2300, 400);
        suggestedItemsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = suggestedItemsTable.rowAtPoint(e.getPoint());
                int col = suggestedItemsTable.columnAtPoint(e.getPoint());

                if(row == 1){
                    System.out.println("Adding to cart");
                    Item i = SmartShoppers.getInstance().getItem(tableModelSuggested.getColumnName(col));
                    i.increaseSuggestPoint();
                    Database.updateItemInformation(i, i.getDescription(), i.getPrice(), i.getTotalAmount(), i.getSale(), i.getSuggestPoint());
                    Store s = account.getStore();
                    account.getCart().addItem(i);
                    s.removeItem(i);
                    if(s.getItemAmount(i) < 1){
                        TableColumn tCol = suggestedItemsTable.getColumnModel().getColumn(col);
                        suggestedItemsTable.removeColumn(tCol);
                    }
                    System.out.println("Added to cart");
                }
                suggestedItemsTable.revalidate();
                suggestedItemsTable.repaint();
            }
        });

        TableColumnModel suggestedTM = suggestedItemsTable.getColumnModel();
        for(int i = 0; i < suggestedItemsTable.getColumnCount(); i++){
            suggestedTM.getColumn(i).setMinWidth(100);
            suggestedTM.getColumn(i).setMaxWidth(500);
        }

        JScrollPane suggestedItemsListScrollPane =  new JScrollPane(suggestedItemsTable, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        suggestedItemsListScrollPane.setBounds(0,100,2300,400);

        suggestedItemsPanel.add(suggestedItemsListScrollPane);

        ///////////////////////////////////////////
        itemsPanel = new JPanel();
        itemsPanel.setBackground(new Color(0x695E93));
        itemsPanel.setBounds(50,1000,2300,600);
        itemsPanel.setLayout(null);

        itemsLabel = new JLabel("Items");
        itemsLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        itemsLabel.setBackground(Color.white);
        itemsLabel.setBounds(50, 0, 2250, 100);

        itemsPanel.add(itemsLabel);

        ///////////////////////////////////////////

        DefaultTableModel itemTableModel = new DefaultTableModel(new Object[]{"Item", "Price", "Sale", "Add to Cart"}, 0);

        for(String item : account.getStore().getItems()) {
            List<Object> itemRow = new ArrayList<>();
            Item i = SmartShoppers.getInstance().getItem(item);
            itemRow.add(i.getDescription());
            itemRow.add(i.getPrice()+"");
            itemRow.add(i.getSale()+"");
            itemRow.add("Add to Cart");
            itemTableModel.addRow(itemRow.toArray());
        }

        itemsTable = new JTable(itemTableModel){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        itemsTable.getTableHeader().setFont(new Font("Consolas", Font.PLAIN, 50));
        itemsTable.setRowHeight(50);
        itemsTable.setFont(new Font("Consolas", Font.PLAIN, 40));
        itemsTable.setCellSelectionEnabled(false);
        itemsTable.setBounds(100, 400, 2200, 500);
        itemsTable.addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = itemsTable.rowAtPoint(e.getPoint());
                int col = itemsTable.columnAtPoint(e.getPoint());
                System.out.println("Admin Manager Row: " + row + "Admin Manager Col: " + col);

                if(col == 3 &&
                    JOptionPane.showConfirmDialog(null, "Add to Cart?","", JOptionPane.YES_NO_OPTION) == 0){

                    System.out.println("Adding to cart");
                    Item i = SmartShoppers.getInstance().getItem(itemTableModel.getValueAt(row, 0).toString());
                    i.increaseSuggestPoint();
                    Database.updateItemInformation(i, i.getDescription(), i.getPrice(), i.getTotalAmount(), i.getSale(), i.getSuggestPoint());
                    Store s = account.getStore();
                    account.getCart().addItem(i);
                    s.removeItem(i);
                    if(s.getItemAmount(i) < 1){
                        itemTableModel.removeRow(row);
                    }
                    System.out.println("Added to cart");
                    itemsTable.revalidate();
                    itemsTable.repaint();
                }
            }
        });

        JScrollPane itemTableScrollPane = new JScrollPane(itemsTable);
        itemTableScrollPane.setBounds(0, 100, 2300, 600);

        itemsPanel.add(itemTableScrollPane);

        ///////////////////////////////////////////

        this.add(saleItemsPanel);
        this.add(suggestedItemsPanel);
        this.add(itemsPanel);
        this.add(currentStorePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
