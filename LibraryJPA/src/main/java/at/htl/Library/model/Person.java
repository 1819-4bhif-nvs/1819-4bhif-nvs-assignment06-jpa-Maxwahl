package at.htl.Library.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Loan> loans;
}
