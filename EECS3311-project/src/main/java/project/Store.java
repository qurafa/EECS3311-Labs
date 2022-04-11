package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private int storeID;
    private List<ManagerAccount> managers;
    //stores will have a map of items and how many in store
    //lost of items and their in store count
    private Map<String, Integer> items;
    //open and close hour in the format HHMM
    private Location location;
    private String openHour;
    private String closeHour;
    private int peopleInStore;//number of people in store

    public Store(){
        location = new Location();
        items = new HashMap<>();
        managers = new ArrayList<>();
    }

    public void setID(int id){
        storeID = id;
    }

    public int getID(){
        return storeID;
    }

    public void setOpenHour(String hour){
        openHour = hour;
    }

    public String getOpenHour(){
        return openHour;
    }

    public void setCloseHour(String hour){
        closeHour = hour;
    }

    public String getCloseHour(){
        return closeHour;
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
            if(items.containsKey(item.getDescription())){
                items.replace(item.getDescription(), items.get(item.getDescription()) + amount);
            }
            else{
                items.put(item.getDescription(), amount);
            }
        }

    }

    public void removeAllItem(Item item){
        items.remove(item.getDescription());
    }

    public void removeItem(Item item){
        if(items.containsKey(item.getDescription())){
            if(items.get(item.getDescription()) <= 1)
                items.remove(item.getDescription());
            else
                items.replace(item.getDescription(), items.get(item.getDescription()) - 1);
        }
    }

    public void removeItem(Item item, int amount) {
        System.out.println(item.getDescription());
        if (items.containsKey(item.getDescription())) {
            if (items.get(item.getDescription()) <= amount)
                items.remove(item.getDescription());
            else
                items.replace(item.getDescription(), items.get(item.getDescription()) - amount);
        }
    }

    //return a list of item in store
    public List<String> getItems(){
        return new ArrayList<>(items.keySet());
    }

    public int getItemAmount(Item item){
        return items.getOrDefault(item.getDescription(), 0);
    }

    public List<Item> getSaleItems(){
        List <Item> output = new ArrayList<>();

        for(String itemDesc : items.keySet()){
            Item item =  SmartShoppers.getInstance().getItem(itemDesc);
            if(item.getSale() > 0) output.add(item);
        }
        return output;
    }

    public List<Item> getSuggestedItems(){
        List <Item> output = new ArrayList<>();

        for(String itemDesc : items.keySet()){
            Item item =  SmartShoppers.getInstance().getItem(itemDesc);
            if(item.getSuggestPoint() > 0) output.add(item);
        }
        return output;
    }

    public boolean equals(Store store){
        return this.getLocation() == store.getLocation();
    }

    public boolean contains(Item item){
        for(String itemDesc : items.keySet()){
            Item i = SmartShoppers.getInstance().getItem(itemDesc);
            if(item.getDescription().equals(i.getDescription()))return true;
        }
        return false;
    }
}
