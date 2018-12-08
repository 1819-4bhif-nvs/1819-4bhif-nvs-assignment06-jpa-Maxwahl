package at.htl.Library.business;

import at.htl.Library.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Startup
@Singleton
public class InitBean {

    @PersistenceContext
    EntityManager em;

    public InitBean() {
    }

    @PostConstruct
    private void init(){
        System.err.print("******** hello");
        CD cd =new CD("test",9.11,"mozart",123.1);
        em.persist(cd);
        Person p = new Person("Meier");
        em.persist(p);
        Exemplar e = new Exemplar(cd, Weariness.undamaged);
        em.persist(e);
        Loan l = new Loan(p,e, LocalDate.now(),LocalDate.now());
        em.persist(l);
    }
}
