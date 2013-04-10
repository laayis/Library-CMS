<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../views_ext/header.jsp" >
	<jsp:param name="title" value="Check in a Journal/Book" />
</jsp:include>

	<hr />
	<div class="container">
		<div class="row">
			<div class="span12">
			<c:if test="${not empty success}">
				<div class="alert alert-success center">${success}</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="alert alert-error center">${error}</div>
			</c:if>

			<!-- CheckIn Form -->
				<form class="form-horizontal login-form" action="checkIn"
					id="checkInForm" method="post" style="display: none;">
					<fieldset>
						<legend>Check in Book/Journals Below:</legend>

					<div id="items">
						<div class="control-group">
							<label class="control-label" for="doc_id">Document ID</label>
							<div class="controls">
								<input type="text" name="copies[0].doc_id" placeholder="Document ID"
									value="${doc_id}">
								</div>
							<label class="control-label" for="copy_no">Copy Number</label>
							<div class="controls">
								<input type="text" name="copies[0].copy_no" placeholder="Copy #"
									value="${copy_no}">
							</div>
						</div>
					</div>

					<div class="offset2 btn-group-wrap">
						<div class="btn-group">
							<button type="button" class="btn btn-primary" id="checkItem">Add
								More</button>
						</div>
					</div>
					<hr/>
					<div class="offset2 btn-group-wrap">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Check In</button>
								<a href="user_page" role="button" class="btn btn-primary">Cancel</a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>		
	</div>
	<script type="text/javascript" src="resources/js/check_more.js"></script>
	<jsp:include page="../views_ext/footer.jsp" />
