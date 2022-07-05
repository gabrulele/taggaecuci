package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Materiale;
import it.uniroma3.siw.service.MaterialeService;

@Controller
public class InitializerController {

	@Autowired
	private MaterialeService materialeService;
	
	@GetMapping("/initializer")
	public String initializer() {
		
		Materiale mat1 = new Materiale("Cotone","100% cotone riciclato", null, null);
		Materiale mat2 = new Materiale("Lino","100% puro lino", null, null);
		Materiale mat3 = new Materiale("Similpelle","finta pelle", null, null);
		
		materialeService.save(mat1);
		materialeService.save(mat2);
		materialeService.save(mat3);
		
		return "/index";
	}

}
