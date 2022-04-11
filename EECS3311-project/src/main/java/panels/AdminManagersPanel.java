package panels;

import panels.SidePanel;
import project.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AdminManagersPanel extends SidePanel implements ActionListener {
    JTable managerTable;

    public AdminManagersPanel(){
        super("Managers");

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"First Name", "Last Name", "Username", "Assigned Store", "Delete"}, 0);

        //adding the entries into the table
        for(ManagerAccount a : SmartShoppers.getInstance().getManagerAccounts()) {
            List<Object> account = new ArrayList<>();
            account.add(a.getFirstName());
            account.add(a.getLastName());
            account.add(a.getUserName());
            account.add(a.getStore().getLocation().toString());
            account.add("Delete");
            tableModel.addRow(account.toArray());
        }

        managerTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        managerTable.getTableHeader().setFont(new Font("Consolas", Font.PLAIN, 50));
        managerTable.setRowHeight(50);
        managerTable.setFont(new Font("Consolas", Font.PLAIN, 40));
        managerTable.setCellSelectionEnabled(false);
        managerTable.setBounds(100, 400, 2200, 1000);
        managerTable.addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = managerTable.rowAtPoint(e.getPoint());
                int col = managerTable.columnAtPoint(e.getPoint());
                    System.out.println("Admin Manager Row: " + row + "Admin Manager Col: " + col);

                    if(col == 4 &&
                            JOptionPane.showConfirmDialog(null, "Are You Sure??","", JOptionPane.YES_NO_OPTION) == 0){
                        String username = tableModel.getValueAt(row, 2).toString();
                        Account account = SmartShoppers.getInstance().getAccount(username);
                        SmartShoppers.getInstance().deleteAccount(account);
                        Database.deleteAccount(account.getID());

                        //removing from JTable
                        DefaultTableModel dm = (DefaultTableModel) managerTable.getModel();
                        dm.removeRow(row);
                        managerTable.revalidate();
                        managerTable.repaint();
                    }
            }
        });

        JScrollPane customerTableScrollPane = new JScrollPane(managerTable);
        customerTableScrollPane.setBounds(100, 400, 2200, 1000);

        this.add(customerTableScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
