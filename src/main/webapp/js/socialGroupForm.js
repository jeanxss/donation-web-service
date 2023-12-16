function getFormatDate(date){
    		var year = date.getFullYear();
    		var month = (1+date.getMonth());
    		month = month >= 10 ? month : '0' + month;
    		var day = date.getDate();
    		day = day >= 10 ? day : '0' + day;
    		return year + '-' + month + '-' + day;
    	}
    
    	function SocialGroupArticleCreate() {
    		if(form.title.value==""){
    			alert("제목을 입력하십시오.");
                form.title.focus();
                return false;
    		}
    		if(form.area.value==""){
    			alert("지역을 입력하십시오.");
                form.area.focus();
                return false;
    		}
			if(form.type.value==""){
    			alert("취약 계층 유형을 입력하십시오.");
                form.type.focus();
                return false;
    		}
    		if(form.age.value==""){
    			alert("나이를 입력하십시오.");
                form.age.focus();
                return false;
    		}
    		if(form.situation.value==""){
    			alert("현재 상황을 입력하십시오.");
                form.situation.focus();
                return false;
    		}
    		if(form.due_date.value==""){
    			alert("사용 마감일을 입력하십시오.");
                form.due_date.focus();
                return false;
    		}
    		var due_date = document.getElementById("due_date").value;
    		var date = new Date();
    		var minDate = getFormatDate(date);
    		date.setDate(date.getDate() + 20);
    		var maxDate = getFormatDate(date);
    		if(due_date < minDate){
    			alert("오늘 이후의 날짜를 선택하십시오.");
    			form.due_date.focus();
    			return false;
    		}
    		if(due_date > maxDate){
    			alert("+20일까지 선택 가능합니다.");
    			form.due_date.focus();
    			return false;
    		}
    		if(form.use_plan.value==""){
    			alert("후원금 사용 계획을 입력하십시오.");
                form.use_plan.focus();
                return false;
    		}		
			form.submit();
		}