package project;

public abstract class Account {
    private int accountID;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private int accountCode;
    private Store store;
    private boolean loggedIn;//maybe remove later, for the ability to log in from different accounts

    //Account constructor
    public Account(){}

    public void setID(int id){
        accountID = id;
    }

    public int getID(){
        return accountID;
    }

    public void setFirstName(String firstName){
        this.firstname = firstName;
    }

    public String getFirstName(){
        return this.firstname;
    }

    public void setLastName(String lastname){
        this.lastname = lastname;
    }

    public String getLastName(){
        return this.lastname;
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
    public int resetAccountCode(){
        return -1;
    }

    public void setAccountCode(int code){
        accountCode = code;
    }

    public int getAccountCode(){
        return accountCode;
    }

    //Maybe divide it into the address,...,country or make a location class
    public void setStore(Store store){
        this.store = store;
    }

    public void setStore(String location){
        this.store = SmartShoppers.getInstance().getStore(location);
    }

    public Store getStore(){
        return store;
    }

    private void searchItem(){}

    private void searchLocation(){}

    private void getAccountInfo(){}

    private void delete(){}

    public String toString(){
        return this.getFirstName() + " " + this.getLastName() + " " + this.getUserName();
    }
}
