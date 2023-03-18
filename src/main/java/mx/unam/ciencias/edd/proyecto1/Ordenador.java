package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.Lista;

/**
 * Ordenador como su nombre nos indica sera un objeto que tiene metodos
 * que nos permiten ordenar en orden o en reversa listas
 */
public class Ordenador {

    /**
     * Regresa una copia ordenada de la lista de lineas original
     * la ordena de manera lexicografica.
     * 
     * NOTA: utiliza la version de Merge Sort en donde T no extiende comparable
     * entonces le pasamos un comparador, que en este caso sera
     * una lambda, comparamos las versiones formateadas de los Strings, esto porque no queremos 
     * comparar las lineas originales pero tampoco queremos ordenar las formateadas pues perderiamos informacion
     * @param listaLineas
     * @return
     */
    public Lista<Linea> ordena(Lista<Linea> listaLineas){
        return listaLineas.mergeSort((linea1, linea2) -> linea1.formateada().compareTo(linea2.formateada()));
    }

    /**
     * Solo utiliza ordena y como esta dentro de una lista de lineas
     * por definicion entonces la reversa sera la reversa de la lista
     * @param listaLineas
     * @return
     */
    public Lista<Linea> ordenaR(Lista<Linea> listaLineas){
        return ordena(listaLineas).reversa();
    }
}
