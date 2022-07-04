package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.service.MagliettaService;

@Component
public class MagliettaValidator implements Validator {
	
	@Autowired
	private MagliettaService magliettaService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Maglietta.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Maglietta maglietta = (Maglietta) o;
		if(magliettaService.alreadyExists(maglietta))
			errors.reject("maglietta.duplicato");
	}

}
