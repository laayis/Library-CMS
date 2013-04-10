<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="Check out a Journal/Book" />
</jsp:include>
	<hr />
	<div class="container">
		
		<div class="row">
		<c:if test ="${logged_user.role eq 'Librarian' || logged_user.role eq 'Admin'}">
			<c:choose>
				<c:when test="${not empty message}">
					<div class="alert alert-success center">${message}</div>
				</c:when>
				<c:when test="${not empty error}">
					<div class="alert alert-error center">${error}</div>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</c:if>
		</div>
		<div class="row margintopsmall">
				<c:choose>
					<c:when test="${book_dexists}">
                    	<div class="alert alert-error"><h1>Book Not Found!</h1><h3>Are you sure you typed the link correctly?</h3></div>
    				</c:when>
    				<c:otherwise>
						<div class="span4">
							<div class="book">
								<h1>${display_book.title}</h1>
								<h5>
									Author(s):
									<c:forEach items="${display_book.authorNames}" var="curAuthor">
										<c:out value="${curAuthor}" />
										<br />
									</c:forEach>
								</h5>
							</div>
						</div>
						<div class="span1"></div>
						<div class="span5">
								<div class="row"><h2>Library Availability</h2>
												<br>
												<h5>Copies Owned: ${display_book.qty}</h5>
								</div>
								<div class ="row">
									<h5>Library Doc ID : ${display_book.doc_id} </h5>
								</div>
								<div class = "row">
									<c:if test="${not empty docCopies}">
										<table class="table  table-striped">  
								        <thead>  
								          <tr>  
								            <th>Copy Number</th>  
								            <th>Location</th>  
								            <th>Availability</th>  
								            <c:if test ="${logged_user.role eq 'Librarian' || logged_user.role eq 'Admin'}">
										          		<th>Edit Copy</th>  
										          		
										    </c:if>
								          </tr>  
								        </thead>
								         <tbody> 
								            <c:forEach items="${docCopies}" var="copy">
										        <tr>
										        
										          <td><c:out value="${copy.copy_no}" /></td>
										          <td><c:out value="${copy.location}" /></td>
										          <td><c:out value="${copy.status}" /></td>
										          <c:if test ="${logged_user.role eq 'Librarian' || logged_user.role eq 'Admin'}">
										          		 <td>
										          		 	<a href="/book/${display_book.ISBN}/${copy.copy_no}/delete" class="btn btn-danger ">Remove Copy</a>
										          		 </td>
										          		
										          </c:if>
										        </tr>
										      </c:forEach>
								        </tbody>  
								      </table>  
													
									</c:if>
							</div>	
							<div class="row">
							 <c:if test ="${logged_user.role eq 'Librarian' || logged_user.role eq 'Admin'}">
								<a href="/book/${display_book.ISBN}/add" class="btn btn-large btn-primary ">Add Copy</a>
							 </c:if>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			
		</div>	
		
	
	</div>
	<jsp:include page="../views_ext/footer.jsp" />