import java.util.ArrayList;

public class Customer {

    // data members
    private static ArrayList<Customer> customers = new ArrayList<>();
    private String name;
    private String pin;
    private double balance;
    private String bank;

    // constructors
    public Customer() {
        name = "Alice Otter";
        pin = "0000";
        balance = 100.00;
        bank = "OtterUnion";
        customers.add(this);
    }
    public Customer(String name) {
        this.name = name;
        pin = "0000";
        balance = 100.00;
        bank = "OtterUnion";
        customers.add(this);
    }
    public Customer(String name, String pin) {
        this.name = name;
        this.pin = pin;
        balance = 100.00;
        bank = "OtterUnion";
        customers.add(this);
    }
    public Customer(String name, String pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        bank = "OtterUnion";
        customers.add(this);
    }
    public Customer(String name, String pin, double balance, String bank) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.bank = bank;
        customers.add(this);
    }

    // getters
    public String toString() {
        return ("Name: " + name +
                "\nPin: " + pin +
                "\nBalance: " + balance +
                "\nBank: " + bank);
    }
    public String getName() {
        return name;
    }
    public String getPin() {
        return pin;
    }
    public double getBalance() {
        return balance;
    }
    public String getBank() {
        return bank;
    }
    public static boolean checkForCustomer(String name) {
        for (Customer curr : customers) {
            if (curr.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
    public static Customer getCustomer(String name) {
        for (Customer curr: customers) {
            if (curr.name.equals(name)) {
                return curr;
            }
        }
        return null;
    }
    public static Customer getCustomerAtIndex(int index) {
        return customers.get(index);
    }
    public static int getNumCustomers() {
        return customers.size();
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public void withdraw(double amount) {
        this.balance -= amount;
    }
    public void deposit(double amount) {
        this.balance += amount;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }




}


