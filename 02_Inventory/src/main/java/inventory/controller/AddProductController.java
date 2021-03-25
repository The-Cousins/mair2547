package inventory.controller;

import inventory.model.Part;
import inventory.model.Product;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddProductController implements Initializable, Controller {
    
    // Declare fields
    private Stage stage;
    private Parent scene;
    private ObservableList<Part> addParts = FXCollections.observableArrayList();
    private String errorMessage = new String();

    private InventoryService service;
    
    @FXML
    private TextField minTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField inventoryTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField productSearchTxt;

    @FXML
    private TableView<Part> addPartTableView;

    @FXML
    private TableColumn<Part, Integer> addProductIdCol;

    @FXML
    private TableColumn<Part, String> addProductNameCol;

    @FXML
    private TableColumn<Part, Double> addProductInventoryCol;

    @FXML
    private TableColumn<Part, Integer> addProductPriceCol;

    @FXML
    private TableView<Part> deletePartTableView;

    @FXML
    private TableColumn<Part, Integer> deleteProductIdCol;

    @FXML
    private TableColumn<Part, String> deleteProductNameCol;

    @FXML
    private TableColumn<Part, Double> deleteProductInventoryCol;

    @FXML
    private TableColumn<Part, Integer> deleteProductPriceCol;

    public AddProductController(){}

    public void setService(InventoryService service){
        this.service = service;
        addPartTableView.setItems(service.getAllParts());

    }

    /**
     * Initializes the controller class and populates table view.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate add product table view
        addProductIdCol.setCellValueFactory(new PropertyValueFactory<>("partId"));
        addProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        addProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        deleteProductIdCol.setCellValueFactory(new PropertyValueFactory<>("partId"));
        deleteProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        deleteProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        deleteProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Method to add to button handler to switch to scene passed as source
     * @param event
     * @param source
     * @throws IOException
     */
    @FXML
    private void displayScene(ActionEvent event, String source) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        FXMLLoader loader= new FXMLLoader(getClass().getResource(source));
        //scene = FXMLLoader.load(getClass().getResource(source));
        scene = loader.load();
        Controller ctrl=loader.getController();
        ctrl.setService(service);
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /**
     * Method to add values of addParts to the bottom table view of the scene.
     */
    public void updateTableView() {
        deletePartTableView.setItems(addParts);
    }

    /**
     * Ask user for confirmation before deleting selected part from current product.
     * @param
     */
    @FXML
    void handleDeleteProduct() {
        Part part = deletePartTableView.getSelectionModel().getSelectedItem();

        if(part != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm Part Deletion!");
            alert.setContentText("Are you sure you want to delete part " + part.getName() + " from parts?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                System.out.println("Part deleted.");
                addParts.remove(part);
            } else {
                System.out.println("Canceled part deletion.");
            }
        }
    }

    /**
     * Ask user for confirmation before canceling product addition
     * and switching scene to Main Screen
     * @param event
     * @throws IOException
     */
    @FXML
    void handleCancelProduct(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Needed");
        alert.setHeaderText("Confirm Cancelation");
        alert.setContentText("Are you sure you want to cancel adding product?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            System.out.println("Ok selected. Product addition canceled.");
            try {
                displayScene(event, "/fxml/MainScreen.fxml");
            }
            catch(IOException e){
                showErrorLoadScene();
            }
        } else {
            System.out.println("Cancel clicked.");
        }
    }
    
    /**
     * Add selected part from top table view to bottom table view in order to create
     * new product
     * @param
     */
    @FXML
    void handleAddProduct() {
        Part part = addPartTableView.getSelectionModel().getSelectedItem();
        if(part != null) {
            addParts.add(part);
            updateTableView();
        }
    }

    /**
     * Validate given product parameters.  If valid, add product to inventory,
     * else give user an error message explaining why the product is invalid.
     * @param event
     * @throws IOException
     */
    @FXML
    void handleSaveProduct(ActionEvent event){
        String name = nameTxt.getText();
        String priceStr = priceTxt.getText();
        String inStockStr = inventoryTxt.getText();
        String minStr = minTxt.getText();
        String maxStr = maxTxt.getText();
        errorMessage = "";
        
        try {

            Double price = Double.parseDouble(priceStr);
            Integer inStock = Integer.parseInt(inStockStr);
            Integer min = Integer.parseInt(minStr);
            Integer max = Integer.parseInt(maxStr);

            errorMessage = Product.isValidProduct(name, price, inStock, min, max, addParts, errorMessage);
            if(errorMessage.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Adding Part!");
                alert.setHeaderText("Error!");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            } else {
                service.addProduct(name, price, inStock, min, max, addParts);
                displayScene(event, "/fxml/MainScreen.fxml");
            }
        } catch (NumberFormatException e) {
            System.out.println("Form contains blank field.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Product!");
            alert.setHeaderText("Error!");
            alert.setContentText("Form contains blank field.");
            alert.showAndWait();
        }
        catch(IOException e){
            showErrorLoadScene();
        }

    }

    /**
     * Gets search text and inputs into lookupAssociatedPart method to highlight desired part
     * @param
     */
    @FXML
    void handleSearchPart() {
        String x = productSearchTxt.getText();
        addPartTableView.getSelectionModel().select(service.lookupPart(x));
    }

    void showErrorLoadScene(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error loading view!");
        alert.setHeaderText("Error!");
        alert.setContentText("The view could not be loaded properly. Try again!");
        alert.showAndWait();
    }
    
}
