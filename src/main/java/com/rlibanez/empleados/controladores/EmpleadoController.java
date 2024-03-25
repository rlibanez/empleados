package com.rlibanez.empleados.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.rlibanez.empleados.modelos.Empleado;
import com.rlibanez.empleados.servicios.EmpleadoService;

import jakarta.validation.Valid;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping({"/", "/empleado/list"})
	public String getListado(Model model) {
		model.addAttribute("listaEmpleados", empleadoService.findAll());
		return "list";
	}

	@GetMapping("/empleado/new")
	public String newEmpleadoForm(Model model) {
		model.addAttribute("empleadoForm", new Empleado());
		return "form";
	}

	@PostMapping("/empleado/new/submit")
	public String newEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") Empleado nuevoEmpleado, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		} else {
			empleadoService.add(nuevoEmpleado);
			return "redirect:/empleado/list";
		}
	}

	@GetMapping("/empleado/edit/{id}")
	public String editEmpleadoForm(@PathVariable long id, Model model) {
		Empleado empleado = empleadoService.findById(id);
		if (empleado != null) {
			model.addAttribute("empleadoForm", empleado);
			return "form";
		} else {
			return "redirect:/empleado/new";
		}
	}

	@PostMapping("/empleado/edit/submit")
	public String editEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") Empleado empleado, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		} else {
			empleadoService.editEmpleado(empleado);
			return "redirect:/empleado/list";
		}
	}

}
