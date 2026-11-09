package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.Vegetable;
import org.acme.repositories.VegetableRepository;

@Path("/vegetables")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VegetableResource {

    @Inject
    VegetableRepository vegetableRepository;

    @GET
    public Response getAll() {
        return Response.ok(vegetableRepository.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Vegetable vegetable = vegetableRepository.findById(id);
        if (vegetable == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vegetable).build();
    }

    @POST
    public Response create(Vegetable vegetable) {
        return Response.status(Response.Status.CREATED).entity(vegetableRepository.create(vegetable)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Vegetable vegetable) {
        Vegetable updated = vegetableRepository.update(id, vegetable);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        if (!vegetableRepository.deleteById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
