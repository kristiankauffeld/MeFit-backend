package noroff.mefit.runners;


import noroff.mefit.models.UserAcc;
import noroff.mefit.services.UserAccService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;


/**
 * Client class for the Services.
 * This class exists to demonstrate the use of services.
 * It implements ApplicationRunner to be able to use dependency injection.
 */
@Component
public class AppRunner implements ApplicationRunner {


    private final UserAccService userAccService;

    public AppRunner(UserAccService userAccService) {

        this.userAccService = userAccService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(userAccService.findById(1));
        System.out.println(userAccService.findAllByName("Fa"));

    }
}