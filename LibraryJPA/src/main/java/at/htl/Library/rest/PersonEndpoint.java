package at.htl.Library.rest;
import at.htl.Library.business.PersonFacade;
import at.htl.Library.model.Person;

        import javax.inject.Inject;
        import javax.persistence.PersistenceException;
        import javax.ws.rs.*;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;
        import java.util.List;

@Path("persons")
public class PersonEndpoint {
    @Inject
    PersonFacade personFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<Person> entities = personFacade.get();
        return Response.ok().entity(entities).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") long id){
        Person entity = personFacade.get(id);
        if(entity != null){
            return Response.ok().entity(entity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Person entity = personFacade.get(id);
        if(entity != null){
            personFacade.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Person entity){
        try {
            entity = personFacade.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Person entity){
        entity = personFacade.update(entity);
        return Response.ok().entity(entity).build();
    }
}

