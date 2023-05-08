package com.taskflow.taskflow.pojo;

public class Tareas {
    private Integer id_tareas;
    private String titulo;
    private String descripcion;
    private String estado;
    private int id_usuarios;
    private int id_categoria; //categoria?

    public Tareas() {
    }

    public Tareas(Integer id_tareas, String titulo, String descripcion, String estado, int id_usuarios, int id_categoria) {
        this.id_tareas = id_tareas;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.id_usuarios = id_usuarios;
        this.id_categoria = id_categoria;
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

    public int getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(int id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    @Override
    public String toString() {
        return "Tareas{" +
                "id_tareas=" + id_tareas +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", id_usuarios=" + id_usuarios +
                ", id_categoria=" + id_categoria +
                '}';
    }
}
