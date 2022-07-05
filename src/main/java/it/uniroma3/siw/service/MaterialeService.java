package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.model.Materiale;
import it.uniroma3.siw.model.Ordine;
import it.uniroma3.siw.repository.MaterialeRepository;

@Service
public class MaterialeService {
	
	@Autowired
	private MaterialeRepository materialeRepository;

	@Transactional
	public void save(Materiale materiale) {
		materialeRepository.save(materiale);
	}

	@Transactional
	public void deleteById(Long id) {
		materialeRepository.deleteById(id); 
	}

	/* Le seguenti operazioni in lettura non è necessario
	 * che siano @Transactional,
	 * nota valida per i metodi findById e findAll
	 */
	
	public Materiale findById(Long id) {
		return materialeRepository.findById(id).get();
	}

	public List<Materiale> findAll() {
		List<Materiale> materialiTotali = new ArrayList<Materiale>();
		
		for(Materiale materiale: materialeRepository.findAll()) {
			materialiTotali.add(materiale);
		}
		
		return materialiTotali;
	}
	
	public boolean alreadyExists(Materiale materiale) {
		return this.materialeRepository.existsMaterialeByNome(materiale.getNome());
	}

	public void updateCollezioneEOrdini(List<Accessorio> accessori, List<Maglietta> magliette) {
		for(Accessorio accessorio: accessori) {
			for(Collezione collezione: accessorio.getCollezioni()) {
				collezione.getAccessori().remove(accessorio);
			}
		}
		
		for(Maglietta maglietta: magliette) {
			for(Collezione collezione: maglietta.getCollezioni()) {
				collezione.getMagliette().remove(maglietta);
			}
		}
		
		for(Accessorio accessorio: accessori) {
			for(Ordine ordine: accessorio.getOrdini()) {
				ordine.getAccessori().remove(accessorio);
			}
		}
		
		for(Maglietta maglietta: magliette) {
			for(Ordine ordine: maglietta.getOrdini()) {
				ordine.getMagliette().remove(maglietta);
			}
		}
	}

}
