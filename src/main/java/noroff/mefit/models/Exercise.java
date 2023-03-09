package noroff.mefit.models;

import jakarta.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(name = "description", length = 100, columnDefinition="TEXT")
    private String description;

    @Column(name = "target_muscle_group")
    @Enumerated(EnumType.STRING)
    private Muscles muscle_group;

    @Column(length = 100, nullable = false)
    private String imageURL; 

    @Column(length = 255, nullable = true)
    private String videoURL;

    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseSet> exerciseSets ;

}
