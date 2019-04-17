public class Customer {
    String name;
    boolean isOnBoarded;
    Account account;

    public Customer(String name) {
        this.name = name;
    }

    public void createAccount(){
        System.out.println("Application has been submitted.");
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Application approved!");
        System.out.println("Issuing your personal credit card.");
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Your personal credit card is issued!");
        System.out.println("Congratulation! You are already onboraded.");
        this.account = new Account(this.name);
        this.isOnBoarded = true;
    }
}
