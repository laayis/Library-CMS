package com.alpsank.entities;

import java.util.Iterator;

import org.springframework.util.AutoPopulatingList;

public class CheckBooksForm {
	
	private String userid;
	private AutoPopulatingList<PhysicalDocumentCopy> copies =
			new AutoPopulatingList<PhysicalDocumentCopy>(PhysicalDocumentCopy.class);

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public AutoPopulatingList<PhysicalDocumentCopy> getCopies() {
		return this.copies;
	}

	public void setCopies(AutoPopulatingList<PhysicalDocumentCopy> copies) {
		this.copies = copies;
	}
	
	public void shrinkCopies() {
        synchronized(copies) {
            for (Iterator<PhysicalDocumentCopy> i = this.copies.iterator(); i.hasNext();) {
                if (i.next() == null) {
                    i.remove();
                }
            }
        }
    }
	
} 
