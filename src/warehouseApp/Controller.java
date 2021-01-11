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
import static java.lang.Math.abs;


public class Controller {

    // Area to display all lists and nodes.
    @FXML
    TextArea textDisplayArea;
    // All floor-related text fields.
    @FXML
    TextField textSecLvl, textFTemp, textGetFloor, textCurrentFloor;
    // All aisle-related text fields.
    @FXML
    TextField textAisleW, textAisleD, textGetAisle, textCurrentAisle;
    // All shelf-related text fields.
    @FXML
    TextField textCurrentShelf, textGetShelf;
    // All pallet-related text fields.
    @FXML
    TextField textProDesc, textProQuantity, textMinStoreTemp, textMaxStoreTemp, textPalPosW, textPalPosD, textPalletID, textPalletSearch;
    // All smart add-related text fields.
    @FXML
    TextField textSmartDescription, textSmartQuantity, textSmartMinTemp, textSmartMaxTemp, textSmartSecurity;

    /////////////////////////////////////////////////////////////////
    ////////////////////////   Smart Add   //////////////////////////
    /////////////////////////////////////////////////////////////////

    public void smartAdd() {

        String smartDescription = textSmartDescription.getText();
        int smartQuantity = Integer.parseInt(textSmartQuantity.getText());
        double smartTempMin = Double.parseDouble(textSmartMinTemp.getText());
        double smartTempMax = Double.parseDouble(textSmartMaxTemp.getText());
        String smartSecurity;
        int palletWidth;
        int palletDepth;

        if (textSmartSecurity.getText().equalsIgnoreCase("high") || textSmartSecurity.getText().equalsIgnoreCase("medium") || textSmartSecurity.getText().equalsIgnoreCase("low")) {
            smartSecurity = textSmartSecurity.getText();
        } else {
            textDisplayArea.setText("Please Enter High, Medium or Low" + "\n");
            smartSecurity = null;
        }

        if (Main.floorList.isEmpty()) {
            textDisplayArea.setText("There are no Floors in the system." + "\n");

        } else {

            for (Floor floor : Main.floorList) {
                if (floor.getSecurityLevel().equalsIgnoreCase(smartSecurity)) {
                    if (floor.getFloorTemperature() <= smartTempMax && floor.getFloorTemperature() >= smartTempMin) {
                        if (!floor.aisleList.isEmpty()) {

                            for (Aisle aisle : floor.aisleList) {
                                if (!aisle.shelfList.isEmpty()) {

                                    for (Shelf shelf : aisle.shelfList) {
                                        if (shelf.palletList.length() < aisle.getAisleWidth() * aisle.getAisleDepth()) {
                                            if (shelf.palletList.isEmpty()) {
                                                palletWidth = 1;
                                                palletDepth = 1;
                                            } else {
                                                palletWidth = Math.abs(shelf.palletList.tail.getContents().getPalletPositionWidth() + 1);
                                                palletDepth = (shelf.palletList.tail.getContents().getPalletPositionDepth());
                                            }

                                            Pallet newPallet = new Pallet(genSmartPalletID(shelf), smartDescription, smartQuantity, smartTempMin, smartTempMax, palletWidth, palletDepth);
                                            shelf.palletList.addElement(newPallet);
                                            textDisplayArea.setText("Pallet Added to " + floor.toString2() + ", " + aisle.toString2() + ", " + " and to " + shelf.toString() + "\n" + "\n" + newPallet.toString());
                                            textSmartSecurity.clear();
                                            textSmartDescription.clear();
                                            textSmartMaxTemp.clear();
                                            textSmartMinTemp.clear();
                                            textSmartQuantity.clear();


                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /////////////////////////////////////////////////////////////////
    ///////////////////////   Generate ID's   ///////////////////////
    /////////////////////////////////////////////////////////////////

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

    /**
     * Generates an ID to assign to a newly created aisle using the floor number and a random letter.
     * @return - Randomly generated aisle ID.
     */
    public String genAisleID() {
        // Sets floorFound to the currently selected floor.
        Floor floorFound = getFloor();
        // Sets temp to the head of the current floor's aisle list.
        Node<Aisle> temp = floorFound.aisleList.head;
        // Sets the int part of the ID to the current floor's number.
        int integerPart = floorFound.getFloorNumber();
        // Initialize string portion of ID.
        int stringIndex;
        // Sets value of alpha to characters which can be used for ID.
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // If the current aisle isn't null:
        if(temp!=null) {
            // Set the string part to the size of the aisle list.
            stringIndex = floorFound.aisleList.length();
        } else {
            // Otherwise if the list is empty set the string part to the first letter in stringIndex.
            stringIndex = 0;
        }
        // Sets the char part to the character at the position defined by stringIndex.
        char charPart = alpha.charAt(stringIndex);
        // Returns new unique ID.
        return integerPart+String.valueOf(charPart);
    }

    /**
     * Generates an ID to assign to a newly created shelf using the aisle ID, a dash: '-' and a random letter (eg. 4A-G).
     * @return - Randomly generated shelf ID.
     */
    public String genShelfID() {
        // Sets aisleFound to the currently selected aisle.
        Aisle aisleFound = getAisle();
        // Sets temp to the head of the current aisle's shelf list.
        Node<Shelf> temp = aisleFound.shelfList.head;
        // Sets the int part of the ID to the current floor's number.
        String integerPart = aisleFound.getAisleIdentifier();
        // Initialize string portion of ID.
        int stringIndex;
        // Sets value of alpha to characters which can be used for ID.
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String x="-";

        // If the current aisle isn't null:
        if(temp!=null) {
            // Set the string part to the size of the aisle list.
            stringIndex = aisleFound.shelfList.length();
        } else {
            // Otherwise if the list is empty set the string part to the first letter in stringIndex.
            stringIndex = 0;
        }
        // Sets the char part to the character at the position defined by stringIndex.
        char charPart = alpha.charAt(stringIndex);
        // Returns new unique ID.
        return integerPart+x+charPart;
    }

    /**
     * Generates an ID to assign to a newly created pallet with smart add using the shelf ID and a random number (eg. 4-G9)
     * @return - Randomly generated pallet ID.
     */
    public String genSmartPalletID(Shelf shelfFound) {
        // Sets temp to the head of the current aisle's shelf list.
        Node<Pallet> temp = shelfFound.palletList.head;
        // Sets the first part of the ID to the current shelf's ID.
        String firstPart = shelfFound.getShelfNumber();
        int stringIndex;

        // If the current aisle isn't null:
        if(temp!=null) {
            // Set the string part to the size of the aisle list.
            stringIndex = shelfFound.palletList.length()+1;
        } else {
            // Otherwise if the list is empty set the string part to the first letter in stringIndex.
            stringIndex = 1;
        }
        // Returns new unique ID.
        return firstPart+stringIndex;
    }

    /**
     * Generates an ID to assign to a newly created pallet using the shelf ID and a random number (eg. 4-G9)
     * @return - Randomly generated pallet ID.
     */
    public String genPalletID() {
        // Sets aisleFound to the currently selected aisle.
        Shelf shelfFound = getShelf();
        // Sets temp to the head of the current aisle's shelf list.
        Node<Pallet> temp = shelfFound.palletList.head;
        // Sets the first part of the ID to the current shelf's ID.
        String firstPart = shelfFound.getShelfNumber();
        int stringIndex;

        // If the current aisle isn't null:
        if(temp!=null) {
            // Set the string part to the size of the aisle list.
            stringIndex = shelfFound.palletList.length()+1;
        } else {
            // Otherwise if the list is empty set the string part to the first letter in stringIndex.
            stringIndex = 1;
        }
        // Returns new unique ID.
        return firstPart+stringIndex;
    }

    public void clearSelections() {
        textGetFloor.clear();
        textCurrentFloor.clear();
        textGetAisle.clear();
        textCurrentAisle.clear();
        textGetShelf.clear();
        textCurrentShelf.clear();
        textDisplayArea.setText("Selections Cleared!" + "\n" + "\n");

    }

    /////////////////////////////////////////////////////////////////
    ///////////////////////   Floor Methods   ///////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Add Floor to floorList in Main
     */
    public void addFloor()  {
        // Fields assigned to data entered into text fields by user.
        String secLevel = textSecLvl.getText();
        Double fTemp = Double.parseDouble(textFTemp.getText());

        if(secLevel.equalsIgnoreCase("high") || secLevel.equalsIgnoreCase("medium") || secLevel.equalsIgnoreCase("low")) {
            // Adds new floor to the list using user entered values in text fields.
            Main.floorList.addElement(new Floor(genFloorNum(), secLevel, fTemp));
            // Displays the updated floor list in the text area
            textDisplayArea.setText(Main.floorList.printList());

            // Clears text fields in GUI to make it easier to add multiple floors.
            textSecLvl.clear();
            textFTemp.clear();
        } else {
            // Error displayed if incorrectly formatted security level is entered.
            textDisplayArea.appendText("Security Level must be either High, Medium or Low. Try again!");
            
        }
    }

    /**
     * Gets floor node to add aisles to.
     * @return - Selected floor.
     */
    public Floor getFloor() {
        try {
            // User specified floor number to be retrieved.
            int floorNumber = Integer.parseInt(textGetFloor.getText());
            // Sets value tempFloor to head of floor list to loop through list and find sought floor.
            Node<Floor> tempFloor = Main.floorList.head;
            // Loops through floor list.
            while (tempFloor != null) {
                // If user specified floor number = number of currently accessed floor, set that as selected floor.
                if (tempFloor.getContents().getFloorNumber() == floorNumber) {
                    // Display success message if successfully selected.
                    textDisplayArea.setText("Floor " + floorNumber + " successfully selected." + "\n");
                    // Display the newly selected floor to the current floor text field.
                    textCurrentFloor.setText(tempFloor.getContents().toString2());
                    return tempFloor.getContents();
                }
                // Go to next node.
                tempFloor = tempFloor.next;
            }
            // If node is null, display error.
            textDisplayArea.appendText("Floor not found! Please try again :)" + "\n");
            return null;
        }
        // Catch if no value is entered into the box.
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
        // Fields assigned to data entered into text fields by user.
        int aisleW = Integer.parseInt(textAisleW.getText());
        int aisleD = Integer.parseInt(textAisleD.getText());
        // Instance of currently selected floor.
        Floor floorFound = getFloor();

        // If the current floor isn't null:
        if (floorFound != null) {
            // And if the aisle list's length is less than 11:
            if (floorFound.aisleList.length() < 11) {
                // And if the aisle's width is under 21 and over 0:
                if (aisleW < 21 && aisleW > 0) {
                    // And if the aisle's depth is under 6 and over 0:
                    if (aisleD < 6 && aisleD > 0) {
                        // Add the new aisle to the list.
                        floorFound.aisleList.addElement(new Aisle(genAisleID(), aisleW, aisleD));
                        // Display an addition success message.
                        textDisplayArea.setText("Aisles in Floor " + floorFound.getFloorNumber() + ": " + "\n" + "\n" + floorFound.aisleList.printList() + "\n");
                        // Clear the text fields for easy addition of more aisles.
                        textAisleW.clear();
                        textAisleD.clear();
                    } else {
                        // If depth is invalid, display error message.
                        textDisplayArea.appendText("Aisle depth must be between 1 and 20. Try again." + "\n");
                    }
                } else {
                    // If width is invalid, display error message.
                    textDisplayArea.appendText("Aisle width must be between 1 and 5. Try again." + "\n");
                }
            } else {
                // If the aisle list if full, display error message.
                textDisplayArea.appendText("Maximum number of aisles reached!" + "\n");
            }
        } else {
            // If the current floor is null, display error message.
            textDisplayArea.appendText("Floor not found. Aisle not added."+"\n");
        }
    }

    /**
     * Gets aisle node to add shelves to.
     * @return - Selected aisle.
     */
    public Aisle getAisle() {
        try {
            // User specified aisle ID to be retrieved.
            String aID = textGetAisle.getText().toUpperCase();
            // Sets value tempAisle to head of aisle list in selected floor to loop through list and find sought aisle.
            Node<Aisle> tempAisle = getFloor().aisleList.head;
            // Loops through aisle list.
            while (tempAisle != null) {
                // If user specified aisle ID = number of currently accessed aisle, set that as selected aisle.
                if (tempAisle.getContents().getAisleIdentifier().equals(aID)) {
                    // Display success message if successfully selected.
                    textDisplayArea.setText("Aisle " + aID.toUpperCase() + " successfully selected." + "\n");
                    // Display the newly selected aisle to the current aisle text field.
                    textCurrentAisle.setText(tempAisle.getContents().toString2());
                    return tempAisle.getContents();
                }
                // Go to next node.
                tempAisle = tempAisle.next;
            }
            // If node is null, display error.
            textDisplayArea.appendText("Aisle not found! Please try again :)" + "\n");
            return null;
        }
        // Catch if no value is entered into the box.
        catch(Exception e) {
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
        // Instance of currently selected aisle.
        Aisle aisleFound = getAisle();
        // If the current aisle isn't null:
        if (aisleFound != null) {
            // And if the shelf list's length is less than 5:
            if (aisleFound.shelfList.length() < 5) {
                // Add the new shelf to the list.
                aisleFound.shelfList.addElement(new Shelf(genShelfID()));
                // Display an addition success message.
                textDisplayArea.setText(getAisle().shelfList.printList());
            } else {
                // If the shelf list is full, display error message.
                textDisplayArea.setText(getAisle().shelfList.printList()+"Maximum number of shelves reached!" + "\n");
            }
        } else {
            // If the current aisle is invalid, display error message.
            textDisplayArea.setText("Please choose a valid aisle to add to!" + "\n");
        }
    }

    /**
     * Gets shelf node to add pallets to.
     * @return - Selected shelf.
     */
    public Shelf getShelf() {
        try {
            // User specified shelf number to be retrieved.
            String sNum = textGetShelf.getText().toUpperCase();
            // Sets value tempShelf to head of selected shelf list to loop through and find sought shelf.
            Node<Shelf> tempShelf = getAisle().shelfList.head;
            // Loops through shelf list.
            while (tempShelf != null) {
                // If user specified shelf number = shelfNum of current shelf, set that as selected shelf.
                if (tempShelf.getContents().getShelfNumber().equals(sNum)) {
                    // Display success message if successfully selected.
                    textDisplayArea.setText("Shelf " + sNum.toUpperCase() + " successfully selected." + "\n");
                    // Display the newly selected shelf to the current shelf text field.
                    textCurrentShelf.setText(tempShelf.getContents().toString());
                    return tempShelf.getContents();
                }
                // Go to next node.
                tempShelf = tempShelf.next;
            }
            // If node is null, display error.
            textDisplayArea.appendText("Shelf not found! Please try again :)" + "\n");
            return null;
        }
        // Catch if no value is entered into the box.
        catch (Exception e){
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////
    ///////////////////////  Pallet Methods  ////////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Add pallet to palletList in selected Shelf.
     */
    public void addPallet() {
        // Fields assigned to data entered into text fields by user.
        String proDesc = textProDesc.getText();
        int proQuantity = Integer.parseInt(textProQuantity.getText());
        double minStoreTemp = Double.parseDouble(textMinStoreTemp.getText());
        double maxStoreTemp = Double.parseDouble(textMaxStoreTemp.getText());
        int palPosW = Integer.parseInt(textPalPosW.getText());
        int palPosD = Integer.parseInt(textPalPosD.getText());
        // Temporary instances of the current aisle and shelf.
        Aisle aisleFound = getAisle();
        Shelf shelfFound = getShelf();

        // Checks if user entered depth matches with aisle depth.
        if (palPosD <= aisleFound.getAisleDepth() && palPosD > 0) {
            // Checks if user entered width matches with aisle width.
            if (palPosW <= aisleFound.getAisleWidth() && palPosW > 0) {
                // Checks if the current shelf is null, if not:
                if (shelfFound != null) {
                    // Then checks if the product quantity is in the correct range,
                    if (proQuantity > 0 && proQuantity < 101) {
                        // If all if statements pass, add the new pallet to the pallet list in the chosen shelf.
                        shelfFound.palletList.addElement(new Pallet(genPalletID(), proDesc, proQuantity, minStoreTemp, maxStoreTemp, palPosW, palPosD));
                        // Display the pallet to the text area.
                        textDisplayArea.setText(shelfFound.palletList.printList());
                        // Clears text fields to make adding another pallet easy.
                        textProDesc.clear();
                        textProQuantity.clear();
                        textMinStoreTemp.clear();
                        textMaxStoreTemp.clear();
                        textPalPosW.clear();
                        textPalPosD.clear();
                    } else {
                        // Error if quantity over 100 is entered.
                        textDisplayArea.appendText("Invalid Product Quantity. Enter a quality between 0 and 100!"+"\n");
                    }
                } else {
                    // Error if invalid shelf.
                    textDisplayArea.appendText("Shelf not found. Pallet not added." + "\n");
                }
            } else {
                // Error if entered width is higher than current aisle's width.
                textDisplayArea.appendText("This aisle's max width is " + aisleFound.getAisleWidth() +"\n");
            }
        } else {
            // Error if entered depth is higher than current aisle's depth.
            textDisplayArea.appendText("This aisle's max depth is " + aisleFound.getAisleDepth() +"\n");
        }

    }

    /**
     * Deletes pallet from selected shelf.
     */
    public void deletePallet() {
        try {
            String palletID = textPalletID.getText();
            MyList<Pallet> palList = getShelf().palletList;

            for (int i = 0; i < palList.length(); i++) {
                if (palList.accessAtIndex(i).getContents().getPalletID().equals(palletID)) {
                    palList.removeNode(i);
                    textDisplayArea.setText("Pallet Successfully Deleted!" + "\n");
                }
            }
        } catch (Exception e) {
            textDisplayArea.setText("Pallet Deletion Unsuccessful, Try Again!" + "\n");
        }
    }

    /**
     *  Searches for pallet from all pallets in all shelves in all aisles in all floors.
     */
    public void searchForPallet() {
        String searchFor = textPalletSearch.getText();

        for (Floor tempFloor : Main.floorList) {
            for (Aisle tempAisle : tempFloor.aisleList) {
                for (Shelf tempShelf : tempAisle.shelfList) {
                    for (Pallet tempPallet : tempShelf.palletList) {

                        if (tempPallet.getProductDescription().equalsIgnoreCase(searchFor)) {
                            textDisplayArea.setText("Pallet Found:" + "\n" + "\n" + tempPallet.toString() + "\n" );
                            return;
                        }
                    }
                }
            }
        }
    }


    /////////////////////////////////////////////////////////////////
    ///////////////////////   View  Methods   ///////////////////////
    /////////////////////////////////////////////////////////////////

    public void viewAll() {
        textDisplayArea.clear();
        for(Floor floor: Main.floorList) {
            textDisplayArea.appendText(floor.toString()+"\n");
            for (Aisle aisle : floor.aisleList) {
                textDisplayArea.appendText( aisle.toString()+"\n");
                for (Shelf shelf: aisle.shelfList) {
                    textDisplayArea.appendText( shelf.toString()+"\n");
                    for (Pallet pallet: shelf.palletList) {
                        textDisplayArea.appendText( pallet.toString()+"\n");
                    }
                }
            }
        }
    }

    /**
     * Prints the list of floors.
     */
    public void viewFloors() {
        // Prints the floor list.
        textDisplayArea.setText(Main.floorList.printList());
    }

    /**
     *  Prints the list of aisles in currently chosen floor.
     */
    public void viewAisles() {
        // If there is a selected floor this will run.
        try {
            // Temporary instance of selected floor.
            Floor tempFloor = getFloor();
            // If aisle list isn't empty:
            if(tempFloor.aisleList.head!=null) {
                // Display them.
                textDisplayArea.setText("Aisles in Floor " + tempFloor.getFloorNumber() + ": " + "\n" + "\n" + tempFloor.aisleList.printList()+"\n");
            } else {
                // Otherwise prompt user to add some.
                textDisplayArea.setText("Try adding some aisles to this floor first."+"\n");
            }
            // If there is no selected floor an error is displayed.
        } catch (Exception e) {
            textDisplayArea.appendText("Select a floor first!" + "\n");
        }
    }

    /**
     *  Prints the list of shelves in currently chosen aisle.
     */
    public void viewShelves() {
        // If there is a selected aisle this will run.
        try {
            // Temporary instance of selected aisle.
            Aisle tempAisle = getAisle();
            // If shelf list isn't empty:
            if(tempAisle.shelfList.head!=null) {
                // Display them.
                textDisplayArea.setText("Shelves in Aisle " + tempAisle.getAisleIdentifier() + ": " + "\n" + "\n" + tempAisle.shelfList.printList()+"\n");
            } else {
                // Otherwise prompt user to add some.
                textDisplayArea.setText("Try adding some shelves to this aisle first."+"\n");
            }
            // If there is no selected aisle an error is displayed.
        } catch (Exception e) {
            textDisplayArea.appendText("Select an aisle first!" + "\n");
        }
    }

    /**
     *  Prints the list of pallets in currently chosen shelf.
     */
    public void viewPallets() {
        // If there is a selected shelf this will run.
        try {
            // Temporary instance of selected shelf.
            Shelf tempShelf = getShelf();
            // If pallet list isn't empty:
            if(tempShelf.palletList.head!=null) {
                // Display them.
                textDisplayArea.setText("Pallets in Shelf " + tempShelf.getShelfNumber() + ": " + "\n" + "\n" + tempShelf.palletList.printList()+"\n");
            } else {
                // Otherwise prompt user to add some.
                textDisplayArea.setText("Try adding some pallets to this shelf first."+"\n");
            }
            // If there is no selected shelf an error is displayed.
        } catch(Exception e) {
            textDisplayArea.appendText("Select a shelf first!" + "\n");
        }
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
