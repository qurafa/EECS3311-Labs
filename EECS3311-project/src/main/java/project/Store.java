package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    //stores will have a map of items and how many in store
    private List<Manager> managers;
    private Map<Item, Integer> items;
    //open and close hour in the format HHMM
    private int openHour;
    private int closeHour;
    private int peopleInStore;//number of people in store

    public Store(int openHour, int closeHour){
        this.openHour = openHour;
        this.closeHour = closeHour;
        items = new HashMap<>();
        managers = new ArrayList<>();
    }

    public void addItem(Item item, int amount){
        if(amount > 0){
            items.put(item, amount);
        }
    }

    public void  removeItem(Item item, int amount){
        if(amount == items.get(item)){
            items.remove(item);
        }else{
            items.replace(item, items.get(item) - amount);
        }
    }

    //return a list of item in store
    private List<Item> getItems(){
        return null;
    }

    private List<Item> getSuggestedItems(){
        return null;
    }

}
