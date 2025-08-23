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


📂 YourProjectName/
[cite_start]├── 📂 controller/ (UI 及業務邏輯) [cite: 17, 18]
[cite_start]│   ├── Login.java [cite: 19]
│   ├── 📂 employ/
[cite_start]│   │   ├── AdminManager.java [cite: 21]
[cite_start]│   │   └── EmployManager.java [cite: 22]
│   ├── 📂 member/
[cite_start]│   │   ├── LoginRegister.java [cite: 24]
[cite_start]│   │   └── MemberManager.java [cite: 25]
│   ├── 📂 porder/
[cite_start]│   │   ├── AddPorderjava [cite: 30]
[cite_start]│   │   ├── Confirm.java [cite: 31]
[cite_start]│   │   └── FindPorderManager.java [cite: 32]
│   ├── 📂 product/
[cite_start]│   │   ├── AdminPorderManager.java [cite: 34]
[cite_start]│   │   └── ProductManager.java [cite: 35]
│   └── 📂 report/
[cite_start]│       └── ProductChart.java [cite: 37]
[cite_start]├── 📂 dao/ (資料存取層) [cite: 39]
[cite_start]│   ├── EmployDao.java [cite: 41]
[cite_start]│   ├── MemberDao.java [cite: 42]
[cite_start]│   ├── PorderDao.java [cite: 43]
[cite_start]│   ├── ProductDao.java [cite: 44]
[cite_start]│   └── 📂 impl/ (DAO 實作) [cite: 45]
[cite_start]│       ├── EmployDaoImpl.java [cite: 46]
[cite_start]│       ├── MemberDaoImpl.java [cite: 47]
[cite_start]│       ├── PorderDaoImpl.java [cite: 48]
[cite_start]│       └── ProductDaoImpl.java [cite: 49]
[cite_start]├── 📂 model/ (資料模型) [cite: 51]
[cite_start]│   ├── Employ.java [cite: 53]
[cite_start]│   ├── Member.java [cite: 54]
[cite_start]│   ├── Porder.java [cite: 55]
[cite_start]│   └── Product.java [cite: 56]
[cite_start]├── 📂 service/ (業務邏輯層) [cite: 58]
[cite_start]│   ├── EmployService.java [cite: 60]
[cite_start]│   ├── MemberService.java [cite: 61]
[cite_start]│   ├── PorderService.java [cite: 62]
[cite_start]│   ├── ProductService.java [cite: 63]
[cite_start]│   └── 📂 impl/ (Service 實作) [cite: 64]
[cite_start]│       ├── EmployoServiceImpl.java [cite: 65]
[cite_start]│       ├── MemberServiceImpl.java [cite: 66]
[cite_start]│       ├── PorderServiceImpl.java [cite: 67]
[cite_start]│       └── ProductServiceImpl.java [cite: 68]
[cite_start]└── 📂 util/ (工具類) [cite: 70]
    [cite_start]├── DbConnection.java [cite: 71]
    [cite_start]├── ReportGenerator.java [cite: 72]
    [cite_start]└── Tool.java [cite: 73]
    
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
