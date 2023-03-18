package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.Lista;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Desde aqui corre todo el proyecto.
 * Leí en convenciones que algunas personas dejan el main separado asi 
 * que intente hacer algo similar, la legibilidad es mucho menor pero 
 * en teoria es lo que se debe hacer con POO.
 */
public class Proyecto1{
    public static void main(String[] args){
        // Comenzamos por revisar y guardar los argumentos.
        Args a = new Args(args);

        // Obtenemos referencia a metodos de uso, para solo tener aqui el main.
        Uso u = new Uso();

        /**
         * Utilizamos varios metodos seguidos de uso y de argumentos aqui, se ve feo, es super illegible
         * pero ahorramos poquita memoria, muchas lineas de codigo
         * y nada de esto se va a reutilizar.
         */
        u.regresaOrdenado(u.ordena(u.lee(a.entrada()), a.r()), a.oSalida());
    }
}
