package com.alpsank.entities;

public class JournalIssue extends Document {
	
	private int volume_no, year, journalID;
	private String journal_title, month, ISSN;
	
	public JournalIssue(){
		super();
		volume_no = year = journalID = 0;
		category = journal_title = month = ISSN = "";
		super.type = "Journal";
	}
	
	public JournalIssue(int volume_no, int year, String journal_title,
	String issue_title, String month, String ISSN,
	String journal_type, int qty, String publisherName){
		super();
		this.journalID = 0;
		this.volume_no = volume_no;
		this.year = year;
		this.setJournal_title(journal_title);
		this.setIssue_title(issue_title);
		super.category = journal_type;
		this.month = month;
		this.ISSN = ISSN;
		super.qty = qty;
		super.publisherName = publisherName;
	}
	
	public JournalIssue(int journalID, int volume_no,
	int year, String ISSN, String issue_title, String month,
	int qty) {
		super();
		this.journalID = journalID;
		this.volume_no = volume_no;
		this.ISSN = ISSN;
		this.year = year;
		this.month = month;
		this.setIssue_title(issue_title);
		super.qty = qty;
	}

	public int getVolume_no() {
		return volume_no;
	}
	public void setVolume_no(int volume_no) {
		this.volume_no = volume_no;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public String getIssue_title() {
		return super.title;
	}
	
	public void setIssue_title(String issue_title) {
		super.title = issue_title;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getISSN() {
		return ISSN;
	}
	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}

	public String getJournal_title() {
		return journal_title;
	}

	public void setJournal_title(String journal_title) {
		this.journal_title = journal_title;
	}

	public int getJournalID() {
		return journalID;
	}

	public void setJournalID(int journalID) {
		this.journalID = journalID;
	}

}
