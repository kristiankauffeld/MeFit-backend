package noroff.mefit.models;


import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column( nullable = false)
    private boolean achieved;

    @Column(name="start_date", length=100, nullable = false)
    private String startDate;

    @Column(name="end_date",length = 100, nullable = false)
    private String endDate ;

    // Add the userId property
    @Column(name = "user_id", length = 100, nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    // TODO should goals be able to have more than one program?
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToMany
    @JoinTable(
            name = "goal_workout",
            joinColumns = {@JoinColumn(name = "goal_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")}
    )

    private Set<Workout> workouts;

    @JsonGetter("profile")
    public String jsonGetProfile(){
        if(profile!= null){
            return profile.getId();
        }
        return null;
    }

    @JsonGetter("program")
    public Program jsonGetProgram(){
        return program;
    }

    @JsonGetter("workouts")
    public List<Integer> jsonGetWorkouts(){
        if(workouts!= null){
            return workouts.stream().map(s -> s.getId())
                    .collect(Collectors.toList());
        }
        return null;
    }

}
