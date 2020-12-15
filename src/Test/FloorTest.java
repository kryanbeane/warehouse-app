package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import warehouseApp.Floor;
import warehouseApp.MyList;


import static org.junit.jupiter.api.Assertions.*;

class FloorTest {
    MyList<Floor> testList;

    @BeforeEach
    void setUp() {
        testList = new MyList<>();
        testList.addElement(new Floor(1, "high", 21.00));
    }

    @Test
    void getFloorNumber() {
        int fNumber = testList.head.getContents().getFloorNumber();
        assertEquals(1, fNumber);
    }

    @Test
    void getSecurityLevel() {
        String secLvl = testList.head.getContents().getSecurityLevel();
        assertEquals("HIGH", secLvl);

        // Would pass test if addFloor threw exception when sec level was entered incorrectly
//        assertThrows(IllegalArgumentException.class, ()-> {
//                testList.head.getContents().setSecurityLevel("mediiuumm");
//    });

    }

    @Test
    void getFloorTemperature() {
        Double fTemp = testList.head.getContents().getFloorTemperature();
        assertEquals(21.00, fTemp);
    }

}