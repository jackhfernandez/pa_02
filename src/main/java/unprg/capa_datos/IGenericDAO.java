
package unprg.capa_datos;

import java.util.List;

/**
 *
 * @author jackh
 */
public interface IGenericDAO<T> {
    void agregar(T objeto);
    List<T> listar();
    void actualizar(List<T> lista);
}
