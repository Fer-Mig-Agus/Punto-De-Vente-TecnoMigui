
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection con;
    
    public Connection getConnection(){
        try{
//            String access="jdbc:ucanaccess://D:/Lugo"
            String myBD="jdbc:mysql://localhost:3306/sistemaventatecno?serverTimezone=UTC";
            con=DriverManager.getConnection(myBD,"root","");
            return con;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se encontró la Base de Datos");
        }
        return null;
    }
    
    
    
}
