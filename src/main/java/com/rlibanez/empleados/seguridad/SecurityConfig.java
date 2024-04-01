package com.rlibanez.empleados.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
// https://spring.io/projects/spring-security#samples
// https://github.com/spring-projects/spring-security-samples/blob/main/servlet/spring-boot/java/hello-security-explicit/src/main/java/example/SecurityConfiguration.java
public class SecurityConfig {

	@Bean
	InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user").password(passwordEncoder().encode("user")).roles("USER").build();
		//UserDetails user1 = User.withDefaultPasswordEncoder().username("usuario1").password("password1").roles("USER").build();
		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

//    @Bean
//    AuthenticationSuccessHandler successHandler() {
//    	System.out.println("successHandler()");
//        return new SimpleUrlAuthenticationSuccessHandler("/inicio");
//    }
//
//    @Bean
//    AuthenticationFailureHandler failureHandler() {
//    	System.out.println("failureHandler()");
//        return new SimpleUrlAuthenticationFailureHandler("/login?error");
//    }
//
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//        .authorizeHttpRequests(authorize -> authorize
//            .requestMatchers("/login").permitAll()
//            .anyRequest().authenticated()
//        )
//        .formLogin(form -> form
//            .loginPage("/login")
//            .successHandler(successHandler())
//            .failureHandler(failureHandler())
//            .permitAll()
//        )
//        .logout(logout -> logout
//            .permitAll()
//        );
//	}

    // https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html#servlet-authentication-unpwd
	// https://docs.spring.io/spring-security/reference/servlet/configuration/java.html#jc-httpsecurity
	// https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
	// https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html#formLogin(org.springframework.security.config.Customizer)
	// https://medium.com/javarevisited/spring-security-without-websecurityconfigureradapter-c712b81cc6ed
	// https://docs.spring.io/spring-security/reference/5.8/migration/servlet/session-management.html#requestcache-query-optimization
	// https://docs.spring.io/spring-security/reference/servlet/architecture.html#savedrequests
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
//    	requestCache.setMatchingRequestParameterName("holi");
//    	RequestCache requestCache = new NullRequestCache();
		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/webjars/**", "/css/**").permitAll().anyRequest().authenticated())
//				.requestCache((cache) -> cache.requestCache(requestCache))
				.requestCache((cache) -> cache.disable())
				.formLogin(form -> form.loginPage("/login").failureUrl("/login?failed").permitAll())
				.logout((logout) -> logout.logoutSuccessUrl("/bye").permitAll())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }

}
