package noroff.mefit.controllers;

import noroff.mefit.models.Profile;
import noroff.mefit.models.Program;
import noroff.mefit.models.Workout;
import noroff.mefit.services.ProgramService;
import noroff.mefit.services.ProgramServiceImpl;
import noroff.mefit.services.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("api/v1/programs")
public class ProgramController {
    private final ProgramService programService;
    private final WorkoutService workoutService;

    public ProgramController(ProgramServiceImpl programService, WorkoutService workoutService) {
        this.programService = programService;
        this.workoutService = workoutService;
    }

    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Program> toReturn = programService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Program program = programService.findById(id);

        return ResponseEntity.ok(program);
    }
    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @PostMapping()
    public ResponseEntity<Program> add(@RequestBody Program program) {
        /*Set<Workout> workouts = program.getWorkouts();
        workouts.forEach(s->{
            Set<Program> tempSet =s.getPrograms();
            tempSet.add(program);
            workoutService.findById(s.getId()).setPrograms(tempSet);
        });*/

        Program programToAdd = programService.add(program);
        URI location = URI.create("api/v1/programs/" + programToAdd.getId());
        return ResponseEntity.created(location).body(program);
    }
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Program program, @PathVariable int id) {
        // Validates if body is correct
        if(id != program.getId())
            return ResponseEntity.badRequest().build();
        programService.update(program);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity delete(@PathVariable int id){
        Program program = programService.findById(id);
        program.getProfiles().forEach(s->{
            s.setProgram(null);
        });
        program.getGoals().forEach(s->{
            s.setProgram(null);
        });
        program.getWorkouts().forEach(s->{
            Set<Program> tempSet = s.getPrograms();
            tempSet.remove(program);
            s.setPrograms(tempSet);
        });


        programService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
