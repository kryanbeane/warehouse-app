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

    /**
     * Add Floor to floorList in Main
     */
    @FXML TextField textFNum, textSecLvl, textFTemp;
    @FXML TextArea textFloorArea;
    public void addFloor() {
        int floorNumber = Integer.parseInt(textFNum.getText());
        String securityLevel = textSecLvl.getText();
        double floorTemperature = Double.parseDouble(textFTemp.getText());
        Main.floorList.addElement(new Floor(floorNumber, securityLevel, floorTemperature));
        textFloorArea.setText(Main.floorList.printList());
        textFNum.clear();
        textSecLvl.clear();
        textFTemp.clear();
    }

    /**
     * Get floor node to add aisle to.
     */
    @FXML TextField textGetFloor, textCurrentFloor;
    public Floor getFloor() {
        int floorNumber = Integer.parseInt(textGetFloor.getText());
        Node<Floor> tempFloor = Main.floorList.head;
        while (tempFloor != null) {
            if (tempFloor.getContents().getFloorNumber() == floorNumber) {
                textCurrentFloor.setText(tempFloor.getContents().toString2());
                return tempFloor.getContents();
            }
            tempFloor = tempFloor.next;
        }
        return null;
    }

    /**
     * Generates an ID to assign to a newly created aisle using the floor number and a random letter.
     * @return - Randomly generated aisle ID.
     */
    public String genAisleID() {
        Random random = new Random();
        Floor floorFound = getFloor();
        int integerPart = floorFound.getFloorNumber();   // Makes the int part a number between 1 and 10
        int stringIndex = random.nextInt(26);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char charPart = alpha.charAt(stringIndex);
        return integerPart + String.valueOf(charPart);
    }

    /**
     *
     * @return -
     */
    public String genShelfID() {
        Random random = new Random();
        Aisle aisleFound = getAisle();
        String aislePart = aisleFound.getAisleIdentifier();
        int stringIndex = random.nextInt(26);
        String a="-";
        return aislePart+a+stringIndex;
    }

    /**
     *
     */
    public String genPalletID() {
        Random random = new Random();
        Shelf shelfFound = getShelf();
        String firstPart = shelfFound.getShelfNumber();
        String a="-";
        int intPart=random.nextInt(26);
        return firstPart + a + intPart;
    }

    /**
     * Add aisle to aisleList in Floor
     */
    @FXML TextField textAisleW, textAisleD;
    @FXML TextArea textAisleArea;
    public void addAisle() {
        int aisleW = Integer.parseInt(textAisleW.getText());
        int aisleD = Integer.parseInt(textAisleD.getText());
        Floor floorFound = getFloor();

        if (floorFound != null) {
            floorFound.aisleList.addElement(new Aisle(genAisleID(), aisleW, aisleD));
            System.out.println("\n" + floorFound.aisleList.printList());
            textAisleArea.setText(floorFound.aisleList.printList());
            textAisleW.clear();
            textAisleD.clear();
        } else {
            System.out.println("Floor not found. Aisle not added.");
        }
    }

    /**
     *
     */
    @FXML TextField textGetAisle, textCurrentAisle;
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
    @FXML TextField textShelfArea;
    public void addShelf() {
        Aisle aisleFound = getAisle();

        if(aisleFound!=null) {
            aisleFound.shelfList.addElement(new Shelf(genShelfID()));
            textShelfArea.setText(getAisle().shelfList.printList());
         } else {
            textShelfArea.appendText("Aisle not selected, please choose an aisle :)");
         }
    }

    /**
     *
     */
    @FXML TextField textCurrentShelf, textGetShelf;
    public Shelf getShelf() {
        String sNum = textGetShelf.getText();
        Node<Shelf> tempShelf = getAisle().shelfList.head;
        while(tempShelf!=null) {
            if(tempShelf.getContents().getShelfNumber()==sNum) {
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
    @FXML TextField textProDesc, textProQuantity, textMinStoreTemp, textMaxStoreTemp, textPalPosW, textPalPosD;
    @FXML TextArea textPalletArea;
    public void addPallet() {
        String proDesc = textProDesc.getText();
        int proQuantity = Integer.parseInt(textProQuantity.getText());
        double minStoreTemp = Double.parseDouble(textMinStoreTemp.getText());
        double maxStoreTemp = Double.parseDouble(textMaxStoreTemp.getText());
        int palPosW = Integer.parseInt(textPalPosW.getText());
        int palPosD = Integer.parseInt(textPalPosD.getText());
        Shelf shelfFound = getShelf();

        shelfFound.palletList.addElement(new Pallet(genPalletID(), proDesc, proQuantity, minStoreTemp, maxStoreTemp, palPosW, palPosD));
        textPalletArea.setText(getShelf().palletList.printList());

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
    @FXML TextField textPalletID;
    public void deletePallet() {
        int palletID = Integer.parseInt(textPalletID.getText());
        getShelf().palletList.removeNode(palletID);
    }

    /**
     *  View all method
     */
    @FXML TextArea textDisplayArea;
    public void viewAll() {

    }

    public void viewFloors() {
        textFloorArea.setText(Main.floorList.printList());
    }

    public void viewAisles() {
        Floor tempFloor = getFloor();
        if (tempFloor != null) {
            textAisleArea.setText(tempFloor.aisleList.printList());
        } else if (tempFloor.aisleList == null) {
            textAisleArea.setText("Add some aisles to this floor to view them!");
        } else {
            textAisleArea.setText("Please select a floor to view it's aisles!");
        }
    }

    public void viewShelves() {
//        if() {
//
//        } else {
//
//        }
    }

    public void viewPallets() {
//        if() {
//
//        } else {
//
//        }
    }

    /**
     * Loads objects from text in xml document
     * @throws Exception - Error printed if floorList is empty
     */
    public void load() throws Exception {
        try {
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("warehouseApp.xml"));
            Main.floorList = (MyList<Floor>)is.readObject();
            textDisplayArea.setText(Main.floorList.printList());
            is.close();
        }catch(Exception e) {
            textDisplayArea.setText("There are no Floors in the Loaded Data!");
        }

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
        System.out.println("File has been saved");
    }

    /**
     *  Reset clears floor list, thus clearing all other lists as aisle is a node of floor,
     *  shelf is a node of aisle and pallet is a node of shelf.
     */
    public void reset() {
        Main.floorList.clear();
        textDisplayArea.setText(Main.floorList.printList());
    }

}
