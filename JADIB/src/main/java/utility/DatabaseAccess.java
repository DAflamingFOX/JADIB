package utility;

import java.sql.*;
import utility.Database;
public class DatabaseAccess {

    /**
     * 
     * @param user_id the user id that will be added to the table
     */
    public static void addUser(String user_id) {
    String updateStmt = "INSERT INTO master(user_id, balance) VALUES("+user_id+", 0);";
    String database = "economy.db";

    try {
        Connection conn = Database.connect(database);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(updateStmt);
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public static boolean isUser(String user_id) {
        String query = "SELECT * FROM master WHERE user_id == "+user_id+";";
        String database = "economy.db";

        try {
            ResultSet rs = Database.sqlQuery(query, database);

            if (rs.next()) {
                rs.close();
                return true;
            } else {
                rs.close();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static int getBalance(String user_id) {
        String getBalanceQuery = "SELECT balance FROM master WHERE user_id == "+user_id+";";
        String database = "economy.db";
        
        try {
            ResultSet rs = Database.sqlQuery(getBalanceQuery, database);


            if (!isUser(user_id)) {
                addUser(user_id);
            }

            
                int balance = rs.getInt("balance");
                rs.close();
                return balance;
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    public static long getLastVote(String user_id) {
        String queryStmt = "SELECT last_vote FROM master WHERE user_id == "+user_id;
        String database = "economy.db";

        try {
            
            // check for user
            if (!isUser(user_id)){
                addUser(user_id);
            }

            ResultSet rs = Database.sqlQuery(queryStmt, database);
            long last_vote = rs.getLong("last_vote");
            rs.close();
            return last_vote;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis();
    }

    public static void addToBalance(int amount, String user_id, String db) {
        int newTotal = (getBalance(user_id) + amount);
        String addQuery = "UPDATE master SET balance = "+newTotal+" WHERE user_id = "+user_id+";";
        Database.executeUpdateStatement(addQuery, db);
        
    }

}
