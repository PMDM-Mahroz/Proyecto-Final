
package clases;

import java.io.Serializable;

public class Usuario implements Serializable{
    private int id;
    private String usuario;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String imagen;

    public Usuario() {
    }

    
    
    public Usuario(int id, String usuario, String contraseña, String nombre, String apellidos, String imagen) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contraseña;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contraseña) {
        this.contrasena = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    

}
