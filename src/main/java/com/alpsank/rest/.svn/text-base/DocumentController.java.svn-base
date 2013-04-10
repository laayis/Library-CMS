package com.alpsank.rest;

import java.util.ArrayList;

import com.alpsank.entities.Book;
import com.alpsank.entities.Borrow;
import com.alpsank.entities.JournalIssue;
import com.alpsank.entities.PhysicalDocumentCopy;
import com.alpsank.entities.User;
import com.alpsank.entities.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alpsank.model.DocumentModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DocumentController {
	
    @RequestMapping(method = RequestMethod.GET, value = "catalogue")
    public String displayItems(Model model, HttpSession session, HttpServletRequest request) {
    	
    	try
    	{
    		ArrayList<Book> books = DocumentModel.listBooks();
    		ArrayList<JournalIssue> journals = DocumentModel.listJournalIssues();
    		ArrayList<Document> docs = DocumentModel.merge(books, journals);
    		docs = DocumentModel.sort(docs, request.getParameter("sort"));
    		
    		model.addAttribute("documents", docs);	
            User curUser = (User) session.getAttribute("user");
            if (curUser != null){
                model.addAttribute("logged_user", curUser);
            }
            return "catalogue";
    	}
    	catch( Exception e )
    	{
    		e.printStackTrace();
    		model.addAttribute("message", "DB connection failed. Please try again later!");
    		return "home";
    	}
    	
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "view_borrowed")
    public String displayBorrowed(Model model, HttpSession session, HttpServletRequest request) {
    	
    	User curUser = (User) session.getAttribute("user");
    	try
    	{	
            if (curUser != null && curUser.getRole().equals("Library User")){
            	ArrayList<Borrow> borrowedItems = DocumentModel.viewBorrowedItems(curUser.getUsername());    		
        		model.addAttribute("borrowed", borrowedItems);
                model.addAttribute("logged_user", curUser);
                return "view_borrowed";
            }
            else {
            	return "home";
            }
    	}
    	catch( Exception e )
    	{
    		e.printStackTrace();
    		model.addAttribute("message", "DB connection failed. Please try again later!");
    		return "home";
    	}
    	
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "book/{bookid}")
    public String displayBook(Model model, HttpSession session, @PathVariable("bookid") String bookid, @RequestParam(value = "add",required = false) String addStatus) {
    	boolean book_dexists = true;
    	ArrayList<Book> books;
    	User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        
		try {
			books = DocumentModel.listBooks();
		
	    	ArrayList<PhysicalDocumentCopy> docCopies = new ArrayList<PhysicalDocumentCopy>();
	    	for(Book book : books){
	    		if(book.getISBN().equals(bookid))
	    		{	
	    			docCopies = DocumentModel.getDocumentCopies(book.getDoc_id());
	    				
	    			model.addAttribute("display_book",book);
	    			model.addAttribute("docCopies",docCopies);
	    			book_dexists = false;
	    		}
	    	}
	    	
    	} catch (Exception e) {
			model.addAttribute("message", "DB connection failed. Please try again later!");
    		return "home";
		}
		if(addStatus!=null)
		{
			if(addStatus.equals("success"))
				model.addAttribute("message", "A new copy of the book was added!");
			else if(addStatus.equals("fail"))
				model.addAttribute("error", "Could not add new copy!");
            else if(addStatus.equals("rsuccess"))
                model.addAttribute("message", "The copy was removed.");
            else if(addStatus.equals("rfail"))
                model.addAttribute("error", "Could not remove copy!");
		}
    	model.addAttribute("book_dexists",book_dexists);  
    	return "view_book";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "issue/{issueid}")
    public String displayIssue(Model model, HttpSession session, @PathVariable("issueid") String issueid,
    @RequestParam(value = "add",required = false) String addStatus) {
    	boolean issue_dexists = true;
    	ArrayList<JournalIssue> issues;
    	User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        
		try {
			issues = DocumentModel.listJournalIssues();
		
	    	ArrayList<PhysicalDocumentCopy> docCopies = new ArrayList<PhysicalDocumentCopy>();
	    	for(JournalIssue issue : issues){
	    		if(issue.getISSN().equals(issueid))
	    		{	
	    			docCopies = DocumentModel.getDocumentCopies(issue.getDoc_id());
	    				
	    			model.addAttribute("display_issue",issue);
	    			model.addAttribute("docCopies",docCopies);
	    			issue_dexists = false;
	    		}
	    	}
	    	
    	} catch (Exception e) {
			model.addAttribute("message", "DB connection failed. Please try again later!");
    		return "home";
		}
		if(addStatus!=null)
		{
			if(addStatus.equals("success"))
				model.addAttribute("message", "A new copy of the journal issue was added!");
			else if(addStatus.equals("fail"))
				model.addAttribute("error", "Could not add new copy!");
            else if(addStatus.equals("rsuccess"))
                model.addAttribute("message", "The copy was removed.");
            else if(addStatus.equals("rfail"))
                model.addAttribute("error", "Could not remove copy!");
		}
    	model.addAttribute("book_dexists",issue_dexists);  
    	return "view_issue";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "book/{bookid}/add")
    public String addBook(Model model, HttpSession session, @PathVariable("bookid") String bookid) {
    	
    	User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        else
            return "redirect:/";
        //verify proper user is asking to add book
        
        System.out.println(curUser.getRole()+ "Lib?"+ curUser.getRole().equals("Librarian")+ "Admin?"+curUser.getRole().equals("Admin"));
        if(!curUser.getRole().equals("Librarian") && !curUser.getRole().equals("Admin"))
            return "redirect:/";
        
		try{
		    if(DocumentModel.addCopy(bookid, 1))
		    		model.addAttribute("add","success");
		    else
		    		model.addAttribute("add","fail");
	    	
    	} catch (Exception e) {
			model.addAttribute("add", "fail");
            return "redirect:/book/"+bookid;
		}
		
    	return "redirect:/book/"+bookid;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "issue/{issueid}/add")
    public String addIssue(Model model, HttpSession session, @PathVariable("issueid") String issueid) {
    	
    	User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        else
            return "redirect:/";
        //verify proper user is asking to add issue
        
       
        if(!curUser.getRole().equals("Librarian") && !curUser.getRole().equals("Admin"))
            return "redirect:/";
        
		try{
			if (DocumentModel.addCopy(issueid, 1)){
		    		model.addAttribute("add","success");
		    }
		    else {
		    		model.addAttribute("add","fail");
		    }    	
    	} catch (Exception e) {
			model.addAttribute("add", "fail");
            return "redirect:/issue/"+issueid;
		}
		
    	return "redirect:/issue/"+issueid;
    }
    
    //This should work as far as I know... the model is currently failing
    @RequestMapping(method = RequestMethod.GET, value = "/book/{bookid}/{copyno}/delete")
    public String deleteBook(Model model, HttpSession session, @PathVariable("bookid") String bookid, @PathVariable("copyno") String copyno) {

        User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        else
            return "redirect:/";
        //verify proper user is asking to add book

        System.out.println(curUser.getRole()+ "Lib?"+ curUser.getRole().equals("Librarian")+ "Admin?"+curUser.getRole().equals("Admin"));
        if(!curUser.getRole().equals("Librarian") && !curUser.getRole().equals("Admin"))
            return "redirect:/";

        try{
            if(DocumentModel.removeCopy(bookid, Integer.parseInt(copyno)))
                model.addAttribute("add","rsuccess");
            else
                model.addAttribute("add","rfail");

        } catch (Exception e) {
            model.addAttribute("add", "fail");
            return "redirect:/book/"+bookid;
        }

        return "redirect:/book/"+bookid;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/issue/{issueid}/{copyno}/delete")
    public String deleteIssue(Model model, HttpSession session, @PathVariable("issueid") String issueid,
    @PathVariable("copyno") String copyno) {

        User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        else
            return "redirect:/";
        //verify proper user is asking to add book
        if(!curUser.getRole().equals("Librarian") && !curUser.getRole().equals("Admin"))
            return "redirect:/";

        try{
            if(DocumentModel.removeCopy(issueid, Integer.parseInt(copyno)))
                model.addAttribute("add","rsuccess");
            else
                model.addAttribute("add","rfail");

        } catch (Exception e) {
            model.addAttribute("add", "fail");
            return "redirect:/issue/"+issueid;
        }

        return "redirect:/issue/"+issueid;
    }
}
