package project;

public abstract class User {
    private String name;
    private Account account;
    private boolean accountCreated;
    private boolean isLoggedIn = false;

    //creating an account
    abstract void createAccount();

    //logging into their account
    abstract void login();

    //logging out of the account
    abstract void logout();

    //searching item
    abstract void searchItem();

    //searching store
    abstract void searchStore();

    //visiting accounts
    abstract void visitAccount();
}