<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="Model.Bill"%>
<%@page import="Model.Pagination"%>
<%@page import="Model.config"%>
<%@page import="java.util.ArrayList"%>
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
	<%
	ArrayList<config> cg = new ArrayList<config>();
	config con = new config();
	con.setApi("PhanTrangDonHang");
	con.setCurrent_page(request.getParameter("pages") != null ? Integer.parseInt(request.getParameter("pages")) : 1);
	con.setLimit(4);
	con.setLink_full("?pages={page}");
	con.setLink_first("/JavaWebMVC/admin/bill-pay");
	con.setRange(3);
	cg.add(con);

	Pagination pg = new Pagination();
	pg.init(cg);

	ArrayList<Bill> listBill = new ArrayList<Bill>();

	JSONArray data = new JSONArray(pg.Getlist());

	data.forEach(d -> {
		JSONObject json = (JSONObject) d;
		Bill bill = new Bill();		
		String date = json.getString("Ngaydat").substring(0, 10);
		bill.setDate(date);
		bill.setId(json.getString("id"));
		bill.setUsername(json.getString("HoTen"));
		bill.setTongTien(json.getDouble("TongTien"));
		listBill.add(bill);
	});
	%>
	<c:forEach var="data" items="<%=listBill%>">
		<div class="Admin__HistoryPay-Details">
			<div class="Bill__BillID">${data.getId() }</div>
			<div class="Bill__Username">${data.getUsername() }</div>			
			<div class="Bill__DatePay">${data.getDate() }</div>

			<div class="Bill__Setting">
				<div class="Bill__Setting-details"
					onclick="showDialog('${data.getId() }','${data.getUsername() }','${data.getDate() }','${data.getTongTien() }')">Chi
					Tiết</div>
			</div>
		</div>
	</c:forEach>
	<%=pg.html()%>
</div>