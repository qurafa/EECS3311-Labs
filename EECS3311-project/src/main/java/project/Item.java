package project;

public class Item {
    private int itemID;
    private String itemDesc;
    private double itemPrice;
    private int totalAmount;
    private int sale;
    private int suggestPoint = 0;

    public Item(){}

    public void setID(int id){
        itemID = id;
    }

    public void setDescription(String desc){
        itemDesc = desc;
    }

    public void setPrice(double price){
        itemPrice = price;
    }

    public void setTotalAmount(int amount){
        totalAmount = amount;
    }

    public void setSale(int sale){
        this.sale = sale;
    }

    public int getID(){
        return itemID;
    }

    public String getDescription(){
        return itemDesc;
    }

    public double getPrice(){
        return itemPrice;
    }

    public int getTotalAmount(){
        return totalAmount;
    }

    public int getSale(){
        return sale;
    }

    public boolean equals(Item item){
        return item.toString().equals(this.toString());
    }

    public int getSuggestPoint(){
        return suggestPoint;
    }

    public void setSuggestPoint(int suggestedPoint){
        this.suggestPoint = suggestedPoint;
    }

    public void increaseSuggestPoint(){
        suggestPoint++;
    }

    public void decreaseSuggestPoint(){
        suggestPoint--;
    }

    public String toString(){
        return itemDesc; //+ ", " + itemPrice + ", " + totalAmount + ", " + sale;
    }
}
