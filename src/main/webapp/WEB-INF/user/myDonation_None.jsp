<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyDonation_None</title>
    <style>
        .container {
            width: 70%;
            margin: 0 auto;
            text-align: center;
        }
        button {
            border: none;
            background-color: gray;
            color: white;
            padding: 10px;
            border-radius: 3px;
            transition: all 0.3s ease;
        }
        button:hover {
            background-color: lightgray;
            color: black;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2>아직 기부를 하지 않았습니다.</h2>

        <img src="../img/no.jpg" alt="">

        <br>

        <button>Donation</button>
    </div>
</body>

</html>