package noroff.mefit.controllers;

import noroff.mefit.models.Profile;
import noroff.mefit.services.ProfileService;
import noroff.mefit.services.ProfileServiceImpl;
import org.apache.coyote.Response;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileServiceImpl profileService) {
        this.profileService = profileService;
    }
    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Profile> toReturn = profileService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        try{
            Profile profile = profileService.findById(id);
            return ResponseEntity.ok(profile);
        } catch(NoSuchElementException e){
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }
    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @PostMapping()
    public ResponseEntity<Profile> add(@RequestBody Profile profile) {
        try {
            Profile profileToAdd = profileService.add(profile);
            URI location = URI.create("api/v1/profiles/" + profileToAdd.getId());
            return ResponseEntity.created(location).body(profile);
        }
        catch (Error e){
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }




    }
    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @PatchMapping("{id}")
    public ResponseEntity update(@RequestBody Profile profile, @PathVariable String id) {

        if(!id.equals(profile.getId())){
            return ResponseEntity.badRequest().build();
        }
        Profile oldProfile = profileService.findById(id);
        oldProfile.setAge(profile.getAge());
        oldProfile.setWeight(profile.getWeight());
        oldProfile.setHeight(profile.getHeight());



        profileService.update(oldProfile);
        return ResponseEntity.ok(oldProfile);
    }

}
