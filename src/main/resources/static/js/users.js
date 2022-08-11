// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUsers()
  $('#users').DataTable();

  updateEmailUser();
})

function updateEmailUser() {
    document.getElementById('txt-email-user').outerHTML = localStorage.email;
}

async function loadUsers() {
    const request = await fetch('api/users', {
        method: 'GET',
        headers: getHeaders()
    })

    const users = await request.json()
    console.log(users)

    let listHTML = ""

    for (let user of users) {

        let txtPhone = user.phone === null ? '-' : user.phone

        let userHTML = `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name} ${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${txtPhone}</td>
                    <td>
                        <a
                            href="#"
                            class="btn btn-danger btn-circle btn-sm"
                            onclick="deleteUser(${user.id})"
                        >
                            <i class="fas fa-trash"></i>
                        </a>
                    </td>
                </tr>
            `

            listHTML += userHTML
    }
    document.querySelector('#user-list  tbody').outerHTML = listHTML
}

async function deleteUser(id) {

    if (!confirm('💀 Desea eliminar este usuario ❓')) {
        return;
    }

   const request = await fetch(`api/users/${id}`, {
           method: 'DELETE',
           headers: getHeaders()
       })

       location.reload()
}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.toke
    }
}