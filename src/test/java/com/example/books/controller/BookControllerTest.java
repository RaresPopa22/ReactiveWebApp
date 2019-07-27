package com.example.books.controller;

import com.example.books.document.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebFluxTest({BookController.class, BookService.class})
public class BookControllerTest {

  @Autowired
  private WebTestClient client;

  @MockBean
  private BookRepository repository;

  private Book book;

  @Before
  public void init() {
    book = new Book("BAC-000-ID", "Slow thinking, fast thinking");
    Mockito.when(repository.findAll()).thenReturn(Flux.fromIterable(data()));
    Mockito.when(repository.findById(book.getId())).thenReturn(Mono.just(book));
    Mockito.when(repository.save(book)).thenReturn(Mono.just(book));
  }

  // "12 Rules of Life", "Crime and Punishment", "The subtle art of not giving a f*ck"
  private List<Book> data() {
    return List.of(
      new Book(null, "12 Rules of Life"),
      new Book(null, "Crime and Punishment"),
      new Book(null, "The subtle art of not giving a f*ck")
    );
  }

  @Test
  public void getAll() {
    client.get().uri("/books").exchange()
      .expectStatus().isOk()
      .expectBodyList(Book.class)
      .hasSize(3);
  }

  @Test
  public void findById() {
    client.get().uri("/books/{id}", book.getId()).exchange()
      .expectStatus().isOk()
      .expectBody(Book.class).isEqualTo(book);
  }

  @Test
  public void save() {
    client.post().uri("/books")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .body(Mono.just(book), Book.class)
      .exchange()
      .expectStatus().isOk()
      .expectBody(Book.class).isEqualTo(book);
  }
}