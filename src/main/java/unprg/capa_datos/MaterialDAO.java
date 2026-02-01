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
    private final String DIRECTORIO = "recursos/data";
    private final String RUTA = DIRECTORIO + "/materiales.dat";

    public MaterialDAO() {
        File carpeta = new File(DIRECTORIO);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        this.materiales = ArchivoHelper.leerArchivo(RUTA);
    }

    @Override
    public void guardar(Material material) {
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
}
