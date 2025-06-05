document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita la recarga de la página

    const formData = new FormData(event.target)
    fetch("/api/v1/auth/login-web", {
            method: "POST",
            body: formData
    })
    .then(response => {
        if (!response.ok){
            throw new Error("Error en las credenciales");
        }
            return response.json();
    })
    .then(responseJson => {
        localStorage.setItem("refreshToken", responseJson.refresh_token);
        localStorage.setItem("token", responseJson.token);

        const claimsPosition = responseJson.claims.position;

        if(claimsPosition == "Admin"){
           return window.location.href= "/admin/dash_admin"
        }

        return window.location.href = "/public/index"

    })
        .catch(error => {
            alert(error.message)
        })
});
