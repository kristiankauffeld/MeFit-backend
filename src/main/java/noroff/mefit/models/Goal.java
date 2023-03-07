package noroff.mefit.models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5, nullable = false)
    private boolean acheived ;

    @Column(name="end_date",length = 100, nullable = false)
    private LocalDate endDate ;


    @OneToOne(mappedBy = "goal")
    private Profile profile;

}
