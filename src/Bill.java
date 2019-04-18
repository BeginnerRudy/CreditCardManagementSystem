public class Bill {
    public static final long due = 1 * 10000;
    private double ampunt;
    private long timeStamp;

    public Bill(double ampunt, long timeStamp) {
        this.ampunt = ampunt;
        this.timeStamp = timeStamp;
    }

    public boolean isOverDue(long currentTimeMili){
        return (due - (currentTimeMili - this.timeStamp) < 0);
    }

    public String toString(long currentTime){
        String out = "";
        out += String.format("Bill issue time %d ", timeStamp);
        out += String.format("Bill amount is %f ", ampunt );
        out += String.format("Bill would over due in %d second\n", (due - (currentTime - timeStamp))/1000);

        return out;
    }
}
