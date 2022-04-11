package panels;

import project.CustomerAccount;
import project.ManagerAccount;
import project.SmartShoppers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ManagerCustomersPanel extends SidePanel implements ActionListener {

    JTable customerTable;

    ManagerAccount account;

    public ManagerCustomersPanel(ManagerAccount account){
        super("Customers");
        this.account = account;

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"First Name", "Last Name", "Username", "Password"}, 0);

        //adding the entries into the table
        for(CustomerAccount a : SmartShoppers.getInstance().getCustomerAccounts()) {
            if(a.getStore().getLocation().toString().equals(account.getStore().getLocation().toString())){
                List<Object> store = new ArrayList<>();
                store.add(a.getFirstName());
                store.add(a.getLastName());
                store.add(a.getUserName());
                store.add(a.getPassword());
                tableModel.addRow(store.toArray());
            }
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

        JScrollPane customerTableScrollPane = new JScrollPane(customerTable);
        customerTableScrollPane.setBounds(100, 400, 2200, 1000);

        this.add(customerTableScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
