package com.alpsank.entities;

import java.util.ArrayList;

public class Book extends Document {

		private int year, edition;
		private String ISBN;
		private ArrayList<String> authorNames;

		
		public Book(){
			super();
			year = edition = 0;
			ISBN = "";
			//has to be initialized to "", see DocumentModel.searchBooks
			category = "";
			authorNames = new ArrayList<String>();
			type = "Book";
		}
		
		public void setAuthorNames(String S){
			this.authorNames.add(S);
		}

		public String getAuthorNames(){
			StringBuffer output = new StringBuffer("");
			
			for(String s: authorNames){
				output.append(s + ", ");
			}
			
			output = new StringBuffer(output.substring(0, output.length() - 2));
			
			return output.toString();
		}
		
		public ArrayList<String> getAuthorNamesArray(){
			return authorNames;
		}



		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}
		
		public int getEdition() {
			return edition;
		}
		
		public void setEdition(int edition) {
			this.edition = edition;
		}
	
		public String getISBN() {
			return ISBN;
		}

		public void setISBN(String iSBN) {
			ISBN = iSBN;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}
}
