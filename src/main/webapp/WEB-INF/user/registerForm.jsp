<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RegisterForm</title>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript" src="./../js/register.js"></script>
    <style>
       .container {
            width: 70%;
            margin: 0 auto;
        }
        h2 {
            color: indianred;
        }
        form {
            width: 100%;
            margin: 0 auto;
        }
        form>div {
            margin: 10px;
        }
        span {
            color: orange;
        }
        input[type="text"],
        input[type="password"],
        input[type="date"],
        input[type="email"] {
            width: 50%;
            border: 1px solid lightgray;
            height: 30px;
            margin: 10px;
        }
        .phone input {
            width: 10%;
        }
        .btn {
            margin: 0 auto;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }
        .btn>input {
            border: none;
            background-color: gray;
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
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>
    
    <div class="container">
        <h2>Join membership</h2>

        <hr>

        <form name="form" method="POST" action="<c:url value='/user/register' />">
            <div>
                <label for="name">이름<span>*</span></label>
                <br>
                <input type="text" class="txtarea" name="userName">
            </div>

            <div>
                <label for="joinId">아이디<span>*</span></label>
                <br>
                <input type="text" class="txtarea" name="userId">
            </div>

            <div>
                <label for="passwd">비밀번호<span>*</span></label>
                <br>
                <input type="password" class="txtarea" name="password">
            </div>

            <div class="phone">
                <label>연락처<span>*</span></label>
                <br>
                <input type="text" style="width:240" name="phone" >  <!--   <input type="text"><span>-</span><input type="text"><span>-</span><input type="text">-->
            </div>

            <div>
                <label for="birth">생년월일<span>*</span></label>
                <br>
                <input type="date" name="birthday">
            </div>

            <div>
                <label for="mail">이메일<span>*</span></label>
                <br>
                <input type="email" name="email">
            </div>

            <div>
                <label id="location">주소<span>*</span></label>
                <br>
                <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
                <input type="text" id="postcode" placeholder="우편번호" name="postcode"><br>
                <input type="text" id="address" placeholder="주소" name="address"><br>
                <input type="text" id="detailAddress" placeholder="상세주소" name="detailAddress"><br>
                <input type="text" id="extraAddress" placeholder="참고항목" name="extraAddress">
            </div>

            <div class="btn">
                <input type="button" value="Join" onClick="userCreate()">
            </div>
        </form>
    </div>
</body>

</html>