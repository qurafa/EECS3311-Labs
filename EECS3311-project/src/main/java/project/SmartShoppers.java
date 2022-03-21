package project;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartShoppers {
    private static final SmartShoppers sys = new SmartShoppers();
    private Map<Item,Integer> ITEMS;//list of all items and their total amount in the SS System
    private List<Store> STORES;
    private List<Account> ACCOUNTS;
    private int lastItemID;

    public static SmartShoppers getInstance(){
        return sys;
    }

    //System constructor
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
        ITEMS = new HashMap<Item,Integer>();
        File file = new File("database/Items.csv");
        if(!file.isFile()){
            try{
                file.createNewFile();
                System.out.println("file created");
                FileWriter writer = new FileWriter("database/Items.csv");
                writer.append("ID,Item,Price,Total,Sale\n");
                writer.close();
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }else{
            try{
                BufferedReader reader = new BufferedReader(new FileReader("database/Items.csv"));
                int line = 0;
                String row;
                while((row = reader.readLine()) != null){
                    if(line != 0){
                        String[] itemPriceTotal = row.split(",");
                        double price = Integer.parseInt(itemPriceTotal[2]);
                        int id = Integer.parseInt(itemPriceTotal[0]);
                        Item item = new Item(id, itemPriceTotal[1], price);
                        int total = Integer.parseInt(itemPriceTotal[3]);
                        ITEMS.put(item, total);
                        lastItemID = id;
                    }
                    line++;
                }
                reader.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Done Initializing Items...");
    }

    //initializing the stores from the database
    private void initStores(){
        System.out.println("Initializing Stores...");
        System.out.println("Done Initializing Stores...");
    }

    //initializing the Accounts from the database
    private void initAccounts(){
        System.out.println("Initializing Accounts...");
        System.out.println("Done Initializing Accounts...");
    }

    //to be used to verify the user making use of the system
    private boolean verifyAccount(String expected){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String actual = stackTrace[3].getClassName();

        return expected.equals(actual);
    }

    //creating an account
    private Account createAccount(){
        return null;
    }

    //deleting an account
    private void deleteAccount(){}

    //getting an account based on the username
    private void getAccount(String username){
        long[] S = new long []{};
        int i = S.length;
    }

    //checking if the username is one that has been used before
    private boolean validUsername(){
        return false;
    }

    //granting privilege to a specified manager, usually the manager
    private void grantPrivilege(){}

    //revoking privilege from a specified manager
    private void revokePrivilege(){}

    //creating an item to the system
    private void createItem(String name, double price){
        try{
            lastItemID++;
            Item item = new Item(lastItemID, name, price);
            ITEMS.put(item, 0);
            FileWriter writer = new FileWriter("database/Items.csv");
            writer.append(item.getName()+","+item.getPrice()+","+0+","+item.getSale()+"\n");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //creating an item to the system without specifying the sale
    private void createItem(String name, double price, int totalAmount){
        try{
            lastItemID++;
            Item item = new Item(lastItemID, name, price);
            ITEMS.put(item, totalAmount);
            FileWriter writer = new FileWriter("database/Items.csv");
            writer.append(item.getName()+","+item.getPrice()+","+totalAmount+","+item.getSale()+"\n");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //creating an item to the system
    private void createItem(String name, double price, int totalAmount, int sale){
        try{
            lastItemID++;
            Item item = new Item(lastItemID, name, price, sale);
            ITEMS.put(item, totalAmount);
            FileWriter writer = new FileWriter("database/Items.csv");
            writer.append(item.getName()+","+item.getPrice()+","+totalAmount+","+item.getSale()+"\n");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //deleting an item from the whole system
    private void deleteItem(){}

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
    private void createStore(){}

    //deleting a store from the system
    private void deleteStore(){}

    //getting a list of all the items, maybe based on specifications given
    private void getItems(){}

    //getting a list of all the stores
    private void getStores(){}

    //getting a list of all accounts
    private void getAccounts(){}

}
