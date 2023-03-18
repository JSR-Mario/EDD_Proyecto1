package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.Lista;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase que nos permitira tener el codigo en una clase diferente del main
 */
public class Uso {

    /**
     * Es un metodo que nos va a leer las lineas de toda la lista
     * de archivos (Lista<String>) y nos la va a regresar en una
     * Lista<Lineas> 
     * @param entrada la lista de archivos 
     * @return la lista de lineas de todos
     */
    public Lista<Lineas> lee(Lista<String> archivos) throws IOException{
        Lista<Lineas> lineas = new Lista<>();
        ReadWrite rw = new ReadWrite();
        try{
            lineas= rw.procesaEntrada(archivos);       // Cada archivo regresa un buffered Reader, juntar metodos para regresar de lineas
        }catch(IOException ioe){
            System.err.println("Error␣al␣leer␣el␣contenido␣de␣");
            System.err.println(archivos);
        }
        return lineas;
    }

    // formatear las cadenas iterando por sus caracteres y comparando con abc

    public  void regresaOrdenado(){

        
    }
    
}
