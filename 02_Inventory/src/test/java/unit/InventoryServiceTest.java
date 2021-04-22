package unit;

import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import inventory.utils.ExceptionNames;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/** USED ANNOTATIONS
 * @org.junit.jupiter.api.Tag()
 * @org.junit.jupiter.api.RepeatedTest()
 * @org.junit.jupiter.api.Order()
 * @org.junit.jupiter.api.DisplayName
 * @org.junit.Ignore
 */


//  Unit testing service
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryServiceTest {

    InventoryRepository repo;
    InventoryService service;
    private final String filename= "src/main/resources/data/testItems.txt";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.repo = new InventoryRepository(filename);
        this.service = new InventoryService(repo);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(filename));
        writer.print("");
        writer.close();
    }

    @org.junit.jupiter.api.Tag("ECP")
    @org.junit.jupiter.api.DisplayName("Success added valid part")
    @org.junit.jupiter.api.Order(1)
    @org.junit.jupiter.api.RepeatedTest(5)
    void addInhouseValidPartTest1() {
        try {
            // Arrange
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
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

    @org.junit.jupiter.api.Tag("ECP")
    @org.junit.jupiter.api.DisplayName("Exception invalid name")
    @org.junit.jupiter.api.Order(2)
    @org.junit.jupiter.api.Test
    void addInhouseInvalidNamePartTest2() {
        try {
            String name = "";
            int price = 5;
            int inStock = 100;
            int min = 50;
            int max = 200;
            int partDynamicValue = 29381;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
        } catch (Exception exception) {
            // Assert
            assert exception.getMessage().equals(ExceptionNames.PartExceptions.emptyName);
        }
    }

    @org.junit.jupiter.api.Tag("ECP")
    @org.junit.jupiter.api.DisplayName("Exception stock lower than min threshold")
    @org.junit.jupiter.api.Test
    void addInhouseStockLowerThanMinPartTest10() {
        try {
            String name = "surub";
            int price = 5;
            int inStock = 49;
            int min = 50;
            int max = 200;
            int partDynamicValue = 19928;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
        } catch (Exception exception) {
            // Assert
            assert exception.getMessage().equals(ExceptionNames.PartExceptions.stockLowerThanMin);
        }
    }

    @org.junit.jupiter.api.Tag("ECP")
    @org.junit.jupiter.api.DisplayName("Exception stock higher than max threshold")
    @org.junit.jupiter.api.Test
    void addInhouseStockGreaterThanMaxPartTest11() {
        try {
            String name = "surub";
            int price = 5;
            int inStock = 201;
            int min = 50;
            int max = 200;
            int partDynamicValue = 19928;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
        } catch (Exception exception) {
            // Assert
            assert exception.getMessage().equals(ExceptionNames.PartExceptions.stockHigherThanMax);
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Exception invalid name")
    @org.junit.jupiter.api.Test
    void addInhouseInvalidNamePartBVATest1() {
        try {
            String name = "";
            int price = 5;
            int inStock = 100;
            int min = 50;
            int max = 200;
            int partDynamicValue = 29381;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
            assert false;
        } catch (Exception exception) {
            // Assert
            assert exception.getMessage().equals(ExceptionNames.PartExceptions.emptyName);
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Ignored test")
    @org.junit.Ignore
    @org.junit.jupiter.api.Test
    void ignoredTest() {
        try {
            String name = "";
            int price = 5;
            int inStock = 100;
            int min = 50;
            int max = 200;
            int partDynamicValue = 29381;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
            assert false;
        } catch (Exception exception) {
            // Assert
            assert exception.getMessage().equals(ExceptionNames.PartExceptions.emptyName);
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Success added valid part")
    @org.junit.jupiter.api.Test
    void addInhouseValidPartBVATest2() {
        try {
            String name = "t";
            int price = 5;
            int inStock = 100;
            int min = 50;
            int max = 200;
            int partDynamicValue = 29381;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);

            List<Part> allParts = service.getAllParts();
            // Assert
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Success added valid part")
    @org.junit.jupiter.api.Test
    void addInhouseValidPartBVATest3() {
        try {
            String name = "te";
            int price = 5;
            int inStock = 100;
            int min = 50;
            int max = 200;
            int partDynamicValue = 29381;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);

            List<Part> allParts = service.getAllParts();
            // Assert
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Success added valid part")
    @org.junit.jupiter.api.Test
    void addInhouseValidPartBVATest4() {
        try {
            String name = "te...12";
            int price = 5;
            int inStock = 100;
            int min = 50;
            int max = 200;
            int partDynamicValue = 29381;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);

            List<Part> allParts = service.getAllParts();
            // Assert
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Success added valid part")
    @org.junit.jupiter.api.Test
    void addInhouseValidPartBVATest5() {
        try {
            String name = "te...123";
            int price = 5;
            int inStock = 100;
            int min = 50;
            int max = 200;
            int partDynamicValue = 29381;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);

            List<Part> allParts = service.getAllParts();
            // Assert
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Success added valid part")
    @org.junit.jupiter.api.Test
    void addInhouseValidPartBVATest26() {
        try {
            String name = "surub";
            int price = 5;
            int inStock = 50;
            int min = 50;
            int max = 200;
            int partDynamicValue = 19928;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);

            List<Part> allParts = service.getAllParts();
            // Assert
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Success added valid part")
    @org.junit.jupiter.api.Test
    void addInhouseValidPartBVATest27() {
        try {
            String name = "surub";
            int price = 5;
            int inStock = 51;
            int min = 50;
            int max = 200;
            int partDynamicValue = 19928;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);

            List<Part> allParts = service.getAllParts();
            // Assert
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Success added valid part")
    @org.junit.jupiter.api.Test
    void addInhouseValidPartBVATest28() {
        try {
            String name = "surub";
            int price = 5;
            int inStock = 199;
            int min = 50;
            int max = 200;
            int partDynamicValue = 19928;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);

            List<Part> allParts = service.getAllParts();
            // Assert
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

    @org.junit.jupiter.api.Tag("BVA")
    @org.junit.jupiter.api.DisplayName("Success added valid part")
    @org.junit.jupiter.api.Test
    void addInhouseValidPartBVATest29() {
        try {
            String name = "surub";
            int price = 5;
            int inStock = 200;
            int min = 50;
            int max = 200;
            int partDynamicValue = 19928;

            // Act
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);

            List<Part> allParts = service.getAllParts();
            // Assert
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

}