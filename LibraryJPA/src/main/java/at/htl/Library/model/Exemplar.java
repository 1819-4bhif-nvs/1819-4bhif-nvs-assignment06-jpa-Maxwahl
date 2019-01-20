package at.htl.Library.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exemplar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @ManyToOne
    Item item;
    @Enumerated(EnumType.STRING)
    Weariness weariness;
    @ManyToMany(mappedBy = "exemplars",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonbTransient
    List<Loan> loans;

    //region constructors
    public Exemplar(Item item, Weariness weariness) {
        this.item = item;
        this.weariness = weariness;
        loans = new ArrayList<>();
        item.addExemplar(this);
    }

    public Exemplar() {
    }
    //endregion

    //region getter and setter
    public Long getId() {
        return Id;
    }

    private void setId(Long id) {
        Id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Weariness getWeariness() {
        return weariness;
    }

    public void setWeariness(Weariness weariness) {
        this.weariness = weariness;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }
    //endregion
}
