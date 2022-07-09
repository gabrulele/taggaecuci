package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.service.AccessorioService;

@Component
public class AccessorioValidator implements Validator {
	
	@Autowired
	private AccessorioService accessorioService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Accessorio.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Accessorio accessorio = (Accessorio) o;
		if(accessorioService.alreadyExists(accessorio)) 
			errors.reject("accessorio.duplicato");
	}

}
