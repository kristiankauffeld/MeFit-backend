package noroff.mefit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String type;

    @Column(length = 5, nullable = false)
    private boolean complete;

    @ManyToMany(mappedBy = "workouts")
    private Set<Program> programs;

    @ManyToMany(mappedBy = "workouts")
    private Set<Goal> goals;

    @ManyToMany
    @JoinTable(
            name = "workout_setcount",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "setcount_id")
    )
    private Set<SetCount> setCounts;




}
