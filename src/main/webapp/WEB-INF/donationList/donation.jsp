<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 제이쿼리 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<!-- 아임포트 -->
	<script src ="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js" type="text/javascript"></script>
    <title>Donation</title>
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
        h2 {
            color: palevioletred;
        }
        form {
            width: 100%;
            margin: 0 auto;
        }
        form>div {
            margin: 15px;
        }
        span {
            color: orange;
        }
        input {
            border: 1px solid lightgray;
            padding: 5px;
            margin: 5px;
        }
        input[type="text"] {
            width: 50%;
        }
        input[type="file"] {
            font-size: medium;
            width: 50%;
        }
        input[type="date"] {
            width: 50%;
            font-size: large;
        }
        textarea {
            margin: 5px;
            border: 1px solid lightgray;
            width: 50%;
        }
		.note{
			font-weight: bold;
			color: red;
		}
        .btn {
            margin: 0 auto;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }
        .btn>input {
            background-color: gray;
            border: none;
            color: white;
            padding: 10px;
            border-radius: 3px;
            transition: all 0.3s ease;
        }
        .btn>input:hover {
            background-color: lightgray;
            color: black;
            cursor: pointer;
        }
    </style>
    <script>
    function payment() {
    	var amount = document.getElementById('amount').value;
    	amount *= 1;
    	
    	if(amount<1000){
    		alert("1000\ 이상부터 기부 가능합니다.");
    		return false;
    	}
    	
    	const IMP = window.IMP;
    	IMP.init("imp03655522");
        // IMP.request_pay(param, callback) 결제창 호출
        IMP.request_pay({ // param
          pg: "kakaopay",
          pay_method: "kakaopay",
          merchant_uid: 'donation_' + new Date().getTime(), // 주문번호
          name: '${article.title}',
          amount: amount,
          buyer_email: '${user.email}',
          buyer_name: '${user.name}',
          buyer_tel: '${user.phoneNum}',
          buyer_addr: '${user.address}',
        }, function (rsp) { // callback
            if (rsp.success) {
                form.submit();
                alert("기부가 완료 되었습니다.");
            } else {
            	alert("기부가 실패 되었습니다.");
            }
        });
      }
    </script>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2>기부글: ${article.title }</h2>
        <hr>
        <form name="form" method="POST" action="<c:url value='/donation' />">
        	<input type="hidden" name="userId" value="${user.userId }">
        	<input type="hidden" name="articleId" value="${article.articleId }">
        	<input type="hidden" name="category" value="${article.category }">
            <div>
                <label for="amount">후원금액<span>*</span> (1000원 이상부터 후원이 가능합니다.)</label>
                <br>
                <input type="number" id="amount" name="amount" min="1000"> \
            </div>
            
            <div>
                <label for="payBankName">은행</label>
                <br>
                <input type="text" id="payBankName" name="payBankName" value="${article.bankName }" readonly="readonly">
            </div>
            
            <div>
                <label for="accHolder">예금주</label>
                <br>
                <input type="text" id="accHolder" name="accHolder" value="${article.accHolder }" readonly="readonly">
            </div>
            
            <div>
                <label for="accNum">계좌번호</label>
                <br>
                <input type="text" id="accNum" name="accNum" value="${article.accNum }" readonly="readonly">
            </div>
            
            <div class="btn">
                <input type="button" value="Donate now" onClick="payment()">
            </div>
        </form>
    </div>
</body>

</html>