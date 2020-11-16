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

    @Override
    public String toString() {
        return "Floor Number: " + floorNumber +
                ", Security Level:'" + securityLevel + '\'' +
                ", Floor Temperature: " + floorTemperature;
    }
}

