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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 60, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String first_name;
    @Column(length = 50, nullable = false)
    private String last_name;
    @Column(length = 10, nullable = true)
    private boolean isContributor;
    @Column(nullable = false)
    private boolean isAdmin;

    @OneToOne(mappedBy = "userAcc")
    private Profile profile;

    @JsonGetter("profile")
    public Integer jsonGetProfile(){
        if(profile!= null){
            return profile.getId();
        }
        return null;
    }


}
