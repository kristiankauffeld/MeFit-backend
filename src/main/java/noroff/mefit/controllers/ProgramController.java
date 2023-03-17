package noroff.mefit.controllers;

import noroff.mefit.models.Program;
import noroff.mefit.services.ProgramService;
import noroff.mefit.services.ProgramServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping("api/v1/programs")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramServiceImpl programService) {
        this.programService = programService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Program> toReturn = programService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Program program = programService.findById(id);

        return ResponseEntity.ok(program);
    }

    @PostMapping()
    public ResponseEntity<Program> add(@RequestBody Program program) {
        Program programToAdd = programService.add(program);
        URI location = URI.create("api/v1/programs/" + programToAdd.getId());
        return ResponseEntity.created(location).build();
    }


}
