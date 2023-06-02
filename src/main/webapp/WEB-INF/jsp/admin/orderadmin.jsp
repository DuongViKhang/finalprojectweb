<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>Danh sách đơn hàng</title>
    <!-- Bootstrap CSS -->
    <link
      rel="stylesheet"
      href="./assets/vendor/bootstrap/css/bootstrap.min.css"
    />
    <link
      href="./assets/vendor/fonts/circular-std/style.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="./assets/libs/css/style.css" />
    <link
      rel="stylesheet"
      href="./assets/vendor/fonts/fontawesome/css/fontawesome-all.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="./assets/vendor/datatables/css/dataTables.bootstrap4.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="./assets/vendor/datatables/css/buttons.bootstrap4.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="./assets/vendor/datatables/css/select.bootstrap4.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="./assets/vendor/datatables/css/fixedHeader.bootstrap4.css"
    />
  </head>
  <body>
    <div class="dashboard-main-wrapper">
      <div class="dashboard-header">
        <nav class="navbar navbar-expand-lg bg-white fixed-top">
          <a class="navbar-brand" href="index.html">Shop</a>
          <button
            class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto navbar-right-top">
              <li class="nav-item">
                <div id="custom-search" class="top-search-bar">
                  <input
                    class="form-control"
                    type="text"
                    placeholder="Search.."
                  />
                </div>
              </li>
              <li class="nav-item dropdown nav-user">
                <a
                  class="nav-link nav-user-img"
                  href="#"
                  id="navbarDropdownMenuLink2"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                  ><img
                    src="assets/images/avatar-1.jpg"
                    alt=""
                    class="user-avatar-md rounded-circle"
                /></a>
                <div
                  class="dropdown-menu dropdown-menu-right nav-user-dropdown"
                  aria-labelledby="navbarDropdownMenuLink2"
                >
                  <div class="nav-user-info">
                    <h5 class="mb-0 text-white nav-user-name">Thanh Thoại</h5>
                    <span class="status"></span
                    ><span class="ml-2">Available</span>
                  </div>
                  <a class="dropdown-item" href="./account-profile.html"
                    ><i class="fas fa-user mr-2"></i>Account</a
                  >
                  <a class="dropdown-item" href="#"
                    ><i class="fas fa-power-off mr-2"></i>Logout</a
                  >
                </div>
              </li>
            </ul>
          </div>
        </nav>
      </div>
      <div class="nav-left-sidebar sidebar-dark">
        <div class="menu-list">
          <nav class="navbar navbar-expand-lg navbar-light">
            <a class="d-xl-none d-lg-none" href="#">Dashboard</a>
            <button
              class="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav flex-column">
                <li class="nav-divider">Menu</li>
                <li class="nav-item">
                  <a
                    class="nav-link active"
                    href="#"
                    data-toggle="collapse"
                    aria-expanded="false"
                    data-target="#submenu-1"
                    aria-controls="submenu-1"
                    ><i class="fa fa-fw fa-user-circle"></i>Dashboard</a
                  >
                  <div id="submenu-1" class="collapse submenu" style="">
                    <ul class="nav flex-column">
                      <ul class="nav flex-column">
                        <li class="nav-item">
                          <a class="nav-link" href="index.html"
                            >Thống kê doanh thu</a
                          >
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="product.html"
                            >Quản lý sản phẩm</a
                          >
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="category.html"
                            >Quản lý danh mục</a
                          >
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="store.html"
                            >Quản lý cửa hàng</a
                          >
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="account.html"
                            >Quản lý tài khoản</a
                          >
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="order.html"
                            >Quản lý đơn hàng</a
                          >
                        </li>
                      </ul>
                    </ul>
                  </div>
                </li>
              </ul>
            </div>
          </nav>
        </div>
      </div>
      <div class="dashboard-wrapper">
        <div class="container-fluid dashboard-content">
          <!-- ============================================================== -->
          <!-- pageheader -->
          <!-- ============================================================== -->
          <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
              <div class="page-header">
                <h2 class="pageheader-title">Admin</h2>
                <div class="page-breadcrumb">
                  <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item">
                        <a href="./index.html" class="breadcrumb-link"
                          >Dashboard</a
                        >
                      </li>
                      <li class="breadcrumb-item active" aria-current="page">
                        Quản lý đơn hàng
                      </li>
                    </ol>
                  </nav>
                </div>
              </div>
            </div>
          </div>
          <!-- ============================================================== -->
          <!-- end pageheader -->
          <!-- ============================================================== -->
          <div class="row">
            <!-- ============================================================== -->
            <!-- basic table  -->
            <!-- ============================================================== -->
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
              <div class="card">
                <h5 class="card-header">Danh sách đơn hàng</h5>
                <!-- <a
                  href="${pageContext.request.contextPath}/admin/add-product"
                  type="submit"
                  class="btn btn-rounded btn-primary"
                  >Thêm sản phẩm</a
                > -->
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-striped table-bordered first">
                      <thead>
                        <tr>
                          <th>ID đơn hàng</th>
                          <th>ID khách hàng</th>
                          <th>Ngày đặt hàng</th>
                          <th>Tổng tiền</th>
                          <th>Trạng thái</th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                       <c:forEach items="${orderlist}" var="p">
                        <tr>
                          <td>${p.getId()}</td>
                          <td>${p.getCustomer().getId()}</td>
                          <td>${p.getgetOrder_date()}</td>
                          <td>${p.getTotal_price() }</td>
                          <td>
                            <span
                              class="badge bg-label-success me-1"
                              style="color: orange !important"
                              >${p.getStatus() }</span
                            >
                          </td>
                          <td>
                            <!-- <a href="./updateOrder?orderId=${order.getId()}" id="confirm-${order.getId()}">Xác nhận</a> -->
                            <a href="">Xác nhận</a>
                          </td>
                        </tr>
                       </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <!-- ============================================================== -->
            <!-- end basic table  -->
            <!-- ============================================================== -->
          </div>
        </div>
      </div>
    </div>
    <script src="./assets/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="./assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script src="./assets/vendor/slimscroll/jquery.slimscroll.js"></script>
    <script src="./assets/vendor/multi-select/js/jquery.multi-select.js"></script>
    <script src="./assets/libs/js/main-js.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="./assets/vendor/datatables/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
    <script src="./assets/vendor/datatables/js/buttons.bootstrap4.min.js"></script>
    <script src="./assets/vendor/datatables/js/data-table.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.colVis.min.js"></script>
    <script src="https://cdn.datatables.net/rowgroup/1.0.4/js/dataTables.rowGroup.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script>
    <script src="https://cdn.datatables.net/fixedheader/3.1.5/js/dataTables.fixedHeader.min.js"></script>
  </body>
</html>
