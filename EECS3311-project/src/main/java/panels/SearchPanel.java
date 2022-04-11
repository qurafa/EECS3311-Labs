package panels;

import frames.AccountFrame;
import project.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SearchPanel extends SidePanel implements ActionListener {

    CustomerAccount account;
    JTable searchTable;
    String search;
    AccountFrame frame;

    public SearchPanel(String search, CustomerAccount account, AccountFrame frame){
        super("Search");
        this.search = search;
        this.account = account;
        this.frame = frame;

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Result", ""}, 0);

        List<String> row = new ArrayList<>();
        row.add("Categories");
        row.add("");
        tableModel.addRow(row.toArray());
        row.clear();
        for(Category category : Search.searchCategory(search, account.getStore())){
            row.add(category.getDesc());
            row.add("View");
            tableModel.addRow(row.toArray());
            row.clear();
        }
        row.add("Items");
        row.add("");
        tableModel.addRow(row.toArray());
        row.clear();
        for(Item item : Search.searchItem(search, account.getStore())){
            row.add(item.getDescription() + " $" + item.getPrice());
            row.add("Add to Cart");
            tableModel.addRow(row.toArray());
            row.clear();
        }
        row.clear();
        row.add("Stores");
        row.add("");
        tableModel.addRow(row.toArray());
        row.clear();
        for(Store store : Search.searchStore(search)){
            row.add(store.getLocation().toString());
            row.add("View");
            tableModel.addRow(row.toArray());
            row.clear();
        }

        searchTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        searchTable.getTableHeader().setFont(new Font("Consolas", Font.PLAIN, 50));
        searchTable.setRowHeight(50);
        searchTable.setFont(new Font("Consolas", Font.PLAIN, 40));
        searchTable.setCellSelectionEnabled(false);
        searchTable.setBounds(100, 400, 2200, 1000);
        searchTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = searchTable.rowAtPoint(e.getPoint());
                int col = searchTable.columnAtPoint(e.getPoint());
                System.out.println("Row: " + row + " Col: " + col);

                String colVal = searchTable.getValueAt(row, 1).toString();

                if(col == 1 && (colVal.equals("View") || colVal.equals("Add to Cart"))){
                    if(searchTable.getValueAt(row, 0).toString().contains("$") && JOptionPane.showConfirmDialog(null, "Add to Cart","", JOptionPane.YES_NO_OPTION) == 0){
                        String desc = searchTable.getValueAt(row, 0).toString();
                        int end = desc.indexOf("$");
                        desc = desc.substring(0, end-1);
                        Item i = SmartShoppers.getInstance().getItem(desc);
                        i.increaseSuggestPoint();
                        Store s = account.getStore();
                        account.getCart().addItem(i);
                        s.removeItem(i);
                        if(s.getItemAmount(i) < 1){
                            tableModel.removeRow(row);
                        }
                        System.out.println("Added to cart");
                    }
                    else if(SmartShoppers.getInstance().getCategory(searchTable.getValueAt(row, 0).toString()) != null){

                    }
                    else if(SmartShoppers.getInstance().getStore(searchTable.getValueAt(row, 0).toString()) != null){
                        switchToStoreView(SmartShoppers.getInstance().getStore(searchTable.getValueAt(row, 0).toString()));
                    }
                    searchTable.revalidate();
                    searchTable.repaint();
                }
            }
        });

        JScrollPane searchTableScrollPane = new JScrollPane(searchTable);
        searchTableScrollPane.setBounds(100, 400, 2200, 1000);

        this.add(searchTableScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void switchToStoreView(Store store){
        System.out.println("storesButton");
        frame.remove(frame.getCurrentPanel());
        frame.setCurrentPanel(new CustomerStorePanel(account, store));
        frame.add(frame.getCurrentPanel());
        frame.repaint();
    }
}
