package com.alpsank.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.util.AutoPopulatingList;

import com.alpsank.entities.Book;
import com.alpsank.entities.Borrow;
import com.alpsank.entities.Document;
import com.alpsank.entities.Journal;
import com.alpsank.entities.JournalIssue;
import com.alpsank.entities.PhysicalDocumentCopy;

public class DocumentModel {
	
    private static final DocumentModel instance = new DocumentModel();
    
    private DocumentModel() {}
 
    public static DocumentModel getInstance() {
        return instance;
    }
    
	private static final String APP_USER = "app_user", DOCUMENT_TABLE = "document",
			ISSUE_TABLE = "issue", BOOK_TABLE = "book",
			JOURNAL_TABLE = "journal", VOLUME_TABLE = "volume",
			BORROW_TABLE = "borrow", BORROW_PACK_TABLE = "borrow_pack",
			COPY_TABLE= "copy", AUTHOR_TABLE = "author", PUBLISHER_TABLE = "publisher",
			AUTHOR_WRITES_TEXT_TABLE = "author_writes_text", BOOK_CATEGORY_TABLE = "book_category",
			JOURNAL_TYPE_TABLE = "journal_type";



	/**
	 * This method queries all books from the database and set all book
	 * attributes accordingly. The query is sorted by the id of the book, and
	 * sees if the same id repeats. If this happens, it means the book has more
	 * than one author. In this case, instead of creating a new book and add to
	 * the arrayList, the new author's name is appended after the old one. (See
	 * setAuthor in Document.java)
	 * @return an ArrayList of Books
	 */
	public static ArrayList<Book> listBooks() throws Exception {
		return searchBooks(new Book());
	}

	public static ArrayList<JournalIssue> listJournalIssues() throws Exception {
		return searchJournalIssues(new JournalIssue());
	}

	/**
	 * (Librarian should only use this method if the document corresponding the
	 * the ISBN/ISSN) is already stored in the DB) This method inserts a new
	 * physical copy in the copy table for the document identified by the
	 * ISBN/ISSN. In the case the ISBN/ISSN doesn't exist in the db, an error
	 * (false flag) is returned. alpsank: I changed it to update instead of
	 * insert
	 *
	 * @param serialNumber
	 *            : ISBN (13 digits) and ISSN (8 digits) corresponding to book
	 *            and journal issue respectively.
	 * @param ncopies
	 *            : Number of copies librarian wishes to add.
	 * @return true if success
	 * @throws Exception
	 */


