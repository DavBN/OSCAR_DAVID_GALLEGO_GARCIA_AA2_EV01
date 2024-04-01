package org.example.util;

import org.example.modelo.producto;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;

import java.sql.*;

public class jdbc {
    public static void main(String[] args) {

        try (Connection conn = ConexionBaseDeDatos.getInstance();){

            Repositorio<producto> repositorio = new ProductoRepositorioImpl();
            repositorio.listar().forEach(System.out::println);

            System.out.println(repositorio.porId(1L));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
