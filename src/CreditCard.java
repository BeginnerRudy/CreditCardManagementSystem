import java.util.Date;

public class CreditCard {
    private String cardID;
    private double creditLimit = 1000;
    private double availableFund = 500;
    private boolean isActive = false;
    private boolean isLostOrStolen = false;
    private Date lastUsedDate;
    private Customer cardHolder;
    private Account account;

    public CreditCard(Customer cardHolder) {
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Your personal credit card is issued!");
        System.out.println("Congratulation! You are already onboraded.");
        this.cardHolder = cardHolder;
        account = new Account(cardHolder.getName());
    }

    public void setActive(boolean active) {
        isActive = active;
        System.out.println(String.format("The credit card is active now!"));
        account.active();
    }
}
