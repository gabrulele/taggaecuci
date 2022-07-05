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
		return "/materiale/toDeleteMateriale.html";
	}
	
	@GetMapping("/deleteMateriale/{id}")
	public String deleteMateriale(@PathVariable("id") Long id, Model model) {
		Materiale materiale = materialeService.findById(id);
		materialeService.updateCollezioneEOrdini(materiale.getAccessori(), materiale.getMagliette());
		materialeService.deleteById(id);
		model.addAttribute("materiali", materialeService.findAll());
		return "/materiale/materiali.html";
	}
	
	@GetMapping("/toUpdateMateriale/{id}")
	public String toUpdateMateriale(@PathVariable("id") Long id, Model model) {
		Materiale materiale = materialeService.findById(id);
		model.addAttribute("materiale", materiale);
		return "/materiale/materialeFormDiModifica.html";
	}
	
	@PostMapping("/updateMateriale/{id}")
	public String updateMateriale(@Valid @ModelAttribute("materiale") Materiale materiale, BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			materialeService.save(materiale);
			model.addAttribute("materiale", materiale);
			return "/materiale/materiale.html";
		}
		return "/materiale/materialeFormDiModifica.html";
	}
	
	@PostMapping("/materiale")
	public String addMateriale(@Valid @ModelAttribute("materiale") Materiale materiale, BindingResult bindingResult, Model model) {
		materialeValidator.validate(materiale, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			materialeService.save(materiale);
			model.addAttribute("materiale", materiale);
			return "/materiale/materiale.html";
		}
		return "/materiale/materialeForm.html";
	}
	
	@GetMapping("/materiali")
	public String getAllMateriali(Model model) {
		List<Materiale> materiali = materialeService.findAll();
		model.addAttribute("materiali", materiali);
		return "/materiale/materiali.html";
	}
	
	@GetMapping("/materiale/{id}")
	public String getAllMateriali(@PathVariable("id") Long id, Model model) {
		Materiale materiale = materialeService.findById(id);
		model.addAttribute("materiale", materiale);
		return "/materiale/materiale.html";
	}
	
	@GetMapping("/materialeForm")
	public String startMateriale(Model model) {
		model.addAttribute("materiale", new Materiale());
		return "/materiale/materialeForm.html";
	}
	
}
