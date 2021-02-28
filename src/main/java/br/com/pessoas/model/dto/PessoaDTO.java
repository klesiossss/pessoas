package br.com.pessoas.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import br.com.pessoas.model.Genero;
import br.com.pessoas.model.Pessoa;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class PessoaDTO implements Serializable {
	
	private static final long serialVersionUID = 3574475229098202153L;
	
	
	private Long id;
	
	@NotBlank(message = "O preenchimento desse campo é obrigatório")
	private String nome;
	
	@NotBlank(message = "O preenchimento desse campo é obrigatório")
	@CPF(message = "Informe um CPF válido")
	private String cpf;
	
	@Email(message = "Informe um Email válido")
	private String email;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@NotNull(message = "O preenchimento desse campo é obrigatório")
	private LocalDate dataNascimento;
	
	private Genero genero;
	private String nacionalidade;
	private String naturalidade;
	
	
	
	public PessoaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.email = pessoa.getEmail();
		this.dataNascimento = pessoa.getDataNascimento();
		this.genero = pessoa.getGenero();
		this.nacionalidade = pessoa.getNacionalidade();
		this.naturalidade = pessoa.getNaturalidade();
	}
	
	@JsonIgnore
	public Pessoa getPessoa() {
		Pessoa p = new Pessoa();
		p.setId(this.id);
		p.setNome(this.nome);
		p.setCpf(this.cpf);
		p.setDataNascimento(this.dataNascimento);
		p.setEmail(this.email);
		p.setGenero(this.genero);
		p.setNaturalidade(this.naturalidade);
		p.setNacionalidade(this.nacionalidade);
		return p;
		
	}
	
	
	
}
