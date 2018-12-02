package at.htl.Library.business;

import at.htl.Library.model.CD;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        em.persist(new CD("test",9.11,"mozart",123.1));
    }
}
