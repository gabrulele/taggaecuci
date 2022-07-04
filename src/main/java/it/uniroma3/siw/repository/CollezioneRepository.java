package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Collezione;

public interface CollezioneRepository extends CrudRepository<Collezione, Long> {

	boolean existsCollezioneByNome(String nome);

}
