package br.com.cineclube.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.model.Category;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Movie;
import br.com.cineclube.model.WrapperMovieSearch;


@Controller
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired
	private FilmeRepository dao;
	
	@Value("${api.moviedb.key}")
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;
	
	//NOVO FILME
		@RequestMapping("/new")
		public String newForm(Model model) {
			//objeto filme será mapeado ao ${filme} na view(manter.html)
			Filme filme = new Filme();
			model.addAttribute("filme", filme);
			
			//criar lista de categorias
			model.addAttribute("categories", Category.values());
			
			return "/filmes/manterFilmes.html";
		}
	
	//SALVAR
	@PostMapping("/save")
	public String save(@Valid Filme filme, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect: /filmes/manterFilmes.html";
		}else {
			dao.save(filme);
			return "redirect:/filmes/list";
		}
	}
	
	//LISTA TODOS
	@RequestMapping("/list")
	public String list(Model model) {
		List<Filme> filmeList = dao.findAll();
		model.addAttribute("filmeList", filmeList);
		model.addAttribute("categories", Category.values());
		
		return "/filmes/listFilmes.html";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable Long id) {
		dao.deleteById(id);
		return "redirect:/filmes/list";
	}

	//ATUALIZAR
	@PostMapping("/update")
	public String update(Filme filme, Model model) {
		dao.save(filme); 	
		return "redirect:/filmes/list";
	}
	
	//ENVIAR DADOS DA BUSCA POR ID PARA A PAGINA MANTER.HTML
	@RequestMapping("/update/{id}")
	public String getUpdate(Model model, @PathVariable Long id) {
		model.addAttribute("filme", dao.getById(id));
		model.addAttribute("categories", Category.values());
		
		return "/filmes/manterFilmes.html";
	}
	
	 @GetMapping("/discover/genre")
		public String searchByName(@RequestParam String genre, Model model) {
			Map<String, String> params = new HashMap<>();
			params.put("key", apiKey);
			params.put("genre", "with_genres");
			String url = "https://api.themoviedb.org/3/discover/movie?api_key={key}&with_genres={genre}";
			WrapperMovieSearch res = apiRequest.getForObject(url, WrapperMovieSearch.class, params);
			
			Movie movie = null;
			if (res.getResults()!=null && res.getResults().size()>0)
				movie = res.getResults().get(0);
			model.addAttribute(movie);
					
			return "/filmes/filmeList.html";
		}
	 
}
