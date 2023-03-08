package noroff.mefit.models;

import jakarta.persistence.*;

@Entity
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5, nullable = false)
    private int exercise_repetitions;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToMany(mappedBy = "sets")
    private java.util.Set<Workout> workouts;

}
