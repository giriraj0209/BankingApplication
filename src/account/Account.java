package account;

import java.io.Serializable;

public class Account implements Serializable {

    private String firstName;
    private String lastName;
    private String active;
    private double balance;

    public Account() {
    }

    public Account(String firstName, String lastName, String active, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
