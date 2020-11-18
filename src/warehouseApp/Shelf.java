package warehouseApp;

public class Shelf {

    // Details stored about each shelf
    private String aisleIdentifier;
    private int shelfNumber;
    private MyList<Shelf> aisleList;
    // List of Pallets
    static MyList<Pallet> palletList = new MyList<>();

    /**
     *
     * @return
     */
    public String getAisleIdentifier() {
        return aisleIdentifier;
    }

    /**
     *
     * @param aisleIdentifier
     */
    public void setAisleIdentifier(String aisleIdentifier) {
        this.aisleIdentifier = aisleIdentifier;
    }

    /**
     *
     * @return
     */
    public int getShelfNumber() {
        return shelfNumber;
    }

    /**
     *
     * @param shelfNumber
     */
    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    /**
     *
     * @return
     */
    public MyList<Shelf> getAisleList() {
        return aisleList;
    }

    /**
     *
     * @param aisleList
     */
    public void setAisleList(MyList<Shelf> aisleList) {
        this.aisleList = aisleList;
    }

    /**
     *
     * @param aisleIdentifier
     * @param shelfNumber
     * @param aisleList
     */
    public Shelf(String aisleIdentifier, int shelfNumber, MyList<Shelf> aisleList) {
        this.setAisleIdentifier(aisleIdentifier);
        this.setShelfNumber(shelfNumber);
        this.setAisleList(aisleList);
    }


}
