package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.Lista;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Clase que nos permitira tener el codigo en una clase diferente del main
 * Como usa io, es commun que nos regrese bastantes errores 
 */
public class Uso {

    /**
     * Metodo que manda a llamar al ordenador y dependiendo si uso o no r
     * le dice si tiene que ordenar en rerversa o normal.
     * @param lineasEntrada
     * @param r bandera -r
     * @return la lista de lineas ordenada
     */
    public Lista<Linea> ordena(Lista<Linea> lineasEntrada, boolean r){
        Ordenador ordenador = new Ordenador();
        if(r)
            return ordenador.ordenaR(lineasEntrada);   
        return ordenador.ordena(lineasEntrada);    
    }

    /**
     * Es un metodo que nos va a leer las lineas de toda la lista
     * de archivos (Lista<String>) y nos la va a regresar en una
     * Lista<Linea> 
     * @param entrada la lista de archivos 
     * @return la lista de lineas de todos
     * @throws IOException
     */
    public Lista<Linea> lee(Lista<String> archivos) throws IOException{
        Lista<Linea> lineas = new Lista<>();
        //Lista<BufferedReader> entradasBR= null;
        ReadWrite rw = new ReadWrite();
        try{
            //entradasBR = rw.readBR(archivos);
            lineas= rw.read(archivos);      
        }catch(IOException ioe){
            System.err.println("Error␣al␣leer␣el␣contenido␣de␣");
            System.err.println(archivos);
        }
        return lineas;
    }

    /**
     * Metodo que dada una lista de lineas y los argumentos 
     * (para saber si tiene -o y entonces su archivo de salida)
     * si no tiene -o entonces imprime en consola todas las lineas
     * si si tiene -o entonces utiliza ReadWrite para mandar a escribir
     * a la direccion del archivo de salida.
     * @param a argumentos de consola
     * @param lineas lista de lineas que tiene el archivo
     * @throws IOException Si BW encuentra algun problema
     */
    public  void regresaOrdenado(Args a, Lista<Linea> lineas) throws IOException{
        ReadWrite rw = new ReadWrite();
        if(!a.o())
            for(Linea l : lineas)
                System.out.println(l.toString());
        else{
            String salida = a.oSalida();
            try{
                rw.write(salida, lineas);
            }catch(IOException ioe){
                System.err.println("Error al escribir al archivo");
            }
        }
    }    
}
