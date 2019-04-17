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

    public void active(){
        if (this.state != State.Close){
            this.state = State.Active;
            System.out.println("+++++++++++Account State Info+++++++++++++");
            System.out.println("Current State: Active\nTransition: Pending -> Active\nEvent -> Credit card is activated\nAction: Account is active");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        }else{
            System.out.println("This account has been closed, please apply for another new account.");
        }
    }
}
