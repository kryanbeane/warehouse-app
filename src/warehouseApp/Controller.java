package warehouseApp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.print.DocFlavor;
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
     * Add aisle to aisleList in Floor
     */
    @FXML TextField textAID, textAisleW, textAisleD;
    public void addAisle() {
        String aID = textAID.getText();
        int aisleW = Integer.parseInt(textAisleW.getText());
        int aisleD = Integer.parseInt(textAisleD.getText());
        Floor floorFound = getFloor();

        if (floorFound != null) {
            floorFound.aisleList.addElement(new Aisle(aID, aisleW, aisleD));
            System.out.println("\n" + floorFound.aisleList.printList());
        }
        else {
            System.out.println("Floor not found. Aisle not added.");
        }
    }

    @FXML TextField textCurrentAisle;
    /**
     * Gets aisle to add shelf to.
     * @return - Aisle node or null if aisle not found.
     */
    public Aisle getAisle() {
        String aID = textAID.getText();
        Node<Aisle> tempAisle = getFloor().aisleList.head;
        while(tempAisle!=null) {
            if(tempAisle.getContents().getAisleIdentifier() == aID) {
                textCurrentAisle.setText(tempAisle.getContents().toString());
                return tempAisle.getContents();
            }
            tempAisle = tempAisle.next;
        }
        return null;
    }

    /**
     * Add Shelf to shelfList in Aisle
     */
    @FXML TextField textSNum;
    public void addShelf() {
        int sNum = Integer.parseInt(textSNum.getText());
        Aisle aisleFound = getAisle();

        aisleFound.shelfList.addElement(new Shelf(sNum));
        System.out.println("\n" + aisleFound.shelfList.printList());
    }

    @FXML TextField textCurrentShelf;
    /**
     * Gets shelf to add pallet to.
     * @return - Shelf node or null if shelf not found.
     */
    public Shelf getShelf() {
        int sNum = Integer.parseInt(textCurrentShelf.getText());
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
    @FXML TextField textSNumber, textProDesc, textProQuantity, textMinStoreTemp, textMaxStoreTemp, textPalPosW, textPalPosD;
    public void addPallet() {
        String proDesc = textProDesc.getText();
        int proQuantity = Integer.parseInt(textProQuantity.getText());
        double minStoreTemp = Double.parseDouble(textMinStoreTemp.getText());
        double maxStoreTemp = Double.parseDouble(textMaxStoreTemp.getText());
        int palPosW = Integer.parseInt(textPalPosW.getText());
        int palPosD = Integer.parseInt(textPalPosD.getText());
        Shelf shelfFound = getShelf();

        shelfFound.palletList.addElement(new Pallet(proDesc, proQuantity, minStoreTemp, maxStoreTemp, palPosW, palPosD));
        System.out.println("\n" + shelfFound.palletList.printList());
    }

    @FXML TextArea textDisplayArea;
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
