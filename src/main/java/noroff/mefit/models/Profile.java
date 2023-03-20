package noroff.mefit.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int age;

    @Column(length = 5, nullable = true)
    private float weight ;

    @Column(length = 5, nullable = true)
    private float height ;

    /// Relations
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAcc userAcc;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne
    //@JoinColumn(name = "program_id")
    private Program program;

    @JsonGetter("program")
    public Integer jsonGetProgram(){
        if(program!= null){
            return program.getId();
        }
        return null;
    }
    @JsonGetter("goal")
    public Integer jsonGetGoal(){
        if(goal!= null){
            return goal.getId();
        }
        return null;
    }
    @JsonGetter("address")
    public Integer jsonGetAddress(){
        if(address!= null){
            return address.getId();
        }
        return null;
    }
    @JsonGetter("user_acc")
    public Integer jsonGetUserAcc(){
        if(userAcc!= null){
            return userAcc.getId();
        }
        return null;
    }





}

