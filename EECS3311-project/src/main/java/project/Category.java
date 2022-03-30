package project;

import java.util.ArrayList;
import java.util.List;

public class Category {
        int categoryID;
        String desc;
        List<Item> items;//items in this category

        //Category constructor
        public Category(){
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

        public List<Item> getItems(){
                return this.items;
        }
}
