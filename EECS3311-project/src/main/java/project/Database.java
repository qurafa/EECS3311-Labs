package project;

import java.io.StringBufferInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        createManagerStoreTable();
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
                    "category_desc VARCHAR(255), " +
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
                    "country VARCHAR(255), " +
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
                    "account_username VARCHAR(255) UNIQUE, " +
                    "account_password VARCHAR(255), " +
                    "account_type ENUM('customer', 'manager', 'admin'), " +
                    "account_code INTEGER UNIQUE, " +
                    "admin_privilege BOOLEAN DEFAULT false, " +
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
                    "FOREIGN KEY (`item_id`) REFERENCES `item_table` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (`category_id`) REFERENCES `category_table` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE)";
            statement.executeUpdate(command);
            System.out.println("ItemCategoryTable Created......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createManagerStoreTable(){
        try{
            System.out.println("Creating ManagerStoreTable......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String command = "CREATE TABLE IF NOT EXISTS manager_store_table " +
                    "(manager_id INTEGER NOT NULL, " +
                    "store_id INTEGER NOT NULL, " +
                    "account_type VARCHAR(255) NOT NULL, " +
                    "CHECK(account_type = 'manager'), " +
                    "FOREIGN KEY (`manager_id`) REFERENCES `account_table` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (`store_id`) REFERENCES `store_table` (`store_id`) ON DELETE CASCADE ON UPDATE CASCADE)";
            statement.executeUpdate(command);
            System.out.println("ManagerStoreTable Created......");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

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
                a.setUserName(rs.getString("account_username"));
                a.setPassword(rs.getString("account_password"));
                a.setAccountCode(rs.getInt("account_code"));
                if(rs.getString("account_type").equals("manager")) {
                    assert a instanceof ManagerAccount;
                    ((ManagerAccount) a).setAdminPrivilege(rs.getBoolean("admin_privilege"));
                }

                output.add(a);
            }
            System.out.println("Done getting all Accounts......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return output;
    }

    //////edit  functions//////
    //TODO adding an item
    public static void addItem(Item item){
        try{
            System.out.println("Adding Item......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "INSERT INTO account_table(item_desc,unit_price,total_amount) VALUES(?,?,?)";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, item.getDescription());
            prepareStatement.setDouble(2, item.getPrice());
            prepareStatement.setInt(3, item.getTotalAmount());
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

    //TODO adding a category
    public static void addCategory(Category category){
        try{
            System.out.println("Adding Category......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String update = "INSERT INTO account_table(category_desc) VALUES(?)";

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
    public static void addStore(Store store){}

    //TODO adding an account
    //inserts into a database and sets the id
    public static void addAccount(Account account){
        try{
            System.out.println("Adding Account......");
            Connection connection = DriverManager.getConnection(URL+DBNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String aType = account.getClass().getName().contains("Customer") ? "customer" :
                    account.getClass().getName().contains("Manager") ? "manager" : "admin";
            String update = "INSERT INTO account_table(account_username,account_password,account_type,account_code) VALUES(?,?,?,?)";

            PreparedStatement prepareStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, account.getUserName());
            prepareStatement.setString(2, account.getPassword());
            prepareStatement.setString(3, aType);
            prepareStatement.setInt(4, createNewAccountCode());
            prepareStatement.executeUpdate();

            //getting the string id
            String query = "SELECT * FROM account_table ORDER BY account_id DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                account.setID(rs.getInt("account_id"));
            }
            //NOTE: admin_privilege set to false by default
            System.out.println("Done Adding Account......");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //TODO making a new account code, based on the already existing account codes
    private static int createNewAccountCode(){
        return 0;
    }

    //TODO adding an item to store
    public static void addItemStore(Item item, Store store){}

    //TODO adding an item to a category
    public static void addItemCategory(Item item, Category category){}

    //TODO adding a manager to a store
    public static void addManagerStore(Manager manager, Store store){}

    //////misc functions//////
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

