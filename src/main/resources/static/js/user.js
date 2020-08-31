const tbody = document.getElementById('data');
console.log(tbody);

function oneUser() {
    const tbodyProf = document.getElementById('data') //'tbody_profile'
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

oneUser()
