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
import org.acme.entities.Plot;
import org.acme.repositories.PlotRepository;

@Path("/plots")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlotResource {

    @Inject
    PlotRepository plotRepository;

    @GET
    public Response getAll() {
        return Response.ok(plotRepository.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Plot plot = plotRepository.findById(id);
        if (plot == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(plot).build();
    }

    @POST
    public Response create(Plot plot) {
        return Response.status(Response.Status.CREATED).entity(plotRepository.create(plot)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Plot plot) {
        Plot updated = plotRepository.update(id, plot);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        if (!plotRepository.deleteById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
