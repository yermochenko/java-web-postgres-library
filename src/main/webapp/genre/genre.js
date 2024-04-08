const apiUrl = '/jwpl/api/genre';

let contentDiv;

function genresView(genres) {
	let genresUl = document.createElement('ul');
	genres.forEach(genre => {
		let genreLi = document.createElement('li');
		let genreSpan = document.createElement('span');
		genreSpan.appendChild(document.createTextNode(genre.name));
		genreSpan.classList.add('genre_item');
		genreSpan.id = genre.id;
		genreSpan.addEventListener('click', editGenreController);
		genreLi.appendChild(genreSpan);
		genresUl.appendChild(genreLi);
	});
	let addingGenreForm = document.createElement('form');
	let nameLabel = document.createElement('label');
	nameLabel.setAttribute('for', 'name');
	nameLabel.appendChild(document.createTextNode('Adding new genre: '))
	addingGenreForm.appendChild(nameLabel);
	let nameInput = document.createElement('input');
	nameInput.setAttribute('id', 'name');
	nameInput.setAttribute('type', 'text');
	nameInput.setAttribute('name', 'name');
	addingGenreForm.appendChild(nameInput);
	let addButton = document.createElement('button');
	addButton.setAttribute('type', 'submit');
	addButton.appendChild(document.createTextNode('Add'));
	addingGenreForm.appendChild(addButton);
	addingGenreForm.addEventListener('submit', saveGenreController);
	contentDiv.innerHTML = '';
	contentDiv.appendChild(genresUl);
	contentDiv.appendChild(addingGenreForm);
}

function genreView(genreLi, genre) {
	genreLi.innerHTML = '';
	let editingGenreForm = document.createElement('form');
	editingGenreForm.classList.add('inline_form');
	let idInput = document.createElement('input');
	idInput.setAttribute('type', 'hidden');
	idInput.setAttribute('name', 'id');
	idInput.setAttribute('value', genre.id);
	editingGenreForm.appendChild(idInput);
	let nameInput = document.createElement('input');
	nameInput.setAttribute('type', 'text');
	nameInput.setAttribute('name', 'name');
	nameInput.setAttribute('value', genre.name);
	editingGenreForm.appendChild(nameInput);
	let saveButton = document.createElement('button');
	saveButton.setAttribute('type', 'submit');
	saveButton.appendChild(document.createTextNode('OK'));
	editingGenreForm.appendChild(saveButton);
	editingGenreForm.addEventListener('submit', saveGenreController);
	genreLi.appendChild(editingGenreForm);
	let deletingGenreForm= document.createElement('form');
	deletingGenreForm.classList.add('inline_form');
	idInput = document.createElement('input');
	idInput.setAttribute('type', 'hidden');
	idInput.setAttribute('name', 'id');
	idInput.setAttribute('value', genre.id);
	deletingGenreForm.appendChild(idInput);
	let deleteButton = document.createElement('button');
	deleteButton.setAttribute('type', 'submit');
	deleteButton.appendChild(document.createTextNode('Del'));
	deletingGenreForm.appendChild(deleteButton);
	deletingGenreForm.addEventListener('submit', deleteGenreController);
	genreLi.appendChild(deletingGenreForm);
}

async function loadGenresController() {
	let response = await fetch(apiUrl);
	let genres = await response.json();
	genresView(genres);
}

async function editGenreController(event) {
	let genreSpan = event.target;
	let urlParams = new URLSearchParams();
	urlParams.append('id', genreSpan.id);
	let response = await fetch(apiUrl + '?' + urlParams);
	if(response.status === 200) {
		let genre = await response.json();
		genreView(genreSpan.parentElement, genre);
	} else {
		alert('There is no such genre');
		await loadGenresController();
	}
}

async function saveGenreController(event) {
	event.preventDefault();
	let genre = {
		name: event.target['name'].value
	};
	if(event.target['id'].value) {
		genre.id = parseInt(event.target['id'].value);
	}
	let response = await fetch(apiUrl, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(genre)
	});
	if(response.status === 204) {
		await loadGenresController();
	} else {
		alert('ERROR: can\'t save genre');
	}
}

async function deleteGenreController(event) {
	event.preventDefault();
	let urlParams = new URLSearchParams();
	urlParams.append('id', event.target['id'].value);
	let response = await fetch(apiUrl + '?' + urlParams, {
		method: 'DELETE'
	});
	if(response.status === 204) {
		await loadGenresController();
	} else {
		alert('ERROR: can\'t delete genre');
	}
}

window.addEventListener('load', async function() {
	contentDiv = document.getElementById('content');
	await loadGenresController();
});
