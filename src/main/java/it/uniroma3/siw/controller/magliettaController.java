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

import it.uniroma3.siw.controller.validator.MagliettaValidator;
import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.MagliettaService;
import it.uniroma3.siw.service.MaterialeService;

@Controller
public class magliettaController {

	@Autowired
	private MagliettaService magliettaService;
	
	@Autowired
	private MaterialeService materialeService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private MagliettaValidator magliettaValidator;
	
	@GetMapping("/toDeleteMaglietta/{id}")
	public String toDeleteMaglietta(@PathVariable("id") Long id, Model model) {
		Maglietta maglietta = magliettaService.findById(id);
		model.addAttribute("maglietta", maglietta);
		return "/maglietta/toDeleteMaglietta.html";
	}
	
	@GetMapping("/deleteMaglietta/{id}")
	public String deleteMaglietta(@PathVariable("id") Long id, Model model) {
		//collezioneService.upadateCollezioni();
		magliettaService.deleteById(id);
		model.addAttribute("magliette", magliettaService.findAll());
		return "/maglietta/magliette.html";
	}
	
	@GetMapping("/toUpdateMaglietta/{id}")
	public String toUpdateMaglietta(@PathVariable("id") Long id, Model model) {
		Maglietta maglietta = magliettaService.findById(id);
		model.addAttribute("maglietta", maglietta);
		model.addAttribute("materiali", materialeService.findAll());
		return "/maglietta/magliettaFormDiModifica.html";
	}
	
	@PostMapping("/updateMaglietta/{id}")
	public String updateMaglietta(@Valid @ModelAttribute("maglietta") Maglietta maglietta, BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			magliettaService.save(maglietta);
			model.addAttribute("maglietta", maglietta);
			return "/maglietta/maglietta.html";
		}
		return "/maglietta/magliettaFormDiModifica.html";
	}
	
	@PostMapping("/maglietta")
	public String addMaglietta(@Valid @ModelAttribute("maglietta") Maglietta maglietta, BindingResult bindingResult, Model model) {
		magliettaValidator.validate(maglietta, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			magliettaService.save(maglietta);
			model.addAttribute("maglietta", maglietta);
			return "/maglietta/maglietta.html";
		}
		return "/maglietta/magliettaForm.html";
	}
	
	@GetMapping("/magliette")
	public String getAllMateriali(Model model) {
		List<Maglietta> magliette = magliettaService.findAll();
		model.addAttribute("magliette", magliette);
		return "/maglietta/magliette.html";
	}
	
	@GetMapping("/maglietta/{id}")
	public String getAllMateriali(@PathVariable("id") Long id, Model model) {
		Maglietta maglietta = magliettaService.findById(id);
		model.addAttribute("maglietta", maglietta);
		return "/maglietta/maglietta.html";
	}
	
	@GetMapping("/magliettaForm")
	public String startMaglietta(Model model) {
		model.addAttribute("maglietta", new Maglietta());
		model.addAttribute("materiali", materialeService.findAll());
		return "/maglietta/magliettaForm.html";
	}
	
}
