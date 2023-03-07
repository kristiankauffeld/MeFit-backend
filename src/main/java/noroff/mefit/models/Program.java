package noroff.mefit.models;

import jakarta.persistence.*;

@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String category;


    @OneToOne(mappedBy = "program")
    private Profile profile;
}
