package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    Map<Item, Integer> items;

    public ShoppingCart(){
        items = new HashMap<>();
    }

    public void addItem(Item item){
        if(items.containsKey(item))
            items.replace(item, items.get(item)+1);
        else
            items.put(item, 1);
    }

    public void addItem(Item item, int count){
        if(items.containsKey(item))
            items.replace(item, items.get(item)+count);
        else
            items.put(item, count);
    }

    public void removeItem(Item item){
        if(items.containsKey(item)){
            if(items.get(item) <= 1)
                items.remove(item);
            else
                items.replace(item, items.get(item)-1);
        }
    }

    public void removeItem(Item item, int count){
        if(items.containsKey(item)){
            if(items.get(item) <= count)
                items.remove(item);
            else
                items.replace(item, items.get(item)-count);
        }
    }

    public List<Item> getItems(){
        System.out.println(items.keySet().size());
        return new ArrayList<Item>(items.keySet());
    }

    public int getItemAmount(Item item){
        return items.get(item);
    }

    public void clearCart(){
        items.clear();
    }
}
