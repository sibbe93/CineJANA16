<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.azienda.progetto.model.Utente"%>
<%@page import="com.azienda.progetto.model.Film"%>
<%@page import="java.util.List"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista Film</title>
<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
</head>
<body>
	<%
		List<Film> lista = (List<Film>) request.getAttribute(Costanti.CHIAVE_FILM);
	Utente ut = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
	Utente st = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
	Utente ad = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_ADMIN);
	Utente u = null;

	if (ut != null) {
		u = ut;
	} else if (st != null) {
		u = st;
	} else if (ad != null) {
		u = ad;
	}
	%>


	<header>

		<!-- primary menu -->
		<nav class="navbar navbar-fixed-top navbar-default"
			style="margin-top: 0;">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<!-- logo area -->
					<%
						if (u!=null && u.getRuolo().getDescrizione().equals("admin")) {
					%>
					<a style="padding-top: 0;" href="/CineJANA16/jsp/private/admin.jsp">
						<%
							} else if (u!=null && u.getRuolo().getDescrizione().equals("staff")) {
						%> <a
						style="padding-top: 0;" href="/CineJANA16/jsp/private/staff.jsp">
							<%
								} else if (u!=null && u.getRuolo().getDescrizione().equals("utente")) {
							%> <a
							style="padding-top: 0;" href="/CineJANA16/jsp/private/utente.jsp">
								<%
									} else {
								%> <a style="padding-top: 0;"
								href="/CineJANA16/jsp/public/homepage.jsp"> <%
 	}
 %> <!-- logo image -->
									<img class="" style="height: 8vh; padding-top: 1vh;"
									src="/CineJANA16/img/logo/log.png" alt="" />
							</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<%
							if (u!=null && u.getRuolo().getDescrizione().equals("admin")) {
						%>
						<li><a href="/CineJANA16/jsp/private/admin.jsp">Homepage</a></li>
						<li><a href="/CineJANA16/logout">Logout</a></li>
						<li class="nav-item dropdown">
			                                <a class="nav-link dropdown-toggle dropdown-mio" href="#" id="navbarDropdownMenuLink"
			                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                                    <img src="/CineJANA16/img/featured/menu.png" width="50vw" alt="">
			                                </a>
			                                <div class="dropdown-menu"
			                                    aria-labelledby="navbarDropdownMenuLink">
			                                    <a class="dropdown-item" href="/CineJANA16/jsp/private/inseriscisala.jsp">Inserisci Sala</a>
			                                    <a class="dropdown-item" href="/CineJANA16/jsp/private/inseriscifilm.jsp">Inserisci Film</a>
			                                    <a class="dropdown-item" href="/CineJANA16/PreInserimentoSpettacoloServlet">Inserisci Spettacolo</a>
			                                    <a class="dropdown-item" href="/CineJANA16/ricercaUtenti">Ricerca Utenti</a>
			                                    <a class="dropdown-item" href="/CineJANA16/PreRicercaSalaServlet">Gestisci Sale</a>
			                                    <a class="dropdown-item" href="/CineJANA16/PreInserimentoSpettacoloServlet?film">Gestisci Film</a>
			                                    <a class="dropdown-item" href="/CineJANA16/PreRicercaSpettacoloServlet">Gestisci Spettacoli</a>
			                                    <a class="dropdown-item" href="/CineJANA16/RicercaPrenotazioniServlet">Gestisci Prenotazioni</a>
			                                </div>
			                     </li>
						<%
							} else if (u!=null && u.getRuolo().getDescrizione().equals("staff")) {
						%>
						<li><a href="/CineJANA16/jsp/private/staff.jsp">Homepage</a></li>
						<li><a href="/CineJANA16/logout">Logout</a></li>
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
						<%
							} else if (u!=null && u.getRuolo().getDescrizione().equals("utente")) {
						%>
						<li><a href="/CineJANA16/jsp/private/utente.jsp">Homepage</a></li>
						<li><a href="/CineJANA16/logout">Logout</a></li>
						<li class="nav-item dropdown">
			                                <a class="nav-link dropdown-toggle dropdown-mio" href="#" id="navbarDropdownMenuLink"
			                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                                    <img src="/CineJANA16/img/featured/menu.png" width="20vw" alt="">
			                                </a>
			                                <div class="dropdown-menu"
			                                    aria-labelledby="navbarDropdownMenuLink">			                                    
			                                    <a class="dropdown-item" href="/CineJANA16/jsp/private/modificaprofilo.jsp">Modifica il tuo Profilo</a>
			                                    <a class="dropdown-item" href="/CineJANA16/jsp/private/gestiscitueprenotazioni.jsp">Gestisci le tue Prenotazioni</a>
			                                    </div>
			                     </li>
						<%
							} else {
						%>
						<li><a href="/CineJANA16/jsp/public/homepage.jsp">Homepage</a></li>
						<li><a href="/CineJANA16/preRicercaServlet">Programmazione</a>
						</li>
						<li><a href="/CineJANA16/jsp/public/login.jsp">Accedi</a></li>
						<li><a href="/CineJANA16/jsp/public/registrazione.jsp">Registrati</a></li>
						<%
							}
						%>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</header>



	<div class="container-fluid" id="accediCont">
		<div class="events parallax-three pad" id="events">
			<div class="container">
				<!-- heading -->
				<h1>Programmazione</h1>
				<!-- events element -->
				<div class="events-element">
					<div class="row" style="margin-bottom: 2vh">
						 <%String mex = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE);
							if (mex != null && mex.equals("ok")) {
							%>
							<div class="col-sm-12" style="margin-top: 2vh">
								<h3 class="mexOk">Modifica effettuata</h3>
							</div>
	
							<%
								} else if (mex != null && mex.equals("eliminaOk")) {
							%>
							
							<div class="col-sm-12" style="margin-top: 2vh">
								<h3 class="mexErr">Eliminazione effettuata</h3>
							</div>
							<%
								}%>
					
						<%
							if (lista != null) {
							for (int i = 0; i < lista.size(); i++) {
						%>
						<div class="col-md-4 col-sm-12">
							<!-- event item -->
							<div class="events-item" style="height: 80vh !important">
								<!-- image container -->
								<div class="figure" style="height: 25vh !important">

									<!-- trailer -->
									<iframe width="100%" src="<%=lista.get(i).getTrailer()%>"
										frameborder="0"
										allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
										allowfullscreen></iframe>
								</div>
								<!-- event information -->
								<div class="event-info">
									<!-- nome film -->
									<div style="height: 17vh !important">
										<h3 id="<%=lista.get(i).getId()%>"><%=lista.get(i).getTitolo()%></h3>
										<!-- genere -->
										<h5><%=lista.get(i).getGenere()%></h5>
										<!-- durata -->
										<h6><%=lista.get(i).getDurata()%></h6>
									</div>
									<!-- paragraph -->
									<p style="height: 20vh !important"><%=lista.get(i).getTrama()%></p>
									<!-- buy ticket button link -->
									<%
										if (u != null && u.getRuolo().getDescrizione().equals("admin")) {
									%>
									<form action="/CineJANA16/GestisciFilmServlet" method="post">
										<button type="submit"
											formaction="/CineJANA16/GestisciFilmServlet?cercafilm"
											class="btn btn-theme" id="botMod">Modifica</button>
										<button type="submit"
											formaction="/CineJANA16/GestisciFilmServlet?elimina"
											class="btn btn-theme" id="botEl">Elimina</button>
										<input type="hidden" value="<%=lista.get(i).getId()%>"
											name="id">
									</form>
								</div>
							</div>
						</div>
						<%
							} else{%>
								</div>
							</div>
						</div>
								
							
						<%}}%>

					 
						<%} else {%>
						
						<h3 class="mexErr" style="margin-top: 2vh">Non ci sono film nel sistema</h3>
						<%
							}
						%>


					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/jsp/public/footer.jsp"></jsp:include>

</body>
</html>