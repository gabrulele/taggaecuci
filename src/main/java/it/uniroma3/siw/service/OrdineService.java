package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.model.Ordine;
import it.uniroma3.siw.repository.OrdineRepository;

@Service
public class OrdineService {

	@Autowired
	private OrdineRepository ordineRepository;

	@Transactional
	public void save(Ordine ordine) {
		ordineRepository.save(ordine);
	}

	@Transactional
	public void deleteById(Long id) {
		ordineRepository.deleteById(id);
	}

	/*
	 * Le seguenti operazioni in lettura non è necessario che siano @Transactional,
	 * nota valida per i metodi findById e findAll
	 */

	public Ordine findById(Long id) {
		return ordineRepository.findById(id).get();
	}

	public List<Ordine> findAll() {
		List<Ordine> ordiniTotali = new ArrayList<Ordine>();

		for (Ordine ordine : ordineRepository.findAll()) {
			ordiniTotali.add(ordine);
		}

		return ordiniTotali;
	}

	public void updateMaglietteInOrdini(List<Ordine> ordini, Maglietta maglietta) {
		for (Ordine ordine : ordini) {
			ordine.getMagliette().remove(maglietta);
		}
	}

	public void updateAccessoriInOrdini(List<Ordine> ordini, Accessorio accessorio) {
		for (Ordine ordine : ordini) {
			ordine.getAccessori().remove(accessorio);
		}
	}

	public boolean isEmpty(Ordine ordine) {
		if (ordine.getAccessori() == null && ordine.getMagliette() == null)
			return true;
		if (ordine.getAccessori() == null)
			return ordine.getMagliette().isEmpty();
		if (ordine.getMagliette() == null)
			return ordine.getAccessori().isEmpty();
		if (ordine.getAccessori().isEmpty() && ordine.getMagliette().isEmpty())
			return true;
			 
		return false;
	}

}
