
package MiModaBD;

import clases.Producto;
import clases.ResponseEntity;
import clases.Usuario;
import com.mysql.jdbc.Connection;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

public class DAOModa {
    private static Connection con = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = "jdbc:mysql://localhost:3306/mi_moda";
        url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf-8";
        String usuario = "root";
        String password = "1234";
        try {
            con = (Connection) DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // **************************
    // Usuarios
    public static ArrayList<Usuario> getUsuarios() {

        Usuario objeto = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();;
        try {
            
            // Crear Statement de la Consulta
            String sentenciaSQL = "SELECT * FROM usuarios";
            Statement statement = con.createStatement();

            // Resulset
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            while (rs.next()) {
                // Cargar valores
                objeto = new Usuario();
                objeto.setId(rs.getInt("id"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setContrasena(rs.getString("contrasena"));
                objeto.setApellidos(rs.getString("apellidos"));
                objeto.setUsuario(rs.getString("usuario"));
                objeto.setImagen(rs.getString("imagen"));
                
                usuarios.add(objeto);
            }
            rs.close();
            
            if (usuarios == null) {
                System.out.println("No existe ningún objeto");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    
    public static Response addUsuario(Usuario user) throws UnsupportedEncodingException {
        String decodedToNombre = new String(user.getNombre().getBytes("ISO-8859-1"), "UTF-8");
        String decodedToApellido = new String(user.getApellidos().getBytes("ISO-8859-1"), "UTF-8");
        String decodedToUsuario = new String(user.getUsuario().getBytes("ISO-8859-1"), "UTF-8");
        String decodedToContraseña = new String(user.getContrasena().getBytes("ISO-8859-1"), "UTF-8");
        
        ResponseEntity entity = new ResponseEntity();
        boolean encontrado = false;
        for (Usuario u : getUsuarios()) {
            if (u.getUsuario().equals(user.getUsuario()))
            {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            try {
                int size = getMaxIdTabla("usuarios") + 1;
                String insertTableSQL = "INSERT INTO usuarios"
                        + "(id, nombre, contrasena, apellidos, usuario, imagen) VALUES"
                        + "("+size+",'"+decodedToNombre+"','"+decodedToContraseña+"','"+decodedToApellido+"','"+decodedToUsuario+"','"+user.getImagen()+"')"
                        + "";
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
            }
            entity.setStatus(Response.Status.NO_CONTENT.getStatusCode());
            return Response.status(Response.Status.NO_CONTENT).entity(entity).build();
        } else {
            entity.setStatus(Response.Status.NOT_FOUND.getStatusCode());
            entity.setMensaje("El usuario ya existe");
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }
    }
    
    public static Response ActualizarUsuario(Usuario user) throws UnsupportedEncodingException {
        String decodedToNombre = new String(user.getNombre().getBytes("ISO-8859-1"), "UTF-8");
        String decodedToApellido = new String(user.getApellidos().getBytes("ISO-8859-1"), "UTF-8");
        String decodedToUsuario = new String(user.getUsuario().getBytes("ISO-8859-1"), "UTF-8");
        String decodedToContraseña = new String(user.getContrasena().getBytes("ISO-8859-1"), "UTF-8");
        
        ResponseEntity entity = new ResponseEntity();
        boolean encontrado = false;
        for (Usuario u : getUsuarios()) {
            if (u.getId() == user.getId())
            {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            try {
                String insertTableSQL = "UPDATE usuarios SET "
                        + "nombre='"+decodedToNombre+"' ,"
                        + "imagen='"+user.getImagen()+"',"
                        + "apellidos='"+decodedToApellido+"',"
                        + "contrasena='"+decodedToContraseña+"',"
                        + "usuario='"+decodedToUsuario+"'"
                        + "WHERE id=" + user.getId();
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
            }
            entity.setStatus(Response.Status.NO_CONTENT.getStatusCode());
            return Response.status(Response.Status.NO_CONTENT).entity(entity).build();
        } else {
            entity.setStatus(Response.Status.NOT_FOUND.getStatusCode());
            entity.setMensaje("El Usuario no existe");
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }
    }
    
    // **************************
    // Productos
    public static ArrayList<Producto> getProductos() {

        Producto objeto = null;
        ArrayList<Producto> productos = new ArrayList<>();;
        try {
            
            // Crear Statement de la Consulta
            String sentenciaSQL = "SELECT * FROM productos;";
            Statement statement = con.createStatement();

            // Resulset
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            while (rs.next()) {
                // Cargar valores
                objeto = new Producto();
                objeto.setId(rs.getInt("id"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setDescripcion(rs.getString("descripcion"));
                objeto.setPrecio(rs.getDouble("precio"));
                objeto.setStock(rs.getInt("stock"));
                objeto.setImagen(rs.getString("imagen"));
                
                productos.add(objeto);
            }
            rs.close();
            
            if (productos == null) {
                System.out.println("No existe ningún objeto");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
    public static Response addProducto(Producto producto) throws UnsupportedEncodingException {
        String decodedToNombre = new String(producto.getNombre().getBytes("ISO-8859-1"), "UTF-8");
        String decodedToDescripcion = new String(producto.getDescripcion().getBytes("ISO-8859-1"), "UTF-8");
        
        ResponseEntity entity = new ResponseEntity();
        boolean encontrado = false;
        for (Producto p : getProductos()) {
            if (p.getNombre().equals(producto.getNombre()))
            {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            try {
                int size = getMaxIdTabla("productos") + 1;
                String insertTableSQL = "INSERT INTO productos"
                        + "(id, nombre, imagen, descripcion, stock, precio) VALUES"
                        + "("+size+",'"+decodedToNombre+"','"+producto.getImagen()+"','"+decodedToDescripcion+"',"+producto.getStock()+","+producto.getPrecio()+")";
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
            }
            entity.setStatus(Response.Status.NO_CONTENT.getStatusCode());
            return Response.status(Response.Status.NO_CONTENT).entity(entity).build();
        } else {
            entity.setStatus(Response.Status.NOT_FOUND.getStatusCode());
            entity.setMensaje("El Producto ya existe");
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }
    }
    
    public static Response ActualizarProducto(int id,Producto producto) throws UnsupportedEncodingException {
        String decodedToNombre = new String(producto.getNombre().getBytes("ISO-8859-1"), "UTF-8");
        String decodedToDescripcion = new String(producto.getDescripcion().getBytes("ISO-8859-1"), "UTF-8");
        ResponseEntity entity = new ResponseEntity();
        boolean encontrado = false;
        for (Producto p : getProductos()) {
            if (p.getId() == id)
            {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            try {
                String insertTableSQL = "UPDATE productos SET "
                        + "nombre='"+decodedToNombre+"' ,"
                        + "imagen='"+producto.getImagen()+"',"
                        + "descripcion='"+decodedToDescripcion+"',"
                        + "stock="+producto.getStock()+","
                        + "precio="+producto.getPrecio()+""
                        + "WHERE id=" + producto.getId();
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
            }
            entity.setStatus(Response.Status.NO_CONTENT.getStatusCode());
            return Response.status(Response.Status.NO_CONTENT).entity(entity).build();
        } else {
            entity.setStatus(Response.Status.NOT_FOUND.getStatusCode());
            entity.setMensaje("El Producto no existe");
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }
    }
    public static Response EliminarProducto(int idProducto) {
        ResponseEntity entity = new ResponseEntity();
        boolean encontrado = false;
        for (Producto p : getProductos()) {
            if (p.getId() == idProducto)
            {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            try {
                String insertTableSQL = "DELETE FROM productos "
                        + "WHERE id=" + idProducto;
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
            }
            entity.setStatus(Response.Status.NO_CONTENT.getStatusCode());
            return Response.status(Response.Status.NO_CONTENT).entity(entity).build();
        } else {
            entity.setStatus(Response.Status.NOT_FOUND.getStatusCode());
            entity.setMensaje("El Producto no existe");
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }
    }
    
    public static int getMaxIdTabla(String nombreTabla) {

        int maximo = 0;
        try {
            
            // Crear Statement de la Consulta
            String sentenciaSQL = "SELECT max(id) AS 'idMax' FROM "+nombreTabla+";";
            Statement statement = con.createStatement();

            // Resulset
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            if (rs.next()) {
                // Cargar valores
                maximo = rs.getInt("idMax");
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOModa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maximo;
    }
    
    
}
