package br.com.zup.casadocodigo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.casadocodigo.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>  {

}
 