<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp">
	<jsp:param name="title" value="Delete Librarians" />
</jsp:include>
<hr />
<div class="container">
	<div class="span12">
		<div class="row">
			<h2>Delete Librarians</h2>
			<hr />
		</div>

		<c:if test="${not empty success}">
			<div class="alert alert-success center">${success}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-error center">${error}</div>
		</c:if>

		<c:choose>
			<c:when test="${not empty librarians}">
				<form action="/admin/deleteLibrarians" id="deleteLibrariansForm"
					method="post" onsubmit="return confirm('Are you sure you want to delete those librarians?');">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Delete?</th>
								<th>Username</th>
								<th>First Name</th>
								<th>Last Name</th>
							</tr>
						</thead>
						<tbody>
							<!-- Use JSTL to loop over the list of books and journals -->
							<c:set var="count" value="0" scope="page"/>
							<c:forEach items="${librarians}" var="current">
								<tr>
									<td><label class="checkbox"> <input name="librarians[${count}]"
											type="checkbox" value="${current.username}">
									</label></td>
									<td><c:out value="${current.username}" /></td>
									<td><c:out
											value="${empty current.firstName ?  'N/A': current.firstName}" /></a></td>
									<td><c:out
											value="${empty current.lastName ?  'N/A': current.lastName}" />
									</td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</tbody>
					</table>

					<div class="offset4 btn-group-wrap">
						<div class="btn-group">
							<button type="submit" class="btn btn-danger">Delete
								Librarians</button>
						</div>
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<p>The library currently has no librarians.</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</div>
<jsp:include page="../views_ext/footer.jsp" />

