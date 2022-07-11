package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object user, Errors errors) {
		if (this.userService.alreadyExist((User) user))
			errors.reject("user.duplicato");
	}
	
	public void validateEmail(Object user, Errors errors) {
		if (this.userService.validaEmail((User) user))
			errors.reject("email.duplicato");
	}
	
}
