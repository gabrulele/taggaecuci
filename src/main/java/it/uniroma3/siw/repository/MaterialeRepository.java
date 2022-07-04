package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Materiale;

public interface MaterialeRepository extends CrudRepository<Materiale, Long> {

	boolean existsMaterialeByNome(String nome);

}
