package SysnovaFeed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
import java.sql.*;
import javax.swing.JOptionPane;
import org.h2.tools.Server;
public class h2JDBCConnection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/feedDatabase;AUTO_SERVER=TRUE";
    //  Database credentials
    static final String USER = "feed";
    static final String PASS = "password";
    static private Server server;
    static Connection con=null;
    public static Connection h2JDBCConnection()
    {
        if (con != null) return con;
        // get db, user, pass from settings file
        return Connection();
    }

    private static Connection Connection() {
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.h2.Driver");
            try
            {
                server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
            }catch(Exception e){
                
            }
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println(""+se.getMessage());
             JOptionPane.showMessageDialog(null, ""+se.getMessage(), "User Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            //Handle errors for Class.forName
            JOptionPane.showMessageDialog(null, ""+e.getMessage(), "User Error", JOptionPane.ERROR_MESSAGE);

        } 
         return conn;  
    } // end main
}
