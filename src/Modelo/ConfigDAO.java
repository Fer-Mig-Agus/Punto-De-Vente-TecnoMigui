
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConfigDAO {
    
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    
    public boolean ActualizarDatos(Config conf){
        String sql="UPDATE config SET cuit=?,nombre=?,telefono=?, direccion=?, tipoDeEmpresa=? WHERE id=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setLong(1, conf.getCuit());
            ps.setString(2, conf.getNombre());
            ps.setLong(3, conf.getTelefono());
            ps.setString(4, conf.getDireccion());
            ps.setString(5, conf.getTipoDeEmpresa());
            ps.setInt(6, conf.getId());
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
    
    public Config BuscarDatos(){
        Config conf=new Config();
        String sql="SELECT * FROM config";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                conf.setId(rs.getInt("id"));
                conf.setCuit(rs.getLong("cuit"));
                conf.setNombre(rs.getString("nombre"));
                conf.setTelefono(rs.getLong("telefono"));
                conf.setDireccion(rs.getString("direccion"));
                conf.setTipoDeEmpresa(rs.getString("tipoDeEmpresa"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return conf;
    }
    
    
    
    
}
