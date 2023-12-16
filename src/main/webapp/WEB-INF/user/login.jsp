<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        .container {
            width: 70%;
            margin: 0 auto;
        }
        h2 {
            color: indianred;
        }
        table {
            margin: 0 auto;
            margin-top: 50px;
            width: 60%;
            text-align: center;
        }
        input[type="button"] {
            border: none;
            background-color: gray;
            color: white;
            border-radius: 3px;
            padding: 7px;
            transition: all 0.3s ease;
        }
        input[type="button"]:hover {
            cursor: pointer;
            background-color: lightgray;
            color: black;
        }
    </style>
    <script>
      function login() {
         if (form.userId.value == "") {
            alert("사용자 ID를 입력하십시오.");
            form.userId.focus();
            return false;
         } 
         if (form.password.value == "") {
            alert("비밀번호를 입력하십시오.");
            form.password.focus();
            return false;
         }      
         form.submit();
      }
      
      function userCreate(targetUri) {
         form.action = targetUri;
         form.method="GET";      // register form 요청
         form.submit();
      }
   </script>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>
    
    <div class="container">
        <h2>Login</h2>

        <hr>
	
        <form name="form"  method="POST" action="<c:url value='/user/login' />">
            <table>
                <tr>
                    <th rowspan="3" class="title">
                        SUPPORT
                        <br>
                        &amp;
                        <br>
                        DONATION
                    </th>
                    <th>
                        <label for="id">아이디</label>
                    </th>
                    <td>
                        <input type="text" style="width: 100%;" name="userId">
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="pw">비밀번호</label>
                    </th>
                    <td>
                        <input type="password" style="width: 100%;" name="password">
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="button" value="Login" onClick="login()">
                        <input type="button" value="Join" onClick="userCreate(
                        '<c:url value='/user/register'/>')">
                    </td>
                </tr>
                
                <tr>
                   <td colspan="2">
                       <c:if test="${loginFailed}">
                       <script>
                           alert("<c:out value='${exception.getMessage()}' />");
                       </script>
                    </c:if>
                   </td>
                </tr>
            </table>
        </form>
    </div>
</body>

</html>