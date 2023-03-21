package noroff.mefit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                // Sessions will not be used
                .sessionManagement().disable()
                // Disable CSRF -- not necessary when there are no sessions
                .csrf().disable()
                // Enable security for http requests
                .authorizeHttpRequests(authorize -> authorize
        /*                .requestMatchers("/api/v1/programs",    "/api/v1/programs/**").permitAll()//TODO clean up
                        .requestMatchers("/api/v1/goals",       "/api/v1/goals/**").permitAll()//TODO clean up
                        .requestMatchers("/api/v1/workouts",    "/api/v1/workouts/**").permitAll()//TODO clean up
                        .requestMatchers("/api/v1/set_counts",  "/api/v1/set_counts/**").permitAll()//TODO clean up
                        .requestMatchers("/api/v1/profiles",    "/api/v1/profiles/**").permitAll()//TODO clean up
                        .requestMatchers("/api/v1/user_accs",   "/api/v1/user_accs/**").permitAll()//TODO clean up
                        .requestMatchers("/api/v1/addresses",   "/api/v1/addresses/**").permitAll()//TODO clean up
                        .requestMatchers("/api/v1/exercises",   "/api/v1/exercises/**").permitAll()//TODO clean up*/

                        //.requestMatchers("/api/v1/resources/authorized").hasAnyAuthority()
                        // All endpoints are protected
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
