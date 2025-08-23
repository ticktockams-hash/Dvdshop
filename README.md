商城訂單與商品管理系統
專案概述
這是一個用於下單和管理會員、商品及訂單的電商系統 。主要功能包括：


商品管理：新增、修改、查詢商品 。


訂單管理：新增、修改、刪除、查詢訂單 。


員工資料管理：新增、修改、刪除員工資料 。


會員資料管理：新增、修改、刪除會員資料 。


報表功能：查看銷售圖表 。
好的，這是您 PowerPoint 檔案的優化版本，內容經過重新組織，並翻譯成更專業、清晰的中文。

商城訂單與商品管理系統
專案概述
這是一個用於下單和管理會員、商品及訂單的電商系統 。主要功能包括：


商品管理：新增、修改、查詢商品 。


訂單管理：新增、修改、刪除、查詢訂單 。


員工資料管理：新增、修改、刪除員工資料 。


會員資料管理：新增、修改、刪除會員資料 。


報表功能：查看銷售圖表 。

系統設計與架構
本系統採用 

MVC (Model-View-Controller) 架構 進行設計 。每個部分都組織在對應的資料夾中，職責分明：


Controller（控制層）：負責使用者介面（UI）與業務邏輯 。使用 

JFrame 設計介面，並與 Service 層互動 。


Model（資料模型層）：定義對應 MySQL 資料表的類別（POJO）。



DAO（資料存取層）：負責直接與 MySQL 資料庫進行 CRUD（新增、查詢、更新、刪除）操作 。



Service（業務邏輯層）：負責處理 DAO 操作和交易管理 。



Util（工具類）：提供 MySQL 連線與其他工具函式 。
程式目錄結構
專案結構清晰且具邏輯性，便於管理：

controller (控制層)：


Login.java：登入介面 。

employ/：


AdminManager.java：管理介面 。


EmployManager.java：員工管理介面 。

member/：


LoginRegister.java：註冊介面 。


MemberManager.java：會員管理介面 。

porder/：


AddPorderjava：商城介面 。


Confirm.java：購物車確認介面 。


FindPorderManager.java：我的訂單介面 。

product/：


AdminPorderManager.java：會員訂單查詢介面 。


ProductManager.java：商品管理介面 。

report/：


ProductChart.java：銷售圖表介面 。

dao (資料存取層)：


EmployDao.java：員工資料存取介面 。


MemberDao.java：會員資料存取介面 。


PorderDao.java：訂單資料存取介面 。


ProductDao：產品資料存取介面 。


impl/：包含所有 DAO 介面的具體實作類別 。

model (資料模型層)：


Employ.java：員工類別 。


Member.java：會員類別 。


Porder.java：訂單類別 。


Product.java：產品類別 。

service (業務邏輯層)：


EmployService.java：員工業務邏輯 。


MemberService.java：會員業務邏輯 。


PorderService.java：訂單業務邏輯 。


ProductService.java：產品業務邏輯 。


impl/：包含所有 Service 介面的具體實作類別 。

util (工具類)：


DbConnection.java：MySQL 連線工具類 。


ReportGenerator.java：圖表工具類 。


Tool.java：其他工具函式 。


