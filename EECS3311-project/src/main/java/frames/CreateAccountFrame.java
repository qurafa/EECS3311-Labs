package frames;

import project.Account;
import project.AdminAccount;
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
    JLabel firstNameLabel;
    JTextField firstName;
    JLabel lastNameLabel;
    JTextField lastName;
    JLabel usernameLabel;
    JTextField username;
    JLabel passwordLabel;
    JPasswordField password;
    JLabel confirmPasswordLabel;
    JPasswordField confirmPassword;
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

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        firstNameLabel.setBounds(250, 125, 500, 100);

        firstName = new JTextField();
        firstName.setPreferredSize(new Dimension(250,40));
        firstName.setFont(new Font("Consolas", Font.PLAIN, 35));
        firstName.setForeground(new Color(0x00FF00));
        firstName.setBackground(Color.black);
        firstName.setCaretColor(Color.white);
        firstName.setBounds(250, 200, 500, 100);

        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        lastNameLabel.setBounds(250, 325, 500, 100);

        lastName = new JTextField();
        lastName.setPreferredSize(new Dimension(250,40));
        lastName.setFont(new Font("Consolas", Font.PLAIN, 35));
        lastName.setForeground(new Color(0x00FF00));
        lastName.setBackground(Color.black);
        lastName.setCaretColor(Color.white);
        lastName.setBounds(250, 400, 500, 100);

        usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        usernameLabel.setBounds(250, 525, 500, 100);

        username = new JTextField();
        username.setPreferredSize(new Dimension(250,40));
        username.setFont(new Font("Consolas", Font.PLAIN, 35));
        username.setForeground(new Color(0x00FF00));
        username.setBackground(Color.black);
        username.setCaretColor(Color.white);
        username.setBounds(250, 600, 500, 100);

        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        passwordLabel.setBounds(250, 725, 500, 100);

        password = new JPasswordField();
        password.setPreferredSize(new Dimension(250,40));
        password.setFont(new Font("Consolas", Font.PLAIN, 35));
        password.setForeground(new Color(0x00FF00));
        password.setBackground(Color.black);
        password.setCaretColor(Color.white);
        password.setBounds(250, 800, 500, 100);

        confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setText("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        confirmPasswordLabel.setBounds(800, 725, 500, 100);

        confirmPassword = new JPasswordField();
        confirmPassword.setPreferredSize(new Dimension(250,40));
        confirmPassword.setFont(new Font("Consolas", Font.PLAIN, 35));
        confirmPassword.setForeground(new Color(0x00FF00));
        confirmPassword.setBackground(Color.black);
        confirmPassword.setCaretColor(Color.white);
        confirmPassword.setBounds(800, 800, 500, 100);

        verificationCodeLabel = new JLabel();
        verificationCodeLabel.setText("Verification Code");
        verificationCodeLabel.setFont(new Font("Consolas", Font.PLAIN, 35));
        verificationCodeLabel.setBounds(250, 925, 500, 100);

        verificationCode = new JTextField();
        verificationCode.setPreferredSize(new Dimension(250,40));
        verificationCode.setFont(new Font("Consolas", Font.PLAIN, 35));
        verificationCode.setForeground(new Color(0x00FF00));
        verificationCode.setBackground(Color.black);
        verificationCode.setCaretColor(Color.white);
        verificationCode.setBounds(250, 1000, 500, 100);

//        createAccountButton = new JButton("Create Account");
//        createAccountButton.setFont(new Font("Consolas", Font.PLAIN, 35));
//        createAccountButton.setFocusable(true);//look at this later...
//        createAccountButton.addActionListener(this);
//        createAccountButton.setBounds(350, 750, 300, 50);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        createAccountButton.setFocusable(true);//look at this later...
        createAccountButton.addActionListener(this);
        createAccountButton.setBounds(350, 1125, 300, 50);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(1500, 1300));
        this.setResizable(false);
        this.setVisible(true);
        this.add(selectRoleLabel);
        this.add(userRoleRadioButton);
        this.add(managerRoleRadioButton);
        this.add(adminRoleRadioButton);
        this.add(firstNameLabel);
        this.add(firstName);
        this.add(lastNameLabel);
        this.add(lastName);
        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(confirmPasswordLabel);
        this.add(confirmPassword);
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

            createAccountButton.setBounds(350, 925, 300, 50);

            verificationCodeLabel.updateUI();
            verificationCode.updateUI();
            createAccountButton.updateUI();
        }
        else if(e.getSource() == managerRoleRadioButton){
            verificationCodeLabel.setVisible(true);
            verificationCode.setVisible(true);

            createAccountButton.setBounds(350, 1125, 300, 50);

            verificationCodeLabel.updateUI();
            verificationCode.updateUI();
            createAccountButton.updateUI();
        }
        else if(e.getSource() == adminRoleRadioButton){
            verificationCodeLabel.setVisible(true);
            verificationCode.setVisible(true);

            createAccountButton.setBounds(350, 1125, 300, 50);

            verificationCodeLabel.updateUI();
            verificationCode.updateUI();
            createAccountButton.updateUI();
        }

        if(e.getSource() == createAccountButton){
            System.out.println("create account");

            //TODO verify and make the actual account...
            //verifies the username and password and loads the pop up if need be
            if (!checkInputs()) return;

            if(userRoleRadioButton.isSelected()) {
                Account account = SmartShoppers.getInstance().createAccount(0, firstName.getText(), lastName.getText(), username.getText(), String.valueOf(password.getPassword()));
                this.setVisible(false);
                new SelectLocationFrame(account, this);
            }
            else if(managerRoleRadioButton.isSelected()){
                if(!SmartShoppers.getInstance().verifyCode(0, Integer.parseInt(verificationCode.getText()))){
                    JOptionPane.showMessageDialog(this, "Invalid Verification Code");
                    return;
                }

                Account account = SmartShoppers.getInstance().createAccount(1, firstName.getText(), lastName.getText(),  username.getText(), String.valueOf(password.getPassword()));
                this.setVisible(false);
                new SelectLocationFrame(account, this);
            }
            else if(adminRoleRadioButton.isSelected()){
                if(!SmartShoppers.getInstance().verifyCode(1, Integer.parseInt(verificationCode.getText()))) {
                    JOptionPane.showMessageDialog(this, "Invalid Verification Code");
                    return;
                }

                Account account = SmartShoppers.getInstance().createAccount(2, firstName.getText(), lastName.getText(),  username.getText(), String.valueOf(password.getPassword()));
                this.setVisible(false);
                new AdminAccountFrame((AdminAccount) account);
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

    private boolean checkInputs(){
        boolean output = true;

        if(!SmartShoppers.getInstance().verifyNewUsername(username.getText())){
            JOptionPane.showMessageDialog(this, "Invalid Username");
            output = false;
        }

        if(!SmartShoppers.getInstance().verifyNewPassword(String.valueOf(password.getPassword())) ||
                !SmartShoppers.getInstance().verifyNewPassword(String.valueOf(confirmPassword.getPassword()))) {
            JOptionPane.showMessageDialog(this, "Invalid Password");
            output = false;
        }

        if(username.getText().length() == 0){
            JOptionPane.showMessageDialog(this, "Empty Username Field");
            output = false;
        }

        if(String.valueOf(password.getPassword()).length() == 0 || String.valueOf(confirmPassword.getPassword()).length() == 0){
            JOptionPane.showMessageDialog(this, "Empty Password Field");
            output = false;
        }

        if(!String.valueOf(password.getPassword()).equals(String.valueOf(confirmPassword.getPassword()))){
            JOptionPane.showMessageDialog(this, "Passwords Do Not Match");
            output = false;
        }

        return output;
    }
}
