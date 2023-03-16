package noroff.mefit.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonGetter("programs")
    public List<Integer> jsonGetPrograms(){
        if(programs!= null){
            return programs.stream().map(s -> s.getId())
                    .collect(Collectors.toList());
        }
        return null;
    }

    @JsonGetter("goals")
    public List<Integer> jsonGetGoals(){
        if(goals!= null){
            return goals.stream().map(s -> s.getId())
                    .collect(Collectors.toList());
        }
        return null;
    }

    @JsonGetter("setCounts")
    public List<Integer> jsonGetSetCounts(){
        if(setCounts!= null){
            return setCounts.stream().map(s -> s.getId())
                    .collect(Collectors.toList());
        }
        return null;
    }



}
