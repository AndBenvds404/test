package rest;
import db.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repo.ClienteRepository;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class ClienteRest {

    @Inject
    ClienteRepository repo;

    @GET
    public List<Cliente> findAll(){
        return repo.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id){
        var cliente = repo.findByIdOptional(id);
        if(cliente.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cliente.get()).build();
    }

    @POST
    public Response create(Cliente obj){
        obj.setId(null);
        repo.persist(obj);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Cliente obj){
        Cliente cliente = repo.findById(id);

        cliente.setNombre((obj.getNombre()));
        cliente.setApellido((obj.getApellido()));
        cliente.setDireccion((obj.getDireccion()));

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){
        repo.deleteById(id);

        return Response.ok().build();
    }

}
