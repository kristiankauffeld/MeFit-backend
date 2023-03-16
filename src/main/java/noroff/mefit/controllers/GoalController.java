package noroff.mefit.controllers;

import noroff.mefit.models.Goal;
import noroff.mefit.models.Program;
import noroff.mefit.services.GoalService;
import noroff.mefit.services.GoalServiceImpl;
import noroff.mefit.services.ProgramService;
import noroff.mefit.services.ProgramServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping("api/v1/goals")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalServiceImpl goalService) {
        this.goalService = goalService;
    }

    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Goal> toReturn = goalService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Goal goal = goalService.findById(id);

        return ResponseEntity.ok(goal);
    }

    @PostMapping()
    public ResponseEntity<Goal> add(@RequestBody Goal goal) {
        Goal goalToAdd = goalService.add(goal);
        URI location = URI.create("api/v1/goals/" + goalToAdd.getId());
        return ResponseEntity.created(location).build();
    }


}
