import java.io.File;

public class Aircraft {

    //Private Members for Aircraft Class
    private String make;
    private String model;
    private String tailNumber;
    private double craftWeight;
    private double maximumTakeoffWeight;
    private File layoutFile;

    //Getter & Setter for Make
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    //Getter & Setter for Model
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    //Getter & Setter for Tail Number
    public String getTailNumber() {
        return tailNumber;
    }
    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    //Getter & Setter for Craft Weight
    public double getCraftWeight() {
        return craftWeight;
    }
    public void setCraftWeight(double craftWeight) {
        this.craftWeight = craftWeight;
    }

    //Getter & Setter for Maximum Takeoff Weight
    public double getMaximumTakeoffWeight() {
        return maximumTakeoffWeight;
    }
    public void setMaximumTakeoffWeight(double maximumTakeoffWeight) {
        this.maximumTakeoffWeight = maximumTakeoffWeight;
    }

    //Getter & Setter for Layout File
    public File getLayoutFile() {
        return layoutFile;
    }
    public void setLayoutFile(File layoutFile) {
        this.layoutFile = layoutFile;
    }

    //Constructor That Takes Make, Model, Tail Number, Craft Weight & Maximum Takeoff Weight
    public Aircraft(String make, String model, String tailNumber, double craftWeight, double maximumTakeoffWeight, File layoutFile) {
        this.make = make;
        this.model = model;
        this.tailNumber = tailNumber;
        this.craftWeight = craftWeight;
        this.maximumTakeoffWeight = maximumTakeoffWeight;
        this.layoutFile = layoutFile;
    }

    @Override
    public String toString() {
        return "Make: " + this.make +
                " Model: " + this.model +
                " Tail Number: " + this.tailNumber +
                " Craft Weight: " + this.craftWeight +
                " Maximum Takeoff Weight: " + this.maximumTakeoffWeight;
    }
}