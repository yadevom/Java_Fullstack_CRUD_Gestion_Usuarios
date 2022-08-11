// Call the dataTables jQuery plugin
$(document).ready(function() {
  //on ready
})

async function logIn() {
    let date = {}

    date.email = document.getElementById('txtEmail').value
    date.password = document.getElementById('txtPassword').value

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(date)
    })

    const response = await request.text()

    if(response != 'FAIL') {
        localStorage.toke = response;
        localStorage.email = date.email;
        window.location.href = 'users.html'
    } else {
        alert("Las credenciales son incorrectas. Por favor intente nuevamente 🫣")
    }


}