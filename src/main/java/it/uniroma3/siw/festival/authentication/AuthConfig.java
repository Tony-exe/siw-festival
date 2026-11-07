package it.uniroma3.siw.festival.authentication;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import it.uniroma3.siw.festival.model.Ruolo;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    private final DataSource dataSource;

    public AuthConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username, password, true as enabled FROM utente WHERE username=?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, ruolo FROM utente WHERE username=?");
        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(Customizer.withDefaults());

        // le chiamate REST e il logout (usati da React) non richiedono il token CSRF
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/rest/**", "/logout"));

        // 1. AUTORIZZAZIONI: chi può accedere a cosa
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/", "/index", "/login", "/register",
                    "/festivals", "/festivals/**", "/films/**", "/css/**", "/images/**", "/favicon.ico",
                    "/app", "/app/**", "/rest/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                    .permitAll();
            auth.requestMatchers(HttpMethod.POST, "/login", "/register").permitAll();
            auth.requestMatchers("/error").permitAll();
            auth.requestMatchers("/admin/**").hasAuthority(Ruolo.ADMIN.name());
            auth.anyRequest().authenticated();
        });

        // 2. LOGIN
        http.formLogin(form -> {
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/", false);
            form.failureUrl("/login?error=true");
        });

        // 3. LOGOUT
        http.logout(logout -> {
            logout.logoutUrl("/logout");
            logout.logoutSuccessUrl("/?logout");
            logout.invalidateHttpSession(true);
            logout.deleteCookies("JSESSIONID");
            logout.clearAuthentication(true);
            logout.permitAll();
        });

        return http.build();
    }
}
