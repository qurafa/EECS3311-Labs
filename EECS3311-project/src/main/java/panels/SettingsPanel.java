package panels;

import frames.AccountFrame;
import project.Account;
import project.Database;
import project.SmartShoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends SidePanel implements ActionListener{

    Account account;
    JLabel firstNameLabel;
    JTextField firstName;
    JLabel lastNameLabel;
    JTextField lastName;
    JLabel usernameLabel;
    JTextField username;
    JLabel passwordLabel;
    JPasswordField password;
    JLabel accountCodeLabel;
    JTextField accountCode;

    JButton saveButton;
    JButton deleteAccountButton;

    AccountFrame frame;

    public SettingsPanel(Account account, AccountFrame frame){
        super("Settings");
        this.account = account;
        this.frame = frame;

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        firstNameLabel.setBackground(Color.white);
        firstNameLabel.setBounds(100, 400, 500, 100);

        firstName = new JTextField();
        firstName.setText(account.getFirstName());
        firstName.setPreferredSize(new Dimension(250,100));
        firstName.setFont(new Font("Consolas", Font.PLAIN, 35));
        firstName.setForeground(new Color(0x00FF00));
        firstName.setBackground(Color.black);
        firstName.setCaretColor(Color.white);
        firstName.setBounds(100, 500, 500, 100);

        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        lastNameLabel.setBackground(Color.white);
        lastNameLabel.setBounds(700, 400, 500, 100);

        lastName = new JTextField();
        lastName.setText(account.getLastName());
        lastName.setPreferredSize(new Dimension(250,100));
        lastName.setFont(new Font("Consolas", Font.PLAIN, 35));
        lastName.setForeground(new Color(0x00FF00));
        lastName.setBackground(Color.black);
        lastName.setCaretColor(Color.white);
        lastName.setBounds(700, 500, 500, 100);

        usernameLabel = new JLabel("User Name");
        usernameLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        usernameLabel.setBackground(Color.white);
        usernameLabel.setBounds(100, 700, 500, 100);

        username = new JTextField();
        username.setText(account.getUserName());
        username.setPreferredSize(new Dimension(250,100));
        username.setFont(new Font("Consolas", Font.PLAIN, 35));
        username.setForeground(new Color(0x00FF00));
        username.setBackground(Color.black);
        username.setBounds(100, 800, 500, 100);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        passwordLabel.setBackground(Color.white);
        passwordLabel.setBounds(700, 700, 500, 100);

        password = new JPasswordField();
        password.setText(account.getPassword());
        password.setPreferredSize(new Dimension(250,100));
        password.setFont(new Font("Consolas", Font.PLAIN, 35));
        password.setForeground(new Color(0x00FF00));
        password.setBackground(Color.black);
        password.setBounds(700, 800, 500, 100);

        accountCodeLabel = new JLabel("Account Code");
        accountCodeLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        accountCodeLabel.setBackground(Color.white);
        accountCodeLabel.setBounds(100, 1000, 500, 100);

        accountCode = new JTextField();
        accountCode.setText(account.getAccountCode()+"");
        accountCode.setPreferredSize(new Dimension(250,100));
        accountCode.setFont(new Font("Consolas", Font.PLAIN, 35));
        accountCode.setForeground(new Color(0x00FF00));
        accountCode.setBackground(Color.black);
        accountCode.setBounds(100, 1100, 500, 100);
        accountCode.setEditable(false);

        saveButton = new JButton("Update");
        saveButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        saveButton.setFocusable(false);//look at this later...
        saveButton.addActionListener(this);
        saveButton.setBounds(100, 1300, 500, 100);
        saveButton.setBorder(null);
        saveButton.setBackground(new Color(0x695E93));

        deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setFont(new Font("Consolas", Font.PLAIN, 35));
        deleteAccountButton.setFocusable(false);//look at this later...
        deleteAccountButton.addActionListener(this);
        deleteAccountButton.setBounds(700, 1300, 500, 100);
        deleteAccountButton.setBorder(null);
        deleteAccountButton.setBackground(new Color(0x695E93));

        this.add(firstNameLabel);
        this.add(firstName);
        this.add(lastNameLabel);
        this.add(lastName);
        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(accountCodeLabel);
        this.add(accountCode);
        this.add(saveButton);
        this.add(deleteAccountButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton &&
                settingChange() &&
                JOptionPane.showConfirmDialog(this, "Update Account Information?","", JOptionPane.YES_NO_OPTION) == 0){
            System.out.println("Setting change");

            if(!SmartShoppers.getInstance().verifyNewUsername(username.getText())){
                JOptionPane.showMessageDialog(this, "Invalid Username");
                return;
            }

            Database.updateAccountInformation(account, firstName.getText(), lastName.getText(), username.getText(), String.valueOf(password.getPassword()));
            account.setFirstName(firstName.getText());
            account.setLastName(lastName.getText());
            account.setUserName(username.getText());
            account.setPassword(String.valueOf(password.getPassword()));
        }
        else if(e.getSource() == deleteAccountButton &&
                JOptionPane.showConfirmDialog(this, "Are you sure??!!","", JOptionPane.YES_NO_OPTION) == 0){
            SmartShoppers.getInstance().deleteAccount(account);
            Database.deleteAccount(account.getID());
            frame.dispose();
        }
    }

    private boolean settingChange(){
        if(firstNameChange())
            return true;
        if(lastNameChange())
            return true;
        if(userNameChange())
            return true;
        if(passwordChange())
            return true;

        return false;
    }

    private boolean firstNameChange(){
        return !firstName.getText().equals(account.getFirstName());
    }

    private boolean lastNameChange(){
        return !lastName.getText().equals(account.getLastName());
    }

    private boolean userNameChange(){
        return !username.getText().equals(account.getUserName());
    }

    private boolean passwordChange(){
        return !String.valueOf(password.getPassword()).equals(account.getPassword());
    }


}
