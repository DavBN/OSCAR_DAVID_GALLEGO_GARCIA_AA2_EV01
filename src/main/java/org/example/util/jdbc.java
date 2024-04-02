package org.example.util;

import org.example.modelo.Categoria;
import org.example.modelo.producto;                            //Estos son los imports de los paquetes y la implementación de las clases, como también
import org.example.repositorio.ProductoRepositorioImpl;        //la importación de la base de datos y el javautil de SQL.
import org.example.repositorio.Repositorio;
import java.sql.*;
import java.util.Date;

public class jdbc {
    public static void main(String[] args) {

        try (Connection conn = ConexionBaseDeDatos.getInstance();){ //---> Conexión base de datos por medio de un Catch, si no hay conexión nos arroja un PointerException.

            Repositorio<producto> repositorio = new ProductoRepositorioImpl();
            System.out.println();
            System.out.println("========== LISTAR PRODUCTOS=========="); //-->Aquí nos listara todos los productos eliminados, actualizados y insertados (SIEMPRE).
            repositorio.listar().forEach(System.out::println);

            System.out.println();
            System.out.println("========== ID PRODUCTOS =========="); //--> Nos mostrará el id que tengamos en la línea de abajo (1,2,3)ETC.(MANUAL)
            System.out.println(repositorio.porId(1L));
            System.out.println();

            System.out.println("========== INSERTAR PRODUCTOS =========="); //-->Aquí se hace la inserción de nuevos productos, solo con el nombre y el precio(MANUAL).
            producto producto = new producto();
            producto.setNombre("Teclado mécanico OURUS VERSION 1");
            producto.setPrecio(20000);
            producto.setFecha_registro(new Date());
            Categoria categoria = new Categoria();
            categoria.setId(3L);
            producto.setCategoria(categoria);//--> Aquí se le asigna una categoría de acuerdo a la base de datos (deportes,tecnología, computacionPC).
            repositorio.guardar(producto);
            repositorio.listar().forEach(System.out::println);
            System.out.println("========== Producto agregado con éxito ==========");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

//Esta es la clase "MAIN", como se observa hay 3 Paquetes, Modelo,Repostiorio,Util, El delete y ek update salen del paquete de ORG.EXAMPLE