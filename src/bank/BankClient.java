/*Bank Client consists of the connection information related to primary and replication servers
 * Author: Giriraj Nagaraju
 * */
package bank;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.naming.Context;
import javax.naming.InitialContext;

import account.Account;
import customer.Customer;
import transaction.Transaction;

public class BankClient {

    String serverName = "";
    String portNo="";
    String primary="";
    String replication1="";
    String replication2="";
    String primary_port="";
    String replication_port="";
    String replication2_port="";
    String curr_ip="";
    public BankClient() {
try{
	Context env = (Context)new InitialContext().lookup("java:comp/env");

	// Get a single value
	primary = (String)env.lookup("primary");
	replication1=(String)env.lookup("replication"); 
	replication2=(String)env.lookup("replication2");
	primary_port=(String)env.lookup("primary_port");
	replication_port=(String)env.lookup("replication_port");
	replication2_port= (String)env.lookup("replication2_port");
	
	serverName=primary;
	portNo=primary_port;
}catch(Exception e){
	e.printStackTrace();
}
        boolean server = false;
        System.out.println("-****************-"+primary+"-************-");
        System.out.println("-****************-"+replication1+"-************-");
        System.out.println("-****************-"+replication2+"-************-");
        System.out.println("-****************-"+primary_port+"-************-");
        System.out.println("-****************-"+replication_port+"-************-");
        System.out.println("-****************-"+replication2_port+"-************-");
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            server = true;
            curr_ip=primary;
        } catch (Exception e) {
            System.out.println("BankClient exception: " + e);
            server = false;
            serverName = replication1;
            portNo = replication_port;
        }
        if (server == false) { // replication server 1
            try {
            	
                BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
                server = true;
                curr_ip=replication1;
            } catch (Exception e) {
                System.out.println("BankClient exception: " + e);
                serverName = replication2;
                portNo = replication2_port;
                server = false;
            }
        }
        if (server == false) { //replication server 2
            try {
                BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
                curr_ip=replication2;
                server = true;
            } catch (Exception e) {
                System.out.println("BankClient exception: " + e);
                server = false;
            }
        }
        gethost(curr_ip);
       
    }

    public int registerUser(String firstName, String lastName, String userId, String password) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.registerUser(firstName, lastName, userId, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 100;// Remote Exception
        }

    }

    public int login(String userId, String password) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.login(userId, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 100;// Remote Exception
        }
    }

    public int createCustomer(String firstName, String lastName) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.createCustomer(firstName, lastName);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 100;// Remote Exception
        }

    }

    public ArrayList<Customer> findCustomers() {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.findCustomers();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Customer findCustomer(String custId) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.findCustomer(custId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int updateCustomer(String custId, String firstName, String lastName) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.updateCustomer(custId, firstName, lastName);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 2;
        }
    }

    public int createAccount(String custId) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.createAccount(custId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 2;
        }
    }

    public int depositAccount(String custId, double amount) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.depositAccount(custId, amount);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 2;
        }
    }

    public int withdrawAccount(String custId, double amount) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.withdrawAccount(custId, amount);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 3;
        }
    }

    public int transferAmount(String custId1, String custId2, double amount) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.transferAmount(custId1, custId2, amount);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 3;
        }
    }

    public ArrayList<Transaction> transactionDetails() {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.transactionDetails();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Account> accountDetails() {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.accountDetails();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int closeAccount(String custId) throws RemoteException {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.closeAccount(custId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 1;
        }
    }
    
    public String gethost(String curr_ip) {
        try {
            BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
            return bankInterface.gethost(curr_ip);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;// Remote Exception
        }
    }
}
