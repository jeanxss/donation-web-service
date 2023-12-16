<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SocialGroupForm</title>
     <script type="text/javascript" src="./../js/socialGroupForm.js"></script>
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
            border: 1px solid lightgray;
            width: 50%;
            margin: 5px;
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
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2>CREATE DONATION(Socially vulnerable)</h2>

        <hr>

        <form name="form" method="POST" action="<c:url value='/donationForm/socialGroup' />" enctype="multipart/form-data">
            <div>
                <label for="title">제목<span>*</span></label>
                <br>
                <input type="text" id="title" name="title">
            </div>

            <div>
                <div>성별<span>*</span></div>
                <br>
                <input type="radio" name="gender" id="female" value="F" checked="checked"><label for="female">F</label>
                <input type="radio" name="gender" id="male" value="M"><label for="male">M</label>
            </div>

            <div>
                <label for="age">나이<span>*</span></label>
                <br>
                <input type="number" id="age" name="age">(단위: 세, 만 나이)
            </div>

            <div>
                <label for="area">지역<span>*</span></label>
                <br>
                <input type="text" id="area" name="area">
            </div>


            <div>
                <label for="type">취약 계층 유형<span>*</span></label>
                <br>
                <input type="text" id="type" name="type">
            </div>

            <div>
                <label for="situation">현재 상황<span>*</span></label>
                <br>
                <textarea name="situation" id="situation" rows="7"></textarea>
            </div>

            <div>
                <label for="image">사진<span>*</span></label>
                <br>
                <input type="file" id="image" name="image" multiple="multiple">
            </div>

            <div>
                <label for="deadline">후원 마감일<span>*</span></label>
                <br>
                <input type="date" id="deadline" name="deadline">
            </div>

            <div>
                <label for="bank_name">후원 계좌 은행<span>*</span></label>
                <br>
                <input type="text" id="bank_name" name="bank_name">
            </div>

            <div>
                <label for="acc_holder">후원 계좌 예금주 이름<span>*</span></label>
                <br>
                <input type="text" id="acc_holder" name="acc_holder">
            </div>

            <div>
                <label for="acc_num">후원 계좌 번호<span>*</span></label>
                <br>
                <input type="text" id="acc_num" name="acc_num">
            </div>

            <div>
                <label for="due_date">후원금 사용 마감일<span>*</span></label>
                <br>
                <input type="date" id="due_date" name="due_date">
            </div>

            <div>
                <label>사용 계획<span>*</span></label>
                <br>
                <textarea name="use_plan" id="use_plan" rows="7" placeholder="예) 마트 : 100,000 등"></textarea>
            </div>

            <div>
                <label for="other_text">기타</label>
                <br>
                <textarea name="other_text" id="other_text" rows="7"></textarea>
            </div>

            <div class="btn">
                <input type="button" value="Create" onClick="SocialGroupArticleCreate()">
            </div>
        </form>
    </div>
</body>

</html>