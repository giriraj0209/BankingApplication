package bank;

import java.rmi.Naming;

class ReplicationInfo implements Runnable {
	   private Thread t;
	   private String threadName;
	   private int rep1error;
	   
	   ReplicationInfo( String name){
	       threadName = name;
	   }
	   public void run() {
	      System.out.println("Running " +  threadName );
	      while(true) {
	      try {
	    	  String serverName="localhost";
	    	  String portNo="1099";
	    	  BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
	          // Let the thread sleep for a while.
	          Thread.sleep(3000);
	        }catch(InterruptedException e){
	       }catch (Exception e) {
	    	   try {
	    		   String serverName="192.168.0.14";
	 	    	   String portNo="2020";
	               BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
	               bankInterface.printalertrep1();
	               Thread.sleep(3000);
	    	   }catch(Exception ex){
	    		   rep1error=10;
	    	   }
	    	   try{
	    		   if(rep1error==10){
	    			   System.out.println("Server 2 is down");
	    		   }
	               String serverName="192.168.0.12";
	               String portNo="3030";
	               BankInterface bankInterface = (BankInterface) Naming.lookup("//" + serverName + ":" + portNo + "/Bank");
	               bankInterface.printalertrep1();
	               Thread.sleep(3000);
	           } catch (Exception ex) {
	               ex.printStackTrace();
	               
	           }
	        
	     }
	    
	   }
	   }
	   
	   public void start ()
	   {
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }

	}

	public class BroadcastServer {
	   public static void main(String args[]) {
		   ReplicationInfo R2 = new ReplicationInfo( "checkingStatus");
	      R2.start();
	   }   
	}
	


