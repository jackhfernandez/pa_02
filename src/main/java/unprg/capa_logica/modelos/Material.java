package unprg.capa_logica.modelos;

import java.io.Serializable;


/**
 *
 * @author jackh
 */
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombProducto;
    private String descripcion;
    private double precioUnitario;
    private int stock;
    private String unidadMedida;
   
    public Material(){       
    }

    public Material(String nombProducto, String descripcion, String unidadMedida, double precioUnitario, int stock) {
        this.nombProducto = nombProducto;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public String getNombProducto() {
        return nombProducto;
    }

    public void setNombProducto(String nombProducto) {
        this.nombProducto = nombProducto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getCosto() {
        return precioUnitario;
    }

    public void setCosto(double costo) {
        this.precioUnitario = costo;
    }
    
    public int getStock(){
        return stock;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
