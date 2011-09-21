var toProject, fromProject
var addToProject, removeFromProject

function addListeners() {
	toProject = document.getElementById('toProject')
	fromProject = document.getElementById('fromProject')
	
	addToProject = document.getElementById('addToProject')
	removeFromProject = document.getElementById('removeFromProject')
	
	toProject.addEventListener('change', valToProject, false)
	fromProject.addEventListener('change', valFromProject, false)
}

function valToProject() {
	if (toProject.value != "") {
		addToProject.disabled = false
		return true
	}
	else {
		addToProject.disabled = true
		return false
	}
}

function valFromProject() {
	if (fromProject.value != "") {
		removeFromProject.disabled = false
		return true
	}
	else {
		removeFromProject.disabled = true
		return false
	}
}