package warehouseApp;

public class Shelf {

    // Details stored about each shelf
    private int shelfNumber;
    // List of Pallets
    MyList<Pallet> palletList = new MyList<>();

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
     * @param sNum - User specified Shelf Number
     */
    public Shelf(int sNum) {
        this.setShelfNumber(sNum);
    }

    /**
     * Presents shelf in User Friendly View
     * @return - Shelf in string data
     */
    @Override
    public String toString() {
        return "\n Shelf Number = " + shelfNumber;
    }
}
