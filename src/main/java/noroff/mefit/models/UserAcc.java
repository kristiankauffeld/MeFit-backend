package noroff.mefit.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAcc {
    @Id
    @Column(length = 100, nullable = false)
    private String id;
    @Column(length = 60, nullable = true)
    private String email;
    @Column(nullable = true)
    private String role;
    @Column(length = 50, nullable = true)
    private String first_name;
    @Column(length = 50, nullable = true)
    private String last_name;


    @OneToOne(mappedBy = "userAcc")
    private Profile profile;

    @JsonGetter("profile")
    public String jsonGetProfile(){
        if(profile!= null){
            return profile.getId();
        }
        return null;
    }


}
