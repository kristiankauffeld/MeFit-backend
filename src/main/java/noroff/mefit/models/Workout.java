package noroff.mefit.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    // Json Getters
    @JsonGetter("programs")
    public List jsonGetPrograms(){
        if(programs!= null){
            return programs.stream().map(Program::getId)
                    .collect(Collectors.toList());
        }
        return null;
    }
    @JsonGetter("goals")
    public List jsonGetGoals(){
        if(goals!= null){
            return goals.stream().map(Goal::getId)
                    .collect(Collectors.toList());
        }
        return null;
    }
    @JsonGetter("setCount")
    public List jsonGetSetCounts(){
        if(setCounts!= null){
            return goals.stream().map(Goal::getId) //TODO wrong item but just to test so change back to setCount
                    .collect(Collectors.toList());
        }
        return null;
    }


}
