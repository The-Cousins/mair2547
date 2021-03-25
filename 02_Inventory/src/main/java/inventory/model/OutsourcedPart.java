
package inventory.model;


public class OutsourcedPart extends Part {
    
    // Declare fields
    private String companyName;

    // Constructor
    public OutsourcedPart(int partId, String name, double price, int inStock, int min, int max, String companyName) {
        super(partId, name, price, inStock, min, max);
        this.companyName = companyName;
    }
    
    // Getter
    public String getCompanyName() {
        return companyName;
    }
    
    // Setter
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static String isValidOutsourcedPart(String companyName){
        String errorMessage = "";

        if(companyName.equals("")){
            errorMessage += "Company name can't be null!";
        }

        return errorMessage;
    }

    @Override
    public String toString() {
        return "O,"+super.toString()+","+getCompanyName();
    }

}
