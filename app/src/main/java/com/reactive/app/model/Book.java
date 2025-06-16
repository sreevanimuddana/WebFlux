package com.reactive.app.model;

import io.r2dbc.spi.Parameter;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "books")
public class Book {
    @Id
    @Column("book_id")
    private int bookId;
    private String name;
    private String description;
    private String author;
    private String publisher;
}
