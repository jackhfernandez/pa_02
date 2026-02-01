
package unprg.capa_datos;

import java.io.*;
import java.util.*;

/**
 *
 * @author jackh
 */
public class ArchivoHelper {
    
    public static <T> void guardarArchivo(String ruta, java.util.List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(ruta))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }
    
    public static <T> List<T> leerArchivo(String ruta) {
        File file = new File(ruta);
        if (!file.exists()) return new ArrayList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream( new FileInputStream(ruta)) {
        }) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
