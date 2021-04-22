package unit;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryUnitTest {

    Inventory inventory;
    Part part;

    @BeforeEach
    void setUp() {
        this.inventory = new Inventory();

        int partId = 0;
        String name = "te";
        int price = 5;
        int inStock = 100;
        int min = 100;
        int max = 200;
        int machineId = 29381;
        this.part = new InhousePart(partId,name,price, inStock, min, max, machineId);
    }

    @Test
    @DisplayName("Add valid part, expected: nothing")
    void addPartValid() {
        // Act
        inventory.addPart(part);

        // Assert
        assertEquals(1, inventory.getAllParts().size());

    }

    @Test
    @DisplayName("Get all parts, expected: parts")
    void getAllParts() {
        // Act
        ObservableList<Part> returnedList = inventory.getAllParts();

        // Assert
        assertEquals(FXCollections.observableArrayList(), returnedList);

        ///

        // Act
        inventory.addPart(part);
        inventory.addPart(part);
        ObservableList<Part> returnedList2 = inventory.getAllParts();

        // Assert
        assertEquals(2, returnedList2.size());
    }
}