//input태그 포커스 아웃시 널이면 빨간색, 아니면 초록색
//가입창
const inputs = document.querySelectorAll('input');
const address_kakao = document.querySelector('.address_kakao');
const address_detail = document.querySelector('.address_detail');
const user_adr = document.querySelector('.user_adr');
const user_role = document.querySelector('.user_role');
const user_auth_yn = document.querySelector('#user_auth_yn');

const join_email = document.getElementById('join_email');
const join_pw = document.getElementById('join_pw');
const join_pw2 = document.getElementById('join_pw2');
const user_nm = document.getElementById('user_nm');
const user_group = document.getElementById('user_group');

const pw = document.querySelector(".pw");
const pw2 = document.querySelector(".pw2");
const warning = document.querySelector(".warning");

const dropbox = document.querySelector('.file_box');
const input_filename = document.querySelector('.file_name');

//const file_btn = document.querySelector('.upload_btn');
let file_data;

const authBtn = document.querySelector('.authBtn');
const authInput = document.querySelector('.authInput');
const profileImg = document.querySelector('.profileImg');
const org_file_nm = document.querySelector('#org_file_nm');
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
		org_file_nm.value=file_data.name;
});


for (var i = 0; i < inputs.length; i++) {

	
	inputs[i].addEventListener('focusout', function() {
		if (this.value === '') {
			this.style.borderBottom = "1px solid red";
		}

		else this.style.borderBottom = '1px solid #1f9eff';
	});
}


//이메일패턴
const regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
let checkId = "";
let authCheck = '';


join_email.addEventListener('focusout', async function() {

	const data = {
		user_email: join_email.value,
	};

	await fetch("http://43.200.202.17/checkId", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	})
		.then((response) => {
			return response.text();

		})

		.then((result) => {
			console.log(result);
			checkId = result;
		});

	//중복체크
	if (checkId === "overlap") {

		join_email.focus();
		join_email.value = '';
		join_email.style.fontSize = "15px";
		join_email.placeholder = "중복된 아이디 입니다.";
		join_email.style.borderBottom = "1px solid red";

	} else {

		if (regex.test(join_email.value)) {

			openModal3();


			await fetch("http://43.200.202.17/random")

				.then((response) => {
					return response.json();
				})

				.then((result) => {
					console.log(result);
					authCheck = result;
					console.log(authCheck);
				});

			// ses하다가 키 털려서 안할거같음
			//			const data = {
			//				recipient: join_email.value,
			//				message: authCheck,
			//			};

			const data = {
				address: join_email.value,
				title: "SMTS 인증번호 메일입니다.",
				message: authCheck,
			};


			fetch("http://43.200.202.17/mail", {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(data),
			})

			authBtn.addEventListener('click', function() {
				if (authInput.value == authCheck) {
					alert("일치합니다.");
					user_auth_yn.value = 'y';
					closeModal3();
				} else
					alert("일치하지 않습니다.");
			})

		} else {
			join_email.style.fontSize = "15px";
			join_email.placeholder = "이메일 형식에 맞지 않습니다.";
			email.style.borderBottom = "1px solid red";
			join_email.value = "";
			join_email.focus();
		}
	}

})




pw2.addEventListener('focusout', function() {

	if (pw.value === this.value) {

	}
	else {
		pw.style.borderBottom = "1px solid red";
		this.style.borderBottom = "1px solid red";
		pw.value = '';
		this.value = '';
		pw.style.fontSize = "15px";
		pw.placeholder = "비밀번호가 일치하지 않습니다.";
		pw2.value = '';
		pw.focus();
	}

})


address_detail.addEventListener('focusout', function() {
	user_adr.value = address_kakao.value + ' ' + address_detail.value;
})

user_role.addEventListener('change', function() {
	user_role.style.borderBottom = '1px solid #1f9eff';
})




//비번바꾸기
const random = document.querySelector(".random");
const address = document.querySelector(".address");
const title = document.querySelector(".title");
const message = document.querySelector(".message");
const checkBtn = document.querySelector(".checkBtn");
const email = document.querySelector(".email");


const hidden1 = document.querySelector(".hidden1");
const hidden2 = document.querySelector(".hidden2");
const hidden3 = document.querySelector(".hidden3");
const pw_label = document.querySelector(".pw_label");
const pw_label2 = document.querySelector(".pw_label2");
const cp_email = document.querySelector(".cp_email");


const changeBtn = document.querySelector(".changeBtn");
var changeNum = "";

address.addEventListener('focusout', function() {

	const data2 = {
		user_email: address.value,
	};


	fetch("http://43.200.202.17/changePE", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data2),
	}).then((response) => {
		return response.text();
	})
		.then((result) => {
			console.log(result);
			if (result !== "exist") {
				address.value = "";
				address.placeholder = "존재하지 않는 이메일 입니다.";
				address.focus();
			}
		});

})


//난수 받아오기
random.addEventListener('click', async function() {

	await fetch("http://43.200.202.17/random")

		.then((response) => {
			return response.json();
		})

		.then((result) => {
			console.log(result);
			changeNum = result;
		});


	const data = {
		address: address.value,
		title: "SMTS 인증번호 메일입니다.",
		message: changeNum,
	};


	await fetch("http://43.200.202.17/mail", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	})
		.then((response) => {
			if (response.ok) {
				alert('인증메일을 보냈습니다.');
			}
			console.log(data);
		})
})

checkBtn.addEventListener('click', function() {

	if (message.value == changeNum) {
		hidden1.type = "password";
		hidden2.type = "password";
		hidden3.type = "submit";
		cp_email.value = address.value;
		//message.readOnly="true"; 이거하면 리드온리로 바뀌는데 바뀌면 라벨이 동작을 안하네
		warning.innerHTML = "";
		pw_label.innerHTML = "재설정할 비밀번호";
		pw_label2.innerHTML = "비밀번호 확인";
	} else {
		warning.innerHTML = "인증번호가 일치하지 않습니다.";
	}


})

hidden2.addEventListener('focusout', function() {
	if (hidden1.value === hidden2.value) {

	} else {
		hidden1.value = '';
		hidden2.value = '';
		hidden1.focus();
	}
})


changeBtn.addEventListener('click', function() {
	if (hidden1.value === hidden2.value) {
		hidden3.type = "submit";
	} else {
		hidden1.value = '';
		hidden2.value = '';
		hidden1.focus();
	}

})

const selectFile = document.querySelector('.selectFile');
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
		org_file_nm.value=file_data.name;
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
		org_file_nm.value=file_data.name;
});




