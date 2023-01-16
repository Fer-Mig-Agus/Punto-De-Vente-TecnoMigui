
package Modelo;

public class Config {
    
    private int id;
    private long cuit;
    private String nombre;
    private long telefono;
    private String direccion;
    private String TipoDeEmpresa;

    public Config() {
    }

    public Config(int id,long cuit, String nombre, long telefono, String direccion, String TipoDeEmpresa) {
        this.id=id;
        this.cuit = cuit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.TipoDeEmpresa = TipoDeEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    
    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoDeEmpresa() {
        return TipoDeEmpresa;
    }

    public void setTipoDeEmpresa(String TipoDeEmpresa) {
        this.TipoDeEmpresa = TipoDeEmpresa;
    }
    
    
}
