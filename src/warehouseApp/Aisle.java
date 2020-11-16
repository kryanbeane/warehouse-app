package warehouseApp;

public class Aisle {

    /* details stored about each aisle */
    String aisleIdentifier;
    int aisleWidth;
    int aisleDepth;
    static MyList<Shelf> shelfList = new MyList<>();

    /* constructor for above fields */
    public Aisle(String aID, int aisleW, int aisleD) {
        this.aisleIdentifier = aID;
        this.aisleWidth = aisleW;
        this.aisleDepth = aisleD;
    }

    @Override
    public String toString() {
        return "Aisle ID: " + aisleIdentifier + '\'' +
                ", Aisle Width: " + aisleWidth + '\'' +
                ", Aisle Length: " + aisleDepth;
    }
}

