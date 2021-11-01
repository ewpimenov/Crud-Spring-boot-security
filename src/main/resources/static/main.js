
alert("привет, все ок");

    fetch('http://localhost:8080/admin').then(
        res => {
            res.json().then(
                data => {
                    if (data.length > 0) {
                        data.forEach((u) => {
                            addUserRow(u);
                        })
                    }
                })
        }
    )


function addUserRow(u) {
    let temp = "";
    temp += `<tr id = 'userDataId-${u.id}'>`;
    temp += "<td class='userData'>" + u.id + "</td>";
    temp += "<td class='userData'>" + u.firstName + "</td>";
    temp += "<td class='userData'>" + u.lastName + "</td>";
    temp += "<td class='userData'>" + u.age + "</td>";
    temp += "<td class='userData'>" + u.username + "</td>";
    temp += "<td>" + JSON.stringify(u.roles) + "</td>";
    temp += "<td> <button class=\"btn btn-info\" data-target=\"#editModal\" id=\"#updateForm\" onclick='editOpenModal(this)' data-toggle=\"modal\" type=\"button\">Edit</button></td>";
    temp += "<td> <button class=\"btn btn-danger\" data-target=\"#deleteModal\" onclick='delOpenModal(this)' data-toggle=\"modal\" type=\"button\">Delete</button></td></tr>";

    document.getElementById("data").insertAdjacentHTML('beforeend', temp);
}

function add() {
    let addForm = document.querySelector('#addForm');

    addForm.onsubmit = async (e) => {
        e.preventDefault();

        const newUser = {
            id: document.getElementById('id').value,
            lastName: document.getElementById('lastName').value,
            firstName: document.getElementById('firstName').value,
            age: document.getElementById('age').value,
            username: document.getElementById('username').value,
            password: document.getElementById('password').value,
            roles: [],
        }

        /*  let select = document.getElementById('roles');
          let selected = [...select.selectedOptions]
              .map(option => option.value);
         newUser.roles.push(selected);
*/

        /*newUser.roles.push(
            {
                "id": 1,
                "role": "ADMIN",
                "authority": "ADMIN"
            },
            {
                "id": 2,
                "role": "USER",
                "authority": "USER"
            })*/


//тебе надо поправить только то, что для отмеченных объектов
// надо добавлять в массив ролей  объект роли со всеми необходимыми полями



        await fetch(`http://localhost:8080/admin`, {
            method: 'POST',
            body: JSON.stringify(newUser),
            headers: {
                'Content-Type': 'application/json'
            }
        })

        addUserRow(newUser);
        let triggerE1 = document.querySelector('a[href="#Users table"]')
        let tabTrigger = new bootstrap.Tab(triggerE1)
        tabTrigger.show();
    }
}

function editOpenModal(button) {
    let editUserRow = button.parentElement.parentElement
    let editRow = Array.from(editUserRow.querySelectorAll('.userData'));
    let userData = [];
    let formInputs = Array.from(editModal.querySelectorAll('input'));
    for (let node of editRow) {
        userData.push(node.textContent)
    }
    for (let i = 0; i < userData.length; i++) {
        formInputs[i].setAttribute('value', userData[i])
    }
}

async function edit(event, id) {
    event.preventDefault();

    let editModalInputs = Array.from(document.querySelectorAll('.editModalForm'));
    console.log(editModalInputs);

    let formData = new FormData(document.querySelector('.editModalForm form'));
    let editUserData = {
        id: formData.get('id'),
        firstName: formData.get('firstName'),
        lastName: formData.get('lastName'),
        age: formData.get('age'),
        username: formData.get('username'),
        password: formData.get('password'),
        roles: formData.getAll('roles'),
    }

    fetch(`http://localhost:8080/admin/${id}`, {
        method: 'PUT',
        body: JSON.stringify(editUserData),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => {
            console.log('editRow');
            $('#editModal').modal('hide')
            let editRow = document.querySelector(`#userDataId-${id}`);

        }).catch(err => {
        console.error(err)
    });
}

function delOpenModal(button) {
    let getUserRow = button.parentElement.parentElement
    let getRow = Array.from(getUserRow.querySelectorAll('.userData'));
    let userData = [];
    let formInputs = Array.from(deleteModal.querySelectorAll('input'));
    for (let node of getRow) {
        userData.push(node.textContent)
    }
    for (let i = 0; i < userData.length; i++) {
        formInputs[i].setAttribute('value', userData[i])
    }
}

async function del(event, id) {
    event.preventDefault();

    let deleteModal = document.querySelector('.deleteModalForm');

    deleteModal.onsubmit = async (e) => {
        e.preventDefault();

        fetch(`http://localhost:8080/admin/${id}`, {
            method: 'DELETE',
        }).then(() => {
            console.log('removed');
            $('#deleteModal').modal('hide')
            let deleteRow = document.querySelector(`#userDataId-${id}`);
            deleteRow.remove();

        }).catch(err => {
            console.error(err)
        });
    }
}
