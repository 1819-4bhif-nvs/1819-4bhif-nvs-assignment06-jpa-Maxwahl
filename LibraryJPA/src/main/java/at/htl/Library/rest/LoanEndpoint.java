package at.htl.Library.rest;
import at.htl.Library.business.LoanFacade;
import at.htl.Library.model.Loan;
import at.htl.Library.model.Person;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("loans")
public class LoanEndpoint {
    @Inject
    LoanFacade loanFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<Loan> entities = loanFacade.get();
        return Response.ok().entity(entities).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") long id){
        Loan entity = loanFacade.get(id);
        if(entity != null){
            return Response.ok().entity(entity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Loan entity = loanFacade.get(id);
        if(entity != null){
            loanFacade.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Loan entity){
        try {
            entity = loanFacade.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Loan entity){
        entity = loanFacade.update(entity);
        return Response.ok().entity(entity).build();
    }
}

