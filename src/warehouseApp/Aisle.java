package warehouseApp;

public class Aisle {

    // Details stored about each aisle
    private int floorNumber;
    private String aisleIdentifier;
    private int aisleWidth;
    private int aisleDepth;
    private MyList<Aisle> floorList;
    // List of Shelves
    static MyList<Shelf> shelfList = new MyList<>();

    /**
     * Gets aisle's floor number
     * @return - User specified floor number
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Sets floor number using user specified data
     * @param floorNumber - User specified floor number
     *                      Floor number must be contained in Floor List
     */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
     * Gets Aisle ID
     * @return - User specified Aisle ID
     */
    public String getAisleIdentifier() {
        return aisleIdentifier;
    }

    /**
     * Sets aisle ID using user specified data
     * @param aisleIdentifier - User specified aisle identifier
     */
    public void setAisleIdentifier(String aisleIdentifier) {
        this.aisleIdentifier = aisleIdentifier;
    }

    /**
     * Gets aisle width
     * @return - User specified aisle width
     */
    public int getAisleWidth() {
        return aisleWidth;
    }

    /**
     * Sets aisle width using user specified data
     * @param aisleWidth - User specified aisle width
     */
    public void setAisleWidth(int aisleWidth) {
        this.aisleWidth = aisleWidth;
    }

    /**
     * Gets aisle depth
     * @return - User specified aisle depth
     */
    public int getAisleDepth() {
        return aisleDepth;
    }

    /**
     * Sets aisle depth using user specified data
     * @param aisleDepth - User specified aisle depth
     */
    public void setAisleDepth(int aisleDepth) {
        this.aisleDepth = aisleDepth;
    }

    /**
     * Gets FloorList associated with aisle
     * @return - FloorList associated with this aisle
     */
    public MyList<Aisle> getFloorList() {
        return floorList;
    }

    /**
     * Sets floor list for this specified aisle
     * @param floorList - Selected floor list
     */
    public void setFloorList(MyList<Aisle> floorList) {
        this.floorList = floorList;
    }

    /**
     * Constructor that creates the Aisle
     * @param floorNumber - User specified Floor Number
     * @param aisleIdentifier - User specified Aisle ID
     * @param aisleWidth - User specified Aisle Width
     * @param aisleDepth - User specified Aisle Depth
     * @param floorList - Floor list associated with this room
     */
    public Aisle(int floorNumber, String aisleIdentifier, int aisleWidth, int aisleDepth, MyList<Aisle> floorList) {
        this.setFloorNumber(floorNumber);
        this.setAisleIdentifier(aisleIdentifier);
        this.setAisleWidth(aisleWidth);
        this.setAisleDepth(aisleDepth);
        this.setFloorList(floorList);
    }

    /**
     * Presents aisle in User Friendly View
     * @return - Aisle in string data
     */
    @Override
    public String toString() {
        return  "\n Floor Number = " + floorNumber +
                ",\n Aisle ID = " + aisleIdentifier +
                ",\n Aisle Width = " + aisleWidth +
                ",\n Aisle Depth = " + aisleDepth;
    }
}

