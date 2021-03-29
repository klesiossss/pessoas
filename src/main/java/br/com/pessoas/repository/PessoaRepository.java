package br.com.pessoas.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pessoas.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByNomeIgnoreCase(String nome);
	List<Pessoa> findByNomeContainingIgnoreCase(String nome);
	
}
