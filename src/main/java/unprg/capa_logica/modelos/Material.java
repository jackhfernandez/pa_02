package unprg.capa_logica.modelos;

import java.io.Serializable;


/**
 *
 * @author jackh
 */
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombProducto;
    private double precioUnitario;
    private int cantidad;
    private String unidadMedida;
   
    public Material(){
        
    }

    public Material(String nombProducto, double precioUnitario, int cantidad, String unidadMedida) {
        this.nombProducto = nombProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
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
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}
