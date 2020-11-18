package warehouseApp;

public class Floor {

    /* details stored about each floor */
    int floorNumber;
    String securityLevel;
    Double floorTemperature;
    static MyList<Aisle> aisleList = new MyList<>();

    /* constructor for above fields */
    public Floor(int fNum, String secLvl, Double fTemp) {
        this.floorNumber = fNum;
        this.securityLevel = secLvl;
        this.floorTemperature = fTemp;
    }

    public int getFloorNumber() { return floorNumber; }
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }

    public String getSecurityLevel() {

        return securityLevel.toUpperCase(); // Returns error if High, Medium or Low is not entered
    }
    public void setSecurityLevel(String securityLevel) { this.securityLevel = securityLevel; }

    public Double getFloorTemperature() { return floorTemperature; }
    public void setFloorTemperature(Double floorTemperature) { this.floorTemperature = floorTemperature; }

    @Override
    public String toString() {
        return "Floor Number: " + floorNumber +
                ", Security Level:'" + securityLevel + '\'' +
                ", Floor Temperature: " + floorTemperature;
    }
}

