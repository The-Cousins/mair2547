package inventory.service;

import inventory.repository.InventoryRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

class InventoryServiceTest {

    InventoryRepository repo;
    InventoryService service;
    private String filename= "src/main/resources/data/testItems.txt";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // Arrange
        this.repo = new InventoryRepository(filename);
        this.service = new InventoryService(repo);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(filename));
        writer.print("");
        writer.close();
    }

    @org.junit.jupiter.api.Test
    void addInhousePart() {
        service.addInhousePart("", 5, 100, 50, 200, 19928);
    }
}