package noroff.mefit.controllers;

import noroff.mefit.dtos.ExerciseGetDTO;
import noroff.mefit.mappers.TestMapper;
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
    private final TestMapper testMapper;

    public ExerciseController(ExerciseServiceImpl exerciseService, TestMapper testMapper) {
        this.exerciseService = exerciseService;
        this.testMapper = testMapper;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Exercise> toReturn = exerciseService.findAll();
        return ResponseEntity.ok(toReturn);
    }

/*
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Exercise exercise = exerciseService.findById(id);

        return ResponseEntity.ok(exercise);
    }
*/

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        ExerciseGetDTO exerciseGetDTO = testMapper.exerciseGetDTO(exerciseService.findById(id));

        return ResponseEntity.ok(exerciseGetDTO);
    }

/*
    @PostMapping()
    public ResponseEntity<Exercise> add(@RequestBody Exercise exercise) {
        Exercise exerciseToAdd = exerciseService.add(exercise);
        URI location = URI.create("api/v1/exercises/" + exerciseToAdd.getId());
        return ResponseEntity.created(location).build();
    }
*/

    @PostMapping()
    public ResponseEntity<Exercise> add(@RequestBody ExerciseGetDTO exerciseGetDTO) {

        Exercise exerciseGetDtoToExercise = testMapper.exerciseDtoToExercise(exerciseGetDTO);
        Exercise exerciseToAdd = exerciseService.add(exerciseGetDtoToExercise);
        URI location = URI.create("api/v1/exercises/" + exerciseToAdd.getId());
        return ResponseEntity.created(location).build();
    }


}
