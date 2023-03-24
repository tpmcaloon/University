public class Seat {

    //Private Members for Seat Class
    private int row;
    private int seat;
    private String flyingClass;
    private Passenger allocatedTo;

    //Getter & Setter for Row
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    //Getter  & Setter for Seat
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }

    //Getter & Setter for Flying Class
    public String getFlyingClass() {
        return flyingClass;
    }
    public void setFlyingClass(String flyingClass) {
        this.flyingClass = flyingClass;
    }

    //Getter & Setter for Seat Allocation
    public Passenger getAllocatedTo() {
        return allocatedTo;
    }
    public void setAllocatedTo(Passenger allocatedTo) {
        this.allocatedTo = allocatedTo;
    }

    //Constructor That Sets Row, Seat & Flying Class
    public Seat(int row, int seat, String flyingClass) {
        this.row = row;
        this.seat = seat;
        this.flyingClass = flyingClass;
    }
}
