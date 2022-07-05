package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.repository.CollezioneRepository;

@Service
public class CollezioneService {

	@Autowired
	private CollezioneRepository collezioneRepository;

	@Transactional
	public void save(Collezione collezione) {
		collezioneRepository.save(collezione);
	}

	@Transactional
	public void deleteById(Long id) {
		collezioneRepository.deleteById(id); 
	}

	/* Le seguenti operazioni in lettura non è necessario
	 * che siano @Transactional,
	 * nota valida per i metodi findById e findAll
	 */
	
	public Collezione findById(Long id) {
		return collezioneRepository.findById(id).get();
	}


	public List<Collezione> findAll() {
		List<Collezione> collezioniTotali = new ArrayList<Collezione>();
		
		for(Collezione collezione: collezioneRepository.findAll()) {
			collezioniTotali.add(collezione);
		}
		
		return collezioniTotali;
	}
	
	public boolean alreadyExists(Collezione collezione) {
		return this.collezioneRepository.existsCollezioneByNome(collezione.getNome());
	}

	public void upadateMaglietteInCollezioni(List<Collezione> collezioni, Maglietta magliettaDaRimuovere) {
		for(Collezione collezione: collezioni) {
			collezione.getMagliette().remove(magliettaDaRimuovere);
		}
	}

	public void updateAccessoriInCollezioni(List<Collezione> collezioni, Accessorio accessorioDaRimuovere) {
		for(Collezione collezione: collezioni) {
			collezione.getAccessori().remove(accessorioDaRimuovere);
		}
	}

}
