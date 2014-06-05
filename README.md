# Ejemplo de Karaku

Ejemplo de utilización del framework karaku.

Para utilizar este proyecto, se debe:

* git clone https://github.com/fpuna-cia/karaku-example.git example
* cd example
* git submodule update --init

Luego se puede importar desde `Eclipse`, como proyecto `maven`, o 
para probar directamente el proyecto ejecutar:

* mvn package jboss-as:deploy

Para esto se requiere un servidor jboss escuchando en localhost.


# Nuevo proyecto

Para crear un nuevo proyecto que utilice karaku, se puede utilizar
este proyecto de ejemplo, y modificar todas las partes donde se 
utilice la palabra `project` por el nombre del nuevo proyecto, un
arquetipo para generar la estructura automaticamente esta en proceso
de desarrollo


# TODO

* Agregar arquetipo de creación
* Creación de un script que ejecute todos los pasos automáticamente.
* Agregar ejemplos de 
  * Web-Services
  * Menú distribuido
  * Replicación
  * Picker
  * DAO (operaciones más complejas)
