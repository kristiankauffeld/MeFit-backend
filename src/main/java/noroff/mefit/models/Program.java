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
        @Column(length = 100, nullable = true)
        private String name;

        @Column(length = 100)
        private String author;

        @Column(columnDefinition="TEXT")
        private String description;

        @Column(length = 50, nullable = true)
        private String category;

        @Column(length = 1000)
        private String imageURL;

        @OneToMany(mappedBy = "program")
        private Set<Profile> profiles;

        @OneToMany(mappedBy = "program")
        private Set<Goal> goals;

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
        @JsonGetter("goals")
        public List<Integer> jsonGetGoals(){
            if(goals!= null){
                return goals.stream().map(s -> s.getId())
                        .collect(Collectors.toList());
            }
            return null;
        }

        /*@JsonGetter("workouts")
        public List<Integer> jsonGetWorkouts(){
            if(workouts!= null){
                return workouts.stream().map(s -> s.getId())
                        .collect(Collectors.toList());
            }
            return null;
        }*/

    }
