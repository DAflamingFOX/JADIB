package commands.economy;

import java.sql.*;

public class CommandDatabaseBackend {
    /**
     * ! This class will be used for command access to databases,
     * ! rather than Database.java, the issues is that there are quite
     * ! alot of methods that I would not like to mix in with the SQLite
     * ! methods; so instead they will go here.
     * TODO: This is currently unused but will be fixed to work later
     */

     //* General Methods
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
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }

     //* Balance Methods
     public static String getBalance(String userId) {
        String getBalanceQuery = "SELECT balance FROM master WHERE user_id == "+userId+";";
        String database = "economy.db";
        
        try {
            ResultSet rs = Database.sqlQuery(getBalanceQuery, database);

            if (rs.next()) {
                String balance = rs.getString("balance");
                return balance;
            } else {
                addUser(userId);
                return getBalance(userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "ERR!";
    }

    //* Vote Methods
    public static long getLastVote(String user_id) {
        String queryStmt = "SELECT voteTime FROM master WHERE user_id == "+user_id;
        String database = "economy.db";
        ResultSet rs = Database.sqlQuery(queryStmt, database);

        try {
            if (!rs.next()){ // checks to see if any value returns, will be empty if user is not in db
                addUser(user_id);
            }
            return rs.getLong("lastVote");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // should never return this but it would work anyway.
    }

	public static void setLastVote(String user_id, long time) {
        String updateStmt = "INSERT INTO master(lastVote) VALUES("+time+") WHERE user_id == "+user_id;
        
        Database.executeUpdateStatement(updateStmt, "economy.db");
	}

    //* Beg Methods


    //* 
}
