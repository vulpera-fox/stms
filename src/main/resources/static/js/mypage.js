const dropbox = document.querySelector('.file_box');
const input_filename = document.querySelector('.file_name');

//const file_btn = document.querySelector('.upload_btn');
let file_data;

const authBtn = document.querySelector('.authBtn');
const authInput = document.querySelector('.authInput');
const profileImg = document.querySelector('.profileImg');
const org_file_nm = document.querySelector('#org_file_nm');
const selectFile = document.querySelector('.selectFile');

const user_pw = document.querySelector('.user_pw');
const user_pw2 = document.querySelector('.user_pw2');


//박스 안에 drag 하고 있을 때
dropbox.addEventListener('dragover', function(e) {
	e.preventDefault();
	this.style.backgroundColor = 'rgb(13 110 253 / 25%)';
});

//박스 밖으로 drag가 나갈 때
dropbox.addEventListener('dragleave', function(e) {
	this.style.backgroundColor = 'white';
});

//박스 안에 drop 했을 때
dropbox.addEventListener('drop', async function(e) {
	e.preventDefault();
	//데이터 크기 검사  
	let byteSize = e.dataTransfer.files[0].size;
	let maxSize = 50;

	if (byteSize / 1000000 > maxSize) {
		alert("파일은 최대 50MB이하만 허용됩니다");
		return;
	} else {


		//파일 데이터를 변수에 저장
		file_data = e.dataTransfer.files[0];

		//객체업로드
		formData = new FormData();
		formData.append('file_data', file_data);

		input_filename.dataset.value = file_data.name;//div태그 벨류 바꿔주기
		await fetch('/uploadProfile', { method: 'post', body: formData })
			.then(response => response.text())
			.then(data => {
				alert(data);
			})
			.catch(err => alert('업로드에 실패했습니다:' + err));

		this.style.border = "2px solid rgb(10 128 255)";

		//파일 이름을 text로 표시


	}
	profileImg.src = "https://demo-jun-hee2.s3.ap-northeast-2.amazonaws.com/" + file_data.name;
	org_file_nm.value = file_data.name;
});

selectFile.addEventListener('change', async function(e) {
	e.preventDefault();
	//데이터 크기 검사  
	let byteSize = e.target.files[0].size;
	let maxSize = 50;

	if (byteSize / 1000000 > maxSize) {
		alert("파일은 최대 50MB이하만 허용됩니다");
		return;
	} else {


		//파일 데이터를 변수에 저장
		file_data = e.target.files[0];

		//객체업로드
		formData = new FormData();
		formData.append('file_data', file_data);

		input_filename.dataset.value = file_data.name;//div태그 벨류 바꿔주기
		await fetch('/uploadProfile', { method: 'post', body: formData })
			.then(response => response.text())
			.then(data => {
				alert(data);
			})
			.catch(err => alert('업로드에 실패했습니다:' + err));

		dropbox.style.border = "2px solid rgb(10 128 255)";
		//파일 이름을 text로 표시


	}
	profileImg.src = "https://demo-jun-hee2.s3.ap-northeast-2.amazonaws.com/" + file_data.name;
	org_file_nm.value = file_data.name;
});


user_pw2.addEventListener('focusout', function(){
	
	if(user_pw.value != user_pw2.value){
		
	alert('비밀번호가 일치하지 않습니다.');
	user_pw.value='';
	user_pw2.value='';
	user_pw.focus();
	}
	
})

function myLeave() {
  var result = confirm("정말로 탈퇴하시겠습니까?");
  if (result === true) {
	 window.location.href = "/user/delete";
  }
}
