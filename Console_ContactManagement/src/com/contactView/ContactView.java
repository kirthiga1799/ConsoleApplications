package com.contactView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.contactModel.Contact;
import com.contact.repository.repository;
import com.contactController.ContactController;

public class ContactView {

	Scanner scanner = new Scanner(System.in);

	public int displayMenu() {
		System.out.println("Contact Management Console Application\n");
		System.out.println("1. Add a contact");
		System.out.println("2. Display all contacts");
		System.out.println("3. Search for a contact");
		System.out.println("4. Edit a contact");
		System.out.println("5. Delete a contact");
		System.out.println("6. Ascending Order");
		System.out.println("7. Exit");

		System.out.print("\nEnter your choice (1-7): ");
		return scanner.nextInt();
	}

	public Contact getContactInfo() {
		scanner.nextLine();
		System.out.print("Enter the contact name: ");
		String name = scanner.nextLine();

		System.out.print("Enter the contact phone number: ");
		String phoneNumber = scanner.nextLine();

		System.out.print("Enter the contact email address: ");
		String email = scanner.nextLine();

		return new Contact(name, phoneNumber, email);
	}

	public void displayContacts(ArrayList<Contact> contacts) {
		if (contacts.isEmpty()) {
			System.out.println("\nNo contacts found.\n");
		} else {
			System.out.println("\nList of contacts:\n");
			for (Contact contact : contacts) {
				System.out.println(contact);
			}
			System.out.println();
		}
	}

	public String getSearchName() {
		scanner.nextLine();
		System.out.print("\nEnter the contact name to search: ");
		return scanner.nextLine();
	}

	public String getDeleteName() {
		scanner.nextLine();
		System.out.print("\nEnter the contact name to delete: ");
		return scanner.nextLine();
	}

	public Contact getEditInfo() {
		scanner.nextLine();
		System.out.print("\nEnter the contact name to edit: ");
		String name = scanner.nextLine();

		System.out.print("Enter the new phone number: ");
		String phoneNumber = scanner.nextLine();

		System.out.print("Enter the new email address: ");
		String email = scanner.nextLine();

		return new Contact(name, phoneNumber, email);
	}

	public void displaySortedContacts(List<Contact> list) {
		if (list.isEmpty()) {
			System.out.println("\nNo contacts found.\n");
		} else {
			System.out.println("\nList of contacts sorted in ascending order:\n");
			for (Contact contact : list) {
				System.out.println(contact);
			}
			System.out.println();
		}
	}

	public void displayMessage(String message) {
		System.out.println(message);
	}

	public static void main(String[] args) {
		ContactView view = new ContactView();
		repository repository = new repository();
		ContactController controller = new ContactController(view, repository);

		controller.run();
	}

}
