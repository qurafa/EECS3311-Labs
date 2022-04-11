package project;

import frames.SelectLocationFrame;

import java.io.*;
import java.util.*;

public class SmartShoppers {
    private static final SmartShoppers sys = new SmartShoppers();
    private List<Item> ITEMS;//list of all items and their total amount in the SS System
    private List<Store> STORES;
    private List<Account> ACCOUNTS;
    private List<Category> CATEGORIES;
    private List<Object> OBSERVERS;///to keep track of the id of the last item

    public static SmartShoppers getInstance(){
        return sys;
    }

    //SmartShoppers private constructor
    private SmartShoppers(){
        System.out.println("Creating System...");
        initItems();
        initStores();
        initAccounts();
        initCategories();
        System.out.println("Done Creating System...");
    }

    //to delete the system if you want to
    private void deleteSystem(){}

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

    //initializing the Categories from the database
    private void initCategories(){
        System.out.println("Initializing Categories...");
        CATEGORIES = Database.getAllCategories();
        System.out.println("Done Initializing Categories...");
    }

    //creating an account, where type ranges from 0 to 2
    public Account createAccount(int type, String firstname, String lastname, String username, String password){
        Account account = null;
        if(type == 2){
            account = new AdminAccount();
        }
        else if(type == 1){
            account = new ManagerAccount();
        }
        else if(type == 0) {
            account = new CustomerAccount();
        }

        account.setFirstName(firstname);
        account.setLastName(lastname);
        account.setUserName(username);
        account.setPassword(password);
        account.setAccountCode(createAccountCode());

        System.out.println(account.getFirstName() + ", " + account.getLastName() + ", " + account.getUserName() + ", " + ACCOUNTS.size());
        //set the id and stuff when you add it to the database
        Database.addAccount(account);
        ACCOUNTS.add(account);

        return account;
    }

    private int createAccountCode(){
        Random rand = new Random();
        int output = rand.nextInt();
        List<Integer> codes = new ArrayList<Integer>();

        for(Account a : ACCOUNTS) codes.add(a.getAccountCode());

        while(codes.contains(output)) output = rand.nextInt();

        return output;
    }

    //deleting an account
    public void deleteAccount(Account account){
        ACCOUNTS.remove(account);
    }

    //deleting a store
    public void deleteStore(Store store){
        STORES.remove(store);
        deleteStoreAccounts(store);
    }

    public void deleteStoreAccounts(Store store){
        for(Account a : ACCOUNTS){
            if(a.getStore() != null && a.getStore().toString().equals(store.toString())){
                ACCOUNTS.remove(a);
                Database.deleteAccount(a.getID());
            }
        }
    }

    //TODO getting an account based on the username
    public Account getAccount(String username){
        for(Account a : ACCOUNTS){
            System.out.println(a.getFirstName() + ", " + a.getLastName() + ", " + a.getUserName() + ", " + ACCOUNTS.size());
            if(a.getUserName().equals(username))
                return a;
        }
        return null;
    }

    public int getAccountType(String username){
        if(getAccount(username) != null) {
            if (getAccount(username).getClass().getName().toLowerCase().contains("customer"))
                return 0;
            else if (getAccount(username).getClass().getName().toLowerCase().contains("manager"))
                return 1;
            else if (getAccount(username).getClass().getName().toLowerCase().contains("admin"))
                return 2;
        }
        return -1;
    }

    //checking if the username is one in the system
    public boolean verifyUsername(String username){
        for(Account a : ACCOUNTS)
            if(a.getUserName().equals(username)) return true;

        return false;
    }

