package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.CollezioneService;

@Component
public class CollezioneValidator implements Validator {

	@Autowired
	private CollezioneService collezioneService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return CollezioneService.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Collezione collezione = (Collezione) o;
		if(collezioneService.alreadyExists(collezione))
			errors.reject("collezione.duplicato");
	}

}
