package com.taskflow.taskflow.entity;

public class Categorias {
    private Integer id_categorias;
    private String nombre;

    public Categorias() {
    }

    public Categorias(Integer id_categorias, String nombre) {
        this.id_categorias = id_categorias;
        this.nombre = nombre;
    }

    public Integer getId_categorias() {
        return id_categorias;
    }

    public void setId_categorias(Integer id_categorias) {
        this.id_categorias = id_categorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categorias{" +
                "id_categorias=" + id_categorias +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
