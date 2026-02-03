
package unprg.capa_logica.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import unprg.capa_datos.MaterialDAO;
import unprg.capa_datos.PedidoDAO;
import unprg.capa_datos.ProyectoDAO;
import unprg.capa_logica.modelos.Material;
import unprg.capa_logica.modelos.Pedido;
import unprg.capa_logica.modelos.Proyecto;

/**
 *
 * @author jackh
 */
public class ReporteService {
    private PedidoDAO pedidoDAO;
    private MaterialDAO materialDAO;
    private ProyectoDAO proyectoDAO;

    public ReporteService() {
        this.pedidoDAO = new PedidoDAO();
        this.materialDAO = new MaterialDAO();
        this.proyectoDAO = new ProyectoDAO();
    }
    
    // ========== MATERIALES ==========
    public List<Material> obtenerTodosMateriales() {
        return materialDAO.listar();
    }
    
    public List<Material> filtrarMaterialesPorNombre(String texto) {
        return materialDAO.listar().stream()
            .filter(m -> m.getNombProducto().toLowerCase().contains(texto.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    public List<Material> obtenerMaterialesStockBajo(int umbral) {
        return materialDAO.listar().stream()
            .filter(m -> m.getStock() <= umbral)
            .collect(Collectors.toList());
    }
    
    // ========== PROYECTOS ==========
    public List<Proyecto> obtenerTodosProyectos() {
        return proyectoDAO.listar();
    }
    
    public List<Proyecto> filtrarProyectosPorNombre(String texto) {
        return proyectoDAO.listar().stream()
            .filter(p -> p.getNombreProyecto().toLowerCase().contains(texto.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    // ========== PEDIDOS ==========
    public List<Pedido> obtenerTodosPedidos() {
        return pedidoDAO.listar();
    }
    
    public List<Pedido> obtenerPedidosPorProyecto(String nombreProyecto) {
        return pedidoDAO.listar().stream()
            .filter(p -> p.getNombreProyecto().equalsIgnoreCase(nombreProyecto))
            .collect(Collectors.toList());
    }
    
    public List<Pedido> filtrarPedidosPorMaterial(String nombreMaterial) {
        return pedidoDAO.listar().stream()
            .filter(p -> p.getNombreMaterial().toLowerCase().contains(nombreMaterial.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    // ========== GASTOS ==========
    public double calcularGastoTotalProyecto(String nombreProyecto) {
        return pedidoDAO.listar().stream()
            .filter(p -> p.getNombreProyecto().equalsIgnoreCase(nombreProyecto))
            .mapToDouble(Pedido::getSubtotal)
            .sum();
    }
    
    public double calcularGastoTotal() {
        return pedidoDAO.listar().stream()
            .mapToDouble(Pedido::getSubtotal)
            .sum();
    }
    
    public Map<String, Double> obtenerGastosPorProyecto() {
        Map<String, Double> gastos = new LinkedHashMap<>();
        for (Proyecto p : proyectoDAO.listar()) {
            double total = calcularGastoTotalProyecto(p.getNombreProyecto());
            gastos.put(p.getNombreProyecto(), total);
        }
        return gastos;
    }
    
    public Map<String, Double> obtenerGastosPorMaterial() {
        Map<String, Double> gastos = new LinkedHashMap<>();
        for (Pedido p : pedidoDAO.listar()) {
            String material = p.getNombreMaterial();
            gastos.merge(material, p.getSubtotal(), Double::sum);
        }
        return gastos;
    }
    
    public Map<String, Double> obtenerGastosPorMes() {
        Map<String, Double> gastos = new LinkedHashMap<>();
        String[] nombresMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                                  "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Calendar cal = Calendar.getInstance();
        
        for (Pedido p : pedidoDAO.listar()) {
            if (p.getFechaPedido() != null) {
                cal.setTime(p.getFechaPedido());
                int mes = cal.get(Calendar.MONTH);
                int anio = cal.get(Calendar.YEAR);
                String clave = nombresMeses[mes] + " " + anio;
                gastos.merge(clave, p.getSubtotal(), Double::sum);
            }
        }
        return gastos;
    }
    
    public Map<Integer, Double> obtenerGastosPorAnio() {
        Map<Integer, Double> gastos = new LinkedHashMap<>();
        Calendar cal = Calendar.getInstance();
        
        for (Pedido p : pedidoDAO.listar()) {
            if (p.getFechaPedido() != null) {
                cal.setTime(p.getFechaPedido());
                int anio = cal.get(Calendar.YEAR);
                gastos.merge(anio, p.getSubtotal(), Double::sum);
            }
        }
        return gastos;
    }
    
    public List<String> obtenerProyectosPorMaterial(String nombreMaterial) {
        return pedidoDAO.listar().stream()
            .filter(p -> p.getNombreMaterial().equalsIgnoreCase(nombreMaterial))
            .map(Pedido::getNombreProyecto)
            .distinct()
            .collect(Collectors.toList());
    }
    
    public List<String> obtenerNombresProyectos() {
        return proyectoDAO.listar().stream()
            .map(Proyecto::getNombreProyecto)
            .collect(Collectors.toList());
    }
    
    public List<String> obtenerNombresMateriales() {
        return materialDAO.listar().stream()
            .map(Material::getNombProducto)
            .collect(Collectors.toList());
    }
    
    // ========== ESTADÍSTICAS ==========
    public int contarTotalMateriales() {
        return materialDAO.listar().size();
    }
    
    public int contarTotalProyectos() {
        return proyectoDAO.listar().size();
    }
    
    public int contarTotalPedidos() {
        return pedidoDAO.listar().size();
    }
    
    public double obtenerValorInventario() {
        return materialDAO.listar().stream()
            .mapToDouble(m -> m.getStock() * m.getPrecioUnitario())
            .sum();
    }
}
