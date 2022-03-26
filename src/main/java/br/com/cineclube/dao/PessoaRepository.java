package br.com.cineclube.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cineclube.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	//disponivel: findAll, findById, get, save, delete - métodos basicos de CRUD e persistência.
}
