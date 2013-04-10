package com.alpsank.rest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alpsank.entities.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		

		User curUser = (User) session.getAttribute("user");
		if(curUser != null){
            if(!curUser.getFirstName().isEmpty())
			    model.addAttribute("loggedin_mess", "Currently logged in as "+ curUser.getFirstName()+ " "+curUser.getLastName());
            else
                model.addAttribute("loggedin_mess", "Currently logged in as "+ curUser.getUsername());

			model.addAttribute("logged_user", curUser);

		}
		String message = (String) session.getAttribute("message");
		String warning = (String) session.getAttribute("warning");
		if(message != null && !message.isEmpty())
		{
			model.addAttribute("message",message);
			session.setAttribute("message", null);
		}
		
		if(warning != null && !warning.isEmpty())
		{
			model.addAttribute("warning",warning);
			session.setAttribute("warning", null);
		}
		return "home";
	}
	
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String home(Model model,@PathVariable("page") String page) {
		return page;
	}
}
