package panels;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {

    JLabel panelLabel;

    public SidePanel(String name){
        this.setBackground(Color.white);
        this.setBounds(500,100,2400,1900);
        this.setLayout(null);

        panelLabel = new JLabel();
        panelLabel.setText(name);
        panelLabel.setFont(new Font("Consolas", Font.PLAIN, 150));
        panelLabel.setBackground(Color.white);
        panelLabel.setBounds(100, 100, 750, 200);

        this.add(panelLabel);
    }

    public SidePanel(String name, int x, int y, int width, int height){
        this(name);

        panelLabel.setBounds(x, y, width, height);
        this.repaint();
    }

}
