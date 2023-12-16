<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DisasterView</title>
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
        .container .desc {
            color: indianred;
        }
        .container-title {
            text-align: center;
        }
        .container .writer {
            text-align: right;
            font-style: italic;
        }

        
        .container .updateDate{
       		text-align: right;
            font-style: italic;
        }
        
        .container .update {
            text-align: right;
            margin: 10px;
        }

        .container .update>a {
            background-color: cadetblue;
            color: white;
            border: none;
            border-radius: 3px;
            text-decoration: none;
        }


        .container .declare {
            text-align: right;
            margin: 10px;
        }
        .container .declare>button {
            background-color: red;
            color: white;
            border: none;
            border-radius: 3px;
        }

        .container .declare>a {

            background-color: lightcoral;
        }
        .container .deadline {
            color: cadetblue;
            text-align: center;
        }
        .container .deadline {
            color: cadetblue;
            text-align: center;
        }
		.container .imgPost {
            width: 500px;
            margin: 0 auto;
            border: 3px solid black;
            white-space: nowrap;
            overflow-x: scroll;
            display: flex;
        }

        .container .imgPost>.item {
            margin: 10px;
        }

        .container .imgPost>.item>img {
            height: 400px;
        }
        .container .info-title {
            font-weight: bold;
            font-size: x-large;
            text-align: center;
            padding: 20px;
            margin: 20px 0;
        }
        .container .info1>div {
            display: flex;
            padding: 5px;
            margin: 5px;
        }
        .container .info .basic {
            font-weight: bold;
            width: 15%;
        }
        .container .info2>div,
        .container .info3>div {
            padding: 5px;
            margin: 5px;
        }
        .container .donation {
            text-align: center;
            padding: 30px;
            margin: 10px 0;
        }
        .container .donation .account {
            font-size: x-large;
            font-weight: bold;
            margin: 20px;
        }
        .container .donation>.account>span {
            color: cornflowerblue;
        }
        .container .donation button {
            border: none;
            border-radius: 5px;
            padding: 10px;
            color: white;
            background-color: darkblue;
            transition: all 0.3s ease;
        }
        .container .donation button:hover {
            background-color: cornflowerblue;
        }
        .container .donater table {
            text-align: center;
            margin: 50px auto;
            width: 70%;
            border-collapse: collapse;
        }
        .container .donater table th,
        .container .donater table td {
            border: 1px solid black;
            height: 30px;
            width: 50%;
        }
        .container .receipt {
            text-align: center;
            padding: 0px 0px 40px 0px;
            margin: 20px 0;
        }
        
        .container .receipt button {
            /* border: none; */
            padding: 10px 15px;
  			text-align: center;
  			font-weight: bold;
            /* color: white; */
            /* background-color: darkblue; */
        }
        
        .container .receipt button:hover {
            background-color: lightgary;
        }
        
        .container .receipt .receipt-info .basic {
            font-weight: bold;
            width: 15%;
        }

        .container .receipt .receipt-info >div {
        	text-align: left;
            padding: 5px;
            margin: 5px;
        }
        .container .comment>div {
            padding: 10px;
        }
        .container .comment>.comment-title {
            font-weight: bold;
            font-size: large;
        }
        .container .comment>form {
            text-align: right;
            padding: 10px;
        }
        .container .comment>form>button {
            background-color: yellowgreen;
            border: none;
            border-radius: 3px;
            color: white;
            padding: 5px;
        }
        .container .comment>form>button:hover {
            background-color: lightgreen;
        }


        .container .comment>.comm>.line {
            border-bottom: 1px dotted green;
        }
        
        .container .comment>.comm>.line>form>input {
            margin: 10px;
        }

        .container .comment>.comm>.line>.person {
            display: flex;
            padding: 5px;
        }

        .container .comment>.comm>.line>.person>div {
            padding: 5px;
        }

        .container .comment>.comm>.line>.person>.person-time {
            color: gray;
        }
        
        .container .comment>.comm>.line>.person-content {
            margin: 10px;
        }

        .container .comment>.comm>.line>button {

      
            border: none;
            padding: 5px;
            border-radius: 3px;
            float: right;
        }
        button {
            transition: all 0.3s ease;
        }
        button:hover {
            cursor: pointer;
        }
        
        .container .btn {
			display: flex;
			align-items: center;
			justify-content: center;
			margin: 10px;
		}
		
		.container .btn>div {
			background-color: gray;
			border-radius: 3px;
			color: white;
			transition: all 0.3s ease;
			padding: 5px;
		}
		
		.container .btn>div:hover {
			background-color: lightgray;
		}
		
		.container .btn > div > a {
			text-decoration: none;
			color: white;
		}
		
		.container .btn > div > a:hover {
			color: black;
			cursor: pointer;
		}
    </style>
    <script>
    	function loginCheck() {
    		if (${sessionScope.userId eq null}) {
    			alert('로그인 후 이용가능한 서비스입니다.');
    			window.location.href = "<c:url value='/user/login' />";
    			return;
    		}
    		else return 1;
    	}
    	
    	function commentCreate() {
			var p = loginCheck();
    		
    		if (p == 1) {
	    		if (event.target.parentElement.com_text.value == "") {
	    			alert("댓글을 입력하세요.");
	    			event.target.parentElement.com_text.focus();
	                return false;
	             }
	    		confirm('댓글을 작성하시겠습니까?');
	    		event.target.parentElement.submit();
    		}
		}
    	
    	function commentUpdate() {
    		if (event.target.parentElement.updateCommText.value == "") {
    			alert("댓글을 입력하세요.");
    			event.target.parentElement.updateCommText.focus();
                return false;
             }
    		confirm('댓글을 수정하시겠습니까?');
    		event.target.parentElement.submit();
		}
    	
    	function cancelEdit() {
    		personContent = event.target.parentElement.parentElement.getElementsByClassName("person-content");
    		
    		if (personContent[0].style.display == "none") 
    			personContent[0].style.display = "inline-block";
    		else 
    			personContent[0].style.display = "none";
    		
    		if (personContent[1].style.display == "none") 
    			personContent[1].style.display = "inline";
    		else
    			personContent[1].style.display = "none";	
    	} 
    	
    	function commUpdateEdit() {
    		event.preventDefault();
    		
    		var commmentList = document.getElementsByClassName("comm");

    		for (var comm of commmentList) {
    			personContent = comm.getElementsByClassName("person-content");
    			personContent[0].style.display = "inline-block";
    			personContent[1].style.display = "none";
    		}
    		
    		personContent = event.target.parentElement.parentElement.getElementsByClassName("person-content");
    		personContent[0].style.display = "none";
    		personContent[1].style.display = "inline";
		}
    	
    	function createDeclare() {
    		event.preventDefault();
    		var p = loginCheck();
    		
    		if (p == 1) {
    			confirm('신고하시겠습니까?');
    			location.href="<c:url value='/user/report' >
    				<c:param name='reportedId' value='${article.userId}'/>
    				<c:param name='articleId' value='${article.articleId}'/>
    				<c:param name='category' value='${article.category}'/>
    			</c:url>";
    		}
    		
    	}
    	
    	function createReceipt() {
    		var child = window.open("<c:url value='/donationList/receipt' > <c:param name='articleId' value='${article.articleId}'/> <c:param name='category' value='${article.category}'/> </c:url>", "receiptCreate", "width=640, height=400");
    		//window.location.reload();
    		//child.close();
    	}
    	
    	function updateReceipt(url) {
    		event.preventDefault();
    		loginCheck();
    		
    		var child = window.open(url, "receiptCreate", "width=640, height=400");
    		//window.location.reload();
    		//child.close();
    	}
    </script>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>
    
    <div class="container">

        <h2 class="desc">Donation for disaster</h2>

        <h2 class="container-title">${article.title }</h2>

        <div class="writer">작성자 id : ${article.userId }</div>
        
        <c:if test="${empty article.updateDate }">
        	<div class="updateDate">작성된 날짜: <fmt:formatDate value="${article.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
        </c:if>
        
        <c:if test="${not empty article.updateDate}">
        	<div class="updateDate">수정된 날짜: <fmt:formatDate value="${article.updateDate }" pattern="yyyy-MM-dd HH:mm:ss" /></div>	
        </c:if>
		
		<!-- [20221120] insert, delete 추가, 신고 수정(글쓴이는 자신을 신고x) from 나현  -->
		<c:if test="${sessionScope.userId eq article.userId }">
			<div class="update">
			  	<a href="<c:url value='/donationForm/disasterArticleUpdate' >
			  				<c:param name='userId' value='${article.userId}'/>
			  				<c:param name='articleId' value='${article.articleId}'/>
			  			</c:url>">수정하기</a>
			  			
			  	<c:if test="${not donatorList.isEmpty() }">
                	<a onclick="confirm('후원이 있는 후원글은 삭제할 수 없습니다.')">삭제하기</a>     
                </c:if>
                
                <c:if test="${donatorList.isEmpty()}">
                	 <a href="<c:url value='/donationList/socialGroupArticleDelete' >
                            <c:param name='userId' value='${article.userId}'/>
                            <c:param name='articleId' value='${article.articleId}'/>
                        </c:url>" onclick="return articleRemove();">삭제하기</a> &nbsp;     
                </c:if>  
			</div>
		</c:if>

		<c:if test="${sessionScope.userId ne article.userId }">
	        <div class="declare">
	            <button onclick="createDeclare()">신고하기</button>
	        </div>
		</c:if>
		
        <hr>

        <h2 class="deadline">[후원 마감일] ${article.deadline }</h2>

        <hr>

         <div class="imgPost">
            <c:forEach var="image" items="${article.imageList}">
                <div class="item">
               		<img src="<c:url value='/upload/${image.fileName}'/>"><br>
            	</div>
            </c:forEach>
        </div>

        <div>
            <h2 class="info-title">후원 기본 정보</h2>

            <div class="info">
                <div class="info1">
                    <div>
                        <div class="basic">지역</div>
                        <div>${article.area }</div>
                    </div>
                    
                    <div>
                        <div class="basic">재난 재해 종류</div>
                        <div>${article.type }</div>
                    </div>

                    <div>
                        <div class="basic">재난 재해 명칭</div>
                        <div>${article.name }</div>
                    </div>
					
					<div>
                        <div class="basic">피해 금액</div>
                        <div>${article.damageAmount }</div>
                    </div>
                    
                    <div>
                        <div class="basic">현재 상황</div>
                        <div>${article.situation }</div>
                    </div>
                   
                </div>
            </div>

        </div>

        <hr>

        <div>
            <h2 class="info-title">후원금 사용 계획</h2>

            <div class="info info3">
                <div>

                    <div class="basic">후원 마감일</div>
                    <div>${article.deadline }</div>
                </div>

                <div>
                    <div class="basic">후원금 사용 마감일</div>
                    <div>${article.dueDate }</div>

                </div>

                <div>
                    <div class="basic">사용 예산안</div>
                    <div>
                        ${article.usePlan }
                    </div>
                </div>

                <div>
                    <div class="basic">기타</div>
                    <div>
                        ${article.otherText }

                    </div>
                </div>
            </div>
        </div>

        <hr>

        <div class="donation">
            <div class="account">
                <span>후원 계좌 : </span>

                <span>${article.bankName }</span>
                <span>${article.accHolder }</span>
                <span>${article.accNum }</span>
            </div>
            
            <c:if test="${cTime > article.deadline  }">
            	<div class="btn">
					<div>
						<a  onclick="confirm('후원기간이 지났습니다.')">Donate now</a>
					</div>
				</div>
            </c:if>
            
            <c:if test="${cTime < article.deadline  }">
            	<div class="btn">
					<div>
						<a href="<c:url value='/donation' >
				  				<c:param name='articleId' value='${article.articleId}'/>
				  				<c:param name='category' value='${article.category}'/>
				  		</c:url>">Donate now</a>
					</div>
				</div>
            </c:if>
        </div>

        <hr>

        <div class="donater">
            <h2 class="info-title">후원금 입금 내역</h2>

            <table>

            	<tr>
                    <th>이름</th>
                    <th>후원금액(단위 : 원)</th>

                </tr>
            
            	<c:forEach var="donator" items="${donatorList}">
            		<tr>
	                    <td>${donator.userId }</td>
	                    <td>${donator.amount }</td>
                	</tr>
            	</c:forEach>
                
                <tr>

                    <th colspan="2">총액 : ${article.totalAmount }</th>

                </tr>
                
            </table>
        </div>

        <hr>

        <div class="receipt">
            <h2 class="info-title">후원금 사용 내역</h2>
			<c:if test="${empty donationReceipt.receiptId }">
            	<c:if test="${sessionScope.userId eq article.userId }">
            		<div>
                		<button onclick="createReceipt('<c:url value='/donationList/receipt' > 
                				<c:param name='articleId' value='${article.articleId}'/> 
                				<c:param name='category' value='${article.category}'/> 
                			</c:url>')">인증글 올리기</button>
            		</div>
            	</c:if>
            
            	<c:if test="${sessionScope.userId ne article.userId }">
            		<div>
                		작성자가 아직 인증을 하지 않았습니다. 
            		</div>
            	</c:if>
            </c:if>
            
            <c:if test="${not empty donationReceipt.receiptId }">
            	<c:if test="${sessionScope.userId eq article.userId }">
					<div class="update">
			  			<a href="#" onclick="updateReceipt('<c:url value='/donationForm/receiptUpdate' > 
			  										<c:param name='receiptId' value='${donationReceipt.receiptId}'/> 
			  										<c:param name='articleId' value='${article.articleId}'/> 
			  										<c:param name='category' value='${article.category}'/> 
			  										</c:url>')">수정하기</a>
			  			<a href="<c:url value='/donationList/receiptDelete' >
			  					<c:param name='articleId' value='${article.articleId}'/>
			  					<c:param name='category' value='${article.category}'/>
			  				</c:url>" onclick="confirm('인증글을 삭제하시겠습니까?')">삭제하기</a>		  
					</div>
				</c:if>
				
            	<div class="receipt-info">
            		<div class="basic">인증 내역 사진</div>
                    <div class="imgPost">
            			<c:forEach var="receiptImage" items="${receiptImageList}">
                			<img src="<c:url value='/upload/${receiptImage.imgLink}'/>"><br>
            			</c:forEach>
        			</div>
                    
                    <div class="basic">인증 내역 설명</div>
                    <div>${donationReceipt.content}</div>
                </div>
            	
            </c:if>   
            

        </div>
            
        <hr>

        <div class="comment">
            <div class="comment-title">댓글</div>

			  			
            <form name="form" method="POST" action="<c:url value='/donationList/comment' >
			  				<c:param name='articleId' value='${article.articleId}'/>
			  				<c:param name='category' value='${article.category}'/>
			  			</c:url>">
                <textarea name="com_text" style="width: 100%;" rows="5"></textarea>
                
                	<input type="button" value="댓글 작성" onclick="commentCreate()">
                </form>
            
			<c:forEach var="comm" items="${comment}">
				<div class="comm">
					<c:if test="${sessionScope.userId eq comm.userId }">
						<div class="update">
						  	<a href="#" onclick="commUpdateEdit()">수정</a>
						  		
						  	<a href="<c:url value='/donationList/commentDelete' >
						  				<c:param name='articleId' value='${article.articleId}'/>
						  				<c:param name='category' value='${article.category}'/>
						  				<c:param name='commentId' value='${comm.commentId}'/>
						  			</c:url>" onclick="confirm('댓글을 삭제하시겠습니까?')">삭제</a>		  
						</div>
					</c:if>
		
					<c:if test="${sessionScope.userId ne comm.userId }">
				        <div class="declare">
				            <button onclick="createDeclare()">신고</button>
				        </div>
					</c:if>
					
					<div class="line">
	                	<div class="person">
	                		<c:if test="${sessionScope.userId eq article.userId }">
	                    		<div class="person-id">${comm.userId}[작성자]</div>
	                    	</c:if>
	                    	<c:if test="${sessionScope.userId ne article.userId }">
	                    		<div class="person-id">${comm.userId}[후원자]</div>
	                    	</c:if>
	                    	
	                    	<c:if test="${empty comm.updateDate }">
	                    		<div class="person-time">
	                    			<fmt:formatDate value="${comm.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
	                    		</div>
	                    	</c:if>
	                    	<c:if test="${not empty comm.updateDate }">
	                    		<div class="person-time">
	                    			<fmt:formatDate value="${comm.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" />
	                    		</div>
	                    	</c:if>
	                	</div>
						
	                	<div class="person-content">${comm.content}</div>
	                	
	                	<form name="form" class="person-content" method="POST" action="<c:url value='/donationList/commentUpdate' >
						  				<c:param name='articleId' value='${article.articleId}'/>
						  				<c:param name='category' value='${article.category}'/>
						  				<c:param name='commentId' value='${comm.commentId}'/>
						  			</c:url>" style="display:none;">
						  			
			                <textarea name="updateCommText" style="width: 100%;" rows="5">${comm.content}</textarea>
			                <input type="button" value="댓글 수정" onclick="commentUpdate()">
			                <input type="button" value="취소" onclick="cancelEdit()">
			            </form>
            		</div>
            	</div>
			</c:forEach>
 

        </div>
    </div>
</body>

</html>