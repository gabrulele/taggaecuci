package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siw.model.Materiale;
import it.uniroma3.siw.service.MaterialeService;

@Component
public class MaterialeValidator implements Validator{
	
	@Autowired
	private MaterialeService materialeService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Materiale.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Materiale materiale = (Materiale) o;
		if(materialeService.alreadyExists(materiale))
			errors.reject("materiale.duplicato");	
	}

}
