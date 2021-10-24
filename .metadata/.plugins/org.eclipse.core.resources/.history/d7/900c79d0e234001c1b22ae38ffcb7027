package br.ifsul.jogodaforca.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PalavraRepository extends JpaRepository<Palavra, Integer> {
	@Query("FROM Palavra WHERE dificuldade=?1")
	List<Palavra> findByDificuldade(Boolean dificuldade);
}
