<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp">
	<jsp:param name="title" value="View Borrowed Item History" />
</jsp:include>
<hr />
<div class="container">
	<div class="span12">
		<div class="row">
			<h2>Borrowed Item History</h2>
			<hr />
		</div>
		<c:choose>
			<c:when test="${empty borrowed}">
				<p>No items exist in your borrow history.</p>
			</c:when>
			<c:otherwise>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Type</th>
							<th>ISBN/ISSN</th>
							<th>Borrowed Date</th>
							<th>Due Date</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<!-- Use JSTL to loop over the list of books and journals -->
						<c:forEach items="${borrowed}" var="current">
							<tr>
								<td>
									<c:choose>
										<c:when test="${current.doc_type == 'Book'}">
											<a href="/book/<c:out value="${current.doc_serialNumber}"/>">
												<c:out value="${current.doc_title}" />
											</a>
										</c:when>
										<c:otherwise>
											<a href="/issue/<c:out value="${current.doc_serialNumber}"/>">
												<c:out value="${current.doc_title}" />
											</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:out value="${current.doc_type}" />
									<c:if test="${current.doc_type == 'Journal'}">
										<c:out value=" Issue"/>
									</c:if>
								</td>
								<td><c:out value="${current.doc_serialNumber}" /></td>
								<td><c:out value="${current.checkoutDate}" /></a></td>
								<td><c:out value="${current.returnDate}" /></a></td>
								<td><c:out value="${current.status}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</div>
<jsp:include page="../views_ext/footer.jsp" />

