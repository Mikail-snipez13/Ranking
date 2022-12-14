package mikail.Ranking.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .authorizeHttpRequests(request -> request
                        .antMatchers(HttpMethod.GET, "/user/*").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.POST, "/ranking/create").hasRole("USER")

                        // ONLY ADMIN
                        .antMatchers(HttpMethod.GET, "/user/all").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/user/create", "/user/delete/*").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/user/delete/*").hasRole("ADMIN")
//                        .antMatchers(HttpMethod.PATCH, "").hasRole("ADMIN")
                )
                .userDetailsService(userDetailsService)
                .httpBasic();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("mikail")
                        .password("mikail")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }
}
