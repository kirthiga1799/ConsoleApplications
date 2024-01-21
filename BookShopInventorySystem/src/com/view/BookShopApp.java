package com.view;

import java.util.ArrayList;
import java.util.List;

import com.model.Book;
import com.repository.BookRepository;
import com.viewmodel.BookViewModel;

public class BookShopApp {
	
	  public static void main(String[] args) {
	        List<Book> initialBooks = new ArrayList<>();
	        BookRepository bookRepository = new BookRepository();
	        BookViewModel bookViewModel = new BookViewModel(bookRepository);
	        BookView bookView = new BookView(bookViewModel);

	        bookView.run();
	    }

}
