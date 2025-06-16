package com.reactive.app.service;

import com.reactive.app.model.Book;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface BookService {
    public Mono<Book> createBook(Book book);

    public Flux<Book> getAllBooks();

    public Mono<Book> getSingleBookById(int  bookId);

    public Mono<Book> updateBook(Book book, int bookId);

    public Mono<Void> delete(int bookId);

    public Flux<Book> saveAllBook(List<Book> bookList);

    public Flux<Book> getBooksGreaterThanId(int bookId);

    public Flux<Book> findByName(String name);

    public Flux<Book> findByAuthor(String author);

    public Flux<Book> findByNameAndAuthor(String name,String author);

    public Flux<Book> findByPublisher(String publisher);

    public Flux<Book> findBookByDescription(String description);

    public Mono<String> createIfNotExists(Book book);

    public Mono<String> createIfNotExistById(Book book);

    public Mono<String> deleteByAuthorName(String author);
    public Mono<String> deleteByAuthorNameIfExist(String author);
}
