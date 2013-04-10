package com.alpsank.model;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
	static final String DB_NAME = "hululu_library";

	public static Connection getDBConnection() throws Exception {
		Connection dbConnection = null;

		final String DB_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_CONNECTION = "jdbc:mysql://localhost:3306/" + DB_NAME;
		final String DB_USER = "root";
		final String DB_PASSWORD = "";

		try {
			Class.forName(DB_DRIVER);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if( dbConnection == null )
			throw new Exception("Could not connect to the database. DB is offline");
		return dbConnection;
	}

	/**
	 * Handles SQLEsceptions
	 * 
	 * @param ex
	 */
	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {

				e.printStackTrace(System.err);
				System.err.println("SQLState: "
						+ ((SQLException) e).getSQLState());
				System.err.println("Error Code: "
						+ ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}

		}
	}
	
	public static boolean exists(String table, String field, String value) throws Exception{
		ResultSet results = null;
		Connection dbConnection = getDBConnection();

		boolean exists = false; 
		
		try{
			String query = "SELECT 1 FROM " + table + " WHERE " + field + " = ?";			
			PreparedStatement select = dbConnection.prepareStatement(query);
			select.setString(1, value);
			results = select.executeQuery();
			exists = results.next();
		}catch(SQLException e){
			printSQLException(e);
		}finally{
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					printSQLException(e);
				}
			}
		}
		return exists;
	}
	
	
	public static boolean exists(String table, String[] fields, String[] values) throws Exception{
		
		if(fields.length != values.length){
			return false;
		}
		
		ResultSet results = null;
		Connection dbConnection = getDBConnection();

		boolean exists = false; 
		
		try{
			StringBuffer query = new StringBuffer("SELECT 1 FROM " + table);
			query.append(" WHERE 1 = 1 ");
			
			for(int i = 0; i < fields.length; ++i){
				query.append(" AND " + fields[i] + " = ? ");
			}			
			
			PreparedStatement select = dbConnection.prepareStatement(query.toString());
			for(int i = 0; i < fields.length; ++i){
				select.setString(i+1, values[i]);
			}	
			results = select.executeQuery();
			exists = results.next();
		}catch(SQLException e){
			printSQLException(e);
		}finally{
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					printSQLException(e);
				}
			}
		}
		return exists;
	}
	
public static boolean exists(String table, String[] fields, int[] values) throws Exception{
		
		if(fields.length != values.length){
			return false;
		}
		
		ResultSet results = null;
		Connection dbConnection = getDBConnection();

		boolean exists = false; 
		
		try{
			StringBuffer query = new StringBuffer("SELECT 1 FROM " + table);
			query.append(" WHERE 1 = 1 ");
			
			for(int i = 0; i < fields.length; ++i){
				query.append(" AND " + fields[i] + " = ? ");
			}			
			
			PreparedStatement select = dbConnection.prepareStatement(query.toString());
			for(int i = 0; i < fields.length; ++i){
				select.setInt(i+1, values[i]);
			}	
			results = select.executeQuery();
			exists = results.next();
		}catch(SQLException e){
			printSQLException(e);
		}finally{
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					printSQLException(e);
				}
			}
		}
		return exists;
	}
	
	/**
	 *	This method returns all values of a specific attribute 
	 * @return ArrayList of String
	 * @throws Exception DB connection failure
	 */
	public static ArrayList<String> listAttributes(String table, 
								String attribute) throws Exception {
		
		ResultSet results = null;
		Connection dbConnection = getDBConnection();
		ArrayList<String> attributeList = new ArrayList<String>();
		
		try{
			String query = "SELECT " + attribute + "  FROM " + table;
			
			PreparedStatement select = dbConnection.prepareStatement(query);
			results = select.executeQuery();
			 while(results.next()){
				 attributeList.add(results.getString(attribute));				 
			 }			
		}catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
		} finally {
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					printSQLException(e);
				}
			}
		}
		return attributeList;	
	}
	
}
