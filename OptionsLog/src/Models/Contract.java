package Models;

import java.time.LocalDateTime;

public class Contract {
    private Integer contractId;
    private String stockName;
    private LocalDateTime strikeDate;
    private Float strikePrice;
    private Integer quantity;
    private LocalDateTime purchaseDate;
    private Integer active;
    private Float purchasePrice;
    private Float totalCost;
    private String notes;
    private Float sellPrice;
    private Float dollarPL;
    private Float percentPL;

    public Contract(Integer contractId, String stockName, LocalDateTime strikeDate, Float strikePrice, Integer quantity, LocalDateTime purchaseDate, Integer active, Float purchasePrice, Float totalCost, String notes, Float sellPrice, Float dollarPL, Float percentPL){
        this.contractId = contractId;
        this.stockName = stockName;
        this.strikeDate = strikeDate;
        this.strikePrice = strikePrice;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.active = active;
        this.purchasePrice = purchasePrice;
        this.totalCost = totalCost;
        this.notes = notes;
        this.sellPrice = sellPrice;
        this.dollarPL = dollarPL;
        this.percentPL = percentPL;
    }

    public Integer getContractId() {
        return contractId;
    }
    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getStockName() {
        return stockName;
    }
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public LocalDateTime getStrikeDate() {
        return strikeDate;
    }
    public void setStrikeDate(LocalDateTime strikeDate) {
        this.strikeDate = strikeDate;
    }

    public Float getStrikePrice() {
        return strikePrice;
    }
    public void setStrikePrice(Float strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getActive() {
        return active;
    }
    public void setActive(Integer active) {
        this.active = active;
    }

    public Float getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Float getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Float getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Float getDollarPL() {
        return dollarPL;
    }
    public void setDollarPL(Float dollarPL) {
        this.dollarPL = dollarPL;
    }

    public Float getPercentPL() {
        return percentPL;
    }
    public void setPercentPL(Float percentPL) {
        this.percentPL = percentPL;
    }
}
