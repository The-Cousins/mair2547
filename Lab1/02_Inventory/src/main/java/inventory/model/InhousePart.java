package inventory.model;


public class InhousePart extends Part {

    // Declare fields
    private int machineId;

    // Constructor
    public InhousePart(int partId, String name, double price, int inStock, int min, int max, int machineId) {
        super(partId, name, price, inStock, min, max);
        this.machineId = machineId;
    }
    
    // Getter
    public int getMachineId() {
        return machineId;
    }
    
    // Setter
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public static String isValidInhousePart(int machineId){
        String errorMessage = "";

        if(machineId <= 0){
            errorMessage += "Machine Id can't be negative!";
        }

        return errorMessage;
    }

    @Override
    public String toString() {
        return "I,"+super.toString()+","+getMachineId();
    }
}
