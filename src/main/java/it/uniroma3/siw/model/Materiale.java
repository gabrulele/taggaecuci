package it.uniroma3.siw.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Materiale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	private String descrizione;
	
	@ManyToMany(mappedBy = "materiali", cascade = {CascadeType.REMOVE})
	private List<Accessorio> accessori;

	@ManyToMany(mappedBy = "materiali", cascade = {CascadeType.REMOVE})
	private List<Maglietta> magliette;
	
	public Materiale() {}

	public Materiale(Long id, @NotBlank String nome, String descrizione, List<Accessorio> accessori,
			List<Maglietta> magliette) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.accessori = accessori;
		this.magliette = magliette;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Accessorio> getAccessori() {
		return accessori;
	}

	public void setAccessori(List<Accessorio> accessori) {
		this.accessori = accessori;
	}

	public List<Maglietta> getMagliette() {
		return magliette;
	}

	public void setMagliette(List<Maglietta> magliette) {
		this.magliette = magliette;
	}
	
}