    //checking if a password with a given account works
    public boolean verifyPassword(String username, String password){
        for(Account a : ACCOUNTS)
            if(a.getUserName().equals(username))
                return a.getPassword().equals(password);

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

    public boolean verifyNewPassword(String password){
        return true;
    }

    //type is 1 for admin, type is 0 for manager
    public boolean verifyCode(int type, int code){
        if(type == 1)
            return code == 3305;
        if(type == 0)
            return code == 3305 || code == 4372;

        return false;
    }

    public boolean verifyLogin(String username, String password){
        for(Account a : ACCOUNTS){
            //System.out.println(a.getUserName() + ", " + a.getPassword() + " in " + this.getClass().getName() + ".verifyLogin");
            if(a.getUserName().equals(username) && a.getPassword().equals(password)) return true;
        }
        return false;
    }

    //TODO granting privilege to a specified manager
    private void grantPrivilege(Account manager){}

    //TODO revoking privilege from a specified manager
    private void revokePrivilege(Account manager){}

    public Category createCategory(){
        return new Category();
    }

    public Category createCategory(String desc){
        System.out.println("Creating Category");
        Category cat = new Category();
        cat.setDesc(desc);

        Database.addCategory(cat);
        CATEGORIES.add(cat);

        System.out.println("Done Creating Category");
        return cat;
    }

    public Category getCategory(String description){
        for(Category category : CATEGORIES)
            if(category.getDesc().equals(description)) return category;

        return null;
    }

    //creating an item to the system without specifying the total amount
    public Item createItem(){
        return new Item();
    }

    //creating an item by specifying eveything
    public Item createItem(String itemDesc, String unitPrice, String totalAmount, String sale){
        System.out.println("Creating Item");
        Item i =  new Item();
        i.setDescription(itemDesc);
        i.setPrice(Double.parseDouble(unitPrice));
        i.setTotalAmount(Integer.parseInt(totalAmount));
        i.setSale(Integer.parseInt(sale));

        Database.addItem(i);
        ITEMS.add(i);
        System.out.println("Done Creating Item");

        return i;
    }

    //TODO deleting an item from the whole system
    public void deleteItem(Item item){
        ITEMS.remove(item);
    }

    //adding an item to a specific store, if you don't specify the amount, all of it goes to the store
    //you can only add items that are already created
    public void addItem(Store store, Item item, int amount){
        store.addItem(item, amount);//the one reduces the total number in the whole stock
    }

    //removing an item from a specific store, if you don't specify the amount, all of it is removed from the store
    private void removeItem(Store store, Item item, int amount){
        item.setTotalAmount(item.getTotalAmount() + amount);
        store.removeItem(item, amount);
    }

    //creating a store for the system
    public Store createStore(){
        return new Store();
    }

    public Store createStore(String address, String city, String state, String postalCode, String country, String openHour, String closeHour){
        System.out.println("Creating Store");
        Store s = new Store();
        s.setAddress(address);
        s.setCity(city);
        s.setState(state);
        s.setPostalCode(postalCode);
        s.setCountry(country);
        s.setOpenHour(openHour);
        s.setCloseHour(closeHour);

        Database.addStore(s);
        STORES.add(s);
        System.out.println("Done Creating Store");

        return s;
    }

    public Store getStore(String location){
        for (Store s : STORES){
            if(s.getLocation().toString().equals(location)) return s;
        }
        return null;
    }

    //getting a list of all accounts
    private List<Account> getAccounts(){
        return ACCOUNTS;
    }

    public List<CustomerAccount> getCustomerAccounts(){
        List <CustomerAccount> output = new ArrayList<CustomerAccount>();

        for(Account c : getAccounts())
            if(c.getClass().getName().toLowerCase().contains("customer")) output.add((CustomerAccount) c);

        return output;
    }

    public List<ManagerAccount> getManagerAccounts(){
        List <ManagerAccount> output = new ArrayList<ManagerAccount>();

        for(Account c : getAccounts())
            if(c.getClass().getName().toLowerCase().contains("manager")) output.add((ManagerAccount) c);

        return output;
    }

    public List<AdminAccount> getAdminAccounts(){
        List <AdminAccount> output = new ArrayList<AdminAccount>();

        for(Account c : getAccounts())
            if(c.getClass().getName().toLowerCase().contains("admin")) output.add((AdminAccount) c);

        return output;
    }

    //getting a list of all the stores
    public List<Store> getStores(){
        return STORES;
    }

    //getting an item based on its string representation
    public Item getItem(String itemDesc){
        for(Item i : ITEMS){
            if(itemDesc.equals(i.getDescription())) return i;
        }
        System.out.println("blehhh");
        return null;
    }

    //getting a list of all the items, maybe based on specifications given
    public List<Item> getItems(){
        return new ArrayList<Item>(ITEMS);
    }

    public List<Item> getSaleItem(){
        List<Item> output = new ArrayList<Item>();

        for(Item item : ITEMS){
            if(item.getSale() > 0) output.add(item);
        }

        return output;
    }

    public List<Category> getCategories(){
        return new ArrayList<>(CATEGORIES);
    }

    private int getNumberOfAccount(){
        return getAccounts().size();
    }

    public int getNumberOfCustomerAccounts(){
        return getCustomerAccounts().size();
    }

    public int getNumberOfCustomerAccounts(Store store){
        int output = 0;

        for(Account a :  ACCOUNTS){
            if(a.getClass().getName().contains("Customer") &&
                    a.getStore().getLocation().toString().equals(store.getLocation().toString()))
                output++;
        }

        return output;
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

    public int getNumberOfItems(Store store){
        return store.getItems().size();
    }

}
