package br.com.cineclube.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;

/*Controler que escuta as requisições*/
@RestController
public class ApiController {
	
	//Planejar os endpoints
		// GET /filmes/{id}

		// Referencia para o banco de dados = daoFilme | Tudo relacionado a banco está em daoFilme
		@Autowired
		private FilmeRepository daoFilme;
		
		@Autowired
		private PessoaRepository daoPessoa;
		
		
		//FILMES API REST
		//http://localhost:8080/api/filme/id - para testar o request.
		//GET FILME
		@GetMapping("/api/filme/{id}")
		public Filme getFilme(@PathVariable Long id) {
			return daoFilme.getById(id);
		}
		
		//GET ALL
		
		//SAVE FILME
		@PostMapping("/api/filme/save")
		Filme postFilme (@RequestBody Filme filme) {
			filme.setRelease(LocalDate.now());
			daoFilme.save(filme);
			return filme;
		}
		
		//DELETE FILME
		@DeleteMapping("/api/filme/delete/{id}")
		public void deleteFilme(@PathVariable Long id) {
			daoFilme.deleteById(id);
		}
		
		///UPDATE FILME
		@PutMapping("/api/filme/update/{id}")
		ResponseEntity<Filme> putFilme(@PathVariable Long id, @RequestBody Filme filme){
			Filme f = daoFilme.save(filme);
			if(f!=null)
					return new ResponseEntity<>(filme, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(filme, HttpStatus.OK);
		}
		
		//GET ALL FILMES
		@GetMapping(value = "/api/filmes")
		public Iterable<Filme> getFilmes() {
			return daoFilme.findAll();
		}

		
		
		
		
		//PESSOAS API REST
		//SAVE PESSOA
		@PostMapping("/api/pessoa/save")
		Pessoa postPessoa (@RequestBody Pessoa pessoa) {
			//pessoa.setBirthday(LocalDate.now());
			daoPessoa.save(pessoa);
			return pessoa;
		}
		
		//GET PESSOA
		@GetMapping("/api/pessoa/{id}")
		Optional<Pessoa> getPessoa(@PathVariable Long id){
			return daoPessoa.findById(id);
		}
		
		
		//GET ALL PESOAS
		@GetMapping(value = "/api/pessoas")
		public Iterable<Pessoa> getPessoas() {
			return daoPessoa.findAll();
		}
		
		//DELETE PESSOA
		@DeleteMapping("/api/pessoa/delete/{id}")
		public void deletePessoa(@PathVariable Long id) {
			daoPessoa.deleteById(id);
		}
		
		//UPDATE PESSOA
		//http :8080/api/pessoa/update < arquivo.json
		@PutMapping("/api/pessoa/update/{id}")
		ResponseEntity<Pessoa> putPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa){
			Pessoa p = daoPessoa.save(pessoa);
			if(p!=null)
					return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(pessoa, HttpStatus.OK);
		}
}
