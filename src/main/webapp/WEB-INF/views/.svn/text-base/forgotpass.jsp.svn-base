<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="Reset your forgotten password" />
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

				
				<!-- Book Form -->
				<form class="form-horizontal login-form" action="forgotPass"
					id="forgotPassForm" method="post">
					<fieldset>
					<legend>Request a temporary password for your account below</legend>
						<div class="control-group">
							<label class="control-label" for="username">Username</label>
							<div class="controls">
								<input type="text" name="username" placeholder="Username" value="${username}">
							</div>
						</div>
						       
						<div class="control-group">
							<label class="control-label" for="email">Email</label>
							<div class="controls">
								<input type="text" name="email" placeholder="Email" value="${email}">
							</div>
						</div>
						                        
						<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Reset Password</button>
								<a href="login" role="button" class="btn btn-primary">Cancel</a>
							</div>
						</div>
					</fieldset>
				</form>
				
			</div>
		</div>
		
	</div>
	<jsp:include page="../views_ext/footer.jsp" />

