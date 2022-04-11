package panels;

import panels.SidePanel;
import project.CustomerAccount;
import project.SmartShoppers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminCustomersPanel extends SidePanel implements ActionListener {

    JTable customerTable;

    public AdminCustomersPanel(){
        super("Customers");

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"First Name", "Last Name", "Username", "Password", "Preferred Store Location"}, 0);

        System.out.println(SmartShoppers.getInstance().getCustomerAccounts().size());
        //adding the entries into the table
        for(CustomerAccount a : SmartShoppers.getInstance().getCustomerAccounts()) {
            List<Object> account = new ArrayList<>();
            account.add(a.getFirstName());
            account.add(a.getLastName());
            account.add(a.getUserName());
            account.add(a.getPassword());
            account.add(a.getStore().getLocation().toString());
            tableModel.addRow(account.toArray());
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
