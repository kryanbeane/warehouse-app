package warehouseApp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Controller {

    // Area to display all lists and nodes.
    @FXML TextArea textDisplayArea;
    // All floor-related text fields.
    @FXML TextField textFNum, textSecLvl, textFTemp, textGetFloor, textCurrentFloor;
    // All aisle-related text fields.
    @FXML TextField textAisleW, textAisleD, textGetAisle, textCurrentAisle;
    // All shelf-related text fields.
    @FXML TextField textCurrentShelf, textGetShelf;
    // All pallet-related text fields.
    @FXML TextField textProDesc, textProQuantity, textMinStoreTemp, textMaxStoreTemp, textPalPosW, textPalPosD, textPalletID;


    /////////////////////////////////////////////////////////////////
    ///////////////////////   Generate ID's   ///////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Generates an ID to assign to a newly created shelf using the aisle ID, a dash: '-' and a random letter (eg. 4-G).
     * @return - Randomly generated shelf ID.
     */
    public String genShelfID() {

        Random random = new Random();
        Aisle aisleFound = getAisle();
        Node<Shelf> temp = aisleFound.shelfList.head;
        String integerPart = aisleFound.getAisleIdentifier();
        int stringIndex = random.nextInt(26);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char charPart = alpha.charAt(stringIndex);
        char x = '-';
        String newID=integerPart+x+charPart;


        while(temp!=null) {
            if (!temp.getContents().getShelfNumber().equals(newID)) {
                break;
            }
            else if(temp.getContents().getShelfNumber().equals(newID)) {
                charPart = alpha.charAt(stringIndex);
                newID = integerPart + x + charPart;
            }
            temp=temp.next;
        }
        return newID;

    }

    /**
     * Generates an ID to assign to a newly created aisle using the floor number and a random letter.
     * @return - Randomly generated aisle ID.
     */
    public String genAisleID() {
        Floor floorFound = getFloor();
        Node<Aisle> temp = floorFound.aisleList.head;
        int integerPart = floorFound.getFloorNumber();
        int stringIndex;
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


        if(temp!=null) {
            // Return the size of the list + 1, so the next available floor number.
            stringIndex = floorFound.aisleList.length();
        } else {
            // Otherwise if the list is empty, give the new floor the first number.
            stringIndex = 0;
        }
        char charPart = alpha.charAt(stringIndex);
        return integerPart+String.valueOf(charPart);
    }

    /**
     * Generates an ID to assign to a newly created pallet using the shelf ID and a random number (eg. 4-G9)
     * @return - Randomly generated pallet ID.
     */
    public String genPalletID() {
        Random random = new Random();
        Shelf shelfFound = getShelf();
        String firstPart = shelfFound.getShelfNumber();
        int intPart=random.nextInt(99);
        return firstPart + intPart;
    }

    /**
     * Generates an unused floor number to assign to a newly created floor.
     * @return - Next un-used floor number.
     */
    public int genFloorNum() {
        // If the floor list isn't empty:
        if(Main.floorList.head!=null) {
            // Return the size of the list + 1, so the next available floor number.
            return Main.floorList.length()+1;
        } else {
            // Otherwise if the list is empty, give the new floor the first number.
            return 1;
        }
    }

    /////////////////////////////////////////////////////////////////
    ///////////////////////   Floor Methods   ///////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Add Floor to floorList in Main
     */
    public void addFloor()  {
        String secLevel = textSecLvl.getText();
        Double fTemp = Double.parseDouble(textFTemp.getText());

        if(secLevel.equalsIgnoreCase("high") || secLevel.equalsIgnoreCase("medium") || secLevel.equalsIgnoreCase("low")) {
            // Adds new floor to the list using user entered values in text fields
            Main.floorList.addElement(new Floor(genFloorNum(), secLevel, fTemp));
            // Displays the updated floor list in the text area
            textDisplayArea.setText(Main.floorList.printList());

            // Clears text fields in GUI to make it easier to add multiple floors
            textSecLvl.clear();
            textFTemp.clear();
        } else {
            textDisplayArea.appendText("Security Level must be either High, Medium or Low. Try again!");
        }
    }

    /**
     * Gets floor node to add aisles to.
     * @return - Selected floor.
     */
    public Floor getFloor() {
        try {
            // User specified floor number to be retrieved
            int floorNumber = Integer.parseInt(textGetFloor.getText());
            // Sets value tempFloor to head of floor list to loop through list and find sought floor
            Node<Floor> tempFloor = Main.floorList.head;
            // Loops through floor list
            while (tempFloor != null) {
                // If user specified floor number = number of currently accessed floor, set that as selected floor
                if (tempFloor.getContents().getFloorNumber() == floorNumber) {
                    textDisplayArea.appendText("Floor " + floorNumber + " successfully selected." + "\n");
                    textCurrentFloor.setText(tempFloor.getContents().toString2());
                    return tempFloor.getContents();
                }
                // Go to next node
                tempFloor = tempFloor.next;
            }
            // If node is null, display error
            textDisplayArea.appendText("Floor not found! Please try again :)" + "\n");
            return null;
        }
        // Catch if no value is entered into the box, displays error to enter a floor
        catch(Exception e) {
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////
    ///////////////////////   Aisle Methods   ///////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Add aisle to aisleList in selected Floor.
     */
    public void addAisle() {
        int aisleW = Integer.parseInt(textAisleW.getText());
        int aisleD = Integer.parseInt(textAisleD.getText());
        Floor floorFound = getFloor();

        if (floorFound != null) {
            if (floorFound.aisleList.length() < 11) {
                if (aisleW < 21 && aisleW > 0) {
                    if (aisleD < 6 && aisleD > 0) {
                        floorFound.aisleList.addElement(new Aisle(genAisleID(), aisleW, aisleD));
                        textDisplayArea.setText("Aisles in Floor " + floorFound.getFloorNumber() + ": " + "\n" + "\n" + floorFound.aisleList.printList() + "\n");
                        textAisleW.clear();
                        textAisleD.clear();
                    } else {
                        textDisplayArea.appendText("Aisle depth must be between 1 and 20. Try again." + "\n");
                    }
                } else {
                    textDisplayArea.appendText("Aisle width must be between 1 and 5. Try again." + "\n");
                }
            } else {
                textDisplayArea.appendText("Maximum number of aisles reached!" + "\n");
            }
        } else {
            textDisplayArea.appendText("Floor not found. Aisle not added."+"\n");
        }
    }

    /**
     * Gets aisle node to add shelves to.
     * @return - Selected aisle.
     */
    public Aisle getAisle() {
        try {
            // User specified aisle ID to be retrieved
            String aID = textGetAisle.getText();
            // Sets value tempAisle to head of aisle list in selected floor to loop through list and find sought aisle
            Node<Aisle> tempAisle = getFloor().aisleList.head;
            // Loops through aisle list
            while (tempAisle != null) {
                // If user specified aisle ID = number of currently accessed aisle, set that as selected aisle
                if (tempAisle.getContents().getAisleIdentifier().equals(aID)) {
                    textDisplayArea.appendText("Aisle " + aID + " successfully selected." + "\n");
                    textCurrentAisle.setText(tempAisle.getContents().toString2());
                    return tempAisle.getContents();
                }
                // Go to next node
                tempAisle = tempAisle.next;
            }
            // If node is null, display error
            textDisplayArea.appendText("Aisle not found! Please try again :)" + "\n");
            return null;
        }
        catch (Exception e) {
            textDisplayArea.appendText("Please enter a aisle to select." +"\n");
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////
    ///////////////////////   Shelf Methods   ///////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Add Shelf to shelfList in selected Aisle.
     */
    public void addShelf() {
        Aisle aisleFound = getAisle();
        if (aisleFound != null) {
            if (aisleFound.shelfList.length() < 5) {
                aisleFound.shelfList.addElement(new Shelf(genShelfID()));
                textDisplayArea.setText(getAisle().shelfList.printList());
            } else {
                textDisplayArea.appendText("Maximum number of shelves reached!" + "\n");
            }
        } else {
            textDisplayArea.appendText("Please choose a valid aisle to add to!" + "\n");
        }
    }

    /**
     * Gets shelf node to add pallets to.
     * @return - Selected shelf.
     */
    public Shelf getShelf() {
        String sNum = textGetShelf.getText();
        Node<Shelf> tempShelf = getAisle().shelfList.head;
        while(tempShelf!=null) {
            if(tempShelf.getContents().getShelfNumber().equals(sNum)) {
                textCurrentShelf.setText(tempShelf.getContents().toString());
                return tempShelf.getContents();
            }
            tempShelf=tempShelf.next;
        }
        return null;
    }


    /////////////////////////////////////////////////////////////////
    ///////////////////////  Pallet Methods  ////////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Add pallet to palletList in selected Shelf.
     */
    public void addPallet() {
        String proDesc = textProDesc.getText();
        int proQuantity = Integer.parseInt(textProQuantity.getText());
        double minStoreTemp = Double.parseDouble(textMinStoreTemp.getText());
        double maxStoreTemp = Double.parseDouble(textMaxStoreTemp.getText());
        int palPosW = Integer.parseInt(textPalPosW.getText());
        int palPosD = Integer.parseInt(textPalPosD.getText());
        Shelf shelfFound = getShelf();

        if (shelfFound!= null) {
            if(proQuantity>0 && proQuantity<101) {
                shelfFound.palletList.addElement(new Pallet(genPalletID(), proDesc, proQuantity, minStoreTemp, maxStoreTemp, palPosW, palPosD));
                textDisplayArea.setText(shelfFound.palletList.printList());
            } else {
                textDisplayArea.appendText("Invalid Product Quantity. Enter a quality between 0 and 100!");
            }
        } else {
            textDisplayArea.appendText("Shelf not found. Pallet not added."+"\n");
        }
        // Clears text fields to make adding another pallet easy.
        textProDesc.clear();
        textProQuantity.clear();
        textMinStoreTemp.clear();
        textMaxStoreTemp.clear();
        textPalPosW.clear();
        textPalPosD.clear();
    }

    /**
     * Deletes pallet from selected shelf.
     */
    public void deletePallet() {
        int palletID = Integer.parseInt(textPalletID.getText());
        getShelf().palletList.removeNode(palletID);
    }


    /////////////////////////////////////////////////////////////////
    ///////////////////////   View  Methods   ///////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     *
     */
    public void viewAll() {

    }

    /**
     *
     */
    public void viewFloors() {
        textDisplayArea.setText(Main.floorList.printList());
    }

    /**
     *
     */
    public void viewAisles() {

        Floor tempFloor = getFloor();
        if(tempFloor.aisleList.head!=null) {
            textDisplayArea.setText("Aisles in Floor"+tempFloor.getFloorNumber() + ": " + "\n" + "\n" + tempFloor.aisleList.printList()+"\n");
        } else {
            textDisplayArea.setText("Try adding some aisles first."+"\n");
        }
    }

    /**
     *
     */
    public void viewShelves() {
//        if() {
//
//        } else {
//
//        }
    }

    /**
     *
     */
    public void viewPallets() {
//        if() {
//
//        } else {
//
//        }
    }


    /////////////////////////////////////////////////////////////////
    ////////////////////   Save Load and Reset   ////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Saves objects as text in xml document
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("WarehouseApp.xml"));
        out.writeObject(Main.floorList);
        out.close();
        textDisplayArea.appendText("File has been saved." + "\n");
    }

    /**
     * Loads objects from text in xml document
     * @throws Exception - Error printed if floorList is empty
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("WarehouseApp.xml"));
            Main.floorList = (MyList<Floor>) is.readObject();
            is.close();
            textDisplayArea.setText(Main.floorList.printList());
    }

    /**
     * Reset clears floor list, thus clearing all other lists.
     */
    public void reset() {
        Main.floorList.emptyList();
        textDisplayArea.setText("System has been reset."+"\n");
    }


    /////////////////////////////////////////////////////////////////
    ///////////////////////////   Exit   ////////////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Exit system
     */
    public void quit(){
        System.exit(0);
    }

}
