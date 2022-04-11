package panels;

import frames.AccountFrame;
import project.Item;
import project.SmartShoppers;
import project.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

public class AdminStoresPanel extends SidePanel implements ActionListener {

    JTable storeTable;
    JButton addStoreButton;

    AccountFrame frame;

    public AdminStoresPanel(AccountFrame frame){
        super("Stores");
        this.frame = frame;

        addStoreButton = new JButton("+");
        addStoreButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        addStoreButton.setFocusable(false);//look at this later...
        addStoreButton.addActionListener(this);
        addStoreButton.setBounds(600, 100, 50, 50);
        addStoreButton.setBorder(null);
        addStoreButton.setBackground(new Color(0x695E93));

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Store Address", "City", "State", "Postal Code", "Country", "Open Hour", "Close Hour", "Update"}, 0);

        //adding the entries into the table
        for(Store s : SmartShoppers.getInstance().getStores()){
            List<Object>store = new ArrayList<>();
            store.add(s.getLocation().getAddress());
            store.add(s.getLocation().getCity());
            store.add(s.getLocation().getState());
            store.add(s.getLocation().getPostalCode());
            store.add(s.getLocation().getCountry());
            store.add(s.getOpenHour());
            store.add(s.getCloseHour());
            store.add("Update");
            tableModel.addRow(store.toArray());
        }

        storeTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        storeTable.getTableHeader().setFont(new Font("Consolas", Font.PLAIN, 50));
        storeTable.setRowHeight(50);
        storeTable.setFont(new Font("Consolas", Font.PLAIN, 40));
        storeTable.setCellSelectionEnabled(false);
        storeTable.setBounds(100, 400, 2200, 1000);
        storeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = storeTable.rowAtPoint(e.getPoint());
                int col = storeTable.columnAtPoint(e.getPoint());

                if(col == 7){
                    StringBuilder store = new StringBuilder();
                    for(int i = 0; i <= 4; i++){
                        store.append(tableModel.getValueAt(row, i));
                        if(i != 4) store.append(", ");
                    }

                    System.out.println(store.toString());
                    Store st = SmartShoppers.getInstance().getStore(store.toString());

                    if(st != null){
                        frame.remove(frame.getCurrentPanel());
                        frame.setCurrentPanel(new StoreEditPanel(st, frame));
                        frame.add(frame.getCurrentPanel());
                        frame.repaint();
                    }
                }
            }
        });

        JScrollPane storeTableScrollPane = new JScrollPane(storeTable);
        storeTableScrollPane.setBounds(100, 400, 2200, 1000);

        this.add(addStoreButton);
        this.add(storeTableScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addStoreButton){
            frame.remove(frame.getCurrentPanel());
            frame.setCurrentPanel(new AddStorePanel(frame));
            frame.add(frame.getCurrentPanel());
            frame.repaint();
        }
    }
}
