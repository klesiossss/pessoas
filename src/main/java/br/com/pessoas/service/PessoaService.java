package br.com.pessoas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pessoas.exceptions.DuplicatedResourceException;
import br.com.pessoas.exceptions.ResourceNotFoundException;
import br.com.pessoas.model.Pessoa;
import br.com.pessoas.model.dto.PessoaDTO;
import br.com.pessoas.repository.PessoaRepository;



@Service
public class PessoaService  {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<PessoaDTO> obterTodos(){	
		var listPessoas = pessoaRepository.findAll();
		return listPessoas.stream().map(PessoaDTO::new).collect(Collectors.toList());
	}
	
	public Page<PessoaDTO> obterTodos(Pageable pageable){	
		var pagePessoas = pessoaRepository.findAll(pageable);
		var listPessoas = pagePessoas.getContent()
				.stream()  
				.map(PessoaDTO::new)
				.collect(Collectors.toList());;
	
		return new PageImpl<>(listPessoas, pageable, pagePessoas.getTotalElements());
	}
	

	public PessoaDTO obterPorId(Long id){
		var pessoa = pessoaRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		return new PessoaDTO(pessoa);
	}
	
	public PessoaDTO obterPorCpf(String cpf){
		var pessoa = pessoaRepository.findByCpf(cpf).orElseThrow(ResourceNotFoundException::new);
		return new PessoaDTO(pessoa);
	}
	
	public List<PessoaDTO> filtrarPorNome(String nome){
		var pessoas = pessoaRepository.findByNomeContainingIgnoreCase(nome);
		return pessoas.stream().map(PessoaDTO::new).collect(Collectors.toList());
	}
	
	public PessoaDTO obterPorNome(String nome){
		var pessoa = pessoaRepository.findByNomeIgnoreCase(nome).orElseThrow(ResourceNotFoundException::new);
		return new PessoaDTO(pessoa);
	}
	
	
	
	public PessoaDTO salvar(PessoaDTO pessoaDTO) {
		Pessoa pessoa = pessoaDTO.getPessoa();
		System.out.println(pessoa);
		var podeSalvar = pessoaDTO.getId() == null;
	
		if(podeSalvar) {
			pessoa.setDataCriacao((LocalDateTime.now()));
			var pessoaSalva = pessoaRepository.save(pessoa);
			
			System.out.println(pessoaSalva);
			return new PessoaDTO(pessoaSalva);
		}
		else throw new DuplicatedResourceException();
	}
	
	
	public PessoaDTO editar(PessoaDTO pessoaDTO) {
		Pessoa pessoa = pessoaDTO.getPessoa();
		var podeEditar = pessoaRepository.findByCpf(pessoa.getCpf()).isPresent();
		if(podeEditar) {
			pessoa.setDataAtualizacao((LocalDateTime.now()));
			var pessoaSalva = pessoaRepository.save(pessoa);
			return new PessoaDTO(pessoaSalva);
		}
		else throw new ResourceNotFoundException();
		
	}
	
	public void remover(PessoaDTO pessoaDTO) {
		Pessoa pessoa = pessoaDTO.getPessoa();
		var deletapessoa = pessoaRepository.findByCpf(pessoa.getCpf()).orElseThrow(ResourceNotFoundException::new);
		 pessoaRepository.delete(deletapessoa);
		 
	}

	
	
}
