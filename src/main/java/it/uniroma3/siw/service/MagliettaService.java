package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.repository.MagliettaRepository;

@Service
public class MagliettaService {
	
	@Autowired
	private MagliettaRepository magliettaRepository;

	@Transactional
	public void save(Maglietta maglietta) {
		magliettaRepository.save(maglietta);
	}

	@Transactional
	public void deleteById(Long id) {
		magliettaRepository.deleteById(id); 
	}

	/* Le seguenti operazioni in lettura non è necessario
	 * che siano @Transactional,
	 * nota valida per i metodi findById e findAll
	 */
	
	public Maglietta findById(Long id) {
		return magliettaRepository.findById(id).get();
	}


	public List<Maglietta> findAll() {
		List<Maglietta> maglietteTotali = new ArrayList<Maglietta>();
		
		for(Maglietta maglietta: magliettaRepository.findAll()) {
			maglietteTotali.add(maglietta);
		}
		
		return maglietteTotali;
	}
	
	public boolean alreadyExists(Maglietta maglietta) {
		return this.magliettaRepository.existsMagliettaByNome(maglietta.getNome());
	}

}
