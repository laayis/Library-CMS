package com.alpsank.model;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.util.AutoPopulatingList;

import com.alpsank.entities.EncryptPassword;
import com.alpsank.entities.SendEmail;
import com.alpsank.entities.User;

public class UserModel {

	private static final UserModel instance = new UserModel();

	private UserModel() {
	}

	public static UserModel getInstance() {
		return instance;
	}

	private static final String USER_TABLE = "app_user";

	/**
	 * This method returns a User if the username and password match the ones in
	 * the database. If not, it will return null
	 * 
	 * @param username
	 * @param password
	 * @return User if username and password match, or null otherwise
	 */
	public static User login(String username, String password) throws Exception {
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();

		User user = null;

		try {
			String query = "SELECT * FROM " + USER_TABLE + " WHERE user_id = ?";

			PreparedStatement select = dbConnection.prepareStatement(query);
			select.setString(1, username);
			results = select.executeQuery();
			if (results.next()) {
				user = new User(results.getString("user_id"),
						results.getString("password"),
						results.getString("firstname"),
						results.getString("lastname"),
						results.getString("email"),
						results.getString("telephone1"),
						results.getString("telephone2"),
						results.getString("address"), results.getString("role"));
				if (!EncryptPassword.check(password, user.getPassword())) {
					user = null;
				}
			}
		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
		} finally {
			if (dbConnection != null) {
				try {

					dbConnection.close();
				} catch (SQLException e) {
					DatabaseHandler.printSQLException(e);
				}
			}
		}
		return user;
	}

