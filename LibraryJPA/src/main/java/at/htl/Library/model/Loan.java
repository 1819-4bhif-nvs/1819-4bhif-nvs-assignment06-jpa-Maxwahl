package at.htl.Library.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Loan.findById",query = "select l from Loan l where l.Id= :Id"),
        @NamedQuery(name = "Loan.findAll",query = "select l from Loan l")
})
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
    Person person;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Exemplar> exemplars;
    LocalDate doT;
    LocalDate doAR;
    LocalDate doR;

    public Loan(Person person, List<Exemplar> exemplars, LocalDate doT, LocalDate doR) {
        this.person = person;
        this.exemplars = exemplars;
        this.doT = doT;
        this.doR = doR;
    }

    public Loan() {
    }

    //region getter and setter
    public Long getId() {
        return Id;
    }

    private void setId(Long id) {
        Id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getDoT() {
        return doT;
    }

    public void setDoT(LocalDate doT) {
        this.doT = doT;
    }

    public LocalDate getDoR() {
        return doR;
    }

    public void setDoR(LocalDate doR) {
        this.doR = doR;
    }

    public List<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplar(List<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }

    public LocalDate getDoAR() {
        return doAR;
    }

    public void setDoAR(LocalDate doAR) {
        this.doAR = doAR;
    }

    public  void addExemplar(Exemplar exemplar){
        this.exemplars.add(exemplar);
        exemplar.addLoan(this);
    }
    //endregion
}
