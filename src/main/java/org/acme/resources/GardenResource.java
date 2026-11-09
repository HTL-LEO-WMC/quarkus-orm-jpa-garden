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
import org.acme.entities.Garden;
import org.acme.repositories.GardenRepository;

@Path("/gardens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GardenResource {

    @Inject
    GardenRepository gardenRepository;

    @GET
    public Response getAll() {
        return Response.ok(gardenRepository.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Garden garden = gardenRepository.findById(id);
        if (garden == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(garden).build();
    }

    @POST
    public Response create(Garden garden) {
        return Response.status(Response.Status.CREATED).entity(gardenRepository.create(garden)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Garden garden) {
        Garden updated = gardenRepository.update(id, garden);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        if (!gardenRepository.deleteById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
