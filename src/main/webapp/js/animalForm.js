function getFormatDate(date){
    		var year = date.getFullYear();
    		var month = (1+date.getMonth());
    		month = month >= 10 ? month : '0' + month;
    		var day = date.getDate();
    		day = day >= 10 ? day : '0' + day;
    		return year + '-' + month + '-' + day;
    	}
    
    	function AnimalArticleCreate() {
    		if(form.title.value==""){
    			alert("제목을 입력하십시오.");
                form.title.focus();
                return false;
    		}
    		if(form.name.value==""){
    			alert("이름을 입력하십시오.");
                form.name.focus();
                return false;
    		}
    		if(form.area.value==""){
    			alert("지역을 입력하십시오.");
                form.area.focus();
                return false;
    		}
    		if(form.age.value==""){
    			alert("나이를 입력하십시오.");
                form.age.focus();
                return false;
    		}
    		if(form.weight.value==""){
    			alert("몸무게를 입력하십시오.");
                form.weight.focus();
                return false;
    		}
    		var fileCheck = document.getElementById("image").value;
    		if(!fileCheck){
    			alert("사진을 첨부하십시오.");
    			return false;
    		}
    		if(form.health_status.value==""){
    			alert("건강상태를 입력하십시오.");
                form.health_status.focus();
                return false;
    		}
    		if(form.deadline.value==""){
    			alert("후원 마감일을 입력하십시오.");
                form.deadline.focus();
                return false;
    		}
    		//deadline 유효성 검사
    		var deadline = document.getElementById("deadline").value;
    		var date = new Date();
    		var minDate = getFormatDate(date);
    		date.setDate(date.getDate() + 40);
    		var maxDate = getFormatDate(date);
    		if(deadline < minDate){
    			alert("오늘 이후의 날짜를 선택하십시오.");
    			form.deadline.focus();
    			return false;
    		}
    		if(deadline > maxDate){
    			alert("+40일까지 선택 가능합니다.");
    			form.deadline.focus();
    			return false;
    		}
    		if(form.bank_name.value==""){
    			alert("계좌 은행을 입력하십시오.");
                form.bank_name.focus();
                return false;
    		}
    		if(form.acc_holder.value==""){
    			alert("예금주 이름을 입력하십시오.");
                form.acc_holder.focus();
                return false;
    		}
    		if(form.acc_num.value==""){
    			alert("계좌 번호을 입력하십시오.");
                form.acc_num.focus();
                return false;
    		}
    		if(form.due_date.value==""){
    			alert("사용 마감일을 입력하십시오.");
                form.due_date.focus();
                return false;
    		}
    		var due_date = document.getElementById("due_date").value;
    		var date = new Date(deadline);
    		date.setDate(date.getDate() + 30);
    		maxDate = getFormatDate(date);
    		//due_date 유효성 검사
    		if(due_date<=deadline){
    			alert("마감일 이후의 날짜를 선택하십시오.");
    			form.due_date.focus();
    			return false;
    		}
    		if(due_date > maxDate){
    			alert("후원금은 한달 내에 사용해야 합니다.");
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