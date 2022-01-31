package Controllers;

import Database.ContractDatabase;
import Models.Contract;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class DetailedViewController implements Initializable {

    @FXML private TableView<Contract> ActiveTable;
    @FXML private TableColumn<Contract, LocalDate> ActiveDayOfMonth;
    @FXML private TableColumn<Contract, String> ActiveStock;
    @FXML private TableColumn<Contract, LocalDate> ActStrikeDate;
    @FXML private TableColumn<Contract, Float> ActStrikePrice;
    @FXML private TableColumn<Contract, Integer> ActiveQuantity;
    @FXML private TableColumn<Contract, Float> ActiveEquity;
    @FXML private TableColumn<Contract, Float> ActiveContractPrice;

    @FXML private TableView<Contract> InactiveTable;
    @FXML private TableColumn<Contract, LocalDate> InactiveDayOfMonth;
    @FXML private TableColumn<Contract, String> InactiveStock;
    @FXML private TableColumn<Contract, LocalDate> InactiveStrikeDate;
    @FXML private TableColumn<Contract, Float> InactiveStrikePrice;
    @FXML private TableColumn<Contract, Integer> InactiveQuantity;
    @FXML private TableColumn<Contract, Float> InactiveEquity;
    @FXML private TableColumn<Contract, Float> InactiveContractPrice;
    @FXML private TableColumn<Contract, Float> InactivePriceSold;
    // @FXML private TableColumn<Contract, ?> InactiveProfitLoss; Delete me?
    @FXML private TableColumn<Contract, Float> InactivePercentPL;
    @FXML private TableColumn<Contract, Float> InactiveDollarPL;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ActiveDayOfMonth.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        ActiveStock.setCellValueFactory(new PropertyValueFactory<>("stockName"));
        ActStrikeDate.setCellValueFactory(new PropertyValueFactory<>("strikeDate"));
        ActStrikePrice.setCellValueFactory(new PropertyValueFactory<>("strikePrice"));
        ActiveQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ActiveContractPrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        ActiveEquity.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        InactiveDayOfMonth.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        InactiveStock.setCellValueFactory(new PropertyValueFactory<>("stockName"));
        InactiveStrikeDate.setCellValueFactory(new PropertyValueFactory<>("strikeDate"));
        InactiveStrikePrice.setCellValueFactory(new PropertyValueFactory<>("strikePrice"));
        InactiveQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        InactiveContractPrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        InactiveEquity.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        InactivePriceSold.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        InactivePercentPL.setCellValueFactory(new PropertyValueFactory<>("percentPL"));
        InactiveDollarPL.setCellValueFactory(new PropertyValueFactory<>("dollarPL"));


        try{
            ActiveTable.setItems(ContractDatabase.getActiveContracts());
            InactiveTable.setItems(ContractDatabase.getInactiveContracts());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void AddContract(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML Files/AddContract.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene((Pane) loader.load()));
        stage.show();
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML Files/Summary.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene((Pane) loader.load()));
        stage.show();
    }

    @FXML
    void deleteContract(ActionEvent event) throws IOException {
        Contract activeContractTemp = ActiveTable.getSelectionModel().getSelectedItem();
        Contract inactiveContractTemp = InactiveTable.getSelectionModel().getSelectedItem();

        if (ActiveTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure you would like to delete this?");
            alert.setContentText("Contract ID: " + activeContractTemp.getContractId() + "\n" +
                    "Stock Name: " + activeContractTemp.getStockName() + "\n" +
                    "Strike Date: " + activeContractTemp.getStrikeDate() + "\n" +
                    "Strike Price: " + activeContractTemp.getStrikePrice() + "\n" +
                    "Purchase Price: " + activeContractTemp.getPurchasePrice());
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                ContractDatabase.deleteContract(activeContractTemp.getContractId());
            }
        } else if (InactiveTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure you would like to delete this?");
            alert.setContentText("Contract ID: " + inactiveContractTemp.getContractId() + "\n" +
                    "Stock Name: " + inactiveContractTemp.getStockName() + "\n" +
                    "Strike Date: " + inactiveContractTemp.getStrikeDate() + "\n" +
                    "Strike Price: " + inactiveContractTemp.getStrikePrice() + "\n" +
                    "Purchase Price: " + inactiveContractTemp.getPurchasePrice());
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                ContractDatabase.deleteContract(inactiveContractTemp.getContractId());
            }
        }
    }

    @FXML
    void modifyContract(ActionEvent event) throws IOException {

        if (ActiveTable.getSelectionModel().getSelectedItem() != null || InactiveTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML Files/ModifyContract.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene((Pane) loader.load()));
            ModifyContractController controller = loader.<ModifyContractController>getController();

            if (ActiveTable.getSelectionModel().getSelectedItem() != null) {
                controller.setUp(ActiveTable.getSelectionModel().getSelectedItem());
            } else if (InactiveTable.getSelectionModel().getSelectedItem() != null){
                controller.setUp(InactiveTable.getSelectionModel().getSelectedItem());
            }
            stage.show();
        }
    }

    @FXML
    void sellContract(ActionEvent event) throws IOException {
        if (ActiveTable.getSelectionModel().getSelectedItem() != null || InactiveTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML Files/SellContract.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene((Pane) loader.load()));
            SellContractController controller = loader.<SellContractController>getController();

            if (ActiveTable.getSelectionModel().getSelectedItem() != null) {
                controller.setUp(ActiveTable.getSelectionModel().getSelectedItem());
            } else if (InactiveTable.getSelectionModel().getSelectedItem() != null){
                controller.setUp(InactiveTable.getSelectionModel().getSelectedItem());
            }
            stage.show();
        }
    }
}
