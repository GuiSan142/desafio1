package conexao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectaBD {
    static final String URL = "jdbc:mysql://localhost:3306/trabalhodao";
    static final String USER = "root";
    static final String PASS = "";
    
    public static Connection createConnectionMySQL(){
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return connection;
    }
    
    
}
