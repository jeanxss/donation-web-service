       function execDaumPostcode() {
           new daum.Postcode({
               oncomplete: function (data) {
                   // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
   
                   // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                   // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                   var addr = ''; // 주소 변수
                   var extraAddr = ''; // 참고항목 변수
   
                   //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                   if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                       addr = data.roadAddress;
                   } else { // 사용자가 지번 주소를 선택했을 경우(J)
                       addr = data.jibunAddress;
                   }
   
                   // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                   if (data.userSelectedType === 'R') {
                       // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                       // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                       if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                           extraAddr += data.bname;
                       }
                       // 건물명이 있고, 공동주택일 경우 추가한다.
                       if (data.buildingName !== '' && data.apartment === 'Y') {
                           extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.bsuildingName);
                       }
                       // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                       if (extraAddr !== '') {
                           extraAddr = ' (' + extraAddr + ')';
                       }
                       // 조합된 참고항목을 해당 필드에 넣는다.
                       document.getElementById("extraAddress").value = extraAddr;
   
                   } else {
                       document.getElementById("extraAddress").value = '';
                   }
   
                   // 우편번호와 주소 정보를 해당 필드에 넣는다.
                   document.getElementById('postcode').value = data.zonecode;
                   document.getElementById("address").value = addr;
   
                   // 커서를 상세주소 필드로 이동한다.
                   document.getElementById("detailAddress").focus();
               }
           }).open();
       }
    
      function userUpdate() {
         if (form.password.value == "") {
            alert("비밀번호를 입력하십시오.");
            form.password.focus();
            return false;
         }
         var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
         if(phoneExp.test(form.phone.value)==false) {
            alert("전화번호 형식이 올바르지 않습니다.");
            form.phone.focus();
            return false;
         }
         var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
         if(emailExp.test(form.email.value)==false) {
            alert("이메일 형식이 올바르지 않습니다.");
            form.email.focus();
            return false;
         }
         if(form.postcode.value == ""){
        	 alert("우편번호를 입력하십시오.");
             form.postcode.focus();
             return false;
         }
         if(form.address.value == ""){
        	 alert("주소를 입력하십시오.");
             form.address.focus();
             return false;
         }
         if(form.detailAddress.value == ""){
        	 alert("상세주소를 입력하십시오.");
             form.detailAddress.focus();
             return false;
         }
         if(form.extraAddress.value == ""){
        	 alert("참고항목을 입력하십시오.");
             form.extraAddress.focus();
             return false;
         }
         form.submit();
      }