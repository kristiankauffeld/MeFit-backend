package noroff.mefit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5, nullable = true)
    private float weight ;

    @Column(length = 5, nullable = true)
    private float height ;

    @Column(length = 255, nullable = true)
    private String disability;

    @Column(name="medical_condition", length = 255, nullable = true)
    private String medicalConditions;


    /// Relations
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAcc userAcc;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @OneToOne
    //@JoinColumn(name = "program_id")
    private Program program;
}


