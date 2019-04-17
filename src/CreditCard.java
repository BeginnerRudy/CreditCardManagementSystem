import java.util.Date;

public class CreditCard {
    private String cardID;
    private boolean isActive = false;
    private boolean isLostOrStolen = false;
    private Date lastUsedDate;
    private Customer cardHolder;

    public CreditCard(Customer cardHolder) {
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Your personal credit card is issued!");
        System.out.println("The available fund is 500");
        System.out.println("The credit limit is 500");
        System.out.println("Congratulation! You are already onboraded.");
        this.cardHolder = cardHolder;
    }

    public boolean isActive() {
        return isActive;
    }



    public void setActive(boolean active) {
        this.isActive = active;
    }
}
