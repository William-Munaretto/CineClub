package br.com.cineclube.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.model.Person;
import br.com.cineclube.model.WrapperPersonSearch;

@RestController
@RequestMapping("/api/v2")
public class PersonConsumer {
		
		@Value("${api.moviedb.key}")
	    private String apiKey;

	    @Autowired
	    private RestTemplate apiRequest;
	    
	    //
	    @RequestMapping("/person/{id}")
	    public Person getPersonById(@PathVariable Long id) {
	    	String endpoint = 
	        		"https://api.themoviedb.org/3/person/" + id + "?api_key=" +  apiKey;
	    	//parâmetros => endpoint : URL onde será feita a request, em a classe que vai jogar os dados)
	        Person people = apiRequest.getForObject(endpoint, Person.class);
	        return people;
	    }
	    
	    //MÉTODO: searchPerson() -> retorna uma lista de pessoas.
	    @GetMapping("/search")
		public WrapperPersonSearch searchPerson(@RequestParam String name) {
			Map<String, String> params = new HashMap<>();
			params.put("key", apiKey);
			params.put("query", name);
			String url = "https://api.themoviedb.org/3/search/person?api_key={key}&query={query}";
			WrapperPersonSearch res = apiRequest.getForObject(url, WrapperPersonSearch.class, params);
			return res;
		}
	    
	    @GetMapping("/person")
		public Optional<Person> searchPersonByName(@RequestParam String name) {
			Map<String, String> params = new HashMap<>();
			params.put("key", apiKey);
			params.put("id", "id");
			params.put("query", name);
			params.put("popularity", "popularity");
			params.put("profile_path", "profile_path");
			String url = "https://api.themoviedb.org/3/search/person?api_key={key}&query={query}&popularity={popularity}&profile_path={profile_path}";
			WrapperPersonSearch res = apiRequest.getForObject(url, WrapperPersonSearch.class, params);		
			Person person = null;
			
			if (res.getResults()!=null && res.getResults().size()>0) 
				person = res.getResults().get(0);
			
			return Optional.ofNullable(person);
	    }
}



