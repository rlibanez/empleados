package com.rlibanez.empleados.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.rlibanez.empleados.modelos.Empleado;
import com.rlibanez.empleados.servicios.EmpleadoService;
import com.rlibanez.empleados.upload.storage.StorageService;

import jakarta.validation.Valid;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private StorageService storageService;

	@GetMapping({ "/", "/empleado/list" })
	public String getListado(Model model) {
		model.addAttribute("listaEmpleados", empleadoService.findAll());
		return "list";
	}

	@GetMapping("/empleado/new")
	public String newEmpleadoForm(Model model) {
		model.addAttribute("empleadoForm", new Empleado());
		return "form";
	}

	// @RequestParam("file"), donde "file" es el nombre del campo dentro del formulario
	@PostMapping("/empleado/new/submit")
	public String newEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") Empleado nuevoEmpleado,
			BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
		if (bindingResult.hasErrors()) {
			return "form";
		} else {
			if (!file.isEmpty()) {
				String avatar = storageService.store(file, nuevoEmpleado.getId());
				nuevoEmpleado.setImagen(MvcUriComponentsBuilder.fromMethodName(EmpleadoController.class, "serveFile", avatar).build().toUriString());
			}
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
	public String editEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") Empleado empleado,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		} else {
			empleadoService.editEmpleado(empleado);
			return "redirect:/empleado/list";
		}
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);

		if (file == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
		//return ResponseEntity.ok().body(file);
	}

}
