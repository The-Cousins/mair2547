package unit;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class InventoryRepositoryUnitTestWithMockObjects {

    Inventory inventory;
    InventoryRepository repository;
    Part part;

    @BeforeEach
    void setUp() {
        this.inventory = mock(Inventory.class);
        String filename = "src/main/resources/data/testProductsEmpty.txt";
        this.repository = new InventoryRepository(filename, inventory);
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
    @DisplayName("Add part valid, expected: success")
    void addPartValid() {
        // Arrange
        Mockito.doNothing().when(inventory).addPart(part);
        Mockito.doReturn(FXCollections.observableArrayList()).when(inventory).getAllParts();
        Mockito.doReturn(FXCollections.observableArrayList()).when(inventory).getProducts();

        // Act
        repository.addPart(part);

        // Assert
        Mockito.verify(inventory, times(1)).addPart(part);
    }

    @Test
    @DisplayName("Get all parts, expected: success")
    void getAllParts() {
        ObservableList<Part> list = FXCollections.observableArrayList();
        list.add(part);
        Mockito.doReturn(list).when(inventory).getAllParts();

        // Act
        ObservableList<Part> returnedList = repository.getAllParts();

        // Assert
        Mockito.verify(inventory, times(1)).getAllParts();

        assertEquals(1, returnedList.size());
    }

}