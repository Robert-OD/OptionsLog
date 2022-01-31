import Database.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// TO DO:
// fix dollarPL and percentPL
// fix selling sold contracts
// fix sell function in ContractDatabase
// optimize SQL database table shit


// LONG TERM?
// build indicators and an option to look at a stock/ stock graph in real time




public class Main extends Application {

    public static void main(String[] args) {
        DatabaseConnector.startConnection();
        launch(args);
        DatabaseConnector.closeConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML Files/Summary.fxml"));
        primaryStage.setTitle("Options Log");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



}
