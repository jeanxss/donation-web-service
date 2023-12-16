<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Article</title>
    <style>
        .container {
            width: 70%;
            margin: 0 auto;
        }
        table {
            width: 90%;
            margin: 0 auto;
            border: 1px solid goldenrod;
            text-align: center;
            border-collapse: collapse;
        }
        th {
            border: 1px solid goldenrod;
            background-color: #fab1a0;
        }
        td {
            border: 1px solid #e17055;
        }
        td:nth-child(1) {
            width: 20%;
        }
        td>img {
            width: 70%;
            height: 140px;
        }
        td:nth-child(2) {
            width: 30%;
        }
        td:nth-child(3),
        td:nth-child(4),
        td:nth-child(5),
        td:nth-child(6) {
            width: 10%;
        }
        
        .update {
            background-color: gray;
            color: white;
            border-radius: 3px;
            border: none;
            transition: all 0.3s ease;
            padding: 10px;
        }
		.update > a {
			text-decoration: none;
			color: white;
		}
		
        .update:hover {
            background-color: lightgray;
            color: black;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2>내가 생성한 기부 목록</h2>

        <hr>

        <table>
            <tr>
                <th>사진</th>
                <th>제목</th>
                <th>작성 날짜</th>
                <th>현재 모금액(단위: 원)</th>
                <th>영수증 인증 여부</th>
                <th>수정</th>
            </tr>

			<c:forEach var="article" items="${myArticles}">
				<tr>
	               <c:if test="${'animal' eq article.category }">
	              	 <td><img src="../img/animal.png" alt=""></td>
	               </c:if>   
	               <c:if test="${'disaster' eq article.category }">
	                  <td><img src="../img/disaster.jpg" alt=""></td>
	               </c:if>   
	               <c:if test="${'socialGroup' eq article.category }">
	                  <td><img src="../img/volunteer.jpg" alt=""></td>
	               </c:if> 
	                <td onclick="location.href='<c:url value='/donationList/${article.category}' >
			  							<c:param name='articleId' value='${article.articleId}'/>
			  						</c:url>'">
	                		${article.title }
	                </td>
	                <td>${article.createDate }</td>
	                <td>${article.totalAmount }</td>
	                <td>${article.receiptCheck }</td>
	                <td> 
	                	<span class="update">
			  				<a href="<c:url value='/donationForm/${article.category }ArticleUpdate' >
			  					<c:param name='userId' value='${article.userId}'/>
			  					<c:param name='articleId' value='${article.articleId}'/>
			  				</c:url>">UPDATE</a> 
			  			</span>
			  		</td>
	            </tr>
			</c:forEach>
        </table>
    </div>
</body>

</html>