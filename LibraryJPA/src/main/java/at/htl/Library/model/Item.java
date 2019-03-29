package at.htl.Library.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
        @NamedQuery(name = "Item.findById",query = "select i from Item i where i.Id= :Id"),
        @NamedQuery(name = "Item.findAll",query = "select i from Item i")
})
public abstract class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String name;
    String genre;
    double price;
    @OneToMany(mappedBy = "item",fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
    List<Exemplar> exemplars;

    //region constructors
    public Item(String name,String genre, double price) {
        this.name = name;
        this.genre=genre;
        this.price = price;
        exemplars=new ArrayList<>();
    }

    public Item() {
    }
    //endregion

    //region getter and setter

    public Long getId() {
        return Id;
    }

    private void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void addExemplar(Exemplar exemplar){
        this.exemplars.add(exemplar);
    }
//endregion
}
