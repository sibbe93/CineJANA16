<%@page import="com.azienda.progetto.model.Utente"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Modifica il tuo profilo</title>
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	</head>
	<body>
	
		<% Utente utente = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			Utente staff = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
			Utente u = null;
			if(utente !=null){
				u = utente;
			} else if(staff != null){
				u = staff;
			}
		%>	
		
			 <!-- wrapper -->
	    <div class="wrapper" id="home">
	
	        <!-- header area -->
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
	                        <%if(u.getRuolo().getDescrizione().equals("utente")){ %>
	                        <a style="padding-top: 0; " href="/CineJANA16/jsp/private/utente.jsp">
	                         <%} else if(u.getRuolo().getDescrizione().equals("staff")){ %>
	                        <a style="padding-top: 0; " href="/CineJANA16/jsp/private/staff.jsp">
	                        <%} %>
	                            <!-- logo image -->
	                            <img class="" style="height: 8vh; padding-top: 1vh;" src="/CineJANA16/img/logo/log.png" alt="" />
	                        </a>
	                    </div>
	
	                    <!-- Collect the nav links, forms, and other content for toggling -->
	                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                        <ul class="nav navbar-nav navbar-right">
	                            <%if (u.getRuolo().getDescrizione().equals("utente")){ %>
	                            <li><a href="/CineJANA16/jsp/private/utente.jsp">Homepage</a></li>
	                            <%} else if(u.getRuolo().getDescrizione().equals("staff")){ %>
	                            <li><a href="/CineJANA16/jsp/private/staff.jsp">Homepage</a></li>                           
	                            
	                             <%} %>
	                            <li><a href="/CineJANA16/preRicercaServlet">Programmazione</a></li>
	                            <li><a href="/CineJANA16/logout">Logout</a></li>
	                             <%if (u.getRuolo().getDescrizione().equals("staff")){ %>
	                             <li class="nav-item dropdown">
			                                <a class="nav-link dropdown-toggle dropdown-mio" href="#" id="navbarDropdownMenuLink"
			                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                                    <img src="/CineJANA16/img/featured/menu.png" width="50vw" alt="">
			                                </a>
			                                <div class="dropdown-menu"
			                                    aria-labelledby="navbarDropdownMenuLink">			                                    
			                                    <a class="dropdown-item" href="/CineJANA16/jsp/private/modificaprofilo.jsp">Modifica il tuo Profilo</a>
			                                    <a class="dropdown-item" href="/CineJANA16/jsp/private/gestiscitueprenotazioni.jsp">Gestisci le tue Prenotazioni</a>
			                                    <a class="dropdown-item" href="/CineJANA16/jsp/private/inseriscifilm.jsp">Inserisci film</a>
			                                    <a class="dropdown-item" href="/CineJANA16/PreInserimentoSpettacoloServlet">Inserisci spettacolo</a>
			                                </div>
			                     </li>
	                             <%}else if (u.getRuolo().getDescrizione().equals("utente")){ %>
	                             	<li class="nav-item dropdown">
			                                <a class="nav-link dropdown-toggle dropdown-mio" href="#" id="navbarDropdownMenuLink"
			                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                                    <img src="/CineJANA16/img/featured/menu.png" width="50vw" alt="">
			                                </a>
			                                <div class="dropdown-menu"
			                                    aria-labelledby="navbarDropdownMenuLink">			                                    
			                                    <a class="dropdown-item" href="/CineJANA16/jsp/private/modificaprofilo.jsp">Modifica il tuo Profilo</a>
			                                    <a class="dropdown-item" href="/CineJANA16/jsp/private/gestiscitueprenotazioni.jsp">Gestisci le tue Prenotazioni</a>
			                                    </div>
			                     </li>
	                             <%} %>
                        
	                        </ul>
	                    </div><!-- /.navbar-collapse -->
	                </div><!-- /.container-fluid -->
	            </nav>
	        </header>
	
	        <div class="container-fluid" id="accediCont">
	            <div class="form-content ">
	                <!-- paragraph -->
	                <h1>Modifica il tuo profilo</h1>
	                <form action="/CineJANA16/ModificaProfiloServlet" role="form" id="contactForm" method="post">
	                    <div class="row" id="accediRow">
	                        <div class="col-md-3 col-md-offset-3 col-sm-12">
	                            <div class="form-group">
	                                <input type="email" class="inputForm form-control" id="email" placeholder="Email"
	                                    name="email" data-toggle="tooltip" title="Campo obbligatorio" required value ="<%= u.getEmail() %>">
	                            </div>
	                            <div class="form-group">
	                                <input type="password" class="inputForm form-control" id="password"
	                                    placeholder="Password" name="password" data-toggle="tooltip" pattern=".{8,}"
	                                    title="Campo obbligatorio" required value="<%= u.getPassword() %>">
	                            </div>
	                            <div class="form-group">
	                                <input type="reset" value="Reset" class="bott">
	                            </div>
	                        </div>
	                        <div class="col-md-3 col-sm-12">
	                            <div class="form-group">
	                                <input type="text" class="inputForm form-control" id="nome" placeholder="Nome"
	                                    name="nome" data-toggle="tooltip" title="Campo obbligatorio" required value="<%= u.getNome() %>">
	                            </div>
	                            <div class="form-group">
	                                <input type="text" class="inputForm form-control" id="cognome" placeholder="Cognome"
	                                    name="cognome" data-toggle="tooltip" title="Campo obbligatorio" required value="<%= u.getCognome() %>">
	                            </div>
	                            <div class="form-group">
	                                <input type="submit" value="Salva modifiche" class="bott">
	                            </div>
	                        </div>
	                    </div>
	                </form>
	                <% String mex = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE); 
					
						if(mex != null && mex.equals("ok")){
					%>
						<h3 class="mexOk"> Modifica effettuata </h3>
						
					<%} else if(mex != null && mex.equals("usernameMail")){ %>
					
						<h3 class="mexErr"> Email già esistente </h3>
						
					<%} else if(mex != null && mex.equals("passwordErrata")){ %>
					
					
						<h3 class="mexErr">La password deve essere almeno di 8 caratteri</h3>
						
					<%} %>
	            </div>
	        </div>
	        
		<jsp:include page="/jsp/public/footer.jsp"></jsp:include>
		
	</body>
</html>