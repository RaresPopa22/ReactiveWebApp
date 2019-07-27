package com.example.books.handler;

import com.example.books.document.Book;
import com.example.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@RequiredArgsConstructor
public class BookHandler {

  private final BookService service;

  public Mono<ServerResponse> findAll(final ServerRequest request) {
    return ServerResponse.ok().body(service.getAll(), Book.class);
  }

  public Mono<ServerResponse> findById(final ServerRequest request) {
    return service.findById(request.pathVariable("id"))
      .flatMap(book -> ServerResponse.ok().body(fromObject(book)))
      .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> save(final ServerRequest request) {
    return ServerResponse.ok()
      .body(service.save(request.bodyToMono(Book.class)), Book.class);
  }
}
