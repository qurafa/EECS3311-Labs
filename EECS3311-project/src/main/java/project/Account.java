package project;

public abstract class Account {
    private String userName;
    private String password;

    //Account constructor
    public Account(){}

    //setting the username for this account
    private void setUserName(String userName){}

    //setting the password for this account
    private void setPassword(String password){}

    //deleting the account
    private void deleteAccount(){}

    abstract void createAccount();
}
