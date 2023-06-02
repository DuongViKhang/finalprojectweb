<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Đăng nhập/Đăng ký</title>
    <!-- Google Font -->
    <link
            href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
            rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/bootstrap.min.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/elegant-icons.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/magnific-popup.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/nice-select.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/owl.carousel.min.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/slicknav.min.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/authentication.css">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
<!-- Header Section Begin-->
<jsp:include page="header.jsp" />
<!-- Header Section End -->
<c:if test = "${messagesuccess != null}">
<div class="alert alert-success text-center">
	${messagesuccess}
</div>
</c:if>
<c:if test = "${messagefail != null}">
<div class="alert alert-danger text-center">
	${messagefail}
</div>
</c:if>
<div class="wrapper">
    <div class="cont">
        <form class="form sign-up" method="POST" action="/registersave">
            <h2 id="tilte-h2">ĐĂNG KÝ TÀI KHOẢN</h2>
            <div class="input-control">
                <input name="fullName" id="nameRe" type="text"
                       placeholder="Họ và tên" required />
            </div>
            <div class="input-control">
                <input name="usernameNew" id="usernameR" type="text"
                       placeholder="Tên đăng nhập" required/>
            </div>
            <div class="input-control">
                <input name="email" id="email" type="text"
                       placeholder="Email" required/>
            </div>
            <div class="input-control">
                <input name="phone" id="phone" type="text"
                       placeholder="Số điện thoại" required/>
            </div>
            <div class="input-control">
                <input name="passwordNew" id="passwd" type="password"
                       placeholder="Mật khẩu" required/>
            </div>
            
            <div class="input-control radio">
						<span> <input type="radio" name="gender" id="gender-male"
                                      value="Name"> <label for="gender-male">Nam</label>
						</span> <span> <input type="radio" name="gender"
                                              id="gender-female" value="Nữ"> <label for="gender-female">Nữ</label>
						</span> <span> <input type="radio" name="gender"
                                              id="gender-others" value="Khác"> <label
                    for="gender-others">Khác</label>
						</span>
            </div>
            
             <div class="g-recaptcha" data-sitekey="6LcWXFMmAAAAAN7_Qo1-zGRVl6VGFzmb4u0bZhOE" required></div>
            
            <%-- <p class="message-text-fail">${messageRegisterFail}</p> --%>
            <p class="message-text-success">${messageRegisterSuccess}</p>
            <button type="submit" class="submit">Đăng ký</button>
        </form>
        <div class="sub-cont">
            <div class="img">
                <div class="img__text m--up">
                    <h2>One of us?</h2>
                    <p>Nếu bạn đã có tài khoản,vui lòng đăng nhập tại đây!</p>
                </div>
                <a href="./login">
                    <div class="img__btn">
                        <span class="m--up">Đăng nhập</span> <span class="m--in">Đăng
							nhập</span>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
<!-- Footer Section Begin -->
<jsp:include page="footer.jsp" />
<!-- Footer Section End -->
<%--<script>--%>
<%--    window.addEventListener('load', () => {--%>
<%--        document.querySelector('.img__btn').addEventListener('click', function() {--%>
<%--            document.querySelector('.cont').classList.toggle('s--signup');--%>
<%--        });--%>
<%--    })--%>
</script>
</body>
</html>
