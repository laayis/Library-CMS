<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="Check out a Journal/Issue" />
</jsp:include>

	<c:choose>
		<c:when test="${not empty success}">
			<div class="alert alert-success center">${success}</div>
		</c:when>
		<c:when test="${not empty error}">
			<div class="alert alert-error center">${error}</div>
		</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
	<hr />
	<div class="container">
		<div class="row">
			<div class="span12">

				<select id="itemChoice">
					<option value="book" <c:if test="${selected eq 'book'}">selected="selected"</c:if>>Add Book</option>
					<option value="journal" <c:if test="${selected eq 'journal'}">selected="selected"</c:if>>Add Journal Issue</option>
					<option value="addCopy" <c:if test="${selected eq 'addCopy'}">selected="selected"</c:if>>Add Copy</option>
					<option value="deleteCopy" <c:if test="${selected eq 'delCopy'}">selected="selected"</c:if>>Delete Copy</option>
				</select>
				
				<!-- Book Form -->
				<form class="form-horizontal login-form" action="addBook"
					id="addBookForm" method="post" style="display:none;">
					<fieldset>
					<legend>Add a Book below:</legend>
						<div class="control-group">
							<label class="control-label" for="title">Book Title</label>
							<div class="controls">
								<input type="text" name="title" placeholder="Book Title" value="${book.title}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="author">Author</label>
							<div class="controls">
								<input type="text" name="authorNames" placeholder="Author" value="${book.authorNames}">
							</div>
						</div>						
						<div class="control-group">
							<label class="control-label" for="category">Category</label>
							<div class="controls">
								<select name="category" class="fillone">
										<c:forEach items="${bookCategories}" var="bookCategory">
											<option value="${bookCategory}">${bookCategory}</option>								
										</c:forEach>							
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="publisher">Publisher</label>
							<div class="controls">
								<input type="text" name="publisherName" placeholder="Publisher" value="${book.publisherName}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="ISBN">ISBN</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="ISBN"
									name="ISBN" placeholder="ISBN" value="${book.ISBN}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="year">Year</label>
							<div class="controls">
								<input type="text" min="0" class="input-xlarge" id="year"
									name="year" placeholder="Year" value="${book.year}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="edition">Edition</label>
							<div class="controls">
								<input type="number" min="1" class="input-xlarge" id="edition"
									name="edition"  placeholder="1" value="${book.edition}">
							</div>
						</div>
						
						<div class="control-group">
                            <label class="control-label" for="qty">Number of Copies</label>
                            <div class="controls">
                                <input type="number" min="0" name="qty" placeholder="Quantity" value="${book.qty}">
                            </div>
                        </div>
                        
						<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Add Book</button>
								<a href="user_page" role="button" class="btn btn-primary">Cancel</a>
							</div>
						</div>
					</fieldset>
				</form>
				
				<!-- Journal Form -->
				<form class="form-horizontal login-form" action="addIssue"
					id="addIssueForm" method="post" style="display:none;">
					<fieldset>
					<legend>Add A Journal/Journal Issue below:</legend>
						<div class="control-group">
							<label class="control-label" for="jtitle">Journal</label>
							<div class="controls">
								<select name="journalID" class="fillone"
									id="selectedJournal">
										<option selected value="-1">Add New Journal</option>
										<c:forEach items="${journals}" var="journal">
											<option value="${journal.journalID}"><c:out value="${journal.journalTitle} (of category: ${journal.journalCategory})"/></option>								
										</c:forEach>					
								</select>
							</div>
						</div>
						
					<div id="journal_data">
						<div class="control-group">
							<label class="control-label" for="journal_title">Journal Title</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="journal_title"
									name="journal_title" placeholder="Title" value="${issue.title}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="journal_title">Publisher</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="journalPublisherName"
									name="publisherName" placeholder="Publisher Name" value="${issue.publisherName}">
							</div>
						</div>
						
						
						<div class="control-group">
							<label class="control-label" for="category">Category</label>
							<div class="controls">
								<select name="journalType" class="fillone">
										<c:forEach items="${journal_types}" var="journalType">
											<option value="${journalType}">${journalType}</option>								
										</c:forEach>							
								</select>
							</div>
						</div>
						
					</div>

					<div class="control-group">
						<label class="control-label" for="qty">Volume Number</label>
						<div class="controls">
							<input type="number" min="1" name="volume_no" placeholder="Volume Number"
								value="${issue.volume_no}">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="ISSN">ISSN</label>
						<div class="controls">
							<input type="text" class="input-xlarge" id="ISSN" name="ISSN"
								placeholder="ISSN" value="${issue.ISSN}">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="issue_title">Issue Title</label>
						<div class="controls">
							<input type="text" name="issue_title" placeholder="Issue Title"
								id="issue_title" value="${issue.issue_title}">
						</div>
					</div>

					<div class="control-group">
							<label class="control-label" for="year">Year</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="year"
									name="year" placeholder="Year" value="${issue.year}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="edition">Month</label>
							<div class="controls">
									<select name="month" class="fillone">
										<!-- To be replaced with Database enumeration -->
										<option selected="0" value="1">January</option>
										<option value="2">February</option>
										<option value="3">March</option>
										<option value="4">April</option>
										<option value="5">May</option>
										<option value="6">June</option>
										<option value="7">July</option>
										<option value="8">August</option>
										<option value="9">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>					
									</select>
							</div>
						</div>
																		
						<div class="control-group">
                            <label class="control-label" for="qty">Number of Copies</label>
                            <div class="controls">
                                <input type="number" min="0" name="qty" placeholder="Quantity" value="${issue.qty}">
                            </div>
                        </div>
						
						<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Add Issue</button>
								<a href="user_page" role="button" class="btn btn-primary">Cancel</a>
							</div>
						</div>
					</fieldset>
				</form>
				
				<!-- Add Copy Form -->
				<form class="form-horizontal login-form addDelForm" action="addCopy"
					id="addCopyForm" method="post" style="display:none;">
					<fieldset>
					<legend>Add copies of a Book/Journal Issue below:</legend>
						<div class="control-group">
							<label class="control-label" for="isbn">ISBN / ISSN</label>
							<div class="controls">
								<input type="text" name="isbn" placeholder="ISBN / ISSN" value="${isbn}">
							</div>
						</div>
						
						 <div class="control-group">
                            <label class="control-label" for="qty">Number of copies added</label>
                            <div class="controls">
                                <input type="text" name="qty" placeholder="Quantity" value="${qty}">
                            </div>
                        </div>
						
						<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Add Copies</button>
								<a href="user_page" role="button" class="btn btn-primary">Cancel</a>
							</div>
						</div>
					</fieldset>
				</form>
				
				<!-- Delete Copy Form -->
				<form class="form-horizontal login-form addDelForm" action="deleteCopy"
					id="deleteCopyForm" method="post" style="display:none;">
					<fieldset>
					<legend>Delete Book/Journal Issue copies below:</legend>
						<div class="control-group">
							<label class="control-label" for="isbn">ISBN / ISSN</label>
							<div class="controls">
								<input type="text" name="isbn" placeholder="ISBN / ISSN" value="${isbn}">
							</div>
						</div>
						
						 <div class="control-group">
                            <label class="control-label" for="copy_no">Copy Number</label>
                            <div class="controls">
                                <input type="text" name="copy_no" placeholder="Copy Number" value="${copy_no}">
                            </div>
                        </div>
						
						<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Delete Copies</button>
								<a href="user_page" role="button" class="btn btn-primary">Cancel</a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		
	</div>
	<jsp:include page="../views_ext/footer.jsp" />

