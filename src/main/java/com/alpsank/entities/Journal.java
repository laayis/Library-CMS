package com.alpsank.entities;

public class Journal {
	
	private String journalTitle, journalCategory;
	private int journalID, publisherID;;
	
	
	public Journal(){
		this.journalCategory = "";
		this.journalID = 0;
		this.journalTitle = "";
		this.publisherID = 0;
	}
	
	public Journal(int journalID, String journalTitle, 
			String journalCategory, int publisherName){
		this.journalCategory = journalCategory;
		this.journalID = journalID;
		this.journalTitle = journalTitle;	
		this.publisherID = publisherName;
	}
	
	public String getJournalTitle() {
		return journalTitle;
	}
	
	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}
	
	public String getJournalCategory() {
		return journalCategory;
	}
	
	public void setJournalCategory(String journalCategory) {
		this.journalCategory = journalCategory;
	}

	public int getJournalID() {
		return journalID;
	}

	public void setJournalID(int journalID) {
		this.journalID = journalID;
	}

	public int getPublisherName() {
		return publisherID;
	}

	public void setPublisherName(int publisherName) {
		this.publisherID = publisherName;
	}
}
