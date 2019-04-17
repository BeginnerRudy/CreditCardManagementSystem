public class Account {
    enum State{
        Pending, Active, Suspended, Default, Close;
    }
    // The default state is pending
    private State state = State.Pending;
//    private String id;
    private String username;

    public Account(String username) {
        this.username = username;
        System.out.println("+++++++++++Account State Info+++++++++++++");
        System.out.println("Current State: Pending\nTransition: Start -> Pending\nEvent -> Customer is onboarded\nAction: Account is pending");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
    }

    public State getState() {
        return state;
    }

    public void close() {
        this.state = State.Close;
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
        }
    }
}
