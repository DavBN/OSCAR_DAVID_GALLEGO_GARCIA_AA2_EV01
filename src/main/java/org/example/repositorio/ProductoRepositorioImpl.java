package org.example.repositorio;

import org.example.modelo.Categoria;
import org.example.modelo.producto;
import org.example.util.ConexionBaseDeDatos;  //-->Imports de repositorio, paquetes y clases

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<producto>{ //-->Nombre clase y que se implemente el repositorio asignando producto

    private Connection getConnection() throws SQLException {
 return ConexionBaseDeDatos.getInstance();
    }
    @Override
    public List<producto> listar() { //-->Listar los productos de la base de datos
        List<producto> productos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " + //-->Con su nombre y categoría
                     "inner join categorias as c ON (p.categoria_id = c.id)")) { //--> Se hace la consulta a la base de datos para que traiga todos los productos

           while (rs.next()) {
               producto p = crearProducto(rs); //-->Cuándo se cree un producto el ciclo while se ejecuta si se crea se agrega a lista
               productos.add(p);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productos; //--> Retorna todos los productos existentes en la base de datos

    }

    @Override
    public producto porId(long id) { //-->Por ID
        producto producto = null;

        try(PreparedStatement stmt = getConnection().
                prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                "inner join categorias as c ON (p.categoria_id = c.id)  WHERE p.id = ?")) { //-->La misma consulta para poder agregar o crear productos
              stmt.setLong(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = crearProducto(rs); //-->Se pregunta si es correcto o no para luego crear la petición y retornar el producto
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void guardar(producto producto) { //-->Metódo guardar
        String sql;
        if (producto.getId() != null && producto.getId()>0) {
            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=? WHERE id=?"; //-->Se hace el UPDATE de acuerdo a lo que se registre en jdbcUpdate
        } else {
            sql = "INSERT INTO productos(nombre, precio,  categoria_id, fecha_registro) VALUES(?, ?, ?, ?)"; //-->Se hace el insert a la base de datos
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
             stmt.setString(1,producto.getNombre());
             stmt.setLong(2, producto .getPrecio());         //--> El 1,2,3 representan los valores de la base de datos en su orden de tablas
             stmt.setLong(3, producto.getCategoria().getId());

             if (producto.getId() != null && producto.getId()>0) {
                 stmt.setLong(4, producto.getId()); //--> El 4 es la categoria_id, esto debe ir así ya que a la hora de los resultados nos debe mostrar el orden
            } else {
                 stmt.setDate(4,new Date(producto.getFecha_registro().getTime()));
             }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminar(Long id) { //-->Eliminar
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id=?")){ //-->Eliminación de la base de datos
            stmt.setLong(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static producto crearProducto(ResultSet rs) throws SQLException { //-->Creación del producto como también eliminación, upgrade del producto
        producto p = new producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFecha_registro(rs.getDate("fecha_registro"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombre(rs.getString("categoria"));
        p.setCategoria(categoria);
        return p; //--> Retorna el producto de acuerdo a lo que se ponga en las clases previas
    }
}

//Este es la implemetanción del repostorio y ejecución de las clases de acuerdo a la conexión de la base de datos