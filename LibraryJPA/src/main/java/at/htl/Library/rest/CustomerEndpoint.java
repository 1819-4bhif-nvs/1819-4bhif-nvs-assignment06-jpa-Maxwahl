package at.htl.Library.rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import at.htl.Library.model.Person;

@Path("customers")
@Stateless
public class CustomerEndpoint {
    @PersistenceContext
    EntityManager em;

    @DELETE
    @Path("deleteCustomer/{id}")
    public Response deleteCustomer(@PathParam("id") int id){
        TypedQuery<Person> pquery = em.createNamedQuery("Person.findById",Person.class);
        pquery.setParameter("Id",(long)id);
        Person p = pquery.getSingleResult();
        if(p != null){
            em.remove(p);
        }
        return Response.status(204).build();
    }
}