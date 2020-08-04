<%@page import="java.util.List"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@page import="com.azienda.progetto.model.Film"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		 <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Modifica film</title>
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	</head>
	<body>
		<% 	Film film = (Film) request.getAttribute(Costanti.CHIAVE_FILM);%>
			
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
            <h1>Modifica Film</h1>
            <div class="row">
                <div class="col-md-4 col-md-offset-4 col-sm-12">
                    <div class="form-content ">
                        <!-- paragraph -->
                        <form action="/CineJANA16/GestisciFilmServlet" role="form" id="contactForm" method="post">
                            <div class="form-group">
                                <input type="hidden" name="id" value="<%=film.getId() %>">
                            </div>
                            <div class="form-group">
                                <input type="text" class="inputForm form-control" id="text" placeholder="Titolo Film"
                                    name="titolo" data-toggle="tooltip" title="Campo obbligatorio" value="<%= film.getTitolo()%>" required>
                            </div>
                            <div class="form-group">
                                <input type="text" class="inputForm form-control" id="genere" placeholder="Genere"
                                    name="genere" data-toggle="tooltip" title="Campo obbligatorio" value="<%= film.getGenere() %>" required>
                            </div>
                            <div class="form-group">
                                <input type="time" class="inputForm form-control" id="durata" name="durata"
                                    data-toggle="tooltip" title="Campo obbligatorio" value="<%= film.getDurata() %>" required>
                            </div>
                            <div class="form-group">
                                <textarea name="trama" id="trama" cols="20" rows="4" class="inputForm form-control"
                                    placeholder="Trama" data-toggle="tooltip" title="Campo obbligatorio" 
                                    required><%= film.getTrama() %></textarea>
                            </div>
                            <div class="form-group">
                                <input type="url" class="inputForm form-control" id="url" placeholder="url trailer"
                                    name="trailer" data-toggle="tooltip" title="Campo obbligatorio" required value="<%= film.getTrailer() %>">
                            </div>
                            <div class="form-group">
                                <button type="submit" formaction="/CineJANA16/GestisciFilmServlet?modifica" class="bott">Salva Modifiche</button>
                            </div>
                        </form>
                        <% 	
						String mex = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE);
						if(mex!=null && mex.equals("errore")){%>
						
							<h3 class="mexErr"> Hai inserito un campo vuoto, o un campo troppo lungo </h3>
							
						<% } %>
                    </div>
                </div>
            </div>
        </div>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
					
			
					
		
			
								
		
		<jsp:include page="/jsp/public/footer.jsp"></jsp:include>
	</body>
</html>