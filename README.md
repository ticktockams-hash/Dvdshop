商城訂單與商品管理系統
專案簡介
這是一個基於 MVC 架構開發的商城系統，主要用於處理下單與管理商品、訂單、會員和員工資料。系統提供直觀的圖形使用者介面（GUI），讓使用者和管理員能輕鬆進行各項操作 。


專案主要功能
使用者功能


會員管理：註冊、登入，並能修改個人密碼和手機號碼 。






商城購物：瀏覽商品、將商品加入購物車，並可更新或刪除購物車內的商品 。



訂單查詢：查詢自己已購買的歷史訂單 。

管理員功能


商品管理：新增、修改、刪除和查詢商品 。




會員管理：新增、修改、刪除會員資料 。




員工管理：新增、修改、刪除員工資料 。




會員訂單管理：查詢會員訂單，並可修改或刪除訂單 。




報表匯出與銷售圖表：匯出會員訂單報表，並能觀看銷售圖表 。



系統架構
本專案遵循 

MVC (Model-View-Controller) 設計模式 ，將各個元件的職責分離，提升程式碼的可維護性與擴展性。



Controller（控制層）：負責處理使用者介面與業務邏輯，使用 JFrame 來顯示介面，並與 Service 層互動 。



Model（資料模型層）：定義與 MySQL 資料庫中的資料表相對應的類別 。



DAO（資料存取層）：負責直接對 MySQL 進行 CRUD（新增、查詢、更新、刪除）操作 。



Service（業務邏輯層）：處理業務邏輯和交易管理，呼叫 DAO 層來完成資料庫操作 。




Util（工具類）：提供如 MySQL 連線、圖表生成等輔助工具函式 。


目錄結構
📂 YourProjectName/
├── 📂 controller/ (UI and business logic) [cite: 17, 18]
│   ├── Login.java
│   ├── 📂 employ/
│   │   ├── AdminManager.java
│   │   └── EmployManager.java
│   ├── 📂 member/
│   │   ├── LoginRegister.java
│   │   └── MemberManager.java
│   ├── 📂 porder/
│   │   ├── AddPorderjava
│   │   ├── Confirm.java
│   │   └── FindPorderManager.java
│   ├── 📂 product/
│   │   ├── AdminPorderManager.java
│   │   └── ProductManager.java
│   └── 📂 report/
│       └── ProductChart.java
├── 📂 dao/ (Data Access Objects) [cite: 39]
│   ├── EmployDao.java
│   ├── MemberDao.java
│   ├── PorderDao.java
│   ├── ProductDao.java
│   └── 📂 impl/ (DAO implementations) [cite: 45]
│       ├── EmployDaoImpl.java
│       ├── MemberDaoImpl.java
│       ├── PorderDaoImpl.java
│       └── ProductDaoImpl.java
├── 📂 model/ (Data models) [cite: 51]
│   ├── Employ.java
│   ├── Member.java
│   ├── Porder.java
│   └── Product.java
├── 📂 service/ (Business logic) [cite: 58]
│   ├── EmployService.java
│   ├── MemberService.java
│   ├── PorderService.java
│   ├── ProductService.java
│   └── 📂 impl/ (Service implementations) [cite: 64]
│       ├── EmployoServiceImpl.java
│       ├── MemberServiceImpl.java
│       ├── PorderServiceImpl.java
│       └── ProductServiceImpl.java
└── 📂 util/ (Utility classes) [cite: 70]
    ├── DbConnection.java
    ├── ReportGenerator.java
    └── Tool.java
介面截圖與功能說明
登入與註冊

登入介面：使用者和管理員的登入入口 。


註冊介面：新使用者可在此建立帳號 。

會員功能

使用者主介面：進入商城、會員管理或訂單查詢 。


商城介面：瀏覽、加入商品至購物車 。


我的訂單：查詢個人已購商品 。

管理員功能

管理員主介面：管理商品、會員、員工、訂單及查看銷售圖表 。


產品管理：對商品進行 CRUD 操作 。


會員管理：對會員資料進行管理 。
