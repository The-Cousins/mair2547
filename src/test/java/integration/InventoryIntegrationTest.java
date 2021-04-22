package integration;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class InventoryIntegrationTest {

    Inventory inventory;
    InventoryRepository repository;
    InventoryService service;
    String filename = "src/main/resources/data/testProductsEmpty.txt";
    InhousePart part;

    @BeforeEach
    void setUp() {
        this.inventory = new Inventory();

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

    @AfterEach
    void tearDown() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(filename));
        writer.print("");
        writer.close();
    }

    @Test
    void addPart() throws Exception {
        // Act
        service.addInhousePart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax(), part.getMachineId(), part);
        String returnedValue = inventory.getAllParts().get(0).getName();

        // Assert
        assertEquals(part.getName(), returnedValue);
    }

    @Test
    void getAllParts() throws Exception {
        // Act
        service.addInhousePart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax(), part.getMachineId(), part);
        ObservableList<Part> returnedList = service.getAllParts();

        // Assert
        assertEquals(1, returnedList.size());
    }
}