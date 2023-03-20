package noroff.mefit.controllers;

import noroff.mefit.models.Program;
import noroff.mefit.models.SetCount;
import noroff.mefit.services.SetCountService;
import noroff.mefit.services.SetCountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping("api/v1/set_counts")
public class SetCountController {
    private final SetCountService setCountService;

    public SetCountController(SetCountServiceImpl setCountService) {
        this.setCountService = setCountService;
    }

    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<SetCount> toReturn = setCountService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        SetCount setCount = setCountService.findById(id);

        return ResponseEntity.ok(setCount);
    }

    @PostMapping()
    public ResponseEntity<SetCount> add(@RequestBody SetCount setCount) {
        SetCount setCountToAdd = setCountService.add(setCount);
        URI location = URI.create("api/v1/set_counts/" + setCountToAdd.getId());
        return ResponseEntity.created(location).build();
    }
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody SetCount setCount, @PathVariable int id) {
        // Validates if body is correct
        if(id != setCount.getId())
            return ResponseEntity.badRequest().build();
        setCountService.update(setCount);
        return ResponseEntity.noContent().build();
    }


}
