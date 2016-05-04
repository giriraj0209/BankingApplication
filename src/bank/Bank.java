/* Bank class consists of implementation of all bank account related actions.
 * Author: Giriraj Nagaraju
 * */
package bank;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import account.Account;
import customer.Customer;
import database.DatabaseController;
import transaction.Transaction;

public class Bank extends UnicastRemoteObject implements BankInterface {
public static final String PRIMARY = "172.20.10.7";
public static final String REPLICATION1 = "172.20.10.14";
public static final String REPLICATION2 = "172.20.10.2";

    public Bank() throws RemoteException {
    
    }

    public int registerUser(String firstName, String lastName, String userId, String password) throws RemoteException {

        int intReturnValue = 0;
        DatabaseController controller = new DatabaseController();

        int intUserValid = controller.isValidUserId(userId);
        if (intUserValid == 0) {
            intReturnValue = controller.registerUser(firstName, lastName, userId, password);
        } else if (intUserValid == 1) {
            intReturnValue = 1;
        }
        return intReturnValue;
    }

    public int login(String userId, String password) throws RemoteException {
        DatabaseController controller = new DatabaseController();
        return controller.login(userId, password);

    }

    public int createCustomer(String firstName, String lastName) throws RemoteException {
        DatabaseController controller = new DatabaseController();
        return controller.createCustomer(firstName, lastName);
    }

    public ArrayList<Customer> findCustomers() throws RemoteException {
        DatabaseController controller = new DatabaseController();
        return controller.findCustomers();
    }

    public Customer findCustomer(String custId) throws RemoteException {
        DatabaseController controller = new DatabaseController();
        return controller.findCustomer(custId);
    }
    
    public int updateCustomer(String custId,String firstName, String lastName)throws RemoteException{
        DatabaseController controller = new DatabaseController();
        return controller.updateCustomer(custId, firstName, lastName);
    }
    
    public int createAccount(String custId) throws RemoteException{
        DatabaseController controller = new DatabaseController();
        return controller.createAccount(custId);
    }
    
    public int depositAccount(String custId, double amount) throws RemoteException{
        DatabaseController controller = new DatabaseController();
        return controller.depositAccount(custId, amount);
    }

    public int withdrawAccount(String custId, double amount) throws RemoteException{
        DatabaseController controller = new DatabaseController();
        return controller.withdrawAccount(custId, amount);
    }
    
    public int transferAmount(String custId1, String custId2, double amount) throws RemoteException{
        DatabaseController controller = new DatabaseController();
        return controller.transferAmount(custId1, custId2, amount);
    }
    
    public ArrayList<Transaction> transactionDetails() throws RemoteException{
        DatabaseController controller = new DatabaseController();
        return controller.transactionDetails();
    }
    
    public ArrayList<Account> accountDetails()throws RemoteException{
        DatabaseController controller = new DatabaseController();
        return controller.accountDetails();
    }
    
    public int closeAccount(String custId) throws RemoteException{
        DatabaseController controller = new DatabaseController();
        return controller.closeAccount(custId);
    }
    public String gethost(String curr_ip){
    		
    	try{
    		//String ipaddress= InetAddress.getLocalHost().getHostAddress();
    		String ipaddress=curr_ip;
    		System.out.println("Current IP ADDRESS: " + ipaddress); 
    		if(ipaddress.equals(PRIMARY)){
    	        	System.out.println("Primary Server is connected");
    	        }
    	        else if(ipaddress.equals(REPLICATION1)){
    	        	System.out.println("Primary server is Down... Replication Server one is Connected");
    	        }
    	        else if(ipaddress.equals(REPLICATION2)){
    	        	System.out.println("Primary server is Down... Replication Server two is Connected");
    	        }
    	        else{
    	        	System.out.println("testing from "+ipaddress);
    	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "";
    }

	@Override
	public void printalertrep1() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Primary Down!!!");
		
	}
}
