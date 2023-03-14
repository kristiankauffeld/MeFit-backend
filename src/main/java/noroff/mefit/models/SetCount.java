package noroff.mefit.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class SetCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5, nullable = false)
    private int exercise_repetitions;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToMany(mappedBy = "setCounts")
    private Set<Workout> workouts;

}
