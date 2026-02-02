
package unprg.capa_logica.modelos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jackh
 */
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombreProyecto;
    private String descripcion;
    private String direccion;
    private Date FechaInicio;
    
    public Proyecto(){
        
    }

    public Proyecto(String nombreProyecto, String descripcion, String direccion, Date FechaInicio) {
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.FechaInicio = FechaInicio;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
