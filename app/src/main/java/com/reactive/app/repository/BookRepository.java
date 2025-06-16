package com.reactive.app.repository;

import com.reactive.app.model.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends ReactiveCrudRepository<Book,Integer> {
    Mono<Book> findTopByOrderByBookIdDesc();

    Flux<Book> findByName(String name);

    Flux<Book> findByAuthor(String author);

    Flux<Book> findByNameAndAuthor(String name, String author);

    Flux<Book> findByPublisher(String publisher);


    @Query("select *from books where description LIKE  :description")
    public Flux<Book> findByDescription( String description);

    Mono<Boolean> existsByName(String name);

    @Query("delete from books where author=:author")
    public Mono<String> deleteByAuthorName(String author);

    Mono<Boolean> existsByAuthor(String author);


    //public Mono<String> createIfNotExists(Book book);


    // Flux<Book> saveAll(List<Book> book);

    // Optional<Object> findTopByOrderByBookIdDesc();
}
