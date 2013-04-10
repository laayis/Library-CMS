<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="Login To Hululu Library" />
</jsp:include>


	<hr/>
	<!-- Login Form -->
	<div class="container">
		<c:if test="${not empty message}">
                    	<div class="alert alert-error">${message}</div>
                    </c:if>
		<div class="row">

			<div class="span12  "><h1>Log in</h1><h4>
				Don't have an account?<a href="register"> Sign up now!</a>
			</h4></div>
			

			<form class="form-horizontal" id="loginform" action="login" method="post">
				<div class="control-group">
					<label class="control-label" for="username">Username</label>
					<div class="controls">
						<input class="input-xlarge" type="text" name="inputUsername"
							placeholder="Username">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPassword">Password</label>
					<div class="controls">
						<input class="input-xlarge" type="password" name="inputPassword"
							placeholder="Password">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">

						<button type="submit" class="btn">Sign in</button>
					</div>
				</div>
				<div class="controls">
						<a href="forgot_pass" role="button" class="btn btn-primary">Forgot Password</a>
				</div>
			</form>
		</div>

	</div>

	
	<!-- Forgotten Password Modal -->
	<div id="forgotPW" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="forgotPW" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 class="login-form-heading" id="forgotPW">Reset Your Password</h3>
		</div>
		<div class="modal-body">
			<form action="forgotPass" id="loginForm" method="post">
				<legend>Request a temporary password</legend>
						<div class="control-group">
							<div class="controls">
								<input type="text" name="username" placeholder="Username" value="${username}">
							</div>
						</div>
						       
						<div class="control-group">
							<div class="controls">
								<input type="text" name="email" placeholder="Email" value="${email}">
							</div>
						</div>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary">Submit</button>
		</div>
	</div>

<jsp:include page="../views_ext/footer.jsp" />
