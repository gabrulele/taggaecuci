package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.authentication.AuthConfiguration;
import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.UserValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private CredentialsValidator credentialsValidator;

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		return "loginForm.html";
	}
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		List<String> roles = new ArrayList<String>();
		roles.add(AuthConfiguration.ADMIN_ROLE);
		roles.add(AuthConfiguration.USER_ROLE);
		
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		model.addAttribute("roles", roles);
		return "registerUser.html";
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "indexStandard.html";
	}

	@GetMapping("/default")
	public String defaultAfterLogin(Model model) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		
		if (credentials.getRole().equals(AuthConfiguration.ADMIN_ROLE)) {
			return "admin/index.html";
		}
		if (credentials.getRole().equals(AuthConfiguration.USER_ROLE)) {
			return "client/indexClient.html";
		}
		return "indexStandard.html";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult userBindingResult,
			@Valid @ModelAttribute("credentials") Credentials credentials, BindingResult credentialsBindingResult,
			Model model) {

		userValidator.validate(user, userBindingResult);
		userValidator.validateEmail(user, userBindingResult);
		credentialsValidator.validate(credentials, credentialsBindingResult);
		
		if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
			if(credentials.getRole().equals(AuthConfiguration.ADMIN_ROLE)) {
				credentials.setUser(user);
				credentials.setRole(AuthConfiguration.ADMIN_ROLE);
				credentialsService.saveCredentials(credentials);
				return "registrationCompleted.html";
			}
			if(credentials.getRole().equals(AuthConfiguration.USER_ROLE)) {
				credentials.setUser(user);
				credentials.setRole(AuthConfiguration.USER_ROLE);
				credentialsService.saveCredentials(credentials);
				return "registrationCompleted.html";
			}
		}

		List<String> roles = new ArrayList<String>();
		roles.add(AuthConfiguration.ADMIN_ROLE);
		roles.add(AuthConfiguration.USER_ROLE);
		model.addAttribute("roles", roles);
		
		return "registerUser.html";
	}
	
	@GetMapping("/indexClient")
	public String indexClient(Model model) {
		return "/client/indexClient.html";
	}
	
	@GetMapping("/indexAdmin")
	public String indexAdmin(Model model) {
		return "/admin/index.html";
	}
	
	@GetMapping({ "/", "/indexStandard", "/indexUser"})
	public String indexStandard(Model model) {
		return "indexStandard.html";
	}

}
