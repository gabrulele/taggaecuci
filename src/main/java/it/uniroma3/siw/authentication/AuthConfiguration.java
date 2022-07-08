package it.uniroma3.siw.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

	public static final String ADMIN_ROLE = "ADMIN";
	
	public static final String USER_ROLE = "CLIENT";
	
	@Autowired
	DataSource datasource;

	/**
	 * This method provides the whole authentication and authorization configuration to use.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()  //da qui si definisce chi può accedere a cosa

		.antMatchers(HttpMethod.GET, "/indexStandard", "/", "/default", "/register", "/collezioniUser", "/maglietteUser", "/accessoriUser", "/materialiUser",
				"/login", "/css/**", "/images/**").permitAll()
		.antMatchers(HttpMethod.GET, "/collezione/{id}", "/maglietta/{id}", "/accessorio/{id}", "/materiale/{id}",
				"/collezioneUser/{id}", "/magliettaUser/{id}", "/accessorioUser/{id}", "/materialeUser/{id}").permitAll()
		.antMatchers(HttpMethod.POST, "/login", "/register").permitAll()

		.antMatchers(HttpMethod.GET,"/indexUser", "/client/**", "/indexClient", "/collezioniClient", "/maglietteClient", "/accessoriClient", "/materialiClient",
				"/magliettaClient/{id}", "/accessorioClient/{id}", "/materialeClient/{id}","/collezioneClient/{id}", "/ordine","/ordineForm", "/ordiniClient").hasAnyAuthority(USER_ROLE,ADMIN_ROLE)
		
		.antMatchers(HttpMethod.GET, "/admin/**", "/indexAdmin", "/initializer", "/ordini").hasAnyAuthority(ADMIN_ROLE)
		.antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)

		.anyRequest().authenticated()

		.and().formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/default")
		.and().logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/index") 
		.invalidateHttpSession(true)
		.clearAuthentication(true).permitAll();
	}


	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		//use the autowired datasource to access the saved credentials
		.dataSource(this.datasource)
		//retrieve username and role
		.authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
		//retrieve username, password and a boolean flag specifying whether the user is enabled or not (always enabled in our case)
		.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
	}

	/**
	 * This method defines a "passwordEncoder" Bean.
	 * The passwordEncoder Bean is used to encrypt and decrpyt the Credentials passwords.
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
