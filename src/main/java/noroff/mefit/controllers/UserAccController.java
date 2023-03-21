package noroff.mefit.controllers;

import noroff.mefit.models.UserAcc;
import noroff.mefit.services.UserAccService;
import noroff.mefit.services.UserAccServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("info")
    public ResponseEntity getLoggedInUserInfo(@AuthenticationPrincipal Jwt principal){
        Map<String, String> map = new HashMap<>();
        map.put("id", principal.getClaimAsString("sub"));
        map.put("first_name", principal.getClaimAsString("given_name"));
        map.put("email", principal.getClaimAsString("email"));
        map.put("last_name", principal.getClaimAsString("family_name"));
        map.put("role", String.valueOf(principal.getClaimAsStringList("roles")));
        return ResponseEntity.ok(map);

    }



    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id) {

        UserAcc userAcc = userAccService.findById(id);

        return ResponseEntity.ok(userAcc);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping()
    public ResponseEntity<UserAcc> add(@RequestBody UserAcc userAcc) {
        System.out.println(userAcc.getId());
        UserAcc userAccToAdd = userAccService.add(userAcc);
        URI location = URI.create("api/v1/user_accs/" + userAccToAdd.getId());
        return ResponseEntity.created(location).build();
    }



    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody UserAcc userAcc, @PathVariable String id) {
        // Validates if body is correct
        if(id != userAcc.getId())
            return ResponseEntity.badRequest().build();
        userAccService.update(userAcc);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity patch(@RequestBody UserAcc userAcc, @PathVariable String id) {
        // Validates if body is correct
        if(id != userAcc.getId())
            return ResponseEntity.badRequest().build();
        userAccService.update(userAcc);
        return ResponseEntity.noContent().build();
    }
/*    @PatchMapping("{id}")
    public ResponseEntity<?> partialUpdateName(
            @RequestBody UserAcc partialUpdate, @PathVariable int id) {
        if(id != partialUpdate.getId())
            return ResponseEntity.badRequest().build();
        userAccService.update(partialUpdate);
        return ResponseEntity.ok("resource updated");
    }*/

}
