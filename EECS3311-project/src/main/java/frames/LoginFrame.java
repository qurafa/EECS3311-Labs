package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    JFrame from;
    JTextField username;
    JTextField password;
    JButton submitButton;
    JButton createAccountButton;//to create an account and log you in...
    JButton backButton;

    public LoginFrame() {
        System.out.println("Start LoginFrame");

        //TODO add background to page
        //TODO add LOGIN words at top of page

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        usernameLabel.setBounds(250, 125, 500, 100);

        username = new JTextField();
        username.setPreferredSize(new Dimension(250,40));
        username.setFont(new Font("Consolas", Font.PLAIN, 35));
        username.setForeground(new Color(0x00FF00));
        username.setBackground(Color.black);
        username.setCaretColor(Color.white);
        username.setBounds(250, 200, 500, 100);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        passwordLabel.setBounds(250, 325, 500, 100);

        password = new JTextField();
        password.setPreferredSize(new Dimension(250,40));
        password.setFont(new Font("Consolas", Font.PLAIN, 35));
        password.setForeground(new Color(0x00FF00));
        password.setBackground(Color.black);
        password.setCaretColor(Color.white);
        password.setBounds(250, 400, 500, 100);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        createAccountButton.setFocusable(true);//look at this later...
        createAccountButton.addActionListener(this);
        createAccountButton.setBounds(250, 550, 300, 50);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        submitButton.setFocusable(true);//look at this later...
        submitButton.addActionListener(this);
        submitButton.setBounds(400, 650, 200, 50);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(1000, 1000));
        this.setResizable(false);
        this.setVisible(true);
        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(createAccountButton);
        this.add(submitButton);

        System.out.println("Done LoginFrame");
    }

    LoginFrame(JFrame from){
        this();
        this.from = from;

        backButton = new JButton("< Back");
        backButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        backButton.setFocusable(true);//look at this later...
        backButton.addActionListener(this);
        backButton.setBounds(25, 825, 150, 50);

        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton && username.getText().length() > 0 && password.getText().length() > 0) {
            System.out.println("Logging in...");
        }
        if(e.getSource() == createAccountButton){
            //TODO bring up create account page...
            System.out.println("Creating Account...");
            this.setVisible(false);
            new CreateAccountFrame(username.getText(), password.getText(), this);
        }
        if(e.getSource() == backButton){
            System.out.println("Back from Login");
            this.setVisible(false);
            from.setVisible(true);
        }

    }
}
