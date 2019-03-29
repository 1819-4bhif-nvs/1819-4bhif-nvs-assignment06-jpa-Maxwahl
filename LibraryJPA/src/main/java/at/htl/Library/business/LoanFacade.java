package at.htl.Library.business;

import at.htl.Library.model.Loan;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class LoanFacade {

    @PersistenceContext
    EntityManager em;


    public List<Loan> get() {
        TypedQuery<Loan> entities = em.createNamedQuery("Loan.findAll",Loan.class);
        return  entities.getResultList();
    }

    public Loan get(long id) {
        TypedQuery<Loan> entities = em.createNamedQuery("Loan.findById",Loan.class);
        entities.setParameter("Id",id);
        System.err.println(entities.toString());
        return  entities.getSingleResult();
    }

    public void remove(Loan entity) {
        entity= em.merge(entity);
        em.remove(entity);
    }

    public Loan save(Loan entity) {
        entity=em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public Loan update(Loan entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}

