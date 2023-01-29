
package Modelo;

//Esta clase toma los detalles de la venta, para luego hacer el ticket

public class Detalle {
    
    private int id;
    private long codProducto;
    private int cantidad;
    private double precio;
    private int idVenta;

    public Detalle() {
    }

    public Detalle(int id, long codProducto, int cantidad, double precio, int idVenta) {
        this.id = id;
        this.codProducto = codProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idVenta = idVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(long codProducto) {
        this.codProducto = codProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    
    
    
}
