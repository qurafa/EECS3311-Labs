package project;

import java.util.List;

public class Item {
    private int itemID;
    private String name;
    private double price;
    private int sale = 0;

    public Item(int itemID ,String name, double price){
        this.itemID = itemID;
        this.name = name;
        this.price = price;
    }

    public Item(int itemID, String name, double price, int sale){
        new Item(itemID, name, price);
        this.sale = sale;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getSale(){
        return sale;
    }

    public boolean equals(Item item){
        if(item.getName() == this.name)
            return true;

        return false;
    }
}
