package frames;

import project.SmartShoppers;
import project.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectLocationFrame extends JFrame implements ActionListener {

    JFrame from;
    JButton backButton;
    JButton doneButton;

    //TODO add observer for when the store is updated or another store is added or removed

    public SelectLocationFrame(int type){
        if(type == 0 || type == 1){
            System.out.println("Start Select Location Frame");

            JLabel selectStoreLabel = new JLabel();
            selectStoreLabel.setText(type == 0 ?
                    "Select Preferred Shopping Location" :
                    "Select Assigned Store Location");
            selectStoreLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
            selectStoreLabel.setBounds(175, 125, 650, 100);

            List<Store> store = SmartShoppers.getInstance().getStores();

            List<String> storesString = new ArrayList<String>();

            for (Store s : store){
                storesString.add(s.getLocation().toString());
            }

            JComboBox<String> storeComboBox = new JComboBox(storesString.toArray());
            storeComboBox.setBounds(175,250,650,100);

            doneButton = new JButton("Done >");
            doneButton.setFont(new Font("Consolas", Font.PLAIN, 35));
            doneButton.setFocusable(true);//look at this later...
            doneButton.addActionListener(this);
            doneButton.setBounds(425, 450, 150, 50);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(new Dimension(1000, 1000));
            this.setResizable(false);
            this.setVisible(true);
            this.add(selectStoreLabel);
            this.add(storeComboBox);
            this.add(doneButton);

            System.out.println("Done Select Location Frame");
        }
        else{
            System.out.println("Incorrect type SelectLocationFrame");
        }
    }

    public SelectLocationFrame(int type, JFrame from){
        this(type);
        this.from = from;

        backButton = new JButton("< Back");
        backButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        backButton.setFocusable(true);//look at this later...
        backButton.addActionListener(this);
        backButton.setBounds(25, 825, 150, 50);
        backButton.setBorder(null);
        backButton.setBackground(Color.white);

        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            this.setVisible(false);
            from.setVisible(true);
        }
    }
}
