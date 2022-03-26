package br.com.cineclube.model;

import java.util.List;

public class WrapperPersonSearch {
	
	// esse results vem da requisição da API TMDB.
    private List<Person> results;

    public List<Person> getResults() {

		return results;
    }
    
    public void setResults(List<Person> results) {
        this.results = results;
    }
}
