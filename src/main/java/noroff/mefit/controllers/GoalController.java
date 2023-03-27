package noroff.mefit.controllers;

import noroff.mefit.models.Goal;
import noroff.mefit.models.Goal;
import noroff.mefit.services.GoalService;
import noroff.mefit.services.GoalServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;

@Controller
@RequestMapping("api/v1/goals")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalServiceImpl goalService) {
        this.goalService = goalService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("")
    public ResponseEntity<Collection<Goal>> getAll(Principal principal) {
        try {
            String userId = principal.getName(); // Get the user ID from the Principal object
            // Retrieve goals associated with the user ID from the goal service
            Collection<Goal> toReturn = goalService.findByUserId(userId);
            // Wrap the collection of goals in a ResponseEntity object with a 200 OK status code
            return ResponseEntity.ok(toReturn);
        } catch (Exception e) {
            // Handle any exceptions that occur and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * @param id The ID of the goal to retrieve.
     * @return A ResponseEntity containing the retrieved goal and a 200 OK status code, or a 404 Not Found status code if no goal is found with the specified ID.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{id}")
    public ResponseEntity<Goal> getById(@PathVariable int id) {
        Goal goal = goalService.findById(id);
        if (goal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(goal);
    }

    /**
     * Creates a new goal
     *
     * @param goal The goal to add.
     * @return A ResponseEntity containing the added goal and a 201 Created status code, or a 400 Bad Request status code if the goal is invalid.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping()
    public ResponseEntity<Goal> add(Principal principal, @RequestBody Goal goal) {
        try {
        String userId = principal.getName(); // Get the user ID from the Principal object
        goal.setUserId(userId); // Set the user ID associated with the goal
        Goal goalToAdd = goalService.add(goal);
        URI location = URI.create("api/v1/goals/" + goalToAdd.getId());
        return ResponseEntity.created(location).body(goalToAdd);
        } catch (Exception e) {
            // Handle any exceptions that occur and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("{id}")
    public ResponseEntity<Goal> update(@RequestBody Goal goal, @PathVariable int id) {
        // Validates if body is correct
        if (id != goal.getId())
            return ResponseEntity.badRequest().build();
        goalService.update(goal);
        return ResponseEntity.noContent().build();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity delete(@PathVariable int id){
        goalService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
