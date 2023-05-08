package com.taskflow.taskflow.entity;

public class Tareas {
    private Integer id_tareas;
    private String titulo;
    private String descripcion;
    private String estado;
    private Usuarios id_usuarios;
    private Categorias id_categorias;

    public Tareas() {
    }

    public Tareas(Integer id_tareas, String titulo, String descripcion, String estado, Usuarios id_usuarios, Categorias id_categorias) {
        this.id_tareas = id_tareas;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.id_usuarios = id_usuarios;
        this.id_categorias = id_categorias;
    }

    public Integer getId_tareas() {
        return id_tareas;
    }

    public void setId_tareas(Integer id_tareas) {
        this.id_tareas = id_tareas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public Categorias getId_categorias() {
        return id_categorias;
    }

    public void setId_categorias(Categorias id_categorias) {
        this.id_categorias = id_categorias;
    }

    @Override
    public String toString() {
        return "Tareas{" +
                "id_tareas=" + id_tareas +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", id_usuarios=" + id_usuarios +
                ", id_categorias=" + id_categorias +
                '}';
    }
}
