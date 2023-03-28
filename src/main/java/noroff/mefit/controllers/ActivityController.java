package noroff.mefit.controllers;

import noroff.mefit.models.Activity;
import noroff.mefit.models.Goal;
import noroff.mefit.services.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping("api/v1/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @GetMapping("")
    public ResponseEntity<Collection<Activity>> getAll() {
        try {
            Collection<Activity> toReturn = activityService.findAll();
            return ResponseEntity.ok(toReturn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * @param id The ID of the activity to retrieve.
     * @return A ResponseEntity containing the retrieved activity and a 200 OK status code, or a 404 Not Found status code if no activity is found with the specified ID.
     */
    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @GetMapping("{id}")
    public ResponseEntity<Activity> getById(@PathVariable int id) {
        Activity activity = activityService.findById(id);
        if (activity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(activity);
    }

    /**
     * Creates a new goal
     *
     * @param activity The activity to add.
     * @return A ResponseEntity containing the added activity and a 201 Created status code, or a 400 Bad Request status code if the activity is invalid.
     */
    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @PostMapping()
    public ResponseEntity<Activity> add(@RequestBody Activity activity) {
        Activity activityToAdd = activityService.add(activity);
        URI location = URI.create("api/v1/goals/" + activityToAdd.getId());
        return ResponseEntity.created(location).body(activity);
    }

    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @PutMapping("{id}")
    public ResponseEntity<Activity> update(@RequestBody Activity activity, @PathVariable int id) {
        // Validates if body is correct
        if (id != activity.getId())
            return ResponseEntity.badRequest().build();
        activityService.update(activity);
        return ResponseEntity.noContent().build();
    }

}
