<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="edit_profile" />
</jsp:include>

	<hr/>
	  <div class="container">
	    <h1>Edit Profile</h1>
	    
	  </div>
	<div class="container">
	   
		<div class="row">
			<div class="span12">
				<c:if test="${not empty message}">
					<div class="alert alert-error">${message}</div>
				</c:if>
				<form class="form-horizontal login-form" action="update_profile" id="editProfileForm" method="post">
					<fieldset>
						<legend>Required Info</legend>
						<div class="control-group">
							<label class="control-label" for="username">Username</label>
							<div class="controls">
								<input type="text" name="username" placeholder="${logged_user.username}" value="${user.username}" readonly>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="email">Email</label>
							<div class="controls">
								<input type="text" name="email" placeholder="${logged_user.email}" value="${user.email}">
							</div>
						</div>


						<legend> Profile Info (Optional)</legend>

						<div class="control-group">
							<label class="control-label" for="fname">First Name</label>
							<div class="controls">
								<input type="text" name="firstName" placeholder="${logged_user.firstName}" value="${user.firstName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="lname">Last Name</label>
							<div class="controls">
								<input type="text" name="lastName" placeholder="${logged_user.lastName}" value="${user.lastName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">Phone</label>
							<div class="controls">

								<input type="text" name="phone1" placeholder="${logged_user.phone1}" value="${user.phone1}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">Additional Phone</label>
							<div class="controls">
								<input type="text" name="phone2" placeholder="${logged_user.phone2}" value="${user.phone2}">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="phone">Address</label>
							<div class="controls">

								<input type="text" name="address"
									placeholder="${logged_user.address}" value="${user.address}">
							</div>
						</div>
						<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../views_ext/footer.jsp" />
