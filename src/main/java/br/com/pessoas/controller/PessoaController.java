
package br.com.pessoas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pessoas.model.dto.PessoaDTO;
import br.com.pessoas.service.PessoaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<PessoaDTO>> obterTodos() {
		var pessoas = pessoaService.obterTodos();
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping()
	public ResponseEntity<Page<PessoaDTO>> obterTodos(@PageableDefault(size = 10, page = 0) Pageable pageable) {
		var pessoas = pessoaService.obterTodos(pageable);
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDTO> obterPorId(@PathVariable Long id) {
		var pessoa = pessoaService.obterPorId(id);
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<PessoaDTO> obterPorCpf(@PathVariable String cpf) {
		var pessoa = pessoaService.obterPorCpf(cpf);
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("filter/nome/{nome}")
	public ResponseEntity<List<PessoaDTO>> filtrarPorNome(@PathVariable String nome) {
		var pessoa = pessoaService.filtrarPorNome(nome);
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<PessoaDTO> obterPorNome(@PathVariable String nome) {
		var pessoa = pessoaService.obterPorNome(nome);
		return ResponseEntity.ok(pessoa);
	}	
	
	@PostMapping
	public ResponseEntity<PessoaDTO> salvar(@RequestBody @Valid PessoaDTO pessoaDTO) {
		var pessoa = pessoaService.salvar(pessoaDTO);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(pessoa);
	}
	
	
	@PutMapping
	public ResponseEntity<PessoaDTO> editar(@RequestBody @Valid PessoaDTO pessoaDTO) {
		var pessoa = pessoaService.editar(pessoaDTO);
		return ResponseEntity.ok(pessoa);	
	}
	
	@DeleteMapping
	public ResponseEntity<PessoaDTO> remover(@RequestBody @Valid PessoaDTO pessoaDTO) {
		pessoaService.remover(pessoaDTO);
		return ResponseEntity.ok().build();
	}

}