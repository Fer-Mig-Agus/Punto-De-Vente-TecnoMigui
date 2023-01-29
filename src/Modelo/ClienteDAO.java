
package Modelo;
//Importo todo lo necesario
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    //Creo las variables globales para la conexion con la BD
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    //Guarda un nuevo cliente, retorna true si se guardo con exito
    public boolean GuardarClientes(Cliente cli){
        String sql="INSERT INTO clientes(dni,nombre,telefono,direccion,fecha) VALUES (?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setLong(1, cli.getDni());
            ps.setString(2, cli.getNombre());
            ps.setLong(3, cli.getTelefono());
            ps.setString(4, cli.getDireccion());
            ps.setString(5, cli.getFecha());
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
    //Lista los clientes, retorna la lista llena de objetos
    public List ListarCliente(){
        List<Cliente> ListaClientes=new ArrayList();
        String sql= "SELECT * FROM clientes";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente cli=new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setDni(rs.getLong("dni"));
                cli.setNombre(rs.getString("nombre"));
                cli.setTelefono(rs.getLong("telefono"));
                cli.setDireccion(rs.getString("direccion"));
                ListaClientes.add(cli);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        
        return ListaClientes;
    }
    //Actualiza un cliente, recibe el cliente a modificar
    public boolean ActualizarCliente(Cliente cli){
        String sql="UPDATE clientes SET telefono=?,direccion=? WHERE id=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setLong(1, cli.getTelefono());
            ps.setString(2, cli.getDireccion());
            ps.setInt(3, cli.getId());
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
    //Elimina un cliente, teniendo en cuenta su ID
    public boolean EliminarCliente(int id){
        String sql="DELETE FROM clientes WHERE id=?";
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
    //Busca un cliente por su numero de dni, retorna el objeto
    public Cliente BuscarCliente(long dni){
        Cliente cli=new Cliente();
        String sql="SELECT * FROM clientes WHERE dni=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setLong(1, dni);
            rs=ps.executeQuery();
            if(rs.next()){
                cli.setId(rs.getInt("id"));
                cli.setDni(rs.getLong("dni"));
                cli.setNombre(rs.getString("nombre"));
                cli.setTelefono(rs.getLong("telefono"));
                cli.setDireccion(rs.getString("direccion"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return cli;
    }
    
}
