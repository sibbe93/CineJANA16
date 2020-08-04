<%@page import="com.azienda.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Home Page</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<jsp:include page="/jsp/public/libreriehead.jsp"></jsp:include>
			
	</head>
	<body>
		
		
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
							<a style="padding-top: 0; " href="#">
								<!-- logo image -->
								<img class="" style="height: 8vh; padding-top: 1vh;" src="/CineJANA16/img/logo/log.png" alt="" />
							</a>
						</div>
	
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="/CineJANA16/preRicercaServlet">Programmazione</a> </li>
								<li><a href="/CineJANA16/jsp/public/login.jsp">Accedi</a></li>
								<li><a href="/CineJANA16/jsp/public/registrazione.jsp">Registrati</a></li>
							</ul>
						</div><!-- /.navbar-collapse -->
					</div><!-- /.container-fluid -->
				</nav>
			</header>
		
				<!-- banner area -->
			<div class="container-">
				<div class="banner">
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
						<div class="item active">
								<img src="/CineJANA16/img/banner/harrypotter.jpg" alt="...">
								<div class="container">
									<!-- banner caption -->
									<div class="carousel-caption slideFoto">
										<!-- heading -->
										<h2 class="animated fadeInLeftBig">Harry Potter<br> e il Principe Mezzosangue</h2>
										<!-- paragraph -->
										<h3 class="animated fadeInRightBig"></h3>
										<!-- button -->
										<a href="/CineJANA16/preRicercaServlet" class="animated fadeIn btn btn-theme">Vedi Film</a>
									</div>
								</div>
							</div>
							<div class="item">
								<img src="/CineJANA16/img/banner/jack.jpg" alt="...">
								<div class="container">
									<!-- banner caption -->
									<div class="carousel-caption slideFoto">
										<!-- heading -->
										<h2 class="animated fadeInLeftBig">La vera storia di <br> Jack Lo Squartatore!</h2>
										<!-- paragraph -->
										<h3 class="animated fadeInRightBig">From Hell.</h3>
										<!-- button -->
										<a href="/CineJANA16/preRicercaServlet" class="animated fadeIn btn btn-theme">Vedi Film</a>
									</div>
								</div>
							</div>
							<div class="item">
								<img src="/CineJANA16/img/banner/hungergames.jpg" alt="...">
								<div class="container">
									<!-- banner caption -->
									<div class="carousel-caption slideFoto">
										<!-- heading -->
										<h2 class="animated fadeInLeftBig">Hunger Games<br>E la Ragazza di Fuoco</h2>
										<!-- paragraph -->
										<h3 class="animated fadeInRightBig"></h3>
										<!-- button -->
										<a href="/CineJANA16/preRicercaServlet" class="animated fadeIn btn btn-theme">Vedi Film</a>
									</div>
								</div>
							</div>
							<div class="item">
								<img src="/CineJANA16/img/banner/avengers.jpg" alt="...">
								<div class="container">
									<!-- banner caption -->
									<div class="carousel-caption slideFoto">
										<!-- heading -->
										<h2 class="animated fadeInLeftBig textColor">Avengers</h2>
										<!-- paragraph -->
										<h3 class="animated fadeInRightBig textColor"> ...uniti!</h3>
										<!-- button -->
										<a href="/CineJANA16/preRicercaServlet" class="animated fadeIn btn btn-theme">Vedi Film</a>
									</div>
								</div>
							</div>
							<div class="item">
								<img src="/CineJANA16/img/banner/titanic.jpg" alt="...">
								<div class="container">
									<!-- banner caption -->
									<div class="carousel-caption slideFoto">
										<!-- heading -->
										<h2 class="animated fadeInLeftBig">Titanic</h2>
										<!-- paragraph -->
										<h3 class="animated fadeInRightBig">C'era spazio anche per Jack!!!</h3>
										<!-- button -->
										<a href="/CineJANA16/preRicercaServlet" class="animated fadeIn btn btn-theme">Vedi Film</a>
									</div>
								</div>
							</div>
						</div>
	
						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
							<span class="fa fa-arrow-left" aria-hidden="true"></span>
						</a>
						<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
							<span class="fa fa-arrow-right" aria-hidden="true"></span>
						</a>
					</div>
				</div>
				<!--/ banner end -->
		
				
					<!-- block for animate navigation menu -->
			<div class="nav-animate"></div>

			</div>
	
			<jsp:include page="/jsp/public/footer.jsp"></jsp:include>
	</body>
</html>