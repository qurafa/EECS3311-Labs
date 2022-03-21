package project;

import java.util.List;

public class Item {
    private String name;
    private double price;
    private int sale = 0;

    public Item(String name, double price){
        this.name = name;
        this.price = price;
    }

    public Item(String name, double price, int sale){
        new Item(name, price);
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
