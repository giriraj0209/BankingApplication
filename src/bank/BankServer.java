/*Bank Server has the bank instance creation method, which creates bank instance for 
 * primary and replication server
 * Author: Giriraj Nagaraju
 * */
package bank;

import java.net.InetAddress;
import java.rmi.registry.Registry;

public class BankServer {

    public static void main(String[] args) {
        try {
            int port = 1099;
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
                port = 1099;
            }
            InetAddress IP=InetAddress.getLocalHost();
            System.out.println(InetAddress.getLocalHost());
            System.out.println("IP of my system is := "+IP.getHostAddress());
            System.setProperty( "java.rmi.server.hostname",IP.getHostAddress() );
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(port);
            Bank bank = new Bank();
            r.rebind("Bank", bank);
            System.out.println("Server is connected and ready for operation.");
        } catch (Exception e) {
            System.out.println("Server not connected: " + e);
        }
    }
}
