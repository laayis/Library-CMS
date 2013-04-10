
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../views_ext/nav_bar.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>View Catalogue</title>
<link href="resources/css/bootstrap.css" rel="stylesheet">
<link href="resources/css/bootstrap-responsive.css" rel="stylesheet">
<link href="resources/css/customstyles.css" rel="stylesheet">


</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<h1 id="message" twi>Welcome to Hululu Library</h1>
        <a href="login">
            <div id="user" class="span6">
                <h1 >User</h1>
            </div>
        </a>
        <a href="/librarian_login">
          <div id="Librarian" class="span6">
              <h1>Librarian</h1>
          </div>
        </a>
        
			</div>
		</div>
	</div>
</body>
</html>
