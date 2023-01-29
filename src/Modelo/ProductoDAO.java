
package Modelo;
//Importo los paquetes que necesito
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class ProductoDAO {
    //Creo las variables globales para la conexion
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    //Este metodo guarda los productos, retorna true si lo hizo con exito
    public boolean GuardarProductos(Producto prod){
        String sql="INSERT INTO productos (codigo,descripcion,cantidad,proveedor,categoria,precioCosto,porcentaje,precioFinal,fecha) VALUES (?,?,?,?,?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setLong(1, prod.getCodigo());
            ps.setString(2, prod.getDescripcion());
            ps.setInt(3, prod.getCantidad());
            ps.setString(4, prod.getProveedor());
            ps.setString(5, prod.getCategoria());
            ps.setDouble(6, prod.getPrecioCosto());
            ps.setInt(7, prod.getPorcentaje());
            ps.setDouble(8, prod.getPrecioFinal());
            ps.setString(9, prod.getFecha());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    //Este metodo consulta el proveedor a la base de datos, para guardarlos en
    //los ComboBox de la seccion de productos
    public void ConsultarProveedor(JComboBox proveedor){
        String sql="SELECT empresa FROM proveedores";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                proveedor.addItem(rs.getString("empresa"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    //Este metodo consulta la categoria a la base de datos, para guardarlos en
    //los ComboBox de la seccion de productos
    public void ConsultarCategoria(JComboBox categoria){
        String sql="SELECT nombre FROM categorias";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                categoria.addItem(rs.getString("nombre"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    //Este metodo lista los productos, retorna la lista
    public List ListarProductos(){
        List<Producto> ListaProductos=new ArrayList();
        String sql="SELECT * FROM productos";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto prod=new Producto();
                prod.setId(rs.getInt("id"));
                prod.setCodigo(rs.getLong("codigo"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setProveedor(rs.getString("proveedor"));
                prod.setCategoria(rs.getString("categoria"));
                prod.setPrecioCosto(rs.getDouble("precioCosto"));
                prod.setPorcentaje(rs.getInt("porcentaje"));
                prod.setPrecioFinal(rs.getDouble("precioFinal"));
                prod.setFecha(rs.getString("fecha"));
                ListaProductos.add(prod);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaProductos;
        
    }
    
    //Elimina un producto, lo hace usando el ID
    public boolean EliminarProducto(int id){
        String sql="DELETE FROM productos WHERE id=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
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
    
    //Actualiza un producto usando un objeto que toma por parametro
    public boolean ActualizarProducto(Producto prod){
        String sql="UPDATE productos SET cantidad=?,proveedor=?,precioCosto=?,porcentaje=?,precioFinal=? WHERE id=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, prod.getCantidad());
            ps.setString(2, prod.getProveedor());
            ps.setDouble(3, prod.getPrecioCosto());
            ps.setInt(4, prod.getPorcentaje());
            ps.setDouble(5, prod.getPrecioFinal());
            ps.setInt(6, prod.getId());
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
    //Busca un producto usando el codigo
    public Producto BuscarProducto(long codigo){
        Producto prod=new Producto();
        String sql="SELECT * FROM productos WHERE codigo=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setLong(1, codigo);
            rs=ps.executeQuery();
            if(rs.next()){
                prod.setId(rs.getInt(("id")));
                prod.setCodigo(rs.getLong("codigo"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setProveedor(rs.getString("proveedor"));
                prod.setCategoria(rs.getString("categoria"));
                prod.setPrecioCosto(rs.getDouble("precioCosto"));
                prod.setPorcentaje(rs.getInt("porcentaje"));
                prod.setPrecioFinal(rs.getDouble("precioFinal"));
                
            }
        }catch(SQLException e){
            System.out.println(e.toString());
            
        }
        return prod;
    }
    
   
    
    
    
    
    
    
    
}
