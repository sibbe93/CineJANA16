<%@page import="java.time.format.DateTimeFormatter"%>
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
		<title>Gestisci gli spettacoli</title>
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
	</head>
	<body>
	
		<%List<Spettacolo> lista = (List<Spettacolo>) request.getAttribute(Costanti.CHIAVE_SPETTACOLO); 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");%>
			
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
		            <h1>Gestisci Spettacolo</h1>
		            <!-- paragraph -->
		            <div class="row" id="accediRow">
		                <div class="col-md-12 col-sm-12">
		                	<%if(!lista.isEmpty()){%>
		                    <table id="tab">
		                        <tr id="titTab">
		                            <th>Film</th>
		                            <th>Data</th>
		                            <th>Sala</th>
		                        </tr>
								<%for(int i=0; i<lista.size();i++){%>
		                        <tr>
		                            <td><%=lista.get(i).getFilm().getTitolo() %></td>
		                            <td><%= lista.get(i).getDataSpettacolo().format(formatter) %></td>
		                            <td><%= lista.get(i).getSala().getNome() %></td>
		                            <td>
		                                <form action="/CineJANA16/GestisciSpettacoloServlet" method="post">
		                                    <div class="btn-group w-55" role="group" aria-label="Basic example">
		                                        <button type="submit" formaction="/CineJANA16/GestisciSpettacoloServlet?eliminaspett"
		                                            class="btn btn-theme" id="botEl" data-toggle="tooltip" title="Elimina Spettacolo">
		                                            <i class="fa fa-times iconeListaU"></i>
		                                        </button>
		                                        <button type="submit" formaction="/CineJANA16/GestisciSpettacoloServlet?cercaspett"
		                                            class="btn btn-theme" id="botMod" data-toggle="tooltip"
		                                            title="Modifica Spettacolo">
		                                            <i class="fa fa-pencil iconeListaU"></i>
		                                        </button>
		                                    </div>
		                                    <input type="hidden" value="<%=lista.get(i).getId()%>" name="id">
		                                </form>
		                            </td>
		                        </tr>
								<%} %>
		                    </table>
								 <%} else if(lista.isEmpty()) {%>
									
										<h3 class="mexErr">Non ci sono spettacoli</h3>
									
								<%}
									String mex = (String) request.getAttribute(Costanti.CHIAVE_MEX_ERRORE);
									if(mex!=null && mex.equals("ok")){%>
										
										<h3 class="mexOk">Eliminazione effettuata</h3>
										
									<% }  else if (mex!= null && mex.equals("modificaOk")){%>
										
										<h3 class="mexOk"> Modifica effettuata</h3>
										
										
									<%} else if (mex!= null && mex.equals("erroreSovrapposizione")){%>
									
										<h3 class="mexErr"> Errore nella modifica, orari spettacoli coincidenti</h3>
									
									<%} else if (mex != null && mex.equals("deviInserireTuttiICampi")){ %>
									
										<h3 class="mexErr"> Errore nella modifica, riempi tutti i campi</h3>
									
									<%} %>
						     
		                </div>
		            </div>
		
		        </div>
			
				<jsp:include page="/jsp/public/footer.jsp"></jsp:include>
	</body>
</html>