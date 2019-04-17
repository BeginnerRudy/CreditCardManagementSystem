public class Customer {
    private String name;
    private boolean isOnBoarded;
    private CreditCard creditCard;

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
    }

    public void activeCreditCard(){
        this.creditCard.setActive(true);
    }

    public void useCard(){
        if (this.creditCard.isActive()) {
            this.creditCard.setCreditUsed(this.creditCard.getCreditUsed() + 400);
        }else{
            System.out.println("Please activate your credit card first!");
        }
    }

    public void reportLostCard(){
        this.
    }
}
