package com.epam.webflux_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	int id;
	String bookName;
	String authorName;
	String publisherName;
	
}
