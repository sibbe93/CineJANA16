<%@page import="com.azienda.progetto.model.Utente"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.azienda.progetto.model.Spettacolo"%>
<%@page import="com.azienda.progetto.businessLogic.BusinessLogic"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Effettua la tua prenotazione</title>
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	</head>
	<body>
		<% Spettacolo spettacolo = (Spettacolo) request.getAttribute(Costanti.CHIAVE_SPETTACOLO);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			Utente utente = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
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
		                            <img class="" style="height: 8vh; padding-top: 1vh;" src="CineJANA16/img/logo/log.png" alt="" />
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
		            <h1>Effettua la tua prenotazione</h1>
		            <!-- paragraph -->
		            <div class="row" id="accediRow">
		                <div class="col-md-12 col-sm-12">
		                    <h4>Stai prenotando per il film <%=spettacolo.getFilm().getTitolo() %> in data <%=spettacolo.getDataSpettacolo().toLocalDate().format(formatter2) %> alle <%=spettacolo.getDataSpettacolo().toLocalTime().format(formatter) %> proiettato nella sala <%=spettacolo.getSala().getNome() %> </h4>
		
		                    <form action="/CineJANA16/prenotazioneServlet" method="post">
		                        <div class="form-group">
		                            <input type="number" min="1" max="6" class="inputForm" placeholder="Numero posti da prenotare" name="numeroPostiPrenotati"
		                            data-toggle="tooltip" title="Massimo 6 posti" required>
		                        </div>
		                        	<input type="hidden" value="<%= spettacolo.getId()%>" name="id">
		                        <div class="form-group">
		                            <input type="submit" value="Conferma prenotazione" class="bott">
		                        </div>
		                    </form>
							<% String mex = (String) request.getAttribute(Costanti.CHIAVE_PRENOTAZIONE);
								if(mex!= null && mex.equals("erroreNumeroPosti")){
							%>
								
								<h3 class="mexErr"> Numero di posti prenotati non consentito </h3>
								
							<%} else if (mex != null && mex.equals("postiNonDisponibili")){%>
							
								<h3 class="mexErr"> Non ci sono abbastanza posti per la tua prenotazione </h3>
							
							<%} %>
							
		                </div>
		            </div>
		
		        </div>
		
			<jsp:include page="/jsp/public/footer.jsp"></jsp:include>	
	</body>
</html>