package noroff.mefit.controllers;

import noroff.mefit.models.Address;
import noroff.mefit.models.Exercise;
import noroff.mefit.services.ExerciseService;
import noroff.mefit.services.ExerciseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping("api/v1/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseServiceImpl exerciseService) {
        this.exerciseService = exerciseService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Exercise> toReturn = exerciseService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Exercise exercise = exerciseService.findById(id);

        return ResponseEntity.ok(exercise);
    }

    @PostMapping()
    public ResponseEntity<Exercise> add(@RequestBody Exercise exercise) {
        Exercise exerciseToAdd = exerciseService.add(exercise);
        URI location = URI.create("api/v1/exercises/" + exerciseToAdd.getId());
        return ResponseEntity.created(location).build();
    }
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Exercise exercise, @PathVariable int id) {
        // Validates if body is correct
        if(id != exercise.getId())
            return ResponseEntity.badRequest().build();
        exerciseService.update(exercise);
        return ResponseEntity.noContent().build();
    }

}
