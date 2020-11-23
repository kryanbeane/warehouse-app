package warehouseApp;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;
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
    public void addFloor() {
        int floorNumber = Integer.parseInt(textFNum.getText());
        String securityLevel = textSecLvl.getText();
        double floorTemperature = Double.parseDouble(textFTemp.getText());
        Main.floorList.addElement(new <Aisle>Floor(floorNumber, securityLevel, floorTemperature));
        System.out.println("Floors" + Main.floorList.printList());
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
        Floor floorFound = getFloor(fNumber);

        if (floorFound!=null) {
            floorFound.aisleList.addElement(new Aisle(fNumber, aID, aisleW, aisleD));
            System.out.println(floorFound);
            System.out.println("\n" + floorFound.aisleList.printList());
        }
        else {
            System.out.println("Floor not found. Aisle not added.");
        }
    }

    /**
     * Gets floor to add aisle to.
     * @param floorNumber - User specified floor number
     * @return - Floor node or null if floor not found
     */
    public Floor getFloor(int floorNumber) {
        Node<Floor> tempFloor = Main.floorList.head;
        while(tempFloor!=null) {
            if(tempFloor.getContents().getFloorNumber()==floorNumber) {
                return tempFloor.getContents();
            }
            tempFloor=tempFloor.next;
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
        Aisle.shelfList.addElement(new <Pallet>Shelf(aID, sNum));
        System.out.println("Shelf" + Aisle.shelfList.printList());
    }

    /**
     *
     * @return
     */
    public Aisle getAisle(String aisleIdentifier) {
        Node<Aisle> tempAisle =
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


        Shelf.palletList.addElement(new Pallet(sNum, proDesc, proQuantity, minStoreTemp, maxStoreTemp, palPosW, palPosD));
        System.out.println("Pallet" + Shelf.palletList.printList());
    }

    /**
     *
     * @return
     */
    public Shelf getShelf() {

    }

    /**
     *  Display the list of floors
     */
    public void displayFloors() { System.out.println(Main.floorList.printList()); }

    /**
     *  View all method
     */
    public void viewAll() {}

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
