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

    /**
     * Generates an ID to assign to a newly created aisle using the floor number and a random letter.
     * @return - Randomly generated aisle ID.
     */
    public String genAisleID() {
        Random random = new Random();
        Floor floorFound = getFloor();
        // Makes the int part a number between 1 and 10
        int integerPart = floorFound.getFloorNumber();
        int stringIndex = random.nextInt(26);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char charPart = alpha.charAt(stringIndex);
        return integerPart + String.valueOf(charPart);
    }

    /**
     * Generates an ID to assign to a newly created shelf using the aisle ID, a dash: '-' and a random letter (eg. 4-G).
     * @return - Randomly generated shelf ID.
     */
    public String genShelfID() {
        Random random = new Random();
        Aisle aisleFound = getAisle();
        String integerPart = aisleFound.getAisleIdentifier();
        int stringIndex = random.nextInt(26);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char charPart = alpha.charAt(stringIndex);
        char x = '-';
        return integerPart + x + charPart;
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
     * Add Floor to floorList in Main
     */
    public void addFloor()  {
        int floorNumber = Integer.parseInt(textFNum.getText());
        String securityLevel = textSecLvl.getText();
        double floorTemperature = Double.parseDouble(textFTemp.getText());
        Main.floorList.addElement(new Floor(floorNumber, securityLevel, floorTemperature));
        textDisplayArea.setText(Main.floorList.printList());
        textFNum.clear();
        textSecLvl.clear();
        textFTemp.clear();
    }

    /**
     * Get floor node to add aisle to.
     */
    public Floor getFloor() {
        int floorNumber = Integer.parseInt(textGetFloor.getText());
        Node<Floor> tempFloor = Main.floorList.head;
        while (tempFloor != null) {
            if (tempFloor.getContents().getFloorNumber() == floorNumber) {
                textCurrentFloor.setText(tempFloor.getContents().toString2());
                //textDisplayArea.clear();
                //textGetFloor.clear();
                return tempFloor.getContents();

            }
            tempFloor = tempFloor.next;
        }
        textDisplayArea.appendText("Node not found! Please try again :)" + "\n" );
        return  null;
    }

    /**
     * Add aisle to aisleList in Floor
     */
    public void addAisle() {
        int aisleW = Integer.parseInt(textAisleW.getText());
        int aisleD = Integer.parseInt(textAisleD.getText());
        Floor floorFound = getFloor();

        if (floorFound != null) {
            floorFound.aisleList.addElement(new Aisle(genAisleID(), aisleW, aisleD));
            textDisplayArea.setText(floorFound.aisleList.printList());
            textAisleW.clear();
            textAisleD.clear();
        } else {
            System.out.println("Floor not found. Aisle not added."+"\n");
        }
    }

    /**
     *
     */
    public Aisle getAisle() {
        String aID = textGetAisle.getText();
        Node<Aisle> tempAisle = getFloor().aisleList.head;
        while(tempAisle!=null) {
            if(tempAisle.getContents().getAisleIdentifier().equals(aID)) {
                textCurrentAisle.setText(tempAisle.getContents().toString2());
                return tempAisle.getContents();
            }
            tempAisle = tempAisle.next;
        }
        return null;
    }

    /**
     * Add Shelf to shelfList in Aisle
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
     *
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

    /**
     * Add pallet to palletList in Shelf
     */
    public void addPallet() {
        String proDesc = textProDesc.getText();
        int proQuantity = Integer.parseInt(textProQuantity.getText());
        double minStoreTemp = Double.parseDouble(textMinStoreTemp.getText());
        double maxStoreTemp = Double.parseDouble(textMaxStoreTemp.getText());
        int palPosW = Integer.parseInt(textPalPosW.getText());
        int palPosD = Integer.parseInt(textPalPosD.getText());
        Shelf shelfFound = getShelf();

        shelfFound.palletList.addElement(new Pallet(genPalletID(), proDesc, proQuantity, minStoreTemp, maxStoreTemp, palPosW, palPosD));
        textDisplayArea.setText(getShelf().palletList.printList());

        textProDesc.clear();
        textProQuantity.clear();
        textMinStoreTemp.clear();
        textMaxStoreTemp.clear();
        textPalPosW.clear();
        textPalPosD.clear();
    }

    /**
     *
     */
    public void deletePallet() {
        int palletID = Integer.parseInt(textPalletID.getText());
        getShelf().palletList.removeNode(palletID);
    }

    /**
     *  View all method
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
        if(tempFloor.aisleList!=null) {
            textDisplayArea.appendText(tempFloor.aisleList.printList());
        } else {
            textDisplayArea.appendText("Try adding some aisles first :)");
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

    ////////////////////   Save Load and Reset   ////////////////////

    /**
     * Loads objects from text in xml document
     * @throws Exception - Error printed if floorList is empty
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("warehouseApp.xml"));
        Main.floorList = (MyList<Floor>) is.readObject();
        is.close();
        textDisplayArea.setText(Main.floorList.printList());
    }

    /**
     * Saves objects as text in xml document
     * @throws Exception -
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("warehouseApp.xml"));
        out.writeObject(Main.floorList);
        out.close();
        textDisplayArea.appendText("File has been saved"+"\n");
    }

    /**
     *  Reset clears floor list, thus clearing all other lists.
     */
    public void reset() {
        Main.floorList.emptyList();

        textDisplayArea.setText("System has been reset"+"\n");
    }

    ////////////////////   Exit   ////////////////////

    /**
     * Exit system.
      */
    public void quit(){
        System.exit(0);
    }

}
