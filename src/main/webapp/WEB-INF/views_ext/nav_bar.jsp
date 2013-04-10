<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty logged_user.username}">
	<div class="navbar navbar-fixed-top navbar-inverse">
		<div class="navbar-inner">
			<div class="container">
				<!-- Add JSP to make buttons active depending on
					the page the user is at -->
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="/">Hululu</a>
				<div class="nav-collapse">

					<ul class="nav">
						<li><a href="/catalogue">Catalogue</a></li>
						<li><a href="/register">Register</a></li>
					</ul>
					<ul class="nav pull-right">
						<li class="divider-vertical"></li>
						<li><a href="/login">Sign In</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${not empty logged_user.username}">
	<div class="navbar  navbar-fixed-top navbar-inverse">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="/">Hululu</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li><a href="/catalogue">Catalogue</a></li>
					</ul>
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Logged in as ${logged_user.username} <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/edit_profile">Edit Profile</a></li>
								<c:choose>
									<c:when test="${logged_user.role == 'Librarian'}">
										<li class="dropdown-submenu"><a href="#">Control
												Panel</a>
											<ul class="dropdown-menu">
												<li><a href="/add_book">Add/Delete Items</a></li>
												<li><a href="/checkin_book">Check In Items</a></li>
												<li><a href="/checkout_book">Check Out Items</a></li>
											</ul></li>
									</c:when>
									<c:when test="${logged_user.role == 'Library User'}">
										<li><a href="/view_borrowed">View Borrowed Items</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/admin/addLibrarian">Add Librarian</a></li>
										<li><a href="/admin/deleteLibrarians">Delete Librarians</a></li>
									</c:otherwise>
								</c:choose>
							</ul></li>
						<li class="divider"></li>
						<li><a href="/logout">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</c:if>
<div class="container">
	<div class="row searchbar top-buffer ">
		<form method="get" class="form-search" action="/search">
			<div class="input-append ">
				<input type="text" class="span4 search-query"
					placeholder="Search by title..." name="query">
				<button type="submit" class="btn">Search Catalogue</button>
			</div>
			<a href="/advancedSearch/">Advanced Search</a>
		</form>
	</div>
</div>