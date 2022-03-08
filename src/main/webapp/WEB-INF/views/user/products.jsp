<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="JavaWebMVC.Controller.HomeController"%>
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
			<div class="Product__ListProduct-Sort">
				<input type="text" class="form-control" placeholder="Search..." />
				<div class="Product__ListProduct-SortArea">
					<h5>Sắp xếp theo:</h5>
					<select class="form-select" aria-label="-- Loại Sắp Xếp --">
						<option selected>Giá Tăng Dần</option>
						<option value="1">Giá Giảm Dần</option>
					</select>
				</div>
			</div>
			<div class="Product__List">
				<%
				int last = 4, pages = 1;
				String link = "sachpagination", IDCHUDE = null;

				if (request.getParameter("pages") != null) {
					pages = (int) Integer.parseInt(request.getParameter("pages"));
				}
				if (request.getParameter("chude") != null) {
					link = "sachpaginationbychude/" + request.getParameter("chude");
					IDCHUDE = request.getParameter("chude");
				}
				//Lấy tổng sản phẩm trong data bằng query select count(id) from name_table với JDBC Connect
				int total = new HomeController().Cout(IDCHUDE);

				List<Model.Book> list = new HomeController().getList(link, pages, last);
				for (Model.Book item : list) {
				%>
				<div class="Product__List">
					<div class="Book">
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
								</p>
							</div>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
			<ul class="pagination" id="pagination">
				<%
				//Button Number pages
				int loop = 0, num = 0;
				if ((total / 4) % 2 == 0) {
					num = total / 4;
				} else {
					num = (total + 1) / 4;
				}
				//Nếu total lẻ thêm 1
				if (total % 2 != 0) {
					loop = (total / 4) + 1;

				} else {
					//Nếu total chẵn nhỏ hơn fullpage và # fullPage thì thêm 1
					if (total < (num * 4) + 4 && total != num * 4) {
						loop = (total / 4) + 1;
					} else {
						//Nếu bằng fullPage thì không thêm
						loop = (total / 4);
					}
				}
				//Lap so pages
				for (int i = 1; i <= loop; i++) {
				%>
				<%
				if (pages == i) {
				%>
				<li class="page-item"><a class="page-link"
					href="products?pages=<%=i%>&chude=<%=IDCHUDE%>"><%=i%></a></li>
				<%
				} else {
				%>
				<li class="page-item"><a class="page-link"
					href="products?pages=<%=i%>&chude=<%=IDCHUDE%>"><%=i%></a></li>
				<%
				}
				}
				%>
			</ul>
		</div>
	</div>
</body>