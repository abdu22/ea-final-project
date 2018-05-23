package edu.mum.ea.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${auth.query}")
	private String authQuery;
	
	@Value("${author.query}")
	private String authorQuery;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home", "/index", "/css/**", "/user/login", "/account/signup").permitAll()
                .antMatchers("/project/create").hasRole("PROJECT_MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin().loginPage("/user/login")
            	.permitAll()
            	.usernameParameter("email")
            	.passwordParameter("password")
            	.defaultSuccessUrl("/")
            	.and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            	.invalidateHttpSession(true)
             .clearAuthentication(true)
            	.logoutSuccessUrl("/")
                .permitAll();
        http.csrf().disable();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(authQuery)
								.passwordEncoder(passwordEncoder())
								.authoritiesByUsernameQuery(authorQuery)
								.dataSource(dataSource);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}