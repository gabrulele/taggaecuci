package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siw.model.Ordine;
import it.uniroma3.siw.service.OrdineService;

@Component
public class OrdineValidator implements Validator {

	@Autowired
	private OrdineService ordineService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Ordine.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Ordine ordine = (Ordine) o;
		if(ordineService.isEmpty(ordine))
			errors.reject("ordine.vuoto");

	}

}
