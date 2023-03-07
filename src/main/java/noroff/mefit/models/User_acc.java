package noroff.mefit.models;

import jakarta.persistence.*;

@Entity
public class User_acc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 60, nullable = false)
    private String password;


    @Column(length = 50, nullable = false)
    private String first_name;
    @Column(length = 50, nullable = false)
    private String last_name;
    @Column(length = 10, nullable = true)
    private boolean isContributor;
    @Column(length = 10, nullable = true)
    private boolean isAdmin;

    @OneToOne(mappedBy = "user_acc")
    private Profile profile;

}
