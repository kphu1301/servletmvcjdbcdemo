const addStudentBtn = document.getElementById("add-student-btn");
const formContainer = document.querySelector(".form-container");
const submitBtn = document.getElementById("submit-btn");
const cancelBtn = document.getElementById("cancel-btn");
const firstNameEl = document.getElementById("first-name");
const lastNameEl = document.getElementById("last-name");
const emailEl = document.getElementById("email");
const editBtn = document.getElementById("edit-btn");
const deleteBtn = document.getElementById("delete-btn");
const formTitle = document.getElementById("form-title");
const studentIDEl = document.getElementById("student-id");
const formEl = document.querySelector(".student-form");
const command = document.getElementById("command");


function showAddForm(e){
	toggleForm();
	formTitle.innerText = "Add Student"
	
	firstNameEl.removeAttribute("disabled");
	lastNameEl.removeAttribute("disabled");
	emailEl.removeAttribute("disabled");
		
	firstNameEl.value = "";
	lastNameEl.value = "";
	email.value = "";
	
	studentIDEl.value = "";
	
	submitBtn.value = "Add";
	command.value = "ADD";
}

function showEditForm(e){
	toggleForm();
	formTitle.innerText = "Edit Student";

	firstNameEl.removeAttribute("disabled");
	lastNameEl.removeAttribute("disabled");
	emailEl.removeAttribute("disabled");
	
	const userInfo = e.target.parentElement.parentElement.children;
	
	studentIDEl.value = userInfo[0].innerText;
	firstNameEl.value = userInfo[1].innerText;
	lastNameEl.value = userInfo[2].innerText;
	emailEl.value = userInfo[3].innerText;
	
	submitBtn.value = "Edit";
	command.value = "EDIT";
}


function showDeleteForm(e){
	toggleForm();
	formTitle.innerText = "Delete Student"
	
	const userInfo = e.target.parentElement.parentElement.children;
	studentIDEl.value = userInfo[0].innerText;
	firstNameEl.value = userInfo[1].innerText;
	lastNameEl.value = userInfo[2].innerText;
	emailEl.value = userInfo[3].innerText;
	
	firstNameEl.setAttribute("disabled", "true");
	lastNameEl.setAttribute("disabled", "true");
	emailEl.setAttribute("disabled", "true");
	
	submitBtn.value = "Delete";
	command.value = "DELETE";
}

function toggleForm(){
	formContainer.classList.toggle("hidden");
}

addStudentBtn.addEventListener("click", showAddForm);

submitBtn.addEventListener("click", (e) => {
	toggleForm();
});

cancelBtn.addEventListener("click",	showAddForm);

formContainer.addEventListener("click", (e) => {
	if (e.target.classList.contains("form-container")){
		toggleForm();
	}
});

document.querySelector(".foobar-container").addEventListener("click", (e) => {
	if (e.target.id === "edit-btn"){
		showEditForm(e);
	}
	
	if (e.target.id === "delete-btn"){
		showDeleteForm(e);
	}
});

