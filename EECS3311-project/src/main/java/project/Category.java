package project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Category {
        int categoryID;
        String desc;
        Set<Item> items;//items in this category

        //Category constructor
        public Category(){
                items = new HashSet<Item>();
        }

        //adding an item to this category
        public void addItem(Item item){
                items.add(item);
        }

        public void addItems(List<Item> items){
                items.addAll(items);
        }

        //removing an item from this category
        private void removeItem(Item item){
                items.remove(item);
        }

        public void setID(int id){
                categoryID = id;
        }

        public int getID(){
                return categoryID;
        }

        public void setDesc(String desc){
                this.desc = desc;
        }

        public String getDesc(){
                return this.desc;
        }

        public Set<Item> getItems(){
                return this.items;
        }
}
