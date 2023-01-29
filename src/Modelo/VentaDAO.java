
package Modelo;
//Importo los paquetes que necesito
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    //Creo las variables globales para la conexion
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    //Este metodo retorna el ID de la venta, selecciona el maximo ID
    public int IdVenta(){
        int id=0;
        String sql="SELECT MAX(id) FROM ventas";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                id=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        
        return id;
    }
    
    //Este metodo guarda una venta
    public int RegistrarVenta(Venta venta){
        String sql="INSERT INTO ventas (cliente,vendedor,total,fecha) VALUES (?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, venta.getCliente());
            ps.setString(2, venta.getVendedor());
            ps.setDouble(3,venta.getTotal());
            ps.setString(4, venta.getFecha());
            ps.execute();
                    
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return r;
    }
    
    //Este metodo guarda los detalles de la venta
    public int RegistrarDetalle(Detalle Dv){
        String slq="INSERT INTO detalle (codProducto,cantidad,precio,idVenta) VALUES (?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(slq);
            ps.setLong(1, Dv.getCodProducto());
            ps.setInt(2,Dv.getCantidad());
            ps.setDouble(3, Dv.getPrecio());
            ps.setInt(4, Dv.getId());
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return r;
    }
    
    
    //Este metodo actualiza el stock de todos los productos vendidos
    //toma como parametro, la cantidad vendida y el codigo del producto
    public boolean ActualizarStock(int cant,long cod){
        String sql="UPDATE productos SET cantidad = ? WHERE codigo =?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setLong(2, cod);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    //Este metodo lista las ventas, retorna la lista
    public List ListarVentas(){
        List<Venta> ListaVenta=new ArrayList();
        String slq="SELECT * FROM ventas";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(slq);
            rs=ps.executeQuery();
            while(rs.next()){
                Venta Vent=new Venta();
                Vent.setId(rs.getInt("id"));
                Vent.setCliente(rs.getString("cliente"));
                Vent.setVendedor(rs.getString("vendedor"));
                Vent.setTotal(rs.getDouble("total"));
                ListaVenta.add(Vent);
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaVenta;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
