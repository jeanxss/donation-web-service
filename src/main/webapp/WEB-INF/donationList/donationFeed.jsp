<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed</title>
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
            text-align: center;
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

        .container {
            width: 70%;
            margin: 0 auto;
        }

        .container .board {
            padding: 10px;
            display: flex;
            justify-content: space-between;
        }

        .post {
            font-size: x-large;
            font-weight: bold;
            color: indianred;
        }

        select {
            width: 25%;
        }

        table {
            width: 100%;
            text-align: center;
            font-size: large;
        }

        td {
            width: 25%;
            padding: 10px;
        }

        td:hover {
            cursor: pointer;
            background-color: lightgray;
        }
        
        td a {
           color: black;
           text-decoration: none;
           display: block;
           width: 100%;
           height: 100%;
　      } 

        td img {
            width: 100%;
            height: 200px;
        }   

        .bottom {
            display: flex;
            flex-direction: row;
            justify-content: center;
        }

        .bottom>input {
            margin: 10px;
        }
    </style>
    <script>
    
    window.onload = function() {
    	const select = document.getElementById('category_feed');
    	const len = select.options.length;
 	  	
    	for (let i=0; i<len; i++){  
 		  	//select box의 option value가 입력 받은 value의 값과 일치할 경우 selected
 		    if(select.options[i].value == '${category_feed}'){
 		    	select.options[i].selected = true;
 		    }
 		  }  
 	  
    };
    
   function feedView() {
	   var select = document.getElementById('category_feed');
	   var option = select.options[select.selectedIndex];
	   
	   if (option.value == "none") {
		   location.href = "<c:url value='/donationList/donationFeed' > <c:param name='category_feed' value='none'/> </c:url>";  
		} 
	   else if (option.value == "animal") {
		   location.href = "<c:url value='/donationList/donationFeed' > <c:param name='category_feed' value='animal'/> </c:url>";
	   }
	   else if (option.value == "disaster") {
		   location.href = "<c:url value='/donationList/donationFeed' > <c:param name='category_feed' value='disaster'/> </c:url>";
	   }
	   else {
		   location.href = "<c:url value='/donationList/donationFeed' > <c:param name='category_feed' value='socialGroup'/> </c:url>";
	   }
	   
	   
   }
   </script>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <div class="board">
            <div class="post">VIEWS</div>

            <select name="category_feed" id="category_feed" onchange="feedView()">
                <option value="none">전체</option>
                <option value="animal">동물</option>
                <option value="disaster">재난 재해</option>
                <option value="socialGroup">사회 취약 계층</option>
            </select>
        </div>

        <hr>

        <table>
			    <tr>
				    <c:forEach var="article" items="${articleList}" varStatus="status">
				    	<c:if test="${status.index % 4 == 0}">
				    		</tr><tr>
				    	</c:if>
				    	<c:choose>
		               		<c:when test="${sessionScope.userId ne null}">
					    		<td onclick="location.href='<c:url value='/donationList/${article.category}' >
					  							<c:param name='articleId' value='${article.articleId}'/>
					  						</c:url>'">
		                			<div>${article.title}</div>
		                			<div><img src="<c:url value='/upload/${article.fileName}'/>" alt=""></div>
		                		</td>
		                	</c:when>
		               		<c:otherwise>
		               			<td onclick="confirm('로그인 후 이용가능한 서비스입니다.')">
		                			<div>${article.title}</div>
		                			<div><img src="<c:url value='/upload/${article.fileName}'/>" alt=""></div>
		                		</td>
		                	</c:otherwise>
		                </c:choose>
				    </c:forEach>
				</tr>
        </table>
    </div>
</body>

</html>