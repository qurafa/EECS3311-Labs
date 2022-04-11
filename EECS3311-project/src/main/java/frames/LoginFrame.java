package frames;

import project.AdminAccount;
import project.CustomerAccount;
import project.ManagerAccount;
import project.SmartShoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    JFrame from;
    JTextField username;
    JPasswordField password;
    JButton submitButton;
    JButton createAccountButton;//to create an account and log you in...
    JButton backButton;

    public LoginFrame() {
        System.out.println("Start LoginFrame");

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

        password = new JPasswordField();
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

        submitButton = new JButton("Login");
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
        if (e.getSource() == submitButton && SmartShoppers.getInstance().verifyLogin(username.getText(), String.valueOf(password.getPassword()))) {
            System.out.println("Logging In...");
            if(SmartShoppers.getInstance().getAccountType(username.getText()) == 2)
                new AdminAccountFrame((AdminAccount) SmartShoppers.getInstance().getAccount(username.getText()));
            else if(SmartShoppers.getInstance().getAccountType(username.getText()) == 1)
                new ManagerAccountFrame((ManagerAccount) SmartShoppers.getInstance().getAccount(username.getText()));
            else if(SmartShoppers.getInstance().getAccountType(username.getText()) == 0)
                new CustomerAccountFrame((CustomerAccount) SmartShoppers.getInstance().getAccount(username.getText()));
            else
                System.out.println("Wrong Account, LoginFrame " + SmartShoppers.getInstance().getAccountType(username.getText()));
            System.out.println("Not Logging In...");
            this.dispose();
        }else if(e.getSource() == submitButton){
            if(!SmartShoppers.getInstance().verifyUsername(username.getText()))
                JOptionPane.showMessageDialog(this, "Invalid Username");
            if(!SmartShoppers.getInstance().verifyPassword(username.getText(), String.valueOf(password.getPassword())))
                JOptionPane.showMessageDialog(this, "Invalid Password");
        }

        if(e.getSource() == createAccountButton){
            //TODO bring up create account page...
            System.out.println("Creating Account...");
            this.setVisible(false);
            new CreateAccountFrame(username.getText(), String.valueOf(password.getPassword()), this);
        }

        if(e.getSource() == backButton){
            System.out.println("Back from Login");
            this.setVisible(false);
            from.setVisible(true);
        }

    }
}
