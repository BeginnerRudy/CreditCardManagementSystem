import java.util.ArrayList;

public class Account {
    public static final long Grace_Period_Duration = 10000 * 4;
    enum State{
        Pending, Active, Suspended, Default, Close, GracePeriod, PlanOffered
    }
    // The default state is pending
    private State state = State.Pending;
//    private String id;
    private String username;
    private double creditLimit = 300;
    private double availableFund = 300;
    private double creditUsed = 0;
    private ArrayList<Bill> bills = new ArrayList<>();
    private long gracePeriodStartTimeStamp;


    public Account(String username) {
        this.username = username;
        System.out.println("+++++++++++Account State Info+++++++++++++");
        System.out.println("Current State: Pending\nTransition: Start -> Pending\nEvent -> Customer is onboarded\nAction: Account is pending");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
    }


    public void close() {
        this.state = State.Close;
    }

    public void pending(){
        if (this.state == State.Pending){
            System.out.println("The account is already in pending state before");
        }else if (this.state == State.Close){
            System.out.println("The account has already by closed, please apply for a new account");
        }else if (this.state == State.Active){
            this.state = State.Pending;
            System.out.println("+++++++++++Account State Info+++++++++++++");
            System.out.println(String.format("Current State: Pending\nTransition: %s -> Pending\nEvent -> Customer reports the card is lost or missing\nAction: Account is pending", this.state));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        }else{
            System.out.println("Only could turn to pending when you are in active state");
            System.out.println(String.format("You are currently in the state of %s", this.state));
        }
    }
    public void suspended(){
        if (this.state == State.Active){
            this.state = State.Suspended;
            System.out.println("+++++++++++Account State Info+++++++++++++");
            System.out.println("Current State: Suspended\nTransition: Active -> Suspended\nEvent -> Credit used over credit limit\nAction: Account is suspended.");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        }else{
            System.out.println("Invalid Transition");
        }
    }
    public void active(){
        if (this.state == State.Pending){
            this.state = State.Active;
            System.out.println("+++++++++++Account State Info+++++++++++++");
            System.out.println("Current State: Active\nTransition: Pending -> Active\nEvent -> Credit card is activated\nAction: Account is active");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        }else if(this.state == State.Active){
            System.out.println("The account has already been activated before!");
        }else if (this.state == State.Close){
            System.out.println("This account has been closed, please apply for another new account.");
        }else if ((this.getState() == Account.State.Suspended
                || this.getState() == Account.State.GracePeriod
                || this.getState() == Account.State.PlanOffered)){
            System.out.println("+++++++++++Account State Info+++++++++++++");
            System.out.println(String.format("Current State: Active\nTransition: %s -> Active\nEvent -> " +
                    "Having more than 0 available funds\nAction: Account is active", this.state));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");

            this.state = State.Active;
        }else if (this.state == State.Default){
            this.state = State.Active;
            System.out.println("+++++++++++Account State Info+++++++++++++");
            System.out.println("Current State: Active\nTransition: Default -> Active\nEvent -> Pays all the overdue bill\nAction: Account is active");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        }
    }
    public void toDefault() {
        if (this.state == State.Active || this.state == State.Suspended) {
            System.out.println("+++++++++++Account State Info+++++++++++++");
            System.out.println(String.format("Current State: Default\nTransition: %s -> Default\n" +
                    "Event -> There is overdue bill\nAction: Account is in default state", this.state));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
            this.state = State.Default;
        } else if (this.state == State.Default) {
            System.out.println("The account has already been in default state! Please pay the overdue bills");
        } else if (this.state == State.Close) {
            System.out.println("This account has been closed, please apply for another new account.");
        }
    }
    public void PlanOffered() {
        if (this.state == State.Default) {
            System.out.println("+++++++++++Account State Info+++++++++++++");
            System.out.println(String.format("Current State: PlanOffered\nTransition: %s -> PlanOffered\n" +
                    "Event -> There is overdue bill and the customer has overdue payment history\n" +
                    "Action: Account is in PlanOffered state", this.state));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
            this.state = State.PlanOffered;
        } else if (this.state == State.Close) {
            System.out.println("This account has been closed, please apply for another new account.");
        }else{
            System.out.println(String.format("Invalid transition: %s -> PlanOffered", state));
        }
    }
    public void GracePeriod(long currTime) {
        if (this.state == State.Default) {
            System.out.println("+++++++++++Account State Info+++++++++++++");
            System.out.println(String.format("Current State: GracePeriod\nTransition: %s -> GracePeriod\n" +
                    "Event -> There is overdue bill and the customer has no overdue payment history\n" +
                    "Action: Account is in GracePeriod state", this.state));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
            this.state = State.GracePeriod;
            this.gracePeriodStartTimeStamp = currTime;
        } else if (this.state == State.Close) {
            System.out.println("This account has been closed, please apply for another new account.");
        }else{
            System.out.println(String.format("Invalid transition: %s -> GracePeriod", state));
        }
    }
    public void creditChecking(){
        System.out.println(String.format("Credit Limit: %.1f Credit Used: %.1f Available Fund %f", this.creditLimit, this.creditUsed, this.availableFund));
    }
    public void checkingBill(long currTime){
        if (bills.isEmpty()){
            System.out.println("Hey, you have no bill now.");
        }else{
            for (Bill bill : bills){
                System.out.println(bill.toString(currTime));
            }
        }
    }
    public boolean isOverGracePeriod(long currTime){
        return (Grace_Period_Duration - (currTime - gracePeriodStartTimeStamp) < 0);
    }

    public double getCreditUsed() {
        return creditUsed;
    }
    public State getState() {
        return state;
    }
    public double getAvailableFund() {
        return availableFund;
    }
    public void setAvailableFund(double availableFund) {
            this.availableFund = availableFund;
    }
    public void setCreditUsed(double creditUsed) {
        this.creditUsed = creditUsed;
    }
    public void addBill(Bill bill) {
        this.bills.add(bill);
    }
    public ArrayList<Bill> getBills() {
        return bills;
    }

    public long getGracePeriodStartTimeStamp() {
        return gracePeriodStartTimeStamp;
    }
}
