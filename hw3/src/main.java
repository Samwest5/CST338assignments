public class main {
    public static void main(String[] args) {
        Customer c1 = new Customer("Alice", "1234", 5000, "OtterUnion");
        Customer c2 = new Customer("Tom", "2000", 200, "OtterUnion");
        Customer c3 = new Customer("Monica", "3000", 50, "OtterUnion");
        Customer c4 = new Customer("Michael", "7777", 0, "OtterUnion");
        Customer c5 = new Customer("John", "8000", 500, "OtterUnion");
        Customer c6 = new Customer("Jane", "2222", 500, "OtterUnion");
        Customer c7 = new Customer("Robert", "2323", 200, "BOA");
        Customer c8 = new Customer("Owen", "4455", 50, "BOA");
        Customer c9 = new Customer("Chris", "8787", 10, "BOA");
        Customer c10 = new Customer("Rebecca", "8080", 555.55, "BOA");
        ATM machine1 = new ATM();
        machine1.setLocation("OtterUnion");
        ATM machine2 = new ATM(9998, 200, "BOA", "Library");
//        Customer alice = new Customer();
        System.out.println("==== Welcome to Demo Program ====");
        System.out.println(machine1); System.out.println();
        System.out.println(machine2);
        System.out.println("\n==== Equality Checking ====");
        System.out.println(machine1.equals(machine2));
        System.out.println();

        machine1.setFunds(100);
        machine1.setLocation("BIT");
        machine1.addFunds(400);

        System.out.println(machine1);
        System.out.println();
        machine1.displayMenu();
//        ATM.displayUsers();

        machine1.withdrawal("Alice", "7777", 10.50);
        machine1.withdrawal("Robert", "2323", 10.50);
        machine1.withdrawal("Alice", "1234", 10000);
        machine1.withdrawal("Alice", "1234", 10);
        machine1.withdrawal("Alice", "1234", 2000);

        System.out.println(machine1.status());





    }
}
