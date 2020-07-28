package diplomna.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/register", "/login", "/home", "/edit", "/about").permitAll()
                    .antMatchers("/static/**", "/css/**", "/js/**", "/img/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/home")
                .and()
                    .logout().logoutSuccessUrl("/?logout").permitAll()
                    .and()
                    .exceptionHandling().accessDeniedPage("/unauthorized");

        //http.headers().disable();
    }
}