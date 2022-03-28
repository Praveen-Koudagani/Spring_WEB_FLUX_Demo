package com.epam.webflux_demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.webflux_demo.dao.BookDao;
import com.epam.webflux_demo.dto.Book;

import reactor.core.publisher.Flux;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {

	@Autowired
	BookService bookService;
	@MockBean
	BookDao bookDao;
	
	
	@Test
	void testLoadAllBooks() {
		 Book book = new Book(1,"book1","pub1","auth1");
	        Book book2 = new Book(2,"book2","pub2","auth2");
	        Flux<Book> flux = Flux.just(book,book2);

	        when(bookDao.getBooks()).thenReturn(flux);
	        assertEquals(flux,bookService.loadAllBooks());
	}

	@Test
	void testSaveBook() {
		 Book book = new Book(1,"book1","pub1","auth1");
	        Book book2 = new Book(2,"book2","pub2","auth2");
	        Flux<Book> flux = Flux.just(book,book2);

	        when(bookDao.saveBook(book)).thenReturn(flux);
	        assertEquals(flux,bookService.saveBook(book));
	}

}
