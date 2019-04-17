import java.util.Date;

public class CreditCard {
    private String cardID;
    private double creditLimit = 500;
    private double availableFund = 500;
    private double creditUsed = 0;
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
        System.out.println("The available fund is 500");
        System.out.println("The credit limit is 500");
        System.out.println("Congratulation! You are already onboraded.");
        this.cardHolder = cardHolder;
        account = new Account(cardHolder.getName());
    }

    public boolean isActive() {
        return isActive;
    }

    public double getCreditUsed() {
        return creditUsed;
    }

    public void setAvailableFund(double availableFund) {
        this.availableFund = availableFund;
    }

    public void setCreditUsed(double creditUsed) {
        if (this.account.getState() == Account.State.Suspended){
            System.out.println("You have been over the credit limit, you cannot use this card this month");
            System.out.println(String.format("Credit Limit: %.1f Credit Used: %.1f", this.creditLimit, this.creditUsed));
        }else {
            if (this.creditUsed > this.creditLimit) {
                this.account.suspended();
                System.out.println("The account is suspended due to over credit limit");
                System.out.println(String.format("Credit Limit: %.1f Credit Used: %.1f", this.creditLimit, this.creditUsed));
            } else {
                this.creditUsed = creditUsed;
                System.out.println("Spent 400 use credit card");
                System.out.println(String.format("Credit Limit: %.1f Credit Used: %.1f", this.creditLimit, this.creditUsed));
            }
        }
    }

    public void setActive(boolean active) {
        if (!this.isActive) {
            isActive = active;
            System.out.println(String.format("The credit card is active now!"));
            account.active();
        }else{
            System.out.println("The card has already been activated before!");
        }
    }
}
