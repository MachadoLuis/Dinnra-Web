const card = document.getElementById("idRoom");
const token = localStorage.getItem("refreshToken");
card.addEventListener("click", function() {
    fetch("/api/v1/auth/validate-auth" , {
        method: "GET",
        headers: {"Authorization": `Bearer ${token}`}

    })
    .then(response =>{
        if(response.ok){
           return window.location.href = "/usuario/rooms";
        }
    })
})