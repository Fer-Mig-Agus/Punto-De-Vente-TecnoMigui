
package Modelo;
//Importo los paquetes que necesito
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAO {
    //Creo las variables globlales para la conexion
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn=new Conexion();
    
    //Este programa consulta a la BD si el usuario esta registrado
    //Retorna un objeto con los datos
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
    
    //Esta funcion registra un nuevo usuario
    public boolean Registrar(Login reg){
        String sql="INSERT INTO usuarios(nombre,correo,pass,rol) VALUES (?,?,?,?)";
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
    
    //Este metodo lista todos los usuarios, retorna la lista completa
    public List ListaUsuario(){
        List<Login> ListaUsuarios = new ArrayList();
        String sql="SELECT * FROM usuarios";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Login lg=new Login();
                lg.setId(rs.getInt("id"));
                lg.setNombre(rs.getString("nombre"));
                lg.setCorreo(rs.getString("correo"));
                lg.setPass(rs.getString("pass"));
                lg.setRol(rs.getString("rol"));
                ListaUsuarios.add(lg);
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaUsuarios;
    }
    
    //Este metodo elimina un usuario, usando su ID
    public boolean EliminarUsuario(int id){
        String sql="DELETE FROM usuarios WHERE id=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
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
