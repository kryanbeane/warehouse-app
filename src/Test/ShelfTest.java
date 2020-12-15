package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import warehouseApp.Floor;
import warehouseApp.MyList;
import warehouseApp.Shelf;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {
    MyList<Shelf> testList;

    @BeforeEach
    void setUp() {
        testList = new MyList<>();
        testList.addElement(new Shelf("1A-A"));
    }

    @Test
    void getShelfNumber() {
        String sNumber = testList.head.getContents().getShelfNumber();
        assertEquals("1A-A", sNumber);
    }
}