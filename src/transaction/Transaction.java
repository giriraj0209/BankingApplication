package transaction;

import java.io.Serializable;

public class Transaction implements Serializable {

    private String Transferer;
    private String Transferee;
    private String transferDate;
    private double transAmount;

    public Transaction() {
    }

    public Transaction(String Transferer, String Transferee, String transferDate, double transAmount) {
        this.Transferer = Transferer;
        this.Transferee = Transferee;
        this.transferDate = transferDate;
        this.transAmount = transAmount;
    }

    public String getTransferer() {
        return Transferer;
    }

    public void setTransferer(String Transferer) {
        this.Transferer = Transferer;
    }

    public String getTransferee() {
        return Transferee;
    }

    public void setTransferee(String Transferee) {
        this.Transferee = Transferee;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(double transAmount) {
        this.transAmount = transAmount;
    }

}
