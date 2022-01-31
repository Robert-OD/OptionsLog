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

public class SellContractController implements Initializable {

    @FXML private TextField stockName;
    @FXML private DatePicker strikeDate;
    @FXML private TextField strikePrice;
    @FXML private TextField quantity;
    @FXML private DatePicker purchaseDate;
    @FXML private TextField contractId;
    @FXML private TextField purchasePrice;
    @FXML private TextField equity;
    @FXML private TextField sellPrice;
    @FXML private Spinner<Integer> sellQuantity;

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
    void calcEquity(ActionEvent event) {
        String temp = equityEquation(Integer.parseInt(quantity.getText()), Float.valueOf(purchasePrice.getText()));
        equity.setText(temp);
    }

    public String equityEquation(int quantity, Float price){
        int hun = 100;
        Float temp = quantity * price * hun;
        return String.valueOf(temp);
    }

// when selling a contract with quantity N, split it into two new contracts, one active with quantity x and one inactive with quantity N-x
    @FXML
    void sellContract(ActionEvent event) throws IOException {
        // takes a contract and sell quantity to make new contracts with valid contract ids, active and inactive
        if (validEntries()) {
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
                    Float.parseFloat(equity.getText()),
                    notes.getText(),
                    Float.parseFloat(sellPrice.getText()),
                    dollarPL,
                    percentPL
            );
            ContractDatabase.sellContract(tempContract, Integer.parseInt(quantity.getText()), sellQuantity.getValue());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML Files/DetailedView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene((Pane) loader.load()));
        stage.show();
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
        equity.setText(String.valueOf(selectedContract.getTotalCost()));
        notes.setText(selectedContract.getNotes());
        sellPrice.setEditable(true);
        sellPrice.setText("0.00");
        SpinnerValueFactory<Integer> quantityValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, selectedContract.getQuantity(), 1);
        this.sellQuantity.setValueFactory(quantityValueFactory);
    }


    public Boolean validEntries(){
        if (stockName.getText().isEmpty() || strikeDate.getValue().toString().isEmpty() || quantity.getText().isEmpty() ||
                contractId.getText().isEmpty() || purchaseDate.getValue().toString().isEmpty() || purchasePrice.getText().isEmpty() ||
                equity.getText().isEmpty() || sellPrice.getText().isEmpty()){
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
        inactiveRadio.setSelected(true);
    }
}
