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
        System.out.println("State: Pending\nTransition: Start -> Pending\nEvent -> Customer is onboarded\nAction: Account is pending");
    }

    public State getState() {
        return state;
    }

    public void close() {
        this.state = State.Close;
    }
}
