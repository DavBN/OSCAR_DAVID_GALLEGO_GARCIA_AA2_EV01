package org.example;

import org.example.modelo.producto;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;            //-->Imports necesarios de los paquetes de las clases y repositorios
import org.example.util.ConexionBaseDeDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class jdbcDelete { //--> Nombramiento de la clase
    public static void main(String[] args) {

        try (Connection conn = ConexionBaseDeDatos.getInstance();){   //-->Se valida nuevamente la conexión por medio del Catch

            Repositorio<producto> repositorio = new ProductoRepositorioImpl();

            System.out.println();
            System.out.println("========== LISTAR PRODUCTOS =========="); //-->Cumple la misma función que en la clase MAIN, lista todos los productos
            repositorio.listar().forEach(System.out::println);

            System.out.println();
            System.out.println("========== ID PRODUCTOS  =========="); //-->Cumple la misma función que en la clase MAIN, por el ID obtiene el producto
            System.out.println(repositorio.porId(1L));
            System.out.println();

            System.out.println("========== ELIMINAR PRODUCTOS ==========");//-->Cumple la misma función que en la clase MAIN; pero, aquí se eliminará el producto por ID
            repositorio.eliminar(20L);
            repositorio.listar().forEach(System.out::println);
            System.out.println("========== Producto eliminado con éxito ==========");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

//Se debe tener en cuenta que al eliminar un producto me seguirá mostrando los demás pero en consola se podrá apreciar que se eliminó, también en la base de datos