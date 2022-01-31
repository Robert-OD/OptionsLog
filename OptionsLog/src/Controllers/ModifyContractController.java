package Controllers;

import Database.ContractDatabase;
import Models.Contract;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifyContractController implements Initializable {

    @FXML private TextField stockName;
    @FXML private DatePicker strikeDate;
    @FXML private TextField strikePrice;
    @FXML private TextField quantity;
    @FXML private TextField contractId;
    @FXML private DatePicker purchaseDate;
    @FXML private TextField purchasePrice;
    @FXML private TextField totalCost;
    @FXML private TextField sellPrice;

    @FXML private RadioButton activeRadio;
    @FXML private RadioButton inactiveRadio;

    @FXML private TextArea notes;

    private Contract selectedContract;

    private Float dollarPL;
    private Float percentPL;

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML Files/DetailedView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene((Pane) loader.load()));
        stage.show();
    }

    @FXML
    void SaveContract(ActionEvent event) throws IOException {
        if (validEntries()){
            Integer active;
            if (activeRadio.isSelected()) {
                active = 1;
                dollarPL = 0.0f; // f to make it a Float
                percentPL = 0.0f;
            } else {
                active = 0;
            }

            Contract tempContract = new Contract(
                    Integer.parseInt(contractId.getText()),
                    stockName.getText(),
                    strikeDate.getValue().atStartOfDay(),
                    Float.parseFloat(strikePrice.getText()),
                    Integer.parseInt(quantity.getText()),
                    purchaseDate.getValue().atStartOfDay(),
                    active,
                    Float.parseFloat(purchasePrice.getText()),
                    Float.parseFloat(totalCost.getText()),
                    notes.getText(),
                    Float.parseFloat(sellPrice.getText()),
                    dollarPL,
                    percentPL
            );
            ContractDatabase.updateContract(tempContract);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML Files/DetailedView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene((Pane) loader.load()));
            stage.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("One or more fields were left blank.");
            alert.setContentText("Please fill all fields before saving.");
            alert.showAndWait();
        }
    }

    public void setUp(Contract contract){
        selectedContract = contract;
        stockName.setText(selectedContract.getStockName());
        strikeDate.setValue(LocalDate.from(selectedContract.getStrikeDate()));
        strikePrice.setText(String.valueOf(selectedContract.getStrikePrice()));
        quantity.setText(String.valueOf(selectedContract.getQuantity()));
        contractId.setText(String.valueOf(selectedContract.getContractId()));
        purchaseDate.setValue(LocalDate.from(selectedContract.getPurchaseDate()));
        purchasePrice.setText(String.valueOf(selectedContract.getPurchasePrice()));
        totalCost.setText(String.valueOf(selectedContract.getTotalCost()));
        notes.setText(selectedContract.getNotes());
        sellPrice.setText(String.valueOf(selectedContract.getSellPrice()));
        if (selectedContract.getActive() == 1){
            activeRadio.setSelected(true);
            sellPrice.setText("0.00");
            sellPrice.setEditable(false);
        }else{
            inactiveRadio.setSelected(true);
            sellPrice.setEditable(true);
        }
    }

    @FXML
    void calcEquity(ActionEvent event) {
        String temp = equityEquation(Integer.parseInt(quantity.getText()), Float.valueOf(purchasePrice.getText()));
        totalCost.setText(temp);
    }

    public String equityEquation(int quantity, Float price){
        int hun = 100;
        Float temp = quantity * price * hun;
        return String.valueOf(temp);
    }

    @FXML
    void enableSellPrice(ActionEvent event) {
        if (activeRadio.isSelected()){
            sellPrice.setText("0.00");
            sellPrice.setEditable(false);
        }else if (inactiveRadio.isSelected()){
            sellPrice.setEditable(true);
        }
    }

    public Boolean validEntries(){
        if (stockName.getText().isEmpty() || strikeDate.getValue().toString().isEmpty() || quantity.getText().isEmpty() ||
                contractId.getText().isEmpty() || purchaseDate.getValue().toString().isEmpty() || purchasePrice.getText().isEmpty() ||
                totalCost.getText().isEmpty() || sellPrice.getText().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup activeBool = new ToggleGroup();
        activeRadio.setToggleGroup(activeBool);
        inactiveRadio.setToggleGroup(activeBool);


    }
}
