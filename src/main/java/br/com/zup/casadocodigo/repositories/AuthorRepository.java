package br.com.zup.casadocodigo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.casadocodigo.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
