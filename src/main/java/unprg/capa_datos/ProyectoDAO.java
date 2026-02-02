package unprg.capa_datos;

import java.io.File;
import java.util.List;
import unprg.capa_logica.modelos.Proyecto;

/**
 *
 * @author jackh
 */
public class ProyectoDAO implements IGenericDAO<Proyecto> {

    private final String DIRECTORIO = "data";
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
    public void agregar(Proyecto objeto) {
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

    public Proyecto buscar(String nombre) {
        for (Proyecto material : proyectos) {
            if (material.getNombreProyecto().equals(nombre)) {
                return material;
            }
        }
        return null;
    }
    
    public int posicion(String nombre) {
        for (int i = 0; i < this.proyectos.size(); i++) {
            if (this.proyectos.get(i).getNombreProyecto().equalsIgnoreCase(nombre)) {
            return i;
            }
           
        }
        return -1;
    }
}