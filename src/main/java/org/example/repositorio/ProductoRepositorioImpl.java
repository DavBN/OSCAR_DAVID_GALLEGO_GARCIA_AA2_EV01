package org.example.repositorio;

import org.example.modelo.producto;
import org.example.util.ConexionBaseDeDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<producto>{

    private Connection getConnection() throws SQLException {
 return ConexionBaseDeDatos.getInstance();
    }
    @Override
    public List<producto> listar() {
        List<producto> productos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {

           while (rs.next()) {
               producto p = crearProducto(rs);
               productos.add(p);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productos;

    }

    @Override
    public producto porId(long id) {
        producto producto = null;

        try(PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM productos WHERE id = ?")) {
              stmt.setLong(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = crearProducto(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void guardar(producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId()>0) {
            sql = "UPDATE productos SET nombre=?, precio=?, WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, fecha_registro) VALUES(?, ?, ?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
             stmt.setString(1,producto.getNombre());
             stmt.setLong(2, producto .getPrecio());

             if (producto.getId() != null && producto.getId()>0) {
                 stmt.setLong(3, producto.getId());
            } else {
                 stmt.setDate(3,new Date(producto.getFecha_registro().getTime()));
             }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminar(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id=?")){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static producto crearProducto(ResultSet rs) throws SQLException {
        producto p = new producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFecha_registro(rs.getDate("fecha_registro"));
        return p;
    }
}
