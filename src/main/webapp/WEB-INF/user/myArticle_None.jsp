<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyArticle_None</title>
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
        <h2>¾ÆÁ÷ »ý¼ºÇÑ DonationÀÌ ¾ø½À´Ï´Ù</h2>

        <img src="../img/no.jpg" alt="">

        <br>

        <button>Create Donation</button>
    </div>
</body>

</html>