package br.ifsul.jogodaforca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Palavra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String palavra;
	private Boolean dificuldade;

	public Palavra() {
	}

	public Palavra(Integer id, String palavra, Boolean dificuldade) {
		this.id = id;
		this.palavra = palavra;
		this.dificuldade = dificuldade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public Boolean getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(Boolean dificuldade) {
		this.dificuldade = dificuldade;
	}
}
