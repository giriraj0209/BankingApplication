/*Database controller class consists of all Database Related methods
 * Author: Anand Amit Kumar
 * 
 * */
package database;

import account.Account;
import customer.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transaction.Transaction;

public class DatabaseController {

    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    public int registerUser(String firstName, String lastName, String userId, String password) {
        int intRetVal = 0;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            StringBuffer f = new StringBuffer();

            f.append("");
            f.append(" INSERT INTO TELLER  \n");
            f.append("( FIRSTNAME,LASTNAME,USERID,PASSWORD) VALUES \n");
            f.append("( '" + firstName + "','" + lastName + "','" + userId + "','" + password + "') \n");
            System.out.println(f.toString());
            Statement statement = conn.createStatement();
            statement.execute(f.toString());
            System.out.println(f.toString());
            conn.commit();
            System.out.println("Registration Sucessfull !!!");
        } catch (Exception ex) {
            ex.printStackTrace();
            intRetVal = 2;// ERROR
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }

        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return intRetVal;

    }

    public int createCustomer(String firstName, String lastName) {
        int intRetVal = 0;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String query = "";

            query += " insert into customer ( firstname,lastname) values"
                    + " ( '" + firstName + "','" + lastName + "') ";
            System.out.println(query);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            intRetVal = 2;// ERROR
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }

        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return intRetVal;

    }

    public ArrayList<Customer> findCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String query = "";

            query += " select * from customer ";
            System.out.println(query);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustId(rs.getInt("custid"));
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customers.add(customer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }
        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return customers;
    }

    public Customer findCustomer(String custId) {
        Customer customer = new Customer();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String query = "";

            query += " select * from customer where custId =" + custId;

            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                customer.setCustId(rs.getInt("custid"));
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }
        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return customer;
    }

    public int updateCustomer(String custId, String firstName, String lastName) {
        int intRetVal = 0;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String query = "";

            query += " update customer set firstname = '" + firstName + "' ,lastname = '" + lastName + "' "
                    + " where custId = " + custId;
            System.out.println(query);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            intRetVal = 2;// ERROR
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }

        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return intRetVal;

    }

    public int createAccount(String custId) {
        int intRetVal = 0;
        Connection conn = null;
        try {
        	System.out.println("Create Account:");
            conn = DBUtil.getConnection();
            String query = "";

            query = "Select custId from account where custId=" + custId;
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                return 1;// Account already exist
            } else {
                query = " insert into account ( custId,balance) values"
                        + " ( " + custId + ",0.0) ";

                statement.executeUpdate(query);

                conn.commit();
                return 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            intRetVal = 2;// ERROR
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }

        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return intRetVal;

    }

    public int depositAccount(String custId, double amount) {
        int intRetVal = 0;
        Connection conn = null;
        try {
        	System.out.println("Deposit ");
            conn = DBUtil.getConnection();
            String query = "";

            query = "Select custId from account where custId=" + custId;
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                query = " update account set balance = balance + " + amount + " where custId=" + custId;
                statement.executeUpdate(query);
                conn.commit();
                return 0; // Deposited successfully

            } else {
                return 1;// Account does not exist
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            intRetVal = 2;// ERROR
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }

        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return intRetVal;

    }

    public int withdrawAccount(String custId, double amount) {
        int intRetVal = 0;
        Connection conn = null;
        try {
        	System.out.println("Withdraw Amount");
            conn = DBUtil.getConnection();
            String query = "";

            query = "Select custId, balance from account where custId=" + custId;
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {

                double balance = rs.getDouble("balance");
                System.out.println("****"+balance);
                if (balance < amount) {
                    return 2;// Insufficient balance
                } else {
                    query = " update account set balance = balance - " + amount + " where custId=" + custId;
                    statement.executeUpdate(query);
                    conn.commit();
                    return 0; // Withdraw successfully
                }

            } else {
                return 1;// Account does not exist
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            intRetVal = 3;// ERROR
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }

        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return intRetVal;

    }

    public int isValidUserId(String userID) {
        Connection conn = null;
        int valid = 0;
        try {

            conn = DBUtil.getConnection();
            if (conn != null) {
                stmt = conn.createStatement();
            } else {
                System.out.println("isValidUserId Valid connection is invalid");
            }
           
            StringBuffer sf = new StringBuffer();
            sf.append("");
            sf.append("SELECT USERID         \n");
            sf.append("FROM TELLER        \n");
            sf.append("WHERE USERID =? \n");

            pstmt = DBUtil.prepareStatement(conn, sf.toString());
            //pstmt = conn.prepareStatement(sf.toString());
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();

            if (rs.next() == false) {
                valid = 0;//user 
            } else {
                valid = 1;// user exist
            }

        } catch (Exception ex) {
            System.out.println("Exception:" + ex);
        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return valid;
    }

    public int login(String userID, String password) {
        Connection conn = null;
        int valid = 0;
        try {

            conn = DBUtil.getConnection();
          

            if (conn != null) {
                stmt = conn.createStatement();
            } else {
                System.out.println("Connection Exception!");
            }
           
            StringBuffer sf = new StringBuffer();
            sf.append("");
            sf.append("SELECT USERID         \n");
            sf.append("FROM TELLER        \n");
            sf.append("WHERE USERID =? \n");
            sf.append("AND PASSWORD =? \n");

            pstmt = DBUtil.prepareStatement(conn, sf.toString());
           
            pstmt.setString(1, userID);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next() == false) {
                valid = 0;//user does not exist
            } else {
                valid = 1;// user exist
            }

            System.out.println("Login Sucessful.User Authentication is valid. Login Id:" + valid);
        } catch (Exception ex) {
            System.out.println("Exception:" + ex);
        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return valid;
    }

    /**
     *
     * @param custId1
     * @param custId2
     * @param amount
     * @return
     */
    public int transferAmount(String custId1, String custId2, double amount) {
        //ERROR CODE 0 = Successful Transaction
        //ERROR CODE 1 = Customer 1 account does not exist
        //ERROR CODE 2 = Customer 2 account does not exist
        //ERROR CODE 3 = Insufficient balance
        //ERROR CODE 4 = Exception
        Connection conn = null;
        int accountId = 0;
        int transferToAccountId = 0;
        try {
            conn = DBUtil.getConnection();

            String query = "Select accountId from account where custId=" + custId1;
            System.out.println(query);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                accountId = rs.getInt("accountId");

                query = "Select accountId from account where custId=" + custId2;
                System.out.println(query);
                rs = stmt.executeQuery(query);

                if (rs.next()) {
                    transferToAccountId = rs.getInt("accountId");

                    query = "Select balance from account where custId=" + custId1;
                    System.out.println(query);
                    rs = stmt.executeQuery(query);
                    rs.next();
                    double balance = rs.getDouble("balance");
                    if (amount <= balance) {
                        query = "Update account set balance = balance - " + amount + " where custId=" + custId1;
                        System.out.println(query);
                        stmt.executeUpdate(query);
                        query = "Update account set balance = balance + " + amount + " where custId=" + custId2;
                        System.out.println(query);
                        stmt.executeUpdate(query);

                        query = "Insert into transaction (accountId, transDate, transType, transferToAccountId, transAmount) "
                                + "VALUES ("
                                + "" + accountId + ","
                                + "now(),"
                                + "0,"
                                + "" + transferToAccountId + ","
                                + "" + amount + ""
                                + ")";
                        System.out.println(query);
                        stmt.executeUpdate(query);

                    } else {
                        return 3;
                    }
                } else {
                    return 2;
                }
            } else {
                return 1;
            }
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception:" + ex);
            return 4;
        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return 0;
    }


    public ArrayList<Transaction> transactionDetails() {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String query = "";

            query += " Select customer.firstName as TransferrerFN,customer.lastName as TransferrerLN, "
                    + "customer.firstName as TransfereeFN,customer.lastName as TransfereeLN, "
                    + "transaction.transDate, "
                    + "transaction.transAmount "
                    + "from customer,transaction,account "
                    + "where transaction.accountId = account.accountId "
                    + "and account.custId = customer.custId ";

            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransferer(rs.getString("TransferrerFN") + " " + rs.getString("TransferrerLN"));
                //transaction.setTransferee(rs.getString("TransfereeFN") + " " + rs.getString("TransfereeLN"));
                transaction.setTransferDate(rs.getString("transDate"));
                transaction.setTransAmount(rs.getDouble("transAmount"));
                transactions.add(transaction);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }
        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return transactions;
    }

    public ArrayList<Account> accountDetails() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String query = "";

            query += " Select customer.firstName ,customer.lastName , "
                    + "account.isActive, "
                    + "account.balance "
                    + "from customer,account "
                    + "where  "
                    + "account.custId = customer.custId ";

            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Account account = new Account();
                account.setFirstName(rs.getString("firstName"));
                account.setLastName(rs.getString("lastName"));
                int status = rs.getInt("isActive");
                if (status == 0) {
                    account.setActive("Inactive");
                } else {
                    account.setActive("Active");
                }
                account.setBalance(rs.getDouble("balance"));
                accounts.add(account);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }
        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return accounts;
    }

    public int closeAccount(String custId) {
        int intRetVal = 0;
        Connection conn = null;
        try {
        	System.out.println("Close Account");
            conn = DBUtil.getConnection();
            String query = "";

            query = "update account set isActive = 0 where custId=" + custId;
            Statement statement = conn.createStatement();

            statement.executeUpdate(query);
            conn.commit();
            return 0; // Closed

        } catch (Exception ex) {
            ex.printStackTrace();
            intRetVal = 1;// ERROR
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }

        } finally {
            DBUtil.closeConnection(conn, pstmt, rs);
        }
        return intRetVal;
    }
}
