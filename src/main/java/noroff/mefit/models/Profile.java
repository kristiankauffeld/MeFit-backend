package noroff.mefit.models;

import jakarta.persistence.*;


@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Userx userx;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}


