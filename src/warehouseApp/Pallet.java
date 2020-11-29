package warehouseApp;

public class Pallet {

    // Details stored about each pallet
    private String productDescription;
    private int productQuantity;
    private double minimumStoreTemperature;
    private double maximumStoreTemperature;
    private int palletPositionWidth;
    private int palletPositionDepth;

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
     * @param proDesc - User specified product description
     * @param proQuantity - User specified product quantity
     * @param minStoreTemp - User specified minimum storage temperature
     * @param maxStoreTemp - User specified maximum storage temperature
     * @param palPosW - User specified pallet position width
     * @param palPosD - User specified pallet position depth
     */
    public Pallet(String proDesc, int proQuantity, double minStoreTemp, double maxStoreTemp, int palPosW, int palPosD) {
        this.setProductDescription(proDesc);
        this.setProductQuantity(proQuantity);
        this.setMinimumStoreTemperature(minStoreTemp);
        this.setMaximumStoreTemperature(maxStoreTemp);
        this.setPalletPositionDepth(palPosD);
        this.setPalletPositionDepth(palPosD);
    }

    /**
     * Presents pallet in User Friendly View
     * @return - Pallet in string data
     */
    @Override
    public String toString() {
        return  "\n Product Description = " + productDescription +
                ",\n Product Description = " + productQuantity +
                ",\n Minimum Storage Temperature = " + minimumStoreTemperature +
                ",\n Maximum Storage Temperature = " + maximumStoreTemperature +
                ",\n Pallet Position Width = " + palletPositionWidth +
                ",\n Pallet Position Depth = " + palletPositionDepth;

    }
}


