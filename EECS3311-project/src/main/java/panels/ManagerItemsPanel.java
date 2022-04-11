package panels;

import frames.AccountFrame;
import frames.ManagerAccountFrame;
import project.CustomerAccount;
import project.Item;
import project.ManagerAccount;
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

public class ManagerItemsPanel extends SidePanel implements ActionListener {

    JButton addItemButton;
    JTable customerTable;

    ManagerAccount account;
    ManagerAccountFrame frame;

    public ManagerItemsPanel(ManagerAccount account, ManagerAccountFrame frame){
        super("Items");
        this.account = account;
        this.frame = frame;

        addItemButton = new JButton("+");
        addItemButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        addItemButton.setFocusable(false);//look at this later...
        addItemButton.addActionListener(this);
        addItemButton.setBounds(600, 100, 50, 50);
        addItemButton.setBorder(null);
        addItemButton.setBackground(new Color(0x695E93));

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Item Description", "Unit Price", "Store Amount", "Sale Percentage"}, 0);

        //adding the entries into the table
        for(String iDesc : account.getStore().getItems()) {
            Item i = SmartShoppers.getInstance().getItem(iDesc);
            List<Object> store = new ArrayList<>();
            store.add(i.getDescription());
            store.add(i.getPrice());
            store.add(account.getStore().getItemAmount(i));
            store.add(i.getSale());
            tableModel.addRow(store.toArray());
        }

        customerTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        customerTable.getTableHeader().setFont(new Font("Consolas", Font.PLAIN, 50));
        customerTable.setRowHeight(50);
        customerTable.setFont(new Font("Consolas", Font.PLAIN, 40));
        customerTable.setCellSelectionEnabled(false);
        customerTable.setBounds(100, 400, 2200, 1000);
        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = customerTable.rowAtPoint(e.getPoint());
                int col = customerTable.columnAtPoint(e.getPoint());
                System.out.println("Admin Item Row: " + row + "Admin Item Col: " + col);

                if(col == 4){
                    StringBuilder item = new StringBuilder();
                    for(int i = 0; i <= 3; i++) {
                        item.append(tableModel.getValueAt(row, i));
                        if(i != 3) item.append(", ");
                    }

                    Item it = SmartShoppers.getInstance().getItem(item.toString());

                    if(it != null){}
                }
            }
        });

        JScrollPane customerTableScrollPane = new JScrollPane(customerTable);
        customerTableScrollPane.setBounds(100, 400, 2200, 1000);

        this.add(addItemButton);
        this.add(customerTableScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addItemButton){
            frame.remove(frame.getCurrentPanel());
            frame.setCurrentPanel(new ManagerAddItemsPanel(account, frame));
            frame.add(frame.getCurrentPanel());
            frame.repaint();
        }
    }
}
