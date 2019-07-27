package com.example.books.service;

import com.example.books.document.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
  Flux<Book> getAll();

  Mono<Book> findById(final String id);

  Mono<Book> save(final Mono<Book> book);
}
