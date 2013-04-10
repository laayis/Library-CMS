<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="Hululu Library" />
</jsp:include>


	<c:if test="${not empty message}">
		<div class="alert alert-success center">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Success!</strong> ${message}
		</div>
		
	</c:if>
	<c:if test="${not empty warning}">
		<div class="alert alert-error center">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Success!</strong> ${warning}
		</div>
	</c:if>

	<hr />
	<c:if test="${not empty loggedin_mess}">
		<div class="alert alert-success center"> ${loggedin_mess}
		</div>
		
	</c:if>
	<c:if test="${empty logged_user}">
	<jsp:include page="../views_ext/home_guest.jsp" />
	</c:if>
	<c:if test="${not empty logged_user}">
	    <c:if test ="${logged_user.role eq 'Librarian'}">
		    <jsp:include page="../views_ext/home_librarian.jsp" />
		</c:if>
		<c:if test ="${logged_user.role eq 'Admin'}">
            <jsp:include page="../views_ext/home_admin.jsp" />
        </c:if>
        <c:if test ="${logged_user.role eq 'Library User'}">
            <jsp:include page="../views_ext/home_user.jsp" />
        </c:if>
	</c:if>

	<jsp:include page="../views_ext/footer.jsp" />
