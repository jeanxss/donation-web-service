<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AnimalForm</title>
    <script type="text/javascript" src="./../js/animalForm.js"></script>
    <style>
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
        <h2>CREATE DONATION(Animal)</h2>

        <hr>

        <form name="form" method="POST" action="<c:url value='/donationForm/animal' />" enctype="multipart/form-data">
            <div>
                <label for="title">제목<span>*</span></label>
                <br>
                <input type="text" id="title" name="title">
            </div>

            <div>
                <label for="name">이름<span>*</span></label>
                <br>
                <input type="text" id="name" name="name">
            </div>

            <div>
                <label for="area">지역<span>*</span></label>
                <br>
                <input type="text" id="area" name="area">
            </div>

            <div>
                <div>현재 상태<span>*</span></div>
                <br>
                <input type="radio" name="current_status" value="반려동물" id="status1" checked="checked"><label for="status1">반려동물</label>
                <input type="radio" name="current_status" value="임시보호" id="status2"><label for="status2">임시보호</label>
                <input type="radio" name="current_status" value="구조동물" id="status3"><label for="status3">구조 동물</label>
                <input type="radio" name="current_status" value="기타" id="status4"><label for="status4">기타</label>
            </div>

            <div>
                <div>종<span>*</span></div>
                <br>
                <input type="radio" name="type" value="bird" id="bird" checked="checked"><label for="bird">조류</label>
                <input type="radio" name="type" value="dog" id="dog"><label for="dog">강아지</label>
                <input type="radio" name="type" value="cat" id="cat"><label for="cat">고양이</label>
                <input type="radio" name="type" value="sortEtc" id="sortEtc"><label for="sortEtc">기타</label>
            </div>

            <div>
                <div>성별<span>*</span></div>
                <br>
                <input type="radio" name="gender" value="암컷"  id="female" checked="checked"><label for="female">암컷</label>
                <input type="radio" name="gender" value="수컷" id="male"><label for="male">수컷</label>
                <input type="radio" name="gender" value="모름" id="fm"><label for="fm">모름</label>
            </div>

            <div>
                <div>중성화 유/무<span>*</span></div>
                <br>
                <input type="radio" name="neutering" value="O" id="presence" checked="checked"><label for="presence">유</label>
                <input type="radio" name="neutering" value="X" id="absence"><label for="absence">무</label>
                <input type="radio" name="neutering" value="모름" id="fm"><label for="fm">모름</label>
            </div>

            <div>
                <label for="age">나이<span>*</span></label>
                <br>
                <input type="text" id="age" placeholder="모를 경우 (모름)" name="age">
            </div>

            <div>
                <label for="weight">몸무게<span>*</span></label>
                <br>
                <input type="text" id="weight" placeholder="모를 경우 (모름)" name="weight">
            </div>

            <div>
                <label for="image">사진<span>*</span></label>
                <br>
                <input type="file" id="image" name="image" multiple="multiple">
            </div>

            <div>
                <label for="health_status">건강 상태<span>*</span></label>
                <br>
                <textarea name="health_status" id="health_status" rows="7"></textarea>
            </div>

            <div>
                <label for="personality">성격</label>
                <br>
                <textarea name="personality" id="personality" rows="7"></textarea>
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
                <label for="use_plan">후원금 사용 계획<span>*</span></label>
                <br>
                <textarea name="use_plan" id="use_plan" rows="7" placeholder="예) 병원 : 550,000"></textarea>
            </div>

            <div>
                <label for="other_text">기타</label>
                <br>
                <textarea name="other_text" id="other_text" rows="7"></textarea>
            </div>

            <div class="btn">
                <input type="button" value="Create" onClick="AnimalArticleCreate()">
            </div>
        </form>
    </div>
</body>

</html>