
package unprg.capa_datos;

import java.util.List;

/**
 *
 * @author jackh
 */
public interface IGenericDAO<T> {
    void guardar(T objeto);
    List<T> listar();
    void actualizar(List<T> lista);
}
