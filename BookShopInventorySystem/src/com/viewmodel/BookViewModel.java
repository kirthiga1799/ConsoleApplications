package com.viewmodel;

import java.util.List;

import com.model.Book;
import com.repository.BookRepository;

public class BookViewModel {
	
	 private BookRepository bookRepository;

	    public BookViewModel(BookRepository bookRepository) {
	        this.bookRepository = bookRepository;
	    }

	    public List<Book> getAllBooks() {
	        return bookRepository.getAllBooks();
	    }

	    public Book getBookById(int id) {
	        return bookRepository.getBookById(id);
	    }

	    public void addBook(Book book) {
	        bookRepository.addBook(book);
	    }

	    public void updateBook(Book book) {
	        bookRepository.updateBook(book);
	    }

	    public void deleteBook(int id) {
	        bookRepository.deleteBook(id);
	    }

}
