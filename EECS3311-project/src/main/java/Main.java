import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){

        /////////JLabel/////////
        //a gui display area for text, image or both
        ImageIcon image = new ImageIcon("Sig3 copy.png");

        JLabel label = new JLabel();//create a label
        label.setText("setting text");//set text of label
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);//can set in LEFT, RIGHT, or CENTER of imageicon
        label.setVerticalTextPosition(JLabel.TOP);//can set inTOP, BOTTOM, CENTER of imageicon

        JFrame frame = new JFrame();//making JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setting it so the program ends when the exit key is pressed
        frame.setSize(500, 500);//setting the size of the frame
        frame.setVisible(true);//setting the frame to be visible

        frame.add(label);

        System.out.println("done");
        /////////JFrame/////////
//        JFrame frame = new JFrame();//making JFrame
//        frame.setTitle("JFrame Test");//settign JFrame title
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setting it so the program ends when the exit key is pressed
//        //normally HIDE_ON_CLOSE by default
//        frame.setResizable(false);//whether we can resize the frame or not...
//        frame.setSize(420, 420);//setting the size of the frame
//        frame.setVisible(true);//setting the frame to be visible
//
//        ImageIcon image = new ImageIcon("Sig3 copy.png");//create an image icon
//        frame.setIconImage(image.getImage());//set icon of frame
//
//        frame.getContentPane().setBackground(new Color(0,0,0));//set panel background
    }

}
