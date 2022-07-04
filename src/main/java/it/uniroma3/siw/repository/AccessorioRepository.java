package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Accessorio;

public interface AccessorioRepository extends CrudRepository<Accessorio, Long> {

	boolean existsAccessorioByNome(String nome);

}
