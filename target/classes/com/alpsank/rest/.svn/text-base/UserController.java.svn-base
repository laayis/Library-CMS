package com.alpsank.rest;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alpsank.entities.DeleteLibrariansForm;
import com.alpsank.entities.User;
import com.alpsank.model.UserModel;
import javax.servlet.http.*;

@Controller
public class UserController {

	@RequestMapping(method = RequestMethod.POST, value = "login")
	public ModelAndView Login(Model model,
			@RequestParam("inputUsername") String email,
			@RequestParam("inputPassword") String password, HttpSession session) {

		try {
			User retrievedUser = UserModel.login(email, password);
			if (retrievedUser != null) {
				session.setAttribute("user", retrievedUser);
				return new ModelAndView("redirect:/");
			} else {
				model.addAttribute("message", "Username or password Invalid!");
				return new ModelAndView("login");
			}
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return new ModelAndView("login");
		}
	}

	@RequestMapping(value = "logout")
	public ModelAndView Logout(Model model, HttpSession session,
			HttpServletRequest request) {

		request.getSession().invalidate();
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "user_page")
	public ModelAndView UserHomePage(Model model, HttpSession session) {
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "forgotPass")
	public ModelAndView ForgotPassHandle(Model model, @RequestParam("username") String username,
			@RequestParam("email") String email,HttpSession session)
	{

		boolean success=false;
		try 
		{
			success = UserModel.ForgotPassSendEmail(username, email);
			if( !success )
				session.setAttribute("warning", "User does not exist in the database. Please sign up instead!");
			else
				session.setAttribute("message",
					"Resetting the password was successful! Check your email!");
		} catch (Exception e) {
			session.setAttribute("warning", "Cannot connect to Database, please try later.");
		}
		
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "forgot_pass")
	public ModelAndView ForgotPassPage(Model model, HttpSession session) {
		return new ModelAndView("forgotpass");
	}

	@RequestMapping(method = RequestMethod.POST, value = "register")
	public String register(Model model, @ModelAttribute("user") User user) {

		user.setRole("library user");

		try {
			AvailabilityChecker checker = AvailabilityChecker
					.checkAvailability(user.getUsername(),
							user.getEmail());

			if (checker != AvailabilityChecker.AVAILABLE) {
				model.addAttribute("message", checker.getMessage());
				return "register";
			}

			UserModel.register(user);
			model.addAttribute("message",
					"Your registration was successful! You may login with your credentials below.");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "register";
		}

		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "edit_profile")
	public String editProfile(Model model, HttpSession session) {
		User curUser = (User) session.getAttribute("user");
		if (curUser != null) {
			model.addAttribute("logged_user", curUser);
		}
		return "edit_profile";
	}

	@RequestMapping(method = RequestMethod.POST, value = "update_profile")
	public ModelAndView updateProfile(Model model,
			@ModelAttribute("user") User user, HttpSession session) {
		User updatedUser=null;
		try {
			updatedUser = UserModel.updateUser(user);
			session.setAttribute("message",
					"Your profile was successfully updated!");

			session.setAttribute("user", updatedUser);
		} catch (Exception e) {
			session.setAttribute("warning", "Cannot connect to Database, please try later.");
		} 
		return new ModelAndView("redirect:/");
		
	}

	// Adding librarians
	@RequestMapping(method = RequestMethod.GET, value = "/admin/addLibrarian")
	public String addLibrarianForm(Model model, HttpSession session) {
		User curUser = (User) session.getAttribute("user");
		if (curUser != null) {
			model.addAttribute("logged_user", curUser);
		}
		if (!curUser.getRole().equals("Admin"))
			return "redirect:/";

		return "add_librarian";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/admin/addLibrarian")
	public String addLibrarian(Model model, HttpSession session,
			@ModelAttribute("librarian") User librarian) {

		librarian.setRole("librarian");

		try {
			User curUser = (User) session.getAttribute("user");
			if (curUser != null) {
				model.addAttribute("logged_user", curUser);
			} else
				return "redirect:/";

			if (!curUser.getRole().equals("Admin"))
				return "redirect:/";

			AvailabilityChecker checker = AvailabilityChecker
					.checkAvailability(librarian.getUsername(),
							librarian.getEmail());

			if (checker != AvailabilityChecker.AVAILABLE) {
				model.addAttribute("message", checker.getMessage());
				return "add_librarian";
			}

			UserModel.register(librarian);
			model.addAttribute("message",
					"The librarian " + librarian.getUsername()
							+ " has been successfully registered.");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "add_librarian";
		}

		return "home";
	}

	// Deleting Librarians
	@RequestMapping(method = RequestMethod.GET, value = "/admin/deleteLibrarians")
	public String deleteLibrarianForm(Model model, HttpSession session) {
		User curUser = (User) session.getAttribute("user");
		if (curUser != null && curUser.getRole().equals("Admin")) {
			model.addAttribute("logged_user", curUser);
			try {
				model.addAttribute("librarians", UserModel.getLibrarians());
			} catch (Exception e) {
				model.addAttribute("message", e.getMessage());
			}
		} else {
			return "redirect:/";
		}

		return "delete_librarians";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/admin/deleteLibrarians")
	public String deleteLibrarians(Model model, HttpSession session,
			@ModelAttribute DeleteLibrariansForm form) {
		form.shrinkLibrarians();
		User curUser = (User) session.getAttribute("user");
		if (curUser != null) {
			model.addAttribute("logged_user", curUser);
			try {
				UserModel.removeLibrarians(form.getLibrarians());
				model.addAttribute("success",
						"Librarian(s) deleted successfully.");
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			} finally {
				try {
					model.addAttribute("librarians", UserModel.getLibrarians());
				} catch (Exception e) {
					model.addAttribute("error", e.getMessage());
				}
			}
		}

		return "delete_librarians";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/users")
	public String listUsers(Model model, HttpSession session,
			HttpServletRequest request) {

		try {
			User curUser = (User) session.getAttribute("user");
			if (curUser != null) {
				model.addAttribute("logged_user", curUser);
			} else
				return "redirect:/";

			if (!curUser.getRole().equals("Librarian")
					&& !curUser.getRole().equals("Admin"))
				return "redirect:/";

			ArrayList<User> libraryUsers = UserModel.listUser();
			System.out.println("Sort users by " + request.getParameter("sort"));
			libraryUsers = UserModel.sort(libraryUsers,
					request.getParameter("sort"));
			model.addAttribute("libraryUsers", libraryUsers);
			return "list_users";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list_users";
	}

	private enum AvailabilityChecker {

		USER_EXISTS("This username is taken."), EMAIL_EXISTS(
				"This email address is being used."), BOTH(
				"Username and email are taken."), AVAILABLE("");

		private String message;

		AvailabilityChecker(String message) {
			this.message = message;
		}

		static AvailabilityChecker checkAvailability(String username, String email) throws Exception {
			boolean userExists = UserModel.exists(username), isEmailUsed = UserModel
					.isEmailUsed(email);

			if (userExists || isEmailUsed) {
				if (userExists && isEmailUsed) {
					return AvailabilityChecker.BOTH;
				} else if (userExists) {
					return AvailabilityChecker.USER_EXISTS;
				} else if (isEmailUsed) {
					return AvailabilityChecker.EMAIL_EXISTS;
				}
			}

			return AvailabilityChecker.AVAILABLE;
		}

		String getMessage() {
			return this.message;
		}
	}
}
