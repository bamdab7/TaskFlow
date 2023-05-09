package com.taskflow.taskflow.pojo;

public class Usuarios {
    private Integer id_usuario;
    private String username;
    private String nombre;
    private String email;
    private String password;

    public Usuarios() {
    }

    public Usuarios(Integer id_usuario,String username, String nombre, String email, String password) {
        this.id_usuario = id_usuario;
        this.username = username;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "id_usuario=" + id_usuario +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
