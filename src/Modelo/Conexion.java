
package Modelo;
//Importo todos los paquetes que necesito
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    //Creo la variable global
    Connection con;
    
    public Connection getConnection(){
        try{
            //Para usa una de las dos, puedo comentar una y usar la otra
            //Base de datos en Access
            String access="jdbc:ucanaccess://D:/BDTecnoMigui/BDTecnoMigui.accdb";
            con = DriverManager.getConnection(access);
            //Base de datos en MySQL
//            String myBD="jdbc:mysql://localhost:3306/sistemaventatecno?serverTimezone=UTC";
//            con=DriverManager.getConnection(myBD,"root","");
            
            return con;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se encontró la Base de Datos");
        }
        return null;
    }
    
    
    
}
