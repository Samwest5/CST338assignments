// Samuel Westigard
// OnlineDemo.java
// Homework 5
// Displays menu for user to interact with online store
// 10/26/2018
import java.util.Scanner;

public class OnlineDemo {

    public static void displayMenu() {
        System.out.println("\nSelect your choice");
        System.out.println("\t1. Add Customer");
        System.out.println("\t2. Customer Info");
        System.out.println("\t3. Add Product");
        System.out.println("\t4. Delete Product");
        System.out.println("\t5. Product Info");
        System.out.println("\t6. Make Order");
        System.out.println("\t7. Exit");
    }

    public static void main(String args[]) {
        OnlineStore store = new OnlineStore();
        System.out.println("Welcome to CSUMB OnlineStore");
        Scanner input = new Scanner(System.in);
        displayMenu();
        int choice = input.nextInt();
        input.nextLine();
        while (true) {
            switch (choice) {
                case 1:
                    store.addCustomer();
                    break;
                case 2:
                    store.customerInfo();
                    break;
                case 3:
                    store.addProduct();
                    break;
                case 4:
                    store.deleteProduct();
                    break;
                case 5:
                    if (!store.noProducts()) {
                        System.out.println("No Product information");
                        break;
                    }
                    System.out.print("Enter product number (or just enter to display all products): ");
                    String productNumber = input.nextLine();
                    if (productNumber.equals("")) {
                        store.allProductInfo();
                    }
                    else {
                        store.productInfo(Integer.parseInt(productNumber));
                    }
                    break;
                case 6:
                    store.makeOrder();
                    break;
                case 7:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Choice must be between 1 and 7");
            }
            displayMenu();
            choice = input.nextInt();
            input.nextLine();
        }
    }
}
