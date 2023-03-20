package noroff.mefit.controllers;

import noroff.mefit.models.UserAcc;
import noroff.mefit.services.UserAccService;
import noroff.mefit.services.UserAccServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping("api/v1/user_accs")
public class UserAccController {
    private final UserAccService userAccService;

    public UserAccController(UserAccServiceImpl userAccService) {
        this.userAccService = userAccService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<UserAcc> toReturn = userAccService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        UserAcc userAcc = userAccService.findById(id);

        return ResponseEntity.ok(userAcc);
    }

    @PostMapping()
    public ResponseEntity<UserAcc> add(@RequestBody UserAcc userAcc) {
        UserAcc userAccToAdd = userAccService.add(userAcc);
        URI location = URI.create("api/v1/user_accs/" + userAccToAdd.getId());
        return ResponseEntity.created(location).build();
    }
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody UserAcc userAcc, @PathVariable int id) {
        // Validates if body is correct
        if(id != userAcc.getId())
            return ResponseEntity.badRequest().build();
        userAccService.update(userAcc);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity patch(@RequestBody UserAcc userAcc, @PathVariable int id) {
        // Validates if body is correct
        if(id != userAcc.getId())
            return ResponseEntity.badRequest().build();
        userAccService.update(userAcc);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("{id}")
    public ResponseEntity<?> partialUpdateName(
            @RequestBody UserAcc partialUpdate, @PathVariable int id) {
        if(id != partialUpdate.getId())
            return ResponseEntity.badRequest().build();
        userAccService.update(partialUpdate, id);
        return ResponseEntity.ok("resource updated");
    }

}
