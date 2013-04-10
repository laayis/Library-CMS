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
		
			<div class="span12">
				<div class="row">
					<div class="span4"><h1>Advanced Search</h1></div><div class="span4 margintopsmall"> <label class="select">IN
						<select id="search">
							
							<option value="book" <c:if test="${search == 'books'}">selected="selected"</c:if>>Books</option>
							<option value="journal" <c:if test="${search == 'journals'}">selected="selected"</c:if>>Journal Issues</option>
							
						</select>
						 </label>
						 
						 </div>
			
				</div>
				<div class="row">
				<hr/>
				<h5>Refine your search by filling one or more of the fields below:</h5>
				<!-- Book Form -->
				<form class="form-horizontal login-form" action="/advancedSearch/books"
					id="advancedSearchForm" method="post" style="display:none;">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="title">Book Title</label>
							<div class="controls">
								<input type="text" class ="fillone" name="title" placeholder="Book Title" value="${book.title}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="author">Author</label>
							<div class="controls">
								<input type="text" class="fillone" name="authorNames" placeholder="Author" value="${book.authorNames}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="category">Category</label>
							<div class="controls">
								<select name="BookCategories" class="fillone">
										<option selected value="empty">Select Category</option>
										<c:forEach items="${bookCategories}" var="bookCategory">
											<option value="${bookCategory}">${bookCategory}</option>								
										</c:forEach>							
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="publisher">Publisher</label>
							<div class="controls">
								<input type="text" class="fillone" name="publisherName" placeholder="Publisher" value="${book.publisherName}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="ISBN">ISBN</label>
							<div class="controls">
								<input type="text" class="input-xlarge fillone" id="ISBN"
									name="ISBN" placeholder="ISBN" value="${book.ISBN}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="year">Year</label>
							<div class="controls">
								<input type="number" min="0" class="input-xlarge fillone" id="year"
									name="year" placeholder="2013" value="${book.year}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="edition">Edition</label>
							<div class="controls">
								<input type="number" min="0" class="input-xlarge fillone" id="edition"
									name="edition"  placeholder="1" value="${book.edition}">
							</div>
						</div>
						
						
                        
						<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Search</button>
							</div>
						</div>
					</fieldset>
				</form>
				
				<!-- Journal Form -->
				<form class="form-horizontal login-form" action="/advancedSearch/journals"
					id=advancedSearchFormJourn method="post" style="display:none;">
					<fieldset>
											
						<div class="control-group">
							<label class="control-label" for="jtitle">Journal</label>
							<div class="controls">
								<select name="journalID" class="fillone"
									id="selectedJournal">
										<option selected value="-1">Select Journal</option>
										<c:forEach items="${journals}" var="journal">
											<option value="${journal.journalID}"><c:out value="${journal.journalTitle} (of category: ${journal.journalCategory})"/></option>								
										</c:forEach>					
								</select>
							</div>
						</div>					
						
						<div class="control-group">
							<label class="control-label" for="ititle">Issue title</label>
							<div class="controls">
								<input type="text" name="title" class = "fillone2 " placeholder="Issue title" 
									id="title" value="${issue.title}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="publisher">Publisher</label>
							<div class="controls">
								<input type="text" class="fillone" name="publisherName" placeholder="Publisher" value="${issue.publisherName}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="ISSN">ISSN</label>
							<div class="controls">
								<input type="text" class="input-xlarge fillone2" id="ISSN"
									name="ISSN" placeholder="ISSN" value="${issue.ISSN}">
							</div>
						</div>
					
						<div class="control-group">
							<label class="control-label"  for="volume_no">Volume No.</label>
							<div class="controls">
								<input type="number" min="1" name="volume_no" class = "fillone2" placeholder="Volume #" 
									id="volume_no" value="${issue.volume_no}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="year">Year</label>
							<div class="controls">
								<input type="text" class="input-xlarge fillone2"  id="year"
									name="year" placeholder="Year" value="${issue.year}">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="edition">Month</label>
							<div class="controls">
									<select name="month" class="fillone">
										<!-- To be replaced with Database enumeration -->
										<option selected value="empty">Select Month</option>
										<option value="January">January</option>
										<option value="February">February</option>
										<option value="March">March</option>
										<option value="April">April</option>
										<option value="May">May</option>
										<option value="June">June</option>
										<option value="July">July</option>
										<option value="August">August</option>
										<option value="September">September</option>
										<option value="October">October</option>
										<option value="November">November</option>
										<option value="December">December</option>					
									</select>
							</div>
						</div>
																		
						
						
						<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Search</button>
							</div>
						</div>
					</fieldset>
				</form>
				
				
			</div>
		</div>
				
				<div class="row">
				<table class="table table-hover">
					<caption>
						
					</caption>
					<thead>
						<tr>
							<th> <a href="/advancedSearch/${search}/title"> Title </a></th>
							<th> <a href="/advancedSearch/${search}/type"> Type </a> </th>
							<th> <a href="/advancedSearch/${search}/category"> Category </a> </th>						
							<th> <a href="/advancedSearch/${search}/publisher"> Publisher </a> </th>
							<th> <a href="/advancedSearch/${search}/quantity"> Quantity </a> </th>
						</tr>
					</thead>
					<tbody>
						<!-- Use JSTL to loop over the list of books and journals -->

						<c:forEach items="${documents}" var="current">
							<tr>
								<td>

									<c:choose>
										<c:when test = "${current.type == 'Book'}">
											<a href="/book/<c:out value="${current.ISBN} "/>"><c:out value="${current.title} "/>
											<c:choose>
												<c:when test="${current.edition == '1'}">
													<c:out value="(1st" />
												</c:when>
												<c:when test="${current.edition == '2'}">
													<c:out value="(2nd" />
												</c:when>
												<c:when test="${current.edition == '3'}">
													<c:out value="(3rd" />
												</c:when>
												<c:otherwise>
													<c:out value="(${current.edition}th" />
												</c:otherwise>
											</c:choose> 
											<c:out value=" Edition, ${current.year})"/>
											</a> <br/>
											Author(s): - 
											<c:forEach items="${current.authorNames}"
												var="curAuthor">
												<c:out value="${curAuthor} - " />
											</c:forEach>
										</c:when>

								<c:when test="${current.type == 'Journal'}">
									<a href="/issue/<c:out value="${current.ISSN} "/>"> <c:out
											value="${current.title} (${current.journal_title}, Vol. ${current.volume_no}, ${current.month} ${current.year})" />
									</a>
								</c:when>

							</c:choose>	
								</td>

								<td><c:out value="${current.type}" /></td>	
								<td><c:out value="${current.category}" /></a></td>
								<td><c:out value="${current.publisherName}" /></td>
								<td><c:out value="${current.qty}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
	</div>
	
	<jsp:include page="../views_ext/footer.jsp" />
	<script type="text/javascript">
	
	

	$(document).ready(function() {
		var selected  = document.getElementById("search").value;
		if(selected == 'book')
		{
			$("#advancedSearchForm").fadeIn();
			$("#advancedSearchFormJourn").hide();
		}
		else if(selected == 'journal'){

			$("#advancedSearchForm").hide();
			$("#advancedSearchFormJourn").fadeIn();
		}
		
		
		$("select").bind("change", function() {
			if ($(this).val() == "book") {
				$("#advancedSearchForm").fadeIn();
				$("#advancedSearchFormJourn").hide();
				
			} else if ($(this).val() == "journal") {
				$("#advancedSearchFormJourn").fadeIn();
				$("#advancedSearchForm").hide();
			} 

			$(".alert").hide();
		});

	});
</script>
