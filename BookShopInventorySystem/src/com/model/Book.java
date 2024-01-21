package com.model;

public class Book {
	 private int id;
	    private String title;
	    private String author;
	    private int quantity;

	    public Book(int id, String title, String author, int quantity) {
	        this.id = id;
	        this.title = title;
	        this.author = author;
	        this.quantity = quantity;
	    }

	    // Getters and setters
	    public int getId() {
	        return id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getAuthor() {
	        return author;
	    }

	    public void setAuthor(String author) {
	        this.author = author;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

}
