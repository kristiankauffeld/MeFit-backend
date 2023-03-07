package noroff.mefit.models;

import jakarta.persistence.*;

@Entity
public class Userx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String first_name;

    @Column(length = 50, nullable = false)
    private String last_name;
    @Column(length = 10, nullable = false)
    private boolean isContributor;
    @Column(length = 10, nullable = false)
    private boolean isAdmin;

    @OneToOne(mappedBy = "userx")
    private Profile profile;

}
