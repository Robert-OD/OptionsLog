package Database;

import Models.Contract;
import Utils.TimeDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ContractDatabase {

    public static Contract getContract(int contractIdIn) throws SQLException {
        String sql = "SELECT * FROM Contracts WHERE contractId = " + contractIdIn;
        Query.makeQuery(sql);
        Contract userResult;
        ResultSet resultSet = Query.getResult();
        while (resultSet.next()){
            int contractId = resultSet.getInt("contractId");
            String stockName = resultSet.getString("stockName");
            LocalDateTime strikeDate = TimeDateFormat.stringToLocalDateTime(resultSet.getString("strikeDate"));
            Float strikePrice = resultSet.getFloat("strikePrice");
            int quantity = resultSet.getInt("quantity");
            LocalDateTime purchaseDate = TimeDateFormat.stringToLocalDateTime(resultSet.getString("purchaseDate"));
            int active = resultSet.getInt("active");
            Float purchasePrice = resultSet.getFloat("purchasePrice");
            Float totalCost = (purchasePrice * quantity * 100);
            String notes = resultSet.getString("notes");

            Float sellPrice = resultSet.getFloat("sellPrice");
            Float dollarPL = 0.0f;
            Float percentPL = 0.0f;

            userResult = new Contract(contractId, stockName, strikeDate, strikePrice, quantity, purchaseDate, active, purchasePrice, totalCost, notes, sellPrice, dollarPL, percentPL);
            return userResult;
        }
        return null;
    }

    public static ObservableList<Contract> getAllContracts() throws SQLException {
        ObservableList<Contract> contractList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Contracts";
        Query.makeQuery(sql);
        Contract userResult;
        ResultSet resultSet = Query.getResult();
        while (resultSet.next()){
            int contractId = resultSet.getInt("contractId");
            String stockName = resultSet.getString("stockName");
            LocalDateTime strikeDate = TimeDateFormat.stringToLocalDateTime(resultSet.getString("strikeDate"));
            Float strikePrice = resultSet.getFloat("strikePrice");
            int quantity = resultSet.getInt("quantity");
            LocalDateTime purchaseDate = TimeDateFormat.stringToLocalDateTime(resultSet.getString("purchaseDate"));
            int active = resultSet.getInt("active");
            Float purchasePrice = resultSet.getFloat("purchasePrice");
            Float totalCost = (purchasePrice * quantity * 100);
            String notes = resultSet.getString("notes");

            Float sellPrice = resultSet.getFloat("sellPrice");
            Float dollarPL = 0.0f;
            Float percentPL = 0.0f;

            userResult = new Contract(contractId, stockName, strikeDate, strikePrice, quantity, purchaseDate, active, purchasePrice, totalCost, notes, sellPrice, dollarPL, percentPL);
            contractList.add(userResult);
        }
        return contractList;
    }

    public static ObservableList<Contract> getActiveContracts() throws SQLException {
        ObservableList<Contract> contractList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Contracts";
        Query.makeQuery(sql);
        Contract userResult;
        ResultSet resultSet = Query.getResult();
        while (resultSet.next()){
            int contractId = resultSet.getInt("contractId");
            String stockName = resultSet.getString("stockName");
            LocalDateTime strikeDate = TimeDateFormat.stringToLocalDateTime(resultSet.getString("strikeDate"));
            Float strikePrice = resultSet.getFloat("strikePrice");
            int quantity = resultSet.getInt("quantity");
            LocalDateTime purchaseDate = TimeDateFormat.stringToLocalDateTime(resultSet.getString("purchaseDate"));
            int active = resultSet.getInt("active");
            Float purchasePrice = resultSet.getFloat("purchasePrice");
            Float totalCost = (purchasePrice * quantity * 100);
            String notes = resultSet.getString("notes");

            Float sellPrice = resultSet.getFloat("sellPrice");
            Float dollarPL = 0.0f;
            Float percentPL = 0.0f;

            userResult = new Contract(contractId, stockName, strikeDate, strikePrice, quantity, purchaseDate, active, purchasePrice, totalCost, notes, sellPrice, dollarPL, percentPL);
            if(active == 1){
                contractList.add(userResult);
            }
        }
        return contractList;
    }

    public static ObservableList<Contract> getInactiveContracts() throws SQLException {
        ObservableList<Contract> contractList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Contracts";
        Query.makeQuery(sql);
        Contract userResult;
        ResultSet resultSet = Query.getResult();
        while (resultSet.next()){
            int contractId = resultSet.getInt("contractId");
            String stockName = resultSet.getString("stockName");
            LocalDateTime strikeDate = TimeDateFormat.stringToLocalDateTime(resultSet.getString("strikeDate"));
            Float strikePrice = resultSet.getFloat("strikePrice");
            int quantity = resultSet.getInt("quantity");
            LocalDateTime purchaseDate = TimeDateFormat.stringToLocalDateTime(resultSet.getString("purchaseDate"));
            int active = resultSet.getInt("active");
            Float purchasePrice = resultSet.getFloat("purchasePrice");
            Float totalCost = (purchasePrice * quantity * 100);
            String notes = resultSet.getString("notes");

            Float sellPrice = resultSet.getFloat("sellPrice");
            Float dollarPL = (totalCost - sellPrice) * quantity;
            Float percentPL = (dollarPL/totalCost) * 100;

            userResult = new Contract(contractId, stockName, strikeDate, strikePrice, quantity, purchaseDate, active, purchasePrice, totalCost, notes, sellPrice, dollarPL, percentPL);
            if(active == 0){
                contractList.add(userResult);
            }
        }
        return contractList;
    }

    public static void deleteContract(int contractId){
        String sql = "DELETE FROM Contracts WHERE contractId = " + contractId + ";";
        Query.makeQuery(sql);
    }

    public static void updateContract(Contract contractIn){
        int contractId = contractIn.getContractId();
        String stockName = contractIn.getStockName();
        LocalDateTime strikeDate = contractIn.getStrikeDate();
        Float strikePrice = contractIn.getStrikePrice();
        int quantity = contractIn.getQuantity();
        LocalDateTime purchaseDate = contractIn.getPurchaseDate();
        int active = contractIn.getActive();
        Float purchasePrice = contractIn.getPurchasePrice();
        Float totalCost = (purchasePrice * quantity * 100);
        CharSequence notes = contractIn.getNotes();
        Float sellPrice = contractIn.getSellPrice();

        String sql = "INSERT INTO Contracts (contractId, stockName, strikeDate, strikePrice, quantity, purchaseDate, active, purchasePrice, equity, notes, sellPrice) " +
                "VALUES (" + "'" + contractId + "', '" + stockName + "', '" + strikeDate + "', '" + strikePrice + "', '" + quantity +
                "', '" + purchaseDate + "', " + active + ", '" + purchasePrice + "', '" + totalCost + "', '" + notes + "', '" + sellPrice + "')" +
                " ON DUPLICATE KEY UPDATE " +
                "stockName = '" + stockName + "', strikeDate = '" + strikeDate + "', strikePrice = '" + strikePrice + "', " +
                "quantity = '" + quantity + "', purchaseDate = '" + purchaseDate + "', active = '" + active + "', purchasePrice = '" +
                purchasePrice + "', equity = '" + totalCost + "', notes = '" + notes + "', sellPrice = '" + sellPrice + "';";

        Query.makeQuery(sql);
    }

    public static void addContract(Contract contractIn){
        int contractId = contractIn.getContractId();
        String stockName = contractIn.getStockName();
        LocalDateTime strikeDate = contractIn.getStrikeDate();
        Float strikePrice = contractIn.getStrikePrice();
        int quantity = contractIn.getQuantity();
        LocalDateTime purchaseDate = contractIn.getPurchaseDate();
        int active = contractIn.getActive();
        Float purchasePrice = contractIn.getPurchasePrice();
        Float totalCost = (purchasePrice * quantity * 100);
        String notes = contractIn.getNotes();
        Float sellPrice = contractIn.getSellPrice();
        // single quotes (') required

        String sql = "INSERT INTO Contracts (contractId, stockName, strikeDate, strikePrice, quantity, purchaseDate, active, purchasePrice, equity, notes, sellPrice) " +
                "VALUES (" + "'" + contractId + "', '" + stockName + "', '" + strikeDate + "', '" + strikePrice + "', '" + quantity +
                "', '" + purchaseDate + "', " + active + ", '" + purchasePrice + "', " + totalCost + ", '" + notes + "', '" + sellPrice + "')" +
                " ON DUPLICATE KEY UPDATE " + "contractId = '" + (contractId + 1) + "', " +
                "stockName = '" + stockName + "', strikeDate = '" + strikeDate + "', strikePrice = '" + strikePrice + "', " +
                "quantity = '" + quantity + "', purchaseDate = '" + purchaseDate + "', active = '" + active + "', purchasePrice = '" +
                purchasePrice + "', equity = '" + totalCost + "', notes = '" + notes + "', sellPrice = '" + sellPrice + "';";
        Query.makeQuery(sql);
    }

    public static void sellContract(Contract contractIn, int maxQuantity, int sellQuantity){

        int contractId = contractIn.getContractId();
        String stockName = contractIn.getStockName();
        LocalDateTime strikeDate = contractIn.getStrikeDate();
        Float strikePrice = contractIn.getStrikePrice();
        int quantity = contractIn.getQuantity();
        LocalDateTime purchaseDate = contractIn.getPurchaseDate();
        int active = contractIn.getActive();
        Float purchasePrice = contractIn.getPurchasePrice();
        Float totalCost = (purchasePrice * quantity * 100);
        String notes = contractIn.getNotes();
        Float sellPrice = contractIn.getSellPrice();

        // if selling all, just update contract to inactive
        if (maxQuantity == sellQuantity){
            String sql = "INSERT INTO Contracts (contractId, stockName, strikeDate, strikePrice, quantity, purchaseDate, active, purchasePrice, equity, notes, sellPrice) " +
                    "VALUES (" + "'" + contractId + "', '" + stockName + "', '" + strikeDate + "', '" + strikePrice + "', '" + quantity +
                    "', '" + purchaseDate + "', " + active + ", '" + purchasePrice + "', '" + totalCost + "', '" + notes + "', '" + sellPrice + "')" +
                    " ON DUPLICATE KEY UPDATE " + "contractId = '" + (contractId + 2) + "', " +
                    "stockName = '" + stockName + "', strikeDate = '" + strikeDate + "', strikePrice = '" + strikePrice + "', " +
                    "quantity = '" + quantity + "', purchaseDate = '" + purchaseDate + "', active = '" + active + "', purchasePrice = '" +
                    purchasePrice + "', equity = '" + totalCost + "', notes = '" + notes + "', sellPrice = '" + sellPrice + "';";
            Query.makeQuery(sql);
            // otherwise just make two new contracts, one active one inactive with the quantity as the difference
        }else{
            int newQuantity = maxQuantity - sellQuantity;
            String newSQL = "INSERT INTO Contracts (contractId, stockName, strikeDate, strikePrice, quantity, purchaseDate, active, purchasePrice, equity, notes, sellPrice) " +
                    "VALUES (" + "'" + contractId + "', '" + stockName + "', '" + strikeDate + "', '" + strikePrice + "', '" + newQuantity +
                    "', '" + purchaseDate + "', " + active + ", '" + purchasePrice + "', " + totalCost + ", '" + notes + "', '" + sellPrice + "')" +
                    " ON DUPLICATE KEY UPDATE " + "contractId = '" + (contractId + 1) + "', " +
                    "stockName = '" + stockName + "', strikeDate = '" + strikeDate + "', strikePrice = '" + strikePrice + "', " +
                    "quantity = '" + newQuantity + "', purchaseDate = '" + purchaseDate + "', active = '" + 1 + "', purchasePrice = '" +
                    purchasePrice + "', equity = '" + totalCost + "', notes = '" + notes + "', sellPrice = '" + sellPrice + "';";
            Query.makeQuery(newSQL);

            String soldSQL = "INSERT INTO Contracts (contractId, stockName, strikeDate, strikePrice, quantity, purchaseDate, active, purchasePrice, equity, notes, sellPrice) " +
                    "VALUES (" + "'" + contractId + "', '" + stockName + "', '" + strikeDate + "', '" + strikePrice + "', '" + (sellQuantity) +
                    "', '" + purchaseDate + "', " + active + ", '" + purchasePrice + "', " + totalCost + ", '" + notes + "', '" + sellPrice + "')" +
                    " ON DUPLICATE KEY UPDATE " + "contractId = '" + (contractId + 1) + "', " +
                    "stockName = '" + stockName + "', strikeDate = '" + strikeDate + "', strikePrice = '" + strikePrice + "', " +
                    "quantity = '" + quantity + "', purchaseDate = '" + purchaseDate + "', active = '" + active + "', purchasePrice = '" +
                    purchasePrice + "', equity = '" + totalCost + "', notes = '" + notes + "', sellPrice = '" + sellPrice + "';";

            Query.makeQuery(soldSQL);
        }
    }


}
