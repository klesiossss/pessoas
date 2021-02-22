package br.com.pessoas.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.pessoas.model.Genero;
import br.com.pessoas.model.Pessoa;
import lombok.Data;
import lombok.NoArgsConstructor;


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
	
	@NotNull(message = "O preenchimento desse campo é obrigatório")
	private LocalDate dataNascimento;
	
	private Genero genero;
	private String nacionalidade;
	private String naturalidade;
	
	public PessoaDTO(Pessoa pessoa) {
		this.nome = pessoa.getName();
		this.cpf = pessoa.getCpf();
		this.email = pessoa.getEmail();
		this.dataNascimento = pessoa.getDataNascimento();
		this.genero = pessoa.getGenero();
		this.nacionalidade = pessoa.getNacionalidade();
		this.naturalidade = pessoa.getNaturalidade();
	}
	
}
