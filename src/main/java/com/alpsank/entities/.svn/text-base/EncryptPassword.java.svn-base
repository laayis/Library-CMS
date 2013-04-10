package com.alpsank.entities;

import java.util.Random;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class EncryptPassword 
{
	private static BasicPasswordEncryptor passwordEncryptor;
	private static EncryptPassword ep;
	private static Random random;
	
	private EncryptPassword()
	{
		passwordEncryptor = new BasicPasswordEncryptor();
	    random = new Random();
	}

	public static String getHash( String password ) 
	{
		if( ep == null )
			ep = new EncryptPassword();
		
		return passwordEncryptor.encryptPassword( password );
	}
	
	public static boolean check( String given, String actual ) 
	{
		if( ep == null )
			ep = new EncryptPassword();
		
		return (passwordEncryptor.checkPassword(given, actual));
	}

	public static String GenRandomPass( ) 
	{
		if( ep == null )
			ep = new EncryptPassword();
	    
		return "temp"+(random.nextInt(1000)+1);
	}

}
