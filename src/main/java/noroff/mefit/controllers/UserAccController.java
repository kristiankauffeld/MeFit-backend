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


}
