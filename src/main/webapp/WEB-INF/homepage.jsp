<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HOME</title>
    <style>
        .container {
            width: 70%;
            margin: 0 auto;
            text-align: center;
        }

        .desc {
            padding: 40px;
            font-size: large;
        }

        .cate {
            padding: 30px;
            font-weight: 500;
            font-size: 50px;
            border-top: 5px solid black;
            border-bottom: 5px solid black;
        }

        .category {
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: space-around;
        }

        .category>div {
            font-size: large;
            margin: 10px;
            width: 30%;
        }

        .category>div>img {
            height: 300px;
            width: 100%;
        }   
    </style>
</head>

<body>    
    <jsp:include page="navigation.jsp"/>

    <div class="container">
        <div class="desc">you can support whatever you want</div>
        <div class="cate">support &amp; donation category</div>

        <div class="category">
            <div>
                <img src="./img/animal.png" alt="">
                <div>동물</div>
            </div>

            <div>
                <img src="./img/volunteer.jpg" alt="">
                <div>사회취약 계층</div>
            </div>

            <div>
                <img src="./img/disaster.jpg" alt="">
                <div>재난 재해</div>
            </div>
        </div>
    </div>
</body>

</html>