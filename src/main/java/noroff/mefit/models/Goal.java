package noroff.mefit.models;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column( nullable = false)
    private boolean acheived ;

    @Column(name="end_date",length = 100, nullable = false)
    private LocalDate endDate ;


    @OneToOne(mappedBy = "goal")
    private Profile profile;

    // TODO should goals be able to have more than one program?
    @OneToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToMany
    @JoinTable(
            name = "goal_workout",
            joinColumns = {@JoinColumn(name = "goal_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")}
    )
    private Set<Workout> workouts;


}
