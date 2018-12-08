package at.htl.Library.rest;

import at.htl.Library.model.Book;
import at.htl.Library.model.CD;
import at.htl.Library.model.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("items")
public class ItemEndpoint {

    @PersistenceContext
    EntityManager em;

    @Path("getItemsByGenre")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsByGenres(@QueryParam("genre") String genre){
        TypedQuery<CD> cdTypedQuery = em.createQuery("SELECT c from CD c where c.genre like :GENRE",CD.class);
        cdTypedQuery.setParameter("GENRE",genre);
        List<CD> cds = cdTypedQuery.getResultList();
        TypedQuery<Book> bookTypedQuery = em.createQuery("SELECT b from Book b where b.genre like :GENRE", Book.class);
        bookTypedQuery.setParameter("GENRE",genre);
        List<Book> books = bookTypedQuery.getResultList();

        if(cds.size()==0 && books.size()==0){
            return Response.status(404).build();
        }
        List<Item> items = new ArrayList<>();
        items.addAll(cds);
        items.addAll(books);
        return Response.status(200).entity(items).build();

    }
}
