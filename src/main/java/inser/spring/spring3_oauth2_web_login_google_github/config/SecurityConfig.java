package inser.spring.spring3_oauth2_web_login_google_github.config;

import inser.spring.spring3_oauth2_web_login_google_github.entities.PasswordConverterEncoder;
import inser.spring.spring3_oauth2_web_login_google_github.services.LoginUtilityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/", "/login", "/error").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard", true)
                    .permitAll()
            )
            .oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard", true)
            )
            .logout(logout -> logout
                    .logoutSuccessUrl("/")
                    .permitAll()
            );

        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails defaultUser = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(defaultUser);
//    }

    LoginUtilityService loginUtilityService;

    public SecurityConfig(LoginUtilityService loginUtilityService) {
        // Auto inject dependent beans
        this.loginUtilityService = loginUtilityService;
    }

    // Custom User Details Service to manage login
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsService userDetailsService = (userName) -> {
            return loginUtilityService.findMatch(userName);
        };
        return userDetailsService;
    }

    // Password encoder
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        PasswordEncoder passwordEncoder = new PasswordConverterEncoder();
        return passwordEncoder;
    }

}
