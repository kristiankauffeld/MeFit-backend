package noroff.mefit.controllers;

import noroff.mefit.dtos.ExerciseDTO;
import noroff.mefit.mappers.TestMapper;
import noroff.mefit.models.Exercise;
import noroff.mefit.models.Goal;
import noroff.mefit.services.ExerciseService;
import noroff.mefit.services.ExerciseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

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
    public ResponseEntity<Collection<ExerciseDTO>> getAll(){
        Collection<Exercise> exercises = exerciseService.findAll();
        Collection<ExerciseDTO> exerciseDTOs = exercises.stream()
                .map(testMapper::exerciseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(exerciseDTOs);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        ExerciseDTO exerciseDTO = testMapper.exerciseDTO(exerciseService.findById(id));

        return ResponseEntity.ok(exerciseDTO);
    }


    @PostMapping()
    public ResponseEntity<Exercise> add(@RequestBody ExerciseDTO exerciseDTO) {
        Exercise dtoToExercise = testMapper.exerciseDtoToExercise(exerciseDTO);
        Exercise exerciseToAdd = exerciseService.add(dtoToExercise);
        URI location = URI.create("api/v1/exercises/" + exerciseToAdd.getId());

        return ResponseEntity.created(location).build();
    }


    @PutMapping("{id}")
    public ResponseEntity<ExerciseDTO> update(@PathVariable("id") int id, @RequestBody ExerciseDTO exerciseDTO) {
    Exercise existingExercise = exerciseService.findById(id);
    if (existingExercise == null) {
        return ResponseEntity.notFound().build();
    }
    testMapper.updateExerciseFromDto(exerciseDTO, existingExercise);
    exerciseService.update(existingExercise);

    ExerciseDTO updatedDto = testMapper.exerciseDTO(existingExercise);
    return ResponseEntity.ok(updatedDto);

}

}
