//input태그 포커스 아웃시 널이면 빨간색, 아니면 초록색
//가입창
const inputs = document.querySelectorAll('input');
const address_kakao = document.querySelector('.address_kakao');
const address_detail = document.querySelector('.address_detail');
const user_adr = document.querySelector('.user_adr');
const user_role = document.querySelector('.user_role');

const join_email = document.getElementById('join_email');
const join_pw = document.getElementById('join_pw');
const join_pw2 = document.getElementById('join_pw2');
const user_nm = document.getElementById('user_nm');
const user_group = document.getElementById('user_group');

const pw = document.querySelector(".pw");
const pw2 = document.querySelector(".pw2");
const warning = document.querySelector(".warning");



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

join_email.addEventListener('focusout', async function() {

	const data = {
		user_email: join_email.value,
	};

	await fetch("http://localhost:8181/checkId", {
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
		join_email.value ='';
		join_email.style.fontSize = "15px";
		join_email.placeholder = "중복된 아이디 입니다.";
		join_email.style.borderBottom = "1px solid red";
	
	} else {

		if (regex.test(join_email.value)) {

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

address.addEventListener('focusout', function(){
	
	const data = {
		user_email: address.value,
	};
	
	
	fetch("http://localhost:8181/changePE", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	}).then((response) => {
			return response.text();
		})
		.then((result) => {
			console.log(result);
			if(result!=="exist"){
				address.value="";
				address.placeholder="존재하지 않는 이메일 입니다.";
				address.focus();
			}
		});
	
})


//난수 받아오기
random.addEventListener('click', async function() {

	await fetch("http://localhost:8181/random")

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


	await fetch("http://localhost:8181/mail", {
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


