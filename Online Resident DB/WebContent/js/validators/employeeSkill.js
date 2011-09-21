/*
 * Name
 * Description
 * Rating
 */

var name, rating
var errorName, errorRating

function addListeners() {
	name = document.getElementById('name')
	rating = document.getElementById('rating')
	
	errorName = document.getElementById('errorName')
	errorRating = document.getElementById('errorRating')
	
	name.addEventListener('keyup', valName, false)
	rating.addEventListener('keyup', valRating, false)
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

function valRating() {
	var regex = /^[0-9]+$/
	
	if (regex.test(rating.value)) {
		errorRating.innerHTML = ""
		rating.style.backgroundColor = normalColor
		return true
	}
	else {
		errorRating.innerHTML = textRating
		rating.style.backgroundColor = errorColor
		rating.focus()
		return false
	}
}

function valEmployeeSkill() {
	if (valName() & valRating()) return true
	else return false
}
