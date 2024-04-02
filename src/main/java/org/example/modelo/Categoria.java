package org.example.modelo; //-->Paquete modelo

public class Categoria { //-->Clase cateogoria
    private Long id; //Metódos

    public Long getId() { //Metódos
        return id;
    }

    public void setId(Long id) { //Metódos
        this.id = id;
    }

    public String getNombre() { //Metódos
        return nombre;
    }

    public void setNombre(String nombre) { //Metódos
        this.nombre = nombre;
    }

    private String nombre;
}
