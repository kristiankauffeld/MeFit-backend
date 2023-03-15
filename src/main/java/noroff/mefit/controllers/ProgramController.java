package noroff.mefit.controllers;

import noroff.mefit.models.Program;
import noroff.mefit.services.ProgramService;
import noroff.mefit.services.ProgramServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping("api/v1/programs")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramServiceImpl programService) {
        this.programService = programService;
    }

    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Program> toReturn = programService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Program program = programService.findById(id);

        return ResponseEntity.ok(program);
    }

}
