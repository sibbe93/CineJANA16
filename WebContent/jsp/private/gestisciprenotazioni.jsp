<%@page import="com.azienda.progetto.model.Utente"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.azienda.progetto.model.Spettacolo"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.azienda.progetto.model.Prenotazione"%>
<%@page import="java.util.List"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">    
		<title>Gestisci prenotazioni</title>
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	
	</head>
	<body>
	
		<% 	Utente u = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_ADMIN);
			List<Prenotazione> prenotazioni = (List<Prenotazione>) request.getAttribute(Costanti.CHIAVE_PRENOTAZIONI); 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm"); 
			List<Prenotazione> prenotazioniTot = (List<Prenotazione>) request.getAttribute(Costanti.CHIAVE_PRENOTAZIONI_TOT); 
			String mess = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE);
			
			List<Spettacolo> datalist = new ArrayList<Spettacolo>(); 
			for (int i=0 ; i<prenotazioniTot.size();i++){
				if(!datalist.contains(prenotazioniTot.get(i).getSpettacolo())){
					datalist.add(prenotazioniTot.get(i).getSpettacolo());
				}
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
			                                    <a class="dropdown-item" href="/CineJANA16//PreInserimentoSpettacoloServlet">Inserisci Spettacolo</a>
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
			
			            <!-- paragraph -->
			            <h1>Gestisci Prenotazioni</h1>
			            <div class="row" id="accediRow">
			                <div class="col-md-3 col-md-offset-3 col-sm-12">
			                    <form action="/CineJANA16/RicercaPrenotazioniServlet?cercaPerData" method="post">
			                        <div class="form-group">
			                            <input type="date" class="inputForm form-control" id="data" placeholder="Cerca per data"
			                                name="data" data-toggle="tooltip" title="Cerca prenotazione per data" value="cerca per data">
			                        </div>
			                        <input type="submit" class="bott" value="Cerca per data">
			                    </form>
			                </div>
			
			                <div class="col-md-3 col-sm-12">
			                    <form action="/CineJANA16/RicercaPrenotazioniServlet?cercaPerSpettacolo" method="POST">                 
			                        <div class="form-group">
			                            <select class="inputForm" placeholder="Nome Spettacolo" name="spettacolo" data-toggle="tooltip"
			                                title="Cerca prenotazione per spettacolo">
			                                <%for (int i=0 ; i<datalist.size();i++){ %>
			                                <option value="<%=datalist.get(i).getId()%>"><%=datalist.get(i).getFilm().getTitolo()%> delle <%=datalist.get(i).getDataSpettacolo().format(formatter)%> in sala <%=datalist.get(i).getSala().getNome()%>. 
											</option>
			                                <%} %>
			                            </select>
			                        </div>
			                        <input type="submit" class="bott" value="Cerca per spettacolo">
			                    </form>
			                </div>
			            </div>
			            <div class="row" id="accediRow">
			                <div class="col-md-12 col-sm-12">
			                	<%if(prenotazioni.isEmpty()){%>
			                		<h3> Non ci sono prenotazioni effettuate</h3>
			                	<%} else {%>	
			                    <table id="tab">
			                        <tr id="titTab">
			                            <th>Film</th>
			                            <th>Data e ora</th>
			                            <th>Sala</th>
			                            <th>Nome</th>
			                            <th>Cognome</th>
			                            <th>Utente</th>
			                        </tr>
								<% for (int i =0 ; i< prenotazioni.size();i++){ %>
			                        <tr>
			                            <td><%= prenotazioni.get(i).getSpettacolo().getFilm().getTitolo() %></td>
			                            <td><%= prenotazioni.get(i).getSpettacolo().getDataSpettacolo().format(formatter) %></td>
			                            <td><%= prenotazioni.get(i).getSpettacolo().getSala().getNome() %></td>
			                            <td><%= prenotazioni.get(i).getUtente().getNome() %></td>
			                            <td><%= prenotazioni.get(i).getUtente().getCognome() %></td>
			                            <td><%= prenotazioni.get(i).getUtente().getUsername() %></td>
			                            <%  if(prenotazioni.get(i).getSpettacolo().getDataSpettacolo().isAfter(LocalDateTime.now())){%>
			                            <td>
			                                <form action="/CineJANA16/EliminaPrenotazioneServlet" method="post">
			                                    <div class="btn-group w-55" role="group" aria-label="Basic example">
			                                        <button type="submit" 
			                                            class="btn btn-theme" id="botEl" data-toggle="tooltip"
			                                            title="Annulla Prenotazione">
			                                            <i class="fa fa-times iconeListaU"></i>
			                                        </button>
			                                    </div>
			                                    <input type="hidden" value="<%=prenotazioni.get(i).getId()%>" name="id">
			                                </form>
			                            </td>
			                            <%} %>	
			                        </tr>
									<%} %>
			                    </table>
			                    <% String mex = (String) request.getAttribute(Costanti.CHIAVE_RICERCA_PRENOTAZIONI); 
									if(mex!=null && mex.equals("perData")){
			
							  
							if(prenotazioni.get(prenotazioni.size()-1)!= null && prenotazioni.get(prenotazioni.size()-1).getSpettacolo().getDataSpettacolo().isAfter(LocalDateTime.now())){
							%>
			                  <form action="/CineJANA16/EliminaPrenotazioneServlet?eliminaPerData" method="post">
									<input type="submit" value="elimina tutte le prenotazioni" class="bott">	
									<input type="hidden" name="data" value="<%= prenotazioni.get(0).getSpettacolo().getDataSpettacolo().toLocalDate()%>">			
								</form>  
			                   <%} }else if(mex!=null && mex.equals("perSpettacolo")){ %>
								<form action="/CineJANA16/EliminaPrenotazioneServlet?eliminaPerSpettacolo" method="post">
									<input type="submit" value="elimina tutte le prenotazioni" class="bott">	
									<input type="hidden" name="spettacolo" value="<%= prenotazioni.get(0).getSpettacolo().getId()%>">			
								</form> 
								<%}} %>
		
		  
									<%	if(mess!=null && mess.equals("ok")){ %>
									
									
									<h3 class="mexOk">Eliminazione effettuata</h3>
								
								<%} else if(mess!=null && mess.equals("inputErrato")) {%>
								
								
									<h3 class="mexErr">Non hai scelto un elemento della lista</h3>
								
								<%} %>
			                    
			                </div>
			            </div>
			
			
			
			        </div>
					<jsp:include page="/jsp/public/footer.jsp"></jsp:include>
		
	</body>
</html>