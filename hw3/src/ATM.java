import java.util.HashSet;

public class ATM {

    // data members
    private int serialNumber;
    private double funds;
    private String bank;
    private String location;

    private int numWithdrawals = 0;
    private int goodWithdrawls = 0;
    private int badWithdrawals = 0;
    private int numDeposits = 0;
    private int goodDeposits = 0;
    private int badDeposits = 0;
    private int numTransfers = 0;
    private int goodTransfers = 0;
    private int badTransfers = 0;
    private int numTranscations = 0;

    // constructors
    public ATM() {
        serialNumber = 9999;
        funds = 100.0;
        bank = "BOA";
        location = "UNKNOWN";
    }
    public ATM(int serialNumber) {
        this.serialNumber = serialNumber;
        funds = 100;
        bank = "BOA";
        location = "UNKNOWN";
    }
    public ATM(int serialNumber,int funds) {
        this.serialNumber = serialNumber;
        this.funds = funds;
        bank = "BAO";
        location = "UNKNOWN";
    }
    public ATM(double funds, String bank) {
        this.funds = funds;
        this.bank = bank;
        location = "UNKNOWN";
    }
    public ATM(int serialNumber, int funds, String bank, String location) {
        this.serialNumber = serialNumber;
        this.funds = funds;
        this.bank = bank;
        this.location = location;
    }

    // getters
    public String toString() {
        return ("Serial Number: " + serialNumber +
                "\nBank Name: " + bank +
                "\nLocation: " + location +
                "\nBalance: " + funds + "\n"
                );
    }
    public String equals(ATM other) {
        boolean match = true;
        if (this.toString() != other.toString()) {
            match = false;
        }
        return (match +
                "\n" + this.toString());
    }
    public String status() {
        return ("===== Machine Status =====" +
                "\n\tSerial Number: " + serialNumber +
                "\n\tBank Name: " + bank +
                "\n\tLocation: " + location +
                "\n\tBalance: " + funds +
                "\n\t\t" + numTranscations + " Transactions so far: " +
                "\n\t\tWithdrawal: " + numWithdrawals + " (" + goodWithdrawls + " success, " + badWithdrawals + " fail)" +
                "\n\t\tDeposit: " + numDeposits + " (" + goodDeposits + " success, " + badDeposits + " fail)" +
                "\n\t\tTransfer: " + numTransfers + " (" + goodTransfers + " success, " + badTransfers + " fail)"
                );

    }
    public void displayMenu() {
        System.out.println("===== ATM Transaction Menu =====\n" +
                            "\t1. Withdrawal\n" +
                            "\t2. Deposit\n" +
                            "\t3. Transfer\n" +
                            "\t4. Display Users\n" +
                            "\t5. Display Atm Info\n");
    }
    public static void displayUsers() {
        for (int i = 0; i < Customer.getNumCustomers(); i++) {
            System.out.println(Customer.getCustomerAtIndex(i));
            System.out.println();
        }
    }
    public void displayGoodWithdrawal() {
        System.out.println("Succeed - withdrawal");
    }
    public void displayBadWithdrawal() {
        System.out.println("Fail - withdrawal");
    }
    public void displayGoodDeposit() {
        System.out.println("Succeed - deposit");
    }
    public void displayBadDesposit() {
        System.out.println("Fail - deposit");
    }
    public void displayGoodTransfer() {
        System.out.println("Succeed - withdrawal");
    }
    public void displayBadTransfer() {
        System.out.println("Fail - withdrawal");
    }

    // setters
    public boolean transfer(String name, String pin, double amount, String otherName, String otherPin) {
        numTranscations += 1;
        numTransfers += 1;
        if (Customer.checkForCustomer(name) && Customer.checkForCustomer(otherName)) {
            Customer from = Customer.getCustomer(name);
            Customer to = Customer.getCustomer(otherName);
            if (from.getBalance() >= amount && from.getPin().equals(pin) && to.getPin().equals(otherPin)) {
                from.withdraw(amount);
                to.deposit(amount);
                goodTransfers += 1;
                displayGoodTransfer();
                return true;
            }
        }
        badTransfers += 1;
        displayBadTransfer();
        return false;
    }
    public boolean deposit(String name, String pin, double amount) {
        numTranscations += 1;
        numDeposits += 1;
        if (Customer.checkForCustomer(name)) {
            Customer curr = Customer.getCustomer(name);
            if (curr.getPin().equals(pin)) {
                curr.deposit(amount);
                funds += amount;
                displayGoodDeposit();
                goodDeposits += 1;
                return true;
            }
        }
        badDeposits += 1;
        displayBadDesposit();
        return false;
    }
    public boolean withdrawal(String name, String pin, double amount) {
        numTranscations += 1;
        numWithdrawals += 1;

        if (funds <= amount) {
            badWithdrawals += 1;
            displayBadWithdrawal();
            return false;
        }
        if (Customer.checkForCustomer(name)) {
            Customer curr = Customer.getCustomer(name);
            if (curr.getBank() != bank) {
                badWithdrawals += 1;
                displayBadWithdrawal();
                return false;
            }
            if (curr.getPin().equals(pin) && curr.getBalance() >= amount) {
                curr.withdraw(amount);
                funds -= amount;
                goodWithdrawls += 1;
                displayGoodWithdrawal();
                return true;
            }
        }
        badWithdrawals += 1;
        displayBadWithdrawal();
        return false;
    }
    public void addFunds(double amount) {
        funds += amount;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }
    public void setFunds(double funds) {
        this.funds = funds;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
