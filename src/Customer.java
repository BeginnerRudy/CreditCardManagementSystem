public class Customer {
    private String name;
    private boolean isOnBoarded;
    private CreditCard creditCard;
    private Account account;

    public Customer(String name) {
        this.name = name;
        System.out.println(String.format("Successfully register %s as a new customer.", name));
    }

    public void applyForAccount(){
        System.out.println("Application has been submitted.");
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Loading.......");
        System.out.println("Application approved!");
        System.out.println("Starting to issue your personal credit card.");
        this.issueCreditCard();
        this.isOnBoarded = true;
    }

    public String getName() {
        return name;
    }

    public void issueCreditCard(){
        this.creditCard = new CreditCard(this);
        this.account = new Account(this.name);
    }

    public void activeCreditCard(){
        if (this.creditCard.isActive() == false) {
            this.creditCard.setActive(true);
            System.out.println("Card has been activated successfully");
            this.account.active();
        }else{
            System.out.println("Card has been activated already");
        }
    }

    public void useCard(){
        if (this.creditCard.isActive()) {
            this.account.setAvailableFund(this.account.getAvailableFund() - 400);
            this.account.setCreditUsed(this.account.getCreditUsed() + 400);
            System.out.println("You spend 400 dollars");
            this.account.creditChecking();

            if (this.account.getAvailableFund() < 0){
                this.account.suspended();
                this.creditCard.setActive(false);
                System.out.println("Your card has been set to inactive.");
                System.out.println("Your account has been suspended, you cannot your card until you have more than zero available funds");
            }
        }else{
            System.out.println("Your account has already been suspended, you cannot your card until you have more than zero available funds");
        }
    }

    public Account getAccount() {
        return account;
    }

    public void reportLostCard(){
        this.account.pending();
        this.creditCard.setActive(false);
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void payBill(double amount){

        this.account.setAvailableFund(this.account.getAvailableFund() + amount);
        if (this.account.getState() == Account.State.Suspended && this.account.getAvailableFund() >= 0) {
            this.account.active();
            this.creditCard.setActive(true);
        }else if (this.account.getState() == Account.State.Suspended){
            System.out.println(String.format("Account is still suspended, the current available fund is %f.", this.account.getAvailableFund() ));
        }else{
            this.account.creditChecking();
        }
    }
}
