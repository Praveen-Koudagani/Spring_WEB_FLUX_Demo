package com.epam.webflux_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.webflux_demo.dto.Book;
import com.epam.webflux_demo.service.BookService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Book> getAllBooks(){
		return bookService.loadAllBooks();
	}
	
	@PostMapping("/{id}")
	public Flux<Book> saveBook(@PathVariable int id ){
		Book book=new Book(id,"book"+id,"author"+id,"publisher"+id);
		return bookService.saveBook(book);
	}
	
}
