package warehouseApp;

public class Shelf {

    // Details stored about each shelf
    private String shelfNumber;
    // List of Pallets
    MyList<Pallet> palletList = new MyList<>();

    /**
     * Gets shelf Number using user specified data
     *
     * @return - User specified shelf number
     */
    public String getShelfNumber() {
        return shelfNumber;
    }

    /**
     * Sets shelf number using user specified data
     *
     * @param shelfNumber - User specified shelf number
     */
    public void setShelfNumber(String shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    /**
     * Constructor that creates the Shelf
     *
     * @param shelfNumber - User specified Shelf Number
     */
    public Shelf(String shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    /**
     * Presents shelf in User Friendly View
     * @return - Shelf in string data
     */
    @Override
    public String toString() {
        return "Shelf " + shelfNumber +"\n";
    }
}
