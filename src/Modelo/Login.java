
package Modelo;
//Esta es una clase para poder crear los objetos de login, con todos sus campos
public class Login {
    
    private int id;
    private String nombre;
    private String correo;
    private String pass;
    private String rol;

    public Login() {
    }

    public Login(int id, String nombre, String correo, String pass, String rol) {
        this.id = id;
        this.nombre=nombre;
        this.correo = correo;
        this.pass = pass;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
    
}
