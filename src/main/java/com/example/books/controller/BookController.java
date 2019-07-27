package com.example.books.controller;

import com.example.books.document.Book;
import com.example.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService service;

  @GetMapping
  public Flux<Book> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Mono<Book> findById(@PathVariable final String id) {
    return service.findById(id);
  }

  @PostMapping
  public Mono<Book> save(@RequestBody final Mono<Book> book) {
    return service.save(book);
  }
}
