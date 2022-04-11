package project;

import java.io.StringBufferInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Database {
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/";
    private final static String USERNAME  = "root";
    private final static String PASSWORD = "TeenRuqie13==";
    private final static String DBNAME = "SmartShoppersDB";

    //////create functions//////
    public static void createDatabase(){
        try{
            System.out.println("Creating Database......");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS "+DBNAME);
            System.out.println("Database Created......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    ////table functions////
    public static void initTables(){
        //creating the individual tables
        createItemTable();
        createCategoryTable();
        createStoreTable();
        createAccountTable();
        //creating the tables showing their relationships
        createItemStoreTable();
        createItemCategoryTable();
        //createManagerStoreTable();
    }

    private static void createItemTable(){
        try{
            System.out.println("Creating ItemTable......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String command = "CREATE TABLE IF NOT EXISTS item_table " +
                    "(item_id INTEGER NOT NULL UNIQUE AUTO_INCREMENT, " +
                    "item_desc VARCHAR(255), " +
                    "unit_price DOUBLE(9,2), " +
                    "total_amount INTEGER, " +
                    "sale_amount INTEGER DEFAULT 0, " +
                    "suggest_point INTEGER DEFAULT 0, " +
                    "PRIMARY KEY(item_id))";
            statement.executeUpdate(command);
            System.out.println("ItemTable Created......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createCategoryTable(){
        try{
            System.out.println("Creating CategoryTable......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String command = "CREATE TABLE IF NOT EXISTS category_table " +
                    "(category_id INTEGER NOT NULL UNIQUE AUTO_INCREMENT, " +
                    "category_desc VARCHAR(255) NOT NULL UNIQUE, " +
                    "PRIMARY KEY(category_id))";
            statement.executeUpdate(command);
            System.out.println("CategoryTable Created......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createStoreTable(){
        try{
            System.out.println("Creating StoreTable......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String command = "CREATE TABLE IF NOT EXISTS store_table " +
                    "(store_id INTEGER NOT NULL UNIQUE AUTO_INCREMENT, " +
                    "store_address VARCHAR(255), " +
                    "city VARCHAR(255), " +
                    "state VARCHAR(255), " +
                    "postal_code VARCHAR(255), " +
                    "country VARCHAR(255)," +
                    "open_hour VARCHAR(5) NOT NULL, " +
                    "close_hour VARCHAR(5) NOT NULL, " +
                    "postal_code_country VARCHAR(510) UNIQUE, " +
                    "PRIMARY KEY(store_id))";
            statement.executeUpdate(command);

            System.out.println("StoreTable Created......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createAccountTable(){
        try{
            System.out.println("Creating AccountTable......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String command = "CREATE TABLE IF NOT EXISTS account_table " +
                    "(account_id INTEGER NOT NULL UNIQUE AUTO_INCREMENT, " +
                    "first_name VARCHAR(255), " +
                    "last_name VARCHAR(255), " +
                    "account_username VARCHAR(255) UNIQUE, " +
                    "account_password VARCHAR(255), " +
                    "account_type ENUM('customer', 'manager', 'admin'), " +
                    "account_code INTEGER UNIQUE, " +
                    "admin_privilege BOOLEAN DEFAULT false, " +
                    "store_location VARCHAR(255), " +
                    "PRIMARY KEY(account_id))";
            statement.executeUpdate(command);
            System.out.println("AccountTable Created......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createItemStoreTable(){
        try{
            System.out.println("Creating ItemStoreTable......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String command = "CREATE TABLE IF NOT EXISTS item_store_table " +
                    "(item_id INTEGER NOT NULL, " +
                    "store_id INTEGER NOT NULL, " +
                    "item_count INTEGER NOT NULL, " +
                    "item_store_id VARCHAR(255) NOT NULL UNIQUE, " +
                    "FOREIGN KEY (`item_id`) REFERENCES `item_table` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (`store_id`) REFERENCES `store_table` (`store_id`) ON DELETE CASCADE ON UPDATE CASCADE)";
            statement.executeUpdate(command);
            System.out.println("ItemStoreTable Created......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createItemCategoryTable(){
        try{
            System.out.println("Creating ItemCategoryTable......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String command = "CREATE TABLE IF NOT EXISTS item_category_table " +
                    "(item_id INTEGER NOT NULL, " +
                    "category_id INTEGER NOT NULL, " +
                    "item_cat_id VARCHAR(255) NOT NULL UNIQUE, " +
                    "FOREIGN KEY (`item_id`) REFERENCES `item_table` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (`category_id`) REFERENCES `category_table` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE)";
            statement.executeUpdate(command);
            System.out.println("ItemCategoryTable Created......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

//    private static void createManagerStoreTable(){
//        try{
//            System.out.println("Creating ManagerStoreTable......");
//            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
//            Statement statement = connection.createStatement();
//            String command = "CREATE TABLE IF NOT EXISTS manager_store_table " +
//                    "(manager_id INTEGER NOT NULL, " +
//                    "store_id INTEGER NOT NULL, " +
//                    "account_type VARCHAR(255) NOT NULL, " +
//                    "CHECK(account_type = 'manager'), " +
//                    "FOREIGN KEY (`manager_id`) REFERENCES `account_table` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE, " +
//                    "FOREIGN KEY (`store_id`) REFERENCES `store_table` (`store_id`) ON DELETE CASCADE ON UPDATE CASCADE)";
//            statement.executeUpdate(command);
//            System.out.println("ManagerStoreTable Created......");
//        }catch(SQLException e){
//            System.out.println(e.getMessage());
//        }
//    }

    //////get functions//////
    public static List<Item> getAllItems(){
        List <Item> output = new ArrayList<Item>();

        try{
            System.out.println("Getting all Items......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM item_table";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next())
            {
                Item i = new Item();
                i.setID(rs.getInt("item_id"));
                i.setDescription(rs.getString("item_desc"));
                i.setPrice(rs.getInt("unit_price"));
                i.setTotalAmount(rs.getInt("total_amount"));
                i.setSale(rs.getInt("sale_amount"));
                i.setSuggestPoint(rs.getInt("suggest_point"));
                output.add(i);
            }
            System.out.println("Done getting all Items......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return output;
    }

    public static List<Store> getAllStores(){
        List <Store> output = new ArrayList<Store>();

        try{
            System.out.println("Getting all Stores......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM store_table";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next())
            {
                Store s = new Store();
                s.setID(rs.getInt("store_id"));
                s.setAddress(rs.getString("store_address"));
                s.setCity(rs.getString("city"));
                s.setState(rs.getString("state"));
                s.setPostalCode(rs.getString("postal_code"));
                s.setCountry(rs.getString("country"));
                s.setOpenHour(rs.getString("open_hour"));
                s.setCloseHour(rs.getString("close_hour"));
                addStoreItems(s);
                output.add(s);
            }
            System.out.println("Done getting all Stores......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return output;
    }

    public static List<Account> getAllAccounts(){
        List<Account> output = new ArrayList<Account>();

        try{
            System.out.println("Getting all Accounts......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM account_table";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next())
            {
                Account a = null;
                if(rs.getString("account_type").equals("admin")){
                    a = new AdminAccount();
                }
                else if(rs.getString("account_type").equals("manager")){
                    a = new ManagerAccount();
                }
                else if(rs.getString("account_type").equals("customer")){
                    a = new CustomerAccount();
                }

                assert a != null;
                a.setID(rs.getInt("account_id"));
                a.setFirstName(rs.getString("first_name"));
                a.setLastName(rs.getString("last_name"));
                a.setUserName(rs.getString("account_username"));
                a.setPassword(rs.getString("account_password"));
                a.setAccountCode(rs.getInt("account_code"));
                a.setStore(getStore(rs.getString("store_location")));
                if(rs.getString("account_type").equals("manager")) {
                    assert a instanceof ManagerAccount;
                    ((ManagerAccount) a).setAdminPrivilege(rs.getBoolean("admin_privilege"));
                }

                output.add(a);
            }
            System.out.println("Done getting all Accounts......");
        }catch(SQLException e){
            System.out.println("Exception: " + e.getMessage() +" in " + Database.class.getName() + ".getAllAccounts");
        }

        return output;
    }

    public static List<Category> getAllCategories(){
        List<Category> output = new ArrayList<Category>();

        try{
            System.out.println("Getting all Categories......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM category_table";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next())
            {
                Category c = new Category();

                c.setID(rs.getInt("category_id"));
                c.setDesc(rs.getString("category_desc"));
                setCategoryItems(c);

                output.add(c);
            }
            System.out.println("Done getting all Categories......");
        }catch(SQLException e){
            System.out.println("Exception: " + e.getMessage() +" in " + Database.class.getName() + ".getAllCategories");
        }

        return output;
    }

    private static Store getStore(String location){
        for(Store s : getAllStores())
            if(s.getLocation().toString().equals(location)) return s;

        return null;
    }

    private static Item getItem(int id){
        for(Item i : getAllItems())
            if(i.getID() == id) return i;

        return null;
    }

    //////set functions//////
    public static void addStoreItems(Store store){
        try{
            System.out.println("Adding Items to Store......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM item_store_table";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next())
            {
                if(rs.getInt("store_id") == store.getID())
                    store.addItem(getItem(rs.getInt("item_id")), rs.getInt("item_count"));
            }
            System.out.println("Done Adding Items to Store......");
        }catch(SQLException e){
            System.out.println("Exception: " + e.getMessage() +" in " + Database.class.getName() + ".setStoreItems");
        }
    }

    private static void setCategoryItems(Category category){
        try{
            System.out.println("Setting all Category Items......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM item_category_table";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next())
            {
                if(rs.getInt("category_id") == category.getID())
                    category.addItem(getItem(rs.getInt("item_id")));
            }
            System.out.println("Done Setting all Category Items......");
        }catch(SQLException e){
            System.out.println("Exception: " + e.getMessage() +" in " + Database.class.getName() + ".setCategoryItems");
        }
    }

    //////edit  functions//////
    //TODO adding an item
    public static void addItem(Item item){
        try{
            System.out.println("Adding Item......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "INSERT INTO item_table (item_desc,unit_price,total_amount,sale_amount,suggest_point) VALUES(?,?,?,?,?)";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, item.getDescription());
            prepareStatement.setDouble(2, item.getPrice());
            prepareStatement.setInt(3, item.getTotalAmount());
            prepareStatement.setInt(4, item.getSale());
            prepareStatement.setInt(5, item.getSuggestPoint());
            prepareStatement.executeUpdate();

            String query = "SELECT * FROM item_table ORDER BY item_id DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                item.setID(rs.getInt("item_id"));
            }

            System.out.println("Done Adding Item......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //TODO adding a category to the database
    public static void addCategory(Category category){
        try{
            System.out.println("Adding Category......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "INSERT INTO category_table (category_desc) VALUES(?)";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, category.getDesc());
            prepareStatement.executeUpdate();

            String query = "SELECT * FROM category_table ORDER BY category_id DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                category.setID(rs.getInt("category_id"));
            }

            System.out.println("Done Adding Category......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //TODO adding a store
    public static void addStore(Store store){
        try{
            System.out.println("Adding Store......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "INSERT INTO store_table(store_address,city,state,postal_code,country,open_hour,close_hour,postal_code_country) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, store.getLocation().getAddress());
            prepareStatement.setString(2, store.getLocation().getCity());
            prepareStatement.setString(3, store.getLocation().getState());
            prepareStatement.setString(4, store.getLocation().getPostalCode());
            prepareStatement.setString(5, store.getLocation().getCountry());
            prepareStatement.setString(6, store.getOpenHour());
            prepareStatement.setString(7, store.getCloseHour());
            prepareStatement.setString(8, store.getLocation().getPostalCode() + " " + store.getLocation().getCountry());
            prepareStatement.executeUpdate();

            //getting the store id
            String query = "SELECT * FROM store_table ORDER BY store_id DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                store.setID(rs.getInt("store_id"));
            }
            System.out.println("Done Adding Store......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //TODO adding an account
    //inserts into a database and sets the id
    public static void addAccount(Account account){
        try{
            System.out.println("Adding Account......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String aType = account.getClass().getName().contains("Customer") ? "customer" :
                    account.getClass().getName().contains("Manager") ? "manager" :
                            account.getClass().getName().contains("Admin") ? "admin" : "NULL";
            System.out.println("Class Name!!!!!! " + account.getClass().getName());
            String update = "INSERT INTO account_table(first_name,last_name,account_username,account_password,account_type,account_code,admin_privilege,store_location) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, account.getFirstName());
            prepareStatement.setString(2, account.getLastName());
            prepareStatement.setString(3, account.getUserName());
            prepareStatement.setString(4, account.getPassword());
            prepareStatement.setString(5, aType);
            prepareStatement.setInt(6, account.getAccountCode());
            prepareStatement.setBoolean(7, aType.equals("admin"));
            prepareStatement.setString(8, "");
            prepareStatement.executeUpdate();

            //getting the account id
            String query = "SELECT * FROM account_table ORDER BY account_id DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                account.setID(rs.getInt("account_id"));
            }
            //NOTE: admin_privilege set to false if not an admin
            System.out.println("Done Adding Account......");
        }catch(SQLException e){
            System.out.println("Exception: " + e.getMessage() + ", in " + Database.class.getName() + ".addAccount()");
        }
    }

    //TODO adding an item to store
    public static void addItemStore(Item item, Store store, int amount){
        try{
            System.out.println("Adding Item to Store......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "INSERT INTO item_store_table (item_id, store_id, item_count, item_store_id) VALUES(?,?,?,?)";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setInt(1, item.getID());
            prepareStatement.setInt(2, store.getID());
            prepareStatement.setInt(3, amount);
            prepareStatement.setString(4, item.getID()+""+store.getID());
            prepareStatement.executeUpdate();

            System.out.println("Done Adding Item to Store......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //TODO removing a set amount of items from a store
    public static void updateItemStore(Item item, Store store, int amount){
        if(amount < 1){
            deleteItemStore(item, store);
        }

        try{
            System.out.println("Updating Item in Store......" + amount);
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "UPDATE item_store_table SET item_count = ? WHERE item_id = ? AND store_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setInt(1, amount);
            prepareStatement.setInt(2, item.getID());
            prepareStatement.setInt(3, store.getID());
            prepareStatement.executeUpdate();

            System.out.println("Done Updating Item in Store......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateStoreInformation(Store store, String address, String city, String state, String postalCode, String country, String openHour, String closeHour){
        try{
            System.out.println("Adding Item to Store......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "UPDATE store_table SET store_address = ?, city = ?, state = ?, postal_code = ?, country = ?, open_hour = ?, close_hour = ?, postal_code_country = ? WHERE store_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, address);
            prepareStatement.setString(2, city);
            prepareStatement.setString(3, state);
            prepareStatement.setString(4, postalCode);
            prepareStatement.setString(5, country);
            prepareStatement.setString(6, openHour);
            prepareStatement.setString(7, closeHour);
            prepareStatement.setString(8, postalCode+country);
            prepareStatement.setInt(9, store.getID());
            prepareStatement.executeUpdate();

            System.out.println("Done Adding Item to Store......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateAccountInformation(Account account, String firstName, String lastName, String username, String password){
        try{
            System.out.println("Updating Account Information......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "UPDATE account_table SET first_name = ?, last_name = ?, account_username = ?, account_password = ? WHERE account_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, firstName);
            prepareStatement.setString(2, lastName);
            prepareStatement.setString(3,username);
            prepareStatement.setString(4, password);
            prepareStatement.setInt(5, account.getID());
            prepareStatement.executeUpdate();

            System.out.println("Done Updating Account Information......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateItemInformation(Item item, String desc, double unitPrice, int totalAmount, int sale, int suggestPoint){

        if(totalAmount < 1){
            deleteItem(item.getID());
            return;
        }

        try{
            System.out.println("Updating Item Information......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "UPDATE item_table SET item_desc = ?, unit_price = ?, total_amount = ?, sale_amount = ?, suggest_point = ? WHERE item_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, desc);
            prepareStatement.setDouble(2, unitPrice);
            prepareStatement.setInt(3, totalAmount);
            prepareStatement.setInt(4, sale);
            prepareStatement.setInt(5, suggestPoint);
            prepareStatement.setInt(6, item.getID());
            prepareStatement.executeUpdate();

            System.out.println("Done Updating Item Information......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateAccountStore(Account account, Store store){
        try{
            System.out.println("Updating Account Store......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "UPDATE account_table SET store_location = ? WHERE account_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, store.getLocation().toString());
            prepareStatement.setInt(2, account.getID());
            prepareStatement.executeUpdate();

            System.out.println("Done Account Store Information......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //TODO adding an item to a category
    public static void addItemCategory(Item item, Category category){
        try{
            System.out.println("Adding Item to Category......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "INSERT INTO item_category_table (item_id, category_id, item_cat_id) VALUES(?,?,?)";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setInt(1, item.getID());
            prepareStatement.setInt(2, category.getID());
            prepareStatement.setString(3, item.getID() + ", " + category.getID());
            prepareStatement.executeUpdate();

            System.out.println("Done Adding Item to Category......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //TODO setting the store of a specific customer or manager
    public static void setAccountStore(int accountID, Store store){
        try{
            System.out.println("Setting Store......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "UPDATE account_table SET store_location = ? WHERE account_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update);
            prepareStatement.setString(1, store.getLocation().toString());
            prepareStatement.setInt(2, accountID);

            prepareStatement.executeUpdate();

            System.out.println("Done Setting Store......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //////misc functions//////
    public static void deleteAccount(int id){
        try{
            System.out.println("Deleting Account......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "DELETE FROM account_table WHERE account_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();

            System.out.println("Done Deleting Account......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStore(int id){
        try{
            System.out.println("Deleting Account......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "DELETE FROM store_table WHERE store_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();

            System.out.println("Done Deleting Account......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteItem(int id){
        try{
            System.out.println("Deleting Item......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "DELETE FROM item_table WHERE item_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();

            System.out.println("Done Deleting Item......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteItemStore(Item item, Store store){
        try{
            System.out.println("Deleting Item......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "DELETE FROM item_store_table WHERE item_id = ? AND store_id = ?";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setInt(1, item.getID());
            prepareStatement.setInt(2, store.getID());
            prepareStatement.executeUpdate();

            System.out.println("Done Deleting Item......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteDatabase(){
        try{
            System.out.println("Deleting Database......");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE IF EXISTS "+DBNAME);
            System.out.println("Database Deleted ......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTable(){}
}

