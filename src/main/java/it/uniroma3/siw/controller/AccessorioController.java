package it.uniroma3.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.AccessorioValidator;
import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.service.AccessorioService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.MaterialeService;
import it.uniroma3.siw.service.OrdineService;

@Controller
public class AccessorioController {
	
	@Autowired
	private AccessorioService accessorioService;
	
	@Autowired
	private MaterialeService materialeService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private OrdineService ordineService;
	
	@Autowired
	private AccessorioValidator accessorioValidator;
	
	@GetMapping("/toDeleteAccessorio/{id}")
	public String toDeleteAccessorio(@PathVariable("id") Long id, Model model) {
		Accessorio accessorio = accessorioService.findById(id);
		model.addAttribute("accessorio", accessorio);
		return "/admin/accessorio/toDeleteAccessorio.html";
	}
	
	@GetMapping("/deleteAccessorio/{id}")
	public String deleteAccessorio(@PathVariable("id") Long id, Model model) {
		Accessorio accessorio = accessorioService.findById(id);
		collezioneService.updateAccessoriInCollezioni(accessorio.getCollezioni(), accessorio);
		ordineService.updateAccessoriInOrdini(accessorio.getOrdini(), accessorio);
		accessorioService.deleteById(id);
		model.addAttribute("accessori", accessorioService.findAll());
		return "/admin/accessorio/accessori.html";
	}
	
	@GetMapping("/toUpdateAccessorio/{id}")
	public String toUpdateAccessorio(@PathVariable("id") Long id, Model model) {
		Accessorio accessorio = accessorioService.findById(id);
		model.addAttribute("accessorio", accessorio);
		model.addAttribute("materiali", materialeService.findAll());
		return "/admin/accessorio/accessorioFormDiModifica.html";
	}
	
	@PostMapping("/updateAccessorio/{id}")
	public String updateAccessorio(@Valid @ModelAttribute("accessorio") Accessorio accessorio, BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			accessorioService.save(accessorio);
			model.addAttribute("accessorio", accessorio);
			return "/admin/accessorio/accessorio.html";
		}
		return "/admin/accessorio/accessorioFormDiModifica.html";
	}
	
	@PostMapping("/accessorio")
	public String addAccessorio(@Valid @ModelAttribute("accessorio") Accessorio accessorio, BindingResult bindingResult, Model model) {
		accessorioValidator.validate(accessorio, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			accessorioService.save(accessorio);
			model.addAttribute("accessorio", accessorio);
			return "/admin/accessorio/accessorio.html";
		}

		return "/admin/accessorio/accessorioForm.html";
	}
	
	@GetMapping("/accessori")
	public String getAllAccessori(Model model) {
		List<Accessorio> accessori = accessorioService.findAll();
		model.addAttribute("accessori", accessori);
		return "/admin/accessorio/accessori.html";
	}
	
	@GetMapping("/accessoriUser")
	public String getAllAccessoriUser(Model model) {
		List<Accessorio> accessori = accessorioService.findAll();
		model.addAttribute("accessori", accessori);
		return "/user/accessorio/accessori.html";
	}
	
	@GetMapping("/accessoriClient")
	public String getAllAccessoriClient(Model model) {
		List<Accessorio> accessori = accessorioService.findAll();
		model.addAttribute("accessori", accessori);
		return "/client/accessorio/accessori.html";
	}
	
	@GetMapping("/accessorioClient/{id}")
	public String getAccessorioClient(@PathVariable("id") Long id, Model model) {
		Accessorio accessorio = accessorioService.findById(id);
		model.addAttribute("accessorio", accessorio);
		return "/client/accessorio/accessorio.html";
	}
	
	@GetMapping("/accessorioUser/{id}")
	public String getAccessorioUser(@PathVariable("id") Long id, Model model) {
		Accessorio accessorio = accessorioService.findById(id);
		model.addAttribute("accessorio", accessorio);
		return "/user/accessorio/accessorio.html";
	}
	
	@GetMapping("/accessorio/{id}")
	public String getAccessorio(@PathVariable("id") Long id, Model model) {
		Accessorio accessorio = accessorioService.findById(id);
		model.addAttribute("accessorio", accessorio);
		return "/admin/accessorio/accessorio.html";
	}
	
	@GetMapping("/accessorioForm")
	public String startAccessorio(Model model) {
		model.addAttribute("accessorio", new Accessorio());
		model.addAttribute("materiali", materialeService.findAll());
		return "/admin/accessorio/accessorioForm.html";
	}

}
