package project;

public abstract class Account {
    private int accountID;
    private String username;
    private String password;
    private int accountCode;
    private boolean loggedIn;//maybe remove later, for the ability to log in from different accounts

    //Account constructor
    public Account(){
        setAccountCode();
    }

    public void setID(int id){
        accountID = id;
    }

    public int getID(){
        return accountID;
    }

    //setting the username for this account
    public void setUserName(String username){
        this.username = username;
    }

    //getting the username attached to the account
    public String getUserName(){
        return this.username;
    }

    //setting the password for this account
    public void setPassword(String password){
        this.password = password;
    }

    //getting the password attached to the account
    public String getPassword(){
        return this.password;
    }

    //setting a specific code for the account
    //changing it from an already existing one, if already exists and setting a new one if it doesn't
    //return it only after it has been set
    public int setAccountCode(){
        return -1;
    }

    public void setAccountCode(int code){
        accountCode = code;
    }

    public int getAccountCode(){
        return accountCode;
    }

    private void searchItem(){}

    private void searchLocation(){}

    private void getAccountInfo(){}

    private void delete(){}
}
