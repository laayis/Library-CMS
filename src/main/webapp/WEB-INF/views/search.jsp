<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="Check out a Journal/Book" />
</jsp:include>
	<hr />
	<div class="container">
	
		<div class="span12">
			<div class="row">
				<h1>Search results for "${query}"</h1>
			</div>
			<div class="row">
			
			
	
				<c:if test="${empty documents}">
						<p>No results found.</p>
				</c:if>
				<c:if test="${not empty documents}">
				
				<table class="table table-hover">
					<caption>
						
					</caption>
					<thead>
						<tr>
							<th> <a href="/search?query=${query}&sort=title"> Title </a></th>
							<th> <a href="/search?query=${query}&sort=type"> Type </a> </th>
							<th> <a href="/search?query=${query}&sort=category"> Category </a> </th>						
							<th> <a href="/search?query=${query}&sort=publisher"> Publisher </a> </th>
							<th> <a href="/search?query=${query}&sort=quantity"> Quantity </a> </th>
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

										<c:when test = "${current.type == 'Journal'}">
											<a href="/issue/<c:out value="${current.ISSN} "/>"> 
											<c:out value="${current.title} (${current.journal_title}, Vol. ${current.volume_no}, ${current.month} ${current.year})" />
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
				</c:if>
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

