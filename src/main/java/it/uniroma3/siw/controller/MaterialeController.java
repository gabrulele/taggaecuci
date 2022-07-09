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
import it.uniroma3.siw.controller.validator.MaterialeValidator;
import it.uniroma3.siw.model.Materiale;
import it.uniroma3.siw.service.MaterialeService;

@Controller
public class MaterialeController {

	@Autowired
	private MaterialeService materialeService;
	
	@Autowired
	private MaterialeValidator materialeValidator;
	
	@GetMapping("/toDeleteMateriale/{id}")
	public String toDeleteMateriale(@PathVariable("id") Long id, Model model) {
		Materiale materiale = materialeService.findById(id);
		model.addAttribute("materiale", materiale);
		return "/admin/materiale/toDeleteMateriale.html";
	}
	
	@GetMapping("/deleteMateriale/{id}")
	public String deleteMateriale(@PathVariable("id") Long id, Model model) {
		Materiale materiale = materialeService.findById(id);
		materialeService.updateCollezioneEOrdini(materiale.getAccessori(), materiale.getMagliette());
		materialeService.deleteById(id);
		model.addAttribute("materiali", materialeService.findAll());
		return "/admin/materiale/materiali.html";
	}
	
	@GetMapping("/toUpdateMateriale/{id}")
	public String toUpdateMateriale(@PathVariable("id") Long id, Model model) {
		Materiale materiale = materialeService.findById(id);
		model.addAttribute("materiale", materiale);
		return "/admin/materiale/materialeFormDiModifica.html";
	}
	
	@PostMapping("/updateMateriale/{id}")
	public String updateMateriale(@Valid @ModelAttribute("materiale") Materiale materiale, BindingResult bindingResult, Model model) {
		materialeValidator.validate(materiale, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			materialeService.save(materiale);
			model.addAttribute("materiale", materiale);
			return "/admin/materiale/materiale.html";
		}
		return "/admin/materiale/materialeFormDiModifica.html";
	}
	
	@PostMapping("/materiale")
	public String addMateriale(@Valid @ModelAttribute("materiale") Materiale materiale, BindingResult bindingResult, Model model) {
		materialeValidator.validate(materiale, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			materialeService.save(materiale);
			model.addAttribute("materiale", materiale);
			return "/admin/materiale/materiale.html";
		}
		return "/admin/materiale/materialeForm.html";
	}
	
	@GetMapping("/materiali")
	public String getAllMateriali(Model model) {
		List<Materiale> materiali = materialeService.findAll();
		model.addAttribute("materiali", materiali);
		return "/admin/materiale/materiali.html";
	}
	
	@GetMapping("/materialiUser")
	public String getAllMaterialiUser(Model model) {
		List<Materiale> materiali = materialeService.findAll();
		model.addAttribute("materiali", materiali);
		return "/user/materiale/materiali.html";
	}
	
	@GetMapping("/materialiClient")
	public String getAllMaterialiClient(Model model) {
		List<Materiale> materiali = materialeService.findAll();
		model.addAttribute("materiali", materiali);
		return "/client/materiale/materiali.html";
	}
	
	@GetMapping("/materialeClient/{id}")
	public String getMaterialeClient(@PathVariable("id") Long id, Model model) {
		Materiale materiale = materialeService.findById(id);
		model.addAttribute("materiale", materiale);
		return "/client/materiale/materiale.html";
	}
	
	@GetMapping("/materialeUser/{id}")
	public String getMaterialeUser(@PathVariable("id") Long id, Model model) {
		Materiale materiale = materialeService.findById(id);
		model.addAttribute("materiale", materiale);
		return "/user/materiale/materiale.html";
	}
	
	@GetMapping("/materiale/{id}")
	public String getMateriale(@PathVariable("id") Long id, Model model) {
		Materiale materiale = materialeService.findById(id);
		model.addAttribute("materiale", materiale);
		return "/admin/materiale/materiale.html";
	}
	
	@GetMapping("/materialeForm")
	public String startMateriale(Model model) {
		model.addAttribute("materiale", new Materiale());
		return "/admin/materiale/materialeForm.html";
	}
	
}
