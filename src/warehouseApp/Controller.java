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
    }


    /**
     * Gets floor to add aisle to.
     * @return - Floor node or null if floor not found
     */
    @FXML TextField textGetFloor, textCurrentFloor;
    public Floor getFloor() {
        int floorNumber = Integer.parseInt(textGetFloor.getText());

        Node<Floor> tempFloor = Main.floorList.head;
        while (tempFloor != null) {
            if (tempFloor.getContents().getFloorNumber() == floorNumber) {
                return tempFloor.getContents();
            }
            tempFloor = tempFloor.next;
            textCurrentFloor.setText(tempFloor.toString());
        }
        return null;
        textCurrentFloor.setText(Invalid Floor)
    }

    /**
     * Add aisle to aisleList in Floor
     */
    @FXML TextField textFNumber, textAID, textAisleW, textAisleD;
    public void addAisle() {
        int fNumber = Integer.parseInt(textFNumber.getText());
        String aID = textAID.getText();
        int aisleW = Integer.parseInt(textAisleW.getText());
        int aisleD = Integer.parseInt(textAisleD.getText());
        Floor floorFound = getFloor();

        if (floorFound != null) {
            floorFound.aisleList.addElement(new Aisle(fNumber, aID, aisleW, aisleD));
            System.out.println(floorFound);
            System.out.println("\n" + floorFound.aisleList.printList());
        } else {
            System.out.println("Floor not found. Aisle not added.");
        }
    }

    /**
     *  Gets aisle to add shelf to.
     * @param floorNumber - User specified floor number
     * @param aisleIdentifier - User specified aisle ID
     * @return - Aisle node or null if aisle not found
     */
    public Aisle getAisle(int floorNumber, String aisleIdentifier) {
        Node<Aisle> tempAisle = getFloor().aisleList.head;
        while(tempAisle!=null) {
            if(tempAisle.getContents().getAisleIdentifier()==aisleIdentifier) {
                return tempAisle.getContents();
            }
            tempAisle = tempAisle.next;
        }
        return null;
    }

    /**
     * Add Shelf to shelfList in Aisle
     */
    @FXML TextField textAisleID, textSNum;
    public void addShelf() {
        String aID = textAisleID.getText();
        int sNum = Integer.parseInt(textSNum.getText());
        int floorNumber = Integer.parseInt(textFNumber.getText());
        Aisle aisleFound = getAisle(floorNumber, aID);

        aisleFound.shelfList.addElement(new Shelf(aID, sNum));
        System.out.println("\n" + aisleFound.shelfList.printList());
    }

    /**
     *  Gets shelf to add pallet to.
     * @param floorNumber - User specified floor number
     * @param aisleIdentifier - User aisle ID
     * @param sNum - User specified shelf number
     * @return - Shelf node or null if shelf not found
     */
    public Shelf getShelf(int floorNumber, String aisleIdentifier, int sNum) {
        Node<Shelf> tempShelf = getAisle(floorNumber, aisleIdentifier).shelfList.head;

        while(tempShelf!=null) {
            if(tempShelf.getContents().getShelfNumber()==sNum) {
                return tempShelf.getContents();
            }
            tempShelf=tempShelf.next;
        }
        return null;
    }

    /**
     * Add pallet to palletList in Shelf
     */
    @FXML TextField textSNumber, textProDesc, textProQuantity, textMinStoreTemp, textMaxStoreTemp, textPalPosW, textPalPosD;
    public void addPallet() {
        int sNum = Integer.parseInt(textSNumber.getText());
        String proDesc = textProDesc.getText();
        int proQuantity = Integer.parseInt(textProQuantity.getText());
        double minStoreTemp = Double.parseDouble(textMinStoreTemp.getText());
        double maxStoreTemp = Double.parseDouble(textMaxStoreTemp.getText());
        int palPosW = Integer.parseInt(textPalPosW.getText());
        int palPosD = Integer.parseInt(textPalPosD.getText());
        int floorNumber = Integer.parseInt(textFNumber.getText());
        String aID = textAisleID.getText();
        Shelf shelfFound = getShelf(floorNumber, aID, sNum);

        shelfFound.palletList.addElement(new Pallet(sNum, proDesc, proQuantity, minStoreTemp, maxStoreTemp, palPosW, palPosD));
        System.out.println("\n" + shelfFound.palletList.printList());
    }

    @FXML
    TextArea textDisplayArea;
    /**
     *  View all method
     */
    public void viewAll() {
        textDisplayArea.setText(Main.floorList.printList());
    }

    /**
     * Loads objects from text in xml document
     * @throws Exception - Error printed if floorList is empty
     */
    public void load() throws Exception {
        try {
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("warehouseApp.xml"));
            Main.floorList = (MyList<Floor>) is.readObject();
            System.out.println(Main.floorList.printList());
            is.close();
        }catch(Exception e) {
            System.out.println("There are no Floors in the Loaded Data!");
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
    }
}
