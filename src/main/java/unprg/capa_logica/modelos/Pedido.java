
package unprg.capa_logica.modelos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jackh
 */
public class Pedido implements Serializable {
    
    private String nombreProyecto;
    private String nombreMaterial;
    private int cantidad;
    private Date fechaPedido;
    private double costoUnitarioAlMomento;
    
    public Pedido(){
        
    }

    public Pedido(String nombreProyecto, String nombreMaterial, int cantidad, Date fechaPedido, double costoUnitario) {
        this.nombreProyecto = nombreProyecto;
        this.nombreMaterial = nombreMaterial;
        this.cantidad = cantidad;
        this.fechaPedido = new Date();
        this.costoUnitarioAlMomento = costoUnitario;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getCostoUnitarioAlMomento() {
        return costoUnitarioAlMomento;
    }

    public void setCostoUnitarioAlMomento(double costoUnitarioAlMomento) {
        this.costoUnitarioAlMomento = costoUnitarioAlMomento;
    }

    public double getSubtotal() {
        return cantidad * costoUnitarioAlMomento;
    }
}
