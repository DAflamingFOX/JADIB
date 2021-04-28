package commands.economy;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Economy {
    static String defaultUrl = "jdbc:sqlite:JADIB/src/main/java/commands/economy/";

    public static void createDatabase(String filename) {
        if (filename.endsWith(".db")) {
            String url = defaultUrl + filename;

            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }
    
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } 
    }
}
