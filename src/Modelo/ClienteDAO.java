
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    
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
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
        
    }
    
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
