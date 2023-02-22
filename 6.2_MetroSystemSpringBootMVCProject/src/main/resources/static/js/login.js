/**
 * 
 */
let container = document.getElementById("container");

toggle = () => {
	container.classList.toggle("sign-in");
	container.classList.toggle("sign-up");
};

if (container.classList.contains("sign-up")) {
	setTimeout(() => {
		container.classList.add("sign-up");
	}, 200);
}
else {
	setTimeout(() => {
		container.classList.add("sign-in");
	}, 200);
}