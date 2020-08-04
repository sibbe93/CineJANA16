<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Login</title>
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
		
		<div class="container-fluid" id="accediCont">
            <div class="form-content ">
                <!-- paragraph -->
                <h1>Bentornato su CineJANA16</h1>
                <h2>Accedi</h2>
                  <form role="form" action="/CineJANA16/LoginServlet" id="contactForm" method="post"> 
                    <div class="row" id="accediRow">
                        <div class="col-md-12 col-sm-12">
                            
                                <div class="form-group">
                                    <input type="text" class="inputForm" id="username" placeholder="Username"
                                        name="username" data-toggle="tooltip" title="Campo obbligatorio" 
                                        required>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="inputForm" id="password" placeholder="Password"
                                        name="pass" data-toggle="tooltip" title="Campo obbligatorio"
                                        required>
                                </div>
                                <div class="form-group">                                	
                                    <input type="submit" value="Login" class="bott">                                    
                                </div>
                                
                       </div>
                    </div>
                </form>
                 <form action="/CineJANA16/jsp/public/registrazione.jsp">
                     <div class="form-group">
                        <small>Non hai un account?</small>
                     </div>
                     <div class="form-group">
                       <input type="submit" value="Registrati" class="bott">
                     </div>                     
                 </form>  
                 <% String mex = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE); 
						if(mex != null && mex.equals("errore")){%>
				
						<h3 class="mexErr">Username e/o password errata</h3>
				
						<%}%>
            </div>
        </div>
		<jsp:include page="/jsp/public/footer.jsp"></jsp:include>
	
	</body>
	
	
</html>