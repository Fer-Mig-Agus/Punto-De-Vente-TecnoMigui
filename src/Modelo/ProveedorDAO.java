
package Modelo;

//Importo los paquetes que necesito
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProveedorDAO {
    //Creo las variables globales para la conexion
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    //Este metodo guarda los proveedores, retorna true si lo hizo con exito
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
    
    //Este metodo realiza una lista de proveedores, retorna la lista
    public List ListarProveedor(){
        List<Proveedor> ListaProveedores=new ArrayList();
        String sql="SELECT * FROM proveedores";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Proveedor pro=new Proveedor();
                pro.setId(rs.getInt("id"));
                pro.setCuit(rs.getLong("cuit"));
                pro.setEmpresa(rs.getString("empresa"));
                pro.setNombre(rs.getString("nombre"));
                pro.setTelefono(rs.getLong("telefono"));
                pro.setDireccion(rs.getString("direccion"));
                ListaProveedores.add(pro);
                
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        
        return ListaProveedores;
    }
    
    //Este metodo actualiza el proveedor, usa un objeto que se toma por parametro
    public boolean ActualizarProveedor(Proveedor pro){
        String sql="UPDATE proveedores SET cuit=?,empresa=?,nombre=?,telefono=?,direccion=? WHERE id=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setLong(1, pro.getCuit());
            ps.setString(2, pro.getEmpresa());
            ps.setString(3, pro.getNombre());
            ps.setLong(4, pro.getTelefono());
            ps.setString(5, pro.getDireccion());
            ps.setInt(6, pro.getId());
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
    
    //Este metodo elimina un proveedor, lo hace usando el ID
    public boolean EliminarProveedor(int id){
        String sql="DELETE FROM proveedores WHERE id=?";
        try{
            con=cn.getConnection();
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
    
    //Este metodo busca un proveedor por el numero de cuit
    public Proveedor BuscarProveedor(long cuit){
        Proveedor prov=new Proveedor();
        String sql="SELECT * FROM proveedores WHERE cuit=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setLong(1, cuit);
            rs=ps.executeQuery();
            if(rs.next()){
                prov.setId(rs.getInt("id"));
                prov.setCuit(rs.getLong("cuit"));
                prov.setEmpresa(rs.getString("empresa"));
                prov.setNombre(rs.getString("nombre"));
                prov.setTelefono(rs.getLong("telefono"));
                prov.setDireccion(rs.getString("direccion"));
                
            }
        }catch(SQLException e){
            System.out.println(e.toString());
            
        }
        return prov;
    }
    
    
    
    
    
    
    
    
}
