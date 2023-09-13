//달력
$(document).ready(function () {
	$(".datepicker").datepicker({
		closeText: "닫기",
	    currentText: "오늘",
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dateFormat: 'yy-mm-dd',
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		weekHeader: "주",
    	yearSuffix: '년',
    	showOtherMonths: true, // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
	    showMonthAfterYear: true, // 년도 먼저 나오고, 뒤에 월 표시
	    changeYear: true, // 콤보박스에서 년 선택 가능
	    changeMonth: true, // 콤보박스에서 월 선택 가능
	    yearSuffix: "년", // 달력의 년도 부분 뒤에 붙는 텍스트
	    minDate: "-1Y", // 최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	    maxDate: "+1Y", // 최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후) });
    
	});
	$(".datepicker").prop('readonly', false); //required사용 
});

//파일업로드
$(document).ready(function() {

	var fileTarget = $('.filebox .upload-hidden'); //jquery는 다중 태그이벤트도 한번에 처리
	fileTarget.on('change', function(){ //change이벤트
	if(window.FileReader){ // modern browser 
		var filename = $(this)[0].files[0].name; } 
	else { // old IE 
		var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
	} // 추출한 파일명 삽입 
	$(this).siblings('.upload-name').val(filename); });

	var imgTarget = $('.preview-image .upload-hidden'); 
	imgTarget.on('change', function() { 
		var parent = $(this).parent(); 
		// parent.children('.upload-display').remove(); 
		
		if(window.FileReader){ //image 파일만 
			if (!$(this)[0].files[0].type.match(/image\//)) return; 

			var reader = new FileReader(); 
			reader.onload = function(e){ 
				var src = e.target.result; 
				// parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb"></div></div>'); 
				parent.find(".upload-thumb-wrap").children().attr("src", src);
			} 
			reader.readAsDataURL($(this)[0].files[0]); 
		} else { 
			$(this)[0].select(); 
			$(this)[0].blur(); 
			var imgSrc = document.selection.createRange().text; 
			parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>'); 

			var img = $(this).siblings('.upload-display').find('img'); 
			img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")"; 
		} 
	});

});



