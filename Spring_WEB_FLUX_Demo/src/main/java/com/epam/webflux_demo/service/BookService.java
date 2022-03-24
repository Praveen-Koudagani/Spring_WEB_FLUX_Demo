package com.epam.webflux_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.webflux_demo.dao.BookDao;
import com.epam.webflux_demo.dto.Book;

import reactor.core.publisher.Flux;
@Service
public class BookService {

	@Autowired
	BookDao bookDao;
	
	public Flux<Book> loadAllBooks() {
		Flux<Book> books=bookDao.getBooks();
		return books;
	}

	public Flux<Book> saveBook(Book book) {
		
		return bookDao.saveBook(book);
	}

}
