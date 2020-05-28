/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiModaBD;

import clases.Producto;
import clases.Usuario;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Mahroz
 */
@Path("apirest")
public class ApiRest {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiRest
     */
    public ApiRest() {
    }

    @GET
    @Path("usuarios")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Usuario> getUsuarios() {
        return DAOModa.getUsuarios();
    }
    @POST
    @Path("add_usuario")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addUsuario(Usuario user) throws Exception{
        return DAOModa.addUsuario(user);
    }
    @PUT
    @Path("actualizar_usuario")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response ActualizarUsuario(Usuario u) throws UnsupportedEncodingException {
        return DAOModa.ActualizarUsuario(u);
    }
    
    @GET
    @Path("productos")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Producto> getProductos() {
        return DAOModa.getProductos();
    }
    
    @POST
    @Path("add_producto")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addProducto(Producto p) throws Exception{
        return DAOModa.addProducto(p);
    }

    @PUT
    @Path("actualizar_producto/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response ActualizarProducto(@PathParam("id") int id, Producto p) throws UnsupportedEncodingException {
        return DAOModa.ActualizarProducto(id, p);
    }
    
    @DELETE
    @Path("eliminar_producto/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response EliminarProducto(@PathParam("id") int id) {
        return DAOModa.EliminarProducto(id);
    }
    
    
}
