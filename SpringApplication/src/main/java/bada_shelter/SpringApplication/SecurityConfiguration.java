package bada_shelter.SpringApplication;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(jdbcTemplate.getDataSource())
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT nazwa_uzytkownika, haslo, wlaczony FROM uzytkownicy WHERE nazwa_uzytkownika=?")
                .authoritiesByUsernameQuery("SELECT nazwa_uzytkownika, uprawnienie FROM uprawnienia WHERE nazwa_uzytkownika=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/animal").permitAll()
                .antMatchers("/searchToAdoption").permitAll()
                .antMatchers("/main").authenticated()
                .antMatchers("/search_panel").authenticated()
                .antMatchers("/addAnimal").authenticated()
                .antMatchers("/editAnimal").authenticated()
                .antMatchers("/editAnimal/{id}").authenticated()
                .antMatchers("/main_staff").authenticated()
                .antMatchers("/searchAnimals").authenticated()
                .antMatchers("/changePassword").authenticated()
                .antMatchers("/deleteAnimal/{id}").authenticated()
                .antMatchers("/addStaffMember").hasAuthority("ADMIN")
                .antMatchers("/searchUsersPanel").hasAuthority("ADMIN")
                .antMatchers("/searchUsers").hasAuthority("ADMIN")
                .antMatchers("/userPage/{username}").hasAuthority("ADMIN")
                .antMatchers("/deleteUser/{username}").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/index")
                .logoutSuccessUrl("/index")
                .permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/scripts/**", "/images/**");
    }
}
