package noroff.mefit.models;

import jakarta.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(name = "description", columnDefinition="TEXT")
    private String description;

    @Column(name = "target_muscle_group")
    private String muscle_group;

    @Column(name="image_url", nullable = false)
    private String imageURL; 

    @Column(name="video_url", nullable = true)
    private String videoURL;

    @OneToMany(mappedBy = "exercise")
    private java.util.Set<Set> Sets ;

}
