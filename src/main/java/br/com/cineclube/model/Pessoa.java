package br.com.cineclube.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "campo obrigatório")
	@Size(min = 3, max = 50, message = "Campo deve conter entre {min} e {max} carácteres")
	private String nome;

//	@Past // não aceita data futura
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	private Integer idade;

	public Pessoa() {}

	public Pessoa(String nome, LocalDate birthday, Integer idade) {
		this.nome = nome;
		this.birthday = birthday;
		this.idade = idade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	

}
