package com.alpsank.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Borrow {

	Date checkoutDate, returnDate;
	String status;
	String doc_title;
	String doc_type;
	String doc_serialNumber;

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	public String getDoc_title() {
		return doc_title;
	}

	public void setDoc_title(String doc_title) {
		this.doc_title = doc_title;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getDoc_serialNumber() {
		return doc_serialNumber;
	}

	public void setDoc_serialNumber(String doc_serialNumber) {
		this.doc_serialNumber = doc_serialNumber;
	}
	
	/*
	 * Sorting methods
	 */

	public static ArrayList<Borrow> sortByStatus(ArrayList<Borrow> bur) {
		Collections.sort(bur, new StatusComparator());
		return bur;
	}

}

class StatusComparator implements Comparator<Borrow> {

	@Override
	public int compare(Borrow b1, Borrow b2) {
		return b2.status.compareToIgnoreCase(b1.status); // reversed order.
	}
}
