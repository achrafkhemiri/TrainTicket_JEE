<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
/* Base navbar */
.navbar {
	background-color: #ffffff;
	border-bottom: 1px solid #ddd;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 14px 30px;
	font-family: 'Segoe UI', sans-serif;
	position: sticky;
	top: 0;
	z-index: 999;
}

.navbar .logo {
	font-size: 1.4rem;
	font-weight: 600;
	color: #3498db;
	display: flex;
	align-items: center;
	gap: 8px;
}

.navbar .logo i {
	font-size: 1.2em;
}

/* Nav links */
.navbar ul {
	list-style: none;
	display: flex;
	gap: 30px;
	margin: 0;
	padding: 0;
}

.navbar ul li a {
	color: #2c3e50;
	text-decoration: none;
	font-size: 1em;
	font-weight: 500;
	transition: color 0.3s ease, border-bottom 0.3s ease;
	position: relative;
}

.navbar ul li a::after {
	content: '';
	position: absolute;
	width: 0%;
	height: 2px;
	bottom: -4px;
	left: 0;
	background-color: #3498db;
	transition: width 0.3s ease;
}

.navbar ul li a:hover::after {
	width: 100%;
}

.navbar ul li a:hover {
	color: #3498db;
}

/* Mobile responsiveness */
.menu-toggle {
	display: none;
	font-size: 1.4em;
	color: #2c3e50;
	cursor: pointer;
}

@media ( max-width : 768px) {
	.navbar ul {
		display: none;
		flex-direction: column;
		gap: 16px;
		background: #ffffff;
		position: absolute;
		top: 60px;
		right: 30px;
		padding: 20px;
		box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
		border-radius: 8px;
	}
	.navbar ul.show {
		display: flex;
	}
	.menu-toggle {
		display: block;
	}
}
</style>

<script>
	function toggleMenu() {
		const nav = document.querySelector('.navbar ul');
		nav.classList.toggle('show');
	}
</script>

<!-- Navbar HTML -->
<div class="navbar">
	<div class="logo">
		<i class="fas fa-train"></i> BilletExpress
	</div>
	<i class="fas fa-bars menu-toggle" onclick="toggleMenu()"></i>
<ul>
	<li><a href="${pageContext.request.contextPath}/ViewsClient/welcome.jsp">
		<i class="fas fa-home"></i> Accueil</a></li>

	<li><a href="${pageContext.request.contextPath}/ListeVoyage">
		<i class="fas fa-list"></i> Voyages</a></li>

	<li><a href="${pageContext.request.contextPath}/ViewsClient/home.jsp">
		<i class="fas fa-search"></i> Rechercher</a></li>

	<c:choose>
		
		<c:when test="${not empty sessionScope.utilisateur}">
			<li><a href="${pageContext.request.contextPath}/ReservationController?action=mesReservations">
				<i class="fas fa-ticket-alt"></i> Mes Réservations</a></li>

			<li><a href="${pageContext.request.contextPath}/ViewsClient/editProfile.jsp">
				<i class="fas fa-user"></i> Profil</a></li>

			<li><a href="${pageContext.request.contextPath}/LogoutController" class="logout-link">
				<i class="fas fa-sign-out-alt"></i> Déconnexion</a></li>
		</c:when>

		
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/login.jsp">
				<i class="fas fa-sign-in-alt"></i> Connexion</a></li>
		</c:otherwise>
	</c:choose>
</ul>


</div>
