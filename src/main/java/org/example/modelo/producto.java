package org.example.modelo;

import java.util.Date;      //-->Imports

public class producto { //--> Clase producto que contiene los atributos
    private Long id; //-->Privado
    private String nombre; //-->Privado
    private Integer precio; //-->Privado      //Se usan solamente cuándo se manden a llamar
    private Date fechaRegistro; //-->Privado
    private  Categoria categoria; //-->Privado


    public producto() { //-->Público

    }

    public producto(Long id, String nombre, Integer precio, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;              //-->Constructor
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
    }
    public Long getId() {
        return id;
    }  //-->Metódos Getter y Setter

    public void setId(Long id) {
        this.id = id;
    }  //-->Metódos Getter y Setter

    public String getNombre() {
        return nombre;
    } //-->Metódos Getter y Setter

    public void setNombre(String nombre) {
        this.nombre = nombre;
    } //-->Metódos Getter y Setter

    public Integer getPrecio() {
        return precio;
    } //-->Metódos Getter y Setter

    public void setPrecio(Integer precio) {
        this.precio = precio;
    } //-->Metódos Getter y Setter

    public Date getFecha_registro() {
        return fechaRegistro;
    } //-->Metódos Getter y Setter

    public void setFecha_registro(Date fecha_registro) { //-->Metódos Getter y Setter
        this.fechaRegistro = fecha_registro;
    }

    public Categoria getCategoria() { //-->Metódos Getter y Setter
        return categoria;
    }

    public void setCategoria(Categoria categoria) { //-->Metódos Getter y Setter
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return  id +
                " " +
                nombre +
                " " +
                precio +                      //-->Constructor
                " " +
                fechaRegistro +
                " " + categoria.getNombre();

    }
}
