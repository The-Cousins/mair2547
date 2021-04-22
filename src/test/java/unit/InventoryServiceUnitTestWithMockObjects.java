package unit;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import inventory.utils.ExceptionNames;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceUnitTestWithMockObjects {

    InventoryRepository repo;
    InventoryService service;

    @BeforeEach
    void setUp() {
        this.repo = mock(InventoryRepository.class);
        this.service = new InventoryService(repo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Add inhouse part valid, expected: success")
    void addInhousePartValid() throws Exception {
        // Arrange
        int partId = 0;
        String name = "te";
        int price = 5;
        int inStock = 100;
        int min = 100;
        int max = 200;
        int machineId = 29381;
        InhousePart part1 = new InhousePart(partId,name,price, inStock, min, max, machineId);

        // Act
        Mockito.doNothing().when(repo).addPart(part1);
        service.addInhousePart(name, price, inStock, min, max, machineId, part1);

        // Assert
        Mockito.verify(repo, times(1)).addPart(part1);
    }

    @Test
    @DisplayName("Add inhouse part invalid, expected: exception")
    void addInhousePartInvalid() {
        // Arrange
        int partId = 0;
        String name = "surub";
        int price = 5;
        int inStock = 201;
        int min = 50;
        int max = 200;
        int machineId = 19928;
        InhousePart part1 = new InhousePart(partId,name,price, inStock, min, max, machineId);

        // Act
        Throwable exception = assertThrows(Exception.class, ()-> service.addInhousePart(name, price, inStock, min, max, machineId, null));

        // Assert
        assertEquals(ExceptionNames.PartExceptions.stockHigherThanMax, exception.getMessage());
        Mockito.verify(repo, never()).addPart(part1);
    }
}