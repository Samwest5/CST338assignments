// Samuel Westigard
// OnlineStore.java
// Homework 5
// Implements options from OnlineDemo.java, controls Customer and Product objects
// 10/26/2018
import java.util.*;

public class OnlineStore {
    private HashMap<Integer, Customer> customers;
    private HashMap<Integer, Product> products;
    private HashSet<Integer> usedCustomerNumbers;
    private HashSet<Integer> usedProductNumbers;
    private HashSet<Integer> usedOrderNumbers;

    Random random;
    Scanner input;

    public OnlineStore() {
        random = new Random();
        input = new Scanner(System.in);
        customers = new HashMap<>();
        products = new HashMap<>();
        usedCustomerNumbers = new HashSet<>();
        usedProductNumbers = new HashSet<>();
        usedOrderNumbers = new HashSet<>();
    }
    public void addCustomer() {
        int customerNumber = random.nextInt(201) + 100;
        while (usedCustomerNumbers.contains(customerNumber)) {
            customerNumber = random.nextInt(201) + 100;
        }
        usedCustomerNumbers.add(customerNumber);
        System.out.println("Customer Number: " + customerNumber);
        System.out.print("Customer Name: ");
        String name = input.nextLine();
        customers.put(customerNumber, new Customer(customerNumber, name));
        System.out.println("Customer Added - " + name + " (# " + customerNumber + ")");
    }
    public void customerInfo() {
        if (customers.isEmpty()) {
            System.out.println("No customer information");
        }
        else {
            int numCustomers = customers.size();
            System.out.println("==== " + numCustomers + " Customers ====");
            for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
                String name = entry.getValue().getName();
                int ID = entry.getValue().getID();
                System.out.println(name + " - " + ID);
            }
        }
    }
    public void addProduct() {
        int productNumber = random.nextInt(201) + 500;
        while (usedProductNumbers.contains(productNumber)) {
            productNumber = random.nextInt(201) + 500;
        }
        usedProductNumbers.add(productNumber);
        System.out.println("Product Number: " + productNumber);
        System.out.print("Product Name: ");
        String name = input.nextLine();
        System.out.print("Product Price: ");
        double price = input.nextDouble();
        input.nextLine();
        products.put(productNumber, new Product(name, productNumber, price));
        System.out.println("Product Added - " + name + ", No: " + productNumber + ", Price: " + price);
    }
    public void deleteProduct() {
        System.out.print("Product number to delete");
        int productNumber = input.nextInt();
        input.nextLine();
        if (!products.containsKey(productNumber)) {
            System.out.println("Input Error: Product " + productNumber + " doesn't exist.");
        }
        else {
            usedProductNumbers.remove(productNumber);
            products.remove(productNumber);
            System.out.println("Product " + productNumber + " deleted.");
        }
    }
    public void productInfo(int productNumber) {
        if (!products.containsKey(productNumber)) {
            System.out.println("Input Error: Product " + productNumber + " doesn't exist.");
        }
        else {
            Product product = products.get(productNumber);
            System.out.println(product);
        }
    }
    public void allProductInfo() {
        if (products.isEmpty()) {
            System.out.println("No products to display.");
        }
        else {
            for (Map.Entry<Integer, Product> entry : products.entrySet()) {
                System.out.print(entry.getValue());
            }
        }
        System.out.println();
    }
    public void makeOrder() {
        int orderNumber = random.nextInt(1001) + 1000;
        while (usedOrderNumbers.contains(orderNumber)) {
            orderNumber = random.nextInt(1001) + 1000;
        }
        usedOrderNumbers.add(orderNumber);
        System.out.println("Order Number: " + orderNumber);
        System.out.print("Type Customer Number: ");
        int customerNumber = input.nextInt();
        input.nextLine();
        if (!customers.containsKey(customerNumber)) {
            System.out.println("Input Error: Customer number " + customerNumber + " doesn't exist.");
            return;
        }
        ArrayList<Integer> productNumbers = new ArrayList<>();
        while (true) {
            System.out.print("Type Product Number (0 to finish): ");
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 0) {
                break;
            }
            if (!products.containsKey(choice)) {
                System.out.println("Input Error: Product " + choice + " does not exist.");
            }
            else {
                productNumbers.add(choice);
            }
        }
        Customer customer = customers.get(customerNumber);
        String customerName = customer.getName();
        double totalPrice = 0;
        System.out.print("Order Summary - Order Number: " + orderNumber);
        System.out.println(", Customer: " + customerName);
        for(int i = 0; i < productNumbers.size(); i++) {
            Product product = products.get(productNumbers.get(i));
            String productName = product.getName();
            int productID = product.getID();
            double price = product.getPrice();
            totalPrice += price;
            System.out.println("\tItem: " + (i+1) + ": " + productName + "(No. " + productID + "): $" + price);
        }
        System.out.println("\tTotal Price: $" + totalPrice);
    }
    boolean noProducts() {
        if (!products.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

}
