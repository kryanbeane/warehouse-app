package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import warehouseApp.Floor;
import warehouseApp.MyList;
import warehouseApp.Pallet;

import static org.junit.jupiter.api.Assertions.*;

class PalletTest {
    MyList<Pallet> testList;

    @BeforeEach
    void setUp() {
        testList = new MyList<>();
        testList.addElement(new Pallet("1A-A1", "Bananas", 25,
                21,30, 12, 4));
    }

    @Test
    void getPalletID() {
        String palID = testList.head.getContents().getPalletID();
        assertEquals("1A-A1", palID);
    }

    @Test
    void getProductDescription() {
        String proDesc = testList.head.getContents().getProductDescription();
        assertEquals("Bananas", proDesc);
    }

    @Test
    void getProductQuantity() {
        int proQ = testList.head.getContents().getProductQuantity();
        assertEquals(25, proQ);
    }

    @Test
    void getMinimumStoreTemperature() {
        Double minST = testList.head.getContents().getMinimumStoreTemperature();
        assertEquals(21, minST);
    }

    @Test
    void getMaximumStoreTemperature() {
        Double maxST = testList.head.getContents().getMaximumStoreTemperature();
        assertEquals(30, maxST);
    }

    @Test
    void getPalletPositionWidth() {
        int palPW = testList.head.getContents().getPalletPositionWidth();
        assertEquals(12, palPW);
    }

    @Test
    void getPalletPositionDepth() {
        int palPD = testList.head.getContents().getPalletPositionDepth();
        assertEquals(4, palPD);
    }
}