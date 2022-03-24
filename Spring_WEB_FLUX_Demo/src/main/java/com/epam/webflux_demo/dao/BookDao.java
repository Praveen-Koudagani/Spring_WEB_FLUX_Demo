package com.epam.webflux_demo.dao;

import org.springframework.stereotype.Component;

import com.epam.webflux_demo.dto.Book;

import reactor.core.publisher.Flux;
@Component
public class BookDao {
	Flux<Book> book2=Flux.empty();
	public Flux<Book> getBooks() {
		return this.book2;
	}
	public Flux<Book> saveBook(Book book){
		Flux<Book> book1=Flux.just(book);
		book2=Flux.concat(book2,book1);
		return this.book2;
	}

}
