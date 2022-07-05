package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Accessorio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	private String descrizione;
	
	@Min(1)
	private float prezzo;
	
	@ManyToMany
	private List<Materiale> materiali;
	
	@ManyToMany(mappedBy = "accessori")
	private List<Collezione> collezioni;
	
	@ManyToMany(mappedBy = "accessori")
	private List<Ordine> ordini;
	
	public Accessorio() {}

	public Accessorio(Long id, @NotBlank String nome, @NotBlank String descrizione, @Min(1) float prezzo,
			List<Materiale> materiali) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.materiali = materiali;
	}

	public List<Collezione> getCollezioni() {
		return collezioni;
	}

	public void setCollezioni(List<Collezione> collezioni) {
		this.collezioni = collezioni;
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

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public List<Materiale> getMateriali() {
		return materiali;
	}

	public void setMateriali(List<Materiale> materiali) {
		this.materiali = materiali;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

}
