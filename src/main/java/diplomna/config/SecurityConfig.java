package diplomna.config;

import diplomna.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private static final String[] ENABLE_ADDRESSES
            = {"/", "/register", "/login", "/home"};

    private static final String[] ENABLE_RESOURCES
            = {"/static/**", "/css/**", "/js/**", "/img/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(ENABLE_ADDRESSES).permitAll()
                .antMatchers(ENABLE_RESOURCES).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized");
    }
}



























/*
    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .disable()
                .csrf()
//                    .csrfTokenRepository(this.csrfTokenRepository())
                .disable()
//                .and()
                .authorizeRequests()
                .antMatchers("/", "/register", "/login", "/home").permitAll()
                .antMatchers("/static/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
//                .rememberMe()
//                    .rememberMeParameter("rememberMe")
//                    .key("rmmbrm")
//                    .userDetailsService(this.userService)
//                    .rememberMeCookieName("RMMBRM")
//                    .tokenValiditySeconds(1200)
//                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/unauthorized")
        ;
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");

        return repository;
    }
*/
