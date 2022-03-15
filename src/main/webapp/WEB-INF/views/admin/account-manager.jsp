<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<div class="Admin__Account-Header">
		<div class="Checkbox__All-Account"></div>
		<div class="Title__username">Tài Khoản</div>
		<div class="Title__email">Email</div>
		<div class="Title__role">Loại Tài Khoản</div>

		<div class="Title__setting">Thiết Lập</div>
	</div>
	<div class="Admin__Account-Body">
		<c:forEach var="data" items="${listUser }">
			<div class="Admin__Account-Account-Details">
				<div class="Checkbox__Account">
					<input type="checkbox" name="" class="checkbox"
						id="checkbox__account" onclick="ifChecked()" )>
				</div>

				<div class="User__username">${data.getHoTen() }</div>
				<div class="User__email">${data.getEmail() }</div>

				<c:if test="${data.getRole() == false}">
					<div class="User__role">Khách</div>
				</c:if>

				<c:if test="${data.getRole() == true}">
					<div class="User__role">Admin</div>
				</c:if>
				<div class="User__setting">
					<div class="User__setting-deleteAccount"
						onclick="showDialogDeleteAccount()">Xóa Tài Khoản</div>
				</div>
			</div>
		</c:forEach>

		<ul class="pagination" id="pagination">
			<%
			//Lap so pages
			int pages = Integer.parseInt(request.getAttribute("pages").toString());
			int TotalPage = Integer.parseInt(request.getAttribute("TotalPage").toString());
			if (pages > 1 && TotalPage > 1) {
			%>
			<li class="page-item active"><a class="page-link"
				href="?pages=<%=(pages - 1)%>">Prev</a></li>
			<%
			}

			for (int i = 1; i <= TotalPage; i++) {
			if (pages == i) {
			%>
			<li class="page-item active"><a class="page-link"
				href="?pages=<%=i%>"><%=i%></a></li>
			<%
			} else {
			%>
			<li class="page-item"><a class="page-link"
				href="?pages=<%=i%>"><%=i%></a></li>
			<%
			}

			}

			if (pages < TotalPage && TotalPage > 1) {
			%>
			<li class="page-item active"><a class="page-link"
				href="?pages=<%=(pages + 1)%>">Next</a></li>
			<%
			}
			%>
		</ul>
		<div class="UpdateAll__Setting" id="UpdateAll">Cấp Quyền Admin</div>
	</div>
</body>
