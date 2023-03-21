package noroff.mefit.runners;


import jakarta.transaction.Transactional;
import noroff.mefit.services.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * Client class for the Services.
 * This class exists to demonstrate the use of services.
 * It implements ApplicationRunner to be able to use dependency injection.
 */
@Component
public class AppRunner implements ApplicationRunner {


    private final AddressService addressService;
    private final ProfileService profileService;
    private final ExerciseService exerciseService;
    private final SetCountService setCountService;
    private final GoalService goalService;
    private final ProgramService programService;
    private final WorkoutService workoutService;
    public AppRunner(AddressService addressService, ProfileService profileService, ExerciseService exerciseService, SetCountService setCountService, GoalService goalService, ProgramService programService, WorkoutService workoutService) {


        this.addressService = addressService;
        this.profileService = profileService;
        this.exerciseService = exerciseService;

        this.setCountService = setCountService;
        this.goalService = goalService;
        this.programService = programService;
        this.workoutService = workoutService;
    }

    @Override
    @Transactional
    //need to add @Transactional to be able to delete from both sides of the relationship.
    public void run(ApplicationArguments args) throws Exception {
        //addressService.deleteById(1);
        //profileService.deleteById(1);
        //exerciseService.deleteById(1);
        //setCountService.deleteById(1);
        //goalService.deleteById(1);
        //programService.deleteById(1);
        //workoutService.deleteById(1);



    }
}