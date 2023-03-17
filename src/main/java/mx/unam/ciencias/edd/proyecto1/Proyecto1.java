package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.Lista;
import java.io.BufferedReader;
import java.io.IOException;

public class Proyecto1{
    public static void main(String[] args){
        // Comenzamos por revisar y guardar los argumentos.

        Args a = new Args(args);

        String[] argumentos = a.args();

        for(String i : argumentos){
            System.out.println(i);
        }

        if(a.o())
            System.out.println(a.ruta());

	    System.out.println("Hola " + a.r());
    }
}
