<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="Admin__Storage-Header">

	<div class="Title__Product-Image">Ảnh</div>
	<div class="Title__Product-Name">Tên Sách</div>
	<div class="Title__Product-Count">Số Lượng Tồn</div>
	<div class="Title__Product-Price">Giá</div>

	<div class="Title__Setting">Thiết Lập</div>
</div>
<div class="Admin__Storage-Body">
	<c:forEach var="data" items="${listBook }">
		<div class="Admin__Storage-Details">
			<div class="Product__Image">
				<img src="${data.getAnh()}" alt="">
			</div>
			<div class="Product__Name">${data.getTensach()}</div>
			<div class="Product__Count">${data.getSoluongton()}</div>
			<div class="Product__Price"><fmt:formatNumber type="number" pattern="#,###0.000" value="${data.getGiaban()}" />đ</div>
			<div class="Bill__Setting">
				<div class="Bill__Setting-details"
					onclick="showDialogChangeDetailsProduct()">Thay Đổi</div>
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