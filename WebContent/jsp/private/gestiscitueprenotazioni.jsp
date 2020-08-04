<%@page import="java.util.ArrayList"%>
<%@page import="com.azienda.progetto.model.Prenotazione"%>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.azienda.progetto.model.Utente"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		 <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Gestisci le tue prenotazioni</title>
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	</head>
	<body>
		<%Utente ut = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE); 
			Utente st = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_STAFF); 
		Utente u = null;
		if(ut!=null){
			u = ut;
		}else if(st!=null){
			u = st;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");%>	
		
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
			                        <%if (u.getRuolo().getDescrizione().equals("utente")){ %>
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
			                                    <img src="/CineJANA16/img/featured/menu.png" width="20vw" alt="">
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
			
						<!-- wrapper -->
			    <div class="wrapper" id="home">
			
			        <div class="container-fluid" id="accediCont">
			             <h1>Gestisci le tue prenotazioni</h1>
			             <!-- paragraph -->
			            <div class="row" id="accediRow">
			                <div class="col-md-12 col-sm-12">
			                	<%if(u.getPrenotazioni().isEmpty()){%>
									<h3 class="mexErr">Non ci sono prenotazioni</h3>
								<%} else { %>
			                
			                    <table id="tab">
			                        <tr id="titTab">
			                            <th>Film</th>
			                            <th>Data ed Ora</th>
			                            <th>Sala</th>
			                            <th>Posti prenotati</th>
			                        </tr>
									<%for(int i=0; i<u.getPrenotazioni().size();i++){ %>
			                        <tr>
			                            <td><%=u.getPrenotazioni().get(i).getSpettacolo().getFilm().getTitolo() %></td>
										<td><%=u.getPrenotazioni().get(i).getSpettacolo().getDataSpettacolo().format(formatter) %></td>
										<td><%=u.getPrenotazioni().get(i).getSpettacolo().getSala().getNome() %></td>
										<td><%=u.getPrenotazioni().get(i).getPostiPrenotati() %></td>
			                            <td>
			                                <form action="/CineJANA16/EliminaPrenotazioneServlet" method="post">
			                                    <div class="btn-group w-55" role="group" aria-label="Basic example">
			                                        <button type="submit" formaction="/CineJANA16/EliminaPrenotazioneServlet"
			                                            class="btn btn-theme" id="botEl" data-toggle="tooltip"
			                                            title="Annulla Prenotazione">
			                                            <i class="fa fa-times iconeListaU"></i>
			                                        </button>
			                                    </div>
			                                    <input type="hidden" value="<%= u.getPrenotazioni().get(i).getId() %>" name="id">
			                                </form>
			                            </td>
			                        </tr>
									<%} %>
			                    </table>
			                    
			                    	<%} %>
										
										<% String mex = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE);
											if(mex!=null && mex.equals("ok")) {%>
												
											<h3 class="mexOk">Prenotazione annullata</h3>
											
										<%} else if(mex!=null && mex.equals("unOraSpettacolo")) {%>
											
											<h3 class="mexErr">Non puoi eliminare una prenotazione entro un'ora dall'inizio dello spettacolo</h3>
											
										<%}%>
			                </div>
			            </div>
			
			        </div>
		
		<jsp:include page="/jsp/public/footer.jsp"></jsp:include>
	</body>
</html>