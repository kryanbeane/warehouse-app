package warehouseApp;

public class Pallet {

    /* details stored about each pallet */
    String productDescription;
    int productQuantity;
    double minimumStoreTemperature;
    double maximumStoreTemperature;
    int palletPositionWidth;
    int palletPositionDepth;

    /* constructor for above fields */
    public Pallet(String proDesc, int proQuantity, double minStoreTemp, double maxStoreTemp, int palPosW, int palPosD) {
        this.productDescription = proDesc;
        this.productQuantity = proQuantity;
        this.minimumStoreTemperature = minStoreTemp;
        this.maximumStoreTemperature = maxStoreTemp;
        this.palletPositionWidth = palPosW;
        this.palletPositionDepth = palPosD;
    }

    @Override
    public String toString() {
        return "Product Description: '" + productDescription + '\'' +
                ", Product Quantity: " + productQuantity +
                ", Minimum Storage Temperature: " + minimumStoreTemperature +
                ", Maximum Storage Temperature: " + maximumStoreTemperature +
                ", Pallet Position Width: " + palletPositionWidth +
                ", Pallet Position Depth: " + palletPositionDepth;
    }
}


