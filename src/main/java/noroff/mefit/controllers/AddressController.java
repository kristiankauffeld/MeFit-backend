package noroff.mefit.controllers;

import noroff.mefit.models.Address;
import noroff.mefit.services.AddressService;
import noroff.mefit.services.AddressServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping("api/v1/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    @CrossOrigin(origins = {"http://localhost:3000", "${react.address}"})
    @GetMapping("")
    public ResponseEntity getAll(){
        Collection<Address> toReturn = addressService.findAll();
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Address address = addressService.findById(id);

        return ResponseEntity.ok(address);
    }

    @PostMapping()
    public ResponseEntity<Address> add(@RequestBody Address address) {
        Address addressToAdd = addressService.add(address);
        URI location = URI.create("api/v1/addresss/" + addressToAdd.getId());
        return ResponseEntity.created(location).body(address);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Address address, @PathVariable int id) {
        // Validates if body is correct
        if(id != address.getId())
            return ResponseEntity.badRequest().build();
        addressService.update(address);
        return ResponseEntity.noContent().build();
    }

}
