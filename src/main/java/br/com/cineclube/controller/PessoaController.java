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

import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Category;
import br.com.cineclube.model.Person;
import br.com.cineclube.model.Pessoa;
import br.com.cineclube.model.WrapperPersonSearch;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository daoPessoa;
	
	
	@Value("${api.moviedb.key}")
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;
	
	/* NOVA PESSOA*/
	@RequestMapping("/new")
	public String novaPessoa(Model model) {
		Pessoa pessoa = new Pessoa();
		model.addAttribute("pessoa", pessoa);
		return "/pessoas/manterPessoas.html";
	}

	/* SAVE PESSOA */
	@PostMapping("/save")
	public String save(@Valid Pessoa pessoa, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:/pessoas/manterPessoas.html";
		}
		daoPessoa.save(pessoa);
		return "redirect:/pessoas/list";
	}
	
	
	/* UPDATE PESSOA */
	@PostMapping("/update")
	public String update(Pessoa pessoa, Model model) {
		daoPessoa.save(pessoa); 	
		return "redirect:/pessoas/list";
	}
		
	//ENVIAR DADOS DA BUSCA POR ID PARA A PAGINA MANTER.HTML
	@RequestMapping("/update/{id}")
	public String getUpdate(Model model, @PathVariable Long id) {
		model.addAttribute("pessoa", daoPessoa.getById(id));
		model.addAttribute("categories", Category.values());
		return "/pessoas/manterPessoas.html";
	}

	/* DELETE PESSOA */
	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable Long id) {
		daoPessoa.deleteById(id);
		return "redirect:/pessoas/list";
	}
	
	//LISTA TODOS
		@RequestMapping("/list")
		public String list(Model model) {
			List<Pessoa> pessoaList = daoPessoa.findAll();
			model.addAttribute("pessoaList", pessoaList);
			
			return "/pessoas/listPessoas.html";
		}
	
		@GetMapping("/personTmdb")
		public String searchPersonByName(@RequestParam String name, Model model) {
			Map<String, String> params = new HashMap<>();
			params.put("key", apiKey);
			params.put("id", "id");
			params.put("query", name);
			params.put("popularity", "popularity");
			params.put("profile_path", "profile_path");
			String url = "https://api.themoviedb.org/3/search/person?api_key={key}&query={query}&popularity={popularity}&profile_path={profile_path}";
			WrapperPersonSearch res = apiRequest.getForObject(url, WrapperPersonSearch.class, params);		
			Person person = null;
			
			if (res.getResults()!=null && res.getResults().size()>0) {
				person = res.getResults().get(0);
				person.setProfile_path("https://image.tmdb.org/t/p/w500/" + person.getProfile_path());
			}
			model.addAttribute("person", person);
			
			return "/pessoas/listPessoas.html";
	    }
}