	/**
	 * @param User
	 *            user. A general user object containing all necessary
	 *            information. IMPORTANT, the role must be specified. This
	 *            method adds a librarian, library user or an administrator
	 * @return true if succeed, false otherwise
	 * @throws SQLException
	 */
	public static boolean register(User user) throws Exception {

		String insert_AppUser = "INSERT INTO app_user"
				+ "(USER_ID, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, "
				+ "TELEPHONE1, TELEPHONE2, PASSWORD, ROLE) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";

		int result = 0;

		Connection dbConnection = DatabaseHandler.getDBConnection();
		PreparedStatement prepareInsert = null;
		try {
			// Prepare multi-statement transaction
			dbConnection.setAutoCommit(false);
			prepareInsert = dbConnection.prepareStatement(insert_AppUser);
			prepareInsert.setString(1, user.getUsername());
			prepareInsert.setString(2, user.getEmail());
			// Handle NULL values
			if (user.getFirstName() != null)
				prepareInsert.setString(3, user.getFirstName());
			else
				prepareInsert.setNull(3, Types.VARCHAR);
			if (user.getLastName() != null)
				prepareInsert.setString(4, user.getLastName());
			else
				prepareInsert.setNull(4, Types.VARCHAR);
			if (user.getAddress() != null)
				prepareInsert.setString(5, user.getAddress());
			else
				prepareInsert.setNull(5, Types.VARCHAR);
			if (user.getPhone1() != null)
				prepareInsert.setString(6, user.getPhone1());
			else
				prepareInsert.setNull(6, Types.VARCHAR);
			if (user.getPhone2() != null)
				prepareInsert.setString(7, user.getPhone2());
			else
				prepareInsert.setNull(7, Types.VARCHAR);
			prepareInsert.setString(8,
					EncryptPassword.getHash(user.getPassword()));
			prepareInsert.setString(9, user.getRole());
			result = prepareInsert.executeUpdate();
			dbConnection.commit();

		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				DatabaseHandler.printSQLException(e1);
			}
		} finally {
			try {
				if (prepareInsert != null) {
					prepareInsert.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (SQLException e) {
				DatabaseHandler.printSQLException(e);
			}
		}
		return (result > 0);
	}

	/**
	 * Check whether a user with an email or name equal to the param, exists in
	 * the DB
	 * 
	 * @param username
	 * @return boolean: true if user exists, false otherwise
	 */
	public static boolean exists(String username) throws Exception {
		return DatabaseHandler.exists(USER_TABLE, "user_id", username);
	}

	/**
	 * Check whether a user with the @param: email exists in the DB
	 * 
	 * @param userId
	 * @return boolean: true if user exists, false otherwise
	 * @throws Exception
	 */
	public static boolean isEmailUsed(String email) throws Exception {
		return DatabaseHandler.exists(USER_TABLE, "email", email);
	}

	public static boolean isLibUser(String user) throws Exception {
		String[] fields = { "user_id", "role" };
		String[] values = { user, "Library User" };
		return DatabaseHandler.exists(USER_TABLE, fields, values);
	}

	public static ArrayList<User> listUser() throws Exception {
		return searchUser(new User());
	}

	public static ArrayList<User> searchUser(User userSearchInfo)
			throws Exception {
		ResultSet results = null;
		PreparedStatement select = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		ArrayList<User> users = new ArrayList<User>();

		try {
			StringBuffer query = new StringBuffer("SELECT * FROM " + USER_TABLE
					+ " WHERE role <> 'Admin' ");

			int i = 0;
			int NUM_OF_FIELDS = 7; // number of fields in User class
			boolean[] hasValue = new boolean[NUM_OF_FIELDS];
			String[] values = { userSearchInfo.getUsername(),
					userSearchInfo.getFirstName(),
					userSearchInfo.getLastName(), userSearchInfo.getEmail(),
					userSearchInfo.getPhone1(), userSearchInfo.getPhone2(),
					userSearchInfo.getRole() };
			if (!userSearchInfo.getUsername().equals("")) {
				query.append(" AND user_id LIKE ? ");
				hasValue[0] = true;
			}
			if (!userSearchInfo.getFirstName().equals("")) {
				query.append(" AND firstname LIKE ? ");
				hasValue[1] = true;
			}
			if (!userSearchInfo.getLastName().equals("")) {
				query.append(" AND lastname LIKE ? ");
				hasValue[2] = true;
			}
			if (!userSearchInfo.getEmail().equals("")) {
				query.append(" AND email LIKE ? ");
				hasValue[3] = true;
			}
			// TODO: OR phone numbers
			if (!userSearchInfo.getPhone1().equals("")) {
				query.append(" AND telephone1 LIKE ? ");
				hasValue[4] = true;
			}
			if (!userSearchInfo.getPhone2().equals("")) {
				query.append(" AND telephone2 LIKE ? ");
				hasValue[5] = true;
			}
			if (!userSearchInfo.getRole().equals("")) {
				query.append(" AND role = ? ");
				hasValue[6] = true;
			}

			query.append(" ORDER BY role, user_id, lastname, firstname, email ");
			select = dbConnection.prepareStatement(query.toString());
			// System.out.println(query);
			int j = 1;
			for (i = 0; i < NUM_OF_FIELDS - 1; ++i) {
				if (hasValue[i]) {
					select.setString(j++, "%" + values[i] + "%");
				}
			}
			// if role not empty
			if (hasValue[NUM_OF_FIELDS - 1]) {
				select.setString(j, values[NUM_OF_FIELDS - 1]);
			}

			results = select.executeQuery();

			while (results.next()) {
				users.add(new User(results.getString("user_id"), results
						.getString("firstname"), results.getString("lastname"),
						results.getString("email"), results
								.getString("telephone1"), results
								.getString("telephone2"), results
								.getString("address"), results
								.getString("role")));
			}
		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
		} finally {
			if (select != null)
				try {
					select.close();
				} catch (Exception ignored) {
					;
				}
			if (results != null)
				try {
					results.close();
				} catch (Exception ignored) {
					;
				}
			if (dbConnection != null)
				try {
					dbConnection.close();
				} catch (Exception ignored) {
					;
				}
		}
		return users;
	}

	public static ArrayList<User> sort(ArrayList<User> users, String sorting) {
		if (sorting == null) {
			return users;
		} else if (sorting.equalsIgnoreCase("username")) {
			return User.sortByUsername(users);
		} else if (sorting.equalsIgnoreCase("firstname")) {
			return User.sortByFirstName(users);
		} else if (sorting.equalsIgnoreCase("lastname")) {
			return User.sortByLastName(users);
		} else if (sorting.equalsIgnoreCase("email")) {
			return User.sortByEmail(users);
		} else if (sorting.equalsIgnoreCase("address")) {
			return User.sortByAddress(users);
		} else if (sorting.equalsIgnoreCase("phone1")) {
			return User.sortByPhone1(users);
		} else if (sorting.equalsIgnoreCase("phone2")) {
			return User.sortByPhone2(users);
		} else if (sorting.equalsIgnoreCase("role")) {
			return User.sortByRole(users);
		} else {
			return users;
		}
	}

	public static boolean ForgotPassSendEmail(String username, String email)
			throws Exception {
		Connection dbConnection = DatabaseHandler.getDBConnection();
		if (DatabaseHandler.exists(USER_TABLE, new String[] { "user_id",
				"email" }, new String[] { username, email })) {
			String newPass = EncryptPassword.GenRandomPass();

			// update user row in db
			String sqlUpdate = "UPDATE app_user SET" + " PASSWORD=?"
					+ " WHERE user_id=?";

			PreparedStatement update = null;
			try {
				update = dbConnection.prepareStatement(sqlUpdate);
				update.setString(1, EncryptPassword.getHash(newPass));

				update.setString(2, username);
				update.executeUpdate();

			} catch (SQLException e) {
				DatabaseHandler.printSQLException(e);
				try {
					dbConnection.rollback();
				} catch (SQLException e1) {
					DatabaseHandler.printSQLException(e1);
				}
			} finally {
				try {
					if (update != null) {
						update.close();
					}

					if (dbConnection != null) {
						dbConnection.close();
					}
				} catch (SQLException e) {
					DatabaseHandler.printSQLException(e);
				}
			}

			SendEmail.SendMsg(new String("Hello " + username
					+ ". Your new password is " + newPass), email);
		} else
			return false;
		return true;
	}

	/***
	 * This method remove all librarians in the list of librarian user IDs. Can
	 * be extended to delete users if we decide to do so.
	 * 
	 * @param user_ids
	 *            :AutoPopulatingList of librarian user IDs
	 * @return True if successful
	 * @throws Exception
	 */
	public static boolean removeLibrarians(AutoPopulatingList<String> user_ids)
			throws Exception {

		boolean success = true;
		int rowsAffected = 0;
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		dbConnection.setAutoCommit(false);
		PreparedStatement ps = null;

		try {
			// Delete each librarian in list
			String lib_delete = "DELETE FROM " + USER_TABLE
					+ " WHERE user_id = '%s';";
			if (!user_ids.isEmpty()) {
				for (int i = 0; i < user_ids.size() && success; i++) {
					ps = dbConnection.prepareStatement(String.format(
							lib_delete, user_ids.get(i)));
					System.out.println(ps.toString());
					rowsAffected = ps.executeUpdate();
					if (rowsAffected == 0) {
						success = false;
					} else {
						dbConnection.commit();
					}
				}
			} else {
				success = false;
			}
		} catch (SQLException e) {
			dbConnection.rollback();
			DatabaseHandler.printSQLException(e);
			success = false;
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (Exception ignored) {
					;
				}
			if (results != null)
				try {
					results.close();
				} catch (Exception ignored) {
					;
				}
			if (dbConnection != null)
				try {
					dbConnection.close();
				} catch (Exception ignored) {
					;
				}
		}
		return success;
	}

	public static ArrayList<User> getLibrarians() throws Exception {
		User librarian = new User();
		librarian.setRole("Librarian");
		return searchUser(librarian);
	}

	/**
	 * @param User
	 *            user. A general user object containing all necessary
	 *            information. IMPORTANT, the role must be specified. This
	 *            method adds a librarian, library user or an administrator
	 * @return updated user
	 * @throws SQLException
	 */
	public static User updateUser(User user) throws Exception {

		String sqlUpdate = "UPDATE app_user SET"
				+ " EMAIL=?, FIRSTNAME=?, LASTNAME=?, ADDRESS=?, "
				+ " TELEPHONE1=?, TELEPHONE2=?" + " WHERE user_id=?";

		int result = 0;
		
		User updatedUser=null;

		Connection dbConnection = DatabaseHandler.getDBConnection();
		PreparedStatement update = null;
		ResultSet results = null;
		try {

			update = dbConnection.prepareStatement(sqlUpdate);
			update.setString(1, user.getEmail());
			// Handle NULL values
			if (user.getFirstName() != null)
				update.setString(2, user.getFirstName());
			else
				update.setNull(2, Types.VARCHAR);
			if (user.getLastName() != null)
				update.setString(3, user.getLastName());
			else
				update.setNull(3, Types.VARCHAR);
			if (user.getAddress() != null)
				update.setString(4, user.getAddress());
			else
				update.setNull(4, Types.VARCHAR);
			if (user.getPhone1() != null)
				update.setString(5, user.getPhone1());
			else
				update.setNull(5, Types.VARCHAR);
			if (user.getPhone2() != null)
				update.setString(6, user.getPhone2());
			else
				update.setNull(6, Types.VARCHAR);

			update.setString(7, user.getUsername());

			result = update.executeUpdate();

			String query = "SELECT * FROM " + USER_TABLE + " WHERE user_id = ?";

			PreparedStatement select = dbConnection.prepareStatement(query);
			select.setString(1, user.getUsername());
			results = select.executeQuery();
			if (results.next()) {
				updatedUser = new User(results.getString("user_id"),
						results.getString("password"),
						results.getString("firstname"),
						results.getString("lastname"),
						results.getString("email"),
						results.getString("telephone1"),
						results.getString("telephone2"),
						results.getString("address"), results.getString("role"));
			}
		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				DatabaseHandler.printSQLException(e1);
			}
		} finally {
			try {
				if (update != null) {
					update.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (SQLException e) {
				DatabaseHandler.printSQLException(e);
			}
		}
		return updatedUser;
	}
}
