import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Flight {

    //Private Members for Flight Class
    private int flightNumber;
    private Aircraft craft;
    private String startLocation;
    private String endLocation;
    private double distance;

    //Getter & Setter for Flight Number
    public int getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    //Getter & Setter for Aircraft
    public Aircraft getCraft() {
        return craft;
    }
    public void setCraft(Aircraft craft) {
        this.craft = craft;
    }

    //Getter & Setter for Start Location
    public String getStartLocation() {
        return startLocation;
    }
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    //Getter & Setter for End Location
    public String getEndLocation() {
        return endLocation;
    }
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    //Getter & Setter for Distance
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public ArrayList<CrewMember> crew; // ArrayList for Crew
    public ArrayList<String> seats = new ArrayList<>(); // ArrayList for Seats

    //Constructor That Takes Flight Number, Craft, Start Location, End Location, Distance
    public Flight(int flightNumber, Aircraft craft, String startLocation, String endLocation, double distance) {
        this.flightNumber = flightNumber;
        this.craft = craft;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;

        //Getting the file from the Aircraft to get layout of seats
        File a = toString(craft.getLayoutFile());
        Scanner in;

        try {
            in = new Scanner(a);

            while (in.hasNextLine()) {
                String row = in.nextLine();
                String[] s_data = row.split(",");
                seats.addAll(Arrays.asList(s_data));
                    }

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        this.crew = new ArrayList<>();
    }

    //Initialising and making the .txt a string
    private File toString(File layoutFile) {
        return layoutFile;
    }

    //Method to calculate the takeoff weight, -1 = too heavy to fly
    public double calculateTakeOffWeight() {
        double totalWeight = 0;
        totalWeight = craft.getCraftWeight() + 15000;
        if (totalWeight > craft.getMaximumTakeoffWeight()) {
            totalWeight = -1;
        }
        return totalWeight;
    }

    //Method to get the next Available seat using the flying class of passengers
    public int getNextAvailableSeat(String flyingClass) {
        File a = toString(craft.getLayoutFile());
        Scanner in;

        try {
            in = new Scanner(a);

            while (in.hasNextLine()) {
                String row = in.nextLine();
                String[] fSeat_data = row.split("F");
                String[] eSeat_data = row.split("E");
                seats.addAll(Arrays.asList(fSeat_data));
                seats.addAll(Arrays.asList(eSeat_data));
            }

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return 0;
    }

    //Method to get book passengers onto a flight and upgrade/ downgrade them
    static int bookSeat(Passenger p) {
        int value = 0;
        String pClass = p.getFlightClass();

        if (pClass.equals("economy")) {
            value = 3; //Value is the number the system uses to dictate whether the booking is completed(1), upgrading(2), downgrading(3) or full(-1)
        }
        return value;
    }


    @Override
    public String toString() {
        String newline = System.lineSeparator();

        return "Flight #" + flightNumber + newline +
                "Start Location: " + startLocation + newline +
                "End Location: " + endLocation + newline +
                "Distance: " + distance + newline +
                "First Class Passengers: " + (seats.size() - 72) + newline +
                "Economy Passengers: " + (seats.size() - 12)+ newline +
                "Unallocated Seats: " + seats.size() + newline +
                "Crew: " + crew + newline +
                "Aircraft: " + craft +
                "Aircraft: " + getNextAvailableSeat("first");

    }
}

