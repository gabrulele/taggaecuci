package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.OrdineValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Ordine;
import it.uniroma3.siw.service.AccessorioService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MagliettaService;
import it.uniroma3.siw.service.OrdineService;

@Controller
public class OrdineController {

	@Autowired
	private OrdineService ordineService;
	
	@Autowired
	private MagliettaService magliettaService;
	
	@Autowired
	private AccessorioService accessorioService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private OrdineValidator ordineValidator;
	
	@GetMapping("/toDeleteOrdine/{id}")
	public String toDeleteOrdine(@PathVariable("id") Long id, Model model) {
		Ordine ordine = ordineService.findById(id);
		model.addAttribute("ordine", ordine);
		return "/client/ordine/toDeleteOrdine.html";
	}
	
	@GetMapping("/deleteOrdine/{id}")
	public String deleteOrdine(@PathVariable("id") Long id, Model model) {
		ordineService.deleteById(id);
		model.addAttribute("ordini", ordineService.findAll());
		return "/client/ordine/ordiniClient.html";
	}
	
	@PostMapping("/ordine")
	public String addOrdine(@Valid @ModelAttribute("ordine") Ordine ordine, BindingResult bindingResult, Model model) {
		ordineValidator.validate(ordine, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			ordine.setNumeroOrdine(Ordine.getIdOrdine()+1);
			Ordine.setIdOrdine(Ordine.getIdOrdine()+1);
			ordineService.save(ordine);
			model.addAttribute("ordine", ordine);
			return "/client/ordine/ordine.html";
		}
		model.addAttribute("magliette", magliettaService.findAll());
		model.addAttribute("accessori", accessorioService.findAll());
		return "/client/ordine/ordineForm.html";
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
//		
//		ordine.se
//		ordine.setNumeroOrdine(Ordine.getIdOrdine()+1);
//  		Ordine.setIdOrdine(Ordine.getIdOrdine()+1);
//	    ordineService.save(ordine);
//        model.addAttribute("ordine", ordine);
//		return "/client/ordine/ordine.html";
		
	}
	
	@GetMapping("/ordini") 
	public String getAllOrdini(Model model) {
		model.addAttribute("ordini", ordineService.findAll());
		return "/client/ordine/ordini.html";
	}
	
	@GetMapping("/ordineForm") 
	public String startOrdine(Model model) {
		model.addAttribute("ordine", new Ordine());
		model.addAttribute("magliette", magliettaService.findAll());
		model.addAttribute("accessori", accessorioService.findAll());
		int i=0;
		return "/client/ordine/ordineForm.html";
	}
	
}
