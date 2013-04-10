<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="Register" />
</jsp:include>

	    
	
	<hr/>
	  <div class="container">
	    <h1>Search Users</h1>
	    <hr/>
	    <h5>Refine your search by filling one or more of the fields below:</h5>
	  </div>
	<div class="container">
	   
		<div class="row">
			<div class="span12">
				<c:if test="${not empty message}">
					<div class="alert alert-error">${message}</div>
				</c:if>
				<form class="form-horizontal" action="/search/users" id="searchUserForm" method="post">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="username">Username</label>
							<div class="controls">
								<input type="text" name="username" placeholder="Username" value="${search_user.username}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="email">Email</label>
							<div class="controls">
								<input type="text" name="email" placeholder="Email" value="${search_user.email}">
							</div>
						</div>
						<c:if test="${logged_user.role == 'Admin'}">
							<div class="control-group">	
								<label class="control-label" for="email">Role</label>
									<div class="controls">
										<select name="role" class="fillone">
												<c:choose>
												<c:when test="${search_user.role=='Library User'}">
												        <option value="">Any Role</option>
														<option selected value="Library User">Library User</option>
														<option value="Librarian">Librarian</option>
												</c:when>
												<c:when test="${search_user.role=='Librarian'}">
												    <option value="">Any Role</option>
													<option value="Library User">Library User</option>
													<option selected value="Librarian">Librarian</option>
												</c:when>	
												<c:otherwise>
												    <option selected value="">Any Role</option>
													<option value="Library User">Library User</option>
													<option value="Librarian">Librarian</option>
												</c:otherwise>
											</c:choose>
											</select>
										</div>
							</div>
						</c:if>
						
						<div class="control-group">
							<label class="control-label" for="fname">First Name</label>
							<div class="controls">
								<input type="text" name="firstName" placeholder="Arnold" value="${search_user.firstName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="lname">Last Name</label>
							<div class="controls">
								<input type="text" name="lastName" placeholder="Schwarzenegger" value="${search_user.lastName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">Phone</label>
							<div class="controls">

								<input type="text" name="phone1" placeholder="5143985151" value="${search_user.phone1}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">Additional Phone</label>
							<div class="controls">
								<input type="text" name="phone2" placeholder="5143989292" value="${search_user.phone2}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="phone">Address</label>
							<div class="controls">

								<input type="text" name="address"
									placeholder="123 Street, City, Province, Country" value="${search_user.address}">
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
			
					<thead>
						<tr>
							<th> <a href="/search/users/username"> Username </a></th>
							<th> <a href="/search/users/email"> Email </a> </th>
							<th> <a href="/search/users/firstname"> First Name </a> </th>
							<th> <a href="/search/users/lastname"> Last Name </a> </th>						
							<th> <a href="/search/users/address"> Address </a> </th>
							<th> <a href="/search/users/phone1"> Phone </a> </th>
							<c:if test="${logged_user.role == 'Admin'}"><th> <a href="/search/users/role"> Role </a> </th></c:if>
						</tr>
					</thead>
					<tbody>
						<!-- Use JSTL to loop over the list of books and journals -->
						<c:if test="${logged_user.role == 'Librarian'}">
						<c:forEach items="${library_users}" var="current">
							<tr>
								
							<c:if test = "${current.role == 'Library User'}">
								<td><c:out value="${current.username}" /></td>	
								<td><c:out value="${current.email}" /></td>
							
								<td><c:out value="${empty current.firstName ?  'N/A': current.firstName}" /></a></td>
								<td><c:out value="${empty current.lastName ?  'N/A': current.lastName}" /></td>
								<td><c:out value="${empty current.address ?  'N/A': current.address}" /></td>
								<!-- Many possibilities for phone number availabilities -->
								<c:choose>
								  <c:when test="${not empty current.phone1 and empty current.phone2}">
								    <td><c:out value="${current.phone1}" /></td>
								  </c:when>
								  <c:when test="${empty current.phone1 and not empty current.phone2}">
								    <td><c:out value="${current.phone2}" /></td>
								  </c:when>
								  <c:when test="${not empty current.phone1 and not empty current.phone2}">
								    <td><c:out value="${current.phone1} / ${current.phone2}" /></td>
								  </c:when>
								  <c:otherwise>
								    <td><c:out value="N/A" /></td>
								  </c:otherwise>
								</c:choose>
							</c:if>
							</tr>
						</c:forEach>
						</c:if>
						<c:if test="${logged_user.role == 'Admin'}">
						<c:forEach items="${library_users}" var="current">
							<tr>
								
							<c:if test = "${current.role != 'Admin'}">
								<td><c:out value="${current.username}" /></td>	
								<td><c:out value="${current.email}" /></td>
							
								<td><c:out value="${empty current.firstName ?  'N/A': current.firstName}" /></a></td>
								<td><c:out value="${empty current.lastName ?  'N/A': current.lastName}" /></td>
								<td><c:out value="${empty current.address ?  'N/A': current.address}" /></td>
								<!-- Many possibilities for phone number availabilities -->
								<c:choose>
								  <c:when test="${not empty current.phone1 and empty current.phone2}">
								    <td><c:out value="${current.phone1}" /></td>
								  </c:when>
								  <c:when test="${empty current.phone1 and not empty current.phone2}">
								    <td><c:out value="${current.phone2}" /></td>
								  </c:when>
								  <c:when test="${not empty current.phone1 and not empty current.phone2}">
								    <td><c:out value="${current.phone1} / ${current.phone2}" /></td>
								  </c:when>
								  <c:otherwise>
								    <td><c:out value="N/A" /></td>
								  </c:otherwise>
								</c:choose>
								<td><c:out value="${current.role}" /></td>
							</c:if>
							</tr>
						</c:forEach>
						</c:if>
					</tbody>
				</table>
		</div>
	</div>
	<jsp:include page="../views_ext/footer.jsp" />
