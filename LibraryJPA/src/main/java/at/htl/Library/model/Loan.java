package at.htl.Library.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
    Person person;
    @ManyToOne
    Exemplar exemplar;
    LocalDate doT;
    LocalDate doAR;
    LocalDate doR;

    public Loan(Person person, Exemplar exemplar, LocalDate doT, LocalDate doR) {
        this.person = person;
        this.exemplar = exemplar;
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

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public LocalDate getDoAR() {
        return doAR;
    }

    public void setDoAR(LocalDate doAR) {
        this.doAR = doAR;
    }

    //endregion
}
