package com.reactive.app.controller;

import com.reactive.app.model.Book;
import com.reactive.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> createBook(@RequestBody Book book){
       return bookService.createBook(book);
    }

    @GetMapping("/get")
    public Flux<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/byID/{book-id}")
    public Mono<Book> getSingleBookById(@PathVariable("book-id") int bookId){
        return bookService.getSingleBookById(bookId);
    }
    @PutMapping("/update/{bookId}")
    public Mono<Book> updateBook(@RequestBody Book book,@PathVariable int bookId){
        return bookService.updateBook(book,bookId);
    }

    @DeleteMapping("/delete/{bookId}")
    public Mono<Void> delete(@PathVariable int bookId){
        return bookService.delete(bookId);
    }

    @PostMapping("/saveAll")
    public Flux<Book> saveAll(@RequestBody List<Book> bookList){
        return bookService.saveAllBook(bookList);
    }

    @GetMapping("/bookGreaterThanId/{bookId}")
    public Flux<Book> getBooksGreaterThanId(@PathVariable int bookId){
        return bookService.getBooksGreaterThanId(bookId);
    }

    @GetMapping("/findByName/{name}")
    public Flux<Book> findByName(@PathVariable String name){
        return bookService.findByName(name);
    }

    @GetMapping("/findByAuthor/{authorName}")
    public Flux<Book> findByAuthor(@PathVariable("authorName") String author){
        return bookService.findByAuthor(author);
    }

    @GetMapping("/findByPublisher/{publisher}")
    public Flux<Book> findByPublisher(@PathVariable String publisher){
        return bookService.findByPublisher(publisher);
    }
    @GetMapping("/findByNameAndAuthor/{name}/{author}")
    public Flux<Book> findByNameAndAuthor(@PathVariable String name,@PathVariable String author){
        return bookService.findByNameAndAuthor(name,author);
    }

    @GetMapping("/findByDescription/{description}")
    public Flux<Book> findByDescription(@PathVariable String description){
        return bookService.findBookByDescription(description);
    }

    @PostMapping("/createIfNotExists")
    public Mono<String> createIfNotExists(@RequestBody Book book){
        return bookService.createIfNotExists(book);
    }
    @PostMapping("/createIfNotExistById")
    public Mono<String> createIfNotExistById(@RequestBody Book book){
        return bookService.createIfNotExistById(book);
    }
    @DeleteMapping("/deleteByAuthorName/{author}")
    public Mono<String> deleteByAuthorName(@PathVariable String author){
        return bookService.deleteByAuthorName(author);
    }
    @DeleteMapping("/deleteByAuthorNameIfExist/{author}")
    public Mono<String> deleteByAuthorNameIfExist(@PathVariable String author){
        return bookService.deleteByAuthorNameIfExist(author);
    }
}
