package at.htl.Library.rest;

import at.htl.Library.model.Exemplar;
import at.htl.Library.model.Loan;
import at.htl.Library.model.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("loans")
@Stateless
public class LoanEndpoint {

    @PersistenceContext
    EntityManager em;
    @Path("getUnfinishedLoansByCustomer/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnfinishedLoansByCustomer(@PathParam("id")int id){
        TypedQuery<Loan> lquery = em.createNamedQuery("Loan.findUnfinishedByCustomer",Loan.class);
        lquery.setParameter("Id",(long)id);
        List<Loan> loans = lquery.getResultList();
        if(loans.size()==0){
            return Response.status(404).build();
        }
        return Response.status(200).entity(loans).build();
    }

    @Path("finishLoan/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response finishLoan(@PathParam("id")int id){
        TypedQuery<Loan> lquery = em.createNamedQuery("Loan.findById",Loan.class);
        lquery.setParameter("Id",(long)id);
        Loan l = lquery.getSingleResult();
        l.setDoAR(LocalDate.now());
        return Response.status(200).entity(l).build();
    }

    @Path("createLoan")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLoan(@QueryParam("pid") int pid,@QueryParam("eid") int eid){
        TypedQuery<Person> pquery = em.createNamedQuery("Person.findById",Person.class);
        pquery.setParameter("Id",(long)pid);
        Person p = pquery.getSingleResult();
        TypedQuery<Exemplar> exemplarTypedQuery = em.createQuery("Select e from Exemplar e where  e.Id=:Id",Exemplar.class);
        exemplarTypedQuery.setParameter("Id",(long)eid);
        Exemplar e = exemplarTypedQuery.getSingleResult();
        Loan l = new Loan(p,e,LocalDate.now(),LocalDate.now().plusDays(5));
        em.persist(l);
        return Response.status(201).entity(l).build();
    }
}
