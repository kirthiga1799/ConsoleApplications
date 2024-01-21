package com.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.Book;
import com.viewmodel.BookViewModel;

public class BookView {
	private BookViewModel bookViewModel;
	private Scanner scanner;

	public BookView(BookViewModel bookViewModel) {
		this.bookViewModel = bookViewModel;
		this.scanner = new Scanner(System.in);
	}

	public void run() {
		System.out.print("Enter admin username: ");
		String username = scanner.nextLine();
		System.out.print("Enter admin password: ");
		String password = scanner.nextLine();
		System.out.println();

		if (authenticateAdmin(username, password)) {
			int choice;

			do {
				System.out.println("BookShop Inventory Management System\n");

				System.out.println("1. Add New Book");
				System.out.println("2. View All Books");
				System.out.println("3. Update Book");
				System.out.println("4. Delete Book");
				System.out.println("5. Search Book");
				System.out.println("6. Exit");
				System.out.print("\nEnter your choice: ");

				choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					addNewBook();
					break;
				case 2:
					displayAllBooks();
					break;
				case 3:
					updateBook();
					break;
				case 4:
					deleteBook();
					break;
				case 5:
					searchBooks();
					break;
				case 6:
					System.out.println("Exiting the program. Goodbye!");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}

			} while (choice != 6);
		} else {
			System.out.println("Authentication failed. Exiting the program.");
		}

		scanner.close();
	}

	private boolean authenticateAdmin(String username, String password) {
		return username.equals("admin") && password.equals("admin123");
	}

	private void displayAllBooks() {
		List<Book> books = bookViewModel.getAllBooks();
		System.out.println("\nList of Books:");
		if (books.isEmpty()) {
			System.out.println("No books found.");
		} else {
			for (Book book : books) {
				System.out.println(book.getId() + ". " + book.getTitle() + " by " + book.getAuthor() + " - Quantity: "
						+ book.getQuantity());
			}
		}
		System.out.println();
	}

	private void addNewBook() {
		System.out.println("\nAdd New Book In Inventory Shop");
		System.out.print("Enter book title: ");
		String title = scanner.nextLine();
		System.out.print("Enter book author: ");
		String author = scanner.nextLine();
		System.out.print("Enter quantity: ");
		int quantity = scanner.nextInt();

		// Assign a unique ID based on the current size of the book list
		int newBookId = bookViewModel.getAllBooks().size() + 1;

		Book newBook = new Book(newBookId, title, author, quantity);
		bookViewModel.addBook(newBook);
		System.out.println("Book added successfully.");
		System.out.println();
	}

	private void updateBook() {
		System.out.print("\nEnter book ID to update: ");
		int id = scanner.nextInt();

		Book existingBook = bookViewModel.getBookById(id);
		if (existingBook != null) {
			System.out.print("Enter new quantity: ");
			int newQuantity = scanner.nextInt();

			existingBook.setQuantity(newQuantity);
			bookViewModel.updateBook(existingBook);
			System.out.println("Book updated successfully.");
		} else {
			System.out.println("Book not found with ID: " + id);
		}

		System.out.println();
	}

	private void deleteBook() {
		System.out.print("\nEnter book ID to delete: ");
		int id = scanner.nextInt();

		Book existingBook = bookViewModel.getBookById(id);
		if (existingBook != null) {
			bookViewModel.deleteBook(id);
			System.out.println("Book deleted successfully.");
		} else {
			System.out.println("Book not found with ID: " + id);
		}
		System.out.println();
	}

	private void searchBooks() {
		System.out.print("\nEnter search keyword (title or author): ");
		String keyword = scanner.nextLine().toLowerCase();

		List<Book> matchingBooks = new ArrayList<>();

		for (Book book : bookViewModel.getAllBooks()) {
			if (book.getTitle().toLowerCase().contains(keyword) || book.getAuthor().toLowerCase().contains(keyword)) {
				matchingBooks.add(book);
			}
		}

		if (!matchingBooks.isEmpty()) {
			System.out.println("Search Results:");
			for (Book book : matchingBooks) {
				System.out.println(book.getId() + ". " + book.getTitle() + " by " + book.getAuthor() + " - Quantity: "
						+ book.getQuantity());
			}
		} else {
			System.out.println("No matching books found.");
		}
		System.out.println();
	}

}
