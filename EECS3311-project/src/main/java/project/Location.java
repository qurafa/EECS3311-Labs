package project;

public class Location {
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Location(){}

    public void setAddress(String address){
        this.address = address;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getAddress(){
        return this.address;
    }

    public String getCity(){
        return this.city;
    }

    public String getState(){
        return this.state;
    }

    public String getPostalCode(){
        return this.postalCode;
    }

    public String getCountry(){
        return this.country;
    }

    public String toString(){
        return this.address + ", " + this.city + ", " + this.state + ", " + this.postalCode + ", " + this.country;
    }

    public boolean equals(Location location){
        return this.toString().equals(location.toString());
    }
}
