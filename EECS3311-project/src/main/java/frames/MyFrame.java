package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    JButton button;

    MyFrame() {
        button = new JButton();
        button.setBounds(100, 100, 250, 100);
        button.addActionListener(this);
        button.setText("button");
        button.setFocusable(false);//gets rid of the border thing that was around the text "button"
        //button.addActionListener(e -> System.out.println("button"));//using a "lamda" expression instead of using the normal way
        //you can also set the icon for the button using "button.setIcon("icon")", where "ImageIcon icon = nw ImageIcon("image directory")"
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setForeground(Color.cyan);
        button.setBackground(Color.lightGray);
        button.setBorder(BorderFactory.createEtchedBorder());//gives it a bit of a 3d effect
        button.setEnabled(true);//set to false if you don't want it to be enabled

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            System.out.println("MyFrame button");
        }
    }
}
