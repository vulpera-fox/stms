const modal = document.querySelector(".modal");
const modal2 = document.querySelector(".modal2");
const modal3 = document.querySelector(".modal3");

function openModal() {
	modal.classList.add('active');
}

function closeModal() {
	modal.classList.remove('active');
}


function openModal2() {
	modal2.classList.add('active');
}

function closeModal2() {
	modal2.classList.remove('active');
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


document.querySelector(".modal").addEventListener("keydown", function(e) {
  if (e.key === "Escape") {
    // 모달창 닫기
    closeModal();
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