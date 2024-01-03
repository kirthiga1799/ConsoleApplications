package com.contactController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.contactModel.Contact;
import com.contactView.ContactView;
import com.contact.repository.repository;

public class ContactController {
	private ContactView view;
    private repository repository;

    public ContactController(ContactView view, repository repository) {
        this.view = view;
        this.repository = repository;
    }

	public void run() {
		int choice;
		do {
			choice = view.displayMenu();

			switch (choice) {
			case 1:
				addContact();
				break;
			case 2:
				displayContacts();
				break;
			case 3:
				searchContact();
				break;
			case 4:
				editContact();
				break;
			case 5:
				deleteContact();
				break;
				
			case 6:
                view.displaySortedContacts(repository.sortContactsAscending());
                break;
			case 7:
				view.displayMessage("\nGoodbye!\n");
				break;
			default:
				view.displayMessage("\nInvalid choice. Please try again.\n");
				break;
			}
		} while (choice != 7);
	}
	 public void addContact() {
	        Contact contact = view.getContactInfo();
	        repository.addContact(contact);
	        view.displayMessage("\nContact added successfully.\n");
	    }

	    public void displayContacts() {
	        view.displayContacts(repository.getAllContacts());
	    }

	    public void searchContact() {
	        String searchName = view.getSearchName();
	        view.displayContacts(repository.searchContactsByName(searchName));
	    }

	    public void editContact() {
	        Contact editContact = view.getEditInfo();
	        if (repository.editContact(editContact)) {
	            view.displayMessage("\nContact edited successfully.\n");
	        } else {
	            view.displayMessage("\nContact not found. Please try again.\n");
	        }
	    }

	    public void deleteContact() {
	        String deleteName = view.getDeleteName();
	        if (repository.deleteContact(deleteName)) {
	            view.displayMessage("\nContact deleted successfully.\n");
	        } else {
	            view.displayMessage("\nContact not found. Please try again.\n");
	        }
	    }
	    
}

