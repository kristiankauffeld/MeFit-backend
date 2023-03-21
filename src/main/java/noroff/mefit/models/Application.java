package noroff.mefit.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;

    @OneToOne(mappedBy = "application")
    private Profile profile;

    @JsonGetter("profile")
    public String jsonGetProfile(){
        if(profile!= null){
            return profile.getId();
        }
        return null;
    }

}
