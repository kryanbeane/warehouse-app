package warehouseApp;

public class Floor {

    // Details stored about each floor
    private int floorNumber;
    private String securityLevel;
    private Double floorTemperature;
    // List of Aisles
    static MyList<Aisle> aisleList = new MyList<>();

    /**
     * Gets Floor Number
     * @return - User specified floor number
     */
    public int getFloorNumber() { return floorNumber; }

    /**
     * Sets the floor number using user specified data
     * @param floorNumber - User specified floor number
     */
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }

    /**
     * Gets Security Level
     * @return - User specified security level
     */
    public String getSecurityLevel() {
        return securityLevel;
    }

    /**
     * Sets the security level value using user specified data or error
     * @param securityLevel - User specified security level
     * @return - Security level or error if params aren't met
     */
    public String setSecurityLevel(String securityLevel) {
        if ((securityLevel.equalsIgnoreCase("high")) || (securityLevel.equalsIgnoreCase("medium")) || (securityLevel.equalsIgnoreCase("low"))) {
            return this.securityLevel.toUpperCase();
        }
        System.out.println("Error: Incorrect Security Level. Please enter HIGH, MEDIUM or LOW"); // Outputs error if high medium or low is not entered
        return null;
    }

    /**
     * Gets Floor Temperature
     * @return - User specified floor temperature
     */
    public Double getFloorTemperature() { return floorTemperature; }

    /**
     * Sets the floor temperature using user specified data
     * @param floorTemperature - User specified floor temperature
     */
    public void setFloorTemperature(Double floorTemperature) { this.floorTemperature = floorTemperature; }

    /**
     * Constructor that creates the Floor
     * @param floorNumber - User specified floor number
     * @param securityLevel - User specified security level
     * @param floorTemperature - User specified floor temperature
     */
    public Floor(int floorNumber, String securityLevel, Double floorTemperature) {
        this.setFloorNumber(floorNumber);
        this.setSecurityLevel(securityLevel);
        this.setFloorTemperature(floorTemperature);
    }

    /**
     * Presents floor in User Friendly View
     * @return - Floor in String data
     */
    @Override
    public String toString() {
        return  "\n Floor Number = " + floorNumber +
                ",\n Security Level = " + securityLevel +
                ",\n Floor Temperature = " + floorTemperature;
    }
}

