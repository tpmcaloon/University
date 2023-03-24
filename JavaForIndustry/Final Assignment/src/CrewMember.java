public class CrewMember extends Person {

    //Constructor That Takes Name, Passport Number
    public CrewMember(String name, int passportNumber) {
        super(name, passportNumber);
    }

    @Override
    public double calculatePersonWeight() {
        return 75;
    }

    @Override
    public String toString() {
        return getName();
    }
}