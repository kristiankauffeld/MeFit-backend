package noroff.mefit.controllers;


import noroff.mefit.dtos.ExerciseGetDTO;
import noroff.mefit.mappers.ExerciseMapper;
import noroff.mefit.models.Exercise;
import noroff.mefit.models.Program;
import noroff.mefit.services.ExerciseService;
import noroff.mefit.services.ExerciseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/v1/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseServiceImpl exerciseService) {
        this.exerciseService = exerciseService;
    }

    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Exercise> toReturn = exerciseService.findAll();
        return ResponseEntity.ok(toReturn);
    }
    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Exercise exercise = exerciseService.findById(id);

        return ResponseEntity.ok(exercise);
    }
    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @PostMapping()
    public ResponseEntity<Exercise> add(@RequestBody Exercise exercise) {
        Exercise exerciseToAdd = exerciseService.add(exercise);
        URI location = URI.create("api/v1/exercises/" + exerciseToAdd.getId());
        return ResponseEntity.created(location).body(exerciseToAdd);
    }

    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity delete(@PathVariable int id){
        Exercise exercise = exerciseService.findById(id);

        exercise.getWorkouts().forEach(s->{
            Set<Exercise> tempSet = s.getExercises();
            tempSet.remove(exercise);
            s.setExercises(tempSet);
        });


        exerciseService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
