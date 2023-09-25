const modal1 = document.querySelector(".modal1");
const modal2 = document.querySelector(".modal2");
const modal3 = document.querySelector(".modal3");
const modal4 = document.querySelector(".modal4");

function openModal1() {
	modal1.classList.add('active');
}

function closeModal1() {
	modal1.classList.remove('active');
	window.location.reload();
}


function openModal2() {
	modal2.classList.add('active');
}

function closeModal2() {
	modal2.classList.remove('active');
	window.location.reload();
}


function openModal3() {
	modal3.classList.add('active');
}

function closeModal3() {
	modal3.classList.remove('active');
	if (user_auth_yn.value === 'n') {
		join_email.value = '';
		join_email.focus();
	}
}

function openModal4() {
	modal4.classList.add('active');
}

function closeModal4() {
	modal4.classList.remove('active');
}



document.querySelector(".modal1").addEventListener("keydown", function(e) {
  if (e.key === "Escape") {
    // 모달창 닫기
    closeModal1();
  }
});

document.querySelector(".modal2").addEventListener("keydown", function(e) {
  if (e.key === "Escape") {
    // 모달창 닫기
    closeModal2();
  }
});

document.querySelector(".modal3").addEventListener("keydown", function(e) {
  if (e.key === "Escape") {
    // 모달창 닫기
    closeModal3();
  }
});

document.querySelector(".modal4").addEventListener("keydown", function(e) {
  if (e.key === "Escape") {
    // 모달창 닫기
    closeModal4();
  }
});