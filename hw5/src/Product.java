// Samuel Westigard
// Product.java
// Homework 5
// Implementation of product class
// 10/26/2018
public class Product {
    private String name;
    private int ID;
    private double price;

    public Product(String name, int ID, double price) {
        this.name = name;
        this.ID = ID;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public int getID() {
        return ID;
    }
    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return "\nNo: " + ID + " - " + name + ", Price: " + price;
    }
}
