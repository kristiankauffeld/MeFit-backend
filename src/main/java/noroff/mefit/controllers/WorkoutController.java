package noroff.mefit.controllers;

import noroff.mefit.models.Address;
import noroff.mefit.models.Workout;
import noroff.mefit.services.WorkoutService;
import noroff.mefit.services.WorkoutServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping("api/v1/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutServiceImpl workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Workout> toReturn = workoutService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Workout workout = workoutService.findById(id);

        return ResponseEntity.ok(workout);
    }

    @PostMapping()
    public ResponseEntity<Workout> add(@RequestBody Workout workout) {
        Workout workoutToAdd = workoutService.add(workout);
        URI location = URI.create("api/v1/workouts/" + workoutToAdd.getId());
        return ResponseEntity.created(location).build();
    }
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Workout workout, @PathVariable int id) {
        // Validates if body is correct
        if(id != workout.getId())
            return ResponseEntity.badRequest().build();
        workoutService.update(workout);
        return ResponseEntity.noContent().build();
    }


}
