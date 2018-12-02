package at.htl.Library.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @ManyToOne
    Person person;
    @ManyToOne
    Item item;
    LocalDate doT;
    LocalDate doR;

    public Loan(Person person, Item item, LocalDate doT, LocalDate doR) {
        this.person = person;
        this.item = item;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
    //endregion
}
