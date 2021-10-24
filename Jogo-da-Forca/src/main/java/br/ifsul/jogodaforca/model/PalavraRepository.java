package br.ifsul.jogodaforca.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PalavraRepository extends JpaRepository<Palavra, Integer> {
	@Query("FROM Palavra WHERE dificuldade=?1")
	List<Palavra> findByDificuldade(Boolean dificuldade);

	@Query(nativeQuery = true, value = "SELECT * FROM palavra p WHERE p.dificuldade = false ORDER BY rand() LIMIT 1")
	Palavra findRandomByDificuldade(Boolean dificuldade);
	// SELECT p.palavra FROM palavra p WHERE p.dificuldade = false ORDER BY rand()
	// LIMIT 1;
}
