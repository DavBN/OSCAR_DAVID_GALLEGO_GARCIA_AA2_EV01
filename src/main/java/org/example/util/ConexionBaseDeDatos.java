package org.example.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager; //--> imports de la conexión a la base de datos JavaUtil de SQL
import java.sql.SQLException;

public class ConexionBaseDeDatos {
    private static String url = "jdbc:mysql://localhost:3306/java_evidencia?serverTimezone=UTC"; //-->URL de la conexión a base de datos
    private static String username = "root"; //-->Username de WorkBench
    private static String password = "123456789"; //-->Contraseña de WorkBench, en mi casi si tengo contraseña
    private static Connection connection; //-->Conexiones privadas, esto se maneja debajo del servidor

    public static Connection getInstance() throws SQLException {
        if (connection == null){ //--> Si la conexión existe entonces nos conectamos sino lanzará un pointerexception
            connection = DriverManager.getConnection(url,username,password); //--> Uso de drivemanager para el manejo de la conexión a la base de datos
        }
        return connection; //--> Retorno de conexión
    }
}
