package commands.economy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    final static String url = "jdbc:sqlite:JADIB/src/main/java/commands/economy/";

    /**
     * @param db the database name ending in .db to connect to
     * if there is no db file, it will create one.
     * @return conn the SQLite db connection
     */
    public static Connection connect(String db) {
        Connection conn = null;
        File location = new File(url.substring(12) + db);
        try {
            if (!location.exists()) {
                createDb(db);
            }
            conn = DriverManager.getConnection(url + db);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * @param db the name of db to create
     */
    private static void createDb(String db) {
        FileWriter file;
        try {
            file = new FileWriter(url.substring(12) + db);
            file.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 
     * @param sql the sqlite statement to use
     * @param db the database name ending in .db to connect to
     */
    public static void executeStatement(String sql, String db) {
        try {
            Connection conn = connect(db);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static void executeUpdateStatement(String sql, String db) {
        try {
            Connection conn = connect(db);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param sql the SQLite query
     * @param db the database name ending in .db to connect to
     * @return rs The ResultSet from the SQLite query
     */
    public static ResultSet sqlQuery(String sql, String db) {

        try {
            Connection conn = connect(db);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    

}
