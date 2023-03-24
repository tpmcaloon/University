import java.util.Objects;

public class Passenger extends Person {

    public static double weight;
    //Private Members for Passenger Concrete Class
    private int holdBags;
    private String flightClass;

    //Getter & Setter for Hold Bags
    public int getHoldBags() {
        return holdBags;
    }
    public void setHoldBags(int holdBags) {
        this.holdBags = holdBags;
    }

    //Getter & Setter for Flight Class
    public String getFlightClass() {
        return flightClass;
    }
    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    //Constructor That Takes Name, Passport Number, Flight Class & HoldBags
    public Passenger(String name, int passportNumber, String flightClass, int holdBags) {
        super(name, passportNumber);
        this.flightClass = flightClass;
        this.holdBags = holdBags;

    }

    public double calculatePersonWeight() {
        double bagWeight = (25 * holdBags);
        double economyPassengerWeight = 80;
        double firstClassPassengerWeight = 87.5;
        double weight;

        if (Objects.equals(this.flightClass, "first")){
            weight = firstClassPassengerWeight + bagWeight;
        }
        else {
            weight = economyPassengerWeight + bagWeight;
        }
        return weight;
    }
}
