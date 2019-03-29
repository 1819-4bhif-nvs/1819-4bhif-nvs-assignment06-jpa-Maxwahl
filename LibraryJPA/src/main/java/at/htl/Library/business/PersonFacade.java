package at.htl.Library.business;

import at.htl.Library.model.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PersonFacade {

    @PersistenceContext
    EntityManager em;


    public List<Person> get() {
        TypedQuery<Person> entities = em.createNamedQuery("Person.findAll",Person.class);
        return  entities.getResultList();
    }

    public Person get(long id) {
        TypedQuery<Person> entities = em.createNamedQuery("Person.findById",Person.class);
        entities.setParameter("Id",id);
        System.err.println(entities.toString());
        return  entities.getSingleResult();
    }

    public void remove(Person entity) {
        entity= em.merge(entity);
        em.remove(entity);
    }

    public Person save(Person entity) {
        entity=em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public Person update(Person entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}
