<%@page import="JavaWebMVC.Controller.BookController"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<body>
	<div class="Product__Container">
		<div class="Product__ListCategory">
			<h6>THỂ LOẠI SẢN PHẨM</h6>
			<ul>
				<c:forEach var="data" items="${Chude }">
					<li><a href="products?chude=${data.get_id()}">${data.getTenChuDe()}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="Product__ListProduct">
			<form action="products" method="post">
				<div class="Product__ListProduct-Sort">
					<input type="text" name="keyword" class="form-control"
						placeholder="Search..." />
					<div class="Product__ListProduct-SortArea">
						<h5>Sắp xếp theo:</h5>
						<select class="form-select" name="Sort" aria-label="-- Loại Sắp Xếp --">
							<option selected>Giá Tăng Dần</option>
							<option value="1">Giá Giảm Dần</option>
						</select>
					</div>
				</div>
			</form>
			<div class="Product__List">
				<%
				int last = 8, pages = 1;
				String IDCHUDE = request.getParameter("chude"), keyword = request.getParameter("keyword");

				if (request.getParameter("pages") != null) {
					pages = (int) Integer.parseInt(request.getParameter("pages"));
				}

				//Lấy tổng sản phẩm trong
				int total = new BookController().Cout(request);

				List<Model.Book> list = new BookController().getList(request, pages, last);
				for (Model.Book item : list) {
					if (item.getMessager() != null) {
				%>
				<h4><%=item.getMessager()%></h4>
				<%
				} else {
				%>

				<a class="Book" href="details?id=<%=item.getID()%>">
					<div class="Book__Img">
						<img src="<%=item.getAnh()%>" alt="">
					</div>
					<div class="Book__Content">
						<div class="Book__Content-BookName">
							<h3><%=item.getTensach()%></h3>
							<p class="Book__Content-Author"><%=item.getTenTG()%></p>
							<p class="Book__Content-Price">
								<fmt:formatNumber type="number" pattern="#,###0.000"
									value="<%=item.getGiaban()%>" />
								đ
							</p>
						</div>
					</div>
				</a>
				<%
				}
				}
				%>
			</div>
			<ul class="pagination" id="pagination">
				<%
				//Button Number pages
				int loop = 0, num = 0;
				if ((total / 8) % 2 == 0) {
					num = total / 8;
				} else {
					num = (total + 1) / 8;
				}
				//Nếu total lẻ thêm 1
				if (total % 2 != 0) {
					loop = (total / 8) + 1;
				} else {
					//Nếu total chẵn nhỏ hơn fullpage và # fullPage thì thêm 1
					if (total < (num * 8) + 8 && total != num * 8) {
						loop = (total / 8) + 1;
					} else {
						//Nếu bằng fullPage thì không thêm
						loop = (total / 8);
					}
				}
				//Lap so pages
				for (int i = 1; i <= loop; i++) {
				%>
				<%
				if (IDCHUDE != null) {
					if (pages == i) {
				%>
				<li class="page-item active"><a class="page-link"
					href="products?pages=<%=i%>&chude=<%=IDCHUDE%>"><%=i%></a></li>
				<%
				} else {
				%>
				<li class="page-item"><a class="page-link"
					href="products?pages=<%=i%>&chude=<%=IDCHUDE%>"><%=i%></a></li>
				<%
				}
				} else if (keyword != null) {
				if (pages == i) {
				%>
				<li class="page-item active"><a class="page-link"
					href="products?pages=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>
				<%
				} else {
				%>
				<li class="page-item"><a class="page-link"
					href="products?pages=<%=i%>&keyword=<%=keyword%>"><%=i%></a></li>
				<%
				}
				} else {
				if (pages == i) {
				%>
				<li class="page-item active"><a class="page-link"
					href="products?pages=<%=i%>"><%=i%></a></li>
				<%
				} else {
				%>
				<li class="page-item"><a class="page-link"
					href="products?pages=<%=i%>"><%=i%></a></li>
				<%
				}

				}
				}
				%>
			</ul>
		</div>
	</div>
</body>