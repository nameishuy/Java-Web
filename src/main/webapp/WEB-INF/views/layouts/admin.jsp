<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<style>
.Body__Container {
	width: 70%;
	margin: 0 auto;
}

.Admin__Container {
	width: 80vw;
	padding: 30px 0;
	margin: 30px auto;
	border-radius: 0.3rem;
	min-height: 80vh;
	border: 1px solid rgba(161, 161, 161, 0.596);
}

.Admin__Account-Header, .Admin__Account-Account-Details,
	.Admin__HistoryPay-Header, .Admin__HistoryPay-Details,
	.DialogDetailsPay__infoPay {
	margin: 20px 0 0 0;
	display: flex;
	align-items: center !important;
	font-weight: 400;
}

.Admin__Account-Header, .Admin__HistoryPay-Header,
	.DialogDetailsPay__infoPay {
	border-radius: 0.3rem;
	background-color: rgba(0, 0, 0, 0.815);
	color: white;
	height: 35px;
}

.Admin__Account-Body {
	display: flex;
	flex-flow: column nowrap;
}

.Checkbox__All-Account, .Checkbox__Account {
	display: flex;
	flex-basis: 10%;
}

.Title__info, .User__info {
	display: flex;
	justify-content: space-around;
	flex-basis: 60%;
}

.Title__setting, .User__setting {
	display: flex;
	justify-content: center;
	flex-basis: 30%;
}

.Admin__Account-Account-Details, .Admin__HistoryPay-Details {
	padding: 30px 0;
	border-top: 1px solid rgba(109, 109, 109, 0.308);
	border-bottom: 1px solid rgba(109, 109, 109, 0.308);
}

.UpdateAll__Setting {
	margin: 30px 0;
	padding: 10px;
	color: white;
	border-radius: 0.3rem;
	background-color: rgba(255, 0, 0, 0.637);
	width: fit-content;
	border: 1px solid rgba(109, 109, 109, 0.308);
	cursor: pointer;
	transition: 0.5s;
}

.UpdateAll__Setting:hover {
	background-color: red;
	box-shadow: 0 0 6px 0 red;
}

.Title__infoPay, .Bill__infoPay {
	display: flex;
	flex-basis: 70%;
	justify-content: space-around;
}

.Title__Setting, .Bill__Setting {
	display: flex;
	justify-content: center;
	flex-basis: 30%;
}

.Bill__Setting-details {
	border-radius: .3rem;
	background-color: rgba(255, 166, 0, 0.705);
	color: white;
	padding: 5px 10px;
	cursor: pointer;
	transition: 1s;
}

.Bill__Setting-details:hover {
	background-color: orange;
	box-shadow: 0 0 10px 0 orange;
}

.DialogDetailsPay__Container {
	z-index: 2;
	background-color: rgba(0, 0, 0, 0.651);
	width: 100%;
	min-height: 100vh;
	height: fit-content;
	position: absolute;
	transition: 1s;
	display: none;
	transition: 1s;
	animation: toVisible 1s 1 ease-in-out;
}

.DialogDetailsPay {
	margin: 100px auto;
	background-color: white;
	width: 40vw;
	min-height: 50vh;
	height: fit-content;
	box-shadow: 0 0 10px 0 black;
	border-radius: .5rem;
	position: relative;
}

.DialogDetailsPay__infoUser {
	padding-top: 50px;
	padding-left: 30px;
}

.DialogDetailsPay__infoUser-Details {
	display: flex;
	flex-flow: column nowrap;
}

.DialogDetailsPay__infoUser h1 {
	font-weight: 600;
	font-size: 20px;
}

.DialogDetailsPay__infoPay {
	border-radius: 0;
}

.DialogDetailsPay__infoPay-Details {
	display: flex;
	padding-bottom: 50px;
	padding-top: 10px;
}

.DialogDetailsPay__Title-Image, .DialogDetailsPay__Image {
	flex-basis: 30%;
	text-align: center;
}

.DialogDetailsPay__Image img {
	width: 60%;
	height: 150px;
}

.DialogDetailsPay__Title-BookName, .DialogDetailsPay__BookName {
	flex-basis: 30%;
	text-align: center;
}

.DialogDetailsPay__Title-Count, .DialogDetailsPay__Count {
	flex-basis: 20%;
	text-align: center;
}

.DialogDetailsPay__Title-Price, .DialogDetailsPay__Price {
	flex-basis: 20%;
	text-align: center;
}

.DialogDetailsPay__Price {
	color: red;
	font-weight: 600;
}

.DialogDetailsPay__CloseBtn {
	position: absolute;
	top: 0;
	right: 0;
	margin: 10px 15px 0 0;
	cursor: pointer;
}

