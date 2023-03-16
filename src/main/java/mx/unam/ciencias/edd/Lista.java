package mx.unam.ciencias.edd;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            this.elemento=elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            anterior=null;
            siguiente=cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente!=null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if(siguiente==null){
                throw new NoSuchElementException("El siguiente nodo no ha sido asignado");
            }
            T elemento = siguiente.elemento;
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return elemento;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            return anterior!=null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if(anterior==null){
                throw new NoSuchElementException("El nodo anterior no ha sido asignado");
            }
            T elemento = anterior.elemento;
            siguiente = anterior;
            anterior = anterior.anterior;
            return elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            anterior = null;
            siguiente = cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            siguiente = null;
            anterior = rabo;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {   
        return getLongitud();
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        return (cabeza==null);
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
        agregaFinal(elemento);
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento==null){
            throw new IllegalArgumentException("No se aceptan elementos nulos");
        }
        longitud++;
        Nodo n = new Nodo(elemento);
        if(rabo==null){
            cabeza = rabo = n;
        }else{
            n.anterior = rabo;
            rabo.siguiente = n;
            rabo = n;
        }   
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento==null){
            throw new IllegalArgumentException("No se aceptan elementos nulos");
        }
        longitud++;
        Nodo n = new Nodo(elemento);
        if(cabeza==null){
            cabeza = rabo = n;
        }else{
            n.siguiente = cabeza;
            cabeza.anterior = n;
            cabeza = n;
        }
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        if (elemento==null){
            throw new IllegalArgumentException("No se aceptan elementos nulos");
        }
        if(i<=0){
            agregaInicio(elemento);
        }
        else if(i>=longitud){
            agregaFinal(elemento);
        }else{
            Nodo n = cabeza;
            Nodo n0 = new Nodo(elemento);
            while (i>0){
                i--;
                n= n.siguiente;
            }
            n0.siguiente = n;
            n0.anterior=n.anterior;
            n.anterior.siguiente=n0;
            n.anterior=n0;  
            longitud++; 
        }
    }

    /**
     * Metodo auxiliar para ver si el elemento se encuentra en la lista y lo regresa
     *  @param elemento el elemento a buscar.
     * */ 
    private Nodo buscaNodo(T elemento){
        Nodo n = cabeza;
        while(n!=null){
            if(n.elemento.equals(elemento)){
                return n;
            }
            n=n.siguiente;
        }
        return null;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        if(elemento==null){
            return;
        }
        if(esVacia()){
            return;
        }
        Nodo n = buscaNodo(elemento);
        if(n==null){
            return;
        }
        Nodo a = n.anterior;
        Nodo b = n.siguiente;

        if(a==null){
            eliminaPrimero();
            return;
        }
        if(b==null){
            eliminaUltimo();
            return;
        }
        
        else {
            a.siguiente=b;
            b.anterior=a; 
            longitud--;
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        if(cabeza==null){
            throw new NoSuchElementException("La lista no tiene primer elemento (es vacia)");
        }
        T elemento = cabeza.elemento;
        if(longitud==1){
            limpia();
        }else{
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longitud--;
        }
        return elemento;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        if(esVacia()){
            throw new NoSuchElementException("La lista no tiene ultimo elemento (es vacia)");
        }
        T elemento = rabo.elemento;
        if(longitud==1){
            cabeza=rabo=null;
            longitud--;
        }else{
            rabo=rabo.anterior;
            rabo.siguiente=null;
            longitud--;
        }
        return elemento;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Lista<T> l = new Lista<>();
        IteradorLista<T> it = iteradorLista();
        while(it.hasNext()){
            l.agregaInicio(it.next());
        }
        return l;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        Lista<T> l = new Lista<>();
        IteradorLista<T> it = iteradorLista();
        while(it.hasNext()){
            l.agregaFinal(it.next());
        }
        return l;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
        cabeza =rabo = null;
        longitud=0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        if(cabeza==null){
            throw new NoSuchElementException("La lista no tiene primer elemento (es vacia)");
        }
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        if(rabo==null){
            throw new NoSuchElementException("La lista no tiene ultimo elemento (es vacia)");
        }
        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        if((i<0)|| (i>=longitud)){
            throw new ExcepcionIndiceInvalido("El indice debe ser mayor que 0 y menor que la longitud");
        }
        Nodo n = cabeza; 
        while(i>0){
            i--;
            n = n.siguiente;  
        }
        return n.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        int c = 0;
        Nodo n = cabeza;
        while(n!=null){
            if(n.elemento.equals(elemento)){
                return c;
            }
            c++;
            n=n.siguiente;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        String s="["; 
        Nodo n = cabeza;
        while(n!=null){
            if(n.siguiente!=null){
                s+=n.elemento.toString()+", ";
            }else{
                s+=n.elemento.toString();
            }
            n=n.siguiente;
        }
        return s+"]";
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        if(this.longitud!=lista.getLongitud()){
            return false;
        }
        IteradorLista<T> it1 = iteradorLista();
        IteradorLista<T> it2 = lista.iteradorLista();

        while(it1.hasNext()){
            if(!it1.next().equals(it2.next()))
                return false;
        }
        return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
        return mergeSort(this, comparador);
    }

    private Lista<T> mergeSort(Lista<T> l, Comparator<T> comparador){
        if(l.longitud<=1)
            return l;
        Lista<T> l1 = new Lista<T>();
        Lista<T> l2 = new Lista<T>();

        int mitad  = 0;

        Nodo n = l.cabeza;

        if(l.longitud % 2 == 0){ // lista de longitud par
            while(mitad < l.longitud/2){ // dividimos entre 2 la lista
                l1.agregaFinal(n.elemento); // agregamos primera mitad
                n=n.siguiente;
                mitad++;
            }
            while(mitad < l.longitud){
                l2.agregaFinal(n.elemento); // agregamos segunda mitad
                n=n.siguiente;
                mitad++;
            }
        }
        else{
            while(mitad<(l.longitud-1)/2){ // si la lista es de longitud impar
                l1.agregaFinal(n.elemento); // agregamos la mitad "chica" de la lista
                n=n.siguiente;
                mitad++;
            }
            while(mitad < l.longitud){
                l2.agregaFinal(n.elemento); // agregamos la mitar "grande"
                n=n.siguiente;
                mitad++;
            }
        }
        return mezcla (mergeSort(l1, comparador), mergeSort(l2, comparador), comparador); // hago recursion sobre las nuevas listas 
    }

    /**
     * Metodo auxiliar para merge sort que nos mezcla 2 listas ordenadas en otra ordenada
     * @param l1
     * @param l2
     * @param comparador
     * @return la lista ordenada
     */
    private Lista<T> mezcla(Lista<T> l1, Lista<T> l2, Comparator<T> comparador){
        Lista<T> lfinal = new Lista<>();

        Nodo i = l1.cabeza;
        Nodo j = l2.cabeza;

        while(i!=null && j!=null){
            if(comparador.compare(i.elemento, j.elemento )<=0){
                lfinal.agregaFinal(i.elemento);
                i=i.siguiente;
            }else{
                lfinal.agregaFinal(j.elemento);
                j=j.siguiente;
            }
            if(i==null)
                while(j!=null){
                    lfinal.agregaFinal(j.elemento);
                    j = j.siguiente;
                }
            if(j==null)
                while(i!=null){
                    lfinal.agregaFinal(i.elemento);
                    i = i.siguiente;
                }
        }
        
        return lfinal;
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
        if(comparador.compare(elemento, cabeza.elemento)<0) // pequeña optimizacion por si el elemento a buscar en la lista ordenada es menor que el primero
            return false;
        if(comparador.compare(elemento, rabo.elemento)>0) // pequeña optimizacion por si el elemento a buscar en la lista ordenada es mayor que el ultimo
            return false;    
        if(comparador.compare(elemento, rabo.elemento)==0 || comparador.compare(elemento, cabeza.elemento)==0) // pequeña optimizacion para ver si esta en los extremos y asi solo es una operacion
            return true;

        Nodo n = cabeza; // creamos un nodo para recorrer lista
        while(n!=null && comparador.compare(elemento, n.elemento)>=0){ // recorremos lista, pero si algun elemento de la lista ordenada ya es mayor que el elemento y no lo hemos encontrado entonces no esta
            if(comparador.compare(elemento, n.elemento)==0){
                return true;                                    // si los elementos son iguales trivialmente si esta
            }
            n = n.siguiente;
        }    
        return false;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }
}
