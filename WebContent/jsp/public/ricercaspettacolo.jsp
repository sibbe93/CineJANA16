<%@page import="com.azienda.progetto.model.Utente"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.azienda.progetto.model.Film"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.azienda.progetto.model.Spettacolo"%>
<%@page import="java.util.List"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		 <title>Ricerca Spettacolo</title>
    	<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
</head>
<body>


		<%Utente ut = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE); 
			Utente st =(Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
			Utente u = null;
			if(ut!=null){
				u=ut;
			} else {
				u=st;
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
		                        <%if(u!=null && u.getRuolo().getDescrizione().equals("utente")) {%>
		                        <a style="padding-top: 0; " href="/CineJANA16/jsp/private/utente.jsp">
		                        <%} else if(u!=null && u.getRuolo().getDescrizione().equals("staff")) { %>
		                        <a style="padding-top: 0; " href="/CineJANA16/jsp/private/staff.jsp">
		                        <%}else{ %>
		                        <a style="padding-top: 0; " href="/CineJANA16/jsp/public/homepage.jsp">
		                        <%} %>
		                            <!-- logo image -->
		                            <img class="" style="height: 8vh; padding-top: 1vh;" src="/CineJANA16/img/logo/log.png"
		                                alt="" />
		                        </a>
		                    </div>
		
		                    <!-- Collect the nav links, forms, and other content for toggling -->
		                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		                        <ul class="nav navbar-nav navbar-right">
		                        <%if(u!=null && u.getRuolo().getDescrizione().equals("utente")) {%>
		                        	<li><a href="/CineJANA16/jsp/private/utente.jsp">Homepage</a></li>
                           			 <li><a href="/CineJANA16/preRicercaServlet">Programmazione</a></li>
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
			                                    </div>
			                     </li>
                            		
                            	 <%} else if(u!=null && u.getRuolo().getDescrizione().equals("staff")) { %>
                            	  <li><a href="/CineJANA16/jsp/private/staff.jsp">Homepage</a></li>
                            		<li><a href="/CineJANA16/preRicercaServlet">Programmazione</a></li>
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
		                        <%} else{  %>
		                            <li><a href="/CineJANA16/jsp/public/homepage.jsp">Homepage</a></li>
		                            <li><a href="/CineJANA16/jsp/public/login.jsp">Accedi</a></li>
		                            <li><a href="/CineJANA16/jsp/public/registrazione.jsp">Registrati</a></li>
		                         <%} %>   
		                        </ul>
		                    </div><!-- /.navbar-collapse -->
		                </div><!-- /.container-fluid -->
		            </nav>
		        </header>
		
		        <div class="container-fluid" id="accediCont">
		            <!-- paragraph -->
		            <div class="row" id="accediRow">
		                <h1>Cerca lo spettacolo</h1>
		                <form action="/CineJANA16/ricercaSpettServlet">
		                    <div class="col-md-3 col-md-offset-2 col-sm-12">
		                        <div class="form-group">
		                            <input type="text" class="inputForm form-control" id="film" placeholder="Cerca Spettacolo"
		                                name="film" list="listaFilm" data-toggle="tooltip" title="Nome Spettacolo">
		                            <datalist id="listaFilm">
		                                <% List<Film> lista = (List<Film>) request.getAttribute(Costanti.CHIAVE_FILM);
		                                if (lista!=null){		
		                                for(int i = 0; i< lista.size();i++){
		                                %>
		                                <option value="<%= lista.get(i).getTitolo()  %>"></option>
		                                <%}} %>
		                            </datalist>
		                        </div>
		                    </div>
		                    <div class="col-md-3 col-sm-12">
		                        <div class="form-group">
		                            <input type="date" class="inputForm form-control" min="<%= LocalDate.now() %>" id="data"
		                                placeholder="Data" name="data" data-toggle="tooltip" title="Data Spettacolo">
		                        </div>
		                    </div>
		                    <div class="col-md-3 col-sm-12">
		                        <div class="form-group">
		                            <input type="submit" value="Cerca" class="bott">
		                        </div>
		                    </div>
		
		                </form>
		            </div>
		
		            <div class="row">
		                <div class="col-md-12 col-sm-12">
		                
		                	<% List<Spettacolo> risultati = (List<Spettacolo>) request.getAttribute(Costanti.CHIAVE_RICERCA_SPETT);
		                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
		                        String mex = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE);
		                     if(risultati != null && risultati.isEmpty()){
		                    %>
		                    
		                    <h3 class="mexErr">La tua ricerca non ha prodotto risultati</h3>			
                        
                   				 <%} else if(risultati != null && !risultati.isEmpty()){ %>
		                    
		                    <table id="tab">
		                        <tr id="titTab">
		                            <th>Film</th>
		                            <th>Orario</th>
		                            <th>Sala</th>
		                            <th>Posti disponibili</th>
		                        </tr>
									<%for (int i=0; i<risultati.size();i++){ 
			                        	Integer postiDisp = risultati.get(i).getSala().getNumeroPosti();
			                        	Integer totPostiPren = 0;
			                        	if(risultati.get(i).getPrenotazioni() != null){
			                        	for (int j=0; j<risultati.get(i).getPrenotazioni().size();j++){
			                        		totPostiPren += risultati.get(i).getPrenotazioni().get(j).getPostiPrenotati();
			                        	}
			                        	}	
			                        %>
		                        <tr>
		                            <td> <a href="/CineJANA16/preRicercaServlet?elencofilm" id="aFilm" > <%= risultati.get(i).getFilm().getTitolo() %></a>                          
		                            
		                             </td>
                           			 <td> <%= risultati.get(i).getDataSpettacolo().format(formatter) %></td>
                            		<td> <%= risultati.get(i).getSala().getNome() %> </td>
                            		<td><%= postiDisp - totPostiPren %></td>
                            		 <%if (postiDisp-totPostiPren !=0){ %>
                            		 <td>
		                                <form action="/CineJANA16/PrePrenotazioneServlet" method="POST">
		                                    <button type="submit" id="bottPrenota" data-toggle="tooltip" title="Prenota Film">
		                                        Prenota </button>
		                                    <input type="hidden" name="id" value="<%=risultati.get(i).getId() %>">
		                                </form>
		                            </td>
		                             <%} %>
		                        </tr>
								<%} %>
		                    </table>
		                    <%} else if (risultati == null && mex != null && mex.equals("erroreData")){ %>
                    
                    	<h3 class="mexErr">Non puoi cercare spettacoli relativi a date passate</h3>
                    	
                    <%} %>
		                </div>
		            </div>
		
		        </div>
		    </div>
     
    <jsp:include page="/jsp/public/footer.jsp"></jsp:include>
	</body>
</html>