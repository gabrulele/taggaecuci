package it.uniroma3.siw.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    protected UserRepository userRepository;

    public User getUser(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

	public boolean alreadyExist(User user) {
		return userRepository.existsByNomeAndCognome(user.getNome(), user.getCognome());
	}
	
	public boolean containsNumbers(String nome, String cognome) {
		for(char c: nome.toCharArray()) {
			if(Character.isDigit(c))
				return true;
		}
		for(char c: cognome.toCharArray()) {
			if(Character.isDigit(c))
				return true;
		}
		return false;
	}

	public boolean validaEmail(User user) {
		return userRepository.existsByEmail(user.getEmail());
	}
	
}
