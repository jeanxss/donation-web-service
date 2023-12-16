<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>receiptForm</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
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

        input[type="file"] {
            font-size: medium;
            width: 100%;
        }

        textarea {
            margin: 5px;
            border: 1px solid lightgray;
            width: 100%;
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
	    function loginCheck() {
			if (${sessionScope.userId eq null}) {
				confirm('로그인 후 이용가능한 서비스입니다.');
				window.location.href = "<c:url value='/user/login' />";
				return;
			}
		}
	    
	    function createReceipt() {
	    	loginCheck();
			
	    	if (form.image.value == "") {
    			alert("인증 내역 사진을 업로드하세요.");
    			form.image.focus();
                return false;
             }
	    	
	    	if (form.receipt_text.value == "") {
    			alert("인증 내역 설명을 입력하세요.");
    			form.receipt_text.focus();
                return false;
             }
	    	
			form.submit();
			
			//window.close();
			
		}

    </script>
</head>

<body>

    <div class="container">
        <h2>CREATE Receipt</h2>

        <hr>
        
        <form name="form" method="POST" enctype="multipart/form-data" action="<c:url value='/donationList/receipt' >
					<c:param name='articleId' value='${articleId}'/>
					<c:param name='category' value='${category}'/>
				</c:url>">
        	<div>
                <label for="image">인증 내역 사진<span>*</span></label>
                <br>
                <input type="file" id="image" name="image" multiple="multiple">
            </div>
            
            <div>
                <label for="receipt_text">인증 내역 설명<span>*</span></label>
                <br>
                <textarea name="receipt_text" id="receipt_text" rows="7" placeholder="실제 사용 내역을 자세히 적어주세요."></textarea>
            </div>
            
            <div class="btn">
                <input type="button" value="업로드" onclick="createReceipt()">
                <input type="button" value="업로드 취소" onclick="window.close()">
            </div>
        
        </form>
    </div>
</body>

</html>