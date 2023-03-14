package noroff.mefit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150, nullable = true)
    private String addressLine1 ;
    @Column(length = 150, nullable = true)
    private String addressLine2;
    @Column(length = 100, nullable = true)
    private String addressLine3;

    @Column(length = 100, nullable = true)
    private String postalCode;
    @Column(length = 100, nullable = true)
    private String city;
    @Column(length = 100, nullable = true)
    private String country;

    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
