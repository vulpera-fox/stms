
const timeOut = document.querySelector(".time-out");
let second = 0;
let minutes = 0;
let seconds = 0;

const timeFunction = function() {
	fetch("http://localhost:8181/timeOut")
		.then((response) => {
			return response.json();
		})
		.then((result) => {
			//console.log(result);
			if (result != 0) {

				second = result;
				minutes = second / 60;
				seconds = second % 60;
				timeOut.value = (parseInt(minutes) >= 10 ? parseInt(minutes) : "0" + parseInt(minutes))
					+ " : " + (parseInt(seconds) >= 10 ? parseInt(seconds) : "0" + parseInt(seconds));

				if (second == 0) {
					fetch("http://localhost:8181/user/logout")
					clearInterval(intervalId);
					window.location.reload();
				}

			}
		});
};

/* 로그인 전 상태 */
if ($('#role').val() != '') {

	intervalId = setInterval(timeFunction, 1000);

}