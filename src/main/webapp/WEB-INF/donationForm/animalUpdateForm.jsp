<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AnimalForm</title>
    <script type="text/javascript" src="./../js/animalUpdateForm.js"></script>
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
        <h2>Update DONATION(Animal)</h2>

        <hr>

        <form name="form" method="POST" action="<c:url value='/donationForm/animalArticleUpdate' />" enctype="multipart/form-data">
        	<input type="hidden" name="articleId" value="${article.articleId }">
        	<input type="hidden" name="userId" value="${article.userId }">
        	<input type="hidden" name="createDate" value="${article.createDate }">
        
            <div>
                <label for="title">제목<span>*</span></label>
                <br>
                <input type="text" id="title" name="title" value="${article.title }">
            </div>

            <div>
                <label for="name">이름<span>*</span></label>
                <br>
                <input type="text" id="name" name="name" value="${article.name }">
            </div>

            <div>
                <label for="area">지역<span>*</span></label>
                <br>
                <input type="text" id="area" name="area" value="${article.area }">
            </div>

            <div>
                <div>현재 상태<span>*</span></div>
                <br>
                <c:if test="${'반려동물' eq article.currentStatus }">
               		 <input type="radio" name="current_status" value="반려동물" id="status1" checked="checked"><label for="status1">반려동물</label>
               		 <input type="radio" name="current_status" value="임시보호" id="status2"><label for="status2">임시보호</label>
               		 <input type="radio" name="current_status" value="구조동물" id="status3"><label for="status3">구조 동물</label>
                	 <input type="radio" name="current_status" value="기타" id="status4"><label for="status4">기타</label>
                </c:if>
                <c:if test="${'임시보호' eq article.currentStatus }">
              		<input type="radio" name="current_status" value="반려동물" id="status1"><label for="status1">반려동물</label>
                	<input type="radio" name="current_status" value="임시보호" id="status2" checked="checked"><label for="status2">임시보호</label>
                	<input type="radio" name="current_status" value="구조동물" id="status3"><label for="status3">구조 동물</label>
                	<input type="radio" name="current_status" value="기타" id="status4"><label for="status4">기타</label>
                </c:if>
                <c:if test="${'구조동물' eq article.currentStatus }">
                	<input type="radio" name="current_status" value="반려동물" id="status1"><label for="status1">반려동물</label>
                	<input type="radio" name="current_status" value="임시보호" id="status2"><label for="status2">임시보호</label>
               		<input type="radio" name="current_status" value="구조동물" id="status3"  checked="checked"><label for="status3">구조 동물</label>
               		<input type="radio" name="current_status" value="기타" id="status4"><label for="status4">기타</label>
                </c:if>
                <c:if test="${'기타' eq article.currentStatus }">
                	<input type="radio" name="current_status" value="반려동물" id="status1"><label for="status1">반려동물</label>
                	<input type="radio" name="current_status" value="임시보호" id="status2"><label for="status2">임시보호</label>
               		<input type="radio" name="current_status" value="구조동물" id="status3"><label for="status3">구조 동물</label>
               		<input type="radio" name="current_status" value="기타" id="status4" checked="checked"><label for="status4">기타</label>
                </c:if>
            </div>

            <div>
                <div>종<span>*</span></div>
                <br>
                <c:if test="${'bird' eq article.type }">
	                <input type="radio" name="type" value="bird" id="bird" checked="checked"><label for="bird">조류</label>
	                <input type="radio" name="type" value="dog" id="dog"><label for="dog">강아지</label>
	                <input type="radio" name="type" value="cat" id="cat"><label for="cat">고양이</label>
	                <input type="radio" name="type" value="sortEtc" id="sortEtc"><label for="sortEtc">기타</label>
                </c:if>
                <c:if test="${'dog' eq article.type }">
	                <input type="radio" name="type" value="bird" id="bird"><label for="bird">조류</label>
	                <input type="radio" name="type" value="dog" id="dog" checked="checked"><label for="dog">강아지</label>
	                <input type="radio" name="type" value="cat" id="cat"><label for="cat">고양이</label>
	                <input type="radio" name="type" value="sortEtc" id="sortEtc"><label for="sortEtc">기타</label>
                </c:if>
                <c:if test="${'cat' eq article.type }">
	                <input type="radio" name="type" value="bird" id="bird"><label for="bird">조류</label>
	                <input type="radio" name="type" value="dog" id="dog"><label for="dog">강아지</label>
	                <input type="radio" name="type" value="cat" id="cat" checked="checked"><label for="cat">고양이</label>
	                <input type="radio" name="type" value="sortEtc" id="sortEtc"><label for="sortEtc">기타</label>
                </c:if>
                <c:if test="${'sortEtc' eq article.type }">
	                <input type="radio" name="type" value="bird" id="bird"><label for="bird">조류</label>
	                <input type="radio" name="type" value="dog" id="dog"><label for="dog">강아지</label>
	                <input type="radio" name="type" value="cat" id="cat"><label for="cat">고양이</label>
	                <input type="radio" name="type" value="sortEtc" id="sortEtc" checked="checked"><label for="sortEtc">기타</label>
                </c:if>     
            </div>

            <div>
                <div>성별<span>*</span></div>
                <br>
                <c:if test="${'암컷' eq article.gender }">
	                  <input type="radio" name="gender" value="암컷"  id="female" checked="checked"><label for="female">암컷</label>
		              <input type="radio" name="gender" value="수컷" id="male"><label for="male">수컷</label>
		              <input type="radio" name="gender" value="모름" id="fm"><label for="fm">모름</label>
                </c:if>   
                <c:if test="${'수컷' eq article.gender }">
	                  <input type="radio" name="gender" value="암컷"  id="female"><label for="female">암컷</label>
		              <input type="radio" name="gender" value="수컷" id="male" checked="checked"><label for="male">수컷</label>
		              <input type="radio" name="gender" value="모름" id="fm"><label for="fm">모름</label>
                </c:if>   
                <c:if test="${'모름' eq article.gender }">
	                  <input type="radio" name="gender" value="암컷"  id="female"><label for="female">암컷</label>
		              <input type="radio" name="gender" value="수컷" id="male"><label for="male">수컷</label>
		              <input type="radio" name="gender" value="모름" id="fm" checked="checked"><label for="fm">모름</label>
                </c:if>   
            </div>

            <div>
                <div>중성화 유/무<span>*</span></div>
                <br>
                <c:if test="${'O' eq article.neutering }">
		            <input type="radio" name="neutering" value="O" id="presence" checked="checked"><label for="presence">유</label>
	                <input type="radio" name="neutering" value="X" id="absence"><label for="absence">무</label>
	                <input type="radio" name="neutering" value="모름" id="fm"><label for="fm">모름</label>
                </c:if> 
                <c:if test="${'X' eq article.neutering }">
		            <input type="radio" name="neutering" value="O" id="presence"><label for="presence">유</label>
	                <input type="radio" name="neutering" value="X" id="absence" checked="checked"><label for="absence">무</label>
	                <input type="radio" name="neutering" value="모름" id="fm"><label for="fm">모름</label>
                </c:if>    
               	<c:if test="${'모름' eq article.neutering }">
		            <input type="radio" name="neutering" value="O" id="presence"><label for="presence">유</label>
	                <input type="radio" name="neutering" value="X" id="absence"><label for="absence">무</label>
	                <input type="radio" name="neutering" value="모름" id="fm" checked="checked"><label for="fm">모름</label>
                </c:if>  
            </div>

            <div>
                <label for="age">나이<span>*</span></label>
                <br>
                <input type="text" id="age" placeholder="모를 경우 (모름)" name="age" value="${article.age }" >
            </div>

            <div>
                <label for="weight">몸무게<span>*</span></label>
                <br>
                <input type="text" id="weight" placeholder="모를 경우 (모름)" name="weight" value="${article.weight }">
            </div>

            <div>
                <label for="image">사진<span>*</span></label>
                <br>
                <input type="file" id="image" name="image" multiple="multiple">
            </div>

            <div>
                <label for="health_status">건강 상태<span>*</span></label>
                <br>
                <textarea name="health_status" id="health_status" rows="7">${article.healthStatus }</textarea>
            </div>

            <div>
                <label for="personality">성격</label>
                <br>
                <textarea name="personality" id="personality" rows="7">${article.personality }</textarea>
            </div>

            <div>
                <label for="deadline">후원 마감일: ${article.deadline }</label>
                <input type="hidden" id="deadline" name="deadline" value="${article.deadline }">
            </div>

            <div>
                <label for="bank_name">후원 계좌 은행<span>*</span></label>
                <br>
                <input type="text" id="bank_name" name="bank_name" value="${article.bankName }" readonly="readonly">
            </div>

            <div>
                <label for="acc_holder">후원 계좌 예금주 이름<span>*</span></label>
                <br>
                <input type="text" id="acc_holder" name="acc_holder" value="${article.accHolder }" readonly="readonly">
            </div>

            <div>
                <label for="acc_num">후원 계좌 번호<span>*</span></label>
                <br>
                <input type="text" id="acc_num" name="acc_num" value="${article.accNum }" readonly="readonly">
            </div>

            <div>
                <label for="due_date">후원금 사용 마감일<span>*</span></label>
                <br>
                <input type="date" id="due_date" name="due_date" value="${article.dueDate }">
            </div>

            <div>
                <label for="use_plan">후원금 사용 계획<span>*</span></label>
                <br>
                <textarea name="use_plan" id="use_plan" rows="7" placeholder="예) 병원 : 550,000">${article.usePlan }</textarea>
            </div>

            <div>
                <label for="other_text">기타</label>
                <br>
                <textarea name="other_text" id="other_text" rows="7">${article.otherText }</textarea>
            </div>

            <div class="btn">
                <input type="button" value="Update" onClick="AnimalArticleUpdate()">
            </div>
        </form>
    </div>
</body>

</html>