package inventory.service;

import inventory.model.Part;
import inventory.repository.InventoryRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

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
        addInhouseValidPart();
        addInhouseInvalidNamePart();
        addInhouseStockLowerThanMinPart();
        addInhouseStockGreaterThanMaxPart();
    }

    @org.junit.jupiter.api.Test
    void addInhouseValidPart() {
        try {
            String name = "surub";
            int price = 5;
            int inStock = 100;
            int min = 5;
            int max = 200;
            int partDynamicValue = 19928;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);

            List<Part> allParts = service.getAllParts();
            // Assert
            assert allParts.get(0).getName().equals(name);
            assert allParts.get(0).getPrice() == price;
            assert allParts.get(0).getInStock() == inStock;
            assert allParts.get(0).getMin() == min;
            assert allParts.get(0).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

    @org.junit.jupiter.api.Test
    void addInhouseInvalidNamePart() {
        try {
            String name = "";
            int price = 5;
            int inStock = 10;
            int min = 5;
            int max = 200;
            int partDynamicValue = 29381;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
        } catch (Exception exception) {
            // Assert
            assert exception.getMessage().equals("A name has not been entered. ");
        }
    }

    @org.junit.jupiter.api.Test
    void addInhouseStockLowerThanMinPart() {
        try {
            String name = "surub";
            int price = 5;
            int inStock = 25;
            int min = 50;
            int max = 200;
            int partDynamicValue = 19928;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
        } catch (Exception exception) {
            // Assert
            assert exception.getMessage().equals("Inventory level is lower than minimum value. ");
        }
    }

    @org.junit.jupiter.api.Test
    void addInhouseStockGreaterThanMaxPart() {
        try {
            String name = "surub";
            int price = 5;
            int inStock = 500;
            int min = 50;
            int max = 200;
            int partDynamicValue = 19928;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
        } catch (Exception exception) {
            // Assert
            assert exception.getMessage().equals("Inventory level is higher than the maximum value. ");
        }
    }
}