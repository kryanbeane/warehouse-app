package warehouseApp;

public class Shelf {

    // Details stored about each shelf
    private String aisleIdentifier;
    private int shelfNumber;
    // List of Pallets
    MyList<Pallet> palletList = new MyList<>();

    /**
     * Gets aisle ID using user specified data
     * @return - User specified aisle ID
     *           Aisle ID must be contained in Aisle list
     */
    public String getAisleIdentifier() {
        return aisleIdentifier;
    }

     /**
     * Sets aisle ID using user specified data
     * @param aisleIdentifier - User specified aisle ID
     */
    public void setAisleIdentifier(String aisleIdentifier) {
        this.aisleIdentifier = aisleIdentifier;
    }

    /**
     * Gets shelf Number using user specified data
     * @return - User specified shelf number
     */
    public int getShelfNumber() {
        return shelfNumber;
    }

    /**
     * Sets shelf number using user specified data
     * @param shelfNumber - User specified shelf number
     */
    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    /**
     * Constructor that creates the Shelf
     * @param aID - User specified Aisle ID
     * @param sNum - User specified Shelf Number
     */
    public Shelf(String aID, int sNum) {
        this.setAisleIdentifier(aID);
        this.setShelfNumber(sNum);

    }

    /**
     * Presents shelf in User Friendly View
     * @return - Shelf in string data
     */
    @Override
    public String toString() {
        return  "\n Aisle ID = " + aisleIdentifier +
                ",\n Shelf Number = " + shelfNumber;
    }
}
