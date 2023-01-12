
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn=new Conexion();
    
    public Login logear(String correo, String pass){
        Login lg=new Login();
        String slq="SELECT * FROM usuarios WHERE correo =? AND pass=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(slq);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            if(rs.next()){
                lg.setId(rs.getInt("id"));
                lg.setNombre(rs.getString("nombre"));
                lg.setCorreo(rs.getString("correo"));
                lg.setPass(rs.getString("pass"));
                lg.setRol(rs.getString("rol"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        
        return lg;
    }
    
    public boolean Registrar(Login reg){
        String sql="INSERT INTO usuarios(nombre,correo,pass,rol) VALUE (?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getPass());
            ps.setString(4, reg.getRol());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    
    
    
    
}
