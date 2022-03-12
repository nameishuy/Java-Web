<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
	<div class="Admin__Account-Header">
                <div class="Checkbox__All-Account">
                    <input type="checkbox" name="" class="checkbox" id="checkbox__all-account" (click)="onCheckAll()">
                    All
                </div>
                <div class="Title__info">
                    <div class="Title__username">Tài Khoản</div>
                    <div class="Title__email">Email</div>
                    <div class="Title__role">Loại Tài Khoản</div>
                </div>
                <div class="Title__setting">Thiết Lập</div>
            </div>
            <div class="Admin__Account-Body">
                <div class="Admin__Account-Account-Details" *ngFor="let user of user |orderBy: key :reverse ">
                    <div class="Checkbox__Account">
                        <input type="checkbox" name="" class="checkbox" id="checkbox__account">
                    </div>
                    <div class="User__info">
                        <div class="User__username">Tài Khoản</div>
                        <div class="User__email" >Email</div>
                        <div class="User__role">Admin</div>
                    </div>
                    <div class="User__setting">Thiết Lập</div>
                </div>
                <div class="UpdateAll__Setting">
                    Thay Đổi Toàn Bộ
                </div>
            </div>
</body>
