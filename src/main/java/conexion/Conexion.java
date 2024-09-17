
package conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    
    public Connection conexionBD() {
    
        try{
            return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/club","root","");
        
        }catch(SQLException e){
            
            throw new RuntimeException(e);
        }
        
        
    }
}
