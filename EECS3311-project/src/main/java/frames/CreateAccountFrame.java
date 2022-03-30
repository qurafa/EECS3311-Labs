package frames;

import project.Account;
import project.SmartShoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountFrame extends JFrame implements ActionListener {

    JFrame from;
    JRadioButton userRoleRadioButton;
    JRadioButton managerRoleRadioButton;
    JRadioButton adminRoleRadioButton;
    JLabel usernameLabel;
    JTextField username;
    JLabel passwordLabel;
    JTextField password;
    JLabel verificationCodeLabel;
    JTextField verificationCode;
    JButton createAccountButton;//to create an account and log you in...
    JButton backButton;

    CreateAccountFrame(){
        System.out.println("Start CreateAccountFrame");

        JLabel selectRoleLabel = new JLabel();
        selectRoleLabel.setText("Select Role");
        selectRoleLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        selectRoleLabel.setBounds(100, 0, 225, 75);

        userRoleRadioButton = new JRadioButton("User");
        userRoleRadioButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        userRoleRadioButton.setBounds(350,0,120,75);
        userRoleRadioButton.setFocusable(false);
        userRoleRadioButton.addActionListener(this);

        managerRoleRadioButton = new JRadioButton("Manager");
        managerRoleRadioButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        managerRoleRadioButton.setBounds(470,0,160,75);
        managerRoleRadioButton.setFocusable(false);
        managerRoleRadioButton.addActionListener(this);

        adminRoleRadioButton = new JRadioButton("Admin");
        adminRoleRadioButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        adminRoleRadioButton.setBounds(645,0,120,75);
        adminRoleRadioButton.setFocusable(false);
        adminRoleRadioButton.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(userRoleRadioButton);
        group.add(managerRoleRadioButton);
        group.add(adminRoleRadioButton);

        usernameLabel = new JLabel();
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

        passwordLabel = new JLabel();
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

        verificationCodeLabel = new JLabel();
        verificationCodeLabel.setText("Verification Code");
        verificationCodeLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        verificationCodeLabel.setBounds(250, 525, 500, 100);

        verificationCode = new JTextField();
        verificationCode.setPreferredSize(new Dimension(250,40));
        verificationCode.setFont(new Font("Consolas", Font.PLAIN, 35));
        verificationCode.setForeground(new Color(0x00FF00));
        verificationCode.setBackground(Color.black);
        verificationCode.setCaretColor(Color.white);
        verificationCode.setBounds(250, 600, 500, 100);

//        createAccountButton = new JButton("Create Account");
//        createAccountButton.setFont(new Font("Consolas", Font.PLAIN, 35));
//        createAccountButton.setFocusable(true);//look at this later...
//        createAccountButton.addActionListener(this);
//        createAccountButton.setBounds(350, 750, 300, 50);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        createAccountButton.setFocusable(true);//look at this later...
        createAccountButton.addActionListener(this);
        createAccountButton.setBounds(350, 550, 300, 50);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(1000, 1000));
        this.setResizable(false);
        this.setVisible(true);
        this.add(selectRoleLabel);
        this.add(userRoleRadioButton);
        this.add(managerRoleRadioButton);
        this.add(adminRoleRadioButton);
        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(verificationCodeLabel);
        this.add(verificationCode);
        this.add(createAccountButton);

        System.out.println("Done CreateAccountFrame");

        userRoleRadioButton.doClick();
    }

    CreateAccountFrame(JFrame from){
        this();
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

    CreateAccountFrame(String username, String password) {
        this();
        this.username.setText(username);
        this.password.setText(password);
    }

    CreateAccountFrame(String username, String password, JFrame from){
        this(from);
        this.username.setText(username);
        this.password.setText(password);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == userRoleRadioButton){
            verificationCodeLabel.setVisible(false);
            verificationCode.setVisible(false);

            createAccountButton.setBounds(350, 550, 300, 50);

            verificationCodeLabel.updateUI();
            verificationCode.updateUI();
            createAccountButton.updateUI();
        }
        else if(e.getSource() == managerRoleRadioButton){
            verificationCodeLabel.setVisible(true);
            verificationCode.setVisible(true);

            createAccountButton.setBounds(350, 750, 300, 50);

            verificationCodeLabel.updateUI();
            verificationCode.updateUI();
            createAccountButton.updateUI();
        }
        else if(e.getSource() == adminRoleRadioButton){
            verificationCodeLabel.setVisible(true);
            verificationCode.setVisible(true);

            createAccountButton.setBounds(350, 750, 300, 50);

            verificationCodeLabel.updateUI();
            verificationCode.updateUI();
            createAccountButton.updateUI();
        }

        if(e.getSource() == createAccountButton){
            System.out.println("create account");

            //TODO verify and make the actual account...
            //verifies the username and password and loads the pop up if need be

            if(!SmartShoppers.getInstance().verifyNewUsername(username.getText()))
                JOptionPane.showMessageDialog(null, "Invalid Username");
            if(!SmartShoppers.getInstance().verifyNewPassword(password.getText()))
                JOptionPane.showMessageDialog(null, "Invalid Password");
            if(username.getText().length() == 0)
                JOptionPane.showMessageDialog(null, "Empty Username Field");
            if(password.getText().length() == 0)
                JOptionPane.showMessageDialog(null, "Empty Password Field");

            if (!SmartShoppers.getInstance().verifyNewUsername(username.getText()) ||
                    !SmartShoppers.getInstance().verifyNewPassword(password.getText()) ||
                    username.getText().length() == 0 ||
                    password.getText().length() == 0)
                return;

            if(userRoleRadioButton.isSelected()) {
                SmartShoppers.getInstance().createAccount(2, username.getText(), password.getText());
                this.setVisible(false);
                new SelectLocationFrame(0, this);
            }
            else if(managerRoleRadioButton.isSelected()){
                if(!SmartShoppers.getInstance().verifyCode(1, Integer.parseInt(verificationCode.getText())))
                    return;

                SmartShoppers.getInstance().createAccount(1, username.getText(), password.getText());
                this.setVisible(false);
                new SelectLocationFrame(1, this);
            }
            else if(adminRoleRadioButton.isSelected()){
                if(!SmartShoppers.getInstance().verifyCode(0, Integer.parseInt(verificationCode.getText())))
                    return;

                Account a = SmartShoppers.getInstance().createAccount(0, username.getText(), password.getText());
                this.setVisible(false);
                new AdminHomePageFrame(a);
            }
            else{
                System.out.println("NO ROLE SELECTED");
            }

        }

        if(e.getSource() == backButton){
            this.setVisible(false);
            from.setVisible(true);
        }
    }
}
