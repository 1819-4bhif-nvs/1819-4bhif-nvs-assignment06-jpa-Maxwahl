package at.htl.Library.model;

import javax.persistence.*;

@Entity
public class Exemplar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @ManyToOne
    Item item;
    @Enumerated(EnumType.STRING)
    Weariness weariness;

    //region constructors
    public Exemplar(Item item, Weariness weariness) {
        this.item = item;
        this.weariness = weariness;
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
    //endregion
}
