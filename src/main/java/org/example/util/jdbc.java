package org.example.util;

import java.sql.*;

public class jdbc {
    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/java_evidencia?serverTimezone=UTC";
        String username = "root";
        String password = "123456789";


        try (Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM productos")){

            while (resultado.next()){
                System.out.print(resultado.getInt("id"));
                System.out.print(" / ");
                System.out.print(resultado.getString("nombre"));
                System.out.print(" / ");
                System.out.print(resultado.getInt("precio"));
                System.out.print(" / ");
                System.out.println(resultado.getDate("fecha_registro"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
