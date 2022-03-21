package project;

import java.util.ArrayList;
import java.util.List;

public class Category {
        String name;
        List<Item> items;//items in this category

        //Category constructor
        public Category(String name){
                this.name = name;
                items = new ArrayList<Item>();
        }

        //adding an item to this category
        private void addItem(Item item){
                items.add(item);
        }

        //removing an item from this category
        private void removeItem(Item item){
                items.remove(item);
        }
}
