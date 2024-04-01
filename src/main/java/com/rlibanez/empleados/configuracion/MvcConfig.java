package com.rlibanez.empleados.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	
public class MvcConfig implements WebMvcConfigurer {
	
	// https://github.com/spring-guides/gs-securing-web/blob/main/complete/src/main/java/com/example/securingweb/MvcConfig.java
	// https://spring.io/guides/gs/securing-web
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login");
	}	
}
