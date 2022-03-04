<%@page import="org.springframework.web.context.annotation.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav
	class="navbar navbar-expand-md navbar-light border-bottom border-3 align-items-center">
	<div class="container-fluid justify-content-around align-items-center">
		<a class="navbar-brand d-flex align-items-center me-md-auto ms-md-5"
			href="home"> <ion-icon name="book-outline"></ion-icon> MBook
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarMenu" aria-controls="navbarMenu"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-md-center"
			id="navbarMenu">
			<ul class="navbar-nav text-center p-md-3 mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="home">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Product</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
			</ul>
		</div>
		<%
		if (session.getAttribute("id") != null) {
		%>
		<div class="dropdown " id="Account_Info">
			<button
				class="dropdown-toggle bg-dark text-white rounded-pill ms-5 d-flex align-items-center"
				type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown"
				aria-expanded="false">
				<ion-icon name="person-circle-outline"></ion-icon>
				${sessionScope.User }
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
				<li><a class="dropdown-item" id="Item__Account" href="myprofile">Profile</a></li>
				<li><a class="dropdown-item" id="Item__Account" href="#">Admin</a></li>
				<li>
					<hr class="dropdown-divider">
				</li>
				<li><a class="dropdown-item" id="Item__Account" href="signout">Sign
						Out</a></li>
			</ul>
		</div>

		<%
		} else {
		%>
		<div class="Account__Area align-items-center" id="Account_Area">
			<a
				class="text-decoration-none link-dark border border-dark rounded-1 pe-3 ps-3 pt-1 pb-1"
				id="SignIn" href="signin">Sign in</a> / <a
				class="text-decoration-none link-light bg-dark border border-dark rounded-1 pe-3 ps-3 pt-1 pb-1"
				id="SignUp" href="signup">Sign up</a>
		</div>
		<%
		}
		%>
	</div>
</nav>