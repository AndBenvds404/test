package rest;

import db.Producto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repo.ProductoRepository;

import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class ProductoRest {

    @Inject
    ProductoRepository repo;

    @GET
    public List<Producto> findAll(){
        return repo.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id){
        var producto = repo.findByIdOptional(id);
        if(producto.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(producto.get()).build();
    }

    @POST
    public Response create(Producto obj){
        obj.setId(null);
        repo.persist(obj);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Producto obj){
        Producto producto = repo.findById(id);

        producto.setNombre((obj.getNombre()));
        producto.setPrecio((obj.getPrecio()));

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){
        repo.deleteById(id);

        return Response.ok().build();
    }

}