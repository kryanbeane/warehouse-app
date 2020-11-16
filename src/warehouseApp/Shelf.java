package warehouseApp;

public class Shelf {

    /* details stored about each shelf */
    int shelfNumber;
    static MyList<Pallet> palletList = new MyList<>();

    /* constructor for above field */
    public Shelf(int sNum) {this.shelfNumber = sNum;}



    @Override
    public String toString() {
        return "Shelf Number: " + shelfNumber;

    }
}
