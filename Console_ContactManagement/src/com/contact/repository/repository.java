package com.contact.repository;

import com.contactModel.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class repository {
	private static ArrayList<Contact> contacts;

	
	    public repository() {
	        this.contacts = new ArrayList<>();
	    }

	    public void addContact(Contact contact) {
	        this.contacts.add(contact);
	    }
	public static ArrayList<Contact> getAllContacts() {
		return new ArrayList<>(contacts);
	}

	public static ArrayList<Contact> searchContactsByName(String name) {
		ArrayList<Contact> searchResults = new ArrayList<>();
		for (Contact contact : contacts) {
			if (contact.getName().equalsIgnoreCase(name)) {
				searchResults.add(contact);
			}
		}
		return searchResults;
	}

	public static boolean editContact(Contact editedContact) {
		for (Contact contact : contacts) {
			if (contact.getName().equalsIgnoreCase(editedContact.getName())) {
				contact.setPhoneNumber(editedContact.getPhoneNumber());
				contact.setEmail(editedContact.getEmail());
				return true;
			}
		}
		return false;
	}

	public static boolean deleteContact(String name) {
		for (Contact contact : contacts) {
			if (contact.getName().equalsIgnoreCase(name)) {
				contacts.remove(contact);
				return true;
			}
		}
		return false;
	}
	
	 public List<Contact> sortContactsAscending() {
	        // Implement sorting logic
	        Collections.sort(contacts, Comparator.comparing(Contact::getName));
	        return new ArrayList<>(contacts);
	    }
}
