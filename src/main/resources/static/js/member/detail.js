var modal = document.getElementById('modal');

var modalClose = document.getElementsByClassName("modal-close")[0];

let modalContent = document.getElementById('modal-content')

document.addEventListener('click', (e) => {
	if (e.target.id == 'farmAddBtn') {
		modal.style.display = "block";
	}
})

modalClose.onclick = function() {
	modal.style.display = "none";
}

document.addEventListener('click', (e) => {
	if (e.target.classList.contains('modal-close')) {
		modal.style.display = "none";
	}
})

window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
}

var maxChecked = 5;
var totalChecked = 0;

function countChecked(field) {
	let postBtn = document.getElementById("postBtn")

	if (field.checked) {
		totalChecked += 1;
	} else {
		totalChecked -= 1;
	}

	if (totalChecked > maxChecked) {
		alert("최대 5개 가능")
		field.checked = false;
		totalChecked -= 1;
	}

	if (totalChecked == 5) {
		postBtn.disabled = false;
	}

	if (totalChecked < 5) {
		postBtn.disabled = true;
	}

}
//10초
//let timer = document.getElementById('timer');
//let time = timer.value * 1000;

let minute = document.getElementById('min');
let min = minute.value;
let second = document.getElementById('sec');
let sec = second.value * 1000; //59000 5800 58

let farmDelBtn = document.getElementById('farmDelBtn');

function TIMER() {
	playtime = setInterval(function() {

		/*		10초
				time = time - 1000;
				timer.value = time/1000;
				if(timer.value <= 0){
					location.reload(true);
				}*/

		minute.value = min;
		sec = sec - 1000;
		second.value = sec / 1000;
			
		if (second.value < 0) {
			minute.value = min - 1;
			second.value = 59;
			location.reload(true);
		}

/*		if (minute.value == 0 && second.value == 59) {
			location.relaod(true);
		}*/
	}, 1000);
}

TIMER();

setTimeout(function() {
	clearInterval(playtime)
}, 180000);






