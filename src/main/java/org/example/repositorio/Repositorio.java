package org.example.repositorio;         //--> Nombre del paquete

import java.util.List; //-->Import de javautil para listar

public interface Repositorio<T> { //--> Nombre clase repositorio
    List<T>listar();  //-->Metódo listar
    T porId(long id); //-->¿Como?, POR su ID + Funciones de la base de datos + Metódos para ejecutar una acción
    void guardar(T t); //-->Metódo guardar
    void eliminar(Long id); //-->Metódo eliminar

}
