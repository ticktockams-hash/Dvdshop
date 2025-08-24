# 商城管理系統

本系統為以 **Java + Swing (JFrame)** 開發的桌面應用程式，結合 **MySQL** 資料庫並採用 **MVC 架構**，提供完整的商城管理與報表視覺化能力。

---

## 系統功能

### 商品管理

* 新增商品
* 修改商品
* 查詢商品
* 刪除商品

### 訂單管理

* 新增訂單
* 修改訂單
* 查詢訂單
* 刪除訂單

### 會員資料管理

* 新增會員資料
* 修改會員資料
* 刪除會員資料

### 員工資料管理

* 新增員工資料
* 修改員工資料
* 刪除員工資料

### 報表功能

* 以圖表形式查看銷售數據（線圖、圓餅圖）

---

## 架構設計

本系統採用 **MVC 架構**，各層職責分明，並組織在對應的資料夾中。

### Controller（控制層）

* 負責使用者介面（UI）與業務邏輯。
* 使用 `JFrame` 設計介面，並與 `Service` 層進行互動。

### Model（資料模型層）

* 定義與 MySQL 資料庫中的資料表相對應的類別（POJO）。

### DAO（資料存取層）

* 專門負責與 MySQL 資料庫進行 CRUD 操作。

### Service（業務邏輯層）

* 處理 DAO 操作及交易管理。

### Util（工具類）

* 提供 MySQL 資料庫連線及其他工具函式。

---

## 程式目錄結構

```text
.
├── controller
│   ├── employ
│   │   ├── AdminManager.java
│   │   └── EmployManager.java
│   ├── member
│   │   ├── LoginRegister.java
│   │   └── MemberManager.java
│   ├── porder
│   │   ├── AddPorderjava.java
│   │   ├── Confirm.java
│   │   └── FindPorderManager.java
│   ├── product
│   │   ├── AdminPorderManager.java
│   │   └── ProductManager.java
│   ├── report
│   │   └── ProductChart.java
│   └── Login.java
├── dao
│   ├── impl
│   │   ├── EmployDaoImpl.java
│   │   ├── MemberDaoImpl.java
│   │   ├── PorderDaoImpl.java
│   │   └── ProductDaoImpl.java
│   ├── EmployDao.java
│   ├── MemberDao.java
│   ├── PorderDao.java
│   └── ProductDao.java
├── model
│   ├── Employ.java
│   ├── Member.java
│   ├── Porder.java
│   └── Product.java
├── service
│   ├── impl
│   │   ├── EmployServiceImpl.java
│   │   ├── MemberServiceImpl.java
│   │   ├── PorderServiceImpl.java
│   │   └── ProductServiceImpl.java
│   ├── EmployService.java
│   ├── MemberService.java
│   ├── PorderService.java
│   └── ProductService.java
└── util
    ├── DbConnection.java
    ├── ReportGenerator.java
    └── Tool.java
```

---

## 使用者介面與操作說明

### 登入與註冊

#### 登入介面

* 使用者與管理員皆可登入；若無帳號可點擊「註冊」。

#### 註冊介面

* 填寫姓名、帳號、密碼、手機號碼即可完成註冊；若帳號已被使用，系統會提示。

### 會員功能

#### 主選單

* 登入後可選擇「進入商城」購物、在「會員管理」中修改個人資料，或在「訂單管理」中查詢歷史訂單。

#### 商城介面

* 選擇商品與數量後可加入購物車，點擊「結帳」會跳轉到確認頁面。
* 在購物車中，可選擇商品並點擊「更新數量」或「刪除商品」。
* 若為會員，總金額會顯示會員優惠。

#### 會員管理

* 會員可以修改自己的密碼與電話號碼。

#### 我的訂單

* 可查詢所有歷史訂單。

### 管理員功能

#### 管理主選單

* 管理員登入後，可進入商品管理、會員管理、員工管理、會員訂單管理及查看銷售圖表。

#### 產品管理

* 可新增、更新或刪除商品。

#### 會員／員工管理

* 可新增、更新或刪除會員或員工資料。

#### 會員訂單查詢

* 可查詢所有使用者的訂單，並可對訂單進行修改或刪除；亦可匯出訂單報表。

#### 銷售圖表

* 以線圖與圓餅圖的形式呈現商品銷售狀況。
