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

//    ECP
    @org.junit.jupiter.api.Test
    void addInhousePart() {
        addInhouseValidPartTest1();
        addInhouseInvalidNamePartTest2();
        addInhouseStockLowerThanMinPartTest10();
        addInhouseStockGreaterThanMaxPartTest11();
    }

    @org.junit.jupiter.api.Test
    void addInhouseValidPartTest1() {
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
            assert allParts.get(allParts.size()-1).getName().equals(name);
            assert allParts.get(allParts.size()-1).getPrice() == price;
            assert allParts.get(allParts.size()-1).getInStock() == inStock;
            assert allParts.get(allParts.size()-1).getMin() == min;
            assert allParts.get(allParts.size()-1).getMax() == max;
        } catch (Exception exception) {
            assert false;
        }
    }

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
            assert exception.getMessage().equals("A name has not been entered. ");
        }
    }

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
            assert exception.getMessage().equals("Inventory level is lower than minimum value. ");
        }
    }

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
            assert exception.getMessage().equals("Inventory level is higher than the maximum value. ");
        }
    }

//    BVA
    @org.junit.jupiter.api.Test
    void addInhousePartBVA() {
        addInhouseInvalidNamePartBVATest1();
        addInhouseValidPartBVATest2();
        addInhouseValidPartBVATest3();
        addInhouseValidPartBVATest4();
        addInhouseValidPartBVATest5();

        addInhouseValidPartBVATest26();
        addInhouseValidPartBVATest27();
        addInhouseValidPartBVATest28();
        addInhouseValidPartBVATest29();
    }

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
            assert exception.getMessage().equals("A name has not been entered. ");
        }
    }

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