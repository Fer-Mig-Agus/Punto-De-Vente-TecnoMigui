
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProveedorDAO {
    
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean GuardarProveedores(Proveedor pro){
        String sql="INSERT INTO proveedores(cuit,empresa,nombre,telefono,direccion,fecha) VALUES (?,?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setLong(1, pro.getCuit());
            ps.setString(2, pro.getEmpresa());
            ps.setString(3, pro.getNombre());
            ps.setLong(4, pro.getTelefono());
            ps.setString(5, pro.getDireccion());
            ps.setString(6, pro.getFecha());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
    }
    
    
    
    
    
    
    
    
    
}
