<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body>
	<div class="Product__Container">
		<div class="Product__ListCategory">
			<h6>THỂ LOẠI SẢN PHẨM</h6>
			<ul>
				<li><a></a></li>
			</ul>
		</div>
		<div class="Product__ListProduct">
			<div class="Product__ListProduct-Sort">
				<input type="text" class="form-control" placeholder="Search..." />
				<div class="Product__ListProduct-SortArea">
					<h4>Sắp xếp theo:</h4>
					<select class="form-select" aria-label="-- Loại Sắp Xếp --">
						<option selected>Giá Tăng Dần</option>
						<option value="1">Giá Giảm Dần</option>
					</select>
				</div>
			</div>
			<div class="Product__List"></div>
			<div class="Product__List">
				<div class="Book">
					<div class="Book__Img">
						<img src="https://www.davibooks.vn/stores/uploads/u/b4__78188.jpg"
							alt="">
					</div>
					<div class="Book__Content">
						<div class="Book__Content-BookName">
							<h3>Đắc Nhân Tâm</h3>
							<p class="Book__Content-Author">Nguyễn Văn Phước</p>
							<p class="Book__Content-Price">1đ</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>