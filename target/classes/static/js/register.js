// Call the dataTables jQuery plugin
$(document).ready(function() {
  //on ready
})

async function registerUser() {
    let date = {}

    date.name = document.getElementById('txtName').value
    date.lastName = document.getElementById('txtLastName').value
    date.email = document.getElementById('txtEmail').value
    date.password = document.getElementById('txtPassword').value

    let repeatPassword = document.getElementById('txtRepeatPassword').value

    if (repeatPassword != date.password) {
        alert('La contraseña que escribiste es diferente 😵‍💫!!')
    }

    const request = await fetch('api/users', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(date)
    })

    alert("La cuenta fue creada con exito❗")
    window.location.href = 'login.html'
}