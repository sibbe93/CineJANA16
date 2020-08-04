<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.azienda.progetto.model.Sala"%>
<%@page import="com.azienda.progetto.model.Film"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@page import="com.azienda.progetto.model.Spettacolo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">   
		<title>Modifica spettacolo</title>
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	</head>
	<body>
		<% 			
			Spettacolo spettacolo = (Spettacolo) request.getAttribute(Costanti.CHIAVE_SPETTACOLO);
			List<Film> listaFilm = (List<Film>) request.getAttribute(Costanti.CHIAVE_FILM);
			List<Sala> listaSale = (List<Sala>) request.getAttribute(Costanti.CHIAVE_SALA);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm");
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
		                        <a style="padding-top: 0; " href="/CineJANA16/jsp/private/admin.jsp">
		                            <!-- logo image -->
		                            <img class="" style="height: 8vh; padding-top: 1vh;" src="/CineJANA16/img/logo/log.png" alt="" />
		                        </a>
		                    </div>
		
		                    <!-- Collect the nav links, forms, and other content for toggling -->
		                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		                        <ul class="nav navbar-nav navbar-right">
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
		                        </ul>
		                    </div><!-- /.navbar-collapse -->
		                </div><!-- /.container-fluid -->
		            </nav>
		        </header>
		
		        <div class="container-fluid" id="accediCont">
		            <div class="form-content ">
		                <!-- paragraph -->
		                <h1>Modifica Spettacolo</h1>
		                <form action="/CineJANA16/GestisciSpettacoloServlet" role="form" id="contactForm" method="post">
		                    <div class="row" id="accediRow">
		                        <div class="col-md-4 col-md-offset-4 col-sm-12">
		                            <div class="form-group">
		                                <input type="hidden" name="id" value="<%=spettacolo.getId()%>">
		                            </div>
		                            <div class="form-group">
		                                <select class="inputForm" name="film"
		                                    data-toggle="tooltip" title="Nome Spettacolo" required>
		                                    <%	if (listaFilm!=null){		
												for(int j = 0; j< listaFilm.size();j++){
											%>
		                                    <option value="<%= listaFilm.get(j).getId()%>" <%if(spettacolo.getFilm().getTitolo().equals(listaFilm.get(j).getTitolo())){%>selected<%} %>><%= listaFilm.get(j).getTitolo()%></option>
		                                    
		                                    <%}} %>
		                                </select>
		                            </div>
		                            <div class="form-group">
		                                <input type="datetime-local" min="<%=LocalDateTime.now().format(formatter) %>" class="inputForm" id="dataOra" name="dataOra"
		                                    data-toggle="tooltip" title="Campo obbligatorio" value="<%= spettacolo.getDataSpettacolo()%>" required>
		                            </div>
		                            <div class="form-group">
		                                <select class="inputForm" placeholder="Nome Sala" name="sala" data-toggle="tooltip"
		                                    title="Nome Sala" required>
		                                    <% 
												if (listaSale!=null){		
												for(int k = 0; k< listaSale.size();k++){
											%>
		                                    <option value="<%= listaSale.get(k).getId()%>"  <%if(spettacolo.getSala().getNome().equals(listaSale.get(k).getNome())){%>selected<%} %>><%=listaSale.get(k).getNome()%></option>
											<%}} %>
		                                 </select>
		
		                            </div>
		                            <div class="form-group">
		                                <button type="submit" formaction="/CineJANA16/GestisciSpettacoloServlet?modificaspett" class="bott">Salva Modifiche</button>
		                            </div>
		                        </div>
		                    </div>
		                </form>
		            </div>
		        </div>	
		<jsp:include page="/jsp/public/footer.jsp"></jsp:include>
	</body>
</html>