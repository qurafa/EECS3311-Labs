import frames.LoginFrame;
import project.Database;
import project.SmartShoppers;

import java.awt.*;

public class Main {

    public static void main(String[] args){
        //TODO setting the sizes based on the screen size
        ///////////Project test///////////
        //testing getting the screen size...
//        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//        System.out.println("Screen Size: " + size.toString());

        //initialize database
        Database.createDatabase();
        Database.initTables();

        //create smart shoppers system
        SmartShoppers smartShoppers = SmartShoppers.getInstance();

        //bring up login frame to get started...
        new LoginFrame();

        ///////////SwingTutorial///////////
        /////////Textfield/////////
        //MyFrame frame = new MyFrame();

        /////////JButton/////////
        // the button triggers and action listener event
        //new MyFrame();

        /////////JPanel/////////
//        JLabel label = new JLabel();
//        label.setText("HI!!!");
//        label.setVerticalAlignment(JLabel.TOP);
//        label.setHorizontalAlignment(JLabel.LEFT);
//        label.setBounds(0,0,75,75);
//        //using setBounds when the layout for its container is set to null can be used to set the position
//        //relative to the corner of the container it is in, where the corner is (0,0)
//
//        JPanel redPanel = new JPanel();
//        redPanel.setBackground(Color.red);
//        redPanel.setBounds(0,0,250,250);
//        redPanel.setLayout(null);
//        //redPanel.setLayout(new BorderLayout());
//
//        JPanel bluePanel = new JPanel();
//        bluePanel.setBackground(Color.blue);
//        bluePanel.setBounds(250,0,250,250);
//        bluePanel.setLayout(null);
//        //bluePanel.setLayout(new BorderLayout());
//
//        JPanel greenPanel = new JPanel();
//        greenPanel.setBackground(Color.green);
//        greenPanel.setBounds(0,250,500,250);
//        greenPanel.setLayout(null);
//        //greenPanel.setLayout(new BorderLayout());
//        //using the BorderLayout initially sets the added things to the left horizontally and
//        //centered vertically by default, but we can set the vertical and horizontal alignment using the label.setVerticalAlignment/setHorizontalAlignment
//
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);
//        frame.setSize(750,750);
//        frame.setVisible(true);
//        //adding the label to our panels
//        //greenPanel.add(label);
//        bluePanel.add(label);
//        //redPanel.add(label);
//        //adding the JPanels to the frame
//        frame.add(redPanel); frame.add(bluePanel); frame.add(greenPanel);

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
//        frame.setTitle("JFrame Test");//setting JFrame title
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
