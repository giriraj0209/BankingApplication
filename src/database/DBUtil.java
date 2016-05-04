/*DBUtil Class consist of utiltiy methods for Database integration with Application
 * Author: Anand Amith Kumar 
 * */
package database;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;

public class DBUtil {

    static final BasicDataSource DBSRC = initDB();
    static final int SQL_TIMEOUT = 10;

    static BasicDataSource initDB() {
        String strClassName = "com.mysql.jdbc.Driver";
        String strUrl = "jdbc:mysql://192.168.1.105:3306/banking";
        String strUser = "root";
        String strPassword = "root";
        int intMax = 20000;
        int intMin = 10000;
        int intWaiting = 500;

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(strClassName);
        ds.setUrl(strUrl);
        ds.setUsername(strUser);
        ds.setPassword(strPassword);
        ds.setMaxActive(intMax);
        ds.setMinIdle(intMin);
        ds.setMaxWait(intWaiting);
        ds.setMaxWait(intWaiting);
        ds.setDefaultAutoCommit(false);
        ds.setPoolPreparedStatements(true);
        //logger.info("DBMS Connection Pool has been created");
        return ds;
    }

    public void setSQLTimeOut(PreparedStatement pstmt, int intTimeOutTime) throws SQLException {
        pstmt.setQueryTimeout(intTimeOutTime);
    }

    public static PreparedStatement prepareStatement(Connection conn, String strQuery) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setQueryTimeout(SQL_TIMEOUT);
        return pstmt;
    }

    public static Connection getConnection() throws SQLException {
        try {

            Connection con = DBSRC.getConnection();
            return con;
        } catch (SQLException e) {
            System.out.println("Exception in getConnection " + e);
            throw e;
        }
    }

    static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rset) {
        if (null != rset) {
            try {
                rset.close();
            } catch (SQLException e) {

                System.out.println("Error in close connection " + e);

            }
        }
        if (null != stmt) {
            try {
                stmt.close();
            } catch (SQLException e) {

                System.out.println("Error in close connection " + e);

            }
        }
        if (null != con) {
            try {
                con.close();
            } catch (SQLException e) {

            }
        }

    }

}
