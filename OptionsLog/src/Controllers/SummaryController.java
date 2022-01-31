package Controllers;


import Database.ContractDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;




public class SummaryController implements Initializable {

    @FXML private Label dollarPLTxt;
    @FXML private Label percentPLTxt;
    @FXML private Label activeContractTxt;
    @FXML private Label inactiveContractsTXT;

    @FXML
    void DetailedView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML Files/DetailedView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene((Pane) loader.load()));
        stage.show();
    }

    @FXML
    void Quit(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        dollarPLTxt.setText("temp$PL");
        percentPLTxt.setText("temp%PL");
        try {
            activeContractTxt.setText(String.valueOf(ContractDatabase.getActiveContracts().size()));
            inactiveContractsTXT.setText(String.valueOf(ContractDatabase.getInactiveContracts().size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
