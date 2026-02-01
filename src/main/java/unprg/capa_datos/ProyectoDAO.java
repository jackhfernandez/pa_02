package unprg.capa_datos;

import java.io.File;
import java.util.List;
import unprg.capa_logica.modelos.Proyecto;

/**
 *
 * @author jackh
 */
public class ProyectoDAO implements IGenericDAO<Proyecto> {

    private final String DIRECTORIO = "recursos/data";
    private final String RUTA = DIRECTORIO + "/proyectos.dat";
    private List<Proyecto> proyectos;

    public ProyectoDAO() {
        File carpeta = new File(DIRECTORIO);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        this.proyectos = ArchivoHelper.leerArchivo(RUTA);
    }

    @Override
    public void guardar(Proyecto objeto) {
        proyectos.add(objeto);
        ArchivoHelper.guardarArchivo(RUTA, proyectos);
    }

    @Override
    public List<Proyecto> listar() {
        return proyectos;
    }

    @Override
    public void actualizar(List<Proyecto> lista) {
        this.proyectos = lista;
        ArchivoHelper.guardarArchivo(RUTA, proyectos);
    }

}
