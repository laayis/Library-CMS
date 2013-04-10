package com.alpsank.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class User {
    
	
	private String username, password, firstName, lastName, email, phone1, phone2, address, role;

    public User()
    {
    	username = password = 
    			firstName = lastName = 
    			email = phone1 = 
    			phone2 = address = role = "";
    }
	public User(String username, String password, String firstName,
			String lastName, String email, String phone1, String phone2,
			String address, String role) {

		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.address = address;
		this.role = role;
	}
	
	public User(String username, String firstName,
			String lastName, String email, String phone1, String phone2,
			String address, String role) {

		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.address = address;
		this.role = role;
	}

    @Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
		
	public String getUsername(){
		return username;
	}
	public String getEmail(){
		return email;
	}
	public String getPassword() {
		return password;
	}
	
    public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}    
	
	public static ArrayList<User> sortByUsername(ArrayList<User> users){
		Collections.sort(users, new UsernameComparable());
		return users;
	}
	
	public static ArrayList<User> sortByFirstName(ArrayList<User> users){
		Collections.sort(users, new FirstNameComparable());
		return users;
	}
	
	public static ArrayList<User> sortByLastName(ArrayList<User> users){
		Collections.sort(users, new LastNameComparable());
		return users;
	}
	
	public static ArrayList<User> sortByEmail(ArrayList<User> users){
		Collections.sort(users, new EmailComparable());
		return users;
	}
	
	public static ArrayList<User> sortByAddress(ArrayList<User> users){
		Collections.sort(users, new AddressComparable());
		return users;
	}
	
	public static ArrayList<User> sortByPhone1(ArrayList<User> users){
		Collections.sort(users, new Phone1Comparable());
		return users;
	}
	
	public static ArrayList<User> sortByPhone2(ArrayList<User> users){
		Collections.sort(users, new Phone2Comparable());
		return users;
	}
	
	public static ArrayList<User> sortByRole(ArrayList<User> users){
		Collections.sort(users, new RoleComparable());
		return users;
	}	
}

class UsernameComparable implements Comparator<User> {
	@Override
	public int compare(User o1, User o2) {
		return o1.getUsername().compareToIgnoreCase(o2.getUsername());
	}	
}

class FirstNameComparable implements Comparator<User> {
	@Override
	public int compare(User o1, User o2) {
		return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
	}	
}

class LastNameComparable implements Comparator<User> {
	@Override
	public int compare(User o1, User o2) {
		return o1.getLastName().compareToIgnoreCase(o2.getLastName());
	}	
}

class EmailComparable implements Comparator<User> {
	@Override
	public int compare(User o1, User o2) {
		return o1.getEmail().compareToIgnoreCase(o2.getEmail());
	}	
}

class Phone1Comparable implements Comparator<User> {
	@Override
	public int compare(User o1, User o2) {
		return o1.getPhone1().compareToIgnoreCase(o2.getPhone1());
	}	
}

class Phone2Comparable implements Comparator<User> {
	@Override
	public int compare(User o1, User o2) {
		return o1.getPhone2().compareToIgnoreCase(o2.getPhone2());
	}	
}

class AddressComparable implements Comparator<User> {
	@Override
	public int compare(User o1, User o2) {
		return o1.getAddress().compareToIgnoreCase(o2.getAddress());
	}	
}

class RoleComparable implements Comparator<User> {
	@Override
	public int compare(User o1, User o2) {
		return o1.getRole().compareToIgnoreCase(o2.getRole());
	}	
}