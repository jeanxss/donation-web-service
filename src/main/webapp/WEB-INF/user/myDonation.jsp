<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Donation</title>
    <style>
        .container {
            width: 70%;
            margin: 0 auto;
        }
        .container h2 {
            text-align: left;
        }
        table {
            width: 90%;
            margin: 0 auto;
            border: 1px solid goldenrod;
            text-align: center;
            border-collapse: collapse;
        }
        th {
            background-color: #fab1a0;
            border: 1px solid goldenrod;
        }
        td {
            border: 1px solid #e17055;
        }
        td:nth-child(1) {
            width: 25%;
        }
        td>img {
            width: 50%;
            height: 100px;
        }
        td:nth-child(2) {
            width: 40%;
        }
        td:nth-child(3),
        td:nth-child(4) {
            width: 10%;
        }
        .btn {
            background-color: gray;
            color: white;
            border-radius: 3px;
            border: none;
            transition: all 0.3s ease;
            padding: 10px;
        }
		.btn > a {
			text-decoration: none;
			color: white;
		}
		
        .btn:hover {
            background-color: lightgray;
            color: black;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2>내가 기부한 목록</h2>
        <hr>
        <table>
            <tr>
                <th>사진</th>
                <th>제목</th>
                <th>작성자</th>
                <th>기부 금액(단위 : 원)</th>
                <th>바로가기</th>
            </tr>

           <c:forEach var="donation" items="${donations}">
				<tr>
	               <c:if test="${'animal' eq donation.category }">
	              	 <td><img src="../img/animal.png" alt=""></td>
	               </c:if>   
	               <c:if test="${'disaster' eq donation.category }">
	                  <td><img src="../img/disaster.jpg" alt=""></td>
	               </c:if>   
	               <c:if test="${'socialGroup' eq donation.category }">
	                  <td><img src="../img/volunteer.jpg" alt=""></td>
	               </c:if>   
	                <td>${donation.title }</td>
	                <td>${donation.writer }</td>
	                <td>${donation.amount }</td>
	                <td> 
	                	<span class="btn">
			  				<a href="<c:url value='/donationList/${donation.category }' >
			  					<c:param name='articleId' value='${donation.articleId}'/>
			  				</c:url>">Go to Site</a> 
			  			</span>
			  		</td>
	            </tr>
			</c:forEach>
        </table>
    </div>
</body>

</html>