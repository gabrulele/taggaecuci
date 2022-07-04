package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.repository.AccessorioRepository;

@Service
public class AccessorioService {
	
	@Autowired
	private AccessorioRepository accessorioRepository;

	@Transactional
	public void save(Accessorio accessorio) {
		accessorioRepository.save(accessorio);
	}

	@Transactional
	public void deleteById(Long id) {
		accessorioRepository.deleteById(id); 
	}

	/* Le seguenti operazioni in lettura non è necessario
	 * che siano @Transactional,
	 * nota valida per i metodi findById e findAll
	 */
	
	public Accessorio findById(Long id) {
		return accessorioRepository.findById(id).get();
	}

	public List<Accessorio> findAll() {
		List<Accessorio> accessoriTotali = new ArrayList<Accessorio>();
		
		for(Accessorio accessorio: accessorioRepository.findAll()) {
			accessoriTotali.add(accessorio);
		}
		
		return accessoriTotali;
	}
	
	public boolean alreadyExists(Accessorio accessorio) {
		return this.accessorioRepository.existsAccessorioByNome(accessorio.getNome());
	}

}
