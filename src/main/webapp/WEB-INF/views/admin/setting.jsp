<%@page import="org.json.JSONObject"%>
<%@ page
	import="java.io.*,java.lang.*,java.util.*,java.net.*,java.util.*,java.text.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.http.HttpRequest"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div class="Admin__Setting-Container">
	<div class="Admin__Setting-Banner">
		<h1>Banner</h1>
		<form id='form' method="post"
			enctype='multipart/form-data'>
			<div class="Setting__Banner">
				<div class="Setting__Banner-Feature">
					<div>Banner 1</div>
					<input onchange="loadimg(event)" type="file" name="Banner1"
						id="Banner1" multiple>
				</div>
				<img id="anh1"
					src="https://i.pinimg.com/736x/86/54/42/8654429a4fbeb82f8210de503dc299ba.jpg"
					alt="">
				<button type="submit" class="Setting__Banner-Feature-Btn">Cập
					Nhật</button>
			</div>
		</form>
		<form action="/admin/setting" method="post">
			<div class="Setting__Banner">
				<div class="Setting__Banner-Feature">
					<div>Banner 2</div>
					<input onchange="loadimg2(event)" type="file" name="Banner2" id="">
				</div>
				<img id="anh2"
					src="https://i.pinimg.com/736x/86/54/42/8654429a4fbeb82f8210de503dc299ba.jpg"
					alt="">
				<div class="Setting__Banner-Feature-Btn">Cập Nhật</div>
			</div>
		</form>
		<form action="/admin/setting" method="post">
			<div class="Setting__Banner">
				<div class="Setting__Banner-Feature">
					<div>Banner 3</div>
					<input onchange="loadimg3(event)" type="file" name="Banner3" id="">
				</div>
				<img id="anh3"
					src="https://i.pinimg.com/736x/86/54/42/8654429a4fbeb82f8210de503dc299ba.jpg"
					alt="">
			</div>
			<div class="Setting__Banner-Feature-Btn">Cập Nhật</div>
		</form>
	</div>
	<div class="Admin__Setting-AddNew">
		<div class="Admin__Setting-Author">
			<h1>Thêm Tác Giả Mới</h1>
			<form class="Add__Container" method="POST">
				<div class="AddAuthor">
					<div>Tên Tác Giả</div>
					<input type="text" name="inputAuthorName" id="">
				</div>
				<div class="AddAuthor">
					<div>Địa Chỉ</div>
					<input type="text" name="inputAuthorAddr" id="">
				</div>
				<div class="AddAuthor">
					<div>Tiểu Sử</div>
					<input type="text" name="inputAuthorHist" id="">
				</div>
				<div class="AddAuthor">
					<div>Điện Thoại</div>
					<input type="tel" name="inputAuthorPhone" id="">
				</div>
				<input type="submit" class="Add__Btn" value="Thêm" />
			</form>
		</div>

		<div class="Admin__Setting-Author">
			<h1>Thêm Chủ Đề Mới</h1>
			<form method="POST">
				<div class="Add__Container">
					<div class="AddCategory">
						<div>Chủ Đề</div>
						<input type="text" name="inputCategory" id="">
					</div>
					<input type="submit" class="Add__Btn" value="Thêm" />
				</div>
			</form>

		</div>

		<div class="Admin__Setting-Author">
			<h1>Nhà Xuất Bản Mới</h1>
			<form class="Add__Container" method="POST">
				<div class="AddAuthor">
					<div>Tên Nhà Xuất Bản</div>
					<input type="text" name="inputNXB" id="inputNXB">
				</div>
				<div class="AddAuthor">
					<div>Địa Chỉ</div>
					<input type="text" name="inputAddress" id="inputAddress">
				</div>
				<div class="AddAuthor">
					<div>Số Điện Thoại</div>
					<input type="text" name="inputPhone" id="inputPhone">
				</div>
				<input type="submit" class="Add__Btn" value="Thêm" />
			</form>
		</div>
	</div>
</div>
</div>