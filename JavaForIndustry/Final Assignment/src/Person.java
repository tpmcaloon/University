public abstract class Person {

    // Private Instance Variables
    private String name;
    private int passportNumber;

    // Getter and Setter for Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Passport Number
    public int getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    // Constructor with Arguments Name & Passport Number
    public Person(String name, int passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    // Abstract Method to Calculate Person Weight
    public abstract double calculatePersonWeight();


    @Override
    public String toString() {
        return "Name: " + name + ' ' +
                "PassportNumber: " + passportNumber;
    }
}