<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="View All Users (Admin)" />
</jsp:include>
	<hr />
	<div class="container">

		
			<div class="span12">
			<div class="row">
				<h2>Library Users</h2>
				<hr/>
			</div>
				
				<table class="table table-hover">
			
					<thead>
						<tr>
							<th> <a href="/list/users?sort=username"> Username </a></th>
							<th> <a href="/list/users?sort=email"> Email </a> </th>
							<th> <a href="/list/users?sort=firstname"> First Name </a> </th>
							<th> <a href="/list/users?sort=lastname"> Last Name </a> </th>						
							<th> <a href="/list/users?sort=address"> Address </a> </th>
							<th> <a href="/list/users?sort=phone1"> Phone </a> </th>
							<c:if test="${logged_user.role == 'Admin'}"><th> <a href="/list/users?sort=role"> Role </a> </th></c:if>
						</tr>
					</thead>
					<tbody>
						<!-- Use JSTL to loop over the list of books and journals -->
						<c:if test="${logged_user.role == 'Librarian'}">
						<c:forEach items="${libraryUsers}" var="current">
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
						<c:forEach items="${libraryUsers}" var="current">
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
				<!--
				<div class="pagination btn-group-wrap">
					<ul>
						<li><a href="#">Prev</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">Next</a></li>
					</ul>
				</div>   -->
			</div>
		</div>
	</div>
	<jsp:include page="../views_ext/footer.jsp" />

