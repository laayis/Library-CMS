package com.alpsank.test;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.alpsank.entities.User;
import com.alpsank.rest.UserController;

public class UserControllerTest 
{
    private UserController oController;
    private Model model;
    private User user;
    private HttpSession session;
    private HttpServletRequest request;
    
	protected void setUp()
	{
    	oController = new UserController();
    	user = new User("alpsank", "hululu", "alp", "sank", "alpsank@yahoo.com", "1211231231", "1211231331", "Montreal", "admin");
	}

	/*@Test
    public void testEditProfile( ) 
    {
    	setUp();
    	String result = oController.editProfile(model, user);
    	assertEquals("home", result) ;
    }*/  
    
    //TODO: alpsank: I will add mocking, plz don't touch these
    
    
//    @Test
//    public void testLogout( ) 
//    {
//    	setUp();
//    	ModelAndView result = oController.Logout(model, session, request);
//    	ModelAndView mv = new ModelAndView("redirect:/");
//    	
//    	assertEquals(mv, result) ;
//    }  
    
//  @Test
//  public void testLogin( ) 
//  {
//  	setUp();
//  	ModelAndView result = oController.Login(model, user.getUsername(), user.getPassword(), session);
//  	ModelAndView mv = new ModelAndView("redirect:/");
//  	assertEquals(mv, result) ;
//  }  
    
//    @Test
//    public void testRegister( ) 
//    {
//    	setUp();
//    	String result = oController.register(model, user);
//    	assertEquals("home", result) ;
//    }  
}       
