package com.alpsank.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Document {

	int qty, doc_id;
	protected String title, publisherName, category, type;

	public Document() {
		qty = doc_id = 0;
		title = publisherName = "";
		category = "z";
	}

	public String getCategory() {
		return category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	/*
	 * Sorting methods
	 */

	public static ArrayList<Document> sortByTitle(ArrayList<Document> docs) {
		Collections.sort(docs, new TitleComparable());
		return docs;
	}

	public static ArrayList<Document> sortByPublisher(ArrayList<Document> docs) {
		Collections.sort(docs, new publisherComparable());
		return docs;
	}

	public static ArrayList<Document> sortByCategory(ArrayList<Document> docs) {
		Collections.sort(docs, new CategoryComparable());
		return docs;
	}

	public static ArrayList<Document> sortByQuantity(ArrayList<Document> docs) {
		Collections.sort(docs, new QuantityComparable());
		return docs;
	}

	public static ArrayList<Document> sortByType(ArrayList<Document> docs) {
		Collections.sort(docs, new TypeComparable());
		return docs;
	}

}

class TitleComparable implements Comparator<Document> {

	@Override
	public int compare(Document d1, Document d2) {
		return d1.title.compareToIgnoreCase(d2.title);
	}
}

class publisherComparable implements Comparator<Document> {

	@Override
	public int compare(Document d1, Document d2) {
		return d1.publisherName.compareToIgnoreCase(d2.publisherName);
	}
}

class CategoryComparable implements Comparator<Document> {

	@Override
	public int compare(Document d1, Document d2) {
		return d1.category.compareToIgnoreCase(d2.category);
	}
}

class QuantityComparable implements Comparator<Document> {

	@Override
	public int compare(Document d1, Document d2) {
		return (d1.qty > d2.qty ? -1 : (d1.qty == d2.qty ? 0 : 1));
	}
}

class TypeComparable implements Comparator<Document> {

	@Override
	public int compare(Document d1, Document d2) {
		return d1.type.compareToIgnoreCase(d2.type);
	}
}
