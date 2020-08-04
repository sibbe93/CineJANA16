<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Registrazione</title>
		
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	</head>
	<body>
	
	<%! LocalDate controllo = LocalDate.now().minusYears(12L);
		
	%>
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
                <h1>Benvenuto su CineJANA16</h1>
                <h2>Registrati</h2>
                <form action="/CineJANA16/RegistrazioneServlet" role="form" id="contactForm" method="post">
                    <div class="row" id="accediRow">
                        <div class="col-md-3 col-md-offset-3 col-sm-12">
                            <div class="form-group">
                                <input type="text" class="inputForm form-control" id="username" placeholder="Username"
                                    name="username" maxlength="50" data-toggle="tooltip" title="Campo obbligatorio"
                                    required>
                            </div>
                            <div class="form-group">
                                <input type="email" class="inputForm form-control" id="email" placeholder="Email"
                                    name="email" data-toggle="tooltip" title="Campo obbligatorio" required>
                            </div>
                            <div class="form-group">
                                <input type="password" class="inputForm form-control" id="password"
                                    placeholder="Password" name="password" required pattern=".{8,}"
                                    data-toggle="tooltip" title="Minimo 8 caratteri">
                                <small>Deve contenere almeno 8 caratteri.</small>
                            </div>

                            <div class="form-group">
                                <input type="reset" value="Reset" class="bott">
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-12">
                            <div class="form-group">
                                <input type="text" class="inputForm form-control" id="nome" placeholder="Nome"
                                    name="nome" data-toggle="tooltip" title="Campo obbligatorio" required>
                            </div>
                            <div class="form-group">
                                <input type="text" class="inputForm form-control" id="cognome" placeholder="Cognome"
                                    name="cognome" data-toggle="tooltip" title="Campo obbligatorio" required>
                            </div>
                            <div class="form-group">
                                <input type="date" max="<%= controllo%>" class="inputForm form-control" id="data"
                                    placeholder="Data" name="data" data-toggle="tooltip"
                                    title="Per registrarsi bisogna avere più di 12 anni" required>
                                <small>Inserisci la data di nascita.</small>
                            </div>
                            <div class="form-group">
                                <input type="submit" value="Registrati" class="bott">
                            </div>
                        </div>
                    </div>
              </form>
                <div class="row">
                    <div class="col-md-12 col-sm-12 mb-5">
                		<form action="/CineJANA16/jsp/public/login.jsp">
                    		<h6>Hai già un account?</h6>
                     		<input type="submit" value="Accedi" class="bott">
                		 </form>
                	</div>
              </div>
                <div class="row">
                	<div class="col-md-12 col-sm-12 mt-5">
                				<% String mex = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE);
						if(mex != null && mex.equals("usernameMail")){%>
					
						<h3 class="mexErr"> Username e/o email già esistenti</h3>	
					
					<%} else if(mex != null && mex.equals("passwordErrata")){ %>
					
						<h3 class="mexErr">La password deve essere almeno di 8 caratteri</h3>
					<%}else if(mex != null && mex.equals("nascitaErrata")){ %>
					
						<h3 class="mexErr">Devi avere un'età minima di 12 anni per registrarti</h3>
					<%} %>
                	
                	</div>
                </div>		
            </div>
        </div>
        
        <jsp:include page="/jsp/public/footer.jsp"></jsp:include>
		
		
	</body>
</html>