package io.volkan.oauth.springbootoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
// @EnableWebSecurity
@EnableOAuth2Sso
@RestController
public class SpringbootOauthApplication extends WebSecurityConfigurerAdapter {

    @RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated(); // .and().formLogin();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOauthApplication.class, args);
	}
}
