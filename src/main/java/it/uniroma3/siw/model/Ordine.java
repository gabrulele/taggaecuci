package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Ordine {
	
	private static Integer idOrdine=0;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Integer numeroOrdine;
	
	@NotBlank
	private String numeroDiTelefono;
	
	@ManyToMany
	private List<Maglietta> magliette;
	
	@ManyToMany
	private List<Accessorio> accessori;

	public Ordine() {}

	public Ordine(Long id, @NotBlank String numeroDiTelefono,
			List<Maglietta> magliette, List<Accessorio> accessori) {
		super();
		this.id = id;
		this.numeroOrdine = Ordine.idOrdine;
		this.numeroDiTelefono = numeroDiTelefono;
		this.magliette = magliette;
		this.accessori = accessori;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Integer getIdOrdine() {
		return idOrdine;
	}

	public static void setIdOrdine(Integer idOrdine) {
		Ordine.idOrdine = idOrdine;
	}

	public Integer getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(Integer numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public String getNumeroDiTelefono() {
		return numeroDiTelefono;
	}

	public void setNumeroDiTelefono(String numeroDiTelefono) {
		this.numeroDiTelefono = numeroDiTelefono;
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
