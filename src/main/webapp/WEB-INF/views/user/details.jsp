<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
<div class="container">
    <div class="Book__details" *ngFor="let book of book">
        <div class="Book__image">
            <img src="{{book.Anh}}" alt="">
        </div>
        <div class="Book__info">
            <div class="Book__info-Name">
                Tên Sách
            </div>
            <div class="Book__info-Author">
                <span>Tác giả: <b>Tên Tác Giả</b> </span>
            </div>
            <div class="Book__info-Publishing-company">
                <span>Nhà xuất bản: <b>Tên NXB</b> </span>
            </div>
            <div class="Book__info-Price">
                <b class="price">300.000đ</b>
            </div>
            <div class="Book__info-Pay">
                <b>Số Lượng:</b>
                <div class="Book__info-Count">
                    <div class="Book__info-Button">
                        <button onclick="lessProducts()">
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/icons-remove.svg"
                                alt="remove-icon" width="20" height="20">
                        </button>
                        <input type="text" id="inputNum" value="1" placeholder="1" class="input">
                        <button onclick="moreProducts()">
                            <img src="https://frontend.tikicdn.com/_desktop-next/static/img/pdp_revamp_v2/icons-add.svg"
                                alt="add-icon" width="20" height="20">
                        </button>
                    </div>
                </div>
                <div class="Book__info-btnCart" onclick="addCart()">
                    <span>Chọn Mua</span>
                </div>
            </div>
        </div>
        <div class="Book__info-About">
            <h3>Thông Tin Sản Phẩm:</h3>
            <span>
                Mô Tả
            </span>
        </div>
    </div>
</div>
</body>