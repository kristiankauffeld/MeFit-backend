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
public class SetCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5, nullable = false)
    private int exercise_repetitions;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToMany(mappedBy = "setCounts")
    private Set<Workout> workouts;

    @JsonGetter("workouts")
    public List<Integer> jsonGetSetCounts(){
        if(workouts!= null){
            return workouts.stream().map(s -> s.getId())
                    .collect(Collectors.toList());
        }
        return null;
    }

    @JsonGetter("exercise")
    public Integer jsonGetExercise(){
        if(exercise!= null){
            return exercise.getId();
        }
        return null;
    }

}