	public static boolean addCopy(String serialNumber, int ncopies) throws Exception {

        boolean success = true;
        if (ncopies <= 0)
            ncopies = 1;

        int length = serialNumber.length();
        ResultSet results = null;
        Connection dbConnection = DatabaseHandler.getDBConnection();
        PreparedStatement ps = null;
        try {
            // Multi-transaction
            dbConnection.setAutoCommit(false);

            /** ISBN - Book copy **/
            if (length == 13) {

                /** Extract document_id **/
                String copy_select = "SELECT * FROM " + BOOK_TABLE + " b, "
                        + DOCUMENT_TABLE + " d, "
                        + COPY_TABLE + " c WHERE b.book_id = d.document_id "
                        + "AND c.document_id = d.document_id "
                        + "AND b.ISBN = " + serialNumber
                        + " ORDER BY copy_no DESC";

                ps = dbConnection.prepareStatement(copy_select);
                results = ps.executeQuery();

                if (results.next()) {
                    // TODO: add copy_location
                    /** Insert "physical book" into copy table **/
                    String copy_add = "INSERT INTO copy (copy_no, document_id, status) VALUES(?,?,?)";
                    ps = dbConnection.prepareStatement(copy_add);
                    for (int i = 1; i <= ncopies; i++) {
                        ps.setInt(1, results.getInt("copy_no") + i);
                        ps.setInt(2, results.getInt("document_id"));
                        ps.setString(3, "Available");
                        ps.addBatch();
                    }
                    ps.executeBatch();
                    dbConnection.commit();


                } /** Check if document exists (but no copies exists) **/
                else{
                    String doc_select = "SELECT document_id FROM " + BOOK_TABLE + " b, "
                            + DOCUMENT_TABLE + " d "
                            + "WHERE b.book_id = d.document_id "
                            + "AND b.ISBN = " + serialNumber;
                    ps.close();
                    ps = dbConnection.prepareStatement(doc_select);
                    results = ps.executeQuery();
                    if (results.next()) {
                        // TODO: add copy_location
                        /** Insert "physical book" into copy table **/
                        String copy_add = "INSERT INTO copy (copy_no, document_id, status) VALUES(?,?,?)";
                        ps = dbConnection.prepareStatement(copy_add);
                        for (int i = 1; i <= ncopies; i++) {
                            ps.setInt(1, i);
                            ps.setInt(2, results.getInt(1));
                            ps.setString(3, "Available");
                            ps.addBatch();
                        }
                        ps.executeBatch();
                        dbConnection.commit();

                    }
                    else // ISBN invalid
                    	success = false;
                }
            }
			/** ISSN (length = 8) - Journal Issue copy **/
			else {

				/** Extract document_id **/
				String copy_select = "SELECT * FROM " + ISSUE_TABLE + " i, "
						+ DOCUMENT_TABLE + " d, "
						+ "copy c WHERE i.journal_issue_id = d.document_id "
						+ "AND c.document_id = d.document_id "
						+ "AND i.ISSN = " + serialNumber
						+ " ORDER BY copy_no DESC";

				ps = dbConnection.prepareStatement(copy_select);
				results = ps.executeQuery();

				if (results.next()) {
					// TODO: add copy_location
					/** Insert "physical book" into copy table **/
					String copy_add = "INSERT INTO copy (copy_no, document_id, status) VALUES(?,?,?)";
					ps = dbConnection.prepareStatement(copy_add);
					for (int i = 1; i <= ncopies; i++) {
                        ps.setInt(1, results.getInt("copy_no") + i);
						ps.setInt(2, results.getInt("document_id"));
						ps.setString(3, "Available");
						ps.addBatch();
					}
					ps.executeBatch();
					dbConnection.commit();
				} else{
                    String doc_select = "SELECT document_id FROM " + ISSUE_TABLE + " i, "
                            + DOCUMENT_TABLE + " d "
                            + "WHERE i.journal_issue_id = d.document_id "
                            + "AND i.ISSN = " + serialNumber;
                    ps.close();
                    ps = dbConnection.prepareStatement(doc_select);
                    results = ps.executeQuery();
                    if (results.next()) {
                        // TODO: add copy_location
                        /** Insert "physical book" into copy table **/
                        String copy_add = "INSERT INTO copy (copy_no, document_id, status) VALUES(?,?,?)";
                        ps = dbConnection.prepareStatement(copy_add);
                        for (int i = 1; i <= ncopies; i++) {
                            ps.setInt(1, i);
                            ps.setInt(2, results.getInt(1));
                            ps.setString(3, "Available");
                            ps.addBatch();
                        }
                        ps.executeBatch();
                        dbConnection.commit();

                    }
                    else // ISBN invalid
                    	success = false;
                }
            }

		} catch (SQLException e) {
			dbConnection.rollback();
			DatabaseHandler.printSQLException(e);
			success = false;
		} finally {
			if (ps != null)	try {ps.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}
		return success;
	}

	/**
	 * (Librarian use this method to insert information related to a NEW
	 * document that has NOT yet been stored in the DB) This method creates new
	 * document/book/author_writes_text/copy (and possibly publisher/author)
	 * entries for the "new" ISBN provided
	 *
	 * @param book
	 *            : New book, with all fields (except quantity and document_id)
	 *            filled.
	 * @param ncopies
	 *            : Number of copies the librarian wishesto add.
	 * @return true if success
	 * @throws Exception
	 */
	public static boolean addBook(Book book) throws Exception {

		int ncopies = book.getQty();
		if (ncopies <= 0)
			ncopies = 1;
		boolean success = true;
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		PreparedStatement ps = null;

		try {
			// Multi-transaction
			dbConnection.setAutoCommit(false);

			if (!DatabaseHandler.exists(BOOK_TABLE, "ISBN", book.getISBN())) {
				/** Create a new document_id for the book **/
				add_document(dbConnection, ps, results, book);

//				/** Check whether category exists in the category table **/
//
//				String category_select = "SELECT * FROM "+ BOOK_CATEGORY_TABLE +" WHERE category = ?";
//				ps = dbConnection.prepareStatement(category_select);
//				ps.setString(1, book.getCategory());
//				results = ps.executeQuery();
//
//				if (!results.next()) {
//
//					/** Insert new catogory if it doesn't exist **/
//					String category_add = "INSERT INTO "+ BOOK_CATEGORY_TABLE+" (category) VALUES(?)";
//					ps = dbConnection.prepareStatement(category_add);
//					ps.setString(1, book.getCategory());
//					ps.executeUpdate();
//
//				}
				/** Check whether Publisher exists in the publisher table **/

				String publisher_select = "SELECT * FROM publisher WHERE corporate_name = ?";
				ps = dbConnection.prepareStatement(publisher_select);
				ps.setString(1, book.getPublisherName());
				results = ps.executeQuery();
				int publisher_id = -1;
				/** Publisher exists **/
				if (results.next()) {
					publisher_id = results.getInt("publisher_id");
				} else {
					/** Insert Publisher as it doesn't exist **/
					String publisher_add = "INSERT INTO publisher (corporate_name) VALUES(?)";
					ps = dbConnection.prepareStatement(publisher_add,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, book.getPublisherName());
					ps.executeUpdate();

					results = ps.getGeneratedKeys();
					if (results.next()) {
						publisher_id = results.getInt(1);
					}

				}

				/** Create book **/
				String book_add = "INSERT INTO book (book_id, ISBN, year, edition, category, publisher_id) VALUES(?,?,?,?,?,?)";
				ps = dbConnection.prepareStatement(book_add);
				ps.setInt(1, book.getDoc_id());
				ps.setString(2, book.getISBN());
				ps.setInt(3, book.getYear());
				ps.setInt(4, book.getEdition());
				ps.setString(5,  book.getCategory());
				ps.setInt(6, publisher_id);
				ps.executeUpdate();

		        /** Check whether Authors exist in author table **/

		        String author_select = "SELECT * from author where author_name = ?";
		        String author_add = "INSERT into author (author_name) VALUES(?)";
		        String author_write_text_add = "INSERT into author_writes_text (author_id,book_id) VALUES(?,?)";
		        int author_id = -1;
		        for (String s:book.getAuthorNamesArray()){
		            ps = dbConnection.prepareStatement(author_select);
		            ps.setString(1, s);
		            results = ps.executeQuery();

		                /** author exists **/
		            if (results.next()){
		                author_id = results.getInt("author_id");
		            }
		            else{/** Insert author in author table if doesn't exist **/
		                ps = dbConnection.prepareStatement(author_add,  Statement.RETURN_GENERATED_KEYS);
		                ps.setString(1,s);
		                ps.executeUpdate();

		                results = ps.getGeneratedKeys();
		                if(results.next()) {
		                    author_id = results.getInt(1);
		                }
		            }

		            /** Insert author writes text entry **/
		            ps = dbConnection.prepareStatement(author_write_text_add);
		            ps.setInt(1, author_id);
		            ps.setInt(2, book.getDoc_id());
		            ps.executeUpdate();
		        }



				/** Create copy **/

				// TODO: add copy_location
				/** Insert "physical book" into copy table **/
				String copy_add = "INSERT INTO copy (copy_no, document_id, status) VALUES(?,?,?)";
				ps = dbConnection.prepareStatement(copy_add);
				for (int i = 1; i <= ncopies; i++) {
					ps.setInt(1, i);
					/** First book copy **/
					ps.setInt(2, book.getDoc_id());
					ps.setString(3, "Available");
					ps.addBatch();
				}
				ps.executeBatch();
				dbConnection.commit();
			}
			// TODO: resolve this
			else {
				/**
				 * ISBN already exists in book table - must use add_copy (OR
				 * UPDATE book??? )
				 **/
				success = false;
			}
		} catch (SQLException e) {
			dbConnection.rollback();
			success = false;
			DatabaseHandler.printSQLException(e);
		}
		// throw e;}
		finally {
			if (ps != null)	try {ps.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}
		return success;

	}

	public static boolean addJournalIssue(JournalIssue journalIssue) throws Exception {
		int ncopies = journalIssue.getQty();
		if (ncopies <= 0)
			ncopies = 1;
		boolean success = true;
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		PreparedStatement ps = null;

		try {
			dbConnection.setAutoCommit(false);

			if (!DatabaseHandler.exists(ISSUE_TABLE, "ISSN", journalIssue.getISSN())) {
				add_document(dbConnection, ps, results, journalIssue);

				/** add_issue fails if librarian inputs an existing journal_title in the db - she should use the dropdown menu **/
				if (add_issue(dbConnection, ps, results, journalIssue)){
					// TODO: add copy_location
					String copy_add = "INSERT INTO copy (copy_no, document_id, status) VALUES(?,?,?)";
					ps = dbConnection.prepareStatement(copy_add);
					for (int i = 1; i <= ncopies; i++) {
						ps.setInt(1, i);
						ps.setInt(2, journalIssue.getDoc_id());
						ps.setString(3, "Available");
						ps.addBatch();
					}
					ps.executeBatch();
					dbConnection.commit();

				}
				else {
					success = false;
					dbConnection.rollback();
				}

			} else {
				success = false;
			}
		} catch (SQLException e) {
			dbConnection.rollback();
			success = false;
			DatabaseHandler.printSQLException(e);
		} finally {
			if (ps != null)	try {ps.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}

		return success;
	}

	private static boolean add_issue(Connection dbConnection, PreparedStatement ps, ResultSet results,
			JournalIssue journalIssue) throws Exception {

		int journal_id = journalIssue.getJournalID();
		if (journal_id == 0){

			/** Check whether Publisher exists in the publisher table **/

			String publisher_select = "SELECT * FROM publisher WHERE corporate_name = ?";
			ps = dbConnection.prepareStatement(publisher_select);
			ps.setString(1, journalIssue.getPublisherName());
			results = ps.executeQuery();
			int publisher_id = -1;
			/** Publisher exists **/
			if (results.next()) {
				publisher_id = results.getInt("publisher_id");
			} else {
				/** Insert Publisher as it doesn't exist **/
				String publisher_add = "INSERT INTO publisher (corporate_name) VALUES(?)";
				ps = dbConnection.prepareStatement(publisher_add,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, journalIssue.getPublisherName());
				ps.executeUpdate();

				results = ps.getGeneratedKeys();
				if (results.next()) {
					publisher_id = results.getInt(1);
				}

			}


			/** Check whether Journal Title + Journal type + Publisher TRIO exists in journal table**/
			String journal_select = "SELECT * FROM "+ JOURNAL_TABLE +" WHERE journal_title = ? AND journal_type = ? AND publisher_id = ?";
			ps = dbConnection.prepareStatement(journal_select);
			ps.setString(1, journalIssue.getJournal_title());
			ps.setString(2,  journalIssue.getCategory());
			ps.setInt(3, publisher_id);
			results = ps.executeQuery();

			if (results.next()) {
				journal_id = results.getInt("journal_id");
			} else {

				/** If journal_title exists, then the librarian SHOULD'VE USED THE DROPDOWN MENU **/
				if (DatabaseHandler.exists(JOURNAL_TABLE, "journal_title", journalIssue.getJournal_title()))
					return false;

				/** Insert Journal Title + journal type as it doesn't exist **/
				String publisher_add = "INSERT INTO "+ JOURNAL_TABLE +" (journal_title, journal_type, publisher_id) VALUES(?,?,?)";
				ps = dbConnection.prepareStatement(publisher_add,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, journalIssue.getJournal_title());
				ps.setString(2,  journalIssue.getCategory());
				ps.setInt(3, publisher_id);
				ps.executeUpdate();

				results = ps.getGeneratedKeys();
				if (results.next()) {
					journal_id = results.getInt(1);
				}

			}


		}

		/** Make sure volume_no, journal_id and year TRIO doesn't exist **/
		if(!DatabaseHandler.exists(VOLUME_TABLE, new String[] {"volume_no",  "journal_id", "year"},
			new int []{journalIssue.getVolume_no(), journal_id, journalIssue.getYear()})) {

			/** Insert volume_no, journal_id and year TRIO as it doesn't exist **/
			String volume_add = "INSERT INTO "+ VOLUME_TABLE +" (volume_no, journal_id, year) VALUES(?,?,?)";
			ps = dbConnection.prepareStatement(volume_add);
			ps.setInt(1, journalIssue.getVolume_no());
			ps.setInt(2, journal_id);
			ps.setInt(3, journalIssue.getYear());;
			ps.executeUpdate();
		}

		/** add issue into issue_table **/
		String issue_add = "INSERT INTO " + ISSUE_TABLE
				+ " VALUES(?,?,?,?,?,?)";
		ps = dbConnection.prepareStatement(issue_add);
		ps.setInt(1, journalIssue.getDoc_id());
		ps.setInt(2, journalIssue.getYear());
		ps.setString(3, journalIssue.getMonth());
		ps.setInt(4, journalIssue.getVolume_no());
		ps.setInt(5, journal_id);
		ps.setString(6, journalIssue.getISSN());
		ps.executeUpdate();

		return true;

	}

	private static void add_document(Connection dbConnection, PreparedStatement ps,
			ResultSet results, Document document) throws SQLException {


		/** Insert New Document **/

		String document_add = "INSERT into document (title, quantity) VALUES(?,?)";
		ps = dbConnection.prepareStatement(document_add,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, document.getTitle());
		ps.setInt(2, 0); // Set to 0, Insert copy statement will activate a
							// trigger to increase this value
		ps.executeUpdate();

		results = ps.getGeneratedKeys();
		if (results.next()) {
			document.setDoc_id(results.getInt(1));
		}


	}

	/**
	 * This method deletes a specific copy of a book.
	 *
	 * @param
	 * @param
	 * @return true if success
	 * @throws Exception
	 */
	public static boolean removeCopy(String serialNumber, int copyno) throws Exception {

		boolean success = true;
		int rowsAffected = 0;
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		PreparedStatement ps = null;

		Book book = new Book();
		JournalIssue js = new JournalIssue();
		int doc_id;

		try {
			if (serialNumber.length() == 13){
				book = retrieveBook(serialNumber);
				doc_id = book.getDoc_id();
			}
			else // JournalIssue
			{
				js = retrieveJournalIssue(serialNumber);
				doc_id = js.getDoc_id();
			}

			/** Delete the physical copy of the document **/
			String copy_delete = String.format(
				"DELETE FROM %s WHERE copy_no = %s AND document_id = %s " +
				"AND status = 'Available'", COPY_TABLE, copyno, doc_id);

			ps = dbConnection.prepareStatement(copy_delete);
			rowsAffected = ps.executeUpdate();

			if (rowsAffected == 0)
				success = false;

		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
			success = false;
		} finally {
			if (ps != null)	try {ps.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}

		return success;
	}

	/**
	 * This method deletes the entire record of a document.
	 *
	 * @param pdc
	 * 				: PhysicalDocumentCopy object
	 * @return true if success
	 * @throws Exception
	 */
	public static boolean removeDocument(PhysicalDocumentCopy pdc) throws Exception {

		boolean success = true;
		int rowsAffected = 0;
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		PreparedStatement ps = null;

		try
		{
			// Delete document- no more copies left
			String document_delete = String.format(
				"DELETE FROM %s WHERE document_id = %s",
				DOCUMENT_TABLE, pdc.getDoc_id());

			ps = dbConnection.prepareStatement(document_delete);
			rowsAffected = 0;
			rowsAffected = ps.executeUpdate();

			if (rowsAffected == 0)
			{
				success = false;
			}
		} catch (SQLException e) {
			dbConnection.rollback();
			DatabaseHandler.printSQLException(e);
			success = false;
		} finally {
			if (ps != null)	try {ps.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}
		return success;
	}

	/**
	 * This method updates each borrow_pack entries corresponding to the physical copies returned by the user to
	 * the 'CheckedIn' status
	 * @param physicalCopies : AutoPopulatingList of valid (copy_no, doc_id) the user wants to give back to the library. Controller verified them already
	 * @param userId : user returning books
	 * @return
	 * @throws Exception
	 */
	public static boolean checkIn(AutoPopulatingList <PhysicalDocumentCopy> physicalCopies)
			throws Exception {

		boolean success = true;
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;

		try {
			dbConnection.setAutoCommit(false);

			/** Retrieve correct borrow_id **/
			String borrow_id_select = "SELECT borrow_id FROM " + BORROW_PACK_TABLE +
					" WHERE copy_no = ? AND document_id = ?";


			String borrow_pack_update = "UPDATE " + BORROW_PACK_TABLE +
					" SET status = 'CheckedIn'" +
					" WHERE borrow_id = ? AND copy_no = ? AND"+
					" document_id = ?";

			ps = dbConnection.prepareStatement(borrow_id_select);
			ps2 = dbConnection.prepareStatement(borrow_pack_update);
			int borrow_id = -1;

			for (PhysicalDocumentCopy pcd: physicalCopies){

				ps.setInt(1, pcd.getCopy_no());
				ps.setInt(2, pcd.getDoc_id());
				results = ps.executeQuery();

				if (results.next())
					borrow_id = results.getInt("borrow_id");


				ps2.setInt(1, borrow_id);
				ps2.setInt(2, pcd.getCopy_no());
				ps2.setInt(3, pcd.getDoc_id());
				ps2.addBatch();
			}

			ps2.executeBatch();
			dbConnection.commit();

		} catch (SQLException e) {
			dbConnection.rollback();
			success = false;
			DatabaseHandler.printSQLException(e);
		} finally {
			if (ps != null)	try {ps.close();} catch (Exception ignored) {;}
			if (ps2 != null)	try {ps2.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}

		return success;
	}

	// to be implemented?
	public static boolean isBlackListed(String userid) {
		return false;
	}

	public static boolean isReserved(int copyNum, int docId, String userid) {
		return false;
	}

	/**
	 * This method creates a new borrow_id in the borrow table, and one or more borrow_pack entries related
	 * to the new borrow_id for each of the physical copies borrowed by the user
	 * @param physicalCopies : AutoPopulatingList of physical copies (copy_no + document_id). CONTROLLER have already verified whether these copies are available or not
	 * @param userId : userId borrowing the copies
	 * @return : True if successful
	 * @throws Exception
	 */
	public static boolean checkOut(AutoPopulatingList<PhysicalDocumentCopy> physicalCopies, String userId)
			throws Exception {

		boolean success = true;
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		PreparedStatement ps = null;

		try {
			// Multi-transaction
			dbConnection.setAutoCommit(false);


			int borrow_id = -1;
			/** Insert new borrow field **/
			String borrow_add = "INSERT INTO " + BORROW_TABLE + " (lib_user_id, borrow_date, date_to_return) VALUES(?,?,?)";
			ps = dbConnection.prepareStatement(borrow_add,
					Statement.RETURN_GENERATED_KEYS);
			java.util.Date today = new java.util.Date();
			ps.setString(1, userId);
			ps.setDate(2, new java.sql.Date(today.getTime()));
			ps.setDate(3, new java.sql.Date(today.getTime() + 604800000));
			ps.executeUpdate();

			results = ps.getGeneratedKeys();
			if (results.next()) {
				borrow_id = results.getInt(1);
			}

			String borrow_pack_add = "INSERT INTO " + BORROW_PACK_TABLE + " (borrow_id, copy_no, document_id, status) VALUES(?,?,?,?)";
			ps = dbConnection.prepareStatement(borrow_pack_add);

			for (PhysicalDocumentCopy pdc: physicalCopies){
				ps.setInt(1, borrow_id);
				ps.setInt(2, pdc.getCopy_no());
				ps.setInt(3, pdc.getDoc_id());
				ps.setString(4, "CheckedOut");
				ps.addBatch();
			}
			ps.executeBatch();
			dbConnection.commit();

		} catch (SQLException e) {
			dbConnection.rollback();
			success = false;
			DatabaseHandler.printSQLException(e);
		}
		// throw e;}
		finally {
			if (ps != null)	try {ps.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}if (dbConnection != null)
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}
		return success;

	}

	/**
	 *	This method returns all journal titles in the database if any.
	 * @return ArrayList of journal title in String
	 * @throws Exception DB connection failure
	 */
	public static ArrayList<String> listJournalTitles() throws Exception {
		return DatabaseHandler.listAttributes(JOURNAL_TABLE, "journal_title");
	}

	/**
	 *	This method returns all journal types in the database if any.
	 * @return ArrayList of journal title in String
	 * @throws Exception DB connection failure
	 */
	public static ArrayList<String> listJournalTypes() throws Exception {
		return DatabaseHandler.listAttributes(JOURNAL_TYPE_TABLE, "journal_type");
	}

	/**
	 *	This method returns all book categories in the database if any.
	 * @return ArrayList of journal title in String
	 * @throws Exception DB connection failure
	 */
	public static ArrayList<String> listBookCategories() throws Exception {
		return DatabaseHandler.listAttributes(BOOK_CATEGORY_TABLE, "category");
	}


	/**
	 * This method takes a Book instance which contains
	 * possible pre-defined fields of a book, and search in the
	 * database if such field specifications exist. If so, it returns
	 * a list of books that matched the conditions. Else it returns an
	 * empty list.
	 * @param bookSearchInfo
	 * @return ArrayList of Book
	 * @throws Exception DB connection failure
	 */
	//TODO: use preparedStatement
	public static ArrayList<Book> searchBooks(Book bookSearchInfo) throws Exception {
		ResultSet results = null;
		Statement select = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			StringBuffer query =  new StringBuffer("SELECT * FROM "
					+ DOCUMENT_TABLE + " d, "
					+ BOOK_TABLE 	+ " b, "
					+ AUTHOR_TABLE + " a, "
					+ AUTHOR_WRITES_TEXT_TABLE + " awt, "
					+ PUBLISHER_TABLE + " p "
					+ "WHERE d.document_id = b.book_id AND "
					+ "b.book_id = awt.book_id AND "
					+ "a.author_id = awt.author_id AND p.publisher_id = b.publisher_id ");

			if(!bookSearchInfo.getTitle().equals("")){
				query.append(" AND d.title LIKE '%" + bookSearchInfo.getTitle() + "%'");
			}
			if(!bookSearchInfo.getCategory().equals("")){
				query.append(" AND b.category = '" + bookSearchInfo.getCategory() + "'");
			}
			if(!bookSearchInfo.getISBN().equals("")){
				query.append(" AND b.ISBN = '" + bookSearchInfo.getISBN() + "'");
			}
			if(!bookSearchInfo.getPublisherName().equals("")){
				query.append(" AND p.corporate_name LIKE '%" + bookSearchInfo.getPublisherName() + "%'");
			}
			if(bookSearchInfo.getEdition() != 0){
				query.append(" AND b.edition = " + bookSearchInfo.getEdition());
			}
			if(bookSearchInfo.getYear() != 0){
				query.append(" AND b.year = " + bookSearchInfo.getYear());
			}
			if(!bookSearchInfo.getAuthorNamesArray().isEmpty()){

				query.append(" AND (");
				for(String author : bookSearchInfo.getAuthorNamesArray()){
					query.append( " a.author_name LIKE '%" + author + "%' OR");
				}
				query = new StringBuffer(query.substring(0, query.length() - 2));
				query.append(")");
			}

			query.append(" ORDER BY d.document_id, d.title, b.year, b.edition");

			select = dbConnection.createStatement();
			//System.out.println(query);
			results = select.executeQuery(query.toString());

			int tmp_book_id = -999; // dummy value
			int i = -1; // start at -1 b/c if first time success, it will be
						// incremented to 0
			while (results.next()) {
				int book_id = results.getInt("document_id");
				if (book_id == tmp_book_id) {
					books.get(i).setAuthorNames(
							results.getString("author_name"));
				} else {
					tmp_book_id = book_id;
					Book book = new Book();
					book.setDoc_id(tmp_book_id);
					book.setAuthorNames(results.getString("author_name"));
					book.setEdition(results.getInt("edition"));
					book.setISBN(results.getString("ISBN"));
					book.setPublisherName(results.getString("corporate_name"));
					book.setQty(results.getInt("quantity"));
					book.setTitle(results.getString("title"));
					book.setYear(results.getInt("year"));
					book.setCategory(results.getString("category"));
					books.add(book);
					i++;
				}
			}
		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
		} finally {
			if (select != null)	try {select.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}

		return books;
	}

	/**
	 *	This method returns all journals in the database if any.
	 * @return ArrayList of journal title in String
	 * @throws Exception DB connection failure
	 */
	public static ArrayList<Journal> listJournals() throws Exception {
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		ArrayList<Journal> journals = new ArrayList<Journal>();

		try{
			String query = "SELECT *  FROM " + JOURNAL_TABLE;

			Statement select = dbConnection.createStatement();
			results = select.executeQuery(query);
			 while(results.next()){
				 journals.add(new Journal(results.getInt("journal_id"),
						 results.getString("journal_title"),
						 results.getString("journal_type"),
						 results.getInt("publisher_id")));
			 }
		}catch (SQLException e) {
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
		return journals;
	}



	/**
	 * This method takes a JournalIssue instance which contains
	 * possible pre-defined fields of an issue, and search in the
	 * database if such field specifications exist. If so, it returns
	 * a list of issues that matched the conditions. Else it returns an
	 * empty list
	 * @param issueSearchInfo
	 * @return ArrayList of JournalIssue
	 * @throws Exception DB connection failure
	 */
	//TODO: use preparedStatement
	public static ArrayList<JournalIssue> searchJournalIssues(JournalIssue issueSearchInfo) throws Exception{
		ResultSet results = null;
		Statement select = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		ArrayList<JournalIssue> journalIssues = new ArrayList<JournalIssue>();

		try {
			StringBuffer query = new StringBuffer("SELECT * FROM "
					+ DOCUMENT_TABLE
					+ " d, "
					+ ISSUE_TABLE
					+ " i, "
					+ VOLUME_TABLE + " v, "
					+ JOURNAL_TABLE + " j, "
					+ PUBLISHER_TABLE + " p "
					+ "WHERE d.document_id = i.journal_issue_id "
					+ "AND i.year = v.year "
					+ "AND i.volume_no = v.volume_no "
					+ "AND i.journal_id = v.journal_id "
					+ "AND v.journal_id = j.journal_id "
					+ "AND p.publisher_id = j.publisher_id ");

			if(issueSearchInfo.getJournalID() != 0){
				query.append(" AND j.journal_id = " + issueSearchInfo.getJournalID());
			}
			if(!issueSearchInfo.getTitle().equals("")){
				query.append(" AND d.title LIKE '%" + issueSearchInfo.getTitle() + "%' ");
			}
			if(!issueSearchInfo.getISSN().equals("")){
				query.append(" AND i.ISSN = '" + issueSearchInfo.getISSN() + "' ");
			}
			if(!issueSearchInfo.getPublisherName().equals("")){
				query.append(" AND p.corporate_name LIKE '%" + issueSearchInfo.getPublisherName() + "%' ");
			}
			if(!issueSearchInfo.getJournal_title().equals("")){
				query.append(" AND j.journal_title LIKE '%" + issueSearchInfo.getJournal_title() + "%' ");
			}
			if(!issueSearchInfo.getCategory().equals("")){
				query.append(" AND j.journal_type = '" + issueSearchInfo.getCategory() + "' ");
			}
			if(issueSearchInfo.getVolume_no() != 0){
				query.append(" AND v.volume_no = " + issueSearchInfo.getVolume_no() + " ");
			}
			if(issueSearchInfo.getYear() != 0){
				query.append(" AND v.year = " + issueSearchInfo.getYear() + " ");
			}
			if(!issueSearchInfo.getMonth().equals("")){
				query.append(" AND i.month = '" + issueSearchInfo.getMonth() + "' ");
			}

			query.append(" ORDER BY d.document_id, d.title, j.journal_title, "
					+ "v.year, v.volume_no, i.year, i.month, i.journal_issue_id ");

			select = dbConnection.createStatement();
			//System.out.println(query);
			results = select.executeQuery(query.toString());

			while (results.next()) {
				JournalIssue journalIssue = new JournalIssue();
				journalIssue.setDoc_id(results.getInt("document_id"));
				journalIssue.setISSN((results.getString("ISSN")));
				journalIssue.setPublisherName(results
						.getString("corporate_name"));
				journalIssue.setJournal_title(results
						.getString("journal_title"));
				journalIssue.setYear(results.getInt("year"));
				journalIssue.setMonth(results.getString("month"));
				journalIssue.setVolume_no(results.getInt("volume_no"));
				journalIssue.setCategory(results
						.getString("journal_type"));
				journalIssue.setQty(results.getInt("quantity"));
				journalIssue.setTitle(results.getString("title"));
				journalIssues.add(journalIssue);
			}
		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
		} finally {
			if (select != null)	try {select.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}

		return journalIssues;
	}

	public static Book retrieveBook(String serialNumber) throws Exception {
		Book book = new Book();
		ResultSet results = null;
		PreparedStatement select = null;
		Connection dbConnection = null;
		String query = "SELECT * FROM "
				+ DOCUMENT_TABLE + " d, "
				+ BOOK_TABLE 	+ " b, "
				+ AUTHOR_TABLE + " a, "
				+ AUTHOR_WRITES_TEXT_TABLE + " awt, "
				+ PUBLISHER_TABLE + " p "
				+ "WHERE d.document_id = b.book_id AND "
				+ "b.book_id = awt.book_id AND "
				+ "a.author_id = awt.author_id AND p.publisher_id = b.publisher_id AND "
				+ "b.ISBN = ? ";
		try{
			dbConnection = DatabaseHandler.getDBConnection();
			select = dbConnection.prepareStatement(query);
			select.setString(1, serialNumber);
			results = select.executeQuery();
			if(results.next()){
				book.setDoc_id(results.getInt("book_id"));
				book.setCategory(results.getString("category"));
				book.setAuthorNames(results.getString("author_name"));
				book.setEdition(results.getInt("edition"));
				book.setISBN(results.getString("ISBN"));
				book.setPublisherName(results.getString("corporate_name"));
				book.setQty(results.getInt("quantity"));
				book.setTitle(results.getString("title"));
				book.setYear(results.getInt("year"));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (select != null)	try {select.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}
		return book;
	}

	public static JournalIssue retrieveJournalIssue(String serialNumber) throws Exception{
		JournalIssue journalIssue = new JournalIssue();
		ResultSet results = null;
		PreparedStatement select = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		String query = "SELECT * FROM "
				+ DOCUMENT_TABLE
				+ " d, "
				+ ISSUE_TABLE
				+ " i, "
				+ "Volume v, Journal j, publisher p "
				+ "WHERE d.document_id = i.journal_issue_id "
				+ "AND i.volume_no = v.volume_no "
				+ "AND i.journal_id = v.journal_id "
				+ "AND v.journal_id = j.journal_id "
				+ "AND p.publisher_id = j.publisher_id AND "
				+ "i.ISSN = ? ";
		try{
			select = dbConnection.prepareStatement(query);
			select.setString(1, serialNumber);
			results = select.executeQuery();
			if(results.next()){
				journalIssue.setDoc_id(results.getInt("document_id"));
				journalIssue.setISSN((results.getString("ISSN")));
				journalIssue.setPublisherName(results
						.getString("corporate_name"));
				journalIssue.setJournal_title(results
						.getString("journal_title"));
				journalIssue.setYear(results.getInt("year"));
				journalIssue.setMonth(results.getString("month"));
				journalIssue.setVolume_no(results.getInt("volume_no"));
				journalIssue.setCategory(results
						.getString("journal_type"));
				journalIssue.setQty(results.getInt("quantity"));
				journalIssue.setTitle(results.getString("title"));
			}
		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
		} finally {
			if (select != null)	try {select.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}
		return journalIssue;
	}

	public static boolean ISBNexists(String ISBN) throws Exception{
		return DatabaseHandler.exists(BOOK_TABLE, "ISBN", ISBN);
	}

	public static boolean ISSNexists(String ISSN) throws Exception{
		return DatabaseHandler.exists(ISSUE_TABLE, "ISSN", ISSN);
	}

	public static boolean isExistingCopy(PhysicalDocumentCopy pdc) throws Exception{

		boolean success = false;
		ResultSet results = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		PreparedStatement ps = null;
		/** Check whether Copy exists in the borrow_pack table **/

		String copy_select = "SELECT * FROM " + BORROW_PACK_TABLE + " b, " + COPY_TABLE
								+ " c WHERE b.document_id = c.document_id AND c.copy_no = ?"
								+ " AND c.document_id = ?";
		try{
			ps = dbConnection.prepareStatement(copy_select);
			ps.setInt(1, pdc.getCopy_no());
			ps.setInt(2,  pdc.getDoc_id());
			results = ps.executeQuery();
			if (results.next())
				success = true;
		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
		} finally {
			if (ps != null)	try {ps.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}
		return success;
    }

	public static boolean isAvailableCopy(PhysicalDocumentCopy pdc) throws Exception{
        String[] fields = {"copy_no", "document_id", "status"};
        String[] values = {pdc.getCopy_no() + "", pdc.getDoc_id() + "", "Available"};
        return DatabaseHandler.exists(COPY_TABLE, fields, values);
    }

	/**
	 * This method returns the list of copies of a particular document
	 * Each copy contains copy information (copy_no, doc_id, status, location)
	 * @param doc_id
	 * @return ArrayList of PhysicalDocumentCopy
	 * @throws Exception
	 */
	public static ArrayList<PhysicalDocumentCopy> getDocumentCopies(int doc_id) throws Exception{
		ResultSet results = null;
		PreparedStatement select = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		ArrayList<PhysicalDocumentCopy> pdcs = new ArrayList<PhysicalDocumentCopy>();

		try{
			StringBuffer query = new StringBuffer("SELECT * FROM " +
									COPY_TABLE + " c " +
									"WHERE c.document_id = ? " +
									" ORDER BY c.document_id, c.copy_no");
			select = dbConnection.prepareStatement(query.toString());
			select.setInt(1, doc_id);
			//System.out.println(query);
			results = select.executeQuery();

			while(results.next()){
				PhysicalDocumentCopy pdc = new PhysicalDocumentCopy();
				pdc.setCopy_no(results.getInt("copy_no"));
				pdc.setDoc_id(results.getInt("document_id"));
				pdc.setStatus(results.getString("status"));
				pdc.setLocation(results.getString("cp_loc_id"));
				pdcs.add(pdc);
			}
		}catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
		} finally {
			if (select != null)	try {select.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}

		return pdcs;
	}

	public static ArrayList<Document> merge( ArrayList<Book> l1,  ArrayList<JournalIssue>l2){
		ArrayList<Document> list = new ArrayList<Document>();
		list.addAll(l1);
		list.addAll(l2);
		return list;
	}

	public static ArrayList<Document> sort(ArrayList<Document> docs, String sorting) {
		if(sorting == null){
			return docs;
		}
		if(sorting.equalsIgnoreCase("title")){
			return Document.sortByTitle(docs);
		}
		if(sorting.equalsIgnoreCase("type")){
			return Document.sortByType(docs);
		}
		if(sorting.equalsIgnoreCase("category")){
			return Document.sortByCategory(docs);
		}
		if(sorting.equalsIgnoreCase("quantity")){
			return Document.sortByQuantity(docs);
		}
		if(sorting.equalsIgnoreCase("publisher")){
			return Document.sortByPublisher(docs);
		}
		return docs;
	}

	public static ArrayList<Borrow> viewBorrowedItems(String userId) throws Exception{
		ArrayList<Borrow> borrowList = new ArrayList<Borrow>();

		ResultSet results = null;
		Statement select = null;
		Connection dbConnection = DatabaseHandler.getDBConnection();
		String book_query = "SELECT * FROM " + BORROW_TABLE + " b, " +
										  BORROW_PACK_TABLE + " t, " +
										  DOCUMENT_TABLE + " d, " +
										  BOOK_TABLE + " bo " +
		               "WHERE b.lib_user_id = '" + userId +"' " +
				       "AND b.borrow_id = t.borrow_id " +
		               "AND t.document_id = d.document_id " +
				       "AND bo.book_id = d.document_id";

		String journal_query = "SELECT * FROM " + BORROW_TABLE + " b, " +
											  BORROW_PACK_TABLE + " t, " +
											  DOCUMENT_TABLE + " d, " +
											  ISSUE_TABLE + " bo " +
							"WHERE b.lib_user_id = '" + userId +"' " +
							"AND b.borrow_id = t.borrow_id " +
							"AND t.document_id = d.document_id " +
						    "AND bo.journal_issue_id = d.document_id";
		try{

			select = dbConnection.createStatement();
			results = select.executeQuery(book_query);

			while (results.next()) {
				Borrow borrowEntry = new Borrow();
				borrowEntry.setCheckoutDate(results.getDate("borrow_date"));
				borrowEntry.setReturnDate(results.getDate("date_to_return"));
				borrowEntry.setStatus(results.getString("status"));
				borrowEntry.setDoc_title(results.getString("title"));
				borrowEntry.setDoc_type("Book");
				borrowEntry.setDoc_serialNumber(results.getString("ISBN"));
				borrowList.add(borrowEntry);
			}

			results = select.executeQuery(journal_query);

			while (results.next()) {
				Borrow borrowEntry = new Borrow();
				borrowEntry.setCheckoutDate(results.getDate("borrow_date"));
				borrowEntry.setReturnDate(results.getDate("date_to_return"));
				borrowEntry.setStatus(results.getString("status"));
				borrowEntry.setDoc_title(results.getString("title"));
				borrowEntry.setDoc_type("Journal");
				borrowEntry.setDoc_serialNumber(results.getString("ISSN"));
				borrowList.add(borrowEntry);
			}

		} catch (SQLException e) {
			DatabaseHandler.printSQLException(e);
		} finally {
			if (select != null)	try {select.close();} catch (Exception ignored) {;}
			if (results != null) try {results.close();} catch (Exception ignored) {;}
			if (dbConnection != null) try {dbConnection.close();} catch (Exception ignored) {;}
		}
		return Borrow.sortByStatus(borrowList);

	}

}
