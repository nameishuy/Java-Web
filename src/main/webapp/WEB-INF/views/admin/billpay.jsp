<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
		                <div class="Bill__Setting-details" onclick="showDialog()">Chi Tiết</div>
		            </div>
	        </div>
      	</c:forEach>
    </div>