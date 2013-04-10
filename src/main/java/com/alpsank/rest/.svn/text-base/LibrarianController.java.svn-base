package com.alpsank.rest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alpsank.entities.AddIssueForm;
import com.alpsank.entities.Book;
import com.alpsank.entities.CheckBooksForm;
import com.alpsank.entities.JournalIssue;
import com.alpsank.entities.PhysicalDocumentCopy;
import com.alpsank.entities.User;
import com.alpsank.model.DocumentModel;
import com.alpsank.model.UserModel;

@Controller
public class LibrarianController {
	@InitBinder
    public void setDataBinder(WebDataBinder dataBinder) {
        dataBinder.setAutoGrowNestedPaths(false);
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "add_book")
	public String addBook(Model model, HttpSession session) {

		User curUser = (User) session.getAttribute("user");
		if(curUser != null && !curUser.getRole().equals("Library User")){
			model.addAttribute("logged_user", curUser);
			model.addAttribute("selected", "book");			
			return addAttributesToAddItems(model);
		}
		else {
			return "login";
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "checkin_book")
	public String checkIn(Model model, HttpSession session) {

		User curUser = (User) session.getAttribute("user");
		if(curUser != null && !curUser.getRole().equals("Library User")){
			model.addAttribute("logged_user", curUser);
			return "checkin_book";
		}
		else {
			return "login";
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "checkout_book")
	public String checkOut(Model model, HttpSession session) {

		User curUser = (User) session.getAttribute("user");
		if(curUser != null && !curUser.getRole().equals("Library User")){
			model.addAttribute("logged_user", curUser);
			return "checkout_book";
		}
		else {
			return "login";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "addBook")
	public String addBook(Model model, HttpSession session, @ModelAttribute("book") Book book){
		
		User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        
        try 
        {
        	if(DocumentModel.addBook(book) ){
        		model.addAttribute("success", "Book added successfully!");
        		model.addAttribute("selected", "book");
        		model.addAttribute("book", null);
        	}
        	else {
        		model.addAttribute("error", "Adding the book failed.");
        	}
        } catch (Exception e) 
        {
        	model.addAttribute("error", e.getMessage());
        }
		
		return addAttributesAfterSubmission(model, "add_book");
	}

	@RequestMapping(method = RequestMethod.POST, value = "addIssue")
	public String addIssue(Model model, HttpSession session,
	@ModelAttribute AddIssueForm issueForm)
	{	
		JournalIssue issue = null;
		User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        
        if (issueForm.getJournalID() == -1){
        	// Use all the form fields
        	issue = new JournalIssue(issueForm.getVolume_no(), issueForm.getYear(), 
        			issueForm.getJournal_title(), issueForm.getIssue_title(), issueForm.getMonth(),
        			issueForm.getISSN(), issueForm.getJournalType(), issueForm.getQty(), 
        			issueForm.getPublisherName()); 		
        }
        else {
        	// Associate with a known journal
        	issue = new JournalIssue(issueForm.getJournalID(), issueForm.getVolume_no(),
        			issueForm.getYear(), issueForm.getISSN(), issueForm.getIssue_title(), 
        			issueForm.getMonth(), issueForm.getQty());
        }
        
        try 
        {	
        	if(DocumentModel.addJournalIssue(issue)){
        		model.addAttribute("success", "Journal Issue added successfully!");
        		model.addAttribute("issue", null);
        	}
        	else {
        		model.addAttribute("error", "Adding the journal issue failed.");
        		
        	}
			
        } catch (Exception e) 
        {
        	model.addAttribute("error", e.getMessage());
        }
		
        model.addAttribute("selected", "journal");
		return addAttributesAfterSubmission(model, "add_book");
	}

	@RequestMapping(method = RequestMethod.POST, value = "addCopy")
	public String addCopy(Model model, HttpSession session, @ModelAttribute("qty") Integer qty, 
	@ModelAttribute("isbn") String isbn )
	{
		User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        
		try 
		{
			if(DocumentModel.addCopy(isbn,qty) ){
				model.addAttribute("success", "Number of copies updated successfully!");
			}
			else {
				model.addAttribute("error", "Couldn't find any such item in the database.");
			}
		} catch (Exception e) 
		{
			model.addAttribute("error", e.getMessage());
		}
		
		model.addAttribute("selected", "addCopy");
		return addAttributesAfterSubmission(model, "add_book");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "deleteCopy")
	public String deleteCopy(Model model, HttpSession session, 
	@ModelAttribute("isbn") String serialNumber, 
	@ModelAttribute("copy_no") int copyNo)
	{	
		User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        
		try 
		{		
			if(DocumentModel.removeCopy(serialNumber, copyNo)){
				model.addAttribute("success", "Item(s) removed successfully.");
			}else{
				model.addAttribute("error", "Item(s) failed to be removed. ");
			}
		}
		catch (Exception e){
			model.addAttribute("error", e.getMessage());
		}
		
		model.addAttribute("selected", "delCopy");
		return addAttributesAfterSubmission(model, "add_book");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "checkIn")
	public String checkIn(Model model, HttpSession session, 
	@ModelAttribute CheckBooksForm form, BindingResult result)
	{
		form.shrinkCopies();
		User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        
        // Check for copy existence
        String errorMessage = "";        
        for (PhysicalDocumentCopy copy : form.getCopies()){
        	try {
        		if (!DocumentModel.isExistingCopy(copy)){
        			errorMessage += "(" + copy.getDoc_id() + ", " + copy.getCopy_no() + ")\n";
        		}
        	}
        	catch (Exception e) {
        		model.addAttribute("error", "Could not connect to the Database. Please try again later.");
        		return "checkin_book";
        	}
        }
        
        if (!errorMessage.equals("")){
        	model.addAttribute("error", 
        		"Failed to check in all the items (the following pairs are invalid: " +
        		errorMessage + ")");
        	return "checkin_book";
        }
        
        // Check-In
		try 
		{
			if( DocumentModel.checkIn(form.getCopies())) {
				model.addAttribute("success", "Items checked in successfully.");
			}
			else {
				model.addAttribute("error", "Failed to check in all the items.");
			}
		}
		catch (Exception e){
			model.addAttribute("error", e.getMessage());
		}
		
		return "checkin_book";
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "checkOut")
	public String checkOut(Model model, HttpSession session, 
	@ModelAttribute CheckBooksForm form, BindingResult result)
	{		
		form.shrinkCopies();
		User curUser = (User) session.getAttribute("user");
        if(curUser != null){
            model.addAttribute("logged_user", curUser);
        }
        
        // Check for username availability
        try {	
        	if (!UserModel.isLibUser(form.getUserid())){
        		model.addAttribute("error", "The username \" " + 
        				form.getUserid() + "\" doesn't exist.");
        		return "checkout_book";
        	}
        }
        catch(Exception e){
        	model.addAttribute("error", "Could not connect to the Database. Please try again later.");
    		return "checkout_book";
        }
        
        String errorMessage = "";
        for (PhysicalDocumentCopy copy : form.getCopies()){
        	try {
        		if (!DocumentModel.isAvailableCopy(copy)){
        			errorMessage += "(" + copy.getDoc_id() + ", " + copy.getCopy_no() + ")\n";
        		}
        	}
        	catch (Exception e) {
        		model.addAttribute("error", "Could not connect to the Database. Please try again later.");
        		return "checkout_book";
        	}
        }
        
        if (!errorMessage.equals("")){
        	model.addAttribute("error", 
        		"Failed to check out the items (the following items are unavailable: " +
        		errorMessage + ")");
        	return "checkout_book";
        }
        
		try 
		{
			DocumentModel.checkOut(form.getCopies(), form.getUserid());
			model.addAttribute("success", "Items checked out successfully.");
		}
		catch (Exception e){
			model.addAttribute("error", e.getMessage());
		}
		
		return "checkout_book";
	}

	private String addAttributesToAddItems(Model model){
		try {
			model.addAttribute("bookCategories", DocumentModel.listBookCategories());
			model.addAttribute("journals", DocumentModel.listJournals());	
			model.addAttribute("journal_types", DocumentModel.listJournalTypes());
			return "add_book";
		}
		catch (Exception e){
			model.addAttribute("message","Could not connect to DB. Please try again later.");
			return "login";
		}
	}
	
	private String addAttributesAfterSubmission(Model model, String returnPage){
		try {
			model.addAttribute("bookCategories", DocumentModel.listBookCategories());
			model.addAttribute("journals", DocumentModel.listJournals());	
			model.addAttribute("journal_types", DocumentModel.listJournalTypes());
		}
		catch (Exception e){
			model.addAttribute("message","Could not connect to DB. Please try again later.");
		}
		
		return returnPage;
	}
}
