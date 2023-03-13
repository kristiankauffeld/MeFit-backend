package noroff.mefit.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/resources")
public class TestController {
    @GetMapping("public")
    public ResponseEntity getPublic(){
        return ResponseEntity.ok("message");
    }


}
