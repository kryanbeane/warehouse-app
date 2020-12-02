package warehouseApp;

public class Aisle {

    // Details stored about each aisle

    private String aisleIdentifier;
    private int aisleWidth;
    private int aisleDepth;
    // List of Shelves
    MyList<Shelf> shelfList = new MyList<>();

    /**
     * Gets Aisle ID using user specified data
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
     * Gets aisle width using user specified data
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
     * Gets aisle depth using user specified data
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
     * Constructor that creates the Aisle
     * @param aID - User specified Aisle ID
     * @param aisleW - User specified Aisle Width
     * @param aisleD - User specified Aisle Depth
     */
    public Aisle(String aID, int aisleW, int aisleD) {
        this.setAisleIdentifier(aID);
        this.setAisleWidth(aisleW);
        this.setAisleDepth(aisleD);
    }

    /**
     * Presents aisle in User Friendly View
     * @return - Aisle in string data
     */
    @Override
    public String toString() {
        return  "Aisle " + aisleIdentifier + "\n" +
                "Aisle Width = " + aisleWidth + "\n" +
                "Aisle Depth = " + aisleDepth + "\n";
    }
}

