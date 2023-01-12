
package Modelo;

public class Proveedor {
    
    private int id;
    private long cuit;
    private String empresa;
    private String nombre;
    private long telefono;
    private String direccion;
    private String fecha;

    public Proveedor() {
    }

    public Proveedor(int id, long cuit, String empresa, String nombre, long telefono, String direccion,String fecha) {
        this.id = id;
        this.cuit = cuit;
        this.empresa = empresa;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha=fecha;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
