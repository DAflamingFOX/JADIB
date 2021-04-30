package commands.economy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    final static String url = "jdbc:sqlite:JADIB/src/main/java/commands/economy/";

    /**
     * 
     * @param db the database name ending in .db to connect to
     * @return conn the SQLite db connection
     */
    public static Connection connect(String db) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url + db);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * 
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

    public static String getBalance(String userid) {
        String getBalanceQuery = "Select balance FROM Users WHERE userid == " + userid;
        String database = "economy.db";
        ResultSet rs = Database.sqlQuery(getBalanceQuery, database);
        try {
            /** if there is data for the user rs.next will return true
            *   so if we use !rs.next() we will add them if they are not in db.
            */
            if (!rs.next()) {
                Connection conn = connect(database);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO Users(userid, balance) VALUES(" + userid +", 0)");
                
            }
            return String.valueOf(rs.getInt("balance"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "error!";
    }

}
