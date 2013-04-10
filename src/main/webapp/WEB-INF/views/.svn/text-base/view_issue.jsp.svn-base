<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="View Journal Issue" />
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
					<c:when test="${issue_dexists}">
                    	<div class="alert alert-error"><h1>Issue Not Found!</h1><h3>Are you sure you typed the link correctly?</h3></div>
    				</c:when>
    				<c:otherwise>
						<div class="span4">
							<div class="book">
								<h1><c:out value="${display_issue.title} (${display_issue.journal_title}, Vol. ${display_issue.volume_no}, ${display_issue.month} ${display_issue.year})"/></h1>
								<h5>
									Category:
									<c:out value="${display_issue.category}"/>
								</h5>
								<h5>
									Publisher:
									<c:out value="${display_issue.publisherName}"/>
								</h5>
							</div>
						</div>
						<div class="span1"></div>
						<div class="span5">
								<div class="row"><h2>Library Availability</h2>
												<br>
												<h5>Copies Owned: ${display_issue.qty}</h5>
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
										          		 	<a href="/issue/${display_issue.ISSN}/${copy.copy_no}/delete" class="btn btn-danger ">Remove Copy</a>
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
								<a href="/issue/${display_issue.ISSN}/add" class="btn btn-large btn-primary ">Add Copy</a>
							 </c:if>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			
		</div>	
		
	
	</div>
	<jsp:include page="../views_ext/footer.jsp" />