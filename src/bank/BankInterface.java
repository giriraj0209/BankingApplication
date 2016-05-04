/*Interface has all the methods which are in contract with the server for implementation
 * Author: Giriraj Nagaraju  
 * */
package bank;

import account.Account;
import customer.Customer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import transaction.Transaction;

public interface BankInterface extends Remote {

    public int registerUser(String firstName, String lastName, String userId, String password) throws RemoteException;

    public int login(String userId, String password) throws RemoteException;

    public int createCustomer(String firstName, String lastName) throws RemoteException;

    public ArrayList<Customer> findCustomers() throws RemoteException;

    public Customer findCustomer(String custId) throws RemoteException;

    public int updateCustomer(String custId, String firstName, String lastName) throws RemoteException;

    public int createAccount(String custId) throws RemoteException;

    public int depositAccount(String custId, double amount) throws RemoteException;

    public int withdrawAccount(String custId, double amount) throws RemoteException;

    public int transferAmount(String custId1, String custId2, double amount) throws RemoteException;

    public ArrayList<Transaction> transactionDetails() throws RemoteException;
    
    public ArrayList<Account> accountDetails()throws RemoteException;
    
    public int closeAccount(String custId) throws RemoteException;
    public String gethost(String curr_ip) throws RemoteException;
    public void printalertrep1() throws RemoteException;
}
