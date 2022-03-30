package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private int storeID;
    private List<Manager> managers;
    //stores will have a map of items and how many in store
    //lost of items and their in store count
    private Map<Item, Integer> items;
    //open and close hour in the format HHMM
    private Location location;
    private int openHour;
    private int closeHour;
    private int peopleInStore;//number of people in store

    public Store(){
        items = new HashMap<>();
        managers = new ArrayList<>();
    }

    public void setID(int id){
        storeID = id;
    }

    public boolean setOpenHour(int hour){
        openHour = hour;
        return true;
    }

    public boolean setCloseHour(int hour){
        closeHour = hour;
        return true;
    }

    public void setAddress(String address){
        location.setAddress(address);
    }

    public void setCity(String city){
        location.setCity(city);
    }

    public void setState(String state){
        location.setState(state);
    }

    public void setCountry(String country){
        location.setCountry(country);
    }

    public void setPostalCode(String code){
        location.setPostalCode(code);
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
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
        return new ArrayList<>(items.keySet());
    }

    private List<Item> getSuggestedItems(){
        return null;
    }

}
