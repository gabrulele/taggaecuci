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

import it.uniroma3.siw.controller.validator.CollezioneValidator;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.AccessorioService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.MagliettaService;

@Controller
public class CollezioneController {

	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private MagliettaService magliettaService;
	
	@Autowired
	private AccessorioService accessorioService;
	
	@Autowired
	private CollezioneValidator collezioneValidator;
	
	@GetMapping("/toDeleteCollezione/{id}")
	public String toDeleteCollezione(@PathVariable("id") Long id, Model model) {
		Collezione collezione = collezioneService.findById(id);
		model.addAttribute("collezione", collezione);
		return "/collezione/toDeleteCollezione.html";
	}
	
	@GetMapping("/deleteCollezione/{id}")
	public String deleteCollezione(@PathVariable("id") Long id, Model model) {
		collezioneService.deleteById(id);
		model.addAttribute("collezioni", collezioneService.findAll());
		return "/collezione/collezioni.html";
	}
	
	@GetMapping("/toUpdateCollezione/{id}")
	public String toUpdateCollezione(@PathVariable("id") Long id, Model model) {
		Collezione collezione = collezioneService.findById(id);
		model.addAttribute("collezione", collezione);
		model.addAttribute("magliette", magliettaService.findAll());
		model.addAttribute("accessori", accessorioService.findAll());
		return "/collezione/collezioneFormDiModifica.html";
	}
	
	@PostMapping("/updateCollezione/{id}")
	public String updateCollezione(@Valid @ModelAttribute("collezione") Collezione collezione, BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			collezioneService.save(collezione);
			model.addAttribute("collezione", collezione);
			return "/collezione/collezione.html";
		}
		return "/collezione/collezioneFormDiModifica.html";
	}
	
	@PostMapping("/collezione")
	public String addCollezione(@Valid @ModelAttribute("collezione") Collezione collezione, BindingResult bindingResult, Model model) {
		collezioneValidator.validate(collezione, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			collezioneService.save(collezione);
			model.addAttribute("collezione", collezione);
			return "/collezione/collezione.html";
		}
		return "/collezione/collezioneForm.html";
	}
	
	@GetMapping("/collezioni")
	public String getAllMateriali(Model model) {
		List<Collezione> collezioni = collezioneService.findAll();
		model.addAttribute("collezioni", collezioni);
		return "/collezione/collezioni.html";
	}
	
	@GetMapping("/collezione/{id}")
	public String getAllMateriali(@PathVariable("id") Long id, Model model) {
		Collezione collezione = collezioneService.findById(id);
		model.addAttribute("collezione", collezione);
		return "/collezione/collezione.html";
	}
	
	@GetMapping("/collezioneForm")
	public String startCollezione(Model model) {
		model.addAttribute("collezione", new Collezione());
		model.addAttribute("magliette", magliettaService.findAll());
		model.addAttribute("accessori", accessorioService.findAll());
		return "/collezione/collezioneForm.html";
	}
	
}
