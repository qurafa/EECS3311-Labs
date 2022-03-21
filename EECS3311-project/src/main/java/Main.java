import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    public static void main(String[] args){

        /////////JPanel/////////
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0,0,250,250);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.red);
        bluePanel.setBounds(250,250,250,250);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.red);
        greenPanel.setBounds(250,250,500,250);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750,750);
        frame.setVisible(true);
        frame.add(redPanel); frame.add(bluePanel);; frame.add(greenPanel);

        System.out.println("done");
        Test.TestRun();
        /////////JLabel/////////
//        //a gui display area for text, image or both
//        ImageIcon image = new ImageIcon("Sig3 copy.png");
//        Border border = BorderFactory.createLineBorder(Color.green, 3);
//
//        JLabel label = new JLabel();//create a label
//        label.setText("setting text");//set text of label
//        label.setIcon(image);//set image within the label
//        label.setHorizontalTextPosition(JLabel.CENTER);//can set in LEFT, RIGHT, or CENTER of image icon
//        label.setVerticalTextPosition(JLabel.TOP);//can set text in TOP, BOTTOM, CENTER of image icon
//        label.setForeground(Color.MAGENTA);//can set the font color of text
//        label.setFont(new Font("MV Boli", Font.PLAIN, 20));//setting the font name, style, size etc...
//        label.setIconTextGap(-25);//sets how far the text should be from the image/gap between text and image, can also be positive or negative
//        label.setBackground(Color.black);//setting the background` color, that is visible as long as the label is opaque
//        label.setOpaque(true);//NOTE: the label takes up as much room as possible
//        label.setBorder(border);//setting the border of our label
//        label.setVerticalAlignment(JLabel.CENTER);//setting the vertical position of the icon+text within the label, but you can still modify their positions within each other
//        label.setHorizontalAlignment(JLabel.CENTER);//setting the horizontal position of the icon+text within the label, but you can still modify their positions within each other
//        //label.setBounds(100,100,500,500);//set x,y positions within a frame, as well as dimensions, especially needed since we set our frame layout to null
//
//        JFrame frame = new JFrame();//making JFrame
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setting it so the program ends when the exit key is pressed
//        //frame.setSize(500, 500);//setting the size of the frame
//        frame.setVisible(true);//setting the frame to be visible
//        //frame.setLayout(null);//by setting this to "null", we will now have to specify the exact positions of what is put in our frame
//        frame.add(label);
//        frame.pack();//doing this does all the resizing for us, and it automatically sizes everything to fit into view, so now we don't have  to "frame.setSize()" or "frame.setLayout" or "label.setBounds" etc.
//        //NOTE: also make sure that you add all the components before packing i.e. "label.pack()"
//        frame.setResizable(false);

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
