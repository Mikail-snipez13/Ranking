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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MyUserDetailsService userDetailsService) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors().disable()
                .authorizeRequests()

                // EVERYONE
                .antMatchers( "/ranking/search/*", "/ticket/isValid", "/ticket/use").permitAll()

                // USER
                .antMatchers(HttpMethod.GET, "/user/get/me", "/ranking/get/*", "/get/usernameById/*").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/ranking/create", "/ticket/create", "/question/create").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.PATCH, "/ranking/update/*", "/question/update/*", "/user/update/*").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/question/delete/*", "/ranking/delete/*").hasAnyAuthority("ADMIN", "USER")

                // ONLY ADMIN
                .antMatchers(HttpMethod.GET,"/user/all", "/user/get/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/user/create").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/delete/*").hasAuthority("ADMIN")
                .and()
                .userDetailsService(userDetailsService)
                .httpBasic();

        return http.build();
    }

//    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("mikail")
                        .password("mikail")
                        .roles("ADMIN", "USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
