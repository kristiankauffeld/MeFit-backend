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

    @Column(length = 100, nullable = false)
    private String targetMuscleGroup; //TODO Should this be set of ENUMs?

    @Column(length = 100, nullable = false)
    private String image; //TODO decide whether to use blob or bytea to store image.

    @Column(length = 255, nullable = true)
    private String vidLink;

    @OneToMany(mappedBy = "exercise")
    private java.util.Set<Set> sets ;

}
