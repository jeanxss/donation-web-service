<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RegisterForm</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
        }

        nav {
            background-color: antiquewhite;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        nav>div {
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 70%;
        }

        .title {
            font-weight: bold;
            font-size: xx-large;
            color: brown;
            margin: 30px 0px;
        }

        #main-menu {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        #main-menu,
        #sub-menu {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }

        #main-menu>li {
            padding: 15px;
        }

        #main-menu>li>a {
            color: black;
            text-align: center;
            text-decoration: none;
            font-weight: 600;
        }

        #main-menu>li>a:hover {
            cursor: pointer;
            color: gray;
        }

        #sub-menu {
            height: 0;
            visibility: hidden;
            transition: all 0.15s ease;
            position: relative;
            z-index: 10;
        }

        #sub-menu>li {
            width: 115px;
            padding: 10px 0px;
            margin: 0 auto;
            background: brown;
            border-bottom: 1px solid rgba(255, 255, 255, 0.6);
        }

        #sub-menu>li>a {
            color: rgba(255, 255, 255, 0.6);
            text-decoration: none;
        }

        #main-menu>li:hover #sub-menu {
            visibility: visible;
        }

        #sub-menu>li>a:hover {
            cursor: pointer;
            color: lightgray;
        }
    </style>
</head>

<body>
   <nav class="top">
        <div>
            <div class="title">SUPPORT &amp; DONATION</div>

            <ul id="main-menu">
                <li><a href="<c:url value='/homepage' />">HOME</a></li>
                <li><a href="<c:url value='/donationList/donationFeed' >
                				<c:param name='category_feed' value='none'/> 
                			</c:url>">VIEW DONATIONS</a></li>
                
               <c:choose>
	               <c:when test="${sessionScope.userId ne null}">
	                  <li><a href="#">CREATE DONATION</a>
	                          <ul id="sub-menu">
	                              <li><a href="<c:url value='/donationForm/idCard' > 
	                              			<c:param name='category' value='animal'/>
	                              	</c:url>">동물</a></li>
	                              <li><a href="<c:url value='/donationForm/idCard' > 
	                              			<c:param name='category' value='disaster'/>
	                              	</c:url>">재난재해</a></li>
	                               <li><a href="<c:url value='/donationForm/idCard' > 
	                              			<c:param name='category' value='socialGroup'/>
	                              	</c:url>">사회취약계층</a></li>
	                          </ul>
	                      </li>
	                      <li><a href="#">MY PAGE</a>
	                         <ul id="sub-menu">
	                              <li><a href="<c:url value='/user/myInfo' />">개인정보</a></li>
	                              <li><a href="<c:url value='/user/myArticle' />">작성글 내역</a></li>
	                              <li><a href="<c:url value='/user/myDonaion' />">후원 내역</a></li>
	                              <li><a href="<c:url value='/user/logout' />">로그아웃</a></li>
	                          </ul>
	                       </li>
	               </c:when>
	               <c:otherwise>
	                  <li><a href="#">CREATE DONATION</a>
	                          <ul id="sub-menu">
	                              <li><a href="<c:url value='/user/login' />" onclick="confirm('로그인 후 이용가능한 서비스입니다.')">동물</a></li>
	                              <li><a href="<c:url value='/user/login' />" onclick="confirm('로그인 후 이용가능한 서비스입니다.')">재난재해</a></li>
	                               <li><a href="<c:url value='/user/login' />" onclick="confirm('로그인 후 이용가능한 서비스입니다.')">사회취약계층</a></li>
	                          </ul>
	                      </li>
	                      <li id="myPage"><a href="#">MY PAGE</a>
	                          <ul id="sub-menu">
	                              <li><a href="<c:url value='/user/login' />">로그인</a></li>
	                              <li><a href="<c:url value='/user/register' />">회원가입</a></li>
	                          </ul>
	                      </li>
              		</c:otherwise>
            </c:choose>
            </ul>
        </div>
    </nav>
</body>
</html>