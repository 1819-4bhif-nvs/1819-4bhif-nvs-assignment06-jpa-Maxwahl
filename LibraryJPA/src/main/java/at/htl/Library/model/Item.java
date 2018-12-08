package at.htl.Library.model;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String name;
    String genre;
    double price;

    //region constructors
    public Item(String name,String genre, double price) {
        this.name = name;
        this.genre=genre;
        this.price = price;
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
//endregion
}
