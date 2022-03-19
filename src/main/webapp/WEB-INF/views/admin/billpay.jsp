<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="Admin__HistoryPay-Header">

	<div class="Title__BillID">Mã Đơn</div>
	<div class="Title__Username">Tên Khách</div>
	<div class="Title__DatePay">Thời Gian Đặt</div>

	<div class="Title__Setting">Thiết Lập</div>
</div>
<div class="Admin__HistoryPay-Body">
	<c:forEach var="data" items="${listBill }">
		<div class="Admin__HistoryPay-Details">
			<div class="Bill__BillID">${data.getId() }</div>
			<div class="Bill__Username">${data.getUsername() }</div>
			<div class="Bill__DatePay">${data.getDate() }</div>

			<div class="Bill__Setting">
				<div class="Bill__Setting-details" onclick="showDialog('${data.getId() }','${data.getUsername() }','${data.getDate() }','${data.getTongTien() }')">Chi
					Tiết</div>
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
		<li class="page-item"><a class="page-link" href="?pages=<%=i%>"><%=i%></a></li>
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
</div>