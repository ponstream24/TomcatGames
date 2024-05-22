function playdrowSound() {
	const audio = new Audio();
	audio.src = "sound/card.mp3";
	audio.play();
}
function drow(element) {
	const url = element.dataset.src;

	if (url == null) return;

	element.classList.add("open");
	setTimeout(() => {

		playdrowSound();
		element.src = url;
	}, 500);
}

const images = document.querySelectorAll("img");

var count = 1;

function start(auto) {

	images.forEach(element => {

		if (auto) {

			setTimeout(() => {
				drow(element);
				
				showMessage();
			}, 100 * count);
			count++;
		}

		else {
			element.addEventListener('click', (e) => {
				drow(element);
				
				showMessage();
			})
		}
	});
}

const a = new Audio();
a.src = "sound/maou_bgm_piano27.mp3";
a.loop = true;
a.volume = 0.3;
a.play();