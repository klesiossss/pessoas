package br.com.pessoas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Serializable {
	private static final long serialVersionUID = -4104580228352976447L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Column(unique = true)
	private String cpf;
	private String email;
	private Genero genero;
	private String nacionalidade;
	private String naturalidade;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate dataNascimento;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;


	public Pessoa(long id,String nome, String cpf, String email, Genero genero, String nacionalidade, String naturalidade, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.genero = genero;
		this.nacionalidade = nacionalidade;
		this.naturalidade = naturalidade;
		this.dataNascimento = dataNascimento;
	}
}
