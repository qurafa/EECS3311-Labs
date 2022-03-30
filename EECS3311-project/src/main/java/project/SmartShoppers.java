package project;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartShoppers {
    private static final SmartShoppers sys = new SmartShoppers();
    private List<Item> ITEMS;//list of all items and their total amount in the SS System
    private List<Store> STORES;
    private List<Account> ACCOUNTS;
    private int lastItemID;///to keep track of the id of the last item

    public static SmartShoppers getInstance(){
        return sys;
    }

    //SmartShoppers private constructor
    private SmartShoppers(){
        System.out.println("Creating System...");
        initItems();
        initStores();
        initAccounts();
        System.out.println("Done Creating System...");
    }

    //to delete the system if you want to
    private void delete(){}

    //initializing the items from the database
    private void initItems(){
        System.out.println("Initializing Items...");
        ITEMS = Database.getAllItems();
        System.out.println("Done Initializing Items...");
    }

    //initializing the stores from the database
    private void initStores(){
        System.out.println("Initializing Stores...");
        STORES = Database.getAllStores();
        System.out.println("Done Initializing Stores...");
    }

    //initializing the Accounts from the database
    private void initAccounts(){
        System.out.println("Initializing Accounts...");
        ACCOUNTS = Database.getAllAccounts();
        System.out.println("Done Initializing Accounts...");
    }

    //to be used to verify the user making use of the system
    private boolean verifyAccount(String expected){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String actual = stackTrace[3].getClassName();

        return expected.equals(actual);
    }

    //creating an account, where type ranges from 0 to 2
    public Account createAccount(int type, String username, String password){
        Account account = null;
        if(type == 0){
            account = new AdminAccount();
        }
        else if(type == 1){
            account = new ManagerAccount();
        }
        else if(type == 2){
            account = new CustomerAccount();
        }

        account.setUserName(username);
        account.setPassword(password);
        //set the id and stuff in
        Database.addAccount(account);

        ACCOUNTS.add(account);

        return account;
    }

    //deleting an account
    private void deleteAccount(Account account){}

    //TODO getting an account based on the username
    private void getAccount(String username){
        long[] S = new long []{};
        int i = S.length;
    }

    //checking if the username is one that has been used before
    public boolean verifyUsername(String username){
        return false;
    }

    //checking if the username is one that has been used before
    public boolean verifyNewUsername(String username){
        for(Account a : ACCOUNTS){
            System.out.println("Account a: " + a.getUserName() + " == " + username + "?");
            if(a.getUserName().equals(username))
                return false;
        }
        return true;
    }

    public boolean verifyPassword(String password){
        return false;
    }

    public boolean verifyNewPassword(String password){
        return true;
    }

    //type is 0 for admin, type is 1 for manager
    public boolean verifyCode(int type, int code){
        if(type == 0)
            return code == 3305;
        if(type == 1)
            return code == 3305 || code == 4372;

        return false;
    }

    //TODO granting privilege to a specified manager
    private void grantPrivilege(Account manager){}

    //TODO revoking privilege from a specified manager
    private void revokePrivilege(Account manager){}

    //creating an item to the system without specifying the total amount
    private Item createItem(){
        return new Item();
    }

    //TODO deleting an item from the whole system
    private void deleteItem(Item item){}

    //adding an item to a specific store, if you don't specify the amount, all of it goes to the store
    //you can only add items that are already created
    private void addItem(Store store, Item item, int amount){
        store.addItem(item, amount);
    }

    //removing an item from a specific store, if you don't specify the amount, all of it is removed from the store
    private void removeItem(Store store, Item item, int amount){
        store.removeItem(item, amount);
    }

    //to get the total number of items in system
    private void getTotalNumberOfItem(){}

    //creating a store for the system
    private Store createStore(){
        return new Store();
    }

    //deleting a store from the system
    private void deleteStore(Store store){}

    //getting a list of all accounts
    private List<Account> getAccounts(){
        return ACCOUNTS;
    }

    public List<Account> getCustomerAccounts(){
        List <Account> output = new ArrayList<Account>();

        for(Account c : getAccounts())
            if(c.getClass().getName().toLowerCase().contains("customer")) output.add(c);

        return output;
    }

    public List<Account> getManagerAccounts(){
        List <Account> output = new ArrayList<Account>();

        for(Account c : getAccounts())
            if(c.getClass().getName().toLowerCase().contains("manager")) output.add(c);

        return output;
    }

    public List<Account> getAdminAccounts(){
        List <Account> output = new ArrayList<Account>();

        for(Account c : getAccounts())
            if(c.getClass().getName().toLowerCase().contains("admin")) output.add(c);

        return output;
    }

    //getting a list of all the stores
    public List<Store> getStores(){
        return STORES;
    }

    //getting a list of all the items, maybe based on specifications given
    private List<Item> getItems(){
        return new ArrayList<Item>(ITEMS);
    }

    private int getNumberOfAccount(){
        return getAccounts().size();
    }

    public int getNumberOfCustomerAccounts(){
        return getCustomerAccounts().size();
    }

    public int getNumberOfManagerAccounts(){
        return getManagerAccounts().size();
    }

    public int getNumberOfAdminAccounts(){
        return getAdminAccounts().size();
    }

    public int getNumberOfStores(){
        return getStores().size();
    }

    public int getNumberOfItems(){
        return getItems().size();
    }
}
