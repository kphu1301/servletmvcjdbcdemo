<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<style>
		* {
			box-sizing: border-box;
		}
		
		body {
			height: 100vh;
			width: 100vw;
			background-color: #d3d3d3;
			display: flex;
			flex-direction: column;
			align-items: center;
		}
		.foobar-container {
			width: 100%;
			height: 100%;
			min-width: 600px;
			max-width: 1024px;
			display: flex;
			flex-direction: column;
		
		}
		
		h1 {
			padding: 0;
			margin: 0;
			align-self: center;
		}
		
		.row {
			display: flex;
			justify-content: center;
			align-items: center;
		}
		
		.row:nth-child(even) {
			background-color: orange;
		}
		
		.row.header-row {
			background-color: #5959de;
		}
		
		.col {
			flex: 1;
			text-align: center;
		}
		
		.col.header {
			font-size: 20px;
		}
		
		.form-container {
			position: fixed;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			background-color: rgba(83, 83, 83,0.5);
		}
		
		.student-form {
			background-color: lightblue;
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			display: flex;
			flex-direction:column;
			justify-content: center;
			align-items: center;
			border-radius: 10px;
			padding: 10px 24px;
		}
		
		.form-input {
			display: flex;
			justify-content: space-evenly;
			align-items: center;
			width: 100%;
			margin-bottom: 4px;
		}
		
		.form-input > label {
			flex: 1;
		}
		
		.form-input > input {
			flex: 2;
		}
		
		.btn {
			font-size: 18px;
			padding: 4px 10px;
			border-radius: 8px;
			border-style: solid;
		}
		
		.btn:hover {
			cursor: pointer;
			transform: scale(1.2);
		}
		
		#add-student-btn {
			float: right;
			background-color: lightgreen;
			border-color: darkgreen;
		}
		
		#submit-btn {
			background-color: #1E90FF;
			border-color: blue;
		}
		
		#cancel-btn {
			background-color: #ff8f66;
			border-color: red;
		}
		
		.action-container {
			display: flex;
			justify-content: flex-end;
			align-items: center;
		}
		
		.action-btn {
			font-size: 16px;
			padding: 1px 3px;
			margin: 0;
			border-radius: 5px;
		}
		
		#edit-btn {
			padding: 1px 8px;
			background-color: #FFFF66;
			border-color: #CCCC00;
		}
		
		#delete-btn {
			background-color: #ff8f66;
			border-color: red;
		}
		
		#form-title {
			margin: 0;
		}
		.hidden {
			display: none;
		}
		
	</style>
	<title>University of Life</title>
</head>
<body>
	<div class="foobar-container">
		<h1>University of Life</h1>
		<div class="add-btn-container">
			<button type="button" class="btn" id="add-student-btn">Add Student</button>
		</div>
		<div class="row header-row">
		<span class="col header">ID</span>
			<span class="col header">First Name</span>
			<span class="col header">Last Name</span>
			<span class="col header">Email</span>ff
			<span class="col header">Action</span>
		</div>
		<c:forEach var="student" items="${student_list}" >
			<div class="row">
				<span class="col">${student.id}</span>
				<span class="col">${student.firstName}</span>
				<span class="col">${student.lastName}</span>
				<span class="col">${student.email}</span>
				<div class="col action-container">
					<button type="button" class="btn action-btn" id="edit-btn">Edit</button>
					<button type="button" class="btn action-btn" id="delete-btn">Delete</button>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="form-container hidden">
		<form class="student-form" action="StudentControllerServlet" method="post">
			<h3 id="form-title">Add Student</h3>
			<input type="hidden" name="student-id" id="student-id">
			<div class="form-input">
				<label for="first-name">First Name:</label>
				<input class="form-input" name="first-name" id="first-name" type="text" placeholder="Enter First Name">
			</div>
			<div class="form-input">
				<label for="last-name">Last Name:</label>
				<input class="form-input" name="last-name" id="last-name" type="text" placeholder="Enter Last Name">
			</div>
			<div class="form-input">
				<label for="email">Email:</label>
				<input class="form-input" name="email" id="email" type="email" placeholder="Enter Email">
			</div>
			<input type="hidden" name="command" id="command" value="LIST">
			<div class="form-input ">
				<input class="btn" id="submit-btn" type="submit" value="Submit">
				<button class="btn" id="cancel-btn" type="button">Cancel</button>
			</div>
		</form>
	</div>
	
	<script src="app.js"></script>
</body>
</html>