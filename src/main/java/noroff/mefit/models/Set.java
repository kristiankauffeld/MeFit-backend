package noroff.mefit.models;

import jakarta.persistence.*;

@Entity
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5, nullable = false)
    private int exercise_repetitions;


}
