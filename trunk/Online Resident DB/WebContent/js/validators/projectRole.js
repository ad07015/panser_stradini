/*
 * Name
 * Start Date
 * End Date
 */

var startDate, endDate
var errorStartDate, errorEndDate

var regex = /^[1-2][0-9]{3}-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))$/

function addListeners() {
	startDate = document.getElementById('startDate')
	endDate = document.getElementById('endDate')
	
	errorStartDate = document.getElementById('errorStartDate')
	errorEndDate = document.getElementById('errorEndDate')
	
	startDate.addEventListener('keyup', valStartDate, false)
	endDate.addEventListener('keyup', valEndDate, false)
}

function valStartDate() {
	if (startDate.value.length > 0 && regex.test(startDate.value)) {
			errorStartDate.innerHTML = ""
			startDate.style.backgroundColor = normalColor
			return true
	}
	else {
		if (startDate.value.length == 0) errorStartDate.innerHTML = textStartDate
		else errorStartDate.innerHTML = textDateValid
		
		startDate.style.backgroundColor = errorColor
		startDate.focus()
		return false
	}
}

function valEndDate() {
	if (endDate.value.length == 0 || regex.test(endDate.value)) {
		errorEndDate.innerHTML = ""
		endDate.style.backgroundColor = normalColor
		return true
	}
	else {
		errorEndDate.innerHTML = textDateValid
		endDate.style.backgroundColor = errorColor
		endDate.focus()
		return false
	}
}

function valProjectRole() {
	if (valStartDate() & valEndDate()) return true
	else return false
}
