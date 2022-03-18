<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section class="py-5 my-5">
	<div class="container">
		${Messenger}
		<div class="bg-white shadow rounded-lg d-block d-sm-flex">
			<div class="profile-tab-nav border-right">
				<form action="myprofile" method="post" enctype="multipart/form-data">
					<div class="p-4">
						<div class="img-circle text-center mb-3">
							<img src="${pageContext.request.contextPath }/image/${Anh }"/>
						</div>
						<h4 class="text-center">${HoTen }</h4>
						<input type="file" name="imgchoose" accept="image/*">
					</div>
					<div class="nav flex-column nav-pills" id="v-pills-tab"
						role="tablist" aria-orientation="vertical">
						<a class="nav-link active" id="account-tab" data-toggle="pill"
							href="#account" role="tab" aria-controls="account"
							aria-selected="true"> <i class="fa fa-home text-center mr-1"></i>
							Cập Nhật Tài Khoản
						</a> <a class="nav-link" id="password-tab" data-toggle="pill"
							href="#password" role="tab" aria-controls="password"
							aria-selected="false"> <i class="fa fa-key text-center mr-1"></i>
							Đổi Mật Khẩu
						</a>
					</div>
			</div>
			<div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
				<div class="tab-pane fade show active" id="account" role="tabpanel"
					aria-labelledby="account-tab">
					<h3 class="mb-4">Account Settings</h3>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>Họ Và Tên</label> <input type="text" value="${HoTen }"
									class="form-control" name="HoTen" required>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Email</label> <input type="text" value="${Email }"
									class="form-control" name="Email" required>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Địa Chỉ</label> <input type="text" value="${DiachiKH }"
									class="form-control" name="DiaChi" required>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Số Điện Thoại</label> <input type="text"
									class="form-control" value="${DienthoaiKH }" name="SDT"
									required>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Ngày Sinh</label> <input type="date" value="${Ngaysinh }"
									class="form-control" name="Date" required>
							</div>
						</div>
					</div>
					<div>
						<button class="btn btn-primary">Cập Nhật</button>
					</div>
			</form>
		</div>
		<div class="tab-pane fade" id="password" role="tabpanel"
			aria-labelledby="password-tab">
			<h3 class="mb-4">Password Settings</h3>
			<form action="changepass" method="post">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Mật Khẩu Hiện Tại</label> <input type="password"
								class="form-control" name="oldpass" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Mật Khẩu Mới</label> <input type="password"
								class="form-control" name="newpass" required>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label>Xác Nhận Mật Khẩu</label> <input type="password"
								class="form-control" name="compass" required>
						</div>
					</div>
				</div>
				<div>
					<button class="btn btn-primary">Đổi Mật Khẩu</button>
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>
</section>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>