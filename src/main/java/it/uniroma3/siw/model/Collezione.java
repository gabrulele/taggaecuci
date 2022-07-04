package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Collezione {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String stagione;
	
	@ManyToMany
	private List<Maglietta> magliette;
	
	@ManyToMany
	private List<Accessorio> accessori;

	public Collezione() {}

	public Collezione(Long id, @NotBlank String nome, @NotBlank String stagione, List<Maglietta> magliette,
			List<Accessorio> accessori) {
		super();
		this.id = id;
		this.nome = nome;
		this.stagione = stagione;
		this.magliette = magliette;
		this.accessori = accessori;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStagione() {
		return stagione;
	}

	public void setStagione(String stagione) {
		this.stagione = stagione;
	}

	public List<Maglietta> getMagliette() {
		return magliette;
	}

	public void setMagliette(List<Maglietta> magliette) {
		this.magliette = magliette;
	}

	public List<Accessorio> getAccessori() {
		return accessori;
	}

	public void setAccessori(List<Accessorio> accessori) {
		this.accessori = accessori;
	}
	
}
