package unprg.capa_datos;

import java.io.File;
import java.util.List;
import unprg.capa_logica.modelos.Material;

/**
 *
 * @author jackh
 */
public class MaterialDAO implements IGenericDAO<Material> {
    
    private List<Material> materiales;
    private final String DIRECTORIO = "data";
    private final String RUTA = DIRECTORIO + "/materiales.dat";

    public MaterialDAO() {
        File carpeta = new File(DIRECTORIO);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        this.materiales = ArchivoHelper.leerArchivo(RUTA);
    }

    @Override
    public void agregar(Material material) {
        materiales.add(material);
        ArchivoHelper.guardarArchivo(RUTA, materiales);
    }

    @Override
    public List<Material> listar() {
        return materiales;
    }

    @Override
    public void actualizar(List<Material> lista) {
        this.materiales = lista;
        ArchivoHelper.guardarArchivo(RUTA, materiales);
    }
    
    public Material buscar(String nombre) {
        for (Material material : materiales) {
            if (material.getNombProducto().equals(nombre)) {
                return material;
            }
        }
        return null;
    }
    
    public int posicion(String nombre) {
        for (int i = 0; i < this.materiales.size(); i++) {
            if (this.materiales.get(i).getNombProducto().equalsIgnoreCase(nombre)) {
            return i;
            }
           
        }
        return -1;
    }
    
    public Material getElemento(String producto) {
        for (Material m : materiales) {
            if (m.getNombProducto().equalsIgnoreCase(producto)) {
                return m;
            }
        }
        return null;
    }
}
