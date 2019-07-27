package com.example.books.service.impl;

import com.example.books.document.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  @Override
  public Flux<Book> getAll() {
    return repository.findAll();
  }

  @Override
  public Mono<Book> findById(final String id) {
    return repository.findById(id);
  }

  @Override
  public Mono<Book> save(final Mono<Book> book) {
    return book.flatMap(repository::save);
  }
}
