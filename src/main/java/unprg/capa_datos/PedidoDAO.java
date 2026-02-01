package unprg.capa_datos;

import java.io.File;
import java.util.List;
import unprg.capa_logica.modelos.Pedido;

/**
 *
 * @author jackh
 */
public class PedidoDAO implements IGenericDAO<Pedido> {

    private List<Pedido> pedidos;
    private final String DIRECTORIO = "recursos/data";
    private final String RUTA = DIRECTORIO + "/pedidos.dat";

    public PedidoDAO() {
        File carpeta = new File(DIRECTORIO);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        this.pedidos = ArchivoHelper.leerArchivo(RUTA);
    }

    @Override
    public void guardar(Pedido pedido) {
        pedidos.add(pedido);
        ArchivoHelper.guardarArchivo(RUTA, pedidos);
    }

    @Override
    public List<Pedido> listar() {
        return pedidos;
    }

    @Override
    public void actualizar(List<Pedido> lista) {
        this.pedidos = lista;
        ArchivoHelper.guardarArchivo(RUTA, pedidos);
    }
}
