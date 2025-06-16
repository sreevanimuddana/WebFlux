package com.reactive.app;


import com.reactive.app.model.Book;
import com.reactive.app.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class RepositoryTest {

//    @Autowired
//    private BookRepository bookRepository;

    @Test
    void methodTest(){
//        Flux<Book> f=bookRepository.findByName(" mariana");
//        StepVerifier.create(f)
//                .expectNextCount(2)
//                .verifyComplete();
//
//        bookRepository.findByAuthor("anusha")
//                .as(StepVerifier::create)
//                        .expectNextCount(1)
//                .verifyComplete();
    }
}
