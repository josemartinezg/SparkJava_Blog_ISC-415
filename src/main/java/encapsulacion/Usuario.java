package encapsulacion;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
    private String username;
    private String nombre;
    private Articulo password;
    private boolean administrator;
    private boolean author;

    public Usuario() {}

    public Usuario(String username, String nombre, Articulo password, boolean administrator, boolean author) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.administrator = administrator;
        this.author = author;
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

    public Articulo getPassword() {
        return password;
    }

    public void setPassword(Articulo password) {
        this.password = password;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public boolean isAuthor() {
        return author;
    }
    public void setAuthor(boolean author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password=" + password +
                ", administrator=" + administrator +
                ", autohor=" + author +
                '}';
    }
}
