package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.Lista;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase que usa la clase "io" para pasar los archivos o 
 * entrada estandar a algo manejable y regresa de algo manejable 
 * a archivos de salida o lo imprime en consola.
 */
public class ReadWrite {

    /**
     * Metodo que dada una lista de entradas:
     * Si no tiene niguna abre la linea de comandos para escribir texto
     * Si si tiene las lee usando BR y las pasamos a una lista de estos
     * Luego vamos linea por linea de cada uno de los BR y lo pasamos 
     * a una lista de Lineas que son parecidos a String pero
     * son una clase creada por nosotros con algunas caracteristicas.
     * @param entrada la lista de rutas
     * @return  la lista de lineas de cada entrada
     * @throws IOException si BR tiene error
     */
    public Lista<Linea> read(Lista<String> entrada) throws IOException{
        Lista<BufferedReader> entradasBR = new Lista<>();
        Lista<Linea> entradaLinea = new Lista<>();
        String linea;
        if(entrada.esVacia())      // Si no nos dio un archivo de entrada 
            entradasBR.agregaFinal(new BufferedReader(new InputStreamReader(System.in)));       //abrimos para escribir   
        else{
            for(String archivo : entrada){      // Nos dieron 1 o mas archivos
                try{
                    BufferedReader BR = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));        // leemos del archivo usando BR
                    entradasBR.agregaFinal(BR);         // Agregamos a la lista de BR todo lo leido de cada archivo
                }catch(IOException ioe){
                    throw new IOException("Error al pasar los archivos usando BR");
                }
            }
        }
        for(BufferedReader e : entradasBR){     // Para cada BR leemos la linea en un string y si es no vacia (llegamos al final) la agregamos a nuestra lista de lineas;
            linea= e.readLine();
            while(linea!=null)
                entradaLinea.agregaFinal(new Linea(linea));
        }
        return entradaLinea;
    }

    /**
     * Metodo que dado un archivo de salida y las lineas de entrada
     * Sobreescribe cada linea de la lista de lineas en el archivo
     * utilizando un BW.
     * @param archivoSalida el archivo resultante
     * @param lineas la lista de lineas de entrada
     * @throws IOException si BW tiene error
     */
    public void write(String archivoSalida, Lista<Linea> lineas)throws IOException{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivoSalida))); 
        for(Linea l : lineas)
            writer.write(l.linea()+"\n");
        writer.close();    
    }    
}
