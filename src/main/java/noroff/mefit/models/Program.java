package noroff.mefit.models;

import jakarta.persistence.*;

import java.util.Set;

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
    @OneToOne(mappedBy = "program")
    private Goal goal;

    @ManyToMany
    @JoinTable(
            name = "program_workout",
            joinColumns = {@JoinColumn(name = "program_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")}
    )
    private Set<Workout> workouts;
}
