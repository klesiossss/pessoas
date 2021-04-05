package br.com.pessoa.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pessoa.exception.DuplicatedResourceException;
import br.com.pessoa.exception.ResourceNotFoundException;
import br.com.pessoa.model.Pessoa;
import br.com.pessoa.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	Page<Pessoa> obterTodos(Pageable pageagle){
		return pessoaRepository.findAll(pageagle);
	}
	
	
	List<Pessoa> obterTodos() {
		return pessoaRepository.findAll();	
	}
	
	
	
	Optional<Pessoa> buscaPorId(Long id) {
		return pessoaRepository.findById(id);
	}
	
	
	Optional<Pessoa> buscaPorCpf(String cpf){
		return pessoaRepository.findByCpf(cpf);
	}
	
	Optional<Pessoa> buscaPorNome(String nome){
		return pessoaRepository.findByNomeIgnoreCase(nome);
	}
	
	
	List<Pessoa> buscaPorNomeFiltro(String nome){
		return pessoaRepository.findByNomeContaingIgnoreCase(nome);
	}
	
	
	Pessoa salvar(Pessoa pessoa) {
		var podeSalvar = pessoa.getId() == null && pessoaRepository.findByCpf(pessoa.getCpf()).isEmpty();
		
		if(podeSalvar) 
			return pessoaRepository.save(pessoa);
		else
			throw new DuplicatedResourceException();
		
	}
	
	Pessoa update(Pessoa pessoa) {
		var podeAtualizar = pessoa.getId() != null && pessoaRepository.findByCpf(pessoa.getCpf()).isPresent();
		
		if(podeAtualizar)
			return pessoaRepository.save(pessoa);
		else
			throw new ResourceNotFoundException();
	}
	
	void delete(Pessoa pessoa) {
		var podeDeletar = pessoa.getId() != null && pessoaRepository.findByCpf(pessoa.getCpf()).isPresent();
		
		if(podeDeletar)
			pessoaRepository.delete(pessoa);
		else
			throw new ResourceNotFoundException();
	}
	
	
	

}
