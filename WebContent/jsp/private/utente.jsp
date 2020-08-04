<%@page import="com.azienda.progetto.model.Utente"%>
<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">    
		<title>Area Utente</title>
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	</head>
	<body>
		
		<% Utente utente = (Utente) session.getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE); 
			String benvenuto = (String)request.getAttribute(Costanti.CHIAVE_BENVENUTO);%>
		
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
                        <a style="padding-top: 0; " href="/CineJANA16/jsp/private/utente.jsp">
                            <!-- logo image -->
                            <img class="" style="height: 8vh; padding-top: 1vh;" src="/CineJANA16/img/logo/log.png"
                                alt="" />
                        </a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="/CineJANA16/jsp/private/utente.jsp">Homepage</a></li>
                            <li><a href="/CineJANA16/preRicercaServlet">Programmazione</a></li>
                            <li><a href="/CineJANA16/logout">Logout</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </header>

        <div class="container-fluid" id="accediCont">
            <div class="events parallax-three pad" id="events">
                <div class="container">
                     <!-- default heading -->
                    <div class="default-heading-shadow">
                    
                        <!-- heading -->
                        <h1>AREA UTENTE</h1>
                        <% if(benvenuto != null && benvenuto.equals("benvenuto")){ %>
				
		
						<h1> Benvenuto <%= utente.getUsername() %></h1>
						
						<%} %>
                    </div>
                    <!-- events element -->
                    <div class="events-element">
                        <div class="row">
                        
                        	<div class="col-sm-12">
	                        	<% String mex = (String) request.getAttribute(Costanti.CHIAVE_PRENOTAZIONE); 
			
									if(mex != null && mex.equals("ok")){
								%>
									<h3 class="mexOk"> Prenotazione effettuata,<br> ti abbiamo inviato una mail di conferma </h3>
									
								<%} %>
							</div>
                            <div class="col-md-3 col-sm-12"></div>
                            <div class="col-md-3 col-sm-12">
                                <!-- event item -->
                                <div class="events-item">
                                    <div class="figure">
                                        <!-- image -->
                                        <img class="img-responsive" src="/CineJANA16/img/featured/ModificaProfiloUtente.png" alt=""
                                            width="100%" />
                                    </div>
                                    <!-- event information -->
                                    <div class="event-info">
                                        <h3>MODIFICA IL TUO PROFILO</h3>
                                        <form action="/CineJANA16/jsp/private/modificaprofilo.jsp">
                                        <!-- buy ticket button link -->
                                        <button type="submit" class="btn btn-theme" id="botMod"
                                            data-toggle="modal">Modifica</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-12">
                                <!-- event item -->
                                <div class="events-item">
                                    <!-- image container -->
                                    <div class="figure">
                                        <img class="img-responsive" src="/CineJANA16/img/featured/prenota.png" alt="" width="100%" />
                                        
                                    </div>
                                    <!-- event information -->
                                    <div class="event-info">
                                        <h3>GESTISCI LE TUE PRENOTAZIONI</h3>
                                        <form action="/CineJANA16/jsp/private/gestiscitueprenotazioni.jsp">
                                        <!-- buy ticket button link -->
                                        <button type="submit" class="btn btn-theme" id="botMod"
                                            data-toggle="modal">Gestisci</button>
                                        </form>    
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-12"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
	 <jsp:include page="/jsp/public/footer.jsp"></jsp:include>
	</body>
</html>