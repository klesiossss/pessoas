package br.com.pessoas.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pessoas.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	
}
