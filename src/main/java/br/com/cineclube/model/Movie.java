package br.com.cineclube.model;

import java.math.BigDecimal;

public class Movie {

		
		private Long id;
		private String title;
		private String overview;
		private BigDecimal vote_average;
		private String poster_path;
		
		public String getPoster_path() {
			return poster_path;
		}
		public void setPoster_path(String poster_path) {
			this.poster_path = poster_path;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getOverview() {
			return overview;
		}
		public void setOverview(String overview) {
			this.overview = overview;
		}
		public BigDecimal getVote_average() {
			return vote_average;
		}
		public void setVote_average(BigDecimal vote_average) {
			this.vote_average = vote_average;
		}
		
		// gerar getters e setters ...
		
	
}
