package encapsulacion;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
    private String username;
    private String nombre;
    private Articulo password;
    private boolean administrator;
    private boolean author;

    public Usuario() {
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


}
