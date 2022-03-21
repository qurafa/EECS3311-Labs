package project;

public interface Staff {

    //adding an item
    void addItem(Item item);

    //removing an item
    void removeItem(Item item);

    //setting item sale
    void setSalesItem(Item item, int sale);

    //remove item sale
    void removeSalesItem(Item item);
}
