# Estructuras de Datos. Proyecto 1 : sort en java
## Sosa Romo Juan Mario

### Fecha de entrega: viernes 17 de Marzo, 2023

El proyecto implementa un ordenador lexicografico parecido a sort en Unix utilizando java y terminal, funciona con 1 o mas archivos de texto como entradas, o directamente la entrada estandar, y que imprima su su salida en la salida estandar.


### Uso

El proyecto se puede compilar utilizando mvn compile.

```
$ mvn compile
```

No tiene pruebas unitarias.


Para generar el .jar en el subdirectorio target se usa:

```
$ mvn install
```

Para ejecutar el programa se invoca el comando

```
$ java -jar target/proyecto1.jar XXXXX.txt -banderas*

o

$ cat XXXXX.txt | java -jar target/proyecto1.jar -banderas*

```

### Que  hace?

* El programa nos ordena el archivo de texto por linea NO por palabra
* Las lineas vacias tienen prioridad sobre las no vacias
* Los espacios se ignoran cuando queremos ordenar las cadenas pero al imprimirlas si los conservamos
* Acentos, eñes y dieresis se toman como sus vocales y la letra n.
* Puede recibir mas de una entrada y si esto pasa, el programa lo entendera como un archivo conformado por sus componentes seguidas en orden.
* Puede recibir una ruta absoluta o relativa siempre y cuando exista.


### Banderas

Son totalmente OPCIONALES y pueden ir en cualquier orden; se comportan como en los comandos de Unix, las que admite son

* `-r` El programa imprimira las lineas en orden reverso
* `-o` + <ejemplo.txt> escribe la salida a `ejemplo.txt`, si ya existia lo REESCRIBE
