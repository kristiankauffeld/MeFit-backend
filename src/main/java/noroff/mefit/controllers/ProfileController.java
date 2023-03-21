package noroff.mefit.controllers;

import noroff.mefit.models.Profile;
import noroff.mefit.services.ProfileService;
import noroff.mefit.services.ProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping("api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileServiceImpl profileService) {
        this.profileService = profileService;
    }

    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Profile> toReturn = profileService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id) {

        Profile profile = profileService.findById(id);

        return ResponseEntity.ok(profile);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping()
    public ResponseEntity<Profile> add(@RequestBody Profile profile) {
        Profile profileToAdd = profileService.add(profile);
        URI location = URI.create("api/v1/profiles/" + profileToAdd.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Profile profile, @PathVariable String id) {
        // Validates if body is correct
        if(id != profile.getId())
            return ResponseEntity.badRequest().build();
        profileService.update(profile);
        return ResponseEntity.noContent().build();
    }

}
