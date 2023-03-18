package mx.unam.ciencias.edd.proyecto1;
import java.text.Normalizer; //https://loquemeinteresadelared.wordpress.com/2015/10/01/eliminar-acentos-y-diacriticos-de-un-string-en-java/
import java.text.Normalizer.Form;

/**
 * Clase que nos da objetos de tipo linea, son como Strings pero 
 * como nosotros no queremos encargarnos de nada de acentos, 
 * espacios, el otro acento o ñ usaremos lineas de tipo Linea
 */
public class Linea {
    // Atributos de clase
    private String linea;    // Sera el string de la linea original
    private String formateada;      // Como su nombre lo indica sera la formateada

    /**
     * Constructor de linea, solo agregamos el string
     * la formateada se hara solo cuando mandemos a formatear al string
     * @param linea
     */
    public Linea(String linea){
        this.linea = linea;
    }

    /**
     * Getter de formateada
     * @return
     */
    public String formateada(){
        if(formateada!= null)
            return formateada;
        return formatea(linea);
    }

    /**
     * Setter de formateada y tambien formatea
     * La convierte en minusculas
     * Quita espacios en blanco
     * Le quita acentos a las vocales
     * Quita dieresis ä
     * No contiene caracteres extraños
     * 
     * NOTA: No se si no se podia usar Normalizer o si tampoco se podia usar
     * algun otro recurso de internet pero por si acaso mis referencias
     * a los codigos marcados son 
     * 
     * *** = https://loquemeinteresadelared.wordpress.com/2015/10/01/eliminar-acentos-y-diacriticos-de-un-string-en-java/
     * ** = https://www.techiedelight.com/es/remove-non-alphanumeric-characters-from-string-java/#:~:text=replaceAll()%20m%C3%A9todo,caracteres%20alfanum%C3%A9ricos%20en%20la%20string.
     * 
     * Si no se usan estos simplemente se puede utilizzar varios ifs
     * que chequen la cadena y reemplacen por casos con replaceAll().
     * @return
     */
    private String formatea(String s){
        String formateada = linea.toLowerCase();
        formateada=formateada.replaceAll(" ", "");
        formateada=Normalizer.normalize(formateada, Normalizer.Form.NFD);
        formateada=formateada.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");     // codigo que utiliza normalizer, ***
        formateada=formateada.replaceAll("[^a-z]", "");      // **
        this.formateada = formateada;
        return formateada;
    }

    /**
     * Getter de linea
     * @return
     */
    public String linea(){
        return linea;
    }    
}
