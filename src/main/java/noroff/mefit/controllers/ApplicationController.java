package noroff.mefit.controllers;

import noroff.mefit.models.Address;
import noroff.mefit.models.Application;
import noroff.mefit.services.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("api/v1/applications")
public class ApplicationController {
    private final ApplicationService applicationService;


    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Application> toReturn = applicationService.findAll();
        return ResponseEntity.ok(toReturn);
    }

}