.DialogDetailsPay__CloseBtn ion-icon {
	font-size: 30px;
	transition: 1s;
}

.DialogDetailsPay__CloseBtn ion-icon:hover {
	color: red;
}
/* Responsive */
@media screen and (max-width: 415px) {
	.Admin__Container {
		width: 95vw;
	}
	.Body__Container {
		width: 95%;
	}
	.Title__email, .Title__role, .User__email, .Bill__DatePay {
		display: none;
	}
	.Title__infoPay {
		flex-basis: 68%;
	}
	.DialogDetailsPay {
		width: 100%;
		border-radius: 0;
	}
}

.checkbox {
	margin: 0 10px;
	-webkit-appearance: none;
	appearance: none;
	background-color: #fff;
	font: inherit;
	color: currentColor;
	width: 1.15em;
	height: 1.15em;
	border: 0.15em solid currentColor;
	border-radius: 0.15em;
	display: grid;
	place-content: center;
}

.checkbox::before {
	content: "";
	width: 0.65em;
	height: 0.65em;
	clip-path: polygon(14% 44%, 0 65%, 50% 100%, 100% 16%, 80% 0%, 43% 62%);
	transform: scale(0);
	transform-origin: bottom left;
	transition: 120ms transform ease-in-out;
	box-shadow: inset 1em 1em var(- -form-control-color);
	/* Windows High Contrast Mode */
	background-color: CanvasText;
}

.checkbox:checked {
	background-color: red;
}

.checkbox:checked::before {
	transform: scale(1);
}

.checkbox:focus {
	outline: max(2px, 0.15em) solid currentColor;
	outline-offset: max(2px, 0.15em);
}

::ng-deep .mat-tab-group.mat-primary .mat-ink-bar, .mat-tab-nav-bar.mat-primary .mat-ink-bar
	{
	background-color: black;
}

@
keyframes toVisible {from { opacity:0;
	
}

to {
	opacity: 1;
}
}
</style>
	<%@include file="/WEB-INF/views/layouts/admin/header.jsp"%>
	<div class="DialogDetailsPay__Container" id="DialogDetailsPay__Container">
    <div class="DialogDetailsPay">
        <div class="DialogDetailsPay__CloseBtn" onclick="closeDialog()">
            <ion-icon name="close-circle-outline"></ion-icon>
        </div>
        <div class="DialogDetailsPay__infoUser">
            <h1>THÔNG TIN ĐƠN HÀNG</h1>
            <div class="DialogDetailsPay__infoUser-Details">
                <span>
                    <span style="font-weight: bold;">Tên Khách Hàng:</span> Doãn Chí Bình
                </span>
                <span>
                    <span style="font-weight: bold;">Mã Đơn Hàng:</span> E0531
                </span>
                <span>
                    <span style="font-weight: bold;">Ngày Đặt Hàng:</span> 12/3/2022
                </span>
                <span>
                    <span style="font-weight: bold;">Tổng Tiền:</span><span style="color: red; font-weight: 600;"> 300.000đ</span>
                </span>
                <span>
                    <span style="font-weight: bold;">Tình Trạng: </span><span style="color: red; font-weight: 600;"> Chưa Thanh Toán</span> 
                </span>
            </div>
        </div>
        <div class="DialogDetailsPay__infoPay">
            <div class="DialogDetailsPay__Title-Image">Ảnh</div>
            <div class="DialogDetailsPay__Title-BookName">Tên Sách</div>
            <div class="DialogDetailsPay__Title-Count">Số Lượng</div>
            <div class="DialogDetailsPay__Title-Price">Thành Tiền</div>
        </div>
        <div class="DialogDetailsPay__infoPay-Details">
            <div class="DialogDetailsPay__Image">
                <img src="https://taisachmoi.com/wp-content/uploads/2018/12/dac-nhan-tam.jpg" alt="">
            </div>
            <div class="DialogDetailsPay__BookName">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quibusdam, culpa, ipsum nisi tempore aliquid reprehenderit debitis harum sapiente iste.</div>
            <div class="DialogDetailsPay__Count">3</div>
            <div class="DialogDetailsPay__Price">300.000đ</div>
        </div>
    </div>
</div>
	<div class="Body__Container">
		<decorator:body />
	</div>

	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script>
		function showDialog() {
			let details = document
					.getElementById("DialogDetailsPay__Container");
			details.style.display = "block";
		}
		function closeDialog() {
			let details = document
					.getElementById("DialogDetailsPay__Container");
			details.style.display = "none";
		}
	</script>
</body>
</html>