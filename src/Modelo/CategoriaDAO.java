
package Modelo;

//Importo todo lo necesario
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    //Creo las variables globales, que me permitiran la conexion con la BD
    Connection con;
    Conexion cn= new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    
    //Guarda las nuevas categorias, retorna true si se ha guardado con exito
    public boolean GuardarCategoria(Categoria cat){
        String sql="INSERT INTO categorias(nombre) VALUES(?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,cat.getNombre() );
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
    
    //Lista la categoria
    public List ListarCategorias(){
        List<Categoria> ListaCategorias=new ArrayList();
        String sql="SELECT * FROM categorias";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id"));
                cat.setNombre(rs.getString("nombre"));
                ListaCategorias.add(cat);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaCategorias;
    }
    
    //Elimina las categorias
    public boolean EliminarCategoria(int id){
        String sql="DELETE FROM categorias WHERE id=?";
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
    
}
