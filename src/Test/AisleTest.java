package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import warehouseApp.Aisle;
import warehouseApp.Floor;
import warehouseApp.MyList;

import static org.junit.jupiter.api.Assertions.*;

class AisleTest {
    MyList<Aisle> testList;

    @BeforeEach
    void setUp() {
        testList = new MyList<>();
        testList.addElement(new Aisle("1A", 12,4));
    }

    @Test
    void getAisleIdentifier() {
        String aisleID = testList.head.getContents().getAisleIdentifier();
        assertEquals("1A", aisleID);
    }

    @Test
    void getAisleWidth() {
        int aW = testList.head.getContents().getAisleWidth();
        assertEquals(12, aW);
    }

    @Test
    void getAisleDepth() {
        int aD = testList.head.getContents().getAisleDepth();
        assertEquals(4, aD);
    }
}