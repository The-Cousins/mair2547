package inventory.service;

import inventory.model.Product;
import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class SearchProductTest {

    InventoryRepository repo;
    InventoryService service;

    InventoryRepository emptyRepo;
    InventoryService emptyService;

    @BeforeEach
    void setUp() {
        String filename = "src/main/resources/data/testProducts.txt";
        this.repo = new InventoryRepository(filename);
        this.service = new InventoryService(repo);

        String emptyFilename = "src/main/resources/data/testProductsEmpty.txt";
        this.emptyRepo = new InventoryRepository(emptyFilename);
        this.emptyService = new InventoryService(emptyRepo);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    @org.junit.jupiter.api.DisplayName("Null input expected null")
    void WBTNullInputExpectedNull() {
        // Act
        Product result = service.lookupProduct(null);

        // Assert
        assert result == null;
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    @org.junit.jupiter.api.DisplayName("Lookup for nonexistent product expected null")
    void WTBEmptyProductListExpectedNull() {
        // Arrange
        String name = "ceas";

        // Act
        Product result = emptyService.lookupProduct(name);

        // Assert
        assert result == null;
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    @org.junit.jupiter.api.DisplayName("First product found by name expected product")
    void WTBFirstProductFoundByNameExpectedProduct() {
        // Arrange
        String name = "ceas";

        // Act
        Product result = service.lookupProduct(name);

        // Assert
        assert result.getProductId() == 1;
        assert result.getName().equals(name);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    @org.junit.jupiter.api.DisplayName("First product found by id expected product")
    void WTBFirstProductFoundByIdExpectedProduct() {
        // Arrange
        String id = "1";
        String expectedName = "ceas";

        // Act
        Product result = service.lookupProduct(id);

        // Assert
        assert result.getProductId() == 1;
        assert result.getName().equals(expectedName);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    @org.junit.jupiter.api.DisplayName("Lookup product by name expected product")
    void WTBProductFoundByNameExpectedProduct() {
        // Arrange
        String name = "lampa";

        // Act
        Product result = service.lookupProduct(name);

        // Assert
        assert result.getProductId() == 2;
        assert result.getName().equals(name);
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    @org.junit.jupiter.api.DisplayName("Lookup product by id expected product")
    void WTBProductFoundByIdExpectedProduct() {
        // Arrange
        String id = "2";
        String expectedName = "lampa";

        // Act
        Product result = service.lookupProduct(id);

        // Assert
        assert result.getProductId() == 2;
        assert result.getName().equals(expectedName);
    }

    @Test
    @org.junit.jupiter.api.Order(7)
    @org.junit.jupiter.api.DisplayName("Lookup second to last product by id expected product")
    void WTBLookupSecondToLastProductFoundByIdExpectedProduct() {
        // Arrange
        String id = "3";
        String expectedName = "laptop";

        // Act
        Product result = service.lookupProduct(id);

        // Assert
        assert result.getProductId() == 3;
        assert result.getName().equals(expectedName);
    }

    @Test
    @org.junit.jupiter.api.Order(8)
    @org.junit.jupiter.api.DisplayName("Lookup last product by id expected product")
    void WTBLookupLastProductFoundByIdExpectedProduct() {
        // Arrange
        String id = "4";
        String expectedName = "mouse";

        // Act
        Product result = service.lookupProduct(id);

        // Assert
        assert result.getProductId() == 4;
        assert result.getName().equals(expectedName);
    }

    @Test
    @org.junit.jupiter.api.Order(9)
    @org.junit.jupiter.api.DisplayName("Lookup product expected null")
    void WTBLookupProductExpectedNull() {
        // Arrange
        String id = "5";

        // Act
        Product result = service.lookupProduct(id);

        // Assert
        assert result == null;
    }
}