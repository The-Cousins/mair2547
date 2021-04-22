package integration;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import inventory.utils.ExceptionNames;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.*;

class InventoryRepositoryIntegrationTest {

    Inventory inventory;
    InventoryRepository repository;
    InventoryService service;
    InhousePart part;

    @BeforeEach
    void setUp() {
        this.inventory = mock(Inventory.class);
        String filename = "src/main/resources/data/testItems.txt";
        this.repository = new InventoryRepository(filename, inventory);
        this.service = new InventoryService(repository);

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
    void addPartValid() throws Exception {
        // Arrange
        Mockito.doNothing().when(inventory).addPart(part);
        Mockito.doReturn(FXCollections.observableArrayList()).when(inventory).getAllParts();
        Mockito.doReturn(FXCollections.observableArrayList()).when(inventory).getProducts();

        // Act
        service.addInhousePart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax(), part.getMachineId(), part);

        // Assert
        Mockito.verify(inventory, times(1)).addPart(part);
    }

    @Test
    @DisplayName("Add invalid part, expected: error")
    void addInvalidPart() {
        // Arrange
        this.part.setInStock(201);

        // Act
        Throwable exception = assertThrows(Exception.class, ()-> service.addInhousePart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax(), part.getMachineId(), null));

        // Assert
        assertEquals(ExceptionNames.PartExceptions.stockHigherThanMax, exception.getMessage());
        Mockito.verify(inventory, never()).addPart(part);
    }
}