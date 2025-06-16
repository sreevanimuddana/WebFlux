package com.reactive.app.service;

import com.reactive.app.model.Book;
import com.reactive.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private R2dbcEntityTemplate template;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Mono<Book> createBook(Book book) {
        return template.insert(Book.class).using(book);
    }

//   return getNextBookId()
//                .map(id -> {
//                 book.setBookId(id);
//                  return book;
//    })
//            .flatMap(newBook -> template.insert(Book.class).using(newBook));

//    public Mono<Integer> getNextBookId() {
//        return bookRepository.findTopByOrderByBookIdDesc()
//                .map(book -> book.getBookId() + 1)
//                .defaultIfEmpty(1); // Start at 1 if table is empty
//    }


//    @Override
//    public Mono<Book> createBook(Book book) {
//       // book.setBook_id(UUID.randomUUID().toString());
//
//        return bookRepository.save(book);
//
//    }


    @Override
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll()
                //.delayElements(Duration.ofSeconds(2))              //delay each ele for 2sec
                .log()
                .map(book->{book.setName(book.getName().toUpperCase());
                 return book;
                });
    }

    @Override
    public Mono<Book> getSingleBookById(int bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Mono<Book> updateBook(Book book, int bookId) {
        return bookRepository.findById(bookId)
                .flatMap(existingBook -> {
                    existingBook.setName(book.getName());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setPublisher(book.getPublisher());
                    existingBook.setDescription(book.getDescription());
                    return bookRepository.save(existingBook);
                });
    }


    @Override
    public Mono<Void> delete(int bookId) {
        return bookRepository.deleteById(bookId);

                 //or using flatmap

//           return bookRepository
//           .findById(book_id)
//            .flatMap(book -> bookRepository.delete(book));
    }

    @Override
    public Flux<Book> saveAllBook(List<Book> bookList) {
        return Flux.fromIterable(bookList)
                .flatMap(book->template.insert(Book.class).using(book));
    }

    @Override
    public Flux<Book> getBooksGreaterThanId(int bookId) {
        return getAllBooks().filter(books->books.getBookId()>bookId);
    }

    @Override
    public Flux<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Flux<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public Flux<Book> findByNameAndAuthor(String name, String author) {
        return bookRepository.findByNameAndAuthor(name,author);
    }

    @Override
    public Flux<Book> findByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }

    @Override
    public Flux<Book> findBookByDescription(String description) {
        return bookRepository.findByDescription("%" + description + "%");
    }

    @Override
    public Mono<String> createIfNotExists(Book book) {
        return bookRepository.existsByName(book.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just("Book already exists");
                    } else {
                        System.out.println("Creating book with ID: " + book.getBookId());

                       return template.insert(Book.class)
                                .using(book)
                                .thenReturn("Created successfully");
                    }
                });

}

    @Override
    public Mono<String> createIfNotExistById(Book book) {
        return bookRepository.existsById(book.getBookId())
                .flatMap(exists->{
                        if(exists){
                            return Mono.just("Id already exist");
                        }else{
                            return template.insert(Book.class).using(book)
                                    .thenReturn("created successfully");
                        }
                });

    }

    @Override
    public Mono<String> deleteByAuthorName(String author) {
        return bookRepository.deleteByAuthorName(author)
                .thenReturn("Deleted successfully.....");
    }

    @Override
    public Mono<String> deleteByAuthorNameIfExist(String author) {
        return bookRepository.existsByAuthor(author)
                .flatMap(exists ->
                {
                    if (!exists) {
                        return Mono.just("Record not found...!");
                    } else {
                        return bookRepository.deleteByAuthorName(author)
                                .thenReturn("Deleted successfully.....");
                    }
                });
    }
}
