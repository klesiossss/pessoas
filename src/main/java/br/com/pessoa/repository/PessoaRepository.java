package br.com.pessoa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pessoa.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	List<Pessoa> findByNomeContaingIgnoreCase(String nome);
	Optional<Pessoa> findByNomeIgnoreCase(String nome);
	Optional<Pessoa>findByCpf(String cpf);	
	
}
