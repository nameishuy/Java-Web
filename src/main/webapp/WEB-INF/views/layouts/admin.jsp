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

.Admin__Account-Header, 
.Admin__Account-Account-Details,
.Admin__HistoryPay-Header, 
.Admin__HistoryPay-Details,
.DialogDetailsPay__infoPay,
.Admin__Storage-Header,
.Admin__Storage-Details{
	margin: 20px 0 0 0;
	display: flex;
	align-items: center !important;
	font-weight: 400;
}

.Admin__Account-Header, 
.Admin__HistoryPay-Header,
.DialogDetailsPay__infoPay,
.Admin__Storage-Header {
	border-radius: 0.3rem;
	background-color: rgba(0, 0, 0, 0.815);
	color: white;
	height: 35px;
}

.Admin__Account-Body {
	display: flex;
	flex-flow: column nowrap;
}

.Checkbox__All-Account, 
.Checkbox__Account {
	display: flex;
	flex-basis: 10%;
	justify-content: center;
}

.Title__email,
.Title__username,
.Title__role,
.User__username,
.User__email,
.User__role{
  flex-basis: 20%;
  text-align: center;
}

.Title__setting, .User__setting {
	display: flex;
	justify-content: center;
	flex-basis: 30%;
}

.Admin__Account-Account-Details, 
.Admin__HistoryPay-Details,
.Admin__Storage-Details {
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

.Title__DatePay,
.Title__Username,
.Bill__DatePay,
.Bill__Username{
  flex-basis: 20%;
  text-align: center;
}
.Bill__BillID,
.Title__BillID{
  text-align: center;
  flex-basis: 30%;
}

.Title__Setting,
.Bill__Setting{
  display: flex;
  justify-content: center;
  flex-basis: 30%;
}
.User__setting-deleteAccount{
	text-align: center;
	width: fit-content;
  }
.Bill__Setting-details,
.User__setting-deleteAccount,
.DeleteAccount__Setting-details,
.Setting__Banner-Feature-Btn,
.DialogChangeDetailsProduct__Setting-details{
	border-radius: .3rem;
	background-color: rgba(255, 166, 0, 0.705);
	color: white;
	padding: 5px 10px;
	cursor: pointer;
	transition: 1s;
}
.DeleteAccount__Setting,
.DialogChangeDetailsProduct__Setting-details{
  display: flex;
  margin-left: 30px;
  width: fit-content;
}
.DeleteAccount__Setting-YES{
  margin-right: 20px;
}
.Bill__Setting-details:hover,
.DeleteAccount__Setting-details:hover,
.Setting__Banner-Feature-Btn:hover,
.DialogChangeDetailsProduct__Setting-details:Hover{
	background-color: orange;
	box-shadow: 0 0 10px 0 orange;
}

.DialogDetailsPay__Container,
.DialogDeleteAccount__Container,
.DialogChangeDetailsProduct__Container{
	z-index: 2;
	background-color: rgba(0, 0, 0, 0.651);
	width: 100%;
	min-height: 100vh;
	height: fit-content;
	position: absolute;
	transition: 1s;
	display: none;
	transition: 1s;
	animation: toVisible 1s 1 ease-in-out !important;
}

.DialogDetailsPay,
.DialogDeleteAccount,
.DialogChangeDetailsProduct{
	margin: 100px auto;
	background-color: white;
	width: 40vw;
	min-height: 50vh;
	height: fit-content;
	box-shadow: 0 0 10px 0 black;
	border-radius: .5rem;
	position: relative;
}
.DialogDeleteAccount{
	min-height: 30vh;
  }
.DialogDetailsPay__infoUser,
.DialogDeleteAccount__infoUser,
.DialogChangeDetailsProduct__changeDetails {
	padding-top: 50px;
	padding-left: 30px;
}
.DialogChangeDetailsProduct__changeDetails{
	display: flex;
	padding-bottom: 30px;
	flex-flow: column nowrap;
  }
  .DialogChangeDetailsProduct__changeDetails input{
	margin: 10px 0;
	width: 100px;
  }
.DialogDetailsPay__infoUser-Details {
	display: flex;
	flex-flow: column nowrap;
}

.DialogDetailsPay__infoUser h1,
.DialogDeleteAccount__infoUser h1{
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

.DialogDetailsPay__CloseBtn,
.DialogDeleteAccount__CloseBtn,
.DialogChangeDetailsProduct__CloseBtn{
	position: absolute;
	top: 0;
	right: 0;
	margin: 10px 15px 0 0;
	cursor: pointer;
}

.DialogDetailsPay__CloseBtn ion-icon,
.DialogChangeDetailsProduct__CloseBtn ion-icon,
.DialogDeleteAccount__CloseBtn ion-icon{
	font-size: 30px;
	transition: 1s;
}

.DialogDetailsPay__CloseBtn ion-icon:hover,
.DialogChangeDetailsProduct__CloseBtn ion-icon:hover,
.DialogDeleteAccount__CloseBtn ion-icon:hover{
	color: red;
}
.Admin__Setting-Container{
	width: 70%;
	min-height: 70vh;
	margin: 40px auto 40px auto;
  }
  .Admin__Setting-Banner{
	position: relative;
	border: 1px solid black;
	padding: 20px;
	border-radius: .5rem;
  }
  .Admin__Setting-Banner h1{
	position: absolute;
	top: 0;
	left: 0;
	transform: translate(20px,-30px);
	background-color: white;
  }
  .Setting__Banner-Feature{
	display: flex;
	width: fit-content;
	margin-bottom: 10px;
	margin-top: 10px;
	align-items: center;
  }
  .Setting__Banner-Feature div{
	margin-right: 10px;
  }
  .Setting__Banner-Feature input,
  .DialogChangeDetailsProduct__Setting-details{
	width: fit-content;
  }
  .Setting__Banner img{
	width: 70%;
  }
  .Admin__Setting-Products{
	margin-top: 20px;
  }
  .Setting__Banner-Feature-Btn{
	margin-top: 10px;
	width: fit-content;
  }
  
  .Title__Product-Image,
  .Product__Image,
  .Title__Product-Name,
  .Title__Product-Count,
  .Title__Product-Price,
  .Product__Name,
  .Product__Count,
  .Product__Price{
	flex-basis: 20%;
	text-align: center;
  }
  .Product__Image img{
	width: 50%;
  }
  .DialogChangeDetailsProduct{
	width: 15vw;
  }
/* Responsive */
@media screen and (max-width: 415px) {
	.Admin__Container {
		width: 95vw;
	}
	.Body__Container {
		width: 95%;
	}

  .Title__email,
  .User__email,
  .Bill__DatePay,
  .Title__DatePay,
  .Title__Product-Image,
  .Product__Image{
    display: none;
  }
  .Title__username,
  .User__username,
  .Title__role,
  .User__role,
  .Title__Product-Count,
  .Product__Count{
    text-align: center;
    flex-basis: 30%;
  }

  .DialogDetailsPay{
    width: 100%;
    border-radius: 0;
  }
  .Setting__Banner-Feature{
    width: 100%;
    display: flex;
    flex-wrap: wrap;
  }
  .DialogChangeDetailsProduct{
    width: 50vw;
    min-height: 30vh;
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

@keyframes toVisible {
	from { 
		opacity:0;
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
    <div class="DialogDeleteAccount__Container" id="Dialog_Messenger">
        <div class="DialogDeleteAccount">
            <div class="DialogDeleteAccount__CloseBtn" onclick="closeDialogDeleteAccount()">
                <ion-icon name="close-circle-outline"></ion-icon>
            </div>
            <div class="DialogDeleteAccount__infoUser">
                <h1 style="margin: auto;">Bạn Muốn Xóa Tài Khoản Này?</h1>
                <h1>Tất Cả Dữ Liệu Của Tài Khoản Này Sẽ Bị Mất</h1>
            </div>
            <div class="DeleteAccount__Setting">
                <div class="DeleteAccount__Setting-details DeleteAccount__Setting-YES">
                    Yes
                </div>
                <div class="DeleteAccount__Setting-details" onclick="closeDialogDeleteAccount()">
                    No
                </div>
            </div>
        </div>
    </div>
    <div class="DialogChangeDetailsProduct__Container" id="DialogChangeDetailsProduct__Container">
        <div class="DialogChangeDetailsProduct">
            <div class="DialogChangeDetailsProduct__CloseBtn" onclick="closeDialogChangeDetails()">
                <ion-icon name="close-circle-outline"></ion-icon>
            </div>
            <div class="DialogChangeDetailsProduct__changeDetails">
                <h1>Cập Nhật</h1>
                Thêm số lượng tồn: <input type="text" name="storageNum" id="">
                Giá: <input type="text" name="storagePrice" id="">
            </div>
            <div class="DialogChangeDetailsProduct__Setting-details DialogChangeDetailsProduct__Setting-YES">
                Cập Nhật
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
        let details = document.getElementById("DialogDetailsPay__Container");
        details.style.display = "block";
    }
    function closeDialog() {
        let details = document.getElementById("DialogDetailsPay__Container");
        details.style.display = "none";
    }

    function showDialogDeleteAccount(){
        let dialog = document.getElementById("Dialog_Messenger");
        dialog.style.display = "block";
    }

    function closeDialogDeleteAccount(){
        let dialog = document.getElementById("Dialog_Messenger");
        dialog.style.display = "none";
    }
    function closeDialogChangeDetails(){
        let dialog = document.getElementById("DialogChangeDetailsProduct__Container");
        dialog.style.display="none";
    }

    function showDialogChangeDetailsProduct(){
        let dialog = document.getElementById("DialogChangeDetailsProduct__Container");
        dialog.style.display="block";
    }
	</script>
</body>
</html>