/*
 * First Name
 * Last Name
 * Middle Initial
 * Level
 * Work Force
 * Enterprise ID
 */

var firstName, lastName, middleInitial
var errorFirstName, errorLastName, errorMiddleInitial

function addListeners() {
	firstName = document.getElementById('firstName')
	lastName = document.getElementById('lastName')
	middleInitial = document.getElementById('middleInitial')
	
	errorFirstName = document.getElementById('errorFirstName')
	errorLastName = document.getElementById('errorLastName')
	errorMiddleInitial = document.getElementById('errorMiddleInitial')
	
	firstName.addEventListener('keyup', valFirstName, false)
	lastName.addEventListener('keyup', valLastName, false)
	middleInitial.addEventListener('keyup', valMiddleInitial, false)
}

function valFirstName() {
	if (firstName.value.length > 0) {
		errorFirstName.innerHTML = ""
		firstName.style.backgroundColor = normalColor
		return true
	}
	else {
		errorFirstName.innerHTML = textFirstName
		firstName.style.backgroundColor = errorColor
		firstName.focus()
		return false
	}
}

function valLastName() {
	if (lastName.value.length > 0) {
		errorLastName.innerHTML = ""
		lastName.style.backgroundColor = normalColor
		return true
	}
	else {
		errorLastName.innerHTML = textLastName
		lastName.style.backgroundColor = errorColor
		lastName.focus()
		return false
	}
}

function valMiddleInitial() {
	var regex = /^[A-Z]?$/
	
	if (regex.test(middleInitial.value)) {
		errorMiddleInitial.innerHTML = ""
		middleInitial.style.backgroundColor = normalColor
		return true
	}
	else {
		errorMiddleInitial.innerHTML = textMiddleInitial
		middleInitial.style.backgroundColor = errorColor
		middleInitial.focus()
		return false
	}
}

function valEmployee() {
	if (valFirstName() & valLastName() & valMiddleInitial()) return true
	else return false
}
