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
     * Floor list methods
     */
    @FXML
    TextField textFNum, textSecLvl, textFTemp;
    public void addFloor() {
        int fNum = Integer.parseInt(textFNum.getText());
        String secLvl = textSecLvl.getText();
        double fTemp = Double.parseDouble(textFTemp.getText());

        // Validation for security level so users can only enter high, medium or low
        if (!secLvl.equalsIgnoreCase("high") && !secLvl.equalsIgnoreCase("medium") && !secLvl.equalsIgnoreCase("low")) {
            System.out.println("Error: Incorrect Security Level. Please enter HIGH, MEDIUM or LOW"); // Outputs error if high medium or low is not entered
        }
        else {
            Main.floorList.addElement(new <Aisle>Floor(fNum, secLvl.toUpperCase(), fTemp));
            System.out.println("Floor" + Main.floorList.printList());
        }
    }

    public void displayFloors() { System.out.println(Main.floorList.printList()); }

    /**
     * Aisle list methods
     */
    @FXML TextField textAID, textAisleW, textAisleD;
    public void addAisle() {
        String aID = textAID.getText();
        int aisleW = Integer.parseInt(textAisleW.getText());
        int aisleD = Integer.parseInt(textAisleD.getText());
        Floor.aisleList.addElement(new <Shelf>Aisle(aID, aisleW, aisleD));
        System.out.println("Aisle" + Floor.aisleList.printList());
    }

    /**
     * Shelf list methods
     */
    @FXML TextField textSNum;
    public void addShelf() {
        int sNum = Integer.parseInt(textSNum.getText());
        Aisle.shelfList.addElement(new Shelf(sNum));
        System.out.println("Shelf" + Aisle.shelfList.printList());
    }

    /**
     * Pallet list methods
     */
    @FXML TextField textProDesc, textProQuantity, textMinStoreTemp, textMaxStoreTemp, textPalPosW, textPalPosD;
    public void addPallet() {
        String proDesc = textProDesc.getText();
        int proQuantity = Integer.parseInt(textProQuantity.getText());
        double minStoreTemp = Double.parseDouble(textMinStoreTemp.getText());
        double maxStoreTemp = Double.parseDouble(textMaxStoreTemp.getText());
        int palPosW = Integer.parseInt(textPalPosW.getText());
        int palPosD = Integer.parseInt(textPalPosD.getText());
        Shelf.palletList.addElement(new Pallet(proDesc, proQuantity, minStoreTemp, maxStoreTemp, palPosW, palPosD));
        System.out.println("Pallet" + Shelf.palletList.printList());
    }

    public void viewAll() {
        System.out.println("Floor" + Main.floorList.printList());
        System.out.println("Aisle" + Floor.aisleList.printList());
        System.out.println("Shelf" + Aisle.shelfList.printList());
        System.out.println("Pallet" + Shelf.palletList.printList());
    }

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

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("warehouseApp.xml"));
        out.writeObject(Main.floorList);
        out.close();
        System.out.println("File has been saved");
    }

    public void reset() {
        Main.floorList.clearList();
    }
}
