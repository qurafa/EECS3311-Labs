package panels;

import frames.CustomerAccountFrame;
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

public class CustomerShoppingCartPanel extends SidePanel implements ActionListener {

    CustomerAccount account;
    CustomerAccountFrame frame;
    JTable itemTable;
    JButton checkOut;

    public CustomerShoppingCartPanel(CustomerAccount account, CustomerAccountFrame frame){
        super("Shopping Cart",100, 100, 1500, 200);
        this.account = account;
        this.frame = frame;

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Item", "Unit Price", "Quantity", "-", "+", "Total Price"}, 0);

        for(Item item : account.getCart().getItems()) {
            List<String> items = new ArrayList<>();
            items.add(item.getDescription());
            items.add(item.getPrice()+"");
            items.add(account.getCart().getItemAmount(item)+"");
            items.add("-");
            items.add("+");
            double totalPrice = item.getPrice()*account.getCart().getItemAmount(item);
            items.add(totalPrice+"");
            tableModel.addRow(items.toArray());
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
        itemTable.setBounds(100, 400, 2200, 1000);
        itemTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = itemTable.rowAtPoint(e.getPoint());
                int col = itemTable.columnAtPoint(e.getPoint());

                if(col == 3){
                    System.out.println("Removing from Cart");
                    int q = Integer.parseInt(itemTable.getValueAt(row, 2).toString());
                    System.out.println("Amount " + q);
                    Item i = SmartShoppers.getInstance().getItem(itemTable.getValueAt(row, 0).toString());
                    Store s = account.getStore();
                    i.decreaseSuggestPoint();
                    Database.updateItemInformation(i, i.getDescription(), i.getPrice(), i.getTotalAmount(), i.getSale(), i.getSuggestPoint());
                    account.getCart().removeItem(i, 1);
                    s.addItem(SmartShoppers.getInstance().getItem(itemTable.getValueAt(row, 0).toString()), 1);
                    if(q <= 1){
                        tableModel.removeRow(row);
                    }
                    else{
                        tableModel.setValueAt(q - 1, row, 2);
                        itemTable.setValueAt((q - 1)*i.getPrice(), row, 5);
                    }
                    System.out.println("Done Removing from Cart");
                }
                else if(col == 4){
                    System.out.println("Adding more to Cart");
                    if(Integer.parseInt(itemTable.getValueAt(row, 2).toString()) >
                            account.getStore().getItemAmount(SmartShoppers.getInstance().getItem(itemTable.getValueAt(row,0).toString()))){
                        JOptionPane.showMessageDialog(null, "Not Enough In Stock");
                        return;
                    }
                    else{
                        Item i = SmartShoppers.getInstance().getItem(itemTable.getValueAt(row, 0).toString());
                        Store s = account.getStore();
                        i.increaseSuggestPoint();
                        Database.updateItemInformation(i, i.getDescription(), i.getPrice(), i.getTotalAmount(), i.getSale(), i.getSuggestPoint());
                        account.getCart().addItem(i);
                        s.removeItem(i, 1);
                        int newQuantity = Integer.parseInt(itemTable.getValueAt(row, 2).toString()) + 1;
                        itemTable.setValueAt(newQuantity, row, 2);
                        itemTable.setValueAt(newQuantity*i.getPrice(), row, 5);
                    }
                    System.out.println("Done Adding more to Cart");
                }
                //removing from JTable
                itemTable.revalidate();
                itemTable.repaint();
            }
        });

        JScrollPane itemTableScrollPane = new JScrollPane(itemTable);
        itemTableScrollPane.setBounds(100, 400, 2200, 1000);

        checkOut = new JButton("Check Out");
        checkOut.setFont(new Font("Consolas", Font.PLAIN, 35));
        checkOut.setFocusable(false);//look at this later...
        checkOut.setBounds(100, 1600, 500, 100);
        checkOut.setBorder(null);
        checkOut.setBackground(new Color(0x695E93));
        checkOut.addActionListener(this);

        this.add(itemTableScrollPane);
        this.add(checkOut);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == checkOut && JOptionPane.showConfirmDialog(this, "Check Out?") == 0){
            for(Item i : account.getCart().getItems()){
                Database.updateItemStore(i, account.getStore(),
                        account.getStore().getItemAmount(i) - account.getCart().getItemAmount(i));
            }
            account.getCart().clearCart();
            itemTable.removeAll();
            itemTable.revalidate();
            itemTable.repaint();
            closePanel();
        }
    }

    private void closePanel(){
        frame.remove(frame.getCurrentPanel());
        frame.setCurrentPanel(new CustomerHomePanel(account));
        frame.add(frame.getCurrentPanel());
        frame.repaint();
    }
}
