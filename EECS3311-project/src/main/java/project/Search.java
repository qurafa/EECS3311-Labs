package project;

import java.util.ArrayList;
import java.util.List;

public class Search {

    public static List<Item> searchItem(String search){
        List<Item> output = new ArrayList<>();

        for(Item item : SmartShoppers.getInstance().getItems()){
            String iString = item.getDescription().replaceAll(" ", "").replaceAll(",", "").toLowerCase();
            String searchString = search.replaceAll(" ", "").replaceAll(",","").toLowerCase();
            if(iString.contains(searchString))
                output.add(item);
        }

        return output;
    }

    public static List<Item> searchItem(String search, Store store){
        List<Item> output = new ArrayList<>();

        for(Item item : SmartShoppers.getInstance().getItems()){
            if(store.getItemAmount(item) > 0){
                String iString = item.getDescription().replaceAll(" ", "").replaceAll(",", "").toLowerCase();
                String searchString = search.replaceAll(" ", "").replaceAll(",","").toLowerCase();
                if(iString.contains(searchString))
                    output.add(item);
            }
        }

        return output;
    }

    public static List<Category> searchCategory(String search){
        List<Category> output = new ArrayList<>();

        for(Category cat : SmartShoppers.getInstance().getCategories()){
            String cString = cat.getDesc().replaceAll(" ", "").replaceAll(",", "").toLowerCase();
            String searchString = search.replaceAll(" ", "").replaceAll(",","").toLowerCase();
            if(cString.contains(searchString))
                output.add(cat);
        }

        return output;
    }

    public static List<Category> searchCategory(String search, Store store){
        List<Category> output = new ArrayList<>();

        for(Category cat : SmartShoppers.getInstance().getCategories()){
            for(Item item : cat.getItems()){
                if(store.getItemAmount(item) > 0){
                    String cString = cat.getDesc().replaceAll(" ", "").replaceAll(",", "").toLowerCase();
                    String searchString = search.replaceAll(" ", "").replaceAll(",","").toLowerCase();
                    if(cString.contains(searchString))
                        output.add(cat);
                }
            }
        }

        return output;
    }

    public static List<Store> searchStore(String search){
        List<Store> output = new ArrayList<>();

        for(Store store : SmartShoppers.getInstance().getStores()){
            String sString = store.getLocation().toString().replaceAll(" ", "").replaceAll(",","").toLowerCase();
            String searchString = search.replaceAll(" ","").replaceAll(",","").toLowerCase();
            if(sString.contains(searchString))
                output.add(store);
        }

        return output;
    }

}
