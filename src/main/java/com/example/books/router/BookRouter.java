package com.example.books.router;

import com.example.books.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class BookRouter {

  @Bean
  public RouterFunction<ServerResponse> route(final BookHandler handler) {
    return RouterFunctions.route(GET("/functional/books"), handler::findAll)
      .andRoute(GET("/functional/books/{id}"), handler::findById)
      .andRoute(POST("/functional/books"), handler::save);
  }
}
