package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Maglietta;

public interface MagliettaRepository extends CrudRepository<Maglietta, Long> {

	boolean existsMagliettaByNome(String nome);

}
