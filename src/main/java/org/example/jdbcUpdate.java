package org.example;

import org.example.modelo.Categoria;
import org.example.modelo.producto;
import org.example.repositorio.ProductoRepositorioImpl;  //-->Imports de clases y repositorios + conexiones a la base de datos
import org.example.repositorio.Repositorio;
import org.example.util.ConexionBaseDeDatos;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class jdbcUpdate {//-->Nombre de clase
    public static void main(String[] args) {

        try (Connection conn = ConexionBaseDeDatos.getInstance();){

            Repositorio<producto> repositorio = new ProductoRepositorioImpl();
            System.out.println();
            System.out.println("========== LISTAR PRODUCTOS ==========");//-->Cumple la misma función que en la MAIN
            repositorio.listar().forEach(System.out::println);

            System.out.println();
            System.out.println("========== ID PRODUCTOS ==========");//-->Cumple la misma función que en la MAIN
            System.out.println(repositorio.porId(1L));
            System.out.println();

            System.out.println("========== EDITAR PRODUCTOS ==========");//-->Cumple la misma función que en la MAIN; pero, en esta parte se editan productos ya existentes
            producto producto = new producto();
            producto.setId(9L); //-->Aquí se inserta el ID a editar
            producto.setNombre("Teclado mécanico Razer ADDA"); //--> Aquí se edita el nombre del producto si se desea
            producto.setPrecio(8000);//-->Se cambia el precio
            Categoria categoria = new Categoria();//-->Aquí ya se le esta asignando una categoría
            categoria.setId(2L);//-->Aquí se le asigna la categoría por ID como se describe en la MAIN
            producto.setCategoria(categoria);
            repositorio.guardar(producto);
            repositorio.listar().forEach(System.out::println);
            System.out.println("========== Producto actualizado con éxito ==========");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
