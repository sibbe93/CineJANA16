<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.azienda.progetto.model.Utente"%>
<%@page import="com.azienda.progetto.model.Film"%>
<%@page import="com.azienda.progetto.model.Sala"%>
<%@page import="java.util.List"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1"> <meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<title>Inserisci spettacolo</title>
		
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	</head>
	<body>
		
		<% List<Sala> listaSale = (List<Sala>) request.getAttribute(Costanti.CHIAVE_SALA);
		List<Film> listaFilm = (List<Film>) request.getAttribute(Costanti.CHIAVE_FILM);	
		Utente st = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
		Utente ad = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_ADMIN);
		Utente u = null;
		if(st!=null){
			u=st;
		} else if(ad!= null){
			u=ad;
		}
		
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
                        <%if(u.getRuolo().getDescrizione().equals("admin")){ %>  
                        <a style="padding-top: 0; " href="/CineJANA16/jsp/private/admin.jsp"> 
                          <%} else if(u.getRuolo().getDescrizione().equals("staff")){ %> 
                         <a style="padding-top: 0; " href="/CineJANA16/jsp/private/staff.jsp"> 
                          <%} %>                   <!-- logo image -->
                            <img class="" style="height: 8vh; padding-top: 1vh;" src="/CineJANA16/img/logo/log.png" alt="" />
                        </a>
                    </div>

                     <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                        	<%if (u.getRuolo().getDescrizione().equals("admin")){ %>
                            <li><a href="/CineJANA16/jsp/private/admin.jsp">Homepage</a></li>
                            <%} else if(u.getRuolo().getDescrizione().equals("staff")){ %>
                            <li><a href="/CineJANA16/jsp/private/staff.jsp">Homepage</a></li>                           
                            <li><a href="/CineJANA16/preRicercaServlet">Programmazione</a></li>
                             <%} %>
                            
                            <li><a href="/CineJANA16/logout">Logout</a></li>
                            <%if (u.getRuolo().getDescrizione().equals("admin")){ %>
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
                            <%} else if(u.getRuolo().getDescrizione().equals("staff")){  %>
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
                            <%} %>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </header>

        <div class="container-fluid" id="accediCont">
            <div class="form-content ">
                <!-- paragraph -->
                <h1>Inserisci Spettacolo</h1>
                <form role="form" action="/CineJANA16/InserisciSpettacoloServlet" id="contactForm" method="post">
                    <div class="row" id="accediRow">
                        <div class="col-md-12 col-sm-12">
                     
                                <div class="form-group">
                                    <select class="inputForm" name="film" data-toggle="tooltip"
                                        title="Nome Film" required>
                                 
												<% 
												if (listaFilm!=null){		
												for(int i = 0; i< listaFilm.size();i++){
												%>
													<option value="<%= listaFilm.get(i).getId()%>"><%= listaFilm.get(i).getTitolo()%></option>
													
												<%}} %>
								  
								</select>
                                </div>
                                <div class="form-group">
                                    <select class="inputForm" name="sala" data-toggle="tooltip"
                                        title="Nome Sala" required>
                                   
										<% 
										if (listaSale!=null){		
										for(int i = 0; i< listaSale.size();i++){
										%>
											<option value="<%= listaSale.get(i).getId()%>"><%= listaSale.get(i).getNome()%></option>
									
										<%}} %>
									</select>
                                </div>
                                <div class="form-group">
                                    <input type="datetime-local"  min="<%=LocalDateTime.now().format(formatter) %>" class="inputForm" placeholder="Data e orario spettacolo" name="dataOra"
                                        required>
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Inserisci" class="bott">
                                </div>
                         
                            <div class="form-group">
	                              <% String mex = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE);
										if(mex != null && mex.equals("ok")){
									%>
										
										<h3 class="mexOk">Inserimento effettuato</h3>
										
										
									<%} else if(mex != null &&  mex.equals("sovrapposizione")){ %>
									
										<h3 class="mexErr"> L'inserimento si sovrappone ad uno spettacolo già esistente </h3>
										
									<%} else if(mex != null &&  mex.equals("deviInserireTuttiICampi")){%>
									
										<h3 class="mexErr"> Riprova, hai inserito un campo vuoto </h3>
									<%} else if(mex != null &&  mex.equals("ahNoNonPosso?")){  %>
									
										<h3 class="mexErr">Non puoi inserire uno spettacolo in una data passata</h3>
										
									<%} %>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
		
		
		<jsp:include page="/jsp/public/footer.jsp"></jsp:include>
	</body>
</html>