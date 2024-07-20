package rest;

import clients.ClienteRestClient;
import clients.ProductoRestClient;
import db.OrdenCompra;
import dtos.OrdenCompraDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import repo.OrdenCompraRepository;

import java.util.List;
import java.util.stream.Collectors;

@Path("/ordencompras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class OrdenCompraRest {

    @Inject
    OrdenCompraRepository repo;

    @Inject
    @RestClient
    ProductoRestClient productoRestClient;

    @Inject
    @RestClient
    ClienteRestClient clienteRestClient;

    @GET
    public List<OrdenCompraDto> findAll() {
        return repo.streamAll()
                .map(ordenCompra->{
                    var producto = productoRestClient.findById(Integer.valueOf(ordenCompra.getProductoId()));
                    var cliente = clienteRestClient.findById(ordenCompra.getClienteId());

                    var dto = OrdenCompraDto.from(ordenCompra);

                    dto.setProductoId(String.valueOf(producto.getId()));
                    dto.setClienteId(cliente.getId());

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id){
        var ordencompra = repo.findByIdOptional(id);
        if(ordencompra.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ordencompra.get()).build();
    }

    @POST
    public Response create(OrdenCompra obj){
        obj.setId(null);
        repo.persist(obj);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, OrdenCompra obj){
        OrdenCompra ordencompra = repo.findById(id);

        ordencompra.setClienteId((obj.getClienteId()));
        ordencompra.setProductoId((obj.getProductoId()));
        ordencompra.setPrecio((obj.getPrecio()));

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){
        repo.deleteById(id);

        return Response.ok().build();
    }

}