package com.epam.webflux_demo.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.epam.webflux_demo.dto.Book;
import com.epam.webflux_demo.service.BookService;

import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BookController.class)
@Import(BookService.class)
class BookControllerTest {

	 @Autowired
	    WebTestClient webTestClient;

	    @MockBean
	    private BookService bookService;

	    @Test
	    public void testGetBooks() {
	        Book book = new Book(1,"book1","pub1","auth1");
	        Book book2 = new Book(2,"book2","pub2","auth2");
	        Flux<Book> flux = Flux.just(book,book2);

	        when(bookService.loadAllBooks()).thenReturn(flux);

	       webTestClient.get()
	                .uri("/books")
	                .exchange()
	                .expectStatus().isOk()
	                .expectBodyList(Book.class);
	    }

	    @Test
	    public void testCreateBook() {

	    	Book book = new Book(1,"book1","","");
	        Flux<Book> bookFlux = Flux.just(book);
	        when(bookService.saveBook(book)).thenReturn(bookFlux);

	        webTestClient.post()
	                .uri("/books")
	                .accept(MediaType.APPLICATION_JSON)
	                .bodyValue(book)
	                .exchange()
	                .expectStatus().isOk()
	                .expectBodyList(Book.class);

	    }
	

}
