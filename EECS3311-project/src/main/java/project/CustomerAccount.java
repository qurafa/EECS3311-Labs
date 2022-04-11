package project;

public class CustomerAccount extends Account{

    private ShoppingCart cart;

    public CustomerAccount(){
        cart = new ShoppingCart();
    }

    public ShoppingCart getCart(){
        return cart;
    }
}
