package com.alpsank.entities;

import java.util.Iterator;

import org.springframework.util.AutoPopulatingList;

public class DeleteLibrariansForm {
	
	private AutoPopulatingList<String> librarians = new AutoPopulatingList<String>(String.class);

	public AutoPopulatingList<String> getLibrarians() {
		return this.librarians;
	}

	public void setCopies(AutoPopulatingList<String> librarians) {
		this.librarians = librarians;
	}
	
	public void shrinkLibrarians() {
        synchronized(librarians) {
            for (Iterator<String> i = this.librarians.iterator(); i.hasNext();) {
                if (i.next() == null) {
                    i.remove();
                }
            }
        }
    }
}
