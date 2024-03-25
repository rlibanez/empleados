package com.rlibanez.empleados.controladores;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

//	@GetMapping("/")
//	public String welcome() {
//		return "index";
//	}

	// Saludo personalizado con Spring
//	@GetMapping("/")
//	public String welcome2(@RequestParam(name="name", required=false, defaultValue= "Mundo") String name, Model model) {
//		model.addAttribute("nombre", name);
//		return "index";
//	}
	
	// Saludo personalizado con Java
	@GetMapping("/hi")
	public String welcome3(@RequestParam("name") Optional<String> name, Model model) {
		model.addAttribute("nombre", name.orElse("Mundo"));
		return "index";
	}
	
	@GetMapping("/saludo/{name}")
	public String welcome4(@PathVariable String name, Model model) {
		//model.addAttribute("saludo", "Hola, " + name);
		model.addAttribute("nombre", name);
		return "index";
	}
	
	@GetMapping("/saludo")
	public String welcome5(@RequestParam(name="name", defaultValue= "Nombre") String name, @RequestParam(name="last", defaultValue= "Apellido") String last, Model model) {
		model.addAttribute("nombre", name + " " + last);
		// Podemos pasar los atributos por separado
		model.addAttribute("name", name);
		model.addAttribute("last", last);
		return "index";
	}
	
	@GetMapping("/saludo/{name}/{last}")
	public String welcome6(@PathVariable String name, @PathVariable String last, Model model) {
		//model.addAttribute("saludo", "Hola, " + name);
		model.addAttribute("nombre", name + " " + last);
		return "index";
	}

}
