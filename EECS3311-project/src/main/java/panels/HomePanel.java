package panels;

import project.SmartShoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    //todo add the observer for when the values change...

    JLabel customerCountValueLabel;
    JLabel customerCountLabel;
    JLabel managerCountValueLabel;
    JLabel managerCountLabel;
    JLabel adminCountValueLabel;
    JLabel adminCountLabel;
    JLabel storeCountValueLabel;
    JLabel storeCountLabel;
    JLabel itemCountValueLabel;
    JLabel itemCountLabel;

    //Admin Home panel
    JPanel customerPanel;
    JPanel managerPanel;
    JPanel adminPanel;
    JPanel storePanel;
    JPanel itemPanel;

    public HomePanel() {
        this.setBackground(Color.white);
        this.setBounds(500,100,2400,1900);
        this.setLayout(null);
    }

    public HomePanel(int x, int y, int width, int height) {
        this();
        this.setBounds(x,y,width,height);
        this.updateUI();
    }
}
