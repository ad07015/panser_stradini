/*
 * Name
 * Description
 * Client
 */

var name, client
var errorName, errorClient

function addListeners() {
	name = document.getElementById('name')
	client = document.getElementById('client')
	
	errorName = document.getElementById('errorName')
	errorClient = document.getElementById('errorClient')
	
	name.addEventListener('keyup', valName, false)
	client.addEventListener('keyup', valClient, false)
}

function valName() {
	if (name.value.length > 0) {
		errorName.innerHTML = ""
		name.style.backgroundColor = normalColor
		return true
	}
	else {
		errorName.innerHTML = textName
		name.style.backgroundColor = errorColor
		name.focus()
		return false
	}
}

function valClient() {
	if (client.value.length > 0) {
		errorClient.innerHTML = ""
		client.style.backgroundColor = normalColor
		return true
	}
	else {
		errorClient.innerHTML = textClient
		client.style.backgroundColor = errorColor
		client.focus()
		return false
	}
}

function valProject() {
	if (valName() & valClient()) return true
	else return false
}
