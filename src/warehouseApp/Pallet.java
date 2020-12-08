package warehouseApp;

public class Pallet {

    // Details stored about each pallet
    private String palletID;
    private String productDescription;
    private int productQuantity;
    private double minimumStoreTemperature;
    private double maximumStoreTemperature;
    private int palletPositionWidth;
    private int palletPositionDepth;

    /**
     *
     * @return -
     */
    public String getPalletID() {
        return palletID;
    }

    /**
     *
     * @param palletID -
     */
    public void setPalletID(String palletID) {
        this.palletID = palletID;
    }

    /**
     * Gets product description using user specified data
     * @return - User specified product description
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets product description using user specified data
     * @param productDescription - User specified product description
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Gets product quantity using user specified data
     * @return - User specified product quantity
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * Sets product quantity using user specified data
     * @param productQuantity - User specified product quantity
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * Gets minimum store temperature using user specified data
     * @return - User specified minimum store temperature
     */
    public double getMinimumStoreTemperature() {
        return minimumStoreTemperature;
    }

    /**
     * Sets minimum storage temperature using user specified data
     * @param minimumStoreTemperature - User specified minimum storage temperature
     */
    public void setMinimumStoreTemperature(double minimumStoreTemperature) {
        this.minimumStoreTemperature = minimumStoreTemperature;
    }

    /**
     * Gets maximum storage temperature using user specified data
     * @return - User specified maximum storage temperature
     */
    public double getMaximumStoreTemperature() {
        return maximumStoreTemperature;
    }

    /**
     * Sets maximum storage temperature using user specified data
     * @param maximumStoreTemperature - User specified maximum storage temperature
     */
    public void setMaximumStoreTemperature(double maximumStoreTemperature) {
        this.maximumStoreTemperature = maximumStoreTemperature;
    }

    /**
     * Gets pallet position width using user specified data
     * @return - User specified pallet position width
     */
    public int getPalletPositionWidth() {
        return palletPositionWidth;
    }

    /**
     * Sets pallet position width using user specified data
     * @param palletPositionWidth - User specified pallet position width
     */
    public void setPalletPositionWidth(int palletPositionWidth) {
        this.palletPositionWidth = palletPositionWidth;
    }

    /**
     * Gets pallet position depth using user specified data
     * @return - User specified pallet position depth
     */
    public int getPalletPositionDepth() {
        return palletPositionDepth;
    }

    /**
     * Sets pallet position depth using user specified data
     * @param palletPositionDepth - User specified pallet position depth
     */
    public void setPalletPositionDepth(int palletPositionDepth) {
        this.palletPositionDepth = palletPositionDepth;
    }

    /**
     * Constructor that creates the Pallet
     * @param palletID -
     * @param productDescription - User specified product description
     * @param productQuantity - User specified product quantity
     * @param minimumStoreTemperature - User specified minimum storage temperature
     * @param maximumStoreTemperature - User specified maximum storage temperature
     * @param palletPositionWidth - User specified pallet position width
     * @param palletPositionDepth - User specified pallet position depth
     */
    public Pallet(String palletID, String productDescription, int productQuantity, double minimumStoreTemperature, double maximumStoreTemperature, int palletPositionWidth, int palletPositionDepth) {
        this.palletID = palletID;
        this.productDescription = productDescription;
        this.productQuantity = productQuantity;
        this.minimumStoreTemperature = minimumStoreTemperature;
        this.maximumStoreTemperature = maximumStoreTemperature;
        this.palletPositionWidth = palletPositionWidth;
        this.palletPositionDepth = palletPositionDepth;
    }

    /**
     * Presents pallet in User Friendly View
     * @return - Pallet in string data
     */
    @Override
    public String toString() {
        return  "Product Description: " + productDescription +
                ",\n Product Quantity: " + productQuantity +
                ",\n Minimum Storage Temperature: " + minimumStoreTemperature +
                ",\n Maximum Storage Temperature: " + maximumStoreTemperature +
                ",\n Pallet Position Width: " + palletPositionWidth +
                ",\n Pallet Position Depth: " + palletPositionDepth;

    }
}


