package at.htl.Library.rest;
import at.htl.Library.business.ItemFacade;
import at.htl.Library.model.Item;
import at.htl.Library.model.Person;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("items")
public class ItemEndpoint {
    @Inject
    ItemFacade itemFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<Item> entities = itemFacade.get();
        return Response.ok().entity(entities).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") long id){
        Item entity = itemFacade.get(id);
        if(entity != null){
            return Response.ok().entity(entity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Item entity = itemFacade.get(id);
        if(entity != null){
            itemFacade.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Item entity){
        try {
            entity = itemFacade.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Item entity){
        entity = itemFacade.update(entity);
        return Response.ok().entity(entity).build();
    }
}


