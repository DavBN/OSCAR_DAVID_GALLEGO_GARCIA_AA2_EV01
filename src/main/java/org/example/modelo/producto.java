package org.example.modelo;

import java.util.Date;

public class producto {
    private Long id;
    private String nombre;
    private Integer precio;
    private Date fechaRegistro;

    public producto() {
    }

    public producto(Long id, String nombre, Integer precio, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Date getFecha_registro() {
        return fechaRegistro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fechaRegistro = fecha_registro;
    }
}
