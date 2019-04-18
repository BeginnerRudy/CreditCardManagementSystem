public class Customer {
    private String name;
    private boolean isOnBoarded;
    private CreditCard creditCard;
    private Account account;
    private boolean hasOverDueHistory = false;
    private boolean hasOverDueBill = false;

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
            this.account.addBill(new Bill(400, System.currentTimeMillis()));

            System.out.println("You spend 400 dollars");
            this.creditCard.setLastUsed(System.currentTimeMillis());
            this.account.creditChecking();
            this.account.checkingBill(System.currentTimeMillis());

            if (this.account.getAvailableFund() < 0){
                this.account.suspended();
                this.creditCard.setActive(false);
                System.out.println("Your card has been set to inactive.");
                System.out.println(String.format("Your account is in %s, you cannot your card until you have more than zero available funds", account.getState()));
            }
        }else{
            System.out.println(String.format("Your account is in %s, you cannot use your card now", account.getState()));
        }
    }
    public void reportLostCard(){
        this.account.pending();
        this.creditCard.setActive(false);
    }
    public void payBill(double amount){

        if (account.getBills().isEmpty() == false){
            this.account.setAvailableFund(this.account.getAvailableFund() + amount);
            account.getBills().remove(0);
            this.hasOverDueBill = false;
            this.account.active();
            this.creditCard.setActive(true);
        }else{
            System.out.println("Pay bill failed, since you do not have any bill to pay!");
        }

        if ((this.account.getState() == Account.State.Suspended
            || this.account.getState() == Account.State.GracePeriod
            || this.account.getState() == Account.State.PlanOffered)
                && this.account.getAvailableFund() >= 0) {
            this.account.active();
            this.creditCard.setActive(true);
        }else if (this.account.getState() == Account.State.Suspended){
            System.out.println(String.format("Account is still suspended, the current available fund is %f.", this.account.getAvailableFund() ));
        }else{
            this.account.creditChecking();
        }
    }
    public boolean hasOverDueBill(long currTime){
        for (Bill bill : this.account.getBills()){
            if (bill.isOverDue(currTime)){
                this.hasOverDueBill = true;
                return true;
            }
        }
        return false;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
    public Account getAccount() {
        return account;
    }
    public String getName() {
        return name;
    }

    public boolean isHasOverDueHistory() {
        return hasOverDueHistory;
    }

    public boolean isHasOverDueBill() {
        return hasOverDueBill;
    }

    public void setHasOverDueHistory(boolean hasOverDueHistory) {
        this.hasOverDueHistory = hasOverDueHistory;
    }
}
