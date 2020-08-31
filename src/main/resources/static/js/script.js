const tbody = document.getElementById('data');
const urlAllUsers = 'http://localhost:8081/rest/users';
const urlDeleteUser = 'http://localhost:8081/rest/delete/'
let html = "";

function foreach(e) {
    html = '<tr id="tr_' + e.id + '">' +
        '<td id="id_' + e.id + '">' + e.id + '</td>' +
        '<td id="name_' + e.id + '">' + e.name + '</td>' +
        '<td id="sName_' + e.id + '">' + e.secondName + '</td>' +
        '<td id="email_' + e.id + '">' + e.email + '</td>' +
        '<td id="age_' + e.id + '">' + e.age + '</td>';
    e.roleSet.forEach(r => {
        html += '<td id="role_' + e.id + '">' + r.roleName + '</td>';
    });
    html += '<td><button id="edit_' + e.id + '" type="button" class="btn btn-info" data-toggle="modal" data-target="#editModalCenter">Edit</button></td>' +
        '<td><button id="' + e.id + '" name="delete-btn" type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModalCenter">Delete</button></td>';
    tbody.insertAdjacentHTML('beforeend', html);
}

async function allUsers() {
    await fetch(urlAllUsers)
        .then(response => response.json())
        .then(arr => {
            arr.forEach(e => {
                foreach(e)

                document.getElementById('edit_' + e.id).addEventListener('click', () => {
                    let editId = document.getElementById('idEdit');
                    editId.setAttribute("placeholder", e.id);

                    let editFirstName = document.getElementById('firstNameEdit');
                    editFirstName.setAttribute("placeholder", e.name);

                    let editLastName = document.getElementById('lastNameEdit');
                    editLastName.setAttribute("placeholder", e.secondName);

                    let editAge = document.getElementById('ageEdit');
                    editAge.setAttribute("placeholder", e.age);

                    let editEmail = document.getElementById('emailEdit');
                    editEmail.setAttribute("placeholder", e.email);

                    let editPassword = document.getElementById('editInputPassword');

                    let editRole = document.getElementById('editFormControlSelect');
                    let selectRole;
                    e.roleSet.forEach(r => {
                        selectRole += '<option>' + r.roleName + '</option>';
                        editRole.insertAdjacentHTML('beforeend', selectRole);
                    });

                    document.getElementById('edit-primary').addEventListener('click', () => {
                        let field = {
                            name: document.getElementById('firstNameEdit').value,
                            secondName: document.getElementById('lastNameEdit').value,
                            age: document.getElementById('ageEdit').value,
                            email: document.getElementById('emailEdit').value,
                            password: document.getElementById('editInputPassword').value,
                            roleSet: document.getElementById('editFormControlSelect').value
                        };

                        fetch('http://localhost:8081/rest/edit/' + e.id, {
                            method: 'PUT',
                            body: JSON.stringify(field),
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8',
                            }
                        })
                            .then(response => response.json())
                            .then(data => console.log(data));

                        $('#editModalCenter').modal('hide');

                        $('#name_' + e.id).html(field.name);
                        $('#sName_' + e.id).html(field.secondName);
                        $('#email_' + e.id).html(field.age);
                        $('#age_' + e.id).html(field.email);
                        $('#role_' + e.id).html(field.roleSet);
                    })
                })

                document.getElementById(e.id).addEventListener('click', () => {
                    let deleteId = document.getElementById('idDelete');
                    deleteId.setAttribute("placeholder", e.id);

                    let deleteFirstName = document.getElementById('firstNameDelete');
                    deleteFirstName.setAttribute("placeholder", e.name);

                    let deleteLastName = document.getElementById('lastNameDelete');
                    deleteLastName.setAttribute("placeholder", e.secondName);

                    let deleteAge = document.getElementById('ageDelete');
                    deleteAge.setAttribute("placeholder", e.age);

                    let deleteEmail = document.getElementById('emailDelete');
                    deleteEmail.setAttribute("placeholder", e.email);

                    let deleteRole = document.getElementById('deleteFormControlSelect');
                    let selectRole;
                    e.roleSet.forEach(r => {
                        selectRole += '<option>' + r.roleName + '</option>';
                        deleteRole.insertAdjacentHTML('beforeend', selectRole);
                    });

                    let btn_primary = document.getElementById('delete-primary');
                    btn_primary.addEventListener('click', () => {
                        // document.getElementById('deleteFormControlSelect').reset();

                        fetch(urlDeleteUser + e.id, {
                            method: 'DELETE',
                        }).then(r => {
                        });
                        $('#tr_' + e.id).remove();
                        $('#deleteModalCenter').modal('hide');
                    });

                })
            })
        });
}

allUsers()

function addNewUser() {
    let but = document.getElementById('add_button');

    but.onclick = function (event) {
        event.preventDefault();

        let field = {
            name: document.getElementById('firstName').value,
            secondName: document.getElementById('lastName').value,
            age: document.getElementById('age').value,
            email: document.getElementById('emailAdd').value,
            password: document.getElementById('password').value,
            roleSet: document.getElementById('roles').value
        };

        fetch('http://localhost:8081/rest/new', {
            method: 'POST',
            body: JSON.stringify(field),
            headers: {
                'Content-type': 'application/json'
            }
        })
            .then(response => response.json())
            .then(e => {
                    foreach(e)
                }
            )
        document.getElementById('formAdd').reset();

        $('#myTab a:first').tab('show');
    }
}

addNewUser();

function oneUser() {
    const tbodyProf = document.getElementById('info') //'tbody_profile'
    function allUsers(tbodyProf) {
        let userId = document.getElementById('user_id').value;
        const url = 'http://localhost:8081/rest/user/' + userId;
        fetch(url)
            .then(response => response.json())
            .then(e => {
                let html = '<tr>' +
                    '<td>' + e.id + '</td>' +
                    '<td>' + e.name + '</td>' +
                    '<td>' + e.secondName + '</td>' +
                    '<td>' + e.email + '</td>' +
                    '<td>' + e.age + '</td>';
                e.roleSet.forEach(r => {
                    html += '<td>' + r.roleName + '</td>';
                });
                tbodyProf.insertAdjacentHTML('beforeend', html);
            })
    }
    allUsers(tbodyProf);
}

oneUser();