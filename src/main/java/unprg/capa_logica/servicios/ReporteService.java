
package unprg.capa_logica.servicios;

import java.util.List;
import java.util.stream.Collectors;
import unprg.capa_datos.PedidoDAO;
import unprg.capa_logica.modelos.Pedido;

/**
 *
 * @author jackh
 */
public class ReporteService {
    private PedidoDAO pedidoDAO;

    public ReporteService() {
     this.pedidoDAO = new PedidoDAO();
    }
    
    public List<Pedido> obtenerPedidosPorProyecto(String nombreProyecto) {
        return pedidoDAO.listar().stream()
            .filter(p -> p.getNombreProyecto().equalsIgnoreCase(nombreProyecto))
            .collect(Collectors.toList());
    }
    
    public double calcularGastoTotalProyecto(String nombreProyecto) {
        return pedidoDAO.listar().stream()
            .filter(p -> p.getNombreProyecto().equalsIgnoreCase(nombreProyecto))
            .mapToDouble(Pedido::getSubtotal)
            .sum();
    }
    
    public List<String> obtenerProyectosPorMaterial(String nombreMaterial) {
        return pedidoDAO.listar().stream()
            .filter(p -> p.getNombreMaterial().equalsIgnoreCase(nombreMaterial))
            .map(Pedido::getNombreProyecto)
            .distinct()
            .collect(Collectors.toList());
    }
}
