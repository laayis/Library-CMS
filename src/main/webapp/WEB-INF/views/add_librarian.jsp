<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="Register" />
</jsp:include>

	    
	
	<hr/>
	  <div class="container">
	    <h1>Add a Librarian</h1>
	  </div>
	<div class="container">
	   
		<div class="row">
			<div class="span12">
				<c:if test="${not empty message}">
					<div class="alert alert-error">${message}</div>
				</c:if>
				<form class="form-horizontal login-form" action="/admin/addLibrarian/" id="registerForm" method="post">
					<fieldset>
						<legend>Required Info</legend>
						<div class="control-group">
							<label class="control-label" for="username">Username</label>
							<div class="controls">
								<input type="text" name="username" placeholder="Username" value="${librarian.username}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="email">Email</label>
							<div class="controls">
								<input type="text" name="email" placeholder="Email" value="${librarian.email}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="password">Password</label>
							<div class="controls">
								<input type="password" class="input-xlarge" id="password"
									name="password" placeholder="Password">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password">Verify
								Password</label>
							<div class="controls">
								<input type="password" class="input-xlarge" id="confirmPassword"
									name="confirmPassword" placeholder="Password">
							</div>
						</div>

						<legend> Profile Info (Optional)</legend>

						<div class="control-group">
							<label class="control-label" for="fname">First Name</label>
							<div class="controls">
								<input type="text" name="firstName" placeholder="Arnold" value="${librarian.firstName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="lname">Last Name</label>
							<div class="controls">
								<input type="text" name="lastName" placeholder="Schwarzenegger" value="${librarian.lastName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">Phone</label>
							<div class="controls">

								<input type="text" name="phone1" placeholder="5143985151" value="${librarian.phone1}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">Additional Phone</label>
							<div class="controls">
								<input type="text" name="phone2" placeholder="5143989292" value="${librarian.phone2}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="phone">Address</label>
							<div class="controls">

								<input type="text" name="address"
									placeholder="123 Street, City, Province, Country" value="${librarian.address}">
							</div>
						</div>
						<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Add Librarian</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../views_ext/footer.jsp" />
