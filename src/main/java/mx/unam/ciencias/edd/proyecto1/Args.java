package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.Lista;

/**
 * Clase que nos ayuda a procesar y guardar los datos importantes en los argumentos de main.
 */
public class Args {
    // Atributos, guardaremos las banderas recibidas
    private String[] args;

    /**
     * Constructor de args
     * @param args banderas
     */
    public Args(String[] args){     // Recibimos la entrada del main
        this.args = args;
    }

    public String[] args(){ // servia para probar argumentos
        return args;
    }

    /**
     * Metodo que nos va a dar una lista de Strings (rutas) a los distintos 
     * archivos que quiere ordenar el usuario
     * @return
     */
    public Lista<String> Entrada(){         // Hacemos uso de listas vistas en clase
        Lista<String> archivos = new Lista<>();
        for(int i = 0; i < args.length; i++){       // Recorremos argumentos
            if(args[i].equals("-o"))                // Si usa -o nos lo saltamos a el y a su argumento (archivo de salida)
                i++;                                
            else if(!args[i].equals("-r"))          // Si usa -r no hacemos nada 
                archivos.agrega(args[i]);           // Si no es -r ni -o, ni el archivo de salida entonces es de entrada
        }
        return archivos;
    }

    /**
     * Metodo para determinar si queremos usar -r
     * @return boolean true si la usa.
     */
    public boolean r(){     
        for(String a : args)        // Iteramos en el array de Strings para buscar si tiene -r
            if(a.equals("-r"))
                return true;
        return false;
    }

    /**
     * Metodo para determinar si usa -o, entonces debe darnos un arhchivo donde escribir
     * @return la ruta del archivo si la encuentra
     */
    public boolean o(){
        for(String a : args)
            if(a.equals("-o"))
                return true;
        return false;
    }

    /**
     * Si tenemos la bandera "-o" queremos saber la ruta del archivo o lanzar error si no la tiene
     * @return la ruta o tira null si no hay
     * @throws IllegalArgumentException si se hace mal uso (no se incluye el archivo de salida usando -o)
     */
    public String ruta(){
        for(int i =0; i<args.length; i++)      // Necesito recorrer de manera que me deje acceder facilmente al siguiente
            if(args[i].equals("-o"))
                if(i<args.length-1)            // Necesitamos verificar que tenga algo despues antes de intentar regresarlo
                    return args[i+1];          // Si existe regresamos el siguiente argumento despues de -o
                else    
                    throw new IllegalArgumentException("La bandaera -o necesita estar seguida de un archivo de salida");
        return null;                        // Si no tiene -o            
    }    
}
