package br.com.pessoas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Pessoa implements Serializable {
	private static final long serialVersionUID = -4104580228352976447L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private String email;
	private Genero genero;
	private String nacionalidade;
	private String naturalidade;
	private LocalDate dataNascimento;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
}
