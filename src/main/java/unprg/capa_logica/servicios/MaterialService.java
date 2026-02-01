
package unprg.capa_logica.servicios;

import java.util.List;
import unprg.capa_datos.MaterialDAO;
import unprg.capa_logica.modelos.Material;

/**
 *
 * @author jackh
 */
public class MaterialService {
    private MaterialDAO materilaDAO;

    public MaterialService() {
       this.materilaDAO = new MaterialDAO();
    }
    
    public void registrarMaterial(Material m) {
        materilaDAO.guardar(m);
    }
    
    public boolean descontarStock(String nombreMaterial, int cantidadRestar) {
        List<Material> lista = materilaDAO.listar();
        for (Material m : lista) {
            if (m.getNombProducto().equalsIgnoreCase(nombreMaterial)) {
                if (m.getCantidad() >= cantidadRestar) {
                    m.setCantidad(m.getCantidad() - cantidadRestar);
                    materilaDAO.actualizar(lista);
                    return true;
                }
            }
        }
        return false;
    } 
    
    public void agregarMaterial(Material nuevoMaterial) {
        List<Material> listaActual = materilaDAO.listar();
        boolean encontrado = false;
        
        for (Material m : listaActual) {
            if (m.getNombProducto().equalsIgnoreCase(nuevoMaterial.getNombProducto())) {
                int stockActualizado = m.getCantidad() + nuevoMaterial.getCantidad();
                m.setCantidad(stockActualizado);
                m.setCosto(nuevoMaterial.getCosto());
                
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            listaActual.add(nuevoMaterial);
        }
        materilaDAO.actualizar(listaActual);
    }
}
