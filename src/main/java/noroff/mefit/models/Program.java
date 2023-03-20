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
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    private String author;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(length = 1000)
    private String imageURL;

    // relationships
    //TODO does program need foreign keys to all entities? so far not added.
    @OneToMany(mappedBy = "program")
    private Set<Profile> profiles;
    @OneToOne(mappedBy = "program")
    private Goal goal;

    @ManyToMany
    @JoinTable(
            name = "program_workout",
            joinColumns = {@JoinColumn(name = "program_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")}
    )
    private Set<Workout> workouts;

    //this is temporary until we use dtos for stuff
    @JsonGetter("profiles")
    public List<String> jsonGetProfile(){
        if(profiles!= null){
            return profiles.stream().map(s -> s.getId())
                    .collect(Collectors.toList());
        }
        return null;
    }
    @JsonGetter("goal")
    public Integer jsonGetGoal(){
        if(goal!= null){
            return goal.getId();
        }
        return null;
    }
    @JsonGetter("workouts")
    public List<Integer> jsonGetWorkouts(){
        if(workouts!= null){
            return workouts.stream().map(s -> s.getId())
                    .collect(Collectors.toList());
        }
        return null;
    }




    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", profile=" + profiles +
                ", goal=" + goal +
                ", workouts=" + workouts +
                '}';
    }
}
