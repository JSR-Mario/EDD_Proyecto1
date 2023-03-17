package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.Lista;
import java.io.BufferedReader;
import java.io.IOException;

public class Proyecto1{
    public static void main(String[] args){
        // Comenzamos por revisar y guardar los argumentos.
        Args a = new Args(args);
        Uso u = new Uso();

        /**
         * Utilizamos varios metodos seguidos de uso y de argumentos aqui, se ve feo, es super illegible
         * pero ahorramos poquita memoria, muchas lineas de codigo
         * y nada de esto se va a reutilizar.
         */
        u.regresaOrdenado(u.ordena(u.lee(a.entrada()), a.r()), a.oSalida());
    }
}
