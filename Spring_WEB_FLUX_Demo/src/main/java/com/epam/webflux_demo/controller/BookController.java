package com.epam.webflux_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.webflux_demo.dto.Book;
import com.epam.webflux_demo.service.BookService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Book> getAllBooks(){
		return bookService.loadAllBooks();
	}
	
	@PostMapping
	public Flux<Book> saveBook(@RequestBody Book book){
		
		log.info(book.getBookName());
		return bookService.saveBook(book);
	}
	
}
