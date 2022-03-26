package br.com.cineclube.model;

import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Filme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@Past // não aceita data futura
//	@NotNull
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate release;
	
//	@NotNull
	private Category category;
	private BigDecimal score;
	
	//@JsonIgnore //vai ser filtrado e não enviado no json
	//@NotNull //pega o caso de entrar "            "
	//@NotEmpty //nao aceita valor nulç
//	@NotBlank(message = "Campo obrigatório") //não aceita null nem string vazia ""
//	@Size(min = 1, max = 50, message = "Campo deve conter entre {min} e {max} carácteres")
	private String title;
	
	//https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg
	/*	String enderecoBase = "https://image.tmdb.org/t/p/w500/"
	 *  String poster_path =  "/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg"
	 *  String imagem = enderecoBase + poster_path
	 */
	private String imagem;
	
	
	public Filme() {} // deixar sempre um contrutor vazio ao se trabalhar com framework
	
	//metodo construtor
	public Filme(String title, LocalDate release, Category cat, BigDecimal score) {
		this.title = title;
		this.release = release;
		this.category = cat;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getRelease() {
		return release;
	}

	public void setRelease(LocalDate release) {
		this.release = release;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
