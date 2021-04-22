
package inventory.model;


import inventory.utils.ExceptionNames;

public abstract class Part {

    // Declare fields
    private int partId;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    // Constructor
    protected Part(int partId, String name, double price, int inStock, int min, int max) {
        this.partId = partId;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }
    
    // Getters
    public int getPartId() {
        return partId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getInStock() {
        return inStock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    
    // Setters
    public void setPartId(int partId) {
        this.partId = partId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * Generate an error message for invalid values in a part
     * Valid part will return an empty string
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param
     * @return 
     */
    public static String isValidPart(String name, double price, int inStock, int min, int max) {

        String errorMessage = "";

        if(name.equals("")) {
            errorMessage += ExceptionNames.PartExceptions.emptyName;
        }
        if(price < 0.01) {
            errorMessage += ExceptionNames.PartExceptions.negativePrice;
        }
        if(inStock < 1) {
            errorMessage += ExceptionNames.PartExceptions.negativeStock;
        }
        if(min > max) {
            errorMessage += ExceptionNames.PartExceptions.minHigherThanMax;
        }
        if(min < 0){
            errorMessage += ExceptionNames.PartExceptions.negativeMinValue;
        }
        if(max < 0){
            errorMessage += ExceptionNames.PartExceptions.negativeMaxValue;
        }
        if(inStock < min) {
            errorMessage += ExceptionNames.PartExceptions.stockLowerThanMin;
        }
        if(inStock > max) {
            errorMessage += ExceptionNames.PartExceptions.stockHigherThanMax;
        }
        return errorMessage;
    }

    @Override
    public String toString() {
        return this.partId+","+this.name+","+this.price+","+this.inStock+","+
                this.min+","+this.max;
    }
}
