
package Modelo;
//Importo los paquete necesarios
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConfigDAO {
    //Creo las varibales globales para la conexion
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    //Actualza los datos de la empresa
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
            //El finally es para que cuando termine de hacer la consulta, 
            //la conexion se cierre, pasa los mismmo en los demas metodos
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
    }
    
    //Busca los datos de la empresa, y retorna un objeto
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
