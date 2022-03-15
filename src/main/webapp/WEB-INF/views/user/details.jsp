<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<body>
	<div class="Details__container">
		<div class="Book__details">
			<c:forEach var="data" items="${Book}">
				<div class="Book__image">
					<img src="${data.getAnh() }" alt="">
				</div>

				<div class="Book__info">
					<div class="Book__info-Name">${data.getTensach()}</div>
					<div class="Book__info-Author">
						<span>Tác giả: <b>${data.getTenTG()}</b>
						</span>
					</div>
					<div class="Book__info-Publishing-company">
						<span>Nhà xuất bản: <b>${data.gettenNXB()}</b>
						</span>
					</div>
					<div class="Book__info-Price">
						<b class="price"> <fmt:formatNumber type="number"
								pattern="#,###0.000" value="${data.getGiaban()}" />đ
						</b>
					</div>
					<div class="Book__info-Pay">
						<b>Số Lượng:</b>
						<c:choose>
							<c:when test="${data.getSoluongton() > 0 }">
								<div class="Book__info-Count">
									<div class="Book__info-Button">
										<button onclick="lessProducts()">
											<img
												src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/icons-remove.svg"
												alt="remove-icon" width="20" height="20">
										</button>
										<input type="text" id="inputNum" value="1" placeholder="1"
											class="input">
										<button onclick="moreProducts()">
											<img
												src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/icons-add.svg"
												alt="add-icon" width="20" height="20">
										</button>
									</div>
								</div>
								<div class="Book__info-btnCart" onclick="addCart()">
									<span>Chọn Mua</span>
								</div>
							</c:when>
							<c:otherwise>
								<b style="color:red;">Đã Hết Hàng</b>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="Book__info-About">
					<h3>Thông Tin Sản Phẩm:</h3>
					<span>${data.getMota()}</span>
				</div>
			</c:forEach>
		</div>
	</div>
</body>