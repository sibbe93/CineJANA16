<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    
		<title>Attesa</title>
		
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
		
	</head>
	<body>
		 <!-- wrapper -->
	    <div class="wrapper" id="home">
	        <header>
	
	            <!-- primary menu -->
	            <nav class="navbar navbar-fixed-top navbar-default" style="margin-top: 0;">
	                <div class="container">
	                    <!-- Brand and toggle get grouped for better mobile display -->
	                    <div class="navbar-header">
	                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
	                            data-target="#bs-example-navbar-collapse-1">
	                            <span class="sr-only">Toggle navigation</span>
	                            <span class="icon-bar"></span>
	                            <span class="icon-bar"></span>
	                            <span class="icon-bar"></span>
	                        </button>
	                        <!-- logo area -->
	                        <a style="padding-top: 0; " href="/CineJANA16/jsp/public/homepage.jsp">
	                            <!-- logo image -->
	                            <img class="" style="height: 8vh; padding-top: 1vh;" src="/CineJANA16/img/logo/log.png" alt="" />
	                        </a>
	                    </div>
	
	                    <!-- Collect the nav links, forms, and other content for toggling -->
	                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                        <ul class="nav navbar-nav navbar-right">
	                            <li><a href="/CineJANA16/jsp/public/homepage.jsp">Homepage</a></li>
	                            <li><a href="/CineJANA16/jsp/public/ricercaspettacolo.jsp">Programmazione</a></li>
	                        </ul>
	                    </div><!-- /.navbar-collapse -->
	                </div><!-- /.container-fluid -->
	            </nav>
	        </header>
	
	        <div class="container-fluid">
	            <div class="row">
	                <div class="col-md-12" id="iconManutenzione">
	                    <i class="fa fa-spinner fa-5x"></i>
	                    <div>
	                        <h3>Attendi...<br>
	                        La tua richiesta è in fase di accettazione.
	                            <br>Ti arriverà una mail di conferma appena l'admin avrà approvato la tua richiesta.
	                        </h3>
	                    </div>
	                </div>
	            </div>
	        </div>
	        
	        <jsp:include page="/jsp/public/footer.jsp"></jsp:include>
		
		
		
	</body>
</html>