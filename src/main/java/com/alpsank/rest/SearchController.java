package com.alpsank.rest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alpsank.entities.Book;
import com.alpsank.entities.Document;
import com.alpsank.entities.JournalIssue;
import com.alpsank.entities.User;
import com.alpsank.model.DocumentModel;
import com.alpsank.model.UserModel;

@Controller
public class SearchController {
	
	@RequestMapping("/search")
	public String search(Model model, HttpSession session,HttpServletRequest request, @RequestParam("query") String query) {

		Book book = new Book();
		JournalIssue journal = new JournalIssue();
		journal.setTitle(query);
		book.setTitle(query);
		try {
			User curUser = (User) session.getAttribute("user");
            if(curUser != null){
                model.addAttribute("logged_user", curUser);
            }
			ArrayList<Book> resultBooks = DocumentModel.searchBooks(book);
			ArrayList<JournalIssue> resultJournals = DocumentModel.searchJournalIssues(journal);
			ArrayList<Document> resultDocs = DocumentModel.merge(resultBooks, resultJournals);
			resultDocs = DocumentModel.sort(resultDocs, request.getParameter("sort"));
			model.addAttribute("documents",resultDocs);
			model.addAttribute("query",query);
			return "search";
		} catch (Exception e) {
			model.addAttribute("message","Could not connect to DB. Please try again later.");
			return "search";
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "advancedSearch")
	public String searchAdvanced(Model model, HttpSession session) {

		
		try {
			if(!this.prepareSearchPage(model, session))
            	return "advancedSearch";
		} catch (Exception e) {	
			model.addAttribute("message","Could not connect to DB. Please try again later.");		
		}
		
		return "advancedSearch";
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/advancedSearch/books")
	public String searchAdvanced(Model model, HttpSession session,  @RequestParam("title") String title,
			@RequestParam("authorNames") String author,
			@RequestParam("ISBN") String ISBN,
			@RequestParam("publisherName") String publisher ,
			@RequestParam("year") String year,
			@RequestParam("edition") String edition,
			@RequestParam("BookCategories") String bookCategory) {
		model.addAttribute("search","books");
		Book book = new Book();
		session.setAttribute("searchBook", null);
		if(!title.isEmpty()){
			book.setTitle(title);
		}
		if(!author.isEmpty()){
			book.setAuthorNames(author);
		}
		if(!edition.isEmpty()){
			book.setEdition(Integer.parseInt(edition));
		}
		if(!year.isEmpty()){
			book.setYear(Integer.parseInt(year));
		}
		if(!bookCategory.equalsIgnoreCase("empty")){
			book.setCategory(bookCategory);
		}
		try {
			
            if(!this.prepareSearchPage(model, session))
            	return "advancedSearch";
			
			ArrayList<Book> resultBooks = DocumentModel.searchBooks(book);
			session.setAttribute("searchBook", book);
			model.addAttribute("documents",resultBooks);
			
			return "advancedSearch";
		} catch (Exception e) {
			model.addAttribute("message","Could not connect DB. Please try again later.");
			return "advancedSearch";
		}
		
	}
	@RequestMapping(method = RequestMethod.GET, value ="/advancedSearch/books/{sort}")
	public String searchAdvanced(Model model, HttpSession session,  @PathVariable("sort") String sort) {
		model.addAttribute("search","books");
		Book searchBook;
		searchBook = (Book) session.getAttribute("searchBook");	
		if(searchBook == null)
			return "redirect:/advancedSearch/";
		try {
			
            if(!this.prepareSearchPage(model, session))
            	return "advancedSearch";
			
			ArrayList<Book> resultBooks = DocumentModel.searchBooks(searchBook);
			ArrayList<Document> list = new ArrayList<Document>();
			list.addAll(resultBooks);
			
			list = DocumentModel.sort(list, sort);
			model.addAttribute("documents",list);
			return "advancedSearch";
		} catch (Exception e) {
			model.addAttribute("message","Could not connect DB. Please try again later.");
			return "advancedSearch";
		}
		
	}
	
	
	//Advanced search for journal issue
	
	@RequestMapping(method = RequestMethod.POST, value ="/advancedSearch/journals")
	public String searchAdvancedJournal(Model model, HttpSession session,  
			@RequestParam("journalID")  int journalID,
			@RequestParam("title")  String title,
			@RequestParam("publisherName")  String publisherName,
			@RequestParam("ISSN")  String ISSN,
			@RequestParam("volume_no") String volume_no,
			@RequestParam("year") String year,
			@RequestParam("month") String month)
	{
		model.addAttribute("search","journals");
		JournalIssue journalissue = new JournalIssue();
		session.setAttribute("searchJournal", null);
		
		if(journalID != -1){
			journalissue.setJournalID(journalID);
		}	
		if(!title.isEmpty()){
			journalissue.setTitle(title);
		}
		if(!publisherName.isEmpty()){
			journalissue.setTitle(title);
		}
		if(!ISSN.isEmpty()){
			journalissue.setISSN(ISSN);
		}
		
		if(!volume_no.isEmpty()){
			journalissue.setVolume_no(Integer.parseInt(volume_no));
		}
		if(!month.equalsIgnoreCase("empty")){
			journalissue.setMonth(month);
		}
		if(!year.isEmpty()){
			journalissue.setYear(Integer.parseInt(year));
		}
		try {
			
            if(!this.prepareSearchPage(model, session))
            	return "advancedSearch";
           
            ArrayList<JournalIssue> resultJournalIssue = DocumentModel.searchJournalIssues(journalissue);
			ArrayList<Document> list = new ArrayList<Document>();
			list.addAll(resultJournalIssue);

			 session.setAttribute("searchJournal", journalissue);
			
			System.out.println(list.size());
			model.addAttribute("documents",list);
			return "advancedSearch";
		} catch (Exception e) {
			model.addAttribute("message","Could not connect DB. Please try again later.");
			return "advancedSearch";
		}
		
	}
	@RequestMapping(method = RequestMethod.GET, value ="/advancedSearch/journals/{sort}")
	public String searchAdvancedJournalSort(Model model, HttpSession session,  @PathVariable("sort") String sort) {
		model.addAttribute("search","journals");
		JournalIssue searchJournal;
		searchJournal = (JournalIssue) session.getAttribute("searchJournal");	
		if(searchJournal == null)
			return "redirect:/advancedSearch/";
		try {
			
            if(!this.prepareSearchPage(model, session))
            	return "advancedSearch";
			
			ArrayList<JournalIssue> resultJournals = DocumentModel.searchJournalIssues(searchJournal);
			ArrayList<Document> list = new ArrayList<Document>();
			list.addAll(resultJournals);
			
			list = DocumentModel.sort(list, sort);
			
			model.addAttribute("documents",list);
			return "advancedSearch";
		} catch (Exception e) {
			model.addAttribute("message","Could not connect DB. Please try again later.");
			return "advancedSearch";
		}
		
	}
	
	//Helper method to fill all the dropdown menus, logged user, etc
	public boolean prepareSearchPage(Model model, HttpSession session){
		
		try {
			User curUser = (User) session.getAttribute("user");
            if(curUser != null){
                model.addAttribute("logged_user", curUser);
            }
    	
			
			model.addAttribute("bookCategories", DocumentModel.listBookCategories());
			model.addAttribute("journals", DocumentModel.listJournals());				
			return true;
		} catch (Exception e) {
			model.addAttribute("message","Could not connect DB. Please try again later.");
			return false;
		}
		
	}
	
	//Search users
	@RequestMapping(method = RequestMethod.GET, value ="/search/users")
	public String searchUser(Model model, HttpSession session) {
		
		User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
            if(!curUser.getRole().equals("Librarian") && !curUser.getRole().equals("Admin"))
                return "redirect:/";
            
        }
        else
        	return "redirect:/";
      
        return "search_users";
        
	}
	@RequestMapping(method = RequestMethod.POST, value ="/search/users")
	public String searchUserPost(Model model, HttpSession session,@ModelAttribute("user") User userSearchInfo) {
		
		
		User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
            if(!curUser.getRole().equals("Librarian") && !curUser.getRole().equals("Admin"))
                return "redirect:/";
        }
        else
        	return "redirect:/";
		
        ArrayList<User> library_users;
		try {
			library_users = UserModel.searchUser(userSearchInfo);
			session.setAttribute("search_user_info", userSearchInfo);
			
			model.addAttribute("search_user", userSearchInfo);
			model.addAttribute("library_users", library_users);
		} catch (Exception e) {
			model.addAttribute("message","Could not connect DB. Please try again later.");
			return "search_users";
		}
		
        return "search_users";
		
	}
	/*
	 * Method to sort out previously searched result
	 */
	@RequestMapping(method = RequestMethod.GET, value ="/search/users/{sort}")
	public String searchUserSort(Model model, HttpSession session, @PathVariable("sort") String sort) {
		
		User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
            if(!curUser.getRole().equals("Librarian") && !curUser.getRole().equals("Admin"))
                return "redirect:/";
        }
        else
        	return "redirect:/";
        User userSearchInfo = (User) session.getAttribute("search_user_info");
        ArrayList<User> library_users;
        try {
			library_users = UserModel.searchUser(userSearchInfo);
			library_users = UserModel.sort(library_users, sort);
			session.setAttribute("search_user_info", userSearchInfo);
			

			model.addAttribute("search_user", userSearchInfo);
			model.addAttribute("library_users", library_users);
		} catch (Exception e) {
			model.addAttribute("message","Could not connect DB. Please try again later.");
			return "search_users";
		}
        
        return "search_users";
		
	}
}
